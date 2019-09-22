package FXGUI.StopWatch;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * @author sam.scott1
 */
public class StopWatchApp extends Application
{

  // TODO: Instance Variables for View Components and Model
  StopWatch sw;
  Label display;
  Button startButton;
  Button lapButton;
  Button stopButton;

  // TODO: Private Event Handlers and Helper Methods

  private void setStartHandler(ActionEvent e)
  {
    sw.start();
    display.setStyle("-fx-background-color: lightblue; -fx-text-fill: green; -fx-alignment: center;");
    display.setText("0.000");
  }

  private void setLapHandler(ActionEvent e)
  {
    sw.stop();
    display.setStyle("-fx-background-color: lightblue; -fx-text-fill: green; -fx-alignment: center;");
    display.setText(String.format("%.3f", sw.getElapsedTime() / 1000.0));
  }

  private void setStopHandler(ActionEvent e)
  {
    sw.stop();
    display.setStyle("-fx-background-color: lightblue; -fx-text-fill: red; -fx-alignment: center;");
    display.setText("0.000");
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
    Scene scene = new Scene(root, 330, 80); // set the size here
    stage.setTitle("FX FXGUI Template"); // set the window title here
    stage.setScene(scene);
    // TODO: Add your FXGUI-building code here

    // 1. Create the model

    sw = new StopWatch();

    // 2. Create the FXGUI components

    Canvas canvas = new Canvas(330, 80);
    display = new Label("0.000");
    startButton = new Button("START");
    lapButton = new Button("LAP");
    stopButton = new Button("STOP");

    // 3. Add components to the root

    root.getChildren().addAll(canvas, display, startButton, lapButton, stopButton);

    // 4. Configure the components (colors, fonts, size, location)

    display.setPrefWidth(320);
    display.setFont(new Font("System", 30));
    display.setStyle("-fx-background-color: lightblue; -fx-text-fill: red; -fx-alignment: center;");
    display.relocate(5, 0);

    startButton.relocate(5, 50);
    startButton.setPrefWidth(100);
    lapButton.relocate(115, 50);
    lapButton.setPrefWidth(100);
    stopButton.relocate(225,50);
    stopButton.setPrefWidth(100);

    // 5. Add Event Handlers and do final setup

    startButton.setOnAction(this::setStartHandler);
    lapButton.setOnAction(this::setLapHandler);
    stopButton.setOnAction(this::setStopHandler);

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
