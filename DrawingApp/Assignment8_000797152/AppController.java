package Assignment8_000797152;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * This is the controller class for this 'Drawing Application' which uses the GeometricObject
 * model found in the same package here.
 */
public class AppController extends Application
  {
  
  /** initializing screen width constant. **/
  private final double SCREEN_WIDTH = 1000;
  
  /** Initializing screen height constant. **/
  private final double SCREEN_HEIGHT = 800;
  
  /** Initializing user controller height constant for GUI component area. **/
  private final double CONTROL_HEIGHT = 170;
  
  /** Initializing the drawing surface Canvas color as constant. **/
  private final Color CANVAS_COLOR = Color.LIGHTYELLOW;
  
  /** Initializing the controller surface color as constant. **/
  private final Color CONTROL_COLOR = Color.DARKGRAY;
  
  /** Initializing the (out)line highlight color for selected objects as constant.**/
  private final Color HIGHLIGHT_COLOR = Color.LIGHTBLUE;
  
  /** Declaring the GraphicsContext objects to be drawn on the top pane. **/
  private GraphicsContext gc;

  /** Declaring the GraphicsContext objects to be drawn on the transparent pane. **/
  private GraphicsContext transgc;
  
  /** Declaring the starting coordinates for moving objects. **/
  private double sx, sy;
  
  /** Declaring ending coordinates for moving objects. **/
  private double ex, ey;
  
  /** Initializing a default (out)line color. **/
  private Color outline_color = Color.BLUE;
  
  /** Declaring a ColorPicker object for selecting an (out)line color. **/
  private ColorPicker outline_color_picker;
  
  /** Initializing a default fill color. **/
  private Color fill_color = Color.RED;
  
  /** Declaring a ColorPicker object for selecting a fill color. **/
  private ColorPicker fill_color_picker;
  
  /** Declaring an (out)line width. **/
  private int line_width;
  
  /** Declaring the object type where: 0 = line, 1 = rectangle, 2 = ellipse. **/
  private int object_type;
  
  /** Declaring the TextField to be parsed for an outline width. **/
  private TextField outline_width_field;
  
  /** Declaring a currently selected GeometricObject. **/
  private GeometricObject selected;
  
  /** Declaring the color for the currently selected GeometricObject. **/
  private Color selected_color;
  
  /** Declaring the index of the currently selected GeometricObject stored in an ArrayLst. **/
  private int selected_index;
  
  /** Declaring temporary coordinates used to hold the location pressed before a MOUSE_DRAGGED MouseEvent occurs.**/
  private double temp_x, temp_y;

  //*** Reference to the Model:
  //    Declaring an ArrayList which stores GeometricObjects to be drawn on the canvas. Last object represents front.
  private ArrayList<GeometricObject> geom_objects;


  //*** Private event handlers and helper methods.

  /**
   * Method used to re-draw all the GeometricObjects stored in the 'geom_objects' ArrayList.
   */
  public void redraw()
    {
    gc.setFill(Color.LIGHTYELLOW);  //Setting the fill color to the canvas color used to reset the canvas.
    gc.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT - CONTROL_HEIGHT);    //Resetting the canvas.

    //*** Looping through the geom_objects ArrayList to redraw all GeometricObjects
    //    onto the currently reset top Canvas' GraphicsContext object called 'gc'.
    for (GeometricObject curr_go : geom_objects)
      curr_go.draw(gc);  //Drawing the current GeometricObject in the ArrayList.

    //*** Resetting the transparent Canvas' GraphicsContext called 'transgc' for another object to be drawn.
    transgc.clearRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
    }

  /**
   * Event handler that sets the object type corresponding to a line whenever the 'Line' button is pressed.
   * @param actionEvent The ActionEvent triggering this event handler.
   */
  private void lineHandler(ActionEvent actionEvent) { this.object_type = 0; } // 0 = Line.

  /**
   * Event handler that sets the object type corresponding to a rectangle whenever the 'Rectangle' button is pressed.
   * @param actionEvent The ActionEvent triggering this event handler.
   */
  private void rectangleHandler(ActionEvent actionEvent) { this.object_type = 1; } // 1 = Rectangle.

  /**
   * Event handler that sets the object type corresponding to an ellipse whenever the 'Ellipse' button is pressed.
   * @param actionEvent The ActionEvent triggering this event handler.
   */
  private void ellipseHandler(ActionEvent actionEvent) { this.object_type = 2; } // 2 = Ellipse.

  /**
   * Event handler that deletes the currently selected object.
   * @param actionEvent The ActionEvent triggering this event handler.
   */
  private void deleteHandler(ActionEvent actionEvent)
    {
    geom_objects.remove(selected_index);  //Removing the currently selected object from all 'geom_objects'.
    selected = null;  //Resetting the currently selected to null.
    this.redraw();  //Using redraw() to re-draw the objects without the removed object.
    }

  /**
   * Event handler that brings the currently selected object to the front of the canvas.
   * @param actionEvent The ActionEvent triggering this event handler.
   */
  private void toFrontHandler(ActionEvent actionEvent)
    {
    geom_objects.remove(selected_index); //Removing the currently selected object from all 'geom_objects'.
    geom_objects.add(selected); //Adding the currently selected object at the end of 'geom_objects' representing front.
    selected_index = geom_objects.size() - 1; //Re-assigning the selected objects 'geom_objects' index since changed.
    this.redraw();  //Redrawing the objects on the canvas.
    }

  /**
   * Event handler that brings the currently selected object to the back of the canvas.
   * @param actionEvent The ActionEvent triggering this event handler.
   */
  private void toBackHandler(ActionEvent actionEvent)
    {
    geom_objects.remove(selected_index); //Removing the currently selected object from all 'geom_objects'.
    //*** Adding currently selected object to beginning of 'geom_objects' representing back.
    geom_objects.add(0, selected);
    selected_index = 0; //Re-assigning the selected objects 'geom_objects' index since changed.
    this.redraw(); //Redrawing the objects on the canvas.
    }

  /**
   * Event handler that clears all objects from the screen.
   * @param actionEvent The ActionEvent triggering this event handler.
   */
  private void clearHandler(ActionEvent actionEvent)
    {
    selected = null;  //Resetting the currently selected to null.
    selected_color = null;  //Resetting the currently selected color to null.
    geom_objects.clear(); //Using the ArrayList clear() method to clear all items.
    this.redraw();  //Redrawing the objects on the canvas.
    }

  /**
   *  Event handler that resets all the colors to those selected on the color pickers.
   * @param actionEvent The ActionEvent triggering this event handler.
   */
  private void resetHandler(ActionEvent actionEvent)
    {
    gc.setFill(CANVAS_COLOR);  //Sets the fill to the screen color.
    gc.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT); //Coloring the canvas.
    //*** Loop iterating and drawing each object with chosen colors.
    for (GeometricObject go : geom_objects)
      {
      go.setLineColor(outline_color_picker.getValue()); //Setting the outline color for this geometric object.
      if (go instanceof GeometricShape) //Casting each object that is a shape to set the fill color.
        ((GeometricShape)go).setFillColor(fill_color_picker.getValue());  //Setting the fill color for each shape.
      go.draw(gc);  //Redrawing the objects on the canvas.
      }
    }

  /**
   * Event handler used for pressing the right and left mouse buttons.
   * @param me The mouse pressed event.
   */
  private void pressHandler(MouseEvent me)
    {
    //*** Condition for secondary (right-click) MouseButton event.
    if (me.getButton().equals(MouseButton.SECONDARY))
      {
      this.temp_x = me.getX();  //Setting temp_x to store originally clicked x-coordinate.
      this.temp_y = me.getY();  //Setting temp_x to store originally clicked y-coordinate.
      
      //*** Iterating backwards (from top of pane) through objects to check if right-clicked on some object.
      for (int geom_object = geom_objects.size() - 1; geom_object >= 0; geom_object--)
        {
        //*** Clicking a shape highlights the outline color and assigns the shape to selected.
        if (geom_objects.get(geom_object).selectedCheck(me.getX(), me.getY())) //Checking if clicked on current object.
          {
          if (selected != null) // checking if any objects are right-clicked on.
            //*** Previously selected outline reset from highlighted outline.
            geom_objects.get(selected_index).setLineColor(selected_color);
          
          selected = geom_objects.get(geom_object); //Setting new selected object.
          selected_color = selected.getLineColor(); //Setting new selected object color.
          selected_index = geom_object; //Setting new selected object ArrayList index.
          geom_objects.get(geom_object).setLineColor(HIGHLIGHT_COLOR);  //Setting selected object outline highlighted.
          break; //Ending the loop since the top-most selected is found.
          }
          
        //*** Clicking no shapes gives back selected outline color.
        else
          {
          geom_objects.get(selected_index).setLineColor(selected_color); //Resetting the line color for selected object.
          }
        }
      }
      
    //*** Condition for primary (left-click) MouseButton event.
    else if (me.getButton().equals(MouseButton.PRIMARY))
      {
      sx = me.getX(); //Getting the x-value for a click MouseEvent and assigning it to the start-point x-value sx.
      sy = me.getY(); //Getting the y-value for a click MouseEvent and assigning it to the start-point y-value sy.
      
      //*** Exception handling for the outline_width whenever a non-integer is input by user.
      try {line_width = Integer.parseInt(outline_width_field.getText());}
        catch(Exception e) {new Alert(Alert.AlertType.WARNING, "Invalid Line Width").showAndWait();}
      }
    }

  /**
   * Event handler used for releasing the right and left mouse buttons.
   * @param me The mouse release event.
   */
  private void releaseHandler(MouseEvent me)
    {
    ex = me.getX(); //Getting the x-value for a click-release MouseEvent and assigning it to the endpoint x-value ex.
    ey = me.getY(); //Getting the y-value for a click-release MouseEvent and assigning it to the endpoint y-value ey.

    //*** Condition for primary (left-click) MouseButton event.
    if (me.getButton().equals(MouseButton.PRIMARY))
      {
      //*** Initializing the geometric object being drawn.
      switch (this.object_type)
        {
        //*** Case for a Line.
        case 0:
          Line l = new Line(sx, sy, ex, ey, line_width, outline_color_picker.getValue()); //Create a new Line.
          geom_objects.add(l);   //Adding the new Line to the 'geom_objects' ArrayList.
          break;

        //*** Case for a Rectangle.
        case 1:
          //*** Checking if the starting and ending x-coordinates are inverted when dragging in the opposite
          //    direction (not necessarily top left to bottom right).
          if (ex - sx < 0)
            {
            //*** Using a temporary variable to switch ending and starting x-coordinates.
            double temp_x = sx;
            sx = ex;
            ex = temp_x;
            }
          
          //*** Checking if the starting and ending y-coordinates are inverted when dragging in the opposite
          //    direction (not necessarily top left to bottom right).
          if (ey - sy < 0)
            {
            //*** Using a temporary variable to switch ending and starting y-coordinates.
            double temp_y = sy;
            sy = ey;
            ey = temp_y;
            }
          //** Creating a Rectangle.
          Rectangle r = new Rectangle(sx, sy, ex, ey, line_width, outline_color_picker.getValue(),
              fill_color_picker.getValue());
          geom_objects.add(r); //Adding the new rectangle to the 'geom_objects' ArrayList.
          break;

        //*** Case for a Ellipse.
        case 2:
          //*** Checking if the starting and ending x-coordinates are inverted when dragging in the opposite
          //    direction (not necessarily top left to bottom right).
          if (ex - sx < 0)
            {
            //*** Using a temporary variable to switch ending and starting x-coordinates.
            double temp_x = sx;
            sx = ex;
            ex = temp_x;
            }
          //*** Checking if the starting and ending y-coordinates are inverted when dragging in the opposite
          //    direction (not necessarily top left to bottom right).
          if (ey - sy < 0)
            {
            //*** Using a temporary variable to switch ending and starting y-coordinates.
            double temp_y = sy;
            sy = ey;
            ey = temp_y;
            }
          //*** Creating an Ellipse.
          Ellipse e = new Ellipse(sx, sy, ex, ey, line_width, outline_color_picker.getValue(),
              fill_color_picker.getValue());
          geom_objects.add(e);  //Adding the new ellipse to the 'geom_objects' ArrayList.
          break;
        }
      this.redraw();  //Re-draw the canvas with new object.
      }
      
    //*** Condition for secondary (right-click) MouseButton event.
    else if (me.getButton().equals(MouseButton.SECONDARY))
      this.redraw();  //Redraw the canvas.
    }
  
  /**
   * This event handler will draw a GeometricObject on the transparent surface above the drawing. Left clicking
   * will begin drawing a new object which has a size determined by where the drag is released. Right clicking will
   * begin dragging the currently right-click selected object ONLY WHILE 'object_type' IS THE SAME AS THAT WHICH IS
   * BEING DRAGGED.
   *
   * @param me The mouse drag event.
   */
  private void dragHandler(MouseEvent me)
    {
    transgc.clearRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);   //Resetting the transparent Canvas' GraphicsContext.
    ex = me.getX();  //Getting the x-value for a drag MouseEvent and assigning it to the endpoint x-value ex.
    ey = me.getY();  //Getting the y-value for a drag MouseEvent and assigning it to the endpoint y-value ey.
    //*** Handling the exception whenever a user tries to draw or drag an object off-screen.
    if (ex < 0 || ex > SCREEN_WIDTH || ey < 0 || ey > SCREEN_HEIGHT)
      new Alert(Alert.AlertType.WARNING, "Cannot draw shape outside of screen.").showAndWait();
    else
      {
      //** Switched on object type: 0 = Line, 1 = Rectangle, 2 = Ellipse.
      switch (object_type)
        {
        //*** Case for a Line.
        case 0:
          //**Condition for a left-click mouse event.
          if (me.getButton().equals(MouseButton.PRIMARY))
            {
            Line temp_l = new Line(sx, sy, ex, ey, line_width, outline_color_picker.getValue()); // Temporary line.
            temp_l.draw(transgc); //Drawing the new temporary line on transparent canvas.
            }
            
          //**Condition for a right-click mouse event.
          else if (me.getButton().equals(MouseButton.SECONDARY))
            {
            //** Condition for the selected object to be a Line.
            if (selected instanceof Line)
              {
              geom_objects.remove(selected_index);  //Remove the originally positioned object from 'geom_objects''.
              this.redraw(); //Redraw the canvas without the originally positioned object.
              
              //*** Create a temporary line to be drawn each time a secondary button dragging MouseEvent occurs.
              GeometricObject temp_l = new Line(selected.getStartX() + ex - temp_x,
                  selected.getStartY() + ey - temp_y, selected.getEndX() + ex - temp_x,
                  selected.getEndY() + ey - temp_y, selected.getLineWidth(), HIGHLIGHT_COLOR);
              temp_l.draw(transgc); //Draw temporary line on the transparent canvas.
              geom_objects.add(selected_index, temp_l); //Add dragged line back to its original index in 'geom_objects'.
              }
            }
          break;

        //*** Case for a Rectangle.
        case 1:
          //**Condition for a right-click mouse event.
          if (me.getButton().equals(MouseButton.PRIMARY))
            {
            Rectangle temp_r; //Declare a temporary rectangle.
            
            //*** If-else conditions here will create a new rectangle by passing in the sx, sy, ex, ey such that
            //    an object can be stretched not only from top-left to bottom-right.
            if (ex < sx && ey < sy)
              {
              temp_r = new Rectangle(ex, ey, sx, sy, line_width, outline_color_picker.getValue(),
                  fill_color_picker.getValue());
              }
            else if (ex < sx && ey > sy)
              {
              temp_r = new Rectangle(ex, sy, sx, ey, line_width, outline_color_picker.getValue(),
                  fill_color_picker.getValue());
              }
            else if (ex > sx && ey < sy)
              {
              temp_r = new Rectangle(sx, ey, ex, sy, line_width, outline_color_picker.getValue(),
                  fill_color_picker.getValue());
              }
            else
              {
              temp_r = new Rectangle(sx, sy, ex, ey, line_width, outline_color_picker.getValue(),
                  fill_color_picker.getValue());
              }
            temp_r.draw(transgc); //Drawing the temporary rectangle for this dragging mouse event.
            }
            
          //**Condition for a right-click mouse event.
          else if (me.getButton().equals(MouseButton.SECONDARY))
            {
            //** Condition for the selected object to be a Rectangle.
            if (selected instanceof Rectangle)
              {
              geom_objects.remove(selected_index);  //Remove the originally positioned object from 'geom_objects'.
              this.redraw(); //Redraw the canvas without the originally positioned object.
              
              //*** Create a temporary rectangle to be drawn each time a secondary button dragging MouseEvent occurs.
              GeometricObject temp_r = new Rectangle(selected.getStartX() + ex - temp_x,
                  selected.getStartY() + ey - temp_y, selected.getEndX() + ex - temp_x,
                  selected.getEndY() + ey - temp_y, selected.getLineWidth(),
                  HIGHLIGHT_COLOR, ((Rectangle) selected).getFillColor());
              temp_r.draw(transgc); //Draw each temporary rectangle on the transparent canvas.
              
              //*** Add dragged rectangle back to its original index in 'geom_objects'.
              geom_objects.add(selected_index, temp_r);
              }
            }
          break;

        //*** Case for an Ellipse.
        case 2:
          //**Condition for a left-click mouse event.
          if (me.getButton().equals(MouseButton.PRIMARY))
            {
            Ellipse temp_e; //Declaring temporary ellipse for the transparent canvas during each dragged mouse event.
            
            //*** If-else conditions here will create a new ellipse by passing in the sx, sy, ex, ey such that
            //    an object can be stretched not only from top-left to bottom-right.
            if (ex < sx && ey < sy)
              {
              temp_e = new Ellipse(ex, ey, sx, sy, line_width, outline_color_picker.getValue(),
                  fill_color_picker.getValue());
              }
            else if (ex < sx && ey > sy)
              {
              temp_e = new Ellipse(ex, sy, sx, ey, line_width, outline_color_picker.getValue(),
                  fill_color_picker.getValue());
              }
            else if (ex > sx && ey < sy)
              {
              temp_e = new Ellipse(sx, ey, ex, sy, line_width, outline_color_picker.getValue(),
                  fill_color_picker.getValue());
              }
            else
              {
              temp_e = new Ellipse(sx, sy, ex, ey, line_width, outline_color_picker.getValue(),
                  fill_color_picker.getValue());
              }
            temp_e.draw(transgc);  //Drawing the temporary ellipse for this dragging mouse event.
            }

          //**Condition for a right-click mouse event.
          else if (me.getButton().equals(MouseButton.SECONDARY))
            {
            //** Condition for the selected object to be a Ellipse.
            if (selected instanceof Ellipse)
              {
              geom_objects.remove(selected_index);  //Remove the originally positioned object from 'geom_objects'.
              this.redraw(); //Redraw the canvas without the originally positioned object.
              //*** Create a temporary ellipse to be drawn each time a secondary button dragging MouseEvent occurs.
              GeometricObject temp_e = new Ellipse(selected.getStartX() + ex - temp_x,
                  selected.getStartY() + ey - temp_y, selected.getEndX() + ex - temp_x,
                  selected.getEndY() + ey - temp_y, selected.getLineWidth(),
                  HIGHLIGHT_COLOR, ((GeometricShape) selected).getFillColor());
              temp_e.draw(transgc); //Draw each temporary ellipse on the transparent canvas.
              
              //*** Add dragged ellipse back to its original index in 'geom_objects'.
              geom_objects.add(selected_index, temp_e);
              }
            }
          break;
        }
      }
    }
  
  /**
   * The components, model, and event handlers for this GUI application are added in this overridden method.
   *
   * @param stage The main stage.
   * @throws Exception
   */
  @Override
  public void start(Stage stage) throws Exception
    {
    Pane root = new Pane(); //Creating a new Pane object where GeometricObjects will be stored.
    Scene scene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT, CONTROL_COLOR); //Setting window size and color.
    stage.setTitle("Drawing App"); //Setting window title.
    stage.setScene(scene);  //Setting the scene on the main stage that parametrizes this start() method.
    
    geom_objects = new ArrayList<>(); //Creating the model.
    
    //*** GUI components required for application.
    Canvas c = new Canvas(SCREEN_WIDTH, SCREEN_HEIGHT - CONTROL_HEIGHT);  //Setting drawing canvas.
    c.relocate(0, CONTROL_HEIGHT);  //Relocating the drawing canvas below the area for GUI controls.
    Canvas transc = new Canvas(SCREEN_WIDTH, SCREEN_HEIGHT);  //Setting transparent canvas for mouse dragging events.
    transc.relocate(0, CONTROL_HEIGHT); //Relocating the transparent canvas below the area for GUI controls.
    
    //*** (Out)line Color controls.
    Label outline_label = new Label("Outline"); //Creating a label for the (out)line color picker.
    outline_label.relocate(10, 50); //Relocating the (out)line label.
    outline_color_picker = new ColorPicker(outline_color);  //Creating a new color picker for the (out)line color.
    outline_color_picker.relocate(60, 50);  //Relocating the (out)line color picker.

    //*** Fill Color controls.
    Label fill_label = new Label("Fill"); //Creating a label for the fill color picker.
    fill_label.relocate(10, 90); //Relocating the fill label.
    fill_color_picker = new ColorPicker(fill_color); //Creating a new color picker for the fill color.
    fill_color_picker.relocate(60, 90); //Relocating the fill color picker.
    
    //Color reset controls.
    Button color_reset_button = new Button("Reset Colours");  //Creating a color reset button.
    color_reset_button.relocate(250, 50); //Relocating the color reset button.
    color_reset_button.setOnAction(this::resetHandler); // Adding an event handler for color reset button being pressed.
    
    //*** Line controls.
    Button line_button = new Button("Line");  //Creating a line button for creating and moving lines.
    line_button.relocate(10, 10 );  //Relocating the line button.
    line_button.setOnAction(this::lineHandler); // Adding event handler for the line button being pressed.
    
    //*** Rectangle controls.
    Button rectangle_button = new Button( "Rectangle" ); //Creating a rectangle button to create and move lines.
    rectangle_button.relocate(55, 10 );  //Relocating the rectangle button.
    rectangle_button.setOnAction(this::rectangleHandler); // Adding event handler for rectangle button being pressed.
    
    //*** Ellipse controls.
    Button ellipse_button = new Button("Ellipse"); //Creating a ellipse button to create and move lines.
    ellipse_button.relocate(130, 10); //Relocating the ellipse button.
    ellipse_button.setOnAction(this::ellipseHandler); // Adding event handler for ellipse button being pressed.
    
    //*** Clear controls.
    Button clear_button = new Button("Clear"); //Creating a clear button to clear the canvas.
    clear_button.relocate(250, 10); //Relocating the clear button.
    clear_button.setOnAction(this::clearHandler); // Adding event handler for clear button being pressed.
    
    //*** Selected object controls.
    Label selected_label = new Label("Selected Object"); //Creating a label for the selected object controls.
    selected_label.relocate(425, 90); //Relocating the label for the selected object controls.
    
    Button delete_button = new Button("Delete"); //Creating a delete button to delete currently selected object.
    delete_button.relocate(380, 130);  //Relocating delete button.
    delete_button.setOnAction(this::deleteHandler); // Adding event handler for delete button being pressed.
    
    Button front_button = new Button("Front"); //Creating a front button to bring selected object to the front.
    front_button.relocate(447, 130); //Relocating front button.
    front_button.setOnAction(this::toFrontHandler); // Adding event handler for front button being pressed.
    
    Button back_button = new Button("Back"); //Creating a back button to bring selected object to the back.
    back_button.relocate(505, 130);//Relocating back button.
    back_button.setOnAction(this::toBackHandler); // Adding event handler for back button being pressed.
    
    //*** Outline Width controls.
    Label outline_width_label = new Label("Outline Width"); //Creating a label for the (out)line width controls.
    outline_width_label.relocate(10, 130); //Relocating the label for the (out)line width controls.
    outline_width_field = new TextField("1"); //Creating a text field to be parsed for an appropriate (out)line width.
    outline_width_field.relocate(100, 130); //Relocating the (out)line width text field.
    
    //*** Instructions: label made a relocated giving th user instructions to operate the application.
    Label instructions = new Label (" INSTRUCTIONS: " +
        "\n -Create an object by selecting which object, then left-click dragging." +
        "\n -An object can be selected using the right-click." +
        "\n -Object can be dragged only if object type button has been pressed." +
        "\n -The delete button will remove the selected object from the drawing." +
        "\n -The front button brings the selected object to the front." +
        "\n -The back button brings the selected object to the back." +
        "\n -The clear button clears all objects from the screen." +
        "\n -Reset all object colors with reset colors button..");
    instructions.relocate(600,10);
    
    //*** Adding all of the components to the root Pane.
    root.getChildren().addAll(c, transc, outline_color_picker, fill_color_picker, fill_label, outline_label,
        line_button, color_reset_button, rectangle_button, ellipse_button, clear_button, selected_label,
        delete_button, front_button, back_button, outline_width_label, outline_width_field, instructions);
    
    //*** Creating the two graphics contexts for top/transparent Panes.
    gc = c.getGraphicsContext2D();
    transgc = transc.getGraphicsContext2D();
    
    //*** Drawing the main background canvas.
    gc.setFill(CANVAS_COLOR);
    gc.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
    
    //*** Adding event handlers for pressing, releasing, and dragging the mouse.
    transc.addEventHandler(MouseEvent.MOUSE_PRESSED, this::pressHandler);
    transc.addEventHandler(MouseEvent.MOUSE_RELEASED, this::releaseHandler);
    transc.addEventHandler(MouseEvent.MOUSE_DRAGGED, this::dragHandler);
    
    //*** Show the stage.
    stage.show();
    }


  /**
   * Method that lauches application..
   *
   * @param args unused
   */
  public static void main(String[] args)
    {
    launch(args);
    }
  }
