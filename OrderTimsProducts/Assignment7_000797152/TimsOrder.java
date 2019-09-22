package Assignment7_000797152;

import java.util.Scanner;

/**
 * An instance of this class, TimsOrder, has one or more instances of TimsProduct. A TimsOrder object is parametrized
 * by name and size. From these two parameters, an array of TimsProducts items is created with the specified size. The
 * array of items is used in this class' getAmountDue() method and for returning the state of this TimsOrder.
 *
 * @author Tommy Zieba
 */
public class TimsOrder
  {
  /** Privately decalring he size of this TimsOrder. **/
  private int size;
  /** Privately declaring the name for this TimsOrder. **/
  private String name;
  /** Declaring the array that will store TimsProducts as items added to this TimsOrder. **/
  private TimsProduct[] items;

  /**
   * Default constructor - not used.
   */
  private TimsOrder() {}
  
  /**
   * Constructor for a TimsOrder object that takes in an order name and a size representing the number of items in
   * the order.
   * @param name  The name passed in for this TimsOrder.
   * @param size  The order size passed in for this TimsOrder.
   */
  private TimsOrder( String name, int size )
    {
    this.name = name; //Initializing the name for this TimsOrder.
    this.size = size; //Initializing the size of this TimsOrder.
    this.items = new TimsProduct[ size ]; //Initializing the TimsProduct[] array with size equal to "size" passed in.
    }

  /**
   * Static factory method that creates a TimsOrder object by prompting for user input and using the corresponding
   * static factory methods used to create each TimsProduct in the returned TimsOrder.
   * @return Returns a TimsOrder containing the array of TimsProducts obtained through user prompts.
   */
  public static TimsOrder create()
    {
    Scanner number_input = new Scanner(System.in);  //Creating a scanner object to store numerical input from the user.
    Scanner text_input = new Scanner(System.in);  //Creating a scanner object to store text input from the user.
    
    //*** Prompt user for name and number of items in the order.
    System.out.println("Please provide a name for this order:\n"); //Prompt for an order name.
    String name = text_input.nextLine();  //Assigning user input to store the order name.
    System.out.println("Please provide the size of this order: ");  //Prompt for order size.
    int size = number_input.nextInt(); //Assigning user input to store the order size.
    
    TimsOrder current_order = new TimsOrder(name, size);  //Storing the current order with parametrized name and size.
    
    //*** Loop which takes user input and adds items to the order depending on user input.
    int item_count = 0; //Initializing the counter for the items in the loop.
    while( item_count < size )
      {
      try
        {
        //*** Prompting user for options to add items to their order.
        System.out.println( "What kind of TimsProduct would you like to add to this order?\n" +
            "1. Soup\t[3 items - includes bowl and plastic spoon]\n" +
            "2. Brewed Coffee\t[1 item]\n" +
            "3. Donut\t[1 item]\n" +
            "4. Mug\t[1 item]\n" +
            "5. Thermos\t[1 item]" );

        //*** Switch statement creating item to be added to the order based on user input.
        switch( number_input.nextInt() )
          {
          case 1:
            //*** Calling the create method for a Soup object and assigning it to items[] array in current_order.
            current_order.items[item_count] = Soup.create();
  
            //*** Outputting consumption method to user.
            System.out.println( ((Soup)current_order.items[item_count]).getConsumptionMethod() + "\n" );
            
            //*** Adding a bowl and plastic spoon to the order.
            item_count += 1;  //Item count is increased, moving to next items[] index.
            current_order.items[item_count] = Bowl.create();  //Assigning a created bowl to order items array.
            item_count += 1;  //Item count is increased, moving to next items[] index.
            current_order.items[item_count] = PlasticSpoon.create();  //Assigning a created spoon to order items array.
            item_count += 1;  //Item count is increased, moving to next items[] index.
            break;
          case 2:
            //*** Calling the create method for BrewedCoffee object and assigning it to items[] array in current_order.
            current_order.items[item_count] = BrewedCoffee.create();
  
            //*** Outputting consumption method to user.
            System.out.println( ((BrewedCoffee)current_order.items[item_count]).getConsumptionMethod() + "\n" );
            item_count ++;  //Item count is increased, moving to next items[] array index.
            break;
          case 3:
            //*** Calling the create method for Donut object and assigning it to items[] array in current_order.
            current_order.items[item_count] = Donut.create();
  
            //*** Outputting consumption method to user.
            System.out.println( ((Donut)current_order.items[item_count]).getConsumptionMethod() + "\n" );
            item_count ++;  //Item count is increased, moving to next items[] array index.
            break;
          case 4:
            //*** Calling the create method for Mug object and assigning it to items[] array in current_order.
            current_order.items[item_count] = Mug.create();
            item_count ++;  //Item count is increased, moving to next items[] array index.
            break;
          case 5:
            //*** Calling the create method for Donut object and assigning it to items[] array in current_order.
            current_order.items[item_count] = Thermos.create();
            item_count ++;  //Item count is increased, moving to next items[] array index.
            break;
          }
        }
      catch( Exception ex )  //Catch a null pointer exception or other exception whenever the user input is not valid.
        {
        System.err.println( "Invalid input, try ordering again from the start." );  //Outputting error message.
        item_count = 0;
        }
      }
    return current_order; //Returning the current order after adding the items.
    }

  /**
   * Method used for calculating and returning the total price paid according to elements of the 'items' array
   * for this TimsOrder.
   * @return Returns this TimsOrder's amount due based on elements of the 'items' array as a double.
   */
  public double getAmountDue()
    {
    double total = 0.00;
    for ( TimsProduct item: items )
      total += item.getRetailPrice();
    
    return total;
    }

  /**
   * The overridden toString() method which outputs the state of this TimsOrder.
   * @return Returns a string representation of this TimsOrder that describes its state.
   */
  @Override
  public String toString()
    {
    String orderItems = ""; //String used to list the states of each order item.
    
    //*** Loop used to construct the orderItems String.
    for ( TimsProduct product : items )
      orderItems += ( product.toString() + "\n" );  //Concatenating to orderItem with each element of 'items' array.
    
    
    return "Order Name: " + this.name
        + "\n\n" + orderItems;
    }
  }
