package gui.uebung4_2;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ExpandableButtons extends Application
{
    private VBox root;

    private ArrayList<Button> bList = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        root = new VBox(10);
        initButtons();
        for (Button b : bList)
        {
            root.getChildren().add(b);
            if (b.getText().equals("Button 1"))
            {
                b.setMaxWidth(Double.MAX_VALUE); // die Maximale breite auf
                                                 // Double.MAX_VALUE
                VBox.setVgrow(b, Priority.ALWAYS); // dann vom Parent Container
                                                   // der Buttons die statische
                                                   // Methode
                                                   // VBox.setVgrow(Priority.ALWAY)
                                                   // setzen,
                                                   // Wachstums-strategie
            }
            else if (b.getText().equals("Button 2"))
            {
                b.setMaxHeight(Double.MAX_VALUE);
                root.setAlignment(Pos.CENTER_RIGHT);
                VBox.setVgrow(b, Priority.ALWAYS);
            }
            else if (b.getText().equals("Button 4"))
            {
                b.setMaxHeight(Double.MAX_VALUE);
                b.setMaxWidth(Double.MAX_VALUE);
                root.setAlignment(Pos.CENTER_RIGHT); // immer den Parent fürs
                                                     // Alignment der childs
                                                     // nutzen
                VBox.setVgrow(b, Priority.ALWAYS);
            }
        }
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("VBox Example");
        primaryStage.show();
    }

    public void initButtons()
    {
        for (int i = 1; i <= 5; i++)
        {
            bList.add(new Button("Button " + i));
        }
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
