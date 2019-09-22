package Assignment1_000797152;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.lang.Math;
import java.util.Scanner;

import static javafx.application.Application.launch;

/**
 * Created:     May 23, 2019
 *
 * Description:     This program displays an 800x800 px canvas with a finished drawing. The drawing will require user
 * input in order to place any number of stars in the field connected with a series of lines back to the first star.
 * When the program runs, the user will be prompted for coordinates and constellation title and then display the canvas.
 *
 * @author Tommy Zieba
 */
public class Constellations extends Application
{

    /**
     * @param stage The FX stage to draw on
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception
    {
        // *** JavaFX Graphics Template
        Group root = new Group();
        Scene scene = new Scene(root, Color.BLACK);
        Canvas canvas = new Canvas(800, 800); // Set canvas Size in Pixels
        stage.setTitle("Constellations"); // Set window title
        root.getChildren().add(canvas);
        stage.setScene(scene);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // *** Declaring the scanner to be used for user input and constants for the max x,y positions in scene
        // and max star radius.
        Scanner sc_int = new Scanner(System.in);
        Scanner sc_str = new Scanner(System.in);

        final int X_MAX = 800;
        final int Y_MAX = 800;
        final int DIAM_MAX = 2;

        // *** Drawing the star-field.
        // Setting the colour to white for the methods .strokeLine() and .fillOval() to draw stars.
        gc.setStroke(Color.WHITE);
        gc.setFill(Color.WHITE);

        // Initialize a for loop to draw stars randomly to cover up to 20% of scene.
        for(int i = 0; i < 8000; i++)
        {
            // Initializing new variables of type double representing some random
            // x,y position on canvas and some random radius upon each iteration.
            double x = Math.random() * X_MAX;
            double y = Math.random() * Y_MAX;
            double diam = Math.random() * DIAM_MAX;


            // Drawing one random "circle" of pixels upon each iteration.
            gc.fillOval(x, y, diam, diam);
        }

        // *** User Input and Drawing Constellation
        // Prompting user by explaining how their inputs influence the program. A time delay is given for reading.
        System.out.println("To draw a constellation, input a series of coordinates (x, y) where 3 < x < 797 and " +
                "3 < y < 797 for star positions. \nWhen finished, input 'done' after the prompt " +
                "to connect the last star position given to the first.");
        Thread.sleep(3000);

        // Prompting user for first x and y coordinate and testing if it is in the correct range considering 'MAX_DIAM'.
        System.out.println("Provide integer x coordinate: ");
        int first_x = sc_int.nextInt();
        if(first_x < 3 || first_x > 797){
            System.out.println("Error: must be integer within range.\nPlease enter another integer x " +
                    "coordinate: ");
            first_x = sc_int.nextInt();
        }
        System.out.println("Provide integer y coordinate: ");
        int first_y = sc_int.nextInt();
        if(first_y < 3 || first_y > 797){
            System.out.println("Error: must be integer within range.\nPlease enter another integer y " +
                    "coordinate: ");
            first_x = sc_int.nextInt();
        }

        // Drawing the first star and choosing an odd diameter (5px) such that the 1px line that will extend from the
        // center of the point. The adjustment of -2 is to center the point where desired.
        gc.fillOval(first_x - 2, first_y - 2, 5, 5);

        // Defining temporary placeholders for coordinates used for connecting two points at a time
        int temp_x1 = first_x;
        int temp_y1 = first_y;
        int temp_x2 = first_x;
        int temp_y2 = first_y;

        // Initializing a sentinal value to terminate the following while loop for drawing stars and connecting lines.
        String user_done = "not done";
        while(!(user_done.equalsIgnoreCase("done")))
        {
            // Prompting user if they are done giving stars for their constellation.
            System.out.println("Are you done drawing stars? (press enter to continue)");
            user_done = sc_str.nextLine();

            // If the user is not done, the user is prompted for another point
            if(!(user_done.equalsIgnoreCase( "done"))){
                // Getting second point from user to connect a line between the temporary points with error prompt.
                System.out.println("Provide the next integer x coordinate: ");
                temp_x2 = sc_int.nextInt();
                if(temp_x2 < 3 || temp_x2 > 797){
                    System.out.println("Error: must be integer within range.\nPlease enter another integer x " +
                            "coordinate: ");
                    temp_x2 = sc_int.nextInt();
                }
                System.out.println("Provide the next integer y coordinate: ");
                temp_y2 = sc_int.nextInt();
                if(temp_y2 < 3 || temp_y2 > 797) {
                    System.out.println("Error: must be integer within range.\nPlease enter another integer y " +
                            "coordinate: ");
                    temp_y2 = sc_int.nextInt();
                }

                // Drawing the stars at the points provided.
                gc.fillOval(temp_x2-2, temp_y2-2, 5, 5);

                // Drawing the line between points.
                gc.strokeLine(temp_x1, temp_y1, temp_x2, temp_y2);

                // Reassigning the temporary coordinates for the next iteration of the while loop.
                temp_x1 = temp_x2;
                temp_y1 = temp_y2;
            }
        }

        // Connecting the last point to the first
        gc.strokeLine(temp_x2, temp_y2, first_x, first_y);

        // *** User Input and Drawing Constellation Title and Program Credits
        // Prompting for and displaying the name of the constellation.
        System.out.println("What would you like to name your constellation? (15 characters or less)");
        String name = sc_str.nextLine();
        while(name.length() > 15){
            System.out.println("Error: Too many characters, give another name.");
            name = sc_str.nextLine();
        }

        // Setting the x-offset to place the title beside the first star without being offscreen.
        int offset_x = 10;
        if(first_x > 400){
            offset_x = -370;
        }
        // Setting the y-offset to place the title beside the first star without being offscreen.
        int offset_y = 30;
        if(first_y > 400){
            offset_y = -30;
        }

        // Setting the font and displaying the constellation name.
        gc.setFill(Color.ORANGERED);
        gc.setFont(Font.font(30));
        gc.fillText(name, first_x + offset_x, first_y + offset_y );

        // Displaying program credits at the footer of the scene.
        gc.setFont(Font.font(14));
        gc.fillText("Program Author: Tommy Zieba, 000797152", 535, 790);

        stage.show();
    }

    /**
     * The actual main method that launches the app.
     *
     * @param args unused
     */
    public static void main(String[] args) {
        launch(args);
    }
}
