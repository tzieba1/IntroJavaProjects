package PolymorphismAndInterface.DrawShapesApp;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import static javafx.application.Application.launch;

/**
 * Circle and Rectangle revisited - this time as GeometricObjects.
 *
 * @author Sam Scott
 */
public class Draw extends Application {

/**
 * Start method (use this instead of main).
 *
 * @param stage The FX stage to draw on
 * @throws Exception
 */
@Override
public void start(Stage stage) throws Exception {
Group root = new Group();
Scene scene = new Scene(root);
Canvas canvas = new Canvas(400, 300); // Set canvas Size in Pixels
stage.setTitle("Draw"); // Set window title
root.getChildren().add(canvas);
stage.setScene(scene);
GraphicsContext gc = canvas.getGraphicsContext2D();

// YOUR CODE STARTS HERE
GeometricObject c = new Circle();   // note the variable type
GeometricObject r = new Rectangle();

r.setX(250);                        // this works - declared type

//c.draw(gc);                       // this doesn't work - declared type

c.draw(gc);              // cast to make it work - risk of exception, though!
r.draw(gc);           // change c or r to be GeometricObject to see it

Circle t = new Target();            // What are the declared and actual type here?
t.setY(220);                        // GeometricObject method called
t.draw(gc);                         // Circle or Target method called?

System.out.println(c);              // calls the toString() of the actual type
System.out.println(r);
System.out.println(t);

System.out.println("Circle x: "+c.getX());
Circle z = (Circle) c;              //
System.out.println("Circle radius: "+z.getRadius());
System.out.println("Circle radius: "+((Circle) c).getRadius());

GeometricObject x = new Rectangle();        // test using instanceOf to avoid exceptions
if (x instanceof Circle)
  System.out.println("x is a circle of radius !"+((Circle) x).getRadius());
else
  System.out.println("x is not a circle.");

// YOUR CODE STOPS HERE
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
