package gui.controls;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

public class SliderListener extends Application
{
    private Slider slider;

    private GridPane root;

    private Label label;

    private CheckBox showGrid;

    private CheckBox showOnlyInteger;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        initSlider();
        initContainer();
        addNodes();
        initLabel();
        onChange();
        initCheckBox();
        initShowInteger();
        primaryStage.setTitle("Slider mit Listener");
        primaryStage.setScene(new Scene(root, 450, 120));
        primaryStage.show();

    }

    public void initCheckBox()
    {
        showGrid = new CheckBox("show Grid");
        root.add(showGrid, 2, 0);
        root.gridLinesVisibleProperty().bind(showGrid.selectedProperty());
    }

    public void initContainer()
    {
        root = new GridPane();
        root.setGridLinesVisible(true);
        GridPane.setHgrow(slider, Priority.ALWAYS);
    }

    public void addNodes()
    {
        root.add(slider, 0, 1, 4, 1);
        GridPane.setFillWidth(slider, true);
        slider.setPadding(new Insets(10));
    }

    public void initShowInteger()
    {
        showOnlyInteger = new CheckBox("Show Integer value");
        root.add(showOnlyInteger, 3, 0);
        showOnlyInteger.selectedProperty().addListener((ov, o, n) ->
        {
            if (n)
            {
                label.setText("Änderung des Sliders um " + ((int) slider.getValue()));
            }
            else
            {
                label.setText("Änderung des Sliders um " + slider.getValue());
            }

        });
    }

    public void initSlider()
    {
        slider = new Slider(-100, 100, 0);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
    }

    public void initLabel()
    {
        label = new Label("Slider wurde noch nicht bewegt.");
        root.add(label, 0, 2);
        label.setPadding(new Insets(10));
    }

    public void onChange()
    {
        slider.valueProperty().addListener((ov, o, n) ->
        {
            if (o != n)
            {
                if (showOnlyInteger.isSelected())
                {
                    label.setText("Änderung des Sliders um " + ((int) slider.getValue()));
                }
                else
                {
                    label.setText("Änderung des Sliders um " + n);
                }

            }
        });
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
