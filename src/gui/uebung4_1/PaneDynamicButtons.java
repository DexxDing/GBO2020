package gui.uebung4_1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class PaneDynamicButtons extends Application
{
    Pane root = new Pane();

    Button obenLinks;

    Button untenRechts;

    public void start(Stage primaryStage)
    {
        untenRechts = new Button("Last");
        obenLinks = new Button("First");
        untenRechts.setLayoutX(200 - untenRechts.getWidth());
        untenRechts.setLayoutY(250 - untenRechts.getHeight());
        obenLinks.setLayoutX(0.0);
        obenLinks.setLayoutY(0.0);
        root.getChildren().addAll(untenRechts, obenLinks);
        middleButtons();
        handleWidthChange(untenRechts);
        handleHeightChange(untenRechts);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Pane-Beispiel");
        primaryStage.show();
    }

    public void middleButtons()
    {
        for (int i = 1; i <= 10; i++)
        {
            int index = i;
            Button b = new Button("Button " + i);
            b.setLayoutX((i - 1) * 20);
            b.setLayoutY((i - 1) * 20);
            root.getChildren().add(b);
            untenRechts.layoutXProperty().addListener((ov, o, n) ->
            {
                b.setLayoutX(((untenRechts.getLayoutX() - (b.getWidth() / 1.2)) - (index - 1) * 20));
            });
            untenRechts.layoutYProperty().addListener((ov, o, n) ->
            {
                b.setLayoutY(((untenRechts.getLayoutY() - (b.getHeight() / 2)) - (index - 1) * 20));
            });
        }
    }

    public void handleWidthChange(Button last)
    {
        root.widthProperty().addListener((ov, o, n) ->
        {
            System.out.println("width: " + n);
            if (n != o && (double) o != 0.0)
            {
                last.setLayoutX((double) n - last.getWidth());
            }
            else
            {
                last.setLayoutX(200 - last.getWidth());
            }
        });
    }

    public void handleHeightChange(Button last)
    {
        root.heightProperty().addListener((ov, o, n) ->
        {
            System.out.println("height: " + n);
            if (n != o && (double) o != 0.0)
            {
                last.setLayoutY((double) n - last.getHeight());
            }
            else
            {
                last.setLayoutY(250 - last.getHeight());
            }
        });
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
