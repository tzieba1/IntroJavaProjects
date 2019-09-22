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
public class MouseEventDemo extends Application {

    // TODO: Instance Variables for View Components and Model
    
    
    // TODO: Private Event Handlers and Helper Methods
    private void pressHandler(MouseEvent me) {
        System.out.println("Pressed " + me.getButton() + " at (" + me.getX() + "," + me.getY() + ").");
    }

    private void releaseHandler(MouseEvent me) {
        System.out.println("Released " + me.getButton() + " at (" + me.getX() + "," + me.getY() + ").");
    }

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
        stage.setTitle("Mouse Events"); // set the window title here
        stage.setScene(scene);
        // TODO: Add your GUI-building code here

        // 1. Create the model
        // 2. Create the GUI components
        Canvas c = new Canvas(400, 225);
        // 3. Add components to the root
        root.getChildren().add(c);
        // 4. Configure the components (colors, fonts, size, location)
        GraphicsContext gc = c.getGraphicsContext2D();
        gc.setFill(Color.LIGHTYELLOW);
        gc.fillRect(0, 0, 400, 225);
        // 5. Add Event Handlers and do final setup
        c.addEventHandler(MouseEvent.MOUSE_PRESSED, this::pressHandler);
        c.addEventHandler(MouseEvent.MOUSE_RELEASED, this::releaseHandler);
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
