package ArrayListAndObjectEqualsMethod;

import java.util.ArrayList;
import java.util.Scanner;

public class ArrayVersusArrayList
  {
  public static void main(String[] args)
    {
    String[] names = new String[5];
    Scanner input  = new Scanner(System.in);
   
    //*** Array can be partially built.
    int count = 0;
    while (count <  5)
      {
      System.out.println("Enter a name (blank to exit): ");
      names[count] = input.nextLine();
      if (names[count].equals(""))
        break;
      count++;
      }
    
    //*** Removing first item and shifting the items in the 'alnames' ArrayList.
    for (int i = 0; i < count - 1; i++ )
      names[i] = names [i + 1];
    count --;
    
    for (int i = 0; i < count; i++)
      System.out.println(names[i]);
  
    
    //*** The syntax 'ArrayList<Type>' requires the Type to be an object type (int, double, boolean will NOT work).
    //    Use a wrapper. I.e. int -> Integer, double -> Double, etc..
    ArrayList<String> alnames = new ArrayList<>();
  
    while (true)
      {
      System.out.println("Enter a name (blank to exit): ");
      String temp = input.nextLine();
      if (temp.equals(""))
        break;
      alnames.add(temp);
      }
      alnames.remove(0);  // Manages the removal and shifting of ArrayList items in one line.
  
    //*** Printing the 'alnames' ArrayList.
    for (String name : alnames)
      {
      System.out.println(name);

      //*** Search for an item.
      if (alnames.contains("Susan"))
        System.out.println("Susan is in the list.");
      else
        System.out.println("Susan is not on the list.");
      }
    }
  }
