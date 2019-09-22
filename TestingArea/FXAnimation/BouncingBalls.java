package FXAnimation;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Bouncing Balls exercise starter
 *
 * @author Sam Scott
 */
public class BouncingBalls extends Application {

    /**
     * Sets up the stage and starts the main thread. Your drawing code should
     * NOT go here.
     *
     * @param stage The first stage
     */
    @Override
    public void start(Stage stage) {
        stage.setTitle("Bouncing Balls!"); // window title here
        Canvas canvas = new Canvas(400, 300); // canvas size here
        Group root = new Group();
        Scene scene = new Scene(root);
        root.getChildren().add(canvas);
        stage.setScene(scene);
        stage.show();
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // This code starts a "thread" which will run your animation
        Thread t = new Thread(() -> animate(gc));
        t.start();
    }

    /**
     * Animation thread. This is where you put your animation code.
     *
     * @param gc The drawing surface
     */
    public void animate(GraphicsContext gc) {
        // YOUR CODE HERE!

        // intial positions and speeds
        Ball ball = new Ball(100, 50, -1, -1, 10, Color.RED);

        while (true) // loop forever
        {
            Platform.runLater(() -> {
                // draw screen 
                gc.setFill(Color.YELLOW);
                gc.fillRect(0, 0, 400, 300);
                ball.draw(gc);
            });

            // moving
            ball.moveOneStep();

            // bouncing
            if (ball.getX() <= 0 || ball.getX() >= 400 - (ball.getSize() - 1)) {
                ball.bounceX();
            }
            if (ball.getY() <= 0 || ball.getY() >= 300 - (ball.getSize() - 1)) {
                ball.bounceY();
            }

            // pause
            pause(1000 / 60);
        }

    }

    /**
     * Use this method instead of Thread.sleep(). It handles the possible
     * exception by catching it, because re-throwing it is not an option in this
     * case.
     *
     * @param duration Pause time in milliseconds.
     */
    public static void pause(int duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException ex) {
        }
    }

    /**
     * Exits the app completely when the window is closed. This is necessary to
     * kill the animation thread.
     */
    @Override
    public void stop() {
        System.exit(0);
    }

    /**
     * Launches the app
     *
     * @param args unused
     */
    public static void main(String[] args) {
        launch(args);
    }
}
