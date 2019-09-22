package Assignment7_000797152;

import javafx.scene.paint.Color;

import java.util.Scanner;

/**
 * Subclass extending TimsProduct that represents BrewedCoffee to be created in a TimsOrder. Every BrewedCoffee must
 * have a description and calorie count in addition to the name, production cost, and retail price inherited by
 * the superclass TimsProduct.
 *
 * @author Tommy Zieba
 */
public class BrewedCoffee extends TimsProduct implements Consumable
  {
  /** Declaring the description for this BrewedCoffee. **/
  private String description;
  /** Declaring the calorie count for this BrewedCoffee. **/
  private int calories;

  /**
   * Default constructor - not used.
   */
  private BrewedCoffee() {}

  /**
   * Private constructor used to create a non-accessible instance of BrewedCoffee with a calorie count and description
   * in addition to the name, cost, and price passed into the TimsProduct super-constructor.
   * @param name The name for this BrewedCoffee.
   * @param description The description for this BrewedCoffee.
   * @param cost The cost for this BrewedCoffee.
   * @param price The price for this BrewedCoffee.
   * @param calories The calorie count for this BrewedCoffee.
   */
  private BrewedCoffee(String name, String description, double cost, double price, int calories )
    {
    super( name, cost, price ); //Initializing this TimsProduct name, production cost, and retail price.
    this.description = description; //Initializing this BrewedCoffee description.
    this.calories = calories; //Initializing this BrewedCoffee calorie count.
    }

  /**
   * Static factory method that creates a BrewedCoffee object by prompting for user input used to
   * parametrize the private constructor.
   * @return Returns a BrewedCoffee with the parametrized properties.
   */
  public static BrewedCoffee create()
    {
    Scanner number_input = new Scanner(System.in);  //Creating a scanner object to store numerical input from the user.

    //*** Prompt user for name, cost, price, color in a loop which breaks unless there is an exception.
    while(true)
      {
      try
        {
        System.out.println("Which roast of coffee would you like?\n" +
            "1. Regular Roast\n" +
            "2. Dark Roast\n" +
            "3. Decaffeinated\n"); //Prompt for a coffee roast that has corresponding cost, price, description, and calorie count.

        int roast = number_input.nextInt();  //Assigning user input corresponding to the following switch cases.

        //*** Parametrize new Soup with name, cost, price, color.
        switch ( roast )
          {
          case 1:
            return new BrewedCoffee("Regular Roast", "Always fresh", 0.58, 1.75,
                1);
          case 2:
            return new BrewedCoffee("Dark Roast", "Bold and delicious", 0.61,
                1.75, 2);
          case 3:
            return new BrewedCoffee("Decaffeinated", "Same great taste", 0.65,
                1.70, 0);
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
   * Method used to get the calorie count for this BrewedCoffee.
   * @return Returns the calorie count for this BrewedCoffee as an integer.
   */
  public int getCalorieCount(){ return this.calories;}

  /**
   * Method that gets the consumption method for this BrewedCoffee.
   * @return Returns the consumption method for this BrewedCoffee as a String.
   */
  public String getConsumptionMethod(){ return "DRINK IT"; }

  /**
   * The overridden toString() method which outputs the extended state of this BrewedCoffee object describing its
   * state as a TimsProduct and its state as a BrewedCoffee.
   * @return Returns a string representation of this BrewedCoffee describing the extended state of this TimsProduct.
   */
  @Override
  public String toString(){ return super.toString()
      + "\n\t " + "Type: BrewedCoffee[ Description = " + this.getDescription() + ", Calorie Count = "
      + this.getCalorieCount() + " ]";}
  }
