package gui.controls;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

public class BindingSliderProgress extends Application
{

    private GridPane root;

    private Slider controlSlider;

    private ProgressBar progressBar;

    private ProgressIndicator progressIndicator;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        initRoot();
        initSlider();
        initProgressIndicator();
        initProgressBar();
        addNodes();
        primaryStage.setTitle("Bindings-Beispiel: Slider und Progress");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void initSlider()
    {
        controlSlider = new Slider(0, 1, 0);
        controlSlider.setPadding(new Insets(10));
        GridPane.setHgrow(controlSlider, Priority.ALWAYS);
    }

    public void addNodes()
    {
        root.add(controlSlider, 0, 0);
        root.add(progressBar, 0, 1);
        root.add(progressIndicator, 0, 2);
    }

    public void initRoot()
    {
        root = new GridPane();
    }

    public void initProgressBar()
    {
        progressBar = new ProgressBar();
        progressBar.progressProperty().bind(controlSlider.valueProperty());
    }

    public void initProgressIndicator()
    {
        progressIndicator = new ProgressIndicator();
        progressIndicator.progressProperty().bind(controlSlider.valueProperty());
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
