package ArrayListAndObjectEqualsMethod;

/**
 * Solution to exercises 1a, 1b, 2b, week 10.
 *
 * @author Sam Scott
 */
public class Truck extends Vehicle {

    public Truck(int horsePower) {
        super("Truck", horsePower);
    }

    @Override
    public int getRacingSpeed() {
        return getHorsePower() / 3;
    }


}
