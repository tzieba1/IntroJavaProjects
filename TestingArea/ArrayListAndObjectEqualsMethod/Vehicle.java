package ArrayListAndObjectEqualsMethod;

/**
 * Solution to exercises 1a, 1b, 2b, week 10.
 *
 * @author Sam Scott
 */
public abstract class Vehicle {
    String name;
    private int horsePower;

    public Vehicle(String name, int horsePower) {
        this.name = name;
        if (horsePower <= 0)
            throw new IllegalArgumentException(" HorsePower must be greater than 0 ");
        this.horsePower = horsePower;
    }
    
    public int getHorsePower() {
        return horsePower;
    }
    
    public abstract int getRacingSpeed();
    
    public boolean raceAgainst(Vehicle v) {
        return getRacingSpeed() > v.getRacingSpeed();
    }

    @Override
    public String toString() {
        return "[" + name + " hp=" + horsePower + " Racing Speed = " + getRacingSpeed() + "]";
    }
}
