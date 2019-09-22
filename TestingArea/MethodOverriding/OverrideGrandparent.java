package MethodOverriding;

public class OverrideGrandparent {

    public void foo(int x) {
        System.out.println("Grandparent Foo " + x);
    }

    public void foo(int x, int y) {
        System.out.println("Grandparent Foo " + x + " " + y);
    }
}
