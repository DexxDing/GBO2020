package gui.Stichpunkte2;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class TilePaneExample extends Application
{

    private TilePane tp;

    private Label l1, l2;

    private CheckBox orientation = new CheckBox("Vertical Orientation");

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        tp = new TilePane();
        orientation.selectedProperty().addListener((ov, o, n) ->
        {
            if (n)
            {
                tp.setOrientation(Orientation.VERTICAL);
            }
            else
            {
                tp.setOrientation(Orientation.HORIZONTAL);
            }
        });
        l1 = new Label("Hallo");
        l2 = new Label("Welt");
        tp.getChildren().addAll(orientation, l1, l2);
        primaryStage.setTitle("TilePane");
        primaryStage.setScene(new Scene(tp, 300, 200));
        primaryStage.show();

    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
