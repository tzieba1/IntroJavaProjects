package week11;

import java.util.Scanner;

/**
 * Catches the OutOfRangeException.
 * 
 * @author Sam Scott
 */
public class OutOfRangeCatcher {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        OutOfRangeThrower thrower1 = new OutOfRangeThrower(-5);
        
        System.out.print("Enter a value for thrower2: ");
        double val = sc.nextDouble();
        OutOfRangeThrower thrower2;
        try {
            thrower2 = new OutOfRangeThrower(val);
        } catch (OutOfRangeException e) {
            System.out.println("*******************");
            System.out.println("Error trying to create thrower1: " + e.getMessage());
            System.out.println("Using closest legal value: " + e.getClosestLegalValue());
            System.out.println();
            thrower2 = new OutOfRangeThrower(e.getClosestLegalValue());
        }
        
        System.out.println("Thrower1: " + thrower1.getX());
        System.out.println("Thrower2: " + thrower2.getX());
    }
}
