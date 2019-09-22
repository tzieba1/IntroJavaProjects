package PolymorphismAndExceptionHandling.VehicleTesting;

import com.sun.corba.se.impl.io.TypeMismatchException;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Solution to exercises 1a, 1b, 2b, week 10.
 *
 * @author Sam Scott
 */
public class VehicleTest
  {
  
  public static Vehicle create()
    {
    while (true)
      {
      try
        {
        Scanner sc = new Scanner(System.in);
        System.out.println("Car, Truck or Motorcycle? (c/t/m)");
        String ct = sc.nextLine();
        System.out.println("Horsepower? ");
        int hp = sc.nextInt();
        sc.nextLine();
        char selected = ct.toLowerCase().charAt(0);
        if (selected == 'c')
          {
          return new Car(hp);
          } else if (selected == 't')
          {
          return new Truck(hp);
          } else if (selected == 'm')
          {
          return new Motorcycle(hp);
          }
        System.err.println( "Must enter C/T/M - Try again." );
        }
        catch (InputMismatchException ex)
          {
          System.err.println( "Invalid input " + ex.getMessage() );
          }
        catch (IllegalArgumentException ex)
          {
          System.err.println( "Invalid input " + ex.getMessage() );
          }
        catch (Exception ex) {}
      }
    }
  
  public static void main(String[] args)
    {
    Vehicle v1 = create();
    Vehicle v2 = create();
    if (v1.raceAgainst(v2))
      {
      System.out.println(v1 + " is faster than " + v2);
      } else if (v2.raceAgainst(v1))
      {
      System.out.println(v2 + " is faster than " + v1);
      } else
      {
      System.out.println(v1 + " and " + v2 + " have the same racing speed.");
      }
    }
  }
