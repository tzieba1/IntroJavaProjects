import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {

  /**
   * Point of entry for execution.
   * @param args command line arguments
   */
  public static void main(String[] args) {
    //Reading data at a specified pathname returns the required simulation data arranged into an array of objects.
    Object[] data = readData("src/CustomerData_Example.txt");

    //TODO: Assign required simulation variables from Objects read from data file
    int expressLinesCount = (int)data[0]; //Simulated number of express check-out lines.
    int normalLinesCount = (int)data[1];  //Simulated number of normal check-out lines.
    int expressLimit = (int)data[2];      //Simulated express line item limit (inclusive).
    int customerCount = (int)data[3];     //Simulated number of customers.

    //TODO: Initialize customers array with the data returned from reading process.
    Customer[] customers = new Customer[customerCount]; //Simulated Customers (ordered by approach to queues) declared.
    int[] customerItems = (int[])data[4];     //Simulated number of items per Customer (ordered by approach to queues).
    for(int i = 0 ; i < customerCount ; i++)  //Initialize customers.
      customers[i] = new Customer(customerItems[i]);

    //TODO: Set-up the queues based on the data that was read in for express/normal lines.
    CheckoutLines expressLines = new CheckoutLines(expressLinesCount, LineType.EXPRESS);
    CheckoutLines normalLines = new CheckoutLines(normalLinesCount, LineType.NORMAL);

    //TODO: Enqueue customers based on the data that was read in for customer items and enqueue them to the most optimal
    // queue. Customers arrive at all possible queues (normal and express lines) in order of customerItems index.
    for(Customer customer : customers) {
      //Condition implying express line is most optimal and valid to use.
      if(expressLines.getServiceTime() <= normalLines.getServiceTime() && customer.getItems() <= expressLimit)
        expressLines.enqueueCustomer(customer);
      else  //Otherwise, only normal line is most optimal and valid to use.s
        normalLines.enqueueCustomer(customer);
    }

    System.out.println(normalLines.toString());
    System.out.println(expressLines.toString());
  }

  /**
   * Read integer values from a data file representing a simulation of Customer item amounts and express/normal Queues.
   * @return Object[] containing a combination of queue-simulation integers and an item-simulation integer array
   */
  public static Object[] readData(String pathname) {
    Object[] data = new Object[5];  //Combination of Objects (integers and an integer array) to be returned.
    try {
      Scanner scanner = new Scanner(new File(pathname));  //Scanner for datafile at specified pathname passed in.

      //TODO: Initialize store and customer data before retrieving item data per customer.
      data[0] = scanner.nextInt();  //Simulated number of express check-out lines.
      data[1] = scanner.nextInt();  //Simulated number of normal check-out lines.
      data[2] = scanner.nextInt();  //Simulated express line item limit (inclusive).
      data[3] = scanner.nextInt();  //Simulated number of customers.

      //TODO: Loop through remainder of data and input items into a regular array of fixed size based on customerCount.
      int[] customerItems = new int[(int)data[3]];
      int count = 0;
      while (scanner.hasNext()) {
        customerItems[count] = scanner.nextInt();
        count++;
      }
      data[4] = customerItems;  //Simulated number of items per Customer (ordered by approach to queues).

    } catch (FileNotFoundException ex) {
      System.out.println("Exception occurred: " + ex.getMessage());
    }
    return data;
  }

  /**
   * Method that finds the ArrayList index of the LinkedQueue of Customers that a passed in Customer most optimally
   * belongs. The group of lines represented by the ArrayList is for either express or normal lines. Note that the
   * difference in total time for each group of lines is not checked here and should be checked first using the
   * getTotalTime() method from this Main class.
   * @param queues ArrayList of either express or normal lines
   * @param customer The next Customer to be queued at the most optimal line in the queues parameter passed in
   * @return Updated queue with customer included that is passed in as a parameter
   */
  public static LinkedQueue<Customer> getOptimalQueue(ArrayList<LinkedQueue<Customer>> queues, Customer customer) {
    int optimalIndex = 0;
    int optimalServiceTime = 0;

    //Loop through queues passed implicitly as a LinkedQueue<Customer> object in the group of queues passed in directly.
    for (int i = 0; i < queues.size(); i++) {
      int queueServiceTime = getQueueTime(queues.get(i));
      optimalServiceTime = Math.max(queueServiceTime, optimalServiceTime);
      optimalIndex = optimalServiceTime == queueServiceTime ? i : optimalIndex;
    }

//    //Enqueue the customer to the most optimal queue from the queues passed in.
//    LinkedQueue<Customer> tempQueue = ;
//    tempQueue.enqueue(customer);
//    queues.set(optimalIndex, tempQueue);
//
      return queues.get(optimalIndex);
  }

  /**
   * Method that determines the total time a queue (express/normal) will currently take.
   * @param queue A queue (express/normal)
   * @return Integer representing the total time taken by Customers in the queue parameter passed in
   */
  public static int getQueueTime(LinkedQueue<Customer> queue) {
    //Loop through Customers of a queue -> Peek and add service times to respective queue service time, then dequeue.
    int queueServiceTime = 0;
    while (queue.peek() != null) {
      queueServiceTime += queue.peek().serviceTime();
      queue.dequeue();
    }
    return queueServiceTime;
  }

  /**
   * Method used to print a checkout line represented as a String to the console for a grocery store simulation dataset.
   * @param line LinkedQueue of Customers to be represented with a string
   * @param lineType Should be either "Express" or "Normal", corresponding to type of line being passed for printing
   * @param lineNum Number of customers in the line being printed (coming from the ArrayList index of lines)
   */
  public static void printLine(LinkedQueue<Customer> line, String lineType, int lineNum) {
    //TODO: Implement a loop that adds up the Customer wait times.
    int waitTime = 0;
    while(line.peek() != null) {
      waitTime += line.peek().serviceTime();
      line.dequeue();
    }

    //TODO: Output a line as a string in the console.
    System.out.printf("Checkout(%7s) #%3d (Est. Time = %d s) = %s", lineType, lineNum, waitTime, line.toString());
  }

}