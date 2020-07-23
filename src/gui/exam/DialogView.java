package gui.exam;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * DialogPane braucht eine Pane damit es ordentlich angezeigt wird. also
 * DialogPane -> Pane -> VBox -> HBox1 -> HBox2 -> HBox3
 * 
 * @author bennet
 *
 */
public class DialogView extends DialogPane
{
    private Stage dialog;

    private Label status, teams, ergebnis;

    private VBox vBox;

    private HBox h1, h2, h3;

    private Button hinzufuegen, abbrechen;

    private TextField teamA, teamB, scoreA, scoreB;

    private Presenter presenter;

    public void setPresenter(Presenter p)
    {
        this.presenter = p;
    }

    public DialogView()
    {
        status = new Label();
        dialog = new Stage();
        this.setMinSize(400, 400);
        Scene scene = new Scene(this);
        initDialog();
        dialog.setScene(scene);
        dialog.initModality(Modality.APPLICATION_MODAL);
        handleClose();
        handleAddMatch();
    }

    public void initDialog()
    {
        Pane pane = new Pane();
        hinzufuegen = new Button("Hinzufügen");
        abbrechen = new Button("Abbrechen");
        vBox = new VBox(10);
        teams = new Label("Teams: ");
        ergebnis = new Label("Ergebnis: ");
        status = new Label();
        teamA = new TextField();
        teamB = new TextField();
        scoreA = new TextField();
        scoreB = new TextField();

        h1 = new HBox(10);
        h1.getChildren().addAll(teams, teamA, new Label(" - "), teamB);
        h2 = new HBox(10);
        h2.getChildren().addAll(ergebnis, scoreA, new Label(" : "), scoreB);
        h3 = new HBox(10);
        h3.getChildren().addAll(hinzufuegen, abbrechen);
        vBox.getChildren().addAll(h1, h2, h3, status);
        pane.getChildren().add(vBox);
        this.getChildren().add(pane);
    }

    public void handleClose()
    {
        abbrechen.setOnAction(EventHandler ->
        {
            dialog.close();
        });
    }

    public void showStage()
    {
        dialog.show();
    }

    public void handleAddMatch()
    {
        hinzufuegen.setOnAction(EventHandler ->
        {

            if (scoreA.getText().matches("[0-9]*") && scoreB.getText().matches("[0-9]*") && !teamA.getText().isEmpty() && !teamB.getText().isEmpty() && !scoreA.getText().isEmpty() && !scoreB.getText().isEmpty() && !teamA.getText().equals(teamB.getText()))
            {
                String teamHome = teamA.getText();
                String teamGuest = teamB.getText();
                Integer scoreHeim = Integer.valueOf(scoreA.getText());
                Integer scoreGast = Integer.valueOf(scoreB.getText());

                Match match = new Match(teamHome, teamGuest, scoreHeim, scoreGast);
                this.presenter.addNewMatch(match);
                status.setText("Ergebnis: Erfolgreiche Eingabe");
                clearInputFields();
            }
            else
            {
                status.setText("Ergebnis: Fehlerhafte Eingabe");
            }
        });
    }

    public void clearInputFields()
    {
        teamA.setText("");
        teamB.setText("");
        scoreA.setText("");
        scoreB.setText("");
    }
}
