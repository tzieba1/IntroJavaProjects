package FXGUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * @author sam.scott1
 */
public class ButtonsAndGridPane extends Application
  {

// TODO: Instance Variables for Inheritance.View Components and Model
  private Button[] buttons = new Button[4];
  
// TODO: Private Event Handlers and Helper Methods
  
  /**
   * Make no changes here.
   *
   * @param args unused
   */
  public static void main(String[] args)
    {
    launch(args);
    }

  /**
   * Method that initializes a private array of Buttons called 'buttons' for this FXGUI.ButtonsAndGridPane class.
   */
  private void initBtnsArray()
    {
    for (int i = 0; i < this.buttons.length; i++)
      this.buttons[i] = new Button("Button #" + i);
    }
  
  /**
   * Method used to make a GridPane of Buttons from the initialized Button[] array called 'buttons'.
   * @return Returns the GridPane representing a row of numbered Buttons the same size as the array 'buttons'.
   */
  private Pane getGridPane ()
    {
    GridPane gridPane  = new GridPane();
    
    int i = 0;
    for(Button b : this.buttons)
      {
    gridPane.add( b, i*(i+(int)b.getWidth()), 0);
    i++;
      }
    
    return gridPane;
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
    Scene scene = new Scene(root, 400, 225); // set the size here
    stage.setTitle("TEST"); // set the window title here
    stage.setScene(scene);
// TODO: Add your FXGUI-building code here

// 1. Create the model
// 2. Create the FXGUI components
    
    //*** Trying to add an array of generic 'b' Buttons to the root one at a time (without positioning them in a grid).
    Button[] button_array = new Button[4];
    Button b = new Button("Press Me!");;
    for (int i = 0; i < button_array.length; i++)
      {
      button_array[i] = b;
      // root.getChildren().add(button_array[i]);       // Cannot be done, produces an error.
      }
    //*** The solution to this problem is that a GridPane must be constructed that holds the Button array in a grid
    //    structure. The constructed GridPane is then added in one step to the root below.
    //*** This class' private methods initBtnsArray() and getGridPane() are used together to return a GridPane instance.

    initBtnsArray();  // The private method in this class that stores an array of
                      // Buttons in this class' private variable 'buttons'.
    root.getChildren().add( getGridPane() );    // getGridPane() returns a GridPane using this
                                                // FXGUI.ButtonsAndGridPane's private variable 'buttons' that is first
                                                // initialized by the initBtnsArray() method.

// 3. Add components to the root
    //*** Adding a generic Button called 'b' to the root Pane.
    // root.getChildren().add(b);
    
// 4. Configure the components (colors, fonts, size, location)
// 5. Add Event Handlers and do final setup
// 6. Show the stage
    stage.show();
    }
  }
