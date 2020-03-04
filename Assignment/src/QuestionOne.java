/* Assignment
Question One
Displaying Three Cards
Software System Development and Integration
CSCI 2020U
March 05, 2020
Keshopan Arunthavachelvan */

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuestionOne extends Application {

    // Allows executions of program in the command line
    public static void main(String[] args) { launch(args); }

    @Override
    public void start(Stage primaryStage) {
        // Variable Declaration
        Random rand = new Random();
        List<Integer> cards = new ArrayList<Integer>();

        // Randomly choose three numbers between 1 and 54 (inclusive)
        while (cards.size() < 3) {
            int randomNum = rand.nextInt(54) + 1;
            // If the randomly generated number does not already exist in the cards list, append the random number to
            // the cards array
            if (!cards.contains(randomNum)) {
                cards.add(randomNum);
            }
        }

        // Instantiates Pane with padding and spacing
        Pane pane = new HBox(10);
        pane.setPadding(new Insets(5,5,5,5));

        // Creates an image view using the first randomly generated number to select a card from the cards directory
        Image image1 = new Image("image/card/" + cards.get(0) + ".png");
        ImageView imageView1 = new ImageView(image1);
        pane.getChildren().add(imageView1);

        // Creates an image view using the second randomly generated number to select a card from the cards directory
        Image image2 = new Image("image/card/" + cards.get(1) + ".png");
        ImageView imageView2 = new ImageView(image2);
        pane.getChildren().add(imageView2);

        // Creates an image view using the third randomly generated number to select a card from the cards directory
        Image image3 = new Image("image/card/" + cards.get(2) + ".png");
        ImageView imageView3 = new ImageView(image3);
        pane.getChildren().add(imageView3);

        // Displays the three cards on the main Pane window
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Question One: Display Three Cards");
        primaryStage.show();
    }
}
