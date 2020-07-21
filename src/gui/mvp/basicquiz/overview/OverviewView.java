package gui.mvp.basicquiz.overview;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

public class OverviewView extends VBox
{

    private OverviewPresenter op;

    private Label overviewLabel = new Label("Übersicht");

    private ListView<String> overviewList;

    private Button deleteSolutionsBtn;

    public void setOverViewPresenter(OverviewPresenter op)
    {
        this.op = op;
    }

    public OverviewView()
    {
        initNodes();
        this.setPadding(new Insets(10));
        this.getChildren().addAll(overviewLabel, overviewList, deleteSolutionsBtn);
        handleDelete();
    }

    public void initNodes()
    {
        overviewLabel = new Label("Übersicht");
        overviewList = new ListView<String>();
        deleteSolutionsBtn = new Button("Ergebnisse löschen");
    }

    public void addListElement(String question, int answers, int rightAnswers)
    {
        overviewList.getItems().add(question + "(Antworten:" + answers + ", davon richtig: " + rightAnswers);
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
