package Assignment6_000797152;

/**
 * This class is the Human subclass of Humanoid superclass. Each human has an elf sworn enemy at creation that does not
 * change, but can be checked.
 *
 * @author Tommy Zieba
 */
public class Human extends Humanoid
  {
  /** Every human has a sworn enemy that is initialized upon construction and cannot change. **/
  private Elf sworn_enemy;

  /**
   * The default Human constructor that initializes the private variables for this Human instance with the
   * super-constructor that passes in a name indicating a default Human.
   */
  public Human()
    {
    super( "Human" ); //Default human name.
    this.sworn_enemy = new Elf(); //Creates a default Elf sworn enemy.
    }

  /**
   * The constructor that creates an instance of Human by passing in only a name for the Human.
   * @param name The name given to this Human.
   */
  public Human( String name )
    {
    super( name );  //Uses the super-constructor to initialize default attributes with the name passed in.
    this.sworn_enemy = new Elf(); //Creates a default Elf sworn enemy.
    }

  /**
   * The constructor that creates an instance of Human by passing in name and attribute ratings for the Human.
   * @param name The name of the Human
   * @param strength The strength rating of the human [0,20].
   * @param dexterity The dexterity rating of the human [0,20].
   * @param armour The armour rating of the human [0,20].
   * @param moxie The moxie rating of the human [0,20].
   */
  public Human( String name, int strength, int dexterity, int armour, int moxie )
    {
    super( name, strength, dexterity, armour, moxie );  //Super-constructor initializes object with passed in name/attr.
    this.sworn_enemy = new Elf(); //Creates a default Elf sworn enemy.
    }

  /**
   * Constructor that creates an instance of Human by passing in only a sworn enemy.
   * @param sworn_enemy The sworn enemy of this Human.
   */
  public Human(Elf sworn_enemy)
    {
    super( "Human" ); //Super-constructor initializes object with default name and attributes.
    this.sworn_enemy = sworn_enemy; //Sworn enemy initialized.
    }

  /**
   * Constructor that creates an instance of Human by passing in only a name and a sworn enemy.
   * @param name The name of the Human being created.
   * @param sworn_enemy The elf sworn enemy of this Human.
   */
  public Human( String name, Elf sworn_enemy )
    {
    super( name );  //Super-constructor initializes object with passed name and default attributes.
    this.sworn_enemy = sworn_enemy; //Sworn enemy initialized.
    }

  /**
   * Constructor that creates an instance of Human by passing in name, attribute ratings and elf enemy for the Human.
   * @param name The name of the Human
   * @param strength The strength rating of the human [0,20].
   * @param dexterity The dexterity rating of the human [0,20].
   * @param armour The armour rating of the human [0,20].
   * @param moxie The moxie rating of the human [0,20].
   * @param sworn_enemy The elf sworn enemy of this Human.
   */
  public Human( String name, int strength, int dexterity, int armour, int moxie, Elf sworn_enemy )
    {
    super(name, strength, dexterity, armour, moxie); //Super-constructor initializes object with passed in name/attr.
    this.sworn_enemy = sworn_enemy; //Sworn enemy initialized.
    }

  /**
   * Method used to check who this Human's sworn enemy is.
   * @return Returns the Elf object that is this Humans sworn enemy.
   */
  public Elf getSwornEnemy() { return this.sworn_enemy; }
  
  /**
   * Overridden method from the Humanoid superclass that is modified to indicate a Human subclass.
   * @return Returns the String representation of this Human with the name, health, coins, strength, dexterity,
   *    * armour and moxie listed.
   */
  @Override
  public String toString()
    {
    return (this.getName().equals("Human") ? "Human:\n" : this.getName() + " the Human:\n")
      + "|Health = " + this.getHealth() + " \tCoins = " + this.getCoins() + "|\n"
      + "|Strength = " + this.getStrength() + "\tDexterity = " + this.getDexterity() +"\tArmour = " + this.getArmour()
      + "\tMoxie = " + this.getMoxie() + "|\n";
    }
  }
