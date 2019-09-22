package PolymorphismAndExceptionHandling.VehicleTesting;

/**
 * Solution to exercises 1a, 1b, 2b, week 10.
 *
 * @author Sam Scott
 */
public abstract class Vehicle {
    private int horsePower;
    private String name;

    public Vehicle(int horsePower, String name) {
        this.horsePower = horsePower;
        if (horsePower <= 0)
            throw new IllegalArgumentException("HorsePower must be greater than 0.");
        this.name = name;
    }
    
    public int getHorsePower() {
        return horsePower;
    }
    
    public abstract int getRacingSpeed();
    
    public boolean raceAgainst(Vehicle v) {
        return getRacingSpeed() > v.getRacingSpeed();
    }
    
    @Override
    public String toString() { return "[" + name + ", hp = " + horsePower + ", Racing Speed = " + getRacingSpeed() + "]"; }
}
