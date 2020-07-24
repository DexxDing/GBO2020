package gui.exam;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

    private ListView<Match> matchList;

    private ListView<ScoreEntry> scoreList;

    private Button neuesSpielergebnis, deleteSpielerebnis;

    private ObservableList<gui.exam.ScoreEntry> observableListScore;

    private ObservableList<gui.exam.Match> observableMatchList;

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
        matchList.getItems().remove(match);
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
            presenter.deleteMatch(matchList.getSelectionModel().getSelectedItem());
        });
    }

    public void updateScore(ScoreEntry[] scoreEntries)
    {
        scoreList.getItems().clear();
        scoreList.getItems().addAll(scoreEntries);
    }
    
    public void updateMatches(Match[] match) {
    	matchList.getItems().clear();
    	matchList.getItems().addAll(match);
    }

    public void removeMatch()
    {
        if (matchList.getItems().size() > 0 && !matchList.getItems().isEmpty())
        {
            deleteMatch(matchList.getSelectionModel().getSelectedItem());
            presenter.updateAllMatches();
        }
    }

    public void setScoreList(ScoreEntry[] allScores)
    {
        observableListScore = FXCollections.observableArrayList(allScores);
    }

    public void setMatchList(Match[] allMatches)
    {
        observableMatchList = FXCollections.observableArrayList(allMatches);
    }

}
