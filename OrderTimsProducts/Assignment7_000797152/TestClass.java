package Assignment7_000797152;

import java.util.Scanner;

/**
 * Class used to test the TimsOrder object and its associated factory design pattern by calling a static factory method.
 * The getAmountDue() method for the TimsOrder object is called and outputted with the order's toString() representation
 * in the format of a receipt.
 *
 * @author Tommy Zieba
 */
public class TestClass
  {
  /**
   * main method that runs the testing program.
   * @param args  Unused.
   */
  public static void main(String[] args)
    {
    //*** Printing a menu and the number of items required when ordering a menu selection.
    System.out.println("Tims Menu - COUNT YOUR ORDER SIZE BEFORE ORDERING (sorry for the inconvenience)\n\n" +
        "1. Soup\t[3 items - includes bowl and plastic spoon]\n" +
        "2. Brewed Coffee\t[1 item]\n" +
        "3. Donut\t[1 item]\n" +
        "4. Mug\t[1 item]\n" +
        "5. Thermos\t[1 item]\n\n" +
        "PRESS ENTER TO CONTINUE" );
    Scanner sc = new Scanner(System.in);  //Scanner used to allow the user to order when they are ready.
    sc.nextLine();  //Taking input from the user.
    
    TimsOrder t = TimsOrder.create(); //Creating the TimsOrder by calling its static factory method.
    System.out.println(t);  //Print the returned String for the created order's toString() method.
    System.out.printf("Total Price: $%.2f\n", t.getAmountDue());  //Print the amount due based on the order.
    }
  }
