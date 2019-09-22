package week11;

import java.util.Scanner;

/**
 * Example of exception handling for Thread.sleep(). Note that
 * InterruptedException is a checked exception. Any method that could throw this
 * exception must either catch it or declare that it will throw it.
 *
 * @author sam scott
 */
public class Except1 {

    /**
     * Pauses the program based on how many frames per second the user wants.
     * For example, if they want 20 frames per second, the pause time should be
     * 1000/20 = 50 ms.
     *
     * @param fps Number of frames per second
     * @throws InterruptedException
     */
    public static void pause(int fps) throws InterruptedException {
        int pauseTime = 1000 / fps;
        Thread.sleep(pauseTime);
        System.out.println("Paused for " + pauseTime + " ms.");
    }

    /**
     * TTD: Fix this so that the program compiles. TTD: Deal with the possible
     * division by 0 exception.
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("How many frames per second?");
        int framesPerSecond =  sc.nextInt();
        //pause(framesPerSecond);
    }
}
