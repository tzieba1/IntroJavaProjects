package Assignment7_000797152;

import javafx.scene.paint.Color;

import java.util.Scanner;

/**
 * Subclass extending TimsProduct that represents a Thermos to be created in a TimsOrder. Every Thermos must have
 * a color in addition to the name, production cost, and retail price inherited by the superclass TimsProduct.
 *
 * @author Tommy Zieba
 */
public class Thermos extends TimsProduct implements Commodity
  {
  /** Declaring the color for this Thermos. **/
  private Color color;

  /**
   * Default constructor - not used.
   */
  private Thermos(){}
  
  /**
   * Private constructor used to create a non-accessible instance of Thermos with a color
   * in addition to the name, cost, and price passed into the TimsProduct super-constructor.
   * @param name The name for this Thermos.
   * @param color The color of this Thermos.
   * @param cost The cost of this Thermos.
   * @param price The price of this Thermos.
   */
  private Thermos( String name, Color color, double cost, double price )
    {
    super( name, cost, price); //Initializing this TimsProduct name, production cost, and retail price.
    this.color = color; //Initializing the color for this TimsProduct.
    }

  /**
   * Static factory method that creates a Thermos object by prompting for user input used to
   * parametrize the private constructor.
   * @return Returns a Thermos with the parametrized properties.
   */
  public static Thermos create()
    {
    Scanner number_input = new Scanner(System.in);  //Creating a scanner object to store numerical input from the user.
    
    //*** Prompt user for name, cost, price, color in a loop which breaks unless there is an exception.
    while(true)
      {
      try
        {
        System.out.println("Which color thermos would you like?\n" +
            "1. White\n" +
            "2. Black\n" +
            "3. Light yellow\n" +
            "4. Dark red\n" +
            "5. Stainless (silver)\n"); //Prompt for a thermos color that has corresponding name, cost, price.
        
        int color = number_input.nextInt();  //Assigning user input corresponding to the following switch cases.
        
        //*** Parametrize new Thermos with name, cost, price, color.
        switch ( color )
          {
          case 1: //Return a white thermos.
            return new Thermos("Tims Thermos - White", Color.WHITE, 1.29, 3.49);
          case 2: //Return a black thermos.
            return new Thermos("Tims Thermos - Black", Color.BLACK, 1.29, 3.49);
          case 3: //Return a light yellow thermos.
            return new Thermos("Tims Thermos - Light yellow", Color.LIGHTYELLOW, 1.29, 3.49);
          case 4: //Return a dark red thermos.
            return new Thermos("Tims Thermos - Dark Red", Color.DARKRED, 1.29, 3.49);
          case 5: //Return a stainless (silver) thermos.
            return new Thermos("Tims Thermos - Stainless", Color.SILVER, 1.29, 3.49);
          }
        }
      catch (Exception ex)
        {
        System.err.println("There was an exception, please try again.");  //Error message.
        }
      }
    }
  
  /**
   * Method used to determine what color this Thermos is.
   * @return Returns the color of this Thermos.
   */
  public Color getColor() { return this.color; }
  
  /**
   * The overridden toString() method which outputs the extended state of this Thermos object describing its state as
   * a TimsProduct and its state as a Thermos.
   * @return Returns a string representation of this Thermos describing the extended state of this TimsProduct.
   */
  @Override
  public String toString(){ return super.toString()
      + "\n\t " + "Type: Thermos[ Color = " + this.getColor() + " ]"; }
  }
