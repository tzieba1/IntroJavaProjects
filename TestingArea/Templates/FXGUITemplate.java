package Templates;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author sam.scott1
 */
public class FXGUITemplate extends Application {

// TODO: Instance Variables for View Components and Model
// TODO: Private Event Handlers and Helper Methods
/**
 * This is where you create your components and the model and add event
 * handlers.
 *
 * @param stage The main stage
 * @throws Exception
 */
@Override
public void start(Stage stage) throws Exception {
Pane root = new Pane();
Scene scene = new Scene(root, 400, 225); // set the size here
stage.setTitle("FX FXGUI Template"); // set the window title here
stage.setScene(scene);
// TODO: Add your FXGUI-building code here

// 1. Create the model
// 2. Create the FXGUI components
// 3. Add components to the root
// 4. Configure the components (colors, fonts, size, location)
// 5. Add Event Handlers and do final setup
// 6. Show the stage
stage.show();
}

/**
 * Make no changes here.
 *
 * @param args unused
 */
public static void main(String[] args) {
launch(args);
}
}
