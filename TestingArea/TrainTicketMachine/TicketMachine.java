package TrainTicketMachine;

/**
 * Purpose:   This class is a ticket machine object with methods that modify the ticket machine's instance variables
 * that describe ticket information.
 *
 * Date: May 31, 2019
 *
 * @author Tommy Zieba
 */

public class TicketMachine
  {
  /** Fare for 1 child **/
  private double child_fare;
  /** Fare for 1 adult **/
  private double adult_fare;
  /** Name of train route **/
  private String route = "Not selected";
  /** Number of adults for a ticket **/
  private int adults = 0;
  /** Number of children for a ticket **/
  private int children = 0;
  /** Amount of credit in dollars inserted **/
  private double credit = 0.00;
  /** Valid ticket flag **/
  public boolean ticket_print = false;

  /**
   * A method for setting the fares for machine 1.
   */
  public void setFare1()
    {
    adult_fare = 5.00;    // Adult fare for machine 1.
    child_fare = 2.50;    // Child fare for machine 1.
    }

  /**
   * A method for setting the fares for machine 2.
   */
  public void setFare2()
    {
    adult_fare = 6.25;    // Adult fare for machine 1.
    child_fare = 3.75;    // Child fare for machine 1.
    }

  /**
   * A method for changing the route to 'Uptown'.
   */
  public void uptownRoute()
    {
    route = "Uptown";   // Setting route to 'Uptown'.
    }

  /**
   * A method for chaning the route to 'Downtown'.
   */
  public void downtownRoute()
    {
    route = "Downtown";   // Setting route to 'Downtown'.
    }

  /**
   * A method for chaning the route to 'Main St.'.
   */
  public void mainStRoute()
    {
    route = "Main St.";   // Setting route to 'Main St.'.
    }

  /**
   * A method for chaning the route to 'King St.'.
   */
  public void kingStRoute()
    {
    route = "King St.";   // Setting route to 'King St.'.
    }

  /**
   * A method for adding an adult to a ticket.
   */
  public void addAdult()
    {
    adults++;   // Adding an adult to the ticket.
    }

  /**
   * A method for removing an adult from a ticket.
   */
  public void removeAdult()
    {
    if (adults > 0)   // Condition for removing an adult from ticket.
      adults--;   // Removing adult from ticket.
    }

  /**
   * A method for adding a child to a ticket.
   */
  public void addChild()
    {
    children++;   //  Adding a child to the ticket.
    }

  /**
   * A method for removing a child from a ticket.
   */
  public void removeChild()
    {
    if (children > 0)   // Condition for removing a child from ticket.
     children--;    // Removing a child from ticket.
    }

  /**
   * A method for inserting a quarter and adding its value to credit.
   */
  public void insertQuarter()
    {
    credit += 0.25;   // Adding $0.25 to credit.
    }

  /**
   * A method for inserting a loonie and adding its value to credit.
   */
  public void insertLoonie()
    {
    credit += 1.0;    // Adding $1.00 to credit.
    }
  
  /**
   * A method for inserting a quarter and adding its value to credit.
   */
  public void insertToonie()
    {
    credit += 2.0;    // Adding $2.00 to credit.
    }

  /**
   * A method for verifying if a ticket is printable and resetting instance variables.
   */
  public void ticketPrint()
    {
    if(((child_fare*children + adult_fare*adults <= credit || children > 0 || adults > 0))
        && !route.equals("Not selected"))
          // Condition for printing a ticket and resetting machine whenever the credit is at least
          // as much as the cost of the ticket, a route is selected, and there is at least one child or adult.
      {
      adults = 0;               // Resetting number of adult.
      children = 0;             // Resetting number of children.
      credit = 0.0;             // Resetting credit.
      route = "Not selected";   // Resetting route to default value.
      ticket_print = true;      // Setting flag for valid ticket to be printed.
      }
    }

  /**
   * A method overwriting the default toString() method.
   * Whenever an instance of TicketMachine is printed, this methods return value is applied to the .print() method.
   *
   * @return Returns a String describing the state for an instance of TicketMachine
   *          which is concatenated with the instance variables to be displayed.
   */
  public String toString()
    {
      return "\n*******************************************************************************\n" +
          "Selected Route: " + this.route + "    Child: $" + this.child_fare + "    Adult: $" + this.adult_fare +
          "\n" + "Children: " + this.children + "    Adults: " + this.adults + "\n" +
          "Credit: $" + this.credit + "\n" +
          "*******************************************************************************";
    }
  }
