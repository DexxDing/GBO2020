package gui.controls;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

public class BindingSliderSlider extends Application
{
    private Slider s1, s2, s3, summeSlider;

    private GridPane root;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        initRoot();
        initSliders();
        addNodes();
        bindSummeSlider();
        primaryStage.setTitle("Binding-Beispiel: Addition von Slidern");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void initRoot()
    {
        root = new GridPane();

    }

    public void initSliders()
    {
        s1 = new Slider(0, 10, 0);
        s2 = new Slider(0, 10, 0);
        s3 = new Slider(0, 10, 0);

        summeSlider = new Slider(0, s1.getMax() + s2.getMax() + s3.getMax(), 0);
    }

    public void bindSummeSlider()
    {
        summeSlider.valueProperty().bind(s1.valueProperty().add(s2.valueProperty()).add(s3.valueProperty()));
    }

    public void addNodes()
    {
        root.add(s1, 0, 0);
        root.add(s2, 1, 0);
        root.add(s3, 2, 0);
        root.add(summeSlider, 0, 2, 3, 1);
        s1.setPadding(new Insets(10));
        s2.setPadding(new Insets(10));
        s3.setPadding(new Insets(10));
        summeSlider.setPadding(new Insets(10));
        GridPane.setHgrow(summeSlider, Priority.ALWAYS);
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
