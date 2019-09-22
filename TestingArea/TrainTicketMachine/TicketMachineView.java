package TrainTicketMachine;

import java.util.Scanner;

/**
 * Puropose:  A train station ticket counter dialogue which provides enumerated options
 * for the user to choose from as input. Options appear in 3 possible menus: ticket counter,
 * machine 1, and machine 2. Users choose a machine which have different ticket options
 * to 'print' a valid train ticket.
 *
 * Date: May 31, 2019
 *
 * @author Tommy Zieba
 */
public class TicketMachineView
  {

  /**
   * The main method which executes the program.
   * @param args unused
   */
  public static void main(String[] args)
    {
    TicketMachine machine = new TicketMachine();    // Declaring a new instance of a ticket machine.
    Scanner input = new Scanner(System.in);         // Declaring a new instance of the Scanner for user input.
    int choice = 0;                                 // Initializing ticket counter menu choice for user input.
    boolean user_active = true;                     // Setting flag for ticket counter being active (program running).
    boolean mach_active = false;                    // Setting flag for a machine being active.
    
    
    while(user_active)                      // Loop which terminates when the user is not active at the ticket counter.
      {
      do                                                             // Loop prompting user with ticket menu options.
        {
        System.out.print("\n=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=\n" +
            "Welcome to the Ticket Counter!\n" +
            "=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=\n\n" +
            "1. Approach first machine.\n" +
            "2. Approach second machine.\n" +
            "3. Exit\n" +
            "===================================\n" +
            "Your choice: ");
        choice = input.nextInt();                                    // Storing user input.
        if (choice == 1 || choice == 2)
          break;
        }while(choice != 3);
  
      do                                                             // Condition for machine 1 menu options.
        {
        if (choice == 1)
          {
          choice = 0;                // Resetting the choice for the ticket counter menu.
          mach_active = true;        // Setting flag for user being active at machine 1.
          machine.setFare1();        // Setting the fares for the 'machine' instance of the TicketMachine class.
          do              // Loop prompting user with machine 1 menu options.
            {
            System.out.println(machine);   // Printing the return value for the 'machine' instance's toString() method.
            System.out.print(
                "==================================\n" +
                    "1. Select route 'Uptown'.\n" +
                    "2. Select route 'Downtown'.\n" +
                    "3. Add adult fare.\n" +
                    "4. Remove adult fare.\n" +
                    "5. Add child fare.\n" +
                    "6. Remove child fare.\n" +
                    "7. Insert quarter. \n" +
                    "8. Insert loonie. \n" +
                    "9. Insert toonie. \n" +
                    "10. Print ticket. \n" +
                    "11. Step away from machine. \n" +
                    "==================================\n" +
                    "Your choice: ");                                     // Printed user options menu for machine 1.
            int machine_choice = input.nextInt();               // User input of machine 1 menu selection.

            switch (machine_choice)      // Changes the state of 'machine' based on cases depending on users input.
              {
              case 1:
                machine.uptownRoute();  // Changes route to 'Uptown'.
                break;
              case 2:
                machine.downtownRoute();  // Changes route to 'Downtown'.
                break;
              case 3:
                machine.addAdult();       // Adds adult to ticket.
                break;
              case 4:
                machine.removeAdult();    // Removes adult from ticket.
                break;
              case 5:
                machine.addChild();       // Adds child to ticket.
                break;
              case 6:
                machine.removeChild();    // Remove child from ticket.
                break;
              case 7:
                machine.insertQuarter();   // Adds $0.25 to credit.
                break;
              case 8:
                machine.insertLoonie();   // Adds $1.00 to credit.
                break;
              case 9:
                machine.insertToonie();   // Adds $2.00 to credit.
                break;
              case 10:
                machine.ticketPrint();    // Checks if ticket is printable.
                if (machine.ticket_print) // Prints ticket when 'machine' variable 'ticket_print' has value true.
                  {
                  System.out.println(
                      "\n*******************************************************************************\n" +
                      "......PRINTING TICKET......" +
                      "\n*******************************************************************************\n");
                  // Output indicating to user a ticket is being printed.
                  break;
                  }
                else
                  {
                  System.out.println(
                      "\n*******************************************************************************\n" +
                      "Not enough credit to print this ticket and/or no route selected and/or no passengers selected." +
                          "Pleases try again." +
                      "\n*******************************************************************************\n");
                  // Output indicating to user a ticket cannot be printed.
                  break;
                  }
              case 11:
                mach_active = false;  // Turning off machine activity which breaks loop and prompts user with ticket
                                      // counter menu.
                break;
              }
            } while (mach_active);    // Testing the looping condition for displaying machine 1 menu (while active).
          }
        if (choice == 2)
          {
          choice = 0;             // Resetting the choice for the ticket counter menu.
          mach_active = true;      // Setting flag for user being active at machine 2.
          machine.setFare2();       // Setting the fares for the 'machine' instance of the TicketMachine class.
          do          // Loop prompting user with machine 1 menu options.
            {
            System.out.println(machine);    // Printing the return value for the 'machine' instance's toString() method.
            System.out.print(
                "==================================\n" +
                    "1. Select route 'Main St.'.\n" +
                    "2. Select route 'King St.'.\n" +
                    "3. Add adult fare.\n" +
                    "4. Remove adult fare.\n" +
                    "5. Add child fare.\n" +
                    "6. Remove child fare.\n" +
                    "7. Insert quarter. \n" +
                    "8. Insert loonie. \n" +
                    "9. Insert toonie. \n" +
                    "10. Print ticket. \n" +
                    "11. Step away from machine. \n" +
                    "==================================\n" +
                    "Your choice: ");                         // Printed user options menu for machine 2.
            int machine_choice = input.nextInt();   // User input of machine 2 menu selection.

            switch (machine_choice)     // Changes the state of 'machine' based on cases depending on users input.
              {
              case 1:
                machine.mainStRoute();    // Changes route to 'Main St.'.
                break;
              case 2:
                machine.kingStRoute();    // Changes route to 'King St.'.
                break;
              case 3:
                machine.addAdult();       // Adds adult to ticket.
                break;
              case 4:
                machine.removeAdult();    // Removes adult from ticket.
                break;
              case 5:
                machine.addChild();       // Adds child to ticket.
                break;
              case 6:
                machine.removeChild();    // Remove child from ticket.
                break;
              case 7:
                machine.insertQuarter();  // Adds $0.25 to credit.
                break;
              case 8:
                machine.insertLoonie();   // Adds $1.00 to credit.
                break;
              case 9:
                machine.insertToonie();   // Adds $2.00 to credit.
                break;
              case 10:
                machine.ticketPrint();      // Checks if ticket is printable.
                if (machine.ticket_print)   // Prints ticket when 'machine' variable 'ticket_print' has value true.
                  {
                  System.out.println(
                      "\n*******************************************************************************\n" +
                      "......PRINTING TICKET......" +
                      "\n*******************************************************************************\n");
                  // Output indicating to user a ticket is being printed.
                  break;
                  }
                else
                  {
                  System.out.println(
                      "\n*******************************************************************************\n" +
                      "Not enough credit to print this ticket and/or no route selected and/or no passengers selected. " +
                          "Please try again." +
                      "\n*******************************************************************************\n");
                  // Output indicating to user a ticket cannot be printed.
                  break;
                  }
              case 11:
                mach_active = false;    // Turning off machine activity which breaks loop and prompts user with ticket
                                        // counter menu.
                break;
              }
            } while (mach_active);  // Testing the looping condition for displaying machine 2 menu (while active).
          }
        }while(mach_active);     // Testing the looping condition for displaying either machine 1 or 2 menu when active.
     
     
      if ( choice == 3 )      // Condition for exiting the program from the ticket menu.
        {
        user_active = false;  // Setting user activity for ticket counter to false when a user inputs '3' to exit.
        System.out.println(
            "\n\n=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=\n" +
                "Goodbye. Come back soon!");                       // Message prompting user that program is over.
        }
      }
    }
  }
