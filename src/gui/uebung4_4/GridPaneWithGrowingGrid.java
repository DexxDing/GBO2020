package gui.uebung4_4;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

/**
 * 
 * @author bennet
 *
 */
public class GridPaneWithGrowingGrid extends Application
{
    private GridPane grid;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        grid = new GridPane();
        addToContainer();
        grid.setGridLinesVisible(true);
        primaryStage.setScene(new Scene(grid));
        primaryStage.setTitle("GridPane with expandable Grid");
        primaryStage.show();
    }

    public void addToContainer()
    {
        for (int i = 0; i < 11; i++)
        {
            for (int j = 0; j < 11; j++)
            {
                Button b = new Button("Button " + i + "" + j);
                GridPane.setHgrow(b, Priority.ALWAYS); // hier setzen wir die
                                                       // Grow Strategie vom
                                                       // GridPane horizontal
                GridPane.setVgrow(b, Priority.ALWAYS); // hier setzen wir die
                                                       // Grow Strategie vom
                                                       // GridPane vertikal
                if (i % 2 == 0)
                {
                    GridPane.setHalignment(b, HPos.RIGHT);
                    GridPane.setValignment(b, VPos.BOTTOM);
                }
                else if (i % 2 + 1 == 0)
                {
                    GridPane.setValignment(b, VPos.TOP);
                    GridPane.setHalignment(b, HPos.LEFT);
                }
                grid.add(b, i, j);
            }
        }
    }

    public static void main(String[] args)
    {
        launch(args);
    }

}
