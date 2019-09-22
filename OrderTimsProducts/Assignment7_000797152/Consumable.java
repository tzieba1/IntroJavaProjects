package Assignment7_000797152;

/**
 * Interface for TimsProduct subclass objects that implements consumable methods. Consumable objects have a method
 * for getting the calorie count as an integer and for getting the consumption method as a String.
 *
 * @author Tommy Zieba
 */
public interface Consumable
  {
  /**
   * Abstract method used for Consumable subclass instances of TimsProduct to return their calorie count.
   * @return Should be implemented to return a consumable subclass instance's calorie count as an integer.
   */
  public abstract int getCalorieCount();

  /**
   * Abstract method used for Consumable subclass instances of TimsProduct to return their consumption method.
   * @return Should be implemented to return a String representing a consumable subclass instance's consumption method.
   */
  public abstract String getConsumptionMethod();
  }
