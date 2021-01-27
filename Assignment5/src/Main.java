/**
 * I, Tommy Zieba, 000797152 certify that this material is my original work.
 * No other person's work has been used without due acknowledgement.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Methods in this Main class are written approximately in order of appearance during execution.
 */
public class Main {
  /**
   * This method is the point of entry for execution and represents the reading of a data file representing a simulation
   * of queues at a grocery store consisting of normal and fast checkout lane types. Simulation occurs during Scanning
   * of the data file since the arrays being instantiated are of fixed size and their sizes exist in the data file. The
   * purpose of this is to remain as efficient as possible by choosing the correct data structures and to not introduce
   * additional complexity.
   * @param args
   */
  public static void main(String[] args) {
    //TODO: Declare two distinct groups of checkout lanes in respective arrays expected to be fixed size.
    LinkedQueue<Customer>[] express, normal;

    //TODO: Declare the data that is global simulation data fixed for a specific simulation from a file.
    int f, n, x, c;

    try {
      //TODO: Define a Scanner object for reading simulation data.
      Scanner s = new Scanner(new File("src/CustomerData.txt")); //Scanner for simulation data.

      //TODO: Read in data and run the simulation for a store with express and normal queues of Customers.
      if (s.hasNextLine()) {
        f = s.nextInt();  //Number of express checkout lanes.
        n = s.nextInt();  //Number of normal checkout lanes.
        x = s.nextInt();  //Express checkout limit (can enqueue in express if number of items <= x).
        c = s.nextInt();  //Number of customers in simulation (number of items per Customer to follow from Scanner, s).

        //TODO: Declare and initialize the simulated queues in two groups - express and normal.
        express = new LinkedQueue[f]; //Group of express checkout lanes represented as LinkedQueues of Customers.
        for (int i = 0; i < express.length; i++)
          express[i] = new LinkedQueue<>(); //Each express queue is only instantiated here.
        normal = new LinkedQueue[n];  //Group of normal checkout lanes represented as LinkedQueues of Customers.
        for (int i = 0; i < normal.length; i++)
          normal[i] = new LinkedQueue<>();  //Each normal queue is only instantiated here.

        //TODO: Scan customer items using Scanner with cursor starting where "number of items per customer" data begins.
        // Additional variables such as queues instantiated, the limit, x, for optionally entering express lanes and
        // the number of customers, c, are required from data already read in sor far.
        scanCustomerItems(express, normal, c, x, s);

        //TODO: Sort checkout lanes by service time and print initial optimal simulation set-up in the console.
        sortQueues(express);
        sortQueues(normal);
        System.out.println("\nPART A: OPTIMAL INITIAL STATE OF QUEUES");
        //Retrieves the longest queue time from all queues (express/normal).
        int longestQueueTime = Math.max(serviceTime(express[0]), serviceTime(normal[0]));
        //Prints express and normal queues and returns total time.
        int serviceTime = printQueues(express, LaneType.EXPRESS) + printQueues(normal, LaneType.NORMAL);
        System.out.println("Cumulative service time of all Customers in store = " + serviceTime + " s");
        System.out.println("Total time to clear all Customers out of store = " + longestQueueTime + " s\n\n");

        //TODO: Simulate the dequeue of Customers every second and output results after each simulation step of seconds.
        System.out.println("PART B: SIMULATION RESULTS OF DEQUEUE");

        runSimulation(express, normal, longestQueueTime, 30);
      }
      s.close();
    } catch (FileNotFoundException e) {
      System.out.println(e.getMessage());
      e.printStackTrace();
    }
  }

  /**
   * Method used to scanCustomerItems by passing in the two arrays of type LinkedQueue<Customer>[] representing a
   * store's express and normal checkout lanes (grouped separately). Number of customers c, express lane item limit x,
   * and the Scanner s used in main() method to read data is also required. Note that this method and remaining
   * accessory methods will appear entirely inside the scope of data file reading occurring in main() method.
   * @param express LinkedQueue<Customer> representing simulated express checkout lanes
   * @param normal LinkedQueue<Customer> representing simulated normal checkout lanes
   * @param c Integer representing simulated number of customers
   * @param x Integer representing simulated inclusive cut-off for number of items to optionally enter express lane
   * @param s Scanner object remaining from the data file reading process so far with cursor at first number of items
   */
  private static void scanCustomerItems(
      LinkedQueue<Customer>[] express, LinkedQueue<Customer>[] normal, int c, int x, Scanner s) {
    //TODO: Iterate over remaining data with the Scanner, s, to simulate enqueue/dequeue of Customers.
    for (int i = 0; i < c; i++) {
      int[] fServiceTimes, nServiceTimes;
      //TODO: Find the shortest queue out of express lanes.
      int fMinIndex = 0;  //Current index for queue with minimum service time from express queues.
      fServiceTimes = serviceTime(express);  //Current express service times returned by helper method.
      for (int j = 1; j < fServiceTimes.length; j++)
        fMinIndex = fServiceTimes[j] <= fServiceTimes[fMinIndex] ? j : fMinIndex;

      //TODO: Find the shortest queue out of normal lanes.
      int nMinIndex = 0;  //Current index for queue with minimum service time from normal queues.
      nServiceTimes = serviceTime(normal);    //Current normal service times returned by helper method.
      for (int j = 1; j < nServiceTimes.length; j++)
        nMinIndex = nServiceTimes[j] <= nServiceTimes[nMinIndex] ? j : nMinIndex;

      Customer customer = new Customer(s.nextInt());  //Next customer to be enqueued.

      //First check if express lanes are valid and that it is faster than normal.
      if ( customer.getItems() <= x && fServiceTimes[fMinIndex] <= nServiceTimes[nMinIndex])
        express[fMinIndex].enqueue(customer);
        //Otherwise, proceed simulating time passage to dequeue/enqueue customers at regular intervals.
      else
        normal[nMinIndex].enqueue(customer);
    }
  }

  /**
   * Accessory method used to iterate over queues in an array of type LinkedQueue<Customer>[] while summing respective
   * service times and storing them in an identically sized array.
   * @param qs Queues to be iterated over of type LinkedQueue<Customer>[]
   * @return Integer array holding sum of service times for all queues and optimal queue index in that order
   */
  public static int[] serviceTime(LinkedQueue<Customer>[] qs) {
    int[] evaluation = new int[qs.length];
    for (int j = 0; j < qs.length; j++)
      evaluation[j] = serviceTime(qs[j]);  //Use similar accessory method to get ONE queue's service time.
    return evaluation;
  }

  /**
   * Accessory method used to iterate over customers in a single queue of type LinkedQueue<Customer> while summing
   * respective service times to be returned. Iterate over Customers in queue. Peek and add a customer service time to
   * queue total service time, then dequeue followed by immediately enqueueing the customer again (as a false re-queue
   * to count service times).
   * @param q Queue to be iterated over of type LinkedQueue<Customer>
   * @return Integer representing total service time for all customers in queue passed in as a parameter
   */
  public static int serviceTime(LinkedQueue<Customer> q) {
    int t = 0;  //Total service time for the queue.
    //TODO: Check if q is empty.
    if(!q.isEmpty()) {
      Customer frontValue = q.dequeue();  //Save frontValue to compare to changing front while Customers are re-queued.
      q.enqueue(frontValue);
      t += frontValue.serviceTime();

      //TODO: Iterate over remainder of queue. Re-queue each Customer until reaching the original frontValue Customer.
      while (q.peek() != frontValue) {
        Customer temp = q.dequeue();
        t += temp.serviceTime();
        q.enqueue(temp);
      }
    }
    return t;
  }

  /**
   * Accessory method used to perform a Selection sort algorithm modified to suit an array of customer queues. Queues
   * sorted from largest to smallest service time.
   * @param qs Queues of type LinkedQueue<Customer> to be sorted.
   */
  public static void sortQueues(LinkedQueue<Customer>[] qs) {
    int startScan;   // Starting position of the scan
    int index;       // To hold a subscript value
    int minIndex;    // Element with smallest value in the scan
    LinkedQueue<Customer> minValue;    // The smallest value found in the scan

    // Outer loop iterates once for each element in array. The startScan variable is position where scan should begin.
    for (startScan = 0; startScan < (qs.length - 1); startScan++)
    {
      //TODO: Assume first element in the scanning area is the smallest value.
      minIndex = startScan;
      minValue = qs[startScan];

      //TODO: Look for the smallest value in the scanning area.
      for (index = startScan + 1; index < qs.length; index++)
      {
        if (serviceTime(qs[index]) > serviceTime(minValue))
        {
          minValue = qs[index];
          minIndex = index;
        }
      }
      //TODO: Swap the element with the smallest value with the first element in the scanning area.
      qs[minIndex] = qs[startScan];
      qs[startScan] = minValue;
    }
  }

  /**
   * Accessory method used to print the current state of queues based on Customers in a group of queues passed in as
   * a parameter. Passed in queues are iterated over to append respective String representations to a StringBuilder that
   * to be printed to console. The LaneType enumerable is used in each checkout lane group's String representation.
   * @param qs Queues of type LinkedQueue<Customer> to be iterated over
   * @param type LaneType that is an enumerable with values either EXPRESS or NORMAL
   * @return Total service time for queues passed in as a parameter as an integer
   */
  public static int printQueues(LinkedQueue<Customer>[] qs, LaneType type) {
    StringBuilder checkoutLanesString = new StringBuilder();  //Used to append each queue's String representation
    int qTime, totalTime = 0;  //Needed to return total time of queues efficiently.
    //TODO: Iterate over queues and append a formatted String to the StringBuilder representing checkout lanes.
    // Simultaneously sum queue times to return total time.
    for (int i = 0; i < qs.length; i++) {
      qTime = serviceTime(qs[i]); //Current queue's service time used in it's string representation and total time.
      totalTime += qTime;
      checkoutLanesString.append(String.format(
          "Checkout(%-7s) #%d (ServiceTime = %d s) = %s\n",
          type,
          i+1,
          qTime,
          qs[i].toString()));
    }
    System.out.println(checkoutLanesString.toString());
    return totalTime;
  }

  /**
   * Method that executes the simulation of dequeue via loop counter determined by the simulation step. The counter
   * represents passage of time in intervals for both each second and every simulationStep of seconds. Longest queue
   * service time must be provided in order to determine total simulation time.
   * @param express Express queues of type LinkedQueue<Customer> to be iterated over
   * @param normal Normal queues of type LinkedQueue<Customer> to be iterated over
   * @param longestQueueTime Integer representing the longest queue service time before simulating dequeue
   * @param simulationStep Integer representing interval of time in seconds that simulation results should be printed
   */
  public static void runSimulation(
      LinkedQueue<Customer>[] express, LinkedQueue<Customer>[] normal, int longestQueueTime, int simulationStep) {
    //TODO: Print headings for table representing a dequeue simulation.
    printHeadings(express, normal);

    //TODO: Iterate over timeElapsed in seconds and print results after each simulationInterval to the console.
    int timeElapsed = 0;  //Simulation clock with interval every second.
    //Service times for each group of checkout lanes changes over time, so each must be instantiated before iterating.
    int[] fServiceTimes = new int[express.length];
    int[] nServiceTimes = new int[normal.length];

    //The condition here uses some modular arithmetic in order to run the simulation exactly one interval past final
    // dequeue whenever it is not a divisible by simulation step interval.
    while (timeElapsed <= longestQueueTime + (simulationStep - (longestQueueTime % simulationStep))) {
      //TODO: Only print an interval result at each multiple of the simulation step.
      if (timeElapsed % simulationStep == 0) printIntervalResult(express,normal, timeElapsed);

      //TODO: Dequeue customers that are finished at the front of each line.
      dequeueCustomers(express, normal, fServiceTimes, nServiceTimes);

      //TODO: Update express and normal service times after dequeue of any Customers that are finished.
      for (int i = 0; i < fServiceTimes.length; i++) fServiceTimes[i]++;
      for (int i = 0; i < nServiceTimes.length; i++) nServiceTimes[i]++;

      //TODO: Advance the simulation clock forward by one second.
      timeElapsed++;
    }
  }

  /**
   * Method used to iterate over queues and their respective service times. When a queue is not empty, the front is
   * checked and if the Customer is finished they are dequeued. When a Customer is dequeued, the respective queue's
   * serviceTime must be reset to proceed in counting until the next Customer is finished.
   * @param express Express queues of type LinkedQueue<Customer> to be iterated over
   * @param normal Normal queues of type LinkedQueue<Customer> to be iterated over
   * @param fServiceTimes Integer array of service times for express (f) lanes
   * @param nServiceTimes Integer array of service times for normal (n) lanes
   */
  private static void dequeueCustomers(
      LinkedQueue<Customer>[] express, LinkedQueue<Customer>[] normal, int[] fServiceTimes, int[] nServiceTimes) {
    //TODO: Dequeue express lanes.
    for (int i = 0; i < express.length; i++) {
      if (!express[i].isEmpty()) {  //Check if queue is empty.
        if (fServiceTimes[i] == express[i].peek().serviceTime()) {  //Check if Customer is finished.
          fServiceTimes[i] = 0;
          express[i].dequeue();
        }
      }
    }

    //TODO: Dequeue normal lanes.
    for (int i = 0; i < normal.length; i++) {
      if (!normal[i].isEmpty()) {   //Check if queue is empty.
        if (nServiceTimes[i] == normal[i].peek().serviceTime()) {   //Check if Customer is finished.
          nServiceTimes[i] = 0;
          normal[i].dequeue();
        }
      }
    }
  }

  /**
   * Accessory method used to print the headings for the number of Customers in each checkout lane before simulating
   * the dequeue of Customers from all lanes over simulated time. StringBuilder is used to append a heading for each
   * checkout lane (queue) from both express and normal checkout lanes while numbering them in sequence.
   * @param express Express queues of type LinkedQueue<Customer> to be iterated over
   * @param normal Normal queues of type LinkedQueue<Customer> to be iterated over
   */
  private static void printHeadings(LinkedQueue<Customer>[] express, LinkedQueue<Customer>[] normal) {
    //TODO: Build a string of headings formatted into columns.
    StringBuilder headings = new StringBuilder();
    headings.append(String.format("%-8s|", "t(s)"));
    int lineCount = 1;

    //TODO: Append each express queue heading to the outputted headings.
    for (int i = 0; i < express.length; i++) {
      headings.append(String.format("| %-8s#%-3d|", LaneType.EXPRESS.toString(), lineCount));
      lineCount++;
    }

    //TODO: Append each normal queue heading and append an underline of '=' to headings, then print the headings.
    lineCount = 1;  //Reset the line count for normal queues.
    for (int i = 0; i < normal.length; i++) {
      headings.append(String.format("| %-8s#%-3d|", LaneType.NORMAL.toString(), lineCount));
      lineCount++;
    }
    headings.append("|\n");
    headings.append("=========");
    for (int i = 0; i < express.length + normal.length; i++) {
      headings.append("===============");
    }
    headings.append("=");
    System.out.println(headings.toString());  //Print resulting headings to the screen.
  }

  /**
   * Accessory method used to print a simulation result of the number of Customers currently in each checkout lane.
   * StringBuilder is used to first append the timeElapsed for a simulation result. Number of Customers in each checkout
   * lane is appended as a formatted String iteratively across express and normal queues.
   * @param express Express queues of type LinkedQueue<Customer> to be iterated over
   * @param normal Normal queues of type LinkedQueue<Customer> to be iterated over
   * @param timeElapsed Integer representing the time that has elapsed as a part of the printed simulation result
   */
  private static void printIntervalResult(
      LinkedQueue<Customer>[] express, LinkedQueue<Customer>[] normal, int timeElapsed) {
    StringBuilder simulationResult = new StringBuilder();
    simulationResult.append(String.format("%-8d|", timeElapsed));

    //TODO: Append each express queue's number of customers to the outputted simulation tick interval result.
    for (int i = 0; i < express.length; i++) simulationResult.append(String.format("|      %-6d |", express[i].size()));

    //TODO: Append each normal queue's number of customers to the outputted simulation tick interval result.
    for (int i = 0; i < normal.length; i++) simulationResult.append(String.format("|      %-6d |", normal[i].size()));
    simulationResult.append("|");

    //TODO: Append an underline of '-' to new line below tick interval result, then print the tick result.
    simulationResult.append("\n---------");
    for (int i = 0; i < express.length + normal.length; i++) {
      simulationResult.append("---------------");
    }
    simulationResult.append("-");
    System.out.println(simulationResult.toString());  //Print simulation tick result to console.
  }
}