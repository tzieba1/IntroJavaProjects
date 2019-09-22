package week9;

/**
 * Solution to exercise 2c, week 10.
 *
 * @author Sam Scott
 */
public class Cat extends Thing {

    public Cat() {
        super(true);
    }
    
    @Override
    public String getDescription() {
        return "Four-footed furry animal that meows.";
    }
}
