package PolymorphismAndExceptionHandling.VehicleTesting;

/**
 * Solution to exercises 1a, 1b, 2b, week 10.
 *
 * @author Sam Scott
 */
public class Car extends Vehicle {

    public Car(int horsePower) {
        super(horsePower, "Car");
    }
    
    @Override
    public int getRacingSpeed() {
        return getHorsePower() / 2;
    }
}
