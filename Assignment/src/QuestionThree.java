/* Assignment
Question Three
Dragging Points on a Circle
Software System Development and Integration
CSCI 2020U
March 05, 2020
Keshopan Arunthavachelvan */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.shape.Line;
import javafx.geometry.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuestionThree extends Application {

    // Global Variables
    Random randInt = new Random();

    // Global Variables for Shapes
    List<Circle> vertex = new ArrayList<>();
    List<Text> degree = new ArrayList<>();
    List<Line> line = new ArrayList<>();
    List<Double> angle = new ArrayList<>();

    // Allows executions of program in the command line
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Instantiates the Global Variables
        for (int i = 0; i < 3; i++) {
            degree.add(new Text());
            line.add(new Line());
            angle.add(0.0);

            // Positions new vertex on a random point on the perimeter of the main circle
            Circle newPoint = new Circle(225, 125, 5);
            Integer randDegree =  randInt.nextInt(360);
            Rotate rotate = new Rotate(randDegree, 125, 125);

            newPoint.getTransforms().add(rotate);
            Double x = newPoint.localToParent(newPoint.getCenterX(), newPoint.getCenterY()).getX();
            Double y = newPoint.localToParent(newPoint.getCenterX(), newPoint.getCenterY()).getY();
            newPoint.setCenterX(x);
            newPoint.setCenterY(y);
            newPoint.getTransforms().clear();

            vertex.add(newPoint);
        }

        // Instantiates Pane
        Pane pane = new Pane();

        // Sets size for main central circle
        Circle mainCircle = new Circle(125, 125, 100);
        mainCircle.setStyle("-fx-stroke: black; -fx-fill: white");

        // Appends main circle and initial points to the window pane
        pane.getChildren().add(mainCircle);
        for (int i = 0; i < 3; i++) {
            pane.getChildren().addAll(vertex.get(i), degree.get(i), line.get(i));
        }

        // Dynamically computes the angles between each three vertices in the triangle
        measureDegree();

        // Assign coordinates of each vertex as it is moved
        for (int i = 0; i < 3; i++) {
            int temp = i;
            vertex.get(temp).setOnMouseDragged(e -> {
                if (vertex.get(temp).contains(e.getX(), e.getY())) {
                    vertex.get(temp).setCenterX(e.getX());
                    vertex.get(temp).setCenterY(e.getY());
                    // Adjusts the degree as the vertex is moved
                    measureDegree();
                }
            });
        }

        // Displays the vertices and main circle on the main pane window
        Scene scene = new Scene(pane, 250, 250);
        primaryStage.setTitle("Question Three: Dragging Points on a Circle");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public int calculateDirection() {
        if (randInt.nextInt(2) == 1) { return 1; }
        else { return -1; }
    }

    public void drawLine() {
        // Draws the lines between each vertex
        line.get(0).setStartX(vertex.get(0).getCenterX());
        line.get(0).setStartY(vertex.get(0).getCenterY());
        line.get(0).setEndX(vertex.get(1).getCenterX());
        line.get(0).setEndY(vertex.get(1).getCenterY());

        line.get(1).setStartX(vertex.get(0).getCenterX());
        line.get(1).setStartY(vertex.get(0).getCenterY());
        line.get(1).setEndX(vertex.get(2).getCenterX());
        line.get(1).setEndY(vertex.get(2).getCenterY());

        line.get(2).setStartX(vertex.get(1).getCenterX());
        line.get(2).setStartY(vertex.get(1).getCenterY());
        line.get(2).setEndX(vertex.get(2).getCenterX());
        line.get(2).setEndY(vertex.get(2).getCenterY());
    }

    private void measureDegree() {
        // Computes lengths between three vertices
        double a = new Point2D(
                vertex.get(2).getCenterX(),
                vertex.get(2).getCenterY()).distance(
                    vertex.get(1).getCenterX(),
                    vertex.get(1).getCenterY()
                );

        double b = new Point2D(
                vertex.get(2).getCenterX(),
                vertex.get(2).getCenterY()).distance(
                    vertex.get(0).getCenterX(),
                    vertex.get(0).getCenterY());

        double c = new Point2D(
                vertex.get(1).getCenterX(),
                vertex.get(1).getCenterY()).distance(
                    vertex.get(0).getCenterX(),
                    vertex.get(0).getCenterY());

        // Calculates the degrees between the three vertices using cosine law
        angle.set(0, Math.acos((a * a - b * b - c * c) / (-2 * b * c)));
        angle.set(1, Math.acos((b * b - a * a - c * c) / (-2 * a * c)));
        angle.set(2, Math.acos((c * c - b * b - a * a) / (-2 * a * b)));

        // Displays the degree beside it's respective vertex
        for (int i = 0; i < 3; i++) {
            degree.get(i).setX(vertex.get(i).getCenterX() + 8);
            degree.get(i).setY(vertex.get(i).getCenterY());
            degree.get(i).setText(Double.toString((int)(Math.toDegrees(angle.get(i)))));
        }

        // Updates the lines as the vertices move
        drawLine();
    }
}

// MAKE SURE THE POINTS APPEAR ONLY ON THE PERIMETER OF THE CIRCLE
// MAKE SURE THE POINTS ARE RANDOMLY ASSIGNED ON THE PERIMETER OF THE CIRCLE WHEN THE PROGRAM OPENS