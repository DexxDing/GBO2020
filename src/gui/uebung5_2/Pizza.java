package gui.uebung5_2;

import java.awt.TextArea;
import java.util.Map;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Pizza extends Application
{
    private GridPane root;

    private CheckBox pilze, käse, salami, tomaten, artischocken, spinat, ei,
                    knoblauch, zwiebel;

    private RadioButton klein, normal, groß;

    private Button bestellen;

    private TextArea display;

    private static Map<String, String> parameterMap = null;
    {
    };

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        root = new GridPane();
        parameterMap = getParameters().getNamed();
        ParameterConverter.createConfiguration(parameterMap);
        System.out.println();
        primaryStage.setTitle("Lieferando");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void initCheckBox()
    {
        käse = new CheckBox("Käse");
        tomaten = new CheckBox("Tomaten");
        tomaten.setSelected(true);
        salami = new CheckBox("Salami");
        pilze = new CheckBox("Pilze");
    }

    // zum Testen einfach die Kommando Zeilen Argumente übergeben
    public static void main(String[] args)
    {
        launch(args);
    }

}
