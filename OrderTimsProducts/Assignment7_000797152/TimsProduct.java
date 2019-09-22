package Assignment7_000797152;

import java.text.DecimalFormat;

/**
 * The superclass for the polymorphic hierarchy representing some Tims products that could be accounted for in an order.
 * Every TimsProduct must have a name, production cost, and retail price that can be accessed after construction.
 *
 * @author Tommy Zieba
 */
public class TimsProduct implements Commodity
  {
  /** Privately declaring the name of this TimsProduct. **/
  private String name;
  /** Privately declaring the production cost of this TimsProduct. **/
  private double cost;
  /** Privately declaring the retail price of this TimsProduct. **/
  private double price;

  /**
   * Default constructor - not used.
   */
  public TimsProduct() {}
  
  public TimsProduct(String name, double cost, double price )
    {
    this.name = name; //Initializing this TimProduct name.
    this.cost = cost; //Initializing this TimProduct production cost.
    this.price = price;//Initializing this TimsProduct retail price.
    }

  /**
   * Method that gets the name of this TimsProduct.
   * @return Returns the name of this TimsProduct as a String.
   */
  public String getName() { return this.name; }

  /**
   * Method declared in <<Commodity>> that allows an instance of this class to return its production cost.
   * @return Returns the production cost in the form of a double.
   */
  public double getProductionCost(){ return this.cost; }

  /**
   * Method declared in <<Commodity>> that allows an instance of this class to return its retail price.
   * @return Returns the retail price in the form of a double.
   */
  public double getRetailPrice(){ return this.price; }

  /**
   * The overridden toString() method which outputs the state of this TimsProduct object.
   * @return Returns a string representation of this TimsProduct that describes its state.
   */
  @Override
  public String toString()
    {
    //*** Creating a formatter object that will output a String representation for a double with a specified number
    //    of decimal places.
    DecimalFormat formatter = new DecimalFormat("$0.00"); //Creating DecimalFormat object with a price pattern.
    
    return "Tims Product[ Product Name = " + this.getName() + ", Cost = "
      + formatter.format(this.getProductionCost()) + ", Price = " + formatter.format(this.getRetailPrice()) + " ]";
    }
  }
