package week9;

/**
 * Solution to exercise 2c, week 10.
 *
 * @author Sam Scott
 */
public class TestThings {

    public static void describe(Thing thing) {
        System.out.println(thing.getClass());
        System.out.println(thing.getDescription());
        if (!thing.isReal()) {
            System.out.println("*** NOT REAL!!! ***");
        }
        System.out.println("");
    }

    public static void main(String[] args) {
        Thing[] things = {new Box(), new Cat(), new Unicorn()};
        for (Thing thing : things) {
            describe(thing);
        }
    }
}
