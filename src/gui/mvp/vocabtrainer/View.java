package gui.mvp.vocabtrainer;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class View extends GridPane
{

    private Presenter p;

    private Label fixLabel;

    private Label wort;

    private TextField inputField;

    private Button weiter, check;

    private Label statusL;

    public View()
    {
        this.setGridLinesVisible(true);
        initNodes();
        addNodes();
        GridPane.setHgrow(inputField, Priority.ALWAYS);
        weiter.setOnAction(e ->
        {
            p.setVocab();
            statusL.setText("");
            inputField.setText("");
        });
        check.setOnAction(e ->
        {
            System.out.println(inputField.getText());
            p.checkRightAnswer(inputField.getText());
        });
    }

    public void initNodes()
    {
        this.weiter = new Button("Weiter");
        this.check = new Button("Überprüfen");
        this.fixLabel = new Label("Bitte übersetzen Sie:");
        this.wort = new Label();
        this.inputField = new TextField();
        this.statusL = new Label();
    }

    public void addNodes()
    {
        this.add(fixLabel, 0, 0);
        this.fixLabel.setPadding(new Insets(5));
        this.add(wort, 0, 1);
        this.wort.setId("vocable");
        this.wort.setPadding(new Insets(5));
        this.add(inputField, 1, 1);
        this.inputField.setPadding(new Insets(5));
        this.inputField.setId("translation");
        this.add(check, 0, 2);
        this.check.setId("check");
        this.check.setPadding(new Insets(10));
        this.add(weiter, 1, 2);
        this.weiter.setId("next");
        this.weiter.setPadding(new Insets(10));
        this.add(statusL, 0, 3, 3, 1);
        this.statusL.setId("status");

    }

    public void setWord(String string)
    {
        this.wort.setText(string);
    }

    public void setPresenter(Presenter p2)
    {
        this.p = p2;
    }

    public void setRightAnswer()
    {
        statusL.setText("Die Lösung war richtig.");
        inputField.setText("");
    }

    public void setWrongAnswer()
    {
        statusL.setText("Die Lösung war falsch. Sie können es nochmal versuchen.");
    }

}
