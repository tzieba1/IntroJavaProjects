package Assignment7_000797152;

import javafx.scene.paint.Color;

/**
 * Subclass extending TimsProduct that represents a Bowl to be created in a TimsOrder. Every Bowl must have a color in
 * addition to the name, production cost, and retail price inherited by the superclass TimsProduct.
 *
 * @author Tommy Zieba
 */
public class Bowl extends TimsProduct implements Commodity
  {
  /** Declaring the color for this Bowl. **/
  private Color color;

  /**
   * Default constructor - not used.
   */
  private Bowl(){}

  /**
   * Private constructor used to create a non-accessible instance of Bowl with a color
   * in addition to the name, cost, and price passed into the TimsProduct super-constructor.
   * @param name The name for this Bowl.
   * @param color The color of this Bowl.
   * @param cost The cost of this Bowl.
   * @param price The price of this Bowl.
   */
  private Bowl( String name, Color color, double cost, double price )
    {
    super( name, cost, price);  //Initializing this TimsProduct name, production cost, and retail price.
    this.color = color;
    }

  /**
   * Static factory method that creates a parametrized Bowl object from the private constructor.
   * @return Returns a Bowl with the parametrized properties.
   */
  public static Bowl create()
    {
    //*** No prompt user for name, cost, price, color because a bowl is added when soup is added.
    //    Parametrize new Bowl with name, cost, price, color.
    return new Bowl("Disposable Tims Bowl", Color.DARKRED, 0.03, 0.00);
    }

  /**
   * Method used to determine what color this Bowl is.
   * @return Returns the color of this Bowl.
   */
  public Color getColor() { return this.color; }

  /**
   * The overridden toString() method which outputs the extended state of this Bowl object describing its state as
   * a TimsProduct and its state as a Bowl.
   * @return Returns a string representation of this Bowl describing the extended state of this TimsProduct.
   */
  @Override
  public String toString(){ return super.toString()
      + "\n\t " + "Type: Bowl[ Color = " + this.getColor() + " ]"; }
  }
