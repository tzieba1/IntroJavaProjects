package Assignment4_000797152;

import java.util.Scanner;

/**
 * Purpose: The class representing the card shuffle program and its view. Contains the main method that controls
 * the view of the program.
 *
 * Description: A prompt is provided for a user to input the number of suits and maximum rank for a deck of cards.
 * The deck of cards is outputted together with a menu in a loop containing options to shuffle once, deal one hand,
 * deal 10 000 times, or exit the program.
 *
 * @author Tommy Zieba
 */
public class CardShuffle
  {
  public static void main(String[] args)
    {
    Scanner input = new Scanner(System.in);
    
    int user_suits;   // Declaring the maximum number of suits the user will choose at a prompt.
    int user_ranks;   // Declaring the number of ranks the user will choose at a prompt.
    int menu_choice;  // Declaring the menu selection from the user after the deck has been constructed.
    
    System.out.println("Please choose a maximum number of suits from a standard deck of 52 playing cards\n" +
        "to include in your deck of cards (integer value 1-4 inclusive):");     // Prompt for user to select user_suits.
    user_suits = input.nextInt();                                               // Assigning user input to user_suits.
    // *** Validation of user_suits to be some integer in the correct range.
    while ( user_suits < 1 | user_suits > 4)
      {
      System.out.println("\nInvalid input!\n\n Please choose a maximum number of suits from a standard\n" +
          " deck of 52 playing cards" + "to include in your deck of cards (integer value 1-4 inclusive): ");
      user_suits = input.nextInt();
      }
    
    
    System.out.println("Please choose a maximum number of ranks from a standard deck of 52 playing cards\n" +
        "to include in your deck of cards (integer value 1-13 inclusive):");    // Prompt for user to select user_ranks.
    user_ranks = input.nextInt();                                               // Assigning user input to user_ranks.
    // *** validation of user_ranks to be some integer in the correct range.
    while ( user_ranks < 1 | user_ranks > 13)
      {
      System.out.println("\nInvalid input!\n\n Please choose a maximum number of ranks from a standard\n" +
          " deck of 52 playing cards" + "to include in your deck of cards (integer value 1-13 inclusive): ");
      user_ranks = input.nextInt();
      }

    DeckOfCards user_deck = new DeckOfCards(user_ranks, user_suits);       // Creating the user's deck of cards.
    
    // *** Loop for prompting user with a menu and taking options.
    do
      {
      // ***  Printing the users deck state and prompting menu options.
      System.out.print(
          "\n=================================================================================================\n" +
          "\n" + user_deck +
          "\n=================================================================================================\n");
      System.out.println("Select a menu option:\n" +
          "1. Shuffle your deck once.\n" +
          "2. Deal a selected number of cards from the deck.\n" +
          "3. Shuffle 100 000 hands of a selected amount.\n" +
          "4. Quit.\n");
      menu_choice = input.nextInt();     // Assigning the users selected menu choice.
      // ***  Validation of menu input.
      while ( menu_choice < 1 | menu_choice > 4 )
        {
        System.out.print(
            "\n=================================================================================================\n" +
            "\n" + user_deck +
            "\n=================================================================================================\n");
        System.out.println("\nInvalid input!\n\n Please choose a menu option listed below:\n " +
            "1. Shuffle your deck once.\n" +
            "2. Deal a selected number of cards from the deck.\n" +
            "3. Shuffle 100 000 hands of a selected amount.\n" +
            "4. Quit.\n");
        menu_choice = input.nextInt();    // Updating the users menu choice.
        }

      // *** Switch statement for handling each menu option selected by user.
      switch (menu_choice)
        {
        case 1:
          user_deck.shuffle();    // Shuffles the user's deck.
          break;
        case 2:
          // ***  Prompting user to enter a number of cards to be dealt in the hand.
          System.out.print("Enter the number of cards to be dealt from the standard deck of 52 playing cards\n" +
              "without repetition for your hand: ");
          int hand_size = input.nextInt();
    
          // ***  Validation for the users inputted hand size.
          while (hand_size < 1 | hand_size > user_deck.getDeckSize())
            {
            System.out.println("Invalid input!\n\n Give an integer in correct range: ");
            hand_size = input.nextInt();    // Updating the users selected hand size.
            }
    
          user_deck.deal(hand_size);
          break;
        case 3:
          // ***  Prompting user to enter a number of cards to be dealt in each hand.
          System.out.println("Enter the number of cards to be dealt for each hand from the selected deck\n" +
              " without repetition: ");
          int single_hand_size = input.nextInt();
    
          // ***  Validation for the users inputted hand size.
          while (single_hand_size < 1 | single_hand_size > user_deck.getDeckSize())
            {
            System.out.println("\nInvalid input!\n\n Give an integer in correct range: ");
            single_hand_size = input.nextInt();    // Updating the users selected hand size.
            }
    
          // ***  Printing the histogram of hand values associated to 100 000 hands dealt.
          System.out.println("Hand value:     Number of hands:");
          user_deck.histogram(single_hand_size);
          break;
        case 4:
          break;
        }
      } while ( menu_choice != 4);
    }
  }
