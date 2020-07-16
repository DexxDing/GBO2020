package gui.calculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Calculator extends Application
{

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Pane root = FXMLLoader.load(getClass().getResource("CalculatorFXMLView.fxml"));
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Calculator");
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }

}
