package MethodOverriding;

public class OverrideParent extends OverrideGrandparent {

    public void foo(int x, int y) {
        System.out.println("Parent foo " + x + " " + y);
    }
}
