package gui.Stichpunkte5;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class TableViewExample extends Application
{
    private TableView<Student> studentTable = new TableView<>();

    final ObservableList<Student> data = FXCollections.observableArrayList(new Student("Bennet", 13, 960597), new Student("Gwendoline Mirella Hertel", 3, 2222));

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        studentTable.getItems().addAll(data);
        TableColumn<Student, String> nameCol = new TableColumn("Name");
        TableColumn<Student, Integer> semesterCol = new TableColumn("Semester");
        TableColumn<Student, Integer> matnrCol = new TableColumn("Matnr");
        nameCol.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));
        semesterCol.setCellValueFactory(new PropertyValueFactory<Student, Integer>("semester"));
        matnrCol.setCellValueFactory(new PropertyValueFactory<Student, Integer>("matnr"));
        studentTable.getColumns().addAll(nameCol, semesterCol, matnrCol);
        primaryStage.setTitle("TableView Example");
        primaryStage.setScene(new Scene(studentTable));
        data.get(1).setMatnr(523452);
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }

}
