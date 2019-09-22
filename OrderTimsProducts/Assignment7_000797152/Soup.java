package Assignment7_000797152;

import java.util.Scanner;

/**
 * Subclass extending TimsProduct that represents Soup to be created in a TimsOrder. Every Soup must
 * have a description and calorie count in addition to the name, production cost, and retail price inherited by
 * the superclass TimsProduct.
 *
 * @author Tommy Zieba
 */
public class Soup extends TimsProduct implements Consumable
  {
  /** Declaring the description for this Soup. **/
  private String description;
  /** Declaring the calorie count for this Soup. **/
  private int calories;

  /**
   * Default constructor - not used.
   */
  private Soup() {}

  /**
   * Private constructor used to create a non-accessible instance of Soup with a calorie count and description
   * in addition to the name, cost, and price passed into the TimsProduct super-constructor.
   * @param name The name for this Soup.
   * @param description The description for this Soup.
   * @param cost The cost for this Soup.
   * @param price The price for this Soup.
   * @param calories The calorie count for this Soup.
   */
  private Soup(String name, String description, double cost, double price, int calories )
    {
    super( name, cost, price );  //Initializing this TimsProduct name, production cost, and retail price.
    this.description = description;
    this.calories = calories; //Initializing this Soup calorie count.
    }

  /**
   * Static factory method that creates a Soup object by prompting for user input used to
   * parametrize the private constructor.
   * @return Returns a Soup with the parametrized properties.
   */
  public static Soup create()
    {
    Scanner number_input = new Scanner(System.in);  //Creating a scanner object to store numerical input from the user.

    //*** Prompt user for name, cost, price, description, and calorie count in a loop which breaks unless
    //    there is an exception.
    while(true)
      {
      try
        {
        System.out.println("Which type of soup would you like?\n" +
            "1. Chicken Noodle\n" +
            "2. Cream of Mushroom\n" +
            "3. Tomato\n"); //Prompt for a soup name that has corresponding cost, price, description, and calorie count.
        
        int name = number_input.nextInt();  //Assigning user input corresponding to the following switch cases.
        
        //*** Parametrize new Soup with name, cost, price, color.
        switch ( name )
          {
          case 1:
            return new Soup("Chicken Noodle","Just like mom used to make" , 1.00, 2.99,
                151 );
          case 2:
            return new Soup("Cream of Mushroom", "Perfectly creamy", 1.13,
                2.99, 174 );
          case 3:
            return new Soup("Tomato", "Tangy", 0.99,
                2.99, 183 );
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
   * Method used to get the calorie count for this Soup.
   * @return Returns the calorie count for this Soup as an integer.
   */
  public int getCalorieCount(){ return this.calories;}

  /**
   * Method that gets the consumption method for this Soup.
   * @return Returns the consumption method for this Soup as a String.
   */
  public String getConsumptionMethod(){ return "EAT IT WITH A SPOON"; }

  /**
   * The overridden toString() method which outputs the extended state of this Soup object describing its
   * state as a TimsProduct and its state as a Soup.
   * @return Returns a string representation of this Soup describing the extended state of this TimsProduct.
   */
  @Override
  public String toString(){ return super.toString()
      + "\n\t " + "Type: Soup[ Description = " + this.getDescription() + ", Calorie Count = "
      + this.getCalorieCount() + " ]";}
  }
