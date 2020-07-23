package gui.exam;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class View extends VBox
{
    private Presenter presenter;

    private HBox h1, h2;

    private DialogView dView = new DialogView();

    ListView<Match> matchList;

    private ListView<ScoreEntry> scoreList;

    private Button neuesSpielergebnis, deleteSpielerebnis;

    public void setPresenter(Presenter p)
    {
        this.presenter = p;
    }

    public View()
    {
        initNodes();
    }

    public void addMatch(Match match)
    {
        matchList.getItems().add(match);
    }

    public void deleteMatch(Match match)
    {
        matchList.getItems().remove(matchList.getSelectionModel().getSelectedItem());
    }

    public void initNodes()
    {
        h1 = new HBox(20);
        h1.setPadding(new Insets(40));
        matchList = new ListView<Match>();
        h1.getChildren().add(matchList);

        scoreList = new ListView<ScoreEntry>();
        h1.getChildren().add(scoreList);
        this.getChildren().add(new HBox(h1));

        HBox buttonGrp = new HBox(10);
        buttonGrp.setPadding(new Insets(10));
        deleteSpielerebnis = new Button("Spielergebnis löschen");
        neuesSpielergebnis = new Button("Neues Spielergebnis");
        buttonGrp.getChildren().addAll(neuesSpielergebnis, deleteSpielerebnis);
        this.getChildren().add(buttonGrp);

        neuesSpielergebnis.setOnAction(e ->
        {
            dView.setPresenter(this.presenter);
            dView.showStage();
        });

        deleteSpielerebnis.setOnAction(EventHandler ->
        {
            deleteMatch(matchList.getSelectionModel().getSelectedItem());
        });
    }

    public void updateScore(ScoreEntry[] scoreEntries)
    {
        scoreList.getItems().clear();
        scoreList.getItems().addAll(scoreEntries);

    }

}
