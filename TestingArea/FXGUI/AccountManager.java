

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
 *Creating left and right mouse click event listeners taken from: https://www.youtube.com/watch?reload=9&v=Pz_FdOdL7SQ.
 *
 *
 * @author Tommy Zieba
 */
public class AccountManager extends Application {

// TODO: Instance Variables for View Components and Model
Label display;
TextField balanceField;
Button deposit;
Button withdraw;

Double balance = 0.0;

// TODO: Private Event Handlers and Helper Methods
private void depositHandler(ActionEvent e)
  {
  String msg = "";
  double b = Double.parseDouble(balanceField.getText());
  balance += b;
  msg = "Balance: " + String.format("%.2f", balance);
  display.setText(msg);
  }

private void withdrawHandler(ActionEvent e)
  {
  String msg = "";
  double b = Double.parseDouble(balanceField.getText());
  if(balance >= b){balance -= b;}
  msg = "Balance: " + String.format("%.2f", balance);
  display.setText(msg);
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
Scene scene = new Scene(root, 500, 200); // set the size here
stage.setTitle("FX FXGUI Template"); // set the window title here
stage.setScene(scene);
// TODO: Add your FXGUI-building code here

// 1. Create the model
// 2. Create the FXGUI components
display = new Label("Balance: $0.00");

balanceField = new TextField("0.00");

deposit = new Button("Deposit");
withdraw = new Button("Withdraw");


// 3. Add components to the root
root.getChildren().addAll(display, balanceField, deposit, withdraw);

// 4. Configure the components (colors, fonts, size, location)
display.setPrefWidth(500);
display.setFont(new Font("System", 40));
display.setStyle("-fx-text-fill: darkblue;");
display.relocate(10, 0);

balanceField.relocate(10, 100);
deposit.relocate(200, 100);
withdraw.relocate(275, 100);

// 5. Add Event Handlers and do final setup

deposit.setOnAction(this::depositHandler);
withdraw.setOnAction(this::withdrawHandler);

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
