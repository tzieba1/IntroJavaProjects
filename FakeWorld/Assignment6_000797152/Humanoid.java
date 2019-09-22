package Assignment6_000797152;

/**
 * This class is at the highest level in the object hierarchy for this model representing Humanoids in FakeWorld.
 * Humanoids all have a name that does not change after construction. Other traits are included as parameters in
 * constructors or are set with default values depending on the constructor being used. Other traits include strength,
 * dexterity, armour and moxie ratings from 0 to 20. Humanoids all have health rating from 0 to 100 and are dead when
 * their health is at 0. Cannot create dead Humanoids. All humanoids have coins which can be stolen by a Hobbit.
 *
 * @author Tommy Zieba
 */
public class Humanoid
  {
  /** The name of the Humanoid instance object. **/
  private String name;
  /** The strength rating for this Humanoid instance in the range [0, 20]. **/
  private int strength;
  /** The dexterity rating for this Humanoid instance in the range [0, 20]. **/
  private int dexterity;
  /** The armour rating for this Humanoid instance in the range [0, 20]. **/
  private int armour;
  /** The moxie rating for this Humanoid instance in the range [0, 20]. **/
  private int moxie;
  /** The strength rating for this Humanoid instance in the range [0, 20]. **/
  private int coins;
  /** The amount of coins owned by this Humanoid object instance. **/
  private double health;

  /**
   * The default Humanoid constructor that initializes the private variables for this instance.
   */
  public Humanoid()
  {
  this.name = "Humanoid";
  this.health = 25;
  this.coins = 19;
  this.strength = 10;
  this.dexterity = 10;
  this.armour = 10;
  this.moxie = 10;
  }

  /**
   * The constructor that creates an instance of Humanoid by passing in name and attribute ratings for the Humanoid.
   * @param name The name of the Humanoid
   * @param strength The strength rating of the humanoid [0,20].
   * @param dexterity The dexterity rating of the humanoid [0,20].
   * @param armour The armour rating of the humanoid [0,20].
   * @param moxie The moxie rating of the humanoid [0,20].
   */
  public Humanoid(String name, int strength, int dexterity, int armour, int moxie)
  {
  this.name = name; //Initializing the name from the given parameter.
  this.health = 85; //Initializing the health for a Humanoid instance with these constructor parameters.
  this.coins = 47;  //Initializing the coins for a Humanoid instance with these constructor parameters.
  
  //*** Validating and initializing the Humanoid instance attributes.
  if ( strength >= 0 && strength <= 20 )
    this.strength = strength;
  else
    this.strength = 10;
  if ( dexterity >= 0 && dexterity <= 20 )
    this.dexterity = dexterity;
  else
    this.dexterity = 10;
  if ( armour >= 0 && armour <= 20 )
    this.armour = armour;
  else
    this.armour = 10;
  if ( moxie >= 0 && moxie <= 20 )
    this.moxie = moxie;
  else
    this.moxie = 10;
  }

  /**
   * The constructor that creates an instance of Humanoid by passing in only a name for the Humanoid.
   * @param name The name given to this Humanoid.
   */
  public Humanoid( String name )
  {
  this.name = name; //Initializing this humanoid instances name.
  
  //***
  if ( this instanceof Wizard ) //Initializing strength, dexterity, armour, moxie for a Wizard subclass instance.
    {
    this.strength = 13;
    this.dexterity = 15;
    this.armour = 7;
    this.moxie = 15;
    }
  else if ( this instanceof Fighter ) //Initializing strength, dexterity, armour, moxie for a Fighter subclass instance.
    {
    this.strength = 17;
    this.dexterity = 11;
    this.armour = 13;
    this.moxie = 17;
    }
  else if ( this instanceof Elf ) //Initializing strength, dexterity, armour, moxie for a Elf subclass instance.
    {
    this.strength = 13;
    this.dexterity = 16;
    this.armour = 13;
    this.moxie = 11;
    }
  else if ( this instanceof Hobbit )  //Initializing strength, dexterity, armour, moxie for a Hobbit subclass instance.
    {
    this.strength = 10;
    this.dexterity = 20;
    this.armour = 11;
    this.moxie = 12;
    }
  else  //Initializing strength, dexterity, armour, moxie for any other super or subclass instance.
    {
    this.strength = 10;
    this.dexterity = 10;
    this.armour = 10;
    this.moxie = 10;
    }
  
  if ( this instanceof Human )  //Initializing coins and health for a Human subclass instance.
    {
    this.coins = 100;
    this.health = 68;
    }
  else if ( this instanceof Elf ) //Initializing coins and health for a Elf subclass instance.
    {
    this.coins = 83;
    this.health = 100;
    }
  else if ( this instanceof Hobbit )  //Initializing coins and health for a Hobbit subclass instance.
    {
    this.coins = 145;
    this.health = 82;
    }
  }

  /**
   * Method used to get the name of this humanoid.
   * @return Returns the name as a String.
   */
  public String getName() { return this.name; }

  /**
   * Method used to check if this Humanoid is alive.
   * @return Returns true if the Humanoid has health less than or equal to zero and false otherwise.
   */
  public Boolean isAlive() { return this.getHealth() == 0 ? true : false; }

  /**
   * Method used to get the strength rating for this Humanoid.
   * @return Returns the integer strength rating for this Humanoid in range [0, 20].
   */
  public int getStrength() { return this.strength; }

  /**
   * Method used to get the dexterity rating for this Humanoid.
   * @return Returns the integer dexterity rating for this Humanoid in range [0, 20].
   */
  public int getDexterity() { return this.dexterity; }

  /**
   * Method used to get the armour rating for this Humanoid.
   * @return Returns the integer armour rating for this Humanoid in range [0, 20].
      */
  public int getArmour() { return this.armour; }
  
  /**
   * Method used to get the moxie rating for this Humanoid.
   * @return Returns the integer moxie rating for this Humanoid in range [0, 20].
   */
  public int getMoxie() { return this.moxie; }

  /**
   * Method used to check the number of coins this Humanoid has.
   * @return Returns the integer coin value for this Humanoid.
   */
  public int getCoins() { return this.coins; }

  /**
   * Method used to get the health rating for this Humanoid.
   * @return Returns the integer health rating for this Humanoid in range [0, 100].
   */
  public double getHealth() { return this.health; }

  /**
   * Method used to get the damage score for this Humanoid.
   * @return Returns the damage score for this Humanoid as the average of strength, dexterity and health.
   */
  public double getDamageScore() { return (this.getStrength() + this.getDexterity() + this.getHealth()) / 3; }

  /**
   * Method used to raise the health rating of this Humanoid by the specified amount.
   * @param health The amount of health that is being added to this Humanoids health rating.
   */
  public void raiseHealth( double health ) { this.health = this.health + health > 100 ? 100 : this.health + health; }

  /**
   * Method used to raise the strength rating of this Humanoid by 1 if it is less than 20.
   */
  public void raiseStrength() { this.strength = this.strength < 20 ? this.strength : this.strength + 1; }
  
  /**
   * Method used to raise the dexterity rating of this Humanoid by 1 if it is less than 20.
   */
  public void raiseDexterity() { this.dexterity = this.dexterity < 20 ? this.dexterity : this.dexterity + 1; }
  
  /**
   * Method used to raise the armour rating of this Humanoid by 1 if it is less than 20.
   */
  public void raiseArmour() { this.armour = this.armour < 20 ? this.armour : this.armour + 1; }

  /**
   * Method used to raise the moxie rating of this Humanoid by 1 if it is less than 20.
   */
  public void raiseMoxie() { this.moxie = this.moxie < 20 ? this.moxie : this.moxie + 1; }

  /**
   * Method used to lower the health rating of this Humanoid by the specified amount.
   * @param health The amount of health that is being subtracted from this Humanoids health rating.
   */
  public void lowerHealth( double health ) { this.health = this.health - health < 0 ? 0 : this.health - health; }
  
  /**
   * Method used to lower the strength rating of this Humanoid by 1 if it is greater than 0.
   */
  public void lowerStrength() { this.strength = this.strength > 0 ? this.strength : this.strength - 1; }

  /**
   * Method used to lower the dexterity rating of this Humanoid by 1 if it is greater than 0.
   */
  public void lowerDexterity() { this.dexterity = this.dexterity > 0 ? this.dexterity : this.dexterity - 1; }

  /**
   * Method used to lower the armour rating of this Humanoid by 1 if it is greater than 0.
   */
  public void lowerArmour() { this.armour = this.armour > 0 ? this.armour : this.armour - 1; }

  /**
   * Method used to lower the moxie rating of this Humanoid by 1 if it is greater than 0.
   */
  public void lowerMoxie() { this.moxie = this.moxie > 0 ? this.moxie : this.moxie - 1; }

  /**
   * Method that Humanoid instances use to attack other Humanoid targets and inflict damage. Dead humanoids cannot
   * attack. Damage inflicted is to the target's health rating is determined by this Humanoids damage score.
   * @param target The target Humanoid that is being attacked.
   */
  public void attack(Humanoid target)
    {
    //*** Checks if the  is alive before subtracting this Humanoid's damage score from the target's health rating.
    if ( this.isAlive() )
      target.lowerHealth( this.getDamageScore() );  //Damage being inflicted.
    }

  /**
   * Method that Humanoid instances use to defend against Humanoid attackers. Dead humanoids cannot defend.
   * Damage inflicted is the average of 1 and attacker damage divided by defender armour rating.
   * @param attacker The attacker that this Humanoid is defending themselves against.
   */
  public void defend(Humanoid attacker)
    {
    //*** Checks if the defender is alive before inflicting damage to (lowering) this Humanoids health rating.
    //    Damage inflicted is max of 1 and the attacker damage score divided by this Humanoids armour rating.
    if ( this.isAlive() )
      this.lowerHealth( Math.max( attacker.getDamageScore() / this.getArmour(), 1 ) ); //Damage being inflicted.
    }

  /**
   * The method that gives a String representation of this Humanoid object with its name and attributes. Attributes
   * describe private variables of this Humanoid instance.
   * @return Returns the String representation of this Humanoid with the name, health, coins, strength, dexterity,
   * armour and moxie listed.
   */
  public String toString()
    {
    return (this.getName().equals("Humanoid") ? "Humanoid:\n" : this.getName() + " the Humanoid:\n")
        + "|Health = " + this.getHealth() + " \tCoins = " + this.getCoins() + "|\n"
        + "|Strength = " + this.getStrength() + "\tDexterity = " + this.getDexterity() +"\tArmour = " + this.getArmour()
        + "\tMoxie = " + this.getMoxie() + "|\n";
    }
  }
