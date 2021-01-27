
import battleship.BattleShip;

import java.awt.Point;
import java.util.ArrayList;

/**
 * I, Tommy Zieba, 000797152 certify that this material is my original work.
 * No other person's work has been used without due acknowledgement.
 * <p>
 * Note: Many lines are commented out for debugging to watch games play out.
 * <p>
 * I had some trouble with the adding parity to my destroy() class that acts as a sinker. I ran out of time to debug it,
 * but it is not grabbing all necessary spaces surrounding a hit during sinking. I initially made the logic to be focus
 * on the probability side of things and found out how little influence the special cases have in over all move reduction.
 * I was getting very good scores on lucky games without specific scenarios ( during destroy() method ). I commented
 * out the parity check in order to ensure that games can finish with the sinker I have implemented so far. I wish I had
 * more time to work on this. Check line 223 if you want to add in the parity check and see how it performs on most
 * games. I will hopefully have a better and debugged destroy() method after the holidays. With parity, I think I have a
 * good chance of getting below 50 shots average.
 */
public class ZiebaBot {
  // Variables for set up of each game and to act on distinct ship sizes for the game being played.
  private BattleShip battleShip;  // Using the BattleShip API.
  private int gameSize;           // Square game is assumed.
  private int sunkenShips;        // Track number of sunken ships to determine if a ship has sunk after a hit.

  // A different enum class was used to construct a map with "Unknown" instead of "Empty" for personal preference.
  private MyCellState[][] map;

  private boolean hunting;              // Indicate if hunting for a hit or sinking a battleship following a hit.
  private int[][][] shipDistribution;   //Cumulative hypothetical coverage, cumulative suspicion, and state per ship.
  private ArrayList<Target> unknownTargets;    //Tracking unknown target Points while searching for optimal target.
  public int[] target;                  //Position of most optimal shot (mutated each turn).

  // Variables used in process of raising suspicions at cells surrounding a target shot at and next target optimization.
  private int xMin;                         // Minimum horizontal cell index of search area for optimal target.
  private int xUpperBound;                  // Upperbound for horizontal cell index of search area for optimal target.
  private int yMin;                         // Maximum vertical cell index of search area for optimal target.
  private int yUpperBound;                  // Upperbound for vertical cell index of search area for optimal target.
  private boolean replacedTarget;           // Bot must be aware of target replaced at each iteration of optimization.

  //Used for sinking ships in the destroy() method.
  private SortedLinkedList<Target> candidateDirectionalTargets;
  private int[] tempTarget;


  /**
   * Constructor keeps a copy of the BattleShip instance
   *
   * @param b previously created battleship instance - should be a new game
   */
  public ZiebaBot(BattleShip b) {
    battleShip = b;
    gameSize = b.boardSize;
    sunkenShips = 0;

    map = new MyCellState[gameSize][gameSize];
    for (int x = 0; x < gameSize; x++)
      for (int y = 0; y < gameSize; y++)
        map[y][x] = MyCellState.Unknown;

    hunting = true;
    shipDistribution = new int[gameSize][gameSize][9];
    unknownTargets = new ArrayList<>();
    target = new int[]{4, 4, 0, 0, 0}; //Last 3 are placement, suspect, and corner counts.

    xMin = 0;
    xUpperBound = gameSize;
    yMin = 0;
    yUpperBound = gameSize;
    replacedTarget = false;

    candidateDirectionalTargets = new SortedLinkedList<Target>();
    tempTarget = new int[5];

    //TODO: Initialize counts for all hypothetical ship placements.
    for (int shipSize = 2; shipSize < 6; shipSize++) {
      investigateNauticalSpace(shipSize);
    }

    //TODO: Initialize unknown cells with targets.
    for (int i = 0; i < gameSize; i++) {
      for (int j = 0; j < gameSize; j++) {
        int[] cell = shipDistribution[j][i];
        unknownTargets.add(new Target(i, j, cell[0] + cell[1] + cell[2] + cell[3],
            cell[4] + cell[5] + cell[6] + cell[7], 8, -1));
      }
    }
  }


  /**
   * Initialize hypothetical ship locations for this game.
   *
   * @param shipSize
   */
  private void investigateNauticalSpace(int shipSize) {
    //TODO: Iterate over cells, increment count at appropriate index if piece fits vertically or horizontally.
    for (int i = 0; i < gameSize - (shipSize - 1); i++) {
      for (int j = 0; j < gameSize - (shipSize - 1); j++) {
        //TODO: Initialize unknown targets
        //unknownTargets.add(new Point(i,j));

        //TODO: Simultaneously scan horizontal/vertical placements until reaching last placement for each direction.
        if (j < gameSize - (shipSize - 1)) {
          //TODO: Iterate over horizontal/vertical indices for corresponding horizontal size and placement.
          for (int k = 0; k < shipSize; k++) {
            //TODO: Increment count for horizontal and vertical directions in same loop (since square).
            shipDistribution[j + k][i][shipSize - 2] = shipDistribution[j + k][i][shipSize - 2] + 1;
            shipDistribution[j][i + k][shipSize - 2] = shipDistribution[j][i + k][shipSize - 2] + 1;
          }
        }

        //TODO: Handle the case where the last horizontal/vertical placement is reached and add counts there.
        if (j == gameSize - shipSize) {
          for (int k = j + 1; k < gameSize; k++) {
            //TODO: Iterate over indices intersecting any remaining horizontal placements (last column/row).
            for (int l = 0; l < shipSize; l++) {
              shipDistribution[k][i + l][shipSize - 2] = shipDistribution[k][i + l][shipSize - 2] + 1;
              shipDistribution[i + l][k][shipSize - 2] = shipDistribution[i + l][k][shipSize - 2] + 1;
            }
          }
        }
      }
    }
  }


  /**
   * Observe changes in shipDistribution after firing a shot.
   */
  public void deployReconnaissance(int x, int y) {
    //TODO: Reduce suspicions of each ship for the next shot (decrement hypothetical placement counts).
    for (int shipSize = 2; shipSize < 6; shipSize++) {
      reduceSuspicions(x, y, shipSize);
    }

    //TODO: Raise suspicions of each ship for the next shot (parity checks with incrementing suspect counts).
    //Note: Simultaneously increments 1 component in the distribution independent of ship size (for corners).
    raiseSuspicions(x, y);
//    printDist();
  }


  /**
   *
   */
  private void search() {
//    printMap();
//    System.out.println("-------------------------------------- SEARCH TARGET AREA --------------");
    //TODO: First check for new optimal target in 5x5 area surrounding it after first pass since reducing those cells.
    //  Must check for edge conditions and adjust algorithm respectively (at least 3rd cell inward from any edge).
    xMin = target[0] < 2 ? 0 : target[0] - 2;                           // Check optimization area's left edge.
    xUpperBound = target[0] > gameSize - 3 ? gameSize : target[0] + 3;  // Check optimization area's right edge.
    yMin = target[1] < 2 ? 0 : target[1] + 2;                           // Check optimization area's upper edge.
    yUpperBound = target[1] < gameSize - 3 ? gameSize : target[1] - 3;  // Check optimization area's bottom edge.


    //TODO: Only check just over half of the board at start
    if (battleShip.totalShotsTaken() == 1) {
      xUpperBound = gameSize / 2 + 3;
      yUpperBound = gameSize / 2 + 3;
    }


    //TODO: Search for targets in a specified area.
    search(0, gameSize, 0, gameSize);

//      if( map[target[1]][target[0]] == MyCellState.Hit)
//        search(xMin, xUpperBound, yMin, yUpperBound);
//      else
//        search(0, gameSize, 0, gameSize);


    //TODO: Iteratively search for optimal target if not replaced in the 5x5 area surrounding the target.
//    while (!replacedTarget) {
//      //TODO: Check if all cells were searched and pick a random target, else perform Search on all cells.
//      if (xMin == 0 && xUpperBound == gameSize) {
////        System.out.println("-------------------------------------- RANDOM TARGET -------------");
//        //TODO: Find a new random target that is unknown and fire a shot at it.
//        //NOTE: This is the condition that handles stale games
//        if(unknownTargets.size() > 0) {
//          Target unknownTarget = unknownTargets.get((int) (Math.random() * unknownTargets.size()));
//          target[0] = unknownTarget.x;
//          target[1] = unknownTarget.y;
//          target[2] = unknownTarget.placement;
//          target[3] = unknownTarget.suspicion;
//          target[4] = unknownTarget.corners;
//          unknownTargets.remove(new Target(target[0], target[1], target[2], target[3], target[4], -1));
//          replacedTarget = true;
//        }
//
//      } else {
////        System.out.println("not found");
////        System.out.println("-------------------------------------- SEARCH ALL -------------");
//        search(0, gameSize, 0, gameSize);
//      }
//    }
  }


  /**
   * @param xM
   * @param xUB
   * @param yM
   * @param yUB
   */
  private void search(int xM, int xUB, int yM, int yUB) {
    //TODO: Set a temporary target to compare each cell to.
    int[] temp = new int[]{0, 0, 0, 0, 0};

    //TODO: Reset global search area boundary.
    xMin = xM;
    xUpperBound = xUB;
    yMin = yM;
    yUpperBound = yUB;

    //TODO: Reset a flag for finding a more optimal cell to replace the target
    replacedTarget = false;

    //TODO: Iterate through all cells and check for the next most likely hit based on placement and suspect sums.
    for (int i = xMin; i < xUpperBound; i++) {
      for (int j = yMin; j < yUpperBound; j++) {
        //TODO: Only check unknown cells for max since all uncovered cells have placement counts [2{0},3{0},4{0},5{0}].
        if (map[j][i] == MyCellState.Unknown /*&& ((i + j) % 2 == 0)*/) {
          int placementSum = 0;
          int suspectSum = 0;
          for (int k = 0; k < 4; k++) {
            placementSum += shipDistribution[j][i][k];
            suspectSum += shipDistribution[j][i][k + 4];
          }
          //TODO: Reset flag to determine if replacedTarget flag should be raised.
          boolean found = false;

          //NOTE: These conditions are like the 'probability dials'. It appears after testing the hunter without the
          //      sinker, changing the inequalities here produces interesting results. Making these all abide by
          //      non-strict inequalities will allow the game to be played with a higher degree of randomness and parity
          //      checks - but will fire many more shots before becoming stale and plays through almost every time. The
          //      stale game can be eliminated completely by taking any shot when all other shots are equally likely. On
          //      the other hand, changing these inequalities to be strict (or a mixture of both) produces far better
          //      results if a game does not go stale. But, in this case chances of going stale is much more likely to
          //      occur and happens sooner (after fewer shots).
          if (placementSum + suspectSum + shipDistribution[j][i][8] > temp[2] + temp[3] + temp[4]) {
            //TODO: Update temp target if a greater magnitude of suspicion and possible placements exists at unknown cell.
            temp[0] = i;
            temp[1] = j;
            temp[2] = placementSum;
            temp[3] = suspectSum;
            temp[4] = shipDistribution[j][1][8];
            found = true;
//            System.out.printf("Found Target: (%d, %d) -> [%d, %d, %d]\n\n", temp[1], temp[0], temp[2], temp[3], temp[4]);
          } else if (placementSum > 0 && suspectSum > temp[3] && shipDistribution[j][i][8] > temp[4]) {
            //TODO: Update temp target if a greater magnitude of suspicion and possible placements exists at unknown cell.
            temp[0] = i;
            temp[1] = j;
            temp[2] = placementSum;
            temp[3] = suspectSum;
            temp[4] = shipDistribution[j][i][8];
            found = true;
//            System.out.printf("Found Target: (%d, %d) -> [%d, %d, %d]\n\n", temp[1], temp[0], temp[2], temp[3], temp[4]);
          }
          //NOTE: Whenever this inequality is strict, there is no chance of stalemate, but games persist for many shots.
          //      In other words, at some point there is no obvious best choice when all shots are predicted equal.
          else if (placementSum >= temp[2]) {
            //TODO: Update temp target if a greater magnitude of suspicion and possible placements exists at unknown cell.
            temp[0] = i;
            temp[1] = j;
            temp[2] = placementSum;
            temp[3] = suspectSum;
            temp[4] = shipDistribution[j][i][8];
            found = true;
//            System.out.printf("Found Target: (%d, %d) -> [%d, %d, %d]\n\n", temp[1], temp[0], temp[2], temp[3], temp[4]);
          }


          //TODO: Set the target to a better location in case one is found at each iteration.
          if (found) {
            target[0] = temp[0];
            target[1] = temp[1];
            target[2] = temp[2];
            target[3] = temp[3];
            target[4] = temp[4];
            replacedTarget = true;
            unknownTargets.remove(new Target(target[0], target[1], target[2], target[3], target[4], -1));
//            System.out.printf("Replaced Target: (%d, %d) -> [%d, %d, %d]\n\n", target[1], target[0], target[2], target[3], target[4]);
          }
        }
      }
    }
  }


  /**
   *
   */
  public void fireShot() {
//    System.out.printf("FIRED AT  (%d, %d) -> [%d, %d, %d]\n\n", target[1], target[0], target[2], target[3], target[4]);
    //TODO: Skip optimization for first shot since it is always at the center.
    if (battleShip.totalShotsTaken() == 0) {
      //TODO: Shoot and update the map's cell state at target accordingly.
      if (battleShip.shoot(new Point(target[0], target[1]))) {
        map[target[1]][target[0]] = MyCellState.Hit;
        hunting = false;
        for (int i = 0; i < tempTarget.length; i++) tempTarget[i] = target[i];
        destroy();
        if (battleShip.allSunk()) {
          return;
        }
      } else {
        map[target[1]][target[0]] = MyCellState.Miss;
      }
      deployReconnaissance(target[0], target[1]);
    } else {
      //TODO: Fire at target and update map cell state at the target cell accordingly.
      if (battleShip.shoot(new Point(target[0], target[1]))) {
        map[target[1]][target[0]] = MyCellState.Hit;
        //TODO: Continue destroying by shooting at candidate targets.
        //      Destroy method should not recursively call fireShot(), but use private fireShot(x,y), then deploy recon.
        hunting = false;
        for (int i = 0; i < tempTarget.length; i++) tempTarget[i] = target[i];
//        System.out.println("-----------------------------------------------------------------------------------------------------------");
//        printMap();
//        System.out.println("DESTROY");
        destroy();
        if (battleShip.allSunk()) {
          return;
        }
      } else {
        map[target[1]][target[0]] = MyCellState.Miss;
//        System.out.println("REGULAR RECONNAISSANCE");
        deployReconnaissance(target[0], target[1]);
      }
    }
    search();
//    printMap();
  }


  /**
   * Method for firing destroyer shots while taking down a ship.
   *
   * @param x
   * @param y
   */
  private void fireShot(int x, int y) {
    int[] temp = new int[5];
    temp = cellToTarget(x, y);
//    System.out.printf("FIRING AT  (%d, %d) -> [%d, %d, %d]\n\n", y, x, temp[2],temp[3],temp[4]);

    //TODO: Shoot and update the map's cell state at target accordingly.
    if (battleShip.shoot(new Point(x, y))) {
      map[y][x] = MyCellState.Hit;
    } else {
      map[y][x] = MyCellState.Miss;
    }
    if (!battleShip.allSunk()) {
//    System.out.println("DESTROYER RECONNAISSANCE");
      deployReconnaissance(x, y);
    } else {
      return;
    }
  }

  /**
   *
   */
  private void destroy() {
    //TODO: Assign a placeholder to indicate that a target cell is on an edge in one of 4 directions.
    int[] noDirection = new int[]{-1, -1, -1, -1, -1};
    //TODO: Assign each adjacent cell to memory and check edge conditions for initial directions to advance to.
    int[] r = tempTarget[0] + 1 < gameSize ? cellToTarget(tempTarget[0] + 1, tempTarget[1]) : noDirection;
    int[] u = tempTarget[1] > 0 ? cellToTarget(tempTarget[0], tempTarget[1] - 1) : noDirection;
    int[] l = tempTarget[0] > 0 ? cellToTarget(tempTarget[0] - 1, tempTarget[1]) : noDirection;
    int[] d = tempTarget[1] + 1 < gameSize ? cellToTarget(tempTarget[0], tempTarget[1] + 1) : noDirection;

    //TODO: Add initial targets to candidateDirectionsList.
    if (r != noDirection) {
      candidateDirectionalTargets.add(new Target(r[0], r[1], r[2], r[3], r[4], 0));
    }
    if (u != noDirection) {
      candidateDirectionalTargets.add(new Target(u[0], u[1], u[2], u[3], u[4], 1));
    }
    if (l != noDirection) {
      candidateDirectionalTargets.add(new Target(l[0], l[1], l[2], l[3], l[4], 2));
    }
    if (d != noDirection) {
      candidateDirectionalTargets.add(new Target(d[0], d[1], d[2], d[3], d[4], 3));
    }

    //TODO: Loop and take shots seeking to destroy a ship, where only shots in adjacent directions of Hits are taken.
    int rAdvances = 0, uAdvances = 0, lAdvances = 0, dAdvances = 0;   //Track any advances in each direction.
    while (!hunting) {
//      System.out.println("DESTROY LOOP START");
      //TODO: First find the next candidate direction of advance and switch logic based on direction.
      Target candidate = (Target) candidateDirectionalTargets.first.value;
      candidateDirectionalTargets.remove(candidate);

//      System.out.printf("Candidate = [%d, %d, %d, %d, %d, %d]\n", candidate.x, candidate.y, candidate.placement, candidate.suspicion, candidate.corners, candidate.direction);

      //TODO: Check valid cells if Hit or Unknown and add them to the SortedLinkedList of candidate target cells or not.
      switch (candidate.direction) {
        case 0:   //-----------------------------------------------------------------------------------------Right----//
          //TODO: Check current candidate state and switch logic to advance in rightward direction.
          //      Check if cell advanced to is a Hit or Unknown. Handle each case separately. Add no more candidates in
          //      the rightward direction when a Miss is encountered.
          rAdvances++;      //Advance 1 cell, i.e. consider a candidate Target.

          //TODO: End advance in rightward direction by not assigning any additional candidateDirectionTargets.
          if (map[candidate.y][candidate.x] == MyCellState.Unknown) {
            //TODO: Fire a shot at the candidate and deploy recon (recon deployed during end of firing shot).
            fireShot(candidate.x, candidate.y);
            unknownTargets.remove(candidate);
//            printMap();

            //TODO: Check for sunken ship and end the destruction if one has been sunk. Otherwise, seek to advance.
            if (sunken()) {
              hunting = true;
              candidateDirectionalTargets = new SortedLinkedList<>();
              return;
            } else {
              //TODO: Seek to advance by checking for an edge from the target based on number of rightward advances.
              if (candidate.x + 1 < gameSize) {
                //TODO: Add to rightward directional candidate targets.
                int[] t = cellToTarget(candidate.x + 1, candidate.y);

                candidateDirectionalTargets.add(new Target(t[0], t[1], t[2], t[3], t[4], 0));
              }
            }
          }
          break;
        case 1:   //--------------------------------------------------------------------------------------------Up----//
          //TODO: Check current candidate state and switch logic to advance in upward direction.
          //      Check if cell advanced to is a Hit or Unknown. Handle each case separately. Add no more candidates in
          //      the upward direction when a Miss is encountered.
          uAdvances++;      //Advance 1 cell, i.e. consider a candidate Target.

          //TODO: End advance in upward direction by not assigning any additional candidateDirectionTargets.
          if (map[candidate.y][candidate.x] == MyCellState.Unknown) {
            //TODO: Fire a shot at the candidate and deploy recon (recon deployed during end of firing shot).
            fireShot(candidate.x, candidate.y);
            unknownTargets.remove(candidate);
//            printMap();

            //TODO: Check for sunken ship and end the destruction if one has been sunk. Otherwise, seek to advance.
            if (sunken()) {
              hunting = true;
              candidateDirectionalTargets = new SortedLinkedList<>();
              return;
            } else {
              //TODO: Seek to advance by checking for an edge from the target based on number of upward advances.
              if (candidate.y > 0) {
                //TODO: Add to upward directional candidate targets.
                int[] t = cellToTarget(candidate.x, candidate.y - 1);

                candidateDirectionalTargets.add(new Target(t[0], t[1], t[2], t[3], t[4], 1));
              }
            }
          }
          break;
        case 2:   //------------------------------------------------------------------------------------------Left----//
          //TODO: Check current candidate state and switch logic to advance in leftward direction.
          //      Check if cell advanced to is a Hit or Unknown. Handle each case separately. Add no more candidates in
          //      the leftward direction when a Miss is encountered.
          lAdvances++;      //Advance 1 cell, i.e. consider a candidate Target.

          //TODO: End advance in leftward direction by not assigning any additional candidateDirectionTargets.
          if (map[candidate.y][candidate.x] == MyCellState.Unknown) {
            //TODO: Fire a shot at the candidate and deploy recon (recon deployed during end of firing shot).
            fireShot(candidate.x, candidate.y);
            unknownTargets.remove(candidate);
//            printMap();

            //TODO: Check for sunken ship and end the destruction if one has been sunk. Otherwise, seek to advance.
            if (sunken()) {
              hunting = true;
              candidateDirectionalTargets = new SortedLinkedList<>();
              return;
            } else {
              //TODO: Seek to advance by checking for an edge from the target based on number of leftward advances.
              if (candidate.x > 0) {
                //TODO: Add to leftward directional candidate targets.
                int[] t = cellToTarget(candidate.x - 1, candidate.y);
                candidateDirectionalTargets.add(new Target(t[0], t[1], t[2], t[3], t[4], 2));
              }
            }
          }
          break;
        case 3:   //------------------------------------------------------------------------------------------Down----//
          //TODO: Check current candidate state and switch logic to advance in downward direction.
          //      Check if cell advanced to is a Hit or Unknown. Handle each case separately. Add no more candidates in
          //      the downward direction when a Miss is encountered.
          dAdvances++;      //Advance 1 cell, i.e. consider a candidate Target.
          //TODO: End advance in downward direction by not assigning any additional candidateDirectionTargets.
          if (map[candidate.y][candidate.x] == MyCellState.Unknown) {
            //TODO: Fire a shot at the candidate and deploy recon (recon deployed during end of firing shot).
            fireShot(candidate.x, candidate.y);
            unknownTargets.remove(candidate);
//            printMap();

            //TODO: Check for sunken ship and end the destruction if one has been sunk. Otherwise, seek to advance.
            if (sunken()) {
              hunting = true;
              candidateDirectionalTargets = new SortedLinkedList<>();
              return;
            } else {
              //TODO: Seek to advance by checking for an edge from the target based on number of downward advances.
              if (candidate.y + 1 < gameSize) {
                //TODO: Add to downward directional candidate targets.
                int[] t = cellToTarget(candidate.x, candidate.y + 1);
                candidateDirectionalTargets.add(new Target(t[0], t[1], t[2], t[3], t[4], 3));
              }
            }
          }
          break;
        default:
          break;
      }

      //TODO: Final check for no more candidates to go back to hunting.
      if (candidateDirectionalTargets.isEmpty()) {
        hunting = true;
      }
    }
  }


  /**
   * Method used to reduce counts in the distribution of cumulative hypothetical shots after firing a shot.
   * <p>
   * Note: Running this method without a sinking mode inherently produces something similar to a parity check, but with
   * additional shots taken in top-right and bottom left corners (could add bias to other corners). Moreover, adding
   * additional code for parity check is no longer necessary during hunting mode.
   * <p>
   * NEED TO EDIT THIS SO THAT x AND y ARE IN THE CORRECT PLACE AND FLIP i, j IN fireShot().
   *
   * @param x
   * @param y
   * @param s
   */
  private void reduceSuspicions(int x, int y, int s) {
    //TODO: Determine if top-down or bottom-up sliding scan needed for vertical count adjustment.
    if (y < s - 1) {
      //TODO: Top-down scan from top edge and moving until reaching lowest vertical placement at y.
      for (int k = 0; k <= y && k + s - 1 < gameSize; k++) {
        //TODO: Top-down scan for each hypothetical vertical placement to decrement counts and update hits.
        for (int c = 0; c < s; c++) {
          shipDistribution[k + c][x][s - 2] = shipDistribution[k + c][x][s - 2] > 0 ? shipDistribution[k + c][x][s - 2] - 1 : 0;
        }
      }
    } else if (y > gameSize - s) {
      //TODO: Bottom-up scan from bottom edge and moving until reaching highest vertical placement at y.
      for (int k = gameSize - 1; k >= y && k - s > -2; k--) {
        //TODO: Bottom-up scan for each hypothetical vertical placement to decrement counts.
        for (int c = 0; c < s; c++) {
          shipDistribution[k - c][x][s - 2] = shipDistribution[k - c][x][s - 2] > 0 ? shipDistribution[k - c][x][s - 2] - 1 : 0;
        }
      }
    } else {
      //TODO: Default scan is top-down for all other cases when all possible placements contained on grid.
      for (int k = y - s + 1; k <= y; k++) {
        //TODO: Top-down scan for each hypothetical vertical placement to decrement counts.
        for (int c = 0; c < s; c++) {
          shipDistribution[k + c][x][s - 2] = shipDistribution[k + c][x][s - 2] > 0 ? shipDistribution[k + c][x][s - 2] - 1 : 0;
        }
      }
    }

    //TODO: Determine if left-right or right-left sliding scan needed for horizontal count adjustment.
    if (x < s - 1) {
      //TODO: Left-right scan from left edge and moving until reaching right-most horizontal placement at x.
      for (int k = 0; k <= x && k + s - 1 < gameSize; k++) {
        //TODO: Left-right scan for each hypothetical horizontal placement to decrement counts.
        for (int c = 0; c < s; c++) {
          shipDistribution[y][k + c][s - 2] = shipDistribution[y][k + c][s - 2] > 0 ? shipDistribution[y][k + c][s - 2] - 1 : 0;
        }
      }
    } else if (x > gameSize - s) {
      //TODO: Right-left scan from right edge and moving until reaching highest horizontal placement at x.
      for (int k = gameSize - 1; k >= x && k - s > -2; k--) {
        //TODO: Right-left scan for each hypothetical horizontal placement to decrement counts.
        for (int c = 0; c < s; c++) {
          shipDistribution[y][k - c][s - 2] = shipDistribution[y][k - c][s - 2] > 0 ? shipDistribution[y][k - c][s - 2] - 1 : 0;
        }
      }
    } else {
      //TODO: Default scan is left-right for all other cases when all possible placements contained on grid.
      for (int k = x - s + 1; k <= x; k++) {
        //TODO: Left-right scan for each hypothetical horizontal placement to decrement counts.
        for (int c = 0; c < s; c++) {
          shipDistribution[y][k + c][s - 2] = shipDistribution[y][k + c][s - 2] > 0 ? shipDistribution[y][k + c][s - 2] - 1 : 0;
        }
      }
    }
  }


  /**
   * Check if the shot taken at x,y is a hit/miss on the map and increment suspicion for surrounding cells.
   *
   * @param x
   * @param y
   */
  public void raiseSuspicions(int x, int y) {
    //TODO: Initialize flags for first, second, and third adjacent cells - indicating a hit (only required for corners).
    boolean c1 = false, c2 = false, c3 = false;

    //TODO: Incremented weight at cells adjacent to target which are unknown and fit each ship size.
    //Loop for checking/switching directions surrounding target that are adjacent to it by an edge.
    for (int direction = 0; direction < 4; direction++) {
      switch (direction) {
        //--------------------------------------------------------------------------------------------------Right--//
        case 0:
          //TODO: Check rightward edge is 1 cell away.
          if (x + 1 < gameSize) {
            //TODO: Check target Hit.
            if (map[y][x] == MyCellState.Hit) {
              //TODO: Check for hit to right of target (used for adding weight to cells inside of corners).
              if (map[y][x + 1] == MyCellState.Hit) {
                c1 = true;
              }
              //TODO: Check for [Hit, Unknown].
              else if (map[y][x + 1] == MyCellState.Unknown) {
                shipDistribution[y][x + 1][4] += 1;   // 2-ship fits here
              }
            }
          }

          //TODO: Check rightward edge is 2 cells away.
          if (x + 2 < gameSize) {
            //TODO: Check target Hit.
            if (map[y][x] == MyCellState.Hit) {
              //TODO: Check for [Hit, Unknown, Hit].
              if (map[y][x + 1] == MyCellState.Unknown && map[y][x + 2] == MyCellState.Hit) {
                shipDistribution[y][x + 1][4] += 2;   // 2-ship fits here twice
                shipDistribution[y][x + 1][5] += 1;   // 3-ship fits here
              }
            }
            //TODO: When target results in Miss, check for [Miss, Hit, Unknown, Hit].
            else if (map[y][x + 1] == MyCellState.Hit && map[y][x + 2] == MyCellState.Unknown) {
              shipDistribution[y][x + 2][4] += 1;   // 2-ship fits here
            }
          }

          //TODO: Check rightward edge is 3 cells away.
          if (x + 3 < gameSize) {
            //TODO: Check target Hit.
            if (map[y][x] == MyCellState.Hit) {
              //TODO: Check for [Hit, Unknown, Hit, Unknown].
              if (map[y][x + 1] == MyCellState.Unknown && map[y][x + 2] == MyCellState.Hit && map[y][x + 3] == MyCellState.Unknown) {
                shipDistribution[y][x + 1][6] += 1;   // 4-ship fits here
                shipDistribution[y][x + 3][6] += 1;   // 4-ship fits here
                shipDistribution[y][x + 3][4] += 1;   // 2-ship fits here
              }
            }
            //TODO: When target results in Miss, check for [Miss, Hit, Unknown, Hit, Unknown].
            else if (map[y][x + 1] == MyCellState.Hit && map[y][x + 2] == MyCellState.Unknown && map[y][x + 3] == MyCellState.Hit) {
              shipDistribution[y][x + 2][5] += 1;   // 3-ship fits here
              shipDistribution[y][x + 2][4] += 1;   // 2-ship fits here
            }
          }

          //TODO: Check rightward edge is 4 cells away.
          if (x + 4 < gameSize) {
            //TODO: Check target Hit.
            if (map[y][x] == MyCellState.Hit) {
              //TODO: Check for [Hit, Unknown, Hit, Unknown, Hit].
              if (map[y][x + 1] == MyCellState.Unknown && map[y][x + 2] == MyCellState.Hit && map[y][x + 3] == MyCellState.Unknown && map[y][x + 4] == MyCellState.Hit) {
                shipDistribution[y][x + 1][7] += 1;   // 5-ship fits here
                shipDistribution[y][x + 3][7] += 1;   // 5-ship fits here
                shipDistribution[y][x + 3][5] += 1;   // 3-ship fits here
                shipDistribution[y][x + 3][4] += 1;   // 2-ship fits here
              }
            }
            //TODO: When target results in Miss, check for [Miss, Hit, Unknown, Hit, Unknown, Hit].
            else if (map[y][x + 1] == MyCellState.Hit && map[y][x + 2] == MyCellState.Unknown && map[y][x + 3] == MyCellState.Hit && map[y][x + 4] == MyCellState.Unknown) {
              shipDistribution[y][x + 2][6] += 1;   // 4-ship fits here
              shipDistribution[y][x + 4][6] += 1;   // 4-ship fits here
              shipDistribution[y][x + 4][4] += 1;   // 2-ship fits here
            }
          }

          //TODO: Check rightward edge is 5 cells away and target Miss.
          if (x + 5 < gameSize && map[y][x] == MyCellState.Miss) {
            //TODO: Check for [Miss, Hit, Unknown, Hit, Unknown, Hit, Hit].
            if (map[y][x + 1] == MyCellState.Hit && map[y][x + 2] == MyCellState.Unknown && map[y][x + 3] == MyCellState.Hit && map[y][x + 4] == MyCellState.Unknown && map[x + 5][y] == MyCellState.Hit) {
              shipDistribution[y][x + 2][7] += 1;   // 5-ship fits here
              shipDistribution[y][x + 4][7] += 1;   // 5-ship fits here
              shipDistribution[y][x + 4][5] += 1;   // 3-ship fits here
              shipDistribution[y][x + 4][4] += 1;   // 2-ship fits here
            }
          }
          break;
        case 1: //-----------------------------------------------------------------------------------------------Up---//
          //TODO: Check upward edge is 1 cell away.
          if (y > 0) {
            //TODO: Check target Hit.
            if (map[y][x] == MyCellState.Hit) {
              //TODO: Check for [Hit, Unknown].
              if (map[y - 1][x] == MyCellState.Unknown) {
                shipDistribution[y - 1][x][4] += 2;   // 2-ship fits here
              }
              //TODO: Check for corner at upward and rightward adjacent cells following target Hit.
              if (map[y - 1][x] == MyCellState.Hit) {
                c2 = true;
                //TODO: Verify corner formed with rightward cell and that inside corner Unknown.
                if (c1 && map[y - 1][x + 1] == MyCellState.Unknown) {
//                  System.out.println("\nTOP RIGHT CORNER\n");

                  shipDistribution[y - 1][x + 1][8] += 1;
                }
              }
            }
          }

          //TODO: Check upward edge is 2 cells away.
          if (y > 1) {
            //TODO: Check target Hit.
            if (map[y][x] == MyCellState.Hit) {
              //TODO: Check for [Hit, Unknown, Hit].
              if (map[y - 1][x] == MyCellState.Unknown && map[y - 2][x] == MyCellState.Hit) {
                shipDistribution[y - 1][x][4] += 2;   // 2-ship fits here twice
                shipDistribution[y - 1][x][5] += 1;   // 3-ship fits here
              }
            }
            //TODO: When target results in Miss, check for [Miss, Hit, Unknown, Hit].
            else if (map[y - 1][x] == MyCellState.Hit && map[y - 2][x] == MyCellState.Unknown) {
              shipDistribution[y - 2][x][4] += 1;   // 2-ship fits here
            }
          }

          ///TODO: Check upward edge is 3 cells away.
          if (y > 2) {
            //TODO: Check target Hit.
            if (map[y][x] == MyCellState.Hit) {
              //TODO: Check for [Hit, Unknown, Hit, Unknown].
              if (map[y - 1][x] == MyCellState.Unknown && map[y - 2][x] == MyCellState.Hit && map[y - 3][x] == MyCellState.Unknown) {
                shipDistribution[y - 1][x][6] += 1;   // 4-ship fits here
                shipDistribution[y - 3][x][6] += 1;   // 4-ship fits here
                shipDistribution[y - 3][x][4] += 1;   // 2-ship fits here
              }
            }
            //TODO: When target results in Miss, check for [Miss, Hit, Unknown, Hit, Unknown].
            else if (map[y - 1][x] == MyCellState.Hit && map[y - 2][x] == MyCellState.Unknown && map[y - 3][x] == MyCellState.Hit) {
              shipDistribution[y - 2][x][5] += 1;   // 3-ship fits here
              shipDistribution[y - 2][x][4] += 1;   // 2-ship fits here
            }
          }

          ///TODO: Check upward edge is 4 cells away.
          if (y > 3) {
            //TODO: Check target Hit.
            if (map[y][x] == MyCellState.Hit) {
              //TODO: Check for [Hit, Unknown, Hit, Unknown, Hit].
              if (map[y - 1][x] == MyCellState.Unknown && map[y - 2][x] == MyCellState.Hit && map[y - 3][x] == MyCellState.Unknown && map[y - 4][x] == MyCellState.Hit) {
                shipDistribution[y - 1][x][7] += 1;   // 5-ship fits here
                shipDistribution[y - 3][x][7] += 1;   // 5-ship fits here
                shipDistribution[y - 3][x][5] += 1;   // 3-ship fits here
                shipDistribution[y - 3][x][4] += 1;   // 2-ship fits here
              }
            }
            //TODO: When target results in Miss, check for [Miss, Hit, Unknown, Hit, Unknown, Hit].
            else if (map[y - 1][x] == MyCellState.Hit && map[y - 2][x] == MyCellState.Unknown && map[y - 3][x] == MyCellState.Hit && map[y - 4][x] == MyCellState.Unknown) {
              shipDistribution[y - 2][x][6] += 1;   // 4-ship fits here
              shipDistribution[y - 4][x][6] += 1;   // 4-ship fits here
              shipDistribution[y - 4][x][4] += 1;   // 2-ship fits here
            }
          }

          ///TODO: Check upward edge is 5 cells away and target Miss.
          if (y > 4 && map[y][x] == MyCellState.Miss) {
            //TODO: Check for [Miss, Hit, Unknown, Hit, Unknown, Hit, Hit].
            if (map[y - 1][x] == MyCellState.Hit && map[y - 2][x] == MyCellState.Unknown && map[y - 3][x] == MyCellState.Hit && map[y - 4][x] == MyCellState.Unknown && map[x][y - 5] == MyCellState.Hit) {
              shipDistribution[y - 2][x][7] += 1;   // 5-ship fits here
              shipDistribution[y - 4][x][7] += 1;   // 5-ship fits here
              shipDistribution[y - 4][x][5] += 1;   // 3-ship fits here
              shipDistribution[y - 4][x][4] += 1;   // 2-ship fits here
            }
          }
          break;
        case 2: //----------------------------------------------------------------------------------------------Left--//
          //TODO: Check leftward edge is 1 cell away.
          if (x > 0) {
            //TODO: Check target Hit.
            if (map[y][x] == MyCellState.Hit) {
              if (map[y][x - 1] == MyCellState.Unknown) {
                shipDistribution[y][x - 1][4] += 2;   // 2-ship fits here
              }
              //TODO: Check for corner at leftward and upward adjacent cells following target Hit.
              if (map[y][x - 1] == MyCellState.Hit) {
                c3 = true;
                //TODO: Verify corner formed with upward cell and that inside corner Unknown.
                if (c2 && map[y - 1][x - 1] == MyCellState.Unknown) {
                  shipDistribution[y - 1][x - 1][8] += 1;
//                  System.out.println("\nTOP LEFT CORNER\n");
                }
              }
            }
          }

          //TODO: Check leftward edge is 2 cells away.
          if (x > 1) {
            //TODO: Check target Hit.
            if (map[y][x] == MyCellState.Hit) {
              //TODO: Check for [Hit, Unknown, Hit].
              if (map[y][x - 1] == MyCellState.Unknown && map[y][x - 2] == MyCellState.Hit) {
                shipDistribution[y][x - 1][4] += 2;   // 2-ship fits here twice
                shipDistribution[y][x - 1][5] += 1;   // 3-ship fits here
              }
            }
            //TODO: When target results in Miss, check for [Miss, Hit, Unknown, Hit].
            else if (map[y][x - 1] == MyCellState.Hit && map[y][x - 2] == MyCellState.Unknown) {
              shipDistribution[y][x - 2][4] += 1;   // 2-ship fits here
            }
          }

          //TODO: Check leftward edge is 3 cells away.
          if (x > 2) {
            //TODO: Check target Hit.
            if (map[y][x] == MyCellState.Hit) {
              //TODO: Check for [Hit, Unknown, Hit, Unknown].
              if (map[y][x - 1] == MyCellState.Unknown && map[y][x - 2] == MyCellState.Hit && map[y][x - 3] == MyCellState.Unknown) {
                shipDistribution[y][x - 1][6] += 1;   // 4-ship fits here
                shipDistribution[y][x - 3][6] += 1;   // 4-ship fits here
                shipDistribution[y][x - 3][4] += 1;   // 2-ship fits here
              }
            }
            //TODO: When target results in Miss, check for [Miss, Hit, Unknown, Hit, Unknown].
            else if (map[y][x - 1] == MyCellState.Hit && map[y][x - 2] == MyCellState.Unknown && map[y][x - 3] == MyCellState.Hit) {
              shipDistribution[y][x - 2][5] += 1;   // 3-ship fits here
              shipDistribution[y][x - 2][4] += 1;   // 2-ship fits here
            }
          }

          //TODO: Check leftward edge is 4 cells away.
          if (x > 3) {
            //TODO: Check target Hit.
            if (map[y][x] == MyCellState.Hit) {
              //TODO: Check for [Hit, Unknown, Hit, Unknown, Hit].
              if (map[y][x - 1] == MyCellState.Unknown && map[y][x - 2] == MyCellState.Hit && map[y][x - 3] == MyCellState.Unknown && map[y][x - 4] == MyCellState.Hit) {
                shipDistribution[y][x - 1][7] += 1;   // 5-ship fits here
                shipDistribution[y][x - 3][7] += 1;   // 5-ship fits here
                shipDistribution[y][x - 3][5] += 1;   // 3-ship fits here
                shipDistribution[y][x - 3][4] += 1;   // 2-ship fits here
              }
            }
            //TODO: When target results in Miss, check for [Miss, Hit, Unknown, Hit, Unknown, Hit].
            else if (map[y][x - 1] == MyCellState.Hit && map[y][x - 2] == MyCellState.Unknown && map[y][x - 3] == MyCellState.Hit && map[y][x - 4] == MyCellState.Unknown) {
              shipDistribution[y][x - 2][6] += 1;   // 4-ship fits here
              shipDistribution[y][x - 4][6] += 1;   // 4-ship fits here
              shipDistribution[y][x - 4][4] += 1;   // 2-ship fits here
            }
          }

          //TODO: Check leftward edge is 5 cells away and target Miss.
          if (x > 4 && map[y][x] == MyCellState.Miss) {
            //TODO: Check for [Miss, Hit, Unknown, Hit, Unknown, Hit, Hit].
            if (map[y][x - 1] == MyCellState.Hit && map[y][x - 2] == MyCellState.Unknown && map[y][x - 3] == MyCellState.Hit && map[y][x - 4] == MyCellState.Unknown && map[x - 5][y] == MyCellState.Hit) {
              shipDistribution[y][x - 2][7] += 1;   // 5-ship fits here
              shipDistribution[y][x - 4][7] += 1;   // 5-ship fits here
              shipDistribution[y][x - 4][5] += 1;   // 3-ship fits here
              shipDistribution[y][x - 4][4] += 1;   // 2-ship fits here
            }
          }
          break;
        case 3: //----------------------------------------------------------------------------------------------Down--//
          //TODO: Check downward edge is 1 cell away.
          if (y + 1 > gameSize) {
            //TODO: Check target Hit.
            if (map[y][x] == MyCellState.Hit) {
              //TODO: Check for [Hit, Unknown].
              if (map[y + 1][x] == MyCellState.Unknown) {
                shipDistribution[y - 1][x][4] += 2;   // 2-ship fits here
              }
              //TODO: Check 2 corners at downward/leftward amd downward/rightward adjacent cells following target Hit.
              if (map[y][x - 1] == MyCellState.Hit) {
                //TODO: Verify corner formed with leftward cell and that inside corner Unknown.
                if (c3 && map[y + 1][x - 1] == MyCellState.Unknown) {
                  shipDistribution[y + 1][x - 1][8] += 1;
//                  System.out.println("\nBOTTOM LEFT CORNER\n");
                }
                //TODO: Verify corner formed with rightward cell and that inside corner Unknown.
                if (c1 && map[y + 1][x + 1] == MyCellState.Unknown) {
                  shipDistribution[y + 1][x + 1][8] += 1;
//                  System.out.println("\nBOTTOM RIGHT CORNER\n");
                }
              }
            }
          }

          //TODO: Check downward edge is 2 cells away.
          if (y + 2 < gameSize) {
            //TODO: Check target Hit.
            if (map[y][x] == MyCellState.Hit) {
              //TODO: Check for [Hit, Unknown, Hit].
              if (map[y + 1][x] == MyCellState.Unknown && map[y + 2][x] == MyCellState.Hit) {
                shipDistribution[y + 1][x][4] += 2;   // 2-ship fits here twice
                shipDistribution[y + 1][x][5] += 1;   // 3-ship fits here
              }
            }
            //TODO: When target results in Miss, check for [Miss, Hit, Unknown, Hit].
            else if (map[y + 1][x] == MyCellState.Hit && map[y + 2][x] == MyCellState.Unknown) {
              shipDistribution[y + 2][x][4] += 1;   // 2-ship fits here
            }
          }

          //TODO: Check downward edge is 3 cells away.
          if (y + 3 < gameSize) {
            //TODO: Check target Hit.
            if (map[y][x] == MyCellState.Hit) {
              //TODO: Check for [Hit, Unknown, Hit, Unknown].
              if (map[y + 1][x] == MyCellState.Unknown && map[y + 2][x] == MyCellState.Hit && map[y + 3][x] == MyCellState.Unknown) {
                shipDistribution[y + 1][x][6] += 1;   // 4-ship fits here
                shipDistribution[y + 3][x][6] += 1;   // 4-ship fits here
                shipDistribution[y + 3][x][4] += 1;   // 2-ship fits here
              }
            }
            //TODO: When target results in Miss, check for [Miss, Hit, Unknown, Hit, Unknown].
            else if (map[y + 1][x] == MyCellState.Hit && map[y + 2][x] == MyCellState.Unknown && map[y + 3][x] == MyCellState.Hit) {
              shipDistribution[y + 2][x][5] += 1;   // 3-ship fits here
              shipDistribution[y + 2][x][4] += 1;   // 2-ship fits here
            }
          }

          //TODO: Check downward edge is 4 cells away.
          if (y + 4 < gameSize) {
            //TODO: Check target Hit.
            if (map[y][x] == MyCellState.Hit) {
              //TODO: Check for [Hit, Unknown, Hit, Unknown, Hit].
              if (map[y + 1][x] == MyCellState.Unknown && map[y + 2][x] == MyCellState.Hit && map[y + 3][x] == MyCellState.Unknown && map[y + 4][x] == MyCellState.Hit) {
                shipDistribution[y + 1][x][7] += 1;   // 5-ship fits here
                shipDistribution[y + 3][x][7] += 1;   // 5-ship fits here
                shipDistribution[y + 3][x][5] += 1;   // 3-ship fits here
                shipDistribution[y + 3][x][4] += 1;   // 2-ship fits here
              }
            }
            //TODO: When target results in Miss, check for [Miss, Hit, Unknown, Hit, Unknown, Hit].
            else if (map[y + 1][x] == MyCellState.Hit && map[y + 2][x] == MyCellState.Unknown && map[y + 3][x] == MyCellState.Hit && map[y + 4][x] == MyCellState.Unknown) {
              shipDistribution[y + 2][x][6] += 1;   // 4-ship fits here
              shipDistribution[y + 4][x][6] += 1;   // 4-ship fits here
              shipDistribution[y + 4][x][4] += 1;   // 2-ship fits here
            }
          }

          //TODO: Check downward edge is 5 cells away and target Miss.
          if (y + 5 < gameSize && map[y][x] == MyCellState.Miss) {
            //TODO: Check for [Miss, Hit, Unknown, Hit, Unknown, Hit, Hit].
            if (map[y + 1][x] == MyCellState.Hit && map[y + 2][x] == MyCellState.Unknown && map[y + 3][x] == MyCellState.Hit && map[y + 4][x] == MyCellState.Unknown && map[x][y + 5] == MyCellState.Hit) {
              shipDistribution[y + 2][x][7] += 1;   // 5-ship fits here
              shipDistribution[y + 4][x][7] += 1;   // 5-ship fits here
              shipDistribution[y + 4][x][5] += 1;   // 3-ship fits here
              shipDistribution[y + 4][x][4] += 1;   // 2-ship fits here
            }
          }
          break;
        default:
          break;
      }
    }
  }


  /**
   * Check if a ship has sunk and update the number of sunken ships for this game.
   *
   * @return boolean representing if a ship was sunk after checking.
   */
  private boolean sunken() {
    if (sunkenShips != battleShip.numberOfShipsSunk()) {
      sunkenShips = battleShip.numberOfShipsSunk();
//      System.out.println("\nSUNKEN SHIP\n");
      return true;
    } else {
//      printMap();
      return false;
    }
  }


  /**
   * @param x
   * @param y
   * @return
   */
  private int[] cellToTarget(int x, int y) {
    int[] temp = new int[5];
    temp[0] = x;
    temp[1] = y;
    temp[2] = shipDistribution[y][x][0] + shipDistribution[y][x][1] + shipDistribution[y][x][2] + shipDistribution[y][x][3];
    temp[3] = shipDistribution[y][x][4] + shipDistribution[y][x][5] + shipDistribution[y][x][6] + shipDistribution[y][x][7];
    temp[4] = shipDistribution[y][x][8];
    return temp;
  }


  /**
   * Method used to print the map that stores the state of all cells that are Unknown, Hit, or Miss.
   */
  public void printMap() {
//    System.out.println("MAP");
    for (int x = 0; x < gameSize; x++) {
      for (int y = 0; y < gameSize; y++)
        System.out.print(map[y][x] + " ");
      System.out.println();
    }
//    System.out.println();
  }


  /**
   * Method used to print the probability distribution of suspect cells as a matrix of integer vectors.
   */
  public void printDist() {
    String result = "";
    for (int i = 0; i < gameSize; i++) {
      for (int j = 0; j < gameSize; j++) {
        for (int k = 0; k < shipDistribution[0][0].length; k++) {
          result = k > 0 ?
              (k < shipDistribution[0][0].length - 1 ?
                  result + String.format(",%-2d", shipDistribution[j][i][k])
                  : result + String.format(",%-2d]    ", shipDistribution[j][i][k]))
              : result + String.format("[%-2d", shipDistribution[j][i][k]);
        }
        result = j < gameSize - 1 ? result : result + "\n\n";
      }
    }

    System.out.println(result);
  }
}
