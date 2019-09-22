package week11;

/**
 * Throws an OutOfRangeException
 * 
 * @author Sam Scott
 */
public class OutOfRangeThrower {
    
    double x; // must be in range -10 to 10
    
    public OutOfRangeThrower(double x) {
        if (x < -10.0 || x > 10.0)
            throw new OutOfRangeException(-10.0, 10.0, x);
        this.x = x;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        if (x < -10.0 || x > 10.0)
            throw new OutOfRangeException(-10.0, 10.0, x);
        this.x = x;
    }
    
    
}
