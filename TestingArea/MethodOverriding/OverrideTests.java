package MethodOverriding;

/**
 * Can you predict which methods get called?
 *
 * @author Sam Scott
 */
public class OverrideTests {

    public static void main(String[] args) {
        OverrideChild child = new OverrideChild();
        OverrideParent parent = new OverrideParent();
        OverrideGrandparent grandparent = new OverrideGrandparent();

        child.foo(1);
        child.foo(1, 2);
        child.foo(1, 2, 3);
        parent.foo(4);
        parent.foo(4, 5);
        //parent.foo(4, 5, 6);
        grandparent.foo(7);
        grandparent.foo(7, 8);
        //grandparent.foo(7, 8, 9);
    }
}
