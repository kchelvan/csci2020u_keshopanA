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
        idCol.setCellValueFactory(new PropertyValueFactory<>("sid"));
        idCol.setPrefWidth(100);

        TableColumn<StudentRecord, Float > assignmentsCol = new TableColumn<>("Assignments");
        assignmentsCol.setCellValueFactory(new PropertyValueFactory<>("assignments"));
        assignmentsCol.setPrefWidth(100);

        TableColumn<StudentRecord, Float > midtermCol = new TableColumn<>("Midterm");
        midtermCol.setCellValueFactory(new PropertyValueFactory<>("midterm"));
        midtermCol.setPrefWidth(100);

        TableColumn<StudentRecord, Float > finalExamCol = new TableColumn<>("Final Exam");
        finalExamCol.setCellValueFactory(new PropertyValueFactory<>("finalExam"));
        finalExamCol.setPrefWidth(100);

        TableColumn<StudentRecord, Float > finalMarkCol = new TableColumn<>("Final Mark");
        finalMarkCol.setCellValueFactory(new PropertyValueFactory<>("finalMark"));
        finalMarkCol.setPrefWidth(100);

        TableColumn<StudentRecord, String > finalLetterGradeCol = new TableColumn<>("Letter Grade");
        finalLetterGradeCol.setCellValueFactory(new PropertyValueFactory<>("letterGrade"));
        finalLetterGradeCol.setPrefWidth(100);

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