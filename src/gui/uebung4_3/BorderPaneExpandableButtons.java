package gui.uebung4_3;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * 
 * @author bennet anders als bei HBox / VBox müssen wir hier nicht expliziet
 *         eine grow priority angeben (VBox.setVGrow(Priority.ALWAS);)
 */
public class BorderPaneExpandableButtons extends Application
{

    private BorderPane root;

    private ArrayList<Button> btns = new ArrayList<>();

    @Override
    public void start(Stage stage) throws Exception
    {
        root = new BorderPane();
        initButtonList();
        setMaxWidthHeight();
        addNodesContainer();
        stage.setScene(new Scene(root));
        stage.setTitle("Expandable Buttons BorderPane");
        stage.show();
    }

    public void initButtonList()
    {
        for (int i = 0; i <= 5; i++)
        {
            btns.add(new Button("Button " + i));
        }
    }

    /**
     * sets to Double.MAX_VALUE from default preferred size
     */
    public void setMaxWidthHeight()
    {
        for (Button b : btns)
        {
            b.setMaxHeight(Double.MAX_VALUE);
            b.setMaxWidth(Double.MAX_VALUE);
        }
    }

    public void addNodesContainer()
    {
        root.setTop(btns.get(0));
        root.setCenter(btns.get(1));
        root.setLeft(btns.get(2));
        root.setRight(btns.get(3));
        root.setBottom(btns.get(4));
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
