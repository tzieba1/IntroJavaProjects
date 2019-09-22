package Assignment7_000797152;

import java.util.Scanner;

/**
 * Subclass extending TimsProduct that represents a Donut to be created in a TimsOrder. Every Donut must
 * have a description and calorie count in addition to the name, production cost, and retail price inherited by
 * the superclass TimsProduct.
 *
 * @author Tommy Zieba
 */
public class Donut extends TimsProduct implements Consumable
  {
  /** Declaring the description for this Donut. **/
  private String description;
  /** Declaring the calorie count for this Donut. **/
  private int calories;

  /**
   * Default constructor - not used.
   */
  private Donut() {}

  /**
   * Private constructor used to create a non-accessible instance of Donut with a calorie count and description
   * in addition to the name, cost, and price passed into the TimsProduct super-constructor.
   * @param name The name for this Donut.
   * @param description The description for this Donut.
   * @param cost The cost for this Donut.
   * @param price The price for this Donut.
   * @param calories The calorie count for this Donut.
   */
  private Donut(String name, String description, double cost, double price, int calories )
    {
    super( name, cost, price ); //Initializing this TimsProduct name, production cost, and retail price.
    
    switch ( name )
      {
      case "Honey Cruller":
        this.description = ""; //Initializing this Donut description for a Honey Cruller.
        break;
      case "Apple Fritter":
        this.description = ""; //Initializing this Donut description for a Apple Fritter.
        break;
      }
    this.calories = calories; //Initializing this Donut calorie count.
    }

  /**
   * Static factory method that creates a Donut object by prompting for user input used to
   * parametrize the private constructor.
   * @return Returns a Donut with the parametrized properties.
   */
  public static Donut create()
    {
    Scanner number_input = new Scanner(System.in);  //Creating a scanner object to store numerical input from the user.

    //*** Prompt user for name, cost, price, color in a loop which breaks unless there is an exception.
    while(true)
      {
      try
        {
        System.out.println("Which type of donut would you like?\n" +
            "1. Honey Cruller\n" +
            "2. Apple Fritter\n" +
            "3. Old Fashion Plain\n" +
            "4. Chocolate\n"); //Prompt for donut name that has corresponding cost, price, description, and calorie count.

        int name = number_input.nextInt();  //Assigning user input corresponding to the following Donut switch cases.

        //*** Parametrize new Donut with name, cost, price, color.
        switch ( name )
          {
          case 1:
            return new Donut("Honey Cruller","Sweet and soft" , 0.10, 0.89, 342 );
          case 2:
            return new Donut("Apple Fritter", "Gooey with cinnamon", 0.12,
                0.99, 376 );
          case 3:
            return new Donut("Old Fashion Plain", "Crisp and sweet", 0.07,
                0.79, 298 );
          case 4:
            return new Donut("Chocolate", "Rich chocolate dough and chocolate glaze", 0.11,
                0.89, 359 );
          }
        }
      catch (Exception ex)
        {
        System.err.println("There was an exception, please try again.");  //Error message.
        }
      }
    }
  
  /**
   * Method used to get this product's description.
   * @return Returns a description of this product as a String.
   */
  public String getDescription(){ return this.description; }

  /**
   * Method used to get the calorie count for this Donut.
   * @return Returns the calorie count for this Donut as an integer.
   */
  public int getCalorieCount(){ return this.calories;}

  /**
   * Method that gets the consumption method for this Donut.
   * @return Returns the consumption method for this Donut as a String.
   */
  public String getConsumptionMethod(){ return "EAT IT"; }

  /**
   * The overridden toString() method which outputs the extended state of this Donut object describing its
   * state as a TimsProduct and its state as a Donut.
   * @return Returns a string representation of this Donut describing the extended state of this TimsProduct.
   */
  @Override
  public String toString()
    {
    return super.toString() + "\n\t " + "Type: Donut[ Description = " + this.getDescription()
        + ", Calorie Count = " + this.getCalorieCount() + " ]";
    }
  }
