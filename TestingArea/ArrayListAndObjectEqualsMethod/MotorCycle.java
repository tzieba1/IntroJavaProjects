package ArrayListAndObjectEqualsMethod;

public class MotorCycle extends Vehicle {
    public MotorCycle(int horsePower) {
        super("MotorCycle", horsePower);
    }

    @Override
    public int getRacingSpeed() {
        // return (int) (1.5 * getHorsePower());
        return 3 * getHorsePower() / 2;
    }


}
