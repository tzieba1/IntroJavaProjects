package FXGUI.CircleManager;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author sam.scott1
 */
public class CircleManager extends Application {

    // Instance Variables for View Components and Model
    private Circle4 model;
    private GraphicsContext gc;
    private Label output;
    private TextField radiusField;
    private TextField xField;
    private TextField yField;

    private void setRadHandler(ActionEvent e) {
        double newRad = Double.parseDouble(radiusField.getText());
        model.setRadius(newRad);
        drawCircle();
        radiusField.requestFocus();
    }

    private void setLocHandler(ActionEvent e) {
        double newX = Double.parseDouble(xField.getText());
        double newY = Double.parseDouble(yField.getText());
        model.setLocation(newX, newY);
        drawCircle();
        xField.requestFocus();
    }

    private void drawCircle() {
        gc.setFill(Color.LIGHTBLUE);
        gc.fillRect(0, 0, 320, 200);
        model.draw(gc);
        output.setText("" + model);
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
        Scene scene = new Scene(root, 320, 400); // set the size here
        stage.setTitle("Circle!"); // set the window title here
        stage.setScene(scene);

        // 1. Create the model
        model = new Circle4(50, 80, 50);

        // 2. Create the FXGUI components
        Canvas canvas = new Canvas(320, 200);
        xField = new TextField("80");
        yField = new TextField("50");
        radiusField = new TextField("50");
        Button setLocButton = new Button("Set Location");
        Button setRadButton = new Button("Set Radius");
        output = new Label("");
        output.setFont(new Font("System", 18));

        // 3. Add components to the root
        root.getChildren().addAll(canvas, radiusField, setRadButton,
                                  output, xField, yField, setLocButton);

        // 4. Configure the components (colors, fonts, size, location)
        xField.relocate(0, 210);
        yField.relocate(0, 240);
        setLocButton.relocate(0, 270);
        radiusField.relocate(0, 300);
        setRadButton.relocate(0, 330);
        output.relocate(0, 360);

        // 5. Add Listeners and do final setup
        setRadButton.setOnAction(this::setRadHandler);
        setLocButton.setOnAction(this::setLocHandler);
        gc = canvas.getGraphicsContext2D();
        drawCircle();

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
