package Assignment6_000797152;

/**
 * This class is the Wizard subclass of the Human superclass. Each wizard additionally has a magic rating in the range
 * [0, 20] and can cast a healing spell at the expense of magic rating.
 */
public class Wizard extends Human
  {
  /** The magic rating for this Wizard in the range [0, 20]. **/
  private int magic_rating;

  /**
   * The default constructor for a Wizard instance.
   */
  public Wizard()
    {
    super("Wizard" ); //Default Wizard name passed into Human super-constructor parametrized by a name.
    this.magic_rating = 8;  //Initializing default magic rating for this Wizard.
    }

  /**
   * The constructor that creates an instance of Wizard by passing in only a name for the Wizard.
   * @param name The name given to this Wizard being created.
   */
  public Wizard( String name )
    {
    super( name );  //Wizard name passed into Human super-constructor parametrized by a name.
    this.magic_rating = 10; //Initializing default magic rating for this named Wizard.
    }

  /**
   * The constructor that creates an instance of Wizard by passing in name and attribute ratings for the Wizard.
   * @param name The name of this Wizard being created.
   * @param strength The strength rating of the Wizard [0,20].
   * @param dexterity The dexterity rating of the Wizard [0,20].
   * @param armour The armour rating of the Wizard [0,20].
   * @param moxie The moxie rating of the Wizard [0,20].
   */
  public Wizard( String name, int strength, int dexterity, int armour, int moxie )
    {
    super( name, strength, dexterity, armour, moxie ); //Super-constructor initializes object with passed in name/attr.
    this.magic_rating = 10; //Initializing default magic rating for this named Wizard with given attributes.
    }

  /**
   * Constructor that creates an instance of Wizard by passing in only the magic rating in the range [0, 20]
   * @param magic_rating The magic rating for this Wizard being created.
   */
  public Wizard(int magic_rating)
    {
    super("Wizard" ); //Default Wizard name passed into Human super-constructor parametrized by a name.
    
    //*** Validating the magic rating passed in for initialization.
    if ( magic_rating >= 0 && magic_rating <= 20 )
      this.magic_rating = magic_rating; //Initializing magic rating when parameter passed is valid.
    else
      this.magic_rating = 10; //Initializing magic rating when parameter passed is not valid.
    }

  /**
   * Constructor that creates an instance of Wizard by passing in only the Wizard name and magic rating in the
   * range [0, 20].
   * @param name  The name for this Wizard being created.
   * @param magic_rating The magic rating for this Wizard being created.
   */
  public Wizard( String name, int magic_rating )
    {
    super( name );  //Wizard name passed into Human super-constructor parametrized by a name.
    
    //*** Validating the magic rating passed in for initialization.
    if ( magic_rating >= 0 && magic_rating <= 20 )  //Initializing magic rating when parameter passed is valid.
      this.magic_rating = magic_rating;
    else
      this.magic_rating = 10; //Initializing magic rating when parameter passed is not valid.
    }

  /**
   * The constructor that creates an instance of Wizard by passing in name, attribute ratings and magic rating
   * for the Wizard.
   * @param name The name of this Wizard being created.
   * @param strength The strength rating of the Wizard [0,20].
   * @param dexterity The dexterity rating of the Wizard [0,20].
   * @param armour The armour rating of the Wizard [0,20].
   * @param moxie The moxie rating of the Wizard [0,20].
   * @param magic_rating The magic rating for this Wizard [0, 20].
   */
  public Wizard( String name, int strength, int dexterity, int armour, int moxie, int magic_rating )
    {
    super( name, strength, dexterity, armour, moxie ); //Super-constructor initializes object with passed in name/attr.

    //*** Validating the magic rating passed in for initialization.
    if ( magic_rating >= 0 && magic_rating <= 20 ) //Initializing magic rating when parameter passed is valid.
      this.magic_rating = magic_rating;
    else
      this.magic_rating = 10;  //Initializing magic rating when parameter passed is not valid.
    }

  /**
   * Method used for this Wizard to cast a healing spell whenever this Wizard is alive and has a non-zero magic rating.
   * Healing adds half of this Wizard's magic rating to this Wizard's health rating and subtracts 3 from this Wizard's
   * magic rating.
   */
  public void castHealingSpell()
    {
    if ( magic_rating > 0 && this.isAlive() ) //Checking if this Wizard is alive.
      {
      this.raiseHealth(magic_rating / 2); //Raising health by half the magic rating of this Wizard.
      this.magic_rating -= 3; //Subtracting 3 from this Wizard's magic rating.
      }
    }

  /**
   * Overridden method from the Human superclass that is modified to indicate a Wizard subclass.
   * @return Returns the String representation of this Wizard with the name, health, coins, strength, dexterity,
   *    * armour and moxie listed.
   */
  @Override
  public String toString()
    {
    return (this.getName().equals("Wizard") ? "Wizard:\n" : this.getName() +  " the Wizard:\n")
        + "|Health = " + this.getHealth() + " \tCoins = " + this.getCoins() + "|\n"
        + "|Strength = " + this.getStrength() + "\tDexterity = " + this.getDexterity() +"\tArmour = " + this.getArmour()
        + "\tMoxie = " + this.getMoxie() + "|\n";
    }
  }
