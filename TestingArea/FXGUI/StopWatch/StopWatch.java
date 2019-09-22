package FXGUI.StopWatch;

import java.util.Scanner;

/**
 * Solution to Exercise 1c, week 4. This class contains its own main method. Not
 * commented to standard.
 *
 * @author Sam Scott
 */
public class StopWatch
{

  private long startTime, endTime = -1;

  public StopWatch()
  {
    startTime = System.currentTimeMillis();
  }

  public void start()
  {
    startTime = System.currentTimeMillis();
    endTime = -1;
  }

  public void stop()
  {
    endTime = System.currentTimeMillis();
  }

  public long getElapsedTime()
  {
    if (endTime > 0)
    {
      return endTime - startTime;
    }
    return -1;
  }

  public long getStartTime()
  {
    return startTime;
  }

  public long getEndTime()
  {
    return endTime;
  }

  public static void main(String[] args) throws InterruptedException
  {
    Scanner sc = new Scanner(System.in);
    StopWatch sw = new StopWatch();
    System.out.println("Stopwatch started! Press enter to stop.");
    sc.nextLine();
    sw.stop();
    System.out.println("Elapsed Time: " + sw.getElapsedTime() + " ms");
  }
}
