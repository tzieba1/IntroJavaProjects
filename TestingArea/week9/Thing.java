package week9;

/**
 * Solution to exercise 2c, week 10.
 *
 * @author Sam Scott
 */
public abstract class Thing {

    private boolean real;

    public Thing(boolean real) {
        this.real = real;
    }
    
    public boolean isReal() {
        return real;
    }
    
    public abstract String getDescription();
}
