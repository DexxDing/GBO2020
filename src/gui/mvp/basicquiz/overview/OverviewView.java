package gui.mvp.basicquiz.overview;

import gui.mvp.basicquiz.model.Question;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class OverviewView extends VBox
{

    private OverviewPresenter op;

    private Label overviewLabel = new Label("Übersicht");

    private ListView<String> overviewList; // ListViewQuiz Uebung08

    private TableView<Question> tableView; // Abgabe TableView

    private ObservableList<Question> itemsOL = FXCollections.observableArrayList();

    private Button deleteSolutionsBtn;

    public void setOverViewPresenter(OverviewPresenter op)
    {
        this.op = op;
    }

    public OverviewView()
    {
        initNodes();
        this.setPadding(new Insets(10));
        initTableView();
        this.getChildren().addAll(overviewLabel, overviewList, tableView, deleteSolutionsBtn);
        handleDelete();
    }

    public void initTableView()
    {
        tableView = new TableView<Question>();
        TableColumn<Question, String> questionColumn = new TableColumn<>("Frage");
        tableView.getColumns().add(questionColumn);
        TableColumn<Question, String> answerColumn = new TableColumn<>("Antwort");
        tableView.getColumns().add(answerColumn);
        questionColumn.setCellValueFactory(new PropertyValueFactory<>("question"));
    }

    public void initNodes()
    {
        overviewLabel = new Label("Übersicht");
        overviewList = new ListView<String>();
        overviewList.setId("overviewList");
        deleteSolutionsBtn = new Button("Ergebnisse löschen");
        deleteSolutionsBtn.setId("deleteHistory");
    }

    public void addListElement(String question, int answers, int rightAnswers)
    {
        overviewList.getItems().add(question + "(Antworten:" + answers + ", davon richtig: " + rightAnswers + ")");
    }

    public void setOverviewPresenter(OverviewPresenter op2)
    {
        this.op = op2;
    }

    public void clearContent()
    {
        this.overviewList.getItems().clear();
    }

    public void handleDelete()
    {
        deleteSolutionsBtn.setOnAction(e ->
        {
            clearContent();
        });
    }
}
