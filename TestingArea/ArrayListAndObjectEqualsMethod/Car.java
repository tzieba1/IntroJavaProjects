package ArrayListAndObjectEqualsMethod;

/**
 * Solution to exercises 1a, 1b, 2b, week 10.
 *
 * @author Sam Scott
 */
public class Car extends Vehicle {

    public Car(int horsePower) {
        super("Car", horsePower);
    }
    
    @Override
    public int getRacingSpeed() {
        return getHorsePower() / 2;
    }

/**
 * Cannot use the .contains() method for an ArrayList when searching for a vehicle without this overridden method.
 * @param otherObject   The other object being compared to the Car.
 * @return  Returns true if the otherObject is a Car.
 */
@Override
    public boolean equals(Object otherObject)
        {
        if (otherObject instanceof Car)
            {
            Car otherCar = (Car)otherObject;    //Safe cast (confirmed with compiler it was a Car first).
            if (getHorsePower() == otherCar.getHorsePower())
                return true;
            }
        return false;
        }
}
