public class CheckoutLines {
  private LineType type;
  private LinkedQueue<Customer>[] queues;
  private int optimalQueueIndex;
  private LinkedQueue<Customer> optimalQueue;
  private int serviceTime;

  public CheckoutLines(int lineCount, LineType type) {
    this.type = type;
    queues = new LinkedQueue[lineCount];
    for (int i = 0 ; i < lineCount ; i ++) queues[i] = new LinkedQueue<>();
    serviceTime = 0;
    optimalQueue = queues[0];
    optimalQueueIndex = 0;
  }

  /**
   * Method for getting the optimalQueue from this instance of CheckoutLines.
   * @return Optimal queue (of type LinkedQueue<Customer>) required when next Customer is enqueued
   */
  public LinkedQueue<Customer> getOptimalQueue() { return queues[optimalQueueIndex]; }

  /**
   * Method for getting the serviceTime for this instance of CheckoutLines.
   * @return Integer representing the current cumulative serviceTime for all queues in this instance of CheckoutLines
   */
  public int getServiceTime() { return serviceTime; }

  /**
   * Method used to modify the end of this optimalQueue by enqueuing the Customer passed in, then updateQueues for this
   * instance of CheckoutLines.
   * @param customer The Customer object being enqueued to the most optimal queue.
   */
  public void enqueueCustomer(Customer customer) {
    this.queues[optimalQueueIndex].enqueue(customer);
    updateQueues();
  }

  /**
   * Method used to update private variables (optimalQueue and serviceTime) for this instance of CheckoutLines after
   * enqueuing or dequeuing Customers.
   */
  private void updateQueues() {
    //TODO: Loop through queues and add each respective queue's service time to the total service time for all queues
    // while simultaneously setting the optimalQueue for this instance of CheckoutLines.
    int optimalQueueTime = 0; //Kept in memory outside the loop to be updated and referenced in each iteration
    for (int i = 0; i < queues.length; i++) {
      serviceTime += getQueueServiceTime(queues[i]); //Sum of queue serviceTimes in this instance of CheckoutLines

      //TODO: Check condition for the first queue in iteration - required for setting the optimalQueue in this loop.
      if (i == 0) {
        optimalQueueTime = serviceTime;
        //optimalQueue = queues.get(i);
        optimalQueueIndex = i;
      } else {
        optimalQueueTime = Math.min(serviceTime, optimalQueueTime);
        //optimalQueue = serviceTime == optimalQueueTime ? queues.get(i) : optimalQueue;
        optimalQueueIndex = i;
      }
    }
  }

  /**
   * Method only used privately to return the serviceTime for a LinkedQueue of Customers.
   * @param queue LinkedQueue<Customer> to be evaluated for its serviceTime
   * @return Integer representing the serviceTime of the queue passed in as a parameter.
   */
  private int getQueueServiceTime(LinkedQueue<Customer> queue) {
    //Loop through Customers of a queue -> Peek and add service times to respective queue service time, then dequeue.
    int queueServiceTime = 0;
    while (queue.peek() != null) {
      queueServiceTime += queue.peek().serviceTime();
      queue.dequeue();
    }
    return queueServiceTime;
  }

  /**
   * Method for printing a String representation of this instance of CheckoutLines.
   * @return Formatted String of all lines for this instance of CheckoutLines.
   */
  @Override
  public String toString() {
    StringBuilder checkoutLinesString = new StringBuilder();
    for (int i = 0, queuesSize = queues.length; i < queuesSize; i++) {
      LinkedQueue<Customer> queue = queues[i];
      checkoutLinesString.append(String.format(
          "Checkout(%-7s) #%d (Est. Time = %d s) = %s\n",
          type,
          i+1,
          getQueueServiceTime(queue),
          queue.toString()));
    }
    return checkoutLinesString.toString();
  }
}
