package week11;


import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Example of multiple catch blocks.
 *
 * @author sam scott
 */
public class Except2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            Thread.sleep(1000);
            int x = sc.nextInt();
            System.out.println("1,000,000 / " + x + " is " + 1_000_000 / x);
        } catch (InterruptedException e) {
            System.out.println("Caught an exception!");
        } catch (InputMismatchException e) {
            System.out.println("Bad input! " + sc.next());
            //e.printStackTrace();
        } catch (ArithmeticException e) {
            System.out.println("Zero has no inverse (Error message: \"" + e.getMessage() + "\").");
        }
        System.out.println("Done. Goodbye!");
    }

}
