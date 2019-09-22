package Assignment4_000797152;

/**
 * Purpose:   Class representing a card with a particular rank and suit.
 *
 * Description:   Constructed with a rank and suit. A value is produced upon construction using the
 * constructor parameters; rank and suit. The rank, suit, and value are accessed using getter methods. A Card is
 * associated to the DeckOfCards class and is created there.
 *
 * @author Tommy Zieba
 */
public class Card
  {
  private int rank;   // Declaring the rank of the card to an instance of Card.
  private int suit;   // Declaring the suit of the card to an instance of Card.
  private int value;  // Declaring the value of a card to an instance of Card.

  /**
   * Null constructor for the Card class. Not used.
   */
  public Card() {}

  /**
   * Constructor for the Card class. The state for an instance of Card is initialized when the parameters are passed.
   *
   * @param rank The rank of the Card instance.
   * @param suit The suit of the Card instance.
   */
  public Card(int rank, int suit)
    {
    this.rank = rank;           // Assigning the card rank passed into the constructor for an instance of Card.
    this.suit = suit;           // Assigning the card suit passed into the constructor for an instance of Card.
    this.value = suit * rank;   // Computing and assigning the card value for an instance of Card.
    }

  /**
   * Method used to access the rank of a card.
   * @return Returns the card rank.
   */
  public int getRank() { return this.rank; }

  /**
   * Method used to access the suit of a card.
   * @return Returns the card suit.
   */
  public int getSuit() { return this.suit; }

  /**
   * Method used to access the value of a card.
   * @return Returns the value of a card.
   */
  public int getValue() { return this.value; }

  /**
   * Method that returns a String which describes the state of a card to be printed.
   * @return Returns a String specifying the rank and suit of a card.
   */
  public String toString() { return String.format("R%d/S%d", this.rank, this.suit );}
  }
