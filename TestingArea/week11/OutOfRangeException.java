package week11;

/**
 * A special exception class to handle range errors.
 *
 * @author Sam Scott
 */
public class OutOfRangeException extends RuntimeException {

    /**
     * Stores the range and the offending value *
     */
    private double min, max, value;

    /**
     * Constructor requires three params for the instance vars
     *
     * @param min The minimum range value
     * @param max The maximum range value
     * @param value The bad value
     */
    public OutOfRangeException(double min, double max, double value) {
        super("Value " + value + " is not in the range " + min + " to " + max + ".");
        this.min = min;
        this.max = max;
        this.value = value;
    }

    /**
     * @return The minimum range value
     */
    public double getMin() {
        return min;
    }

    /**
     * @return The maximum range value
     */
    public double getMax() {
        return max;
    }

    /**
     * @return The bad value
     */
    public double getValue() {
        return value;
    }

    /**
     * Computes the closest legal value in the range
     *
     * @return The closest legal value
     */
    public double getClosestLegalValue() {
        if (min - value > value - max) {
            return min;
        }
        return max;
    }
}
