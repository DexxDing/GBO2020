package gui.uebung4_1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class PaneDynamicButtons extends Application
{
    Pane root = new Pane();

    public void start(Stage primaryStage)
    {
        Button untenRechts = new Button("Kill It");
        root.setLayoutX(200);
        root.setLayoutY(200);
        untenRechts.setLayoutX(200 - untenRechts.getWidth());
        untenRechts.setLayoutY(200 - untenRechts.getHeight());
        root.getChildren().addAll(untenRechts);
        handleWidthChange(untenRechts);
        handleHeightChange(untenRechts);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Pane-Beispiel");
        primaryStage.show();
    }

    public void handleWidthChange(Button last)
    {
        root.widthProperty().addListener((ov, o, n) ->
        {
            System.out.println("width: " + n);
            if (n != o)
            {
                last.setLayoutX((double) n - last.getWidth());
            }
            else
            {
                last.setLayoutX((double) o - last.getWidth());
            }
        });
    }

    public void handleHeightChange(Button last)
    {
        root.heightProperty().addListener((ov, o, n) ->
        {
            if (n != o)
            {
                last.setLayoutY((double) n - last.getHeight());
            }
            else
            {
                last.setLayoutY((double) o - last.getHeight());
            }
        });
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
