/* Assignment
Question Two
Investment Value Calculator
Software System Development and Integration
CSCI 2020U
March 05, 2020
Keshopan Arunthavachelvan */

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.text.DecimalFormat;

public class QuestionTwo extends Application {

    // Allows execution of program
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Instantiates Grid with padding and spacing
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(10,10,10,10));
        pane.setVgap(5);
        pane.setHgap(5);

        // Instantiates the TextFields used in the form
        TextField investmentAmount = new TextField();
        investmentAmount.setAlignment(Pos.CENTER_RIGHT);
        TextField years = new TextField();
        years.setAlignment(Pos.CENTER_RIGHT);
        TextField annualInterestRate = new TextField();
        annualInterestRate.setAlignment(Pos.CENTER_RIGHT);
        TextField futureValue = new TextField();
        futureValue.setAlignment(Pos.CENTER_RIGHT);

        // Turns Future Value Text Field into a non editable field
        futureValue.setEditable(false);
        futureValue.setStyle("-fx-background-color: lightgrey");

        // Button used to calculate the Future Value using the provided values in the form text fields
        Button calculate = new Button("Calculate");
        calculate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Assign formatting for Decimal Value
                DecimalFormat df = new DecimalFormat("###.##");
                // Calculates the compounded interest using the provided values in the form text fields
                Double compoundedInterest = Math.pow(1+(Double.parseDouble(annualInterestRate.getText())/100/12),
                        (Double.parseDouble(years.getText())*12));
                // The compounded interest is used to calculate the future value which is then assigned the the Future
                // Value TextField. Future Value is rounded to two decimal places.
                futureValue.setText((df.format(Double.parseDouble(investmentAmount.getText()) *
                        compoundedInterest)));
            }
        });

        // Appends the TextField Form values and the button the the Grid pane
        pane.add(new Label("Investment Amount"),0,0);
        pane.add(investmentAmount, 1,0);

        pane.add(new Label("Years"),0,1);
        pane.add(years, 1,1);

        pane.add(new Label("Annual Interest Rate"), 0,2);
        pane.add(annualInterestRate, 1,2);

        pane.add(new Label("Future Value"), 0 , 3);
        pane.add(futureValue, 1 ,3);

        pane.add(calculate, 1,4);
        GridPane.setHalignment(calculate, HPos.RIGHT);

        // Displays the Investment Calculator GridPane on the main window
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Question Two: Investment-Value Calculator");
        primaryStage.show();
    }
}