package Assignment7_000797152;

import javafx.scene.paint.Color;

import java.util.Scanner;

/**
 * Subclass extending TimsProduct that represents a Mug to be created in a TimsOrder. Every Mug must have a color in
 * addition to the name, production cost, and retail price inherited by the superclass TimsProduct.
 *
 * @author Tommy Zieba
 */
public class Mug extends TimsProduct implements Commodity
  {
  /** Declaring the color for this Mug. **/
  private Color color;

  /**
   * Default constructor - not used.
   */
  private Mug(){}

  /**
   * Private constructor used to create a non-accessible instance of Mug with a color
   * in addition to the name, cost, and price passed into the TimsProduct super-constructor.
   * @param name The name for this Mug.
   * @param color The color of this Mug.
   * @param cost The cost of this Mug.
   * @param price The price of this Mug.
   */
  private Mug( String name, Color color, double cost, double price )
    {
    super( name, cost, price); //Initializing this TimsProduct name, production cost, and retail price.
    this.color = color; //Initializing the color for this TimsProduct.
    }

  /**
   * Static factory method that creates a Mug object by prompting for user input used to
   * parametrize the private constructor.
   * @return Returns a Mug with the parametrized properties.
   */
  public static Mug create()
    {
    Scanner number_input = new Scanner(System.in);  //Creating a scanner object to store numerical input from the user.

    //*** Prompt user for name, cost, price, color in a loop which breaks unless there is an exception.
    while(true)
      {
      try
        {
        System.out.println("Which color mug would you like?\n" +
            "1. White\n" +
            "2. Black\n" +
            "3. Light yellow\n" +
            "4. Dark red\n"); //Prompt for a mug color that has corresponding name, cost, price.

        int color = number_input.nextInt();  //Assigning user input corresponding to the following switch cases.

        //*** Parametrize new Mug with name, cost, price, color.
        switch ( color )
          {
          case 1: //Return a white mug.
            return new Mug("Tims Original Mug - White", Color.WHITE, 1.29, 3.49);
          case 2: //Return a black mug.
            return new Mug("Tims Original Mug - Black", Color.BLACK, 1.29, 3.49);
          case 3: //Return a light yellow mug.
            return new Mug("Tims Original Mug - Light yellow", Color.LIGHTYELLOW, 1.29, 3.49);
          case 4: //Return a dark red mug.
            return new Mug("Tims Original Mug - Dark Red", Color.DARKRED, 1.29, 3.49);
        }
        }
      catch (Exception ex)
        {
        System.err.println("There was an exception, please try again.");  //Error message.
        }
      }
    }

  /**
   * Method used to determine what color this Mug is.
   * @return Returns the color of this Mug.
   */
  public Color getColor() { return this.color; }

  /**
   * The overridden toString() method which outputs the extended state of this Mug object describing its state as
   * a TimsProduct and its state as a Mug.
   * @return Returns a string representation of this Mug describing the extended state of this TimsProduct.
   */
  @Override
  public String toString(){ return super.toString()
      + "\n\t " + "Type: Mug[ Color = " + this.getColor() + " ]"; }
  }
