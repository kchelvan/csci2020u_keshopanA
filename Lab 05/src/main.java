import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class main extends Application {
    private TableView<StudentRecord> students;


    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Lab 05");

        TableColumn<StudentRecord, String> idCol = new TableColumn<>("SID");
        idCol.setPrefWidth(100);
        idCol.setCellValueFactory(new PropertyValueFactory<>("sid"));

        TableColumn<StudentRecord, Float > assignmentsCol = new TableColumn<>("Assignments");
        assignmentsCol.setPrefWidth(100);
        assignmentsCol.setCellValueFactory(new PropertyValueFactory<>("assignments"));

        TableColumn<StudentRecord, Float > midtermCol = new TableColumn<>("Midterm");
        midtermCol.setPrefWidth(100);
        midtermCol.setCellValueFactory(new PropertyValueFactory<>("midterm"));

        TableColumn<StudentRecord, Float > finalExamCol = new TableColumn<>("Final Exam");
        finalExamCol.setPrefWidth(100);
        finalExamCol.setCellValueFactory(new PropertyValueFactory<>("finalExam"));

        TableColumn<StudentRecord, Float > finalMarkCol = new TableColumn<>("Final Mark");
        finalMarkCol.setPrefWidth(100);
        finalMarkCol.setCellValueFactory(new PropertyValueFactory<>("finalMark"));

        TableColumn<StudentRecord, String > finalLetterGradeCol = new TableColumn<>("Letter Grade");
        finalLetterGradeCol.setPrefWidth(100);
        finalLetterGradeCol.setCellValueFactory(new PropertyValueFactory<>("letterGrade"));

        students = new TableView<>();
        students.getColumns().add(idCol);
        students.getColumns().add(assignmentsCol);
        students.getColumns().add(midtermCol);
        students.getColumns().add(finalExamCol);
        students.getColumns().add(finalMarkCol);
        students.getColumns().add(finalLetterGradeCol);

        primaryStage.setScene(new Scene(students, 605, 270));
        primaryStage.show();

        students.setItems(DataSource.getAllMarks());
    }


    public static void main(String[] args) {
        launch(args);
    }
}