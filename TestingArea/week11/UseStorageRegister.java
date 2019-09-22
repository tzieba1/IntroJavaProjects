package week11;

import java.util.Scanner;

/**
 * Scratchpad for testing the StorageRegister
 *
 * @author Sam Scott
 */
public class UseStorageRegister {

    public static void main(String[] args) {
        StorageRegister sr = new StorageRegister();
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter a register number: ");
        int myReg = sc.nextInt();

        System.out.println("Register " + myReg + ": " + sr.getRegister(myReg));
        sr.incrementRegister(myReg);
        System.out.println("Incrementing Register " + myReg + ".");
        System.out.println(sr.getRegister(myReg));

        System.out.println("Enter another register number: ");
        int myReg2 = sc.nextInt();
        System.out.println("Enter a value to store in register " + myReg2);
        int value = sc.nextInt();
        sr.setRegister(myReg2, value);
        System.out.println("Register " + myReg2 + ": " + sr.getRegister(myReg2));

        System.out.println("Dividing Register " + myReg + " by Register " + myReg2 + ".");
        System.out.println("Result: " + sr.registerQuotient(myReg, myReg2));
    }

}
