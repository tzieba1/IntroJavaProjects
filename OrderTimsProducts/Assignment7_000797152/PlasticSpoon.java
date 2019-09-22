package Assignment7_000797152;

import javafx.scene.paint.Color;

import java.util.Scanner;

/**
 * Subclass extending TimsProduct that represents a PlasticSpoon to be created in a TimsOrder. Every PlasticSpoon must
 * have a color in addition to the name, production cost, and retail price inherited by the superclass TimsProduct.
 *
 * @author Tommy Zieba
 */
public class PlasticSpoon extends TimsProduct implements Commodity
  {
  /** Declaring the color for this PlasticSpoon. **/
  private Color color;

  /**
   * Default constructor - not used.
   */
  private PlasticSpoon(){}

  /**
   * Private constructor used to create a non-accessible instance of PlasticSpoon with a color
   * in addition to the name, cost, and price passed into the TimsProduct super-constructor.
   * @param name The name for this PlasticSpoon.
   * @param color The color of this PlasticSpoon.
   * @param cost The cost of this PlasticSpoon.
   * @param price The price of this PlasticSpoon.
   */
  private PlasticSpoon( String name, Color color, double cost, double price )
    {
    super( name, cost, price);  //Initializing this TimsProduct name, production cost, and retail price.
    this.color = color;
    }

  /**
   * Static factory method that creates a parametrized PlasticSpoon object from the private constructor.
   * @return Returns a PlasticSpoon with the parametrized properties.
   */
  public static PlasticSpoon create()
    {
    //*** No prompt user for name, cost, price, color because a plastic spoon is added when soup is added.
    //    Parametrize new PlasticSpoon with name, cost, price, color.
    return new PlasticSpoon("White plastic spoon", Color.WHITE, 0.02, 0.00);
    }

  /**
   * Method used to determine what color this PlasticSpoon is.
   * @return Returns the color of this PlasticSpoon.
   */
  public Color getColor() { return this.color; }

  /**
   * The overridden toString() method which outputs the extended state of this PlasticSpoon object describing its
   * state as a TimsProduct and its state as a PlasticSpoon.
   * @return Returns a string representation of this PlasticSpoon describing the extended state of this TimsProduct.
   */
  @Override
  public String toString(){ return super.toString()
      + "\n\t " + "Type: PlasticSpoon[ Color = " + this.getColor() + " ]"; }
  }
