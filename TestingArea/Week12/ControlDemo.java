package Week12;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;


/**
 * Example of using Java FX Controls and a Canvas together
 * COMP-10062 Demo - mark.yendt@mohawkcollege.ca
 */

public class ControlDemo extends Application {

    // Layout sizes
    final double SCREEN_WIDTH = 1000;
    final double SCREEN_HEIGHT = 800;
    final double CONTROL_HEIGHT = 100;

    private GraphicsContext gc, transgc;
    private double sx, sy;
    private double ex, ey;

    private Color lineColor = Color.BLUE;
    private ColorPicker lineColorPicker;
    
    //Reference to the Model
    private ArrayList<Line> lines;
    
    // TODO: Private Event Handlers and Helper Methods
    private void pressHandler(MouseEvent me) {
        sx = me.getX();
        sy = me.getY();
    }

    private void releaseHandler(MouseEvent me) {
        ex = me.getX();
        ey = me.getY();
        
        Line l = new Line(sx, sy, ex, ey, lineColorPicker.getValue());
        lines.add(l);
        gc.setFill(Color.LIGHTYELLOW);
        gc.fillRect(0,0,SCREEN_WIDTH, SCREEN_HEIGHT - CONTROL_HEIGHT);
        for (Line l1 : lines)
            l1.draw(gc);
        transgc.clearRect(0,0,SCREEN_WIDTH, SCREEN_HEIGHT);
    }

    /**
     * This will draw a "Rubberband" line on the transparent surface above the drawing.
     *
     * @param me The mouse drag event - not used in method
     */
    private void dragHandler(MouseEvent me) {
        transgc.clearRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        ex = me.getX();
        ey = me.getY();
        Line tempLine = new Line(sx,sy,ex,ey,lineColorPicker.getValue());
        tempLine.draw(transgc);
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
        Scene scene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT, Color.DARKGRAY); // set the size here
        stage.setTitle("Drawing and Java FX Controls"); // set the window title here
        stage.setScene(scene);
        // TODO: Add your GUI-building code here

        // 1. Create the model - No model yet
        lines = new ArrayList<>();

        // 2. Create the GUI components - Just a CANVAS for now

        Canvas c = new Canvas(SCREEN_WIDTH, SCREEN_HEIGHT - CONTROL_HEIGHT);
        c.relocate(0, CONTROL_HEIGHT);
        Canvas transc = new Canvas(SCREEN_WIDTH, SCREEN_HEIGHT);
        transc.relocate(0, CONTROL_HEIGHT);

        // Add JavaFX controls to top pane ...
    
        Label colorLabel = new Label("Line Colour");
        colorLabel.relocate(20, 50);

        lineColorPicker = new ColorPicker(lineColor);
        lineColorPicker.relocate(100, 50);
        
        Button resetButton = new Button("Reset Line Colours");
        resetButton.relocate(250, 50);
        resetButton.setOnAction(this::resetHandler);
        
        

        // 3. Add components to the root
        root.getChildren().addAll(c, transc, colorLabel, lineColorPicker, resetButton);

        // Create the two graphics contexts

        gc = c.getGraphicsContext2D();
        transgc = transc.getGraphicsContext2D();

        gc.setFill(Color.LIGHTYELLOW);
        gc.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        // 5. Add Event Handlers and do final setup
        transc.addEventHandler(MouseEvent.MOUSE_PRESSED, this::pressHandler);
        transc.addEventHandler(MouseEvent.MOUSE_RELEASED, this::releaseHandler);
        transc.addEventHandler(MouseEvent.MOUSE_DRAGGED, this::dragHandler);
        // 6. Show the stage
        stage.show();
    }

private void resetHandler(ActionEvent actionEvent)
    {
    gc.setFill(Color.LIGHTYELLOW);
    gc.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
    for (Line l : lines)
        {
        l.setLineColor(lineColorPicker.getValue());
        l.draw(gc);
        }
    }

/**
     * Make no changes here.
     * @param args unused
     */
    public static void main(String[] args) {
        launch(args);
    }
}
