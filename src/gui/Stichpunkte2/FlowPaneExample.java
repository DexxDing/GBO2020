package gui.Stichpunkte2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * 
 * @author bennet
 *
 *         Beim Flow Pane passt der Inhalt sich automatisch an die Fenstergröße
 *         an und wird bei Bedarf in die nächste Zeile umgebrochen.
 */
public class FlowPaneExample extends Application
{

    private FlowPane fp;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        fp = new FlowPane();
        for (int i = 0; i <= 100; i++)
        {
            fp.getChildren().add(new Text(" Hallo Welt "));
        }
        primaryStage.setTitle("FlowPane");
        primaryStage.setScene(new Scene(fp, 300, 300));
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
