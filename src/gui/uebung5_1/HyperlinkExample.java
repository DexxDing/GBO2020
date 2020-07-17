package gui.uebung5_1;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HyperlinkExample extends Application
{

    private VBox root;

    private Label countLabel = new Label("Klicks: " + count);

    private Hyperlink hyperLink;

    private static int count = 0;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        root = new VBox(10);
        initHyperLink("Dies ist ein ziemlich langer Hypertext mit ziemlich viel Text");
        root.getChildren().add(hyperLink);
        initChoice();
        root.getChildren().add(countLabel);
        primaryStage.setTitle("Hyperlink Examples");
        primaryStage.setScene(new Scene(root, 200, 300));
        primaryStage.show();
    }

    public void initHyperLink(String s)
    {
        hyperLink = new Hyperlink(s);
        hyperLink.setOnAction(e ->
        {
            count += 1;
            countLabel.setText("Klicks: ");
            countLabel.setText(countLabel.getText().concat(String.valueOf(count)));
        });
    }

    public void initChoice()
    {
        CheckBox visited = new CheckBox("Hyperlink Besucht");
        CheckBox underlined = new CheckBox("Hyperlink Unterstrichen");
        CheckBox lineBreak = new CheckBox("Hyperlink Zeilenumbruch");

        root.getChildren().addAll(visited, underlined, lineBreak);

        // inner anonymous class umständlich
        visited.selectedProperty().addListener(new ChangeListener<Boolean>()
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue)
            {
                if (newValue)
                {
                    hyperLink.setVisited(true);
                }
                else
                {
                    hyperLink.setVisited(false);
                }
            }
        });
        // kürzere Version mit Lambda Ausdruck SAM Type Method (functional
        // interface) mit genau einer abstrakten Methode
        underlined.selectedProperty().addListener((ov, o, n) ->
        {
            if (n)
            {
                hyperLink.setUnderline(true);
            }
            else
            {
                hyperLink.setUnderline(false);
            }
        });
        // Beste Variante sehr viel angenehmer mit
        // Bindings!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        hyperLink.wrapTextProperty().bind(lineBreak.selectedProperty());
    }

    public static void main(String[] args)
    {
        launch(args);
    }

}
