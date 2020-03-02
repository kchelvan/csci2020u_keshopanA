/* Assignment
Question Four
Histogram
Software System Development and Integration
CSCI 2020U
March 05, 2020
Keshopan Arunthavachelvan */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class QuestionFour extends Application {
    // Global Variable Declaration
    CategoryAxis xAxis = new CategoryAxis();
    NumberAxis yAxis = new NumberAxis();
    BarChart <String,Number> histogram = new BarChart<>(xAxis, yAxis);

    Button viewButton = new Button("View");
    // Example filename formats include
    // "src/resources/text/Test.txt"
    // "src/resources/text/Test2.txt"
    Label labelFilename = new Label("Filename");
    TextField filename = new TextField();

    Map<Character, Integer> occurrences = new HashMap<>();
    VBox box = new VBox();
    HBox footer = new HBox(labelFilename, filename, viewButton);

    // Allows executions of program in the command line
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Sets styling for histogram
        histogram.setBarGap(0);
        histogram.setMinWidth(720);
        histogram.setAnimated(false);
        histogram.setLegendVisible(false);
        labelFilename.setStyle("-fx-min-width: 70px");

        // Creates an HBox with the histogram on the top-most layer, and a footer on the bottom-most layer
        box.getChildren().addAll(histogram, footer);

        // Event handler to generate histogram using view button and enter key in TextField
        viewButton.setOnAction(e -> search());
        filename.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                search();
            }
        });

        // Displays the histogram on the main Pane window
        Scene scene = new Scene(box);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void search() {
        // Resets number of occurrences from the previous file
        occurrences.clear();
        // Calculates the number of occurrences of each letter in the user's file and generates a new histogram
        // with the updated result
        calculateOccurrences();
        generateHistogram();
        box.getChildren().clear();
        box.getChildren().addAll(histogram, footer);
    }

    public void calculateOccurrences() {
        // Opens file with user's filename for reading
        File file = new File(filename.getText());

        try {
            // Iterates through each character in the file and calculates the number of occurrences for each
            // alphabetical letter into a hashmap
            Scanner scanner = new Scanner(file);
            scanner.useDelimiter("");

            while (scanner.hasNext()) {
                Character curr = scanner.next().charAt(0);
                if (Character.isLetter(curr)) {
                    // Characters are changed to uppercase to ignore case sensitivity
                    if (occurrences.containsKey(Character.toUpperCase(curr))) {
                        // If the current letter already exists in the hashmap, increase it's counter by one
                        occurrences.replace(
                                Character.toUpperCase(curr),
                                occurrences.get(Character.toUpperCase(curr)) + 1
                        );
                    }
                    // If the current character does not exist in the current hashmap, add it to the hashmap with a
                    // value of 1
                    else {
                        occurrences.put(Character.toUpperCase(curr), 1);
                    }
                }
            }
        }
        // If the input file does not exist, print a statement to the user indicating that the file does not exist
        catch (FileNotFoundException e) {
            System.out.println("File does not exist.");
        }
    }

    public void generateHistogram() {
        // Variable Declaration
        ArrayList<Character> alphabet = new ArrayList<>(Arrays.asList(
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
                'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'));
        XYChart.Series histogramData = new XYChart.Series();

        // iterate through each element in the alphabet and create a column with the number of occurrences that the
        // current character occurs in the selected file
        for (Character key : alphabet) {
            if (occurrences.containsKey(key)) {
                histogramData.getData().add(new XYChart.Data(key.toString(), occurrences.get(key)));
            }
            else {
                // If current character doesn't exist in the hashmap, initialize it with a value of 0
                histogramData.getData().add(new XYChart.Data(key.toString(), 0));
            }
        }

        // update the histogram with the updated number of occurrences for each letter
        histogram.getData().clear();
        histogram.getData().addAll(histogramData);
    }
}
