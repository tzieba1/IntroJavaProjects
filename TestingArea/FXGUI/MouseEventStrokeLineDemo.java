package FXGUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Basic demonstration of mouse events in action.
 *
 * @author sam scott
 */
public class MouseEventStrokeLineDemo extends Application
  {
  
  // TODO: Instance Variables for View Components and Model
  final double SCREEN_WIDTH = 1000;
  final double SCREEN_HEIGHT = 800;
  GraphicsContext gc, transgc;
  double startx, starty;
  double endx, endy;
  
  
  // TODO: Private Event Handlers and Helper Methods
  private void pressHandler(MouseEvent me)
    {
    startx = me.getX();
    starty = me.getY();
    
    System.out.println("Pressed " + me.getButton() + " at (" + me.getX() + "," + me.getY() + ").");
    }
  
  private void releaseHandler(MouseEvent me)
    {
    endx = me.getX();
    endy = me.getY();
    gc.setStroke(Color.BLUE);
    gc.setLineWidth(5.0);
    gc.strokeLine( startx, starty, endx, endy );
    System.out.println("Released " + me.getButton() + " at (" + me.getX() + "," + me.getY() + ").");
    }

  private void dragHandler(MouseEvent me)
    {
    //*** (FIRST ATTEMPT AT ELASTIC LINE SOLUTION)
    //    Erase old line.
    //gc.setStroke(Color.LIGHTYELLOW);
    //gc.setLineWidth(5.0);
    //gc.strokeLine( startx, starty, endx, endy );

    //*** (SECOND ATTEMPT AT ELASTIC LINE SOLUTION)
    //    Reset the canvas with a filled rectangle before drawing the final strokeLine.
    //gc.setFill(Color.LIGHTYELLOW);
    //gc.fillRect(0,0, SCREEN_WIDTH, SCREEN_HEIGHT );

    //*** (THIRD ATTEMPT AT ELASTIC LINE SOLUTION)
    //    Reset a TRANSPARENT CANVAS with an empty rectangle before drawing the final strokeLine.
    transgc.clearRect(0,0,SCREEN_WIDTH,SCREEN_HEIGHT);
    
    //*** Draw new line onto either canvas (SOLUTION ONE||TWO) or transparent canvas (SOLUTION THREE).
    endx = me.getX();
    endy = me.getY();
    //gc.setStroke(Color.BLUE);
    //gc.setLineWidth(5.0);
    //gc.strokeLine( startx, starty, endx, endy );
    transgc.setStroke(Color.BLUE);
    transgc.setLineWidth(5.0);
    transgc.strokeLine( startx, starty, endx, endy );
    System.out.println("Dragged " + me.getButton() + " to (" + me.getX() + "," + me.getY() + ").");
    }
  
  /**
   * This is where you create your components and the model and add event
   * handlers.
   *
   * @param stage The main stage
   * @throws Exception
   */
  @Override
  public void start(Stage stage) throws Exception
    {
    Pane root = new Pane();
    Scene scene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT); // set the size here
    stage.setTitle("Mouse Events"); // set the window title here
    stage.setScene(scene);
    // TODO: Add your GUI-building code here
    
    // 1. Create the model
    // 2. Create the GUI components
    Canvas c = new Canvas(SCREEN_WIDTH, SCREEN_HEIGHT);
    Canvas transc = new Canvas(SCREEN_WIDTH, SCREEN_HEIGHT);  //Transparent canvas.
    // 3. Add components to the root
    root.getChildren().add(c);
    root.getChildren().add(transc); //Adding transparent canvas AFTER the back (release) canvas.
    // 4. Configure the components (colors, fonts, size, location)
    gc = c.getGraphicsContext2D();
    transgc = transc.getGraphicsContext2D();
    gc.setFill(Color.LIGHTYELLOW);
    gc.fillRect(0, 0, SCREEN_WIDTH , SCREEN_HEIGHT);
    // 5. Add Event Handlers and do final setup
    //*** All mouse handlers must be attached to the TOPMOST canvas (transparent canvas).
    transc.addEventHandler(MouseEvent.MOUSE_PRESSED, this::pressHandler);
    transc.addEventHandler(MouseEvent.MOUSE_RELEASED, this::releaseHandler);
    transc.addEventHandler(MouseEvent.MOUSE_DRAGGED, this::dragHandler);
    // 6. Show the stage
    stage.show();
    }
  
  /**
   * Make no changes here.
   *
   * @param args unused
   */
  public static void main(String[] args)
    {
    launch(args);
    }
  }
