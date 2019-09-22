package Assignment7_000797152;

/**
 * Interface for TimsProduct subclass objects that implements commodity methods. Commodity objects have a method
 * for getting the production cost as and the retail price both as doubles.
 *
 * @author Tommy Zieba
 */
public interface Commodity
  {
  /**
   * Abstract method used for Commodity subclass instances of TimsProduct to return their production cost.
   * @return Should be implemented to return a commodity subclass instance's production cost.
   */
  public abstract double getProductionCost();

  /**
   * Abstract method used for Commodity subclass instances of TimsProduct to return their retail price.
   * @return Should be implemented to return a commodity subclass instance's retail price.
   */
  public abstract double getRetailPrice();
  }
