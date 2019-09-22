package Assignment6_000797152;

/**
 * This class is the Elf subclass of the Humanoid superclass. Elves have a best friend who is a Hobbit and can only
 * belong to either 'forest' or 'city' clan.
 *
 * @author Tommy Zieba
 */
public class Elf extends Humanoid
  {
  /** Constant that represents the city clan for an elf. **/
  private final String CITY = "city";
  /** Constant that represents the forest clan for an elf. **/
  private final String FOREST = "forest";
  /** The string representing the clan that this instance of Elf has.**/
  private String clan;
  /** The Hobbit that represents this Elf's best friend.**/
  private Hobbit best_friend;

  /**
   * The default constructor for an Elf. Uses a default name passed into Humanoid super-constructor.
   */
  public Elf()
    {
    super("Elf" ); //Default Elf name passed into Humanoid super-constructor parametrized by a name.
    this.clan = CITY; //Default clan for unnamed Elf is the 'city' clan.
    this.best_friend = new Hobbit();  //A new Hobbit object instance is created as a default best friend.
    }

  /**
   * The constructor that creates an instance of Elf by passing in only a name for the Elf.
   * @param name The name given to this Elf being created.
   */
  public Elf( String name )
    {
    super( name );  //Elf name passed into Humanoid super-constructor parametrized by a name.
    this.clan = FOREST; //Default clan for unnamed Elf is the 'forest' clan.
    this.best_friend = new Hobbit();  //A new Hobbit object instance is created as a default best friend.
    }

  /**
   * The constructor that creates an instance of Elf by passing in only a name and clan for the Elf.
   * @param name The name given to this Elf being created.
   * @param clan The clan given to this Elf being created.
   */
  public Elf( String name, String clan )
    {
    super(name);  //Elf name passed into Humanoid super-constructor parametrized by a name.
    this.best_friend = new Hobbit(); //A new Hobbit object instance is created as a default best friend.
    
    //*** Validating if one of the two possible clans is passed in.
    if (clan.equals(FOREST) || clan.equals(CITY))
      this.clan = clan; //Initializing the passed clan as this Elf's clan whenever valid.
    else
      this.clan = CITY; //Initializing the clan for this Elf's to be 'city' whenever invalid clan is passed.
    }

  /**
   * The constructor tht creates an instance of Elf by passing in only a name, clan and best friend.
   * @param name The name given to this Elf being created.
   * @param clan The clan given to this Elf being created.
   * @param best_friend The best friend for this Elf being created.
   */
  public Elf( String name, String clan, Hobbit best_friend )
    {
    super(name);  //Elf name passed into Humanoid super-constructor parametrized by a name.
    this.best_friend = best_friend; //The Hobbit passed in is initialized as this Elf's best friend.

    //*** Validating if one of the two possible clans is passed in.
    if (clan.equals(FOREST) || clan.equals(CITY))
      this.clan = clan; //Initializing the passed clan as this Elf's clan whenever valid.
    else
      this.clan = CITY; //Initializing the clan for this Elf's to be 'city' whenever invalid clan is passed.
    }

  /**
   * The constructor tht creates an instance of Elf by passing in only a name and best friend.
   * @param name The name given to this Elf being created.
   * @param best_friend The best friend for this Elf being created.
   */
  public Elf( String name, Hobbit best_friend )
    {
    super(name);  //Elf name passed into Humanoid super-constructor parametrized by a name.
    this.clan = FOREST; //Default clan for an Elf with a name and best friend is the 'forest' clan.
    this.best_friend = best_friend; //The Hobbit passed in is initialized as this Elf's best friend.
    }
  /**
   * The constructor that creates an instance of Elf by passing in name, attribute ratings, clan and best friend
   * for the Elf.
   * @param name The name of this Elf being created.
   * @param strength The strength rating of the Elf [0,20].
   * @param dexterity The dexterity rating of the Elf [0,20].
   * @param armour The armour rating of the Elf [0,20].
   * @param moxie The moxie rating of the Elf [0,20].
   * @param clan The clan given to this Elf being created.
   * @param best_friend The best friend for this Elf being created.
   */
  public Elf( String name, int strength, int dexterity, int armour, int moxie, String clan, Hobbit best_friend )
    {
    super(name, strength, dexterity, armour, moxie);  //Super-constructor initializes object with passed in name/attr.
    this.best_friend = best_friend; //The Hobbit passed in is initialized as this Elf's best friend.
    
    //*** Validating if one of the two possible clans is passed in.
    if (clan.equals(FOREST) || clan.equals(CITY))
      this.clan = clan; //Initializing the passed clan as this Elf's clan whenever valid.
    else
      this.clan = CITY;  //Initializing the clan for this Elf's to be 'city' whenever invalid clan is passed.
    }

  /**
   * Method used to get the name of the clan that this Elf is a part of.
   * @return Returns the String that states the clan name for this Elf.
   */
  public String getClan() { return this.clan; }

  /**
   * Method used to check which Hobbit is the best friend of this Elf.
   * @return Returns the Hobbit that is assigned as this Elf's best friend.
   */
  public Hobbit getBestFriend() { return this.best_friend; }

  /**
   * Method used to set a new set a new best friend for this Elf.
   * @param new_friend The new best friend that the Elf would like to replace his current best friend with.
   */
  public void setBestFriend(Hobbit new_friend ) { this.best_friend = new_friend; }

  /**
   * Overridden method from the Humanoid superclass that is modified to indicate a Elf subclass.
   * @return Returns the String representation of this Elf with the name, health, coins, strength, dexterity,
   *    * armour and moxie listed.
   */
  @Override
  public String toString()
    {
    return (this.getName().equals("Elf") ? "Elf:\n" : this.getName() + " the Elf:\n")
        + "|Health = " + this.getHealth() + " \tCoins = " + this.getCoins() + "|\n"
        + "|Strength = " + this.getStrength() + "\tDexterity = " + this.getDexterity() +"\tArmour = " + this.getArmour()
        + "\tMoxie = " + this.getMoxie() + "|\n";
    }
  }
