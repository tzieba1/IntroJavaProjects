
import battleship.BattleShip;

/**
 * Starting code for COMP10205 - Assignment#6
 * @author mark.yendt
 */

// Leave this code alone except for collecting data at the end of each game.
public class A6
{
   static final int NUMBEROFGAMES = 10000;
   public static void startingSolution()
  {
    int totalShots = 0;
    int minShots = 100;
    int maxShots = 0;
    System.out.println(BattleShip.version());
    long start = System.nanoTime();
    for (int game = 0; game < NUMBEROFGAMES; game++) {

      BattleShip battleShip = new BattleShip();             // Create a same with new placement of ships.
      ZiebaBot ziebaBot = new ZiebaBot(battleShip);      // Create instance of solution to avoid reusing memory.

      long gameStart = System.nanoTime();
      while (!battleShip.allSunk()) {                       // DON'T CHANGE THIS!
        ziebaBot.fireShot();
      }
//      ziebaBot.printDist();

      int gameShots = battleShip.totalShotsTaken();
      totalShots += gameShots;
      minShots = Math.min(gameShots, minShots);
      maxShots = Math.max(gameShots, maxShots);

      //TODO: Make a histogram out of these game times and game shots.
//      System.out.printf("Game Time = %1.4f ms      Game Shots = %d\n", (double)(System.nanoTime() - gameStart)/1000000, gameShots );
    }
    System.out.printf("\nZiebaBot -> # Games = %d\n" +
                        "         -> Average # Shots = %.2f\n" +
                        "         -> Maximum # Shots = %d\n" +
                        "         -> Minimum # Shots = %d\n" +
                        "         -> Total Time = %1.4f ms\n",
     NUMBEROFGAMES, (double)totalShots / NUMBEROFGAMES, maxShots, minShots,(double)(System.nanoTime() - start)/1000000);
  }
  public static void main(String[] args)
  {
    startingSolution();
  }
}
