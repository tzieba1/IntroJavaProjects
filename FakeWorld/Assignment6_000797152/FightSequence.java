package Assignment6_000797152;

import java.util.Scanner;

public class FightSequence
  {
  private Humanoid[] fighter_pool = new Humanoid[10];
  
  public FightSequence()
    {
    this.fighter_pool[0] = new Wizard("Gandalf");
    this.fighter_pool[1] = new Hobbit("Smeagol");
    this.fighter_pool[2] = new Elf("Legolas");
    this.fighter_pool[3] = new Fighter("Aragorn");
    this.fighter_pool[4] = new Human();
    this.fighter_pool[5] = new Hobbit("Hobby", 1, 1, 1, 1);
    this.fighter_pool[6] = new Elf("Dobby", 1, 1, 1, 1,"city", (Hobbit) this.fighter_pool[5]);
    this.fighter_pool[7] = new Elf();
    this.fighter_pool[8] = new Wizard("Powerful Caster", 20, 20, 20, 20, 20);
    this.fighter_pool[9] = new Humanoid("Guy");
    }
  
  public Humanoid getFighter(int fighter_pool_index )
    {
    if ( fighter_pool_index >= 0 || fighter_pool_index <= 9 )
      return fighter_pool[fighter_pool_index];
    else
      return new Humanoid();
    }
  
  public void printFighterStats()
    {
    for (int i = 0; i < 10; i++)
      System.out.println(fighter_pool[i]);
    }
  
  public void instigateFight(Humanoid target, Humanoid instigator)
    {
    Scanner sc = new Scanner(System.in);
    System.out.println( "\n****** " + target.getName() + " IS ATTACKED BY " + instigator.getName() + " ******\n" );

    Humanoid attacker = instigator;
    Humanoid defender = target;
    Humanoid temp_fighter;
    Boolean stop = false;
    while ( defender.getHealth() > 0 && stop == false )
      {
      defender.defend(attacker);
      attacker.attack(defender);
      
      temp_fighter = attacker;
      attacker = defender;
      defender = temp_fighter;
      
      System.out.println(instigator);
      System.out.println(target);
      
      System.out.println("Type STOP (case sensitive) for the fight to end. Otherwise, press enter.");
      if ( sc.nextLine().equals("STOP") )
        stop = true;
      }
    System.out.println("THE FIGHT IS OVER.");
    
    }
  public static void main(String[] args)
    {
    
    FightSequence new_fight_seq = new FightSequence();
    
    new_fight_seq.printFighterStats();
    
    new_fight_seq.instigateFight( new_fight_seq.getFighter(3), new_fight_seq.getFighter( 6 ) );
    
    }
  }
