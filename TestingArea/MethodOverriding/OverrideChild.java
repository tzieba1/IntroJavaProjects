package MethodOverriding;

/**
 *
 * @author Sam Scott
 */
public class OverrideChild extends OverrideParent {

    public void foo(int x) {
        System.out.println("Child Foo " + x);
    }

    public void foo(int x, int y, int z) {
        System.out.println("Child foo " + x + " " + y + " " + z);
    }
}
