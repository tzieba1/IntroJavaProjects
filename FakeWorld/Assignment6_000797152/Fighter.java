package Assignment6_000797152;

/**
 * This class is the Fighter subclass of the Human superclass. Each fighter has double the regular Humanoid damage
 * score when using the '.attack(Humanoid target)' method.
 */
public class Fighter extends Human
  {
  /**
   * The default constructor for a Fighter. Uses a default name passed into Human super-constructor.
   */
  public Fighter()
    {
    super( "Fighter" ); //Default Fighter name passed into Human super-constructor parametrized by a name.
    }

  /**
   * The constructor that creates an instance of Fighter by passing in only a name for the Fighter.
   * @param name The name given to this Fighter being created.
   */
  public Fighter( String name )
    {
    super( name );  //Fighter name passed into Human super-constructor parametrized by a name.
    }

  /**
   * The constructor that creates an instance of Fighter by passing in name and attribute ratings for the Fighter.
   * @param name The name of this Fighter being created.
   * @param strength The strength rating of the Fighter [0,20].
   * @param dexterity The dexterity rating of the Fighter [0,20].
   * @param armour The armour rating of the Fighter [0,20].
   * @param moxie The moxie rating of the Fighter [0,20].
   */
  public Fighter( String name, int strength, int dexterity, int armour, int moxie )
    {
    super( name, strength, dexterity, armour, moxie ); //Super-constructor initializes object with passed in name/attr.
    }

  /**
   * Overridden method from Humanoid superclass that will lower the health of a Fighter's target by double the damage
   * score for all other Humanoids. This is double the average of strength, dexterity and health.
   * @param target The target Humanoid that is being attacked.
   */
  @Override
  public void attack( Humanoid target )
    {
    //*** Fighter dealing double the average of strength, dexterity and health to the Humanoid target.
    target.lowerHealth( 2 * (this.getStrength() + this.getDexterity() + this.getHealth()) / 3 );
    }

  /**
   * Overridden method from the Human superclass that is modified to indicate a Fighter subclass.
   * @return Returns the String representation of this Fighter with the name, health, coins, strength, dexterity,
   *    * armour and moxie listed.
   */
  @Override
  public String toString()
    {
    return (this.getName().equals("Fighter") ? "Fighter:\n" : this.getName() + " the Fighter:\n")
        + "|Health = " + this.getHealth() + " \tCoins = " + this.getCoins() + "|\n"
        + "|Strength = " + this.getStrength() + "\tDexterity = " + this.getDexterity() +"\tArmour = " + this.getArmour()
        + "\tMoxie = " + this.getMoxie() + "|\n";
    }
  }
