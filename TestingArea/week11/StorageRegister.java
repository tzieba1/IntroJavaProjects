package week11;


/**
 * This class defines a data structure that can store and perform operations on
 * up to 10 ints. Note that commenting is not to JavaDoc standard.
 *
 * @author Sam Scott
 */
public class StorageRegister {

    public static final int NUMBER_OF_REGISTERS = 10;

    public static final int MAX_REGISTER_VALUE = 1000;
    public static final int MIN_REGISTER_VALUE = 0;

    private int[] registers;

    public StorageRegister() {
        registers = new int[NUMBER_OF_REGISTERS];
        for (int i = 0; i < NUMBER_OF_REGISTERS; i++) {
            registers[i] = (int) (Math.random() * MAX_REGISTER_VALUE + MIN_REGISTER_VALUE);
        }
    }

    public int getRegister(int r) {
        return registers[r - 1];
    }

    public void setRegister(int r, int value) {
        registers[r - 1] = value;
    }

    public void incrementRegister(int r) {
        registers[r - 1]++;
    }

    public int registerQuotient(int r1, int r2) {
        return registers[r1 - 1] / registers[r2 - 1];
    }
}
