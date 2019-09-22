package Assignment6_000797152;

/**
 * This class is the Hobbit subclass of the Humanoid superclass. Only hobbits can steal money from other Humanoids.
 *
 * @author Tommy Zieba
 */
public class Hobbit extends Humanoid
  {
  /**
   * The default constructor for a Hobbit. Uses a default name passed into Humanoid super-constructor.
   */
  public Hobbit()
    {
    super("Hobbit" ); //Default Hobbit name passed into Humanoid super-constructor parametrized by a name.
    }

  /**
   * The constructor that creates an instance of Hobbit by passing in only a name for the Hobbit.
   * @param name The name given to this Hobbit being created.
   */
  public Hobbit( String name )
    {
    super( name );  //Hobbit name passed into Humanoid super-constructor parametrized by a name.
    }

  /**
   * The constructor that creates an instance of Hobbit by passing in name and attribute ratings for the Hobbit.
   * @param name The name of this Hobbit being created.
   * @param strength The strength rating of the Hobbit [0,20].
   * @param dexterity The dexterity rating of the Hobbit [0,20].
   * @param armour The armour rating of the Hobbit [0,20].
   * @param moxie The moxie rating of the Hobbit [0,20].
   */
  public Hobbit( String name, int strength, int dexterity, int armour, int moxie )
    {
    super( name, strength, dexterity, armour, moxie ); //Super-constructor initializes object with passed in name/attr.
    }

  /**
   * Method that this Hobbit can use to steal money whenever this Hobbit is alive.
   * @return Returns success score of coins than can be stolen that is half of this Hobbits dexterity.
   */
  public double steal()
    {
    if ( this.isAlive() )
      return this.getDexterity() / 2; //Returns the success score for coins stolen.
    else
      return 0; //Returning a success score of zero when Hobbit is dead.
    }

  /**
   * Overridden method from the Humanoid superclass that is modified to indicate a Hobbit subclass.
   * @return Returns the String representation of this Hobbit with the name, health, coins, strength, dexterity,
   *    * armour and moxie listed.
   */
  @Override
  public String toString()
    {
    return (this.getName().equals("Hobbit") ? "Hobbit:\n" : this.getName() + " the Hobbit:\n")
        + "|Health = " + this.getHealth() + " \tCoins = " + this.getCoins() + "|\n"
        + "|Strength = " + this.getStrength() + "\tDexterity = " + this.getDexterity() +"\tArmour = " + this.getArmour()
        + "\tMoxie = " + this.getMoxie() + "|\n";
    }
  }
