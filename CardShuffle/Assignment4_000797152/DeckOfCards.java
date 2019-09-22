package Assignment4_000797152;

import java.util.Random;

/**
 * Purpose:   Class representing a deck of cards to be shuffled and dealt.
 *
 * Description:   Constructed with a max_rank and num_suits. A max_value is produced upon construction using the
 * constructor parameters. The deck is an array created within the constructor. The getter methods
 * return max_value = deck_size and MIN_VALUE. Other methods allow the cards to be shuffled or hands to be
 * dealt in the associated CardShuffle class. Another method produces the histogram to be displayed whenever
 * the user deals 10 000 hands in the associated CardShuffle class.
 *
 * @author Tommy Zieba
 */
public class DeckOfCards
  {
  /** Array of cards representing a deck. **/
  private Card[] deck;
  /** The maximum rank possible for a card. **/
  private int max_rank;
  /** The number of suits possible for a card. **/
  private int num_suits;
  /** Minimum value of any card from the deck. **/
  private final int MIN_VALUE = 1;
  /** Maximum value of any card from the deck (equivalent to deck size). **/
  private int max_value;

  
  /**
   * Null constructor. Not used.
   */
  public DeckOfCards() {}

  
  /**
   * Constructor that creates a deck of cards and initializes the max_value
   *
   * @param max_rank The maximum rank a card in the deck can have.
   * @param num_suits The number of possible suits a card in the deck can have.
   */
  public DeckOfCards(int max_rank, int num_suits)
    {
    this.max_rank = max_rank;                 // Setting maximum rank for a DeckOfCards instance.
    this.num_suits = num_suits;               // Setting number of possible suits for a DeckOfCards instance.
    this.max_value = max_rank * num_suits;    // Setting max value a card can have for a DeckOfCards instance.
    
    // ***  While loop iterating through the deck size and creating cards in a deck array.
    int count_deck = 0;                             // Initializing the counter for cards created in deck array.
    this.deck = new Card[this.getDeckSize()];       // Declaring a deck array of the appropriate size.
    while(count_deck != this.getDeckSize() )
       {
       // *** Nested loop that traverses each index in a (rank x suit) matrix representation of the deck of cards.
       for(int count_suit = 0; count_suit < this.max_rank ; count_suit++)
         {
         for(int count_rank = 0; count_rank < this.num_suits ; count_rank++)
           {
           this.deck[count_deck] = new Card(count_rank + 1, count_suit + 1);// Each card of possible rank/suit is added to deck.
           count_deck++;         // New card implies iterating to next card in deck.
           }
         }
       }

    }

  
  /**
   * Void method used to shuffle cards in the deck. A loop passes over each index of the array representing the deck
   * and switches the card with another card at a random index using a temporary card.
   */
  public void shuffle()
    {
    // ***  Loop that switches a card at each index with another randomly.
    for (int i = 0; i < deck.length; i++)
      {
      // ***  Using the Random class imported through java.util containing .nextInt() method that returns random
      //      integers from a range [0, n-1] where n is the .nextInt() 'bound' parameter being passed.
      Random random = new Random();
      
      // ***  Assigning a random integer representing the index of a card in the deck to be switched with card i.
      //      A do-while is used to prevent the card from switching with itself.
      int random_card;
      do{ random_card = random.nextInt( deck.length ); }while( random_card == i );
      
      // ***  Creating a temporary card and switching the cards.
      Card temp_card = deck[i];
      deck[i] = deck[random_card];
      deck[random_card] = temp_card;
      }
    }

  
  /**
   * Method that retrieves the size of the deck.
   *
   * @return Returns the deck size as an integer.
   */
  public int getDeckSize() {return this.max_value;}

  
  /**
   * Method that retrieves the max value of any card from the deck.
   *
   * @return  Returns the maximum card value as an integer.
   */
  public int getMaxValue() {return this.max_value;}

  
  /**
   * Method that deals a number of cards in a hand according to the parameter num_cards.
   *
   * @param num_cards The number of cards to be dealt in the hand.
   * @return Returns the array cards_dealt containing all of the cards required to be dealt in a hand.
   */
  public void deal(int num_cards)
    {
    // *** Declares array of cards with size being number of cards dealt.
    Card[] cards_dealt = new Card[num_cards];
    // *** Statement prompting user that the cards in the hand will be printed.
    System.out.println("Your cards: ");
    // ***  Loop which prints each card and starts a new line.
    for(int i = 0; i < num_cards; i++) { System.out.println(deck[i].toString() + ",");}
    }

  /**
   * Method used to produce an array representing the histogram that can be returned to the CardShuffle class for
   * its output on the display. The histogram represents card values for shuffled hands dealt with a size
   * being parametrized in the method argument.
   *
   * @param num_cards The number of cards for each hand dealt in the histogram.
   * @return Returns a histogram as an Integer array. Card values occurring over the series of hands dealt represent
   * the array indices.
   */
  public void histogram(int num_cards)
    {
    final int MIN_HAND_VALUE = 1;   // Possibly dealing one card at a time means the minimum hand value is constant.
    int max_hand_value = 0;     // Declaring the max hand value possible for a hand of particular size = num_cards.

    // ***  The max_value of a card can only happen once in any single hand, the next highest card value has exactly
    //      2 ways to happen in any single hand. In general, treating rank = m and suit = n, using an m x n matrix shows
    //      that indices are unique (rank, suit)-pairs having card values like in a multiplication table.
    //
    //      Need to initialize an array with enough indices to store up to the max_hand_value that occurs
    //      when drawing cards without repetition from a standard deck of casino playing cards. Note that by
    //      fundamental theorem of arithmetic it can be shown that any integer between the min and max hand value
    //      can be reached by some combination of cards that are dealt.

    // ***  Without repetition, the following array size (= max_hand_value) must be computed and initialized:
    // ***  Iterating over matrix indices below with a nested loop that updates the max_hand_value based on the value
    //      of the next card in the matrix representing all cards of the standard deck. Hence, if all cards are dealt
    //      then the max_hand_value is reached. Any integer value between the MIN_HAND_VALUE = 1
    for (int count_suits = this.num_suits; count_suits >= 1; count_suits--)     // Iterating over ranks.
      {
      for (int count_rank = this.max_rank; count_rank >= 1; count_rank--)       // Iterating over suits.
        {
        max_hand_value += count_rank * count_suits;                             // Updating max hand value.
        }
      }

    int[] graph = new int[max_hand_value];        // Declaring the array storing the histogram 'graph'.
    for(int count = 0; count < max_hand_value - 1; count++) {graph[count] = 0;}
    // ***  Loop iterating over each of the 100 000 hands to be dealt.
    for (int i = 1; i <= 100000; i++)
      {
      this.shuffle();                             // Shuffling the deck.
      Card[] curr_hand = new Card[num_cards];    // Declaring an array to hold the current hand of 100 000.
      // *** Loop that deals each card from the deck and stores them in the cards_dealt array.
      for(int j = 0; j < num_cards; j++) {curr_hand[j] = deck[j];}
      // ***  Loop iterating over cards in current hand to compute the curr_hand_value and account
      //      for it in the histogram.
      int curr_hand_value = 0;                          // Declaring curr_hand_value.
      for (int j = 0 ; j < curr_hand.length ; j++)   // Iterating over cards in current hand.
        {
        curr_hand_value += curr_hand[j].getValue();     // Adding each card value to the current hand value.
        }
      // ***  Increasing the count for the histogram graph in the index representing the curr_hand_value.
      graph[curr_hand_value] += 1;
      }
    
    // *** Loop used to print the histogram when the method is called by iterating over graph indices.
    for (int count = 0; count <= graph.length - 1; count++)
      {
      if(count < 9)         // Conditional statement to line up characters in console view.
        System.out.printf("%d:                  %d\n", count + 1, graph[count]);   // Printing one entry of histogram.
      else
        System.out.printf("%d:                 %d\n", count + 1, graph[count]);   // Printing one entry of histogram.
      }
    }

  /**
   * Method that returns a String which describes the state of the deck of cards to be printed.
   * @return Returns a String specifying the deck size, max/min values, and the top card on the deck.
    */
  public String toString()
    {
    return String.format("Deck size: %d   Lowest value: %d   Highest value: %d   Top Card: %s\n", this.max_value,
        this.MIN_VALUE, this.max_value, this.deck[0].toString());
    }
  
  }
