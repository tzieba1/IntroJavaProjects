package ArrayListAndObjectEqualsMethod;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Solution to exercises 1a, 1b, 2b, week 10.
 *
 * @author Sam Scott
 */
public class VehicleTest {

    public static Vehicle create() {
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Car, Motorcycle or Truck? (c/m/t) ");
            String ct = sc.nextLine();
            char selected = ct.toLowerCase().charAt(0);
            if (selected != 'e')
              System.out.println("Horsepower? ");
              int hp = sc.nextInt();
            
            if (selected == 'c')
                return new Car(hp);
            else if (selected == 'm')
                return new MotorCycle(hp);
            else if (selected == 't')
                return new Truck(hp);
            System.err.println("Must enter C/T/M - Try again");
        }
    }

    public static void main(String[] args) {
            ArrayList<Vehicle> vehicles = new ArrayList<>();
            Scanner input = new Scanner(System.in);
            
            //*** Use an ArrayList to output any number of vehicles.
            while (true)
              {
              Vehicle v = create();
              if (vehicles.contains(v))
                System.out.println(v + " is already in the list.");
              else
                vehicles.add(v);
              System.out.println("More vehicles? (Y/N) ");
              if (input.nextLine().equals("N"))
                break;
              }
    
            //*** Print Vehicles to the screen. This does not work without a .equals() method for each vehicle object.
            //    Check the Car methodfor an example of the overridden .equals() method.
            for (Vehicle v : vehicles)
              System.out.println(v);

        }

}
