package FXGUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * A simple hello world FXGUI.
 *
 * @author sam scott
 */
public class HelloWorldGUI extends Application {

    // TODO: Instance Variables for View Components and Model
    TextField nameField;
    TextField numField;
    Label output;

    // TODO: Private Event Handlers and Helper Methods
    private void sayHandler(ActionEvent e) {
        String msg = "";
        int n = Integer.parseInt(numField.getText());
        for (int i = 0; i < n; i++) {
            msg += "Hello, " + nameField.getText() + "! ";
        }
        output.setText(msg);
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
        Scene scene = new Scene(root, 600, 125); // set the size here
        stage.setTitle("Hello"); // set the window title here
        stage.setScene(scene);
        // TODO: Add your FXGUI-building code here

        // 1. Create the model
        // 2. Create the FXGUI components
        nameField = new TextField("World");
        numField = new TextField("1");
        Button sayButton = new Button("Say It!");
        output = new Label("---");

        // 3. Add components to the root
        root.getChildren().addAll(nameField, numField, sayButton, output);

        // 4. Configure the components (colors, fonts, size, location)
        nameField.relocate(0,35);
        numField.relocate(0,65);
        sayButton.relocate(0,95);
        output.setPrefWidth(600);
        output.setFont(new Font("System", 20));
        output.setStyle("-fx-background-color: lightblue;-fx-text-fill:darkblue;");

         // 5. Add Listeners and do final setup
        sayButton.setOnAction(this::sayHandler);

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
