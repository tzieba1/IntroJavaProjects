package codeTemplates;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

/**
 * Use this template to create simple animations in FX. Change the name of the
 * class and put your own name as author below. Change the size of the canvas
 * and the window title where marked and add your drawing code in the animation
 * method where shown.
 *
 * @author YOUR NAME
 */
public class FXAnimationTemplate extends Application {

    /**
     * Sets up the stage and starts the main thread. Your drawing code should
     * NOT go here.
     *
     * @param stage The first stage
     */
    @Override
    public void start(Stage stage) {
        stage.setTitle("Section 1.4 Animated!"); // window title here
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
     * Note: Although most of what you will do will probably work, there is a 
     * possibility of the application appearing to freeze after a while because
     * the drawing is not happening in a thread safe way. If this is happening,
     * create a private draw() method with parameters for x, y, etc. to do the 
     * actual drawing work, then call it like this...
     * 
     * Platform.runLater(() -> {
     *      draw(x,y...);
     * });
     * 
     * @param gc The drawing surface
     */
    public void animate(GraphicsContext gc) {
        // YOUR CODE HERE!
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
