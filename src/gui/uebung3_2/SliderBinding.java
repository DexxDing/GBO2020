package gui.uebung3_2;

import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * 
 * @author bennet Kopplungsnetz mit zwei Slidern welches die Formel a2 + b2
 *         berechnen! Jedes Mal, wenn sich a oder b ändert, sollen Sie den neuen
 *         Wert der Formel auf der im Programm ausgeben!
 */
public class SliderBinding extends Application
{
    private Slider sliderA, sliderB;

    private Text sliderTextA, sliderTextB, aQuadratPlusbQuadrat;

    private SimpleIntegerProperty propA, propB;

    private VBox vbox;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        vbox = new VBox(15);
        vbox.setAlignment(Pos.CENTER);
        initSlider();
        initText();
        bindProperties();
        // initBidirectionalBinding(); // shows the bidirectional binding of two
        // sliders
        vbox.getChildren().addAll(sliderTextA, sliderA, sliderTextB, sliderB, aQuadratPlusbQuadrat);
        changeListener();
        primaryStage.setScene(new Scene(vbox, 400, 400));
        primaryStage.setTitle("Slider Binding Example");
        primaryStage.show();
    }

    public void initText()
    {
        sliderTextA = new Text("Slider A: " + (int) sliderA.getValue());
        sliderTextB = new Text("Slider B: " + (int) sliderB.getValue());
        aQuadratPlusbQuadrat = new Text("a*a + b*b = 0");
    }

    public void initProperties()
    {
        propA = new SimpleIntegerProperty();
        propB = new SimpleIntegerProperty();
    }

    public void bindProperties()
    {
        // init properties
        propA = new SimpleIntegerProperty();
        propB = new SimpleIntegerProperty();
        propA.bind(sliderA.valueProperty());
        propB.bind(sliderB.valueProperty());
    }

    public void initSlider()
    {
        sliderA = new Slider(0, 10, 0);
        sliderB = new Slider(0, 10, 0);

        sliderA.setShowTickLabels(true);
        sliderA.setShowTickMarks(true);
        sliderB.setShowTickLabels(true);
        sliderB.setShowTickMarks(true);
    }

    public void changeListener()
    {
        propA.addListener(new ChangeListener<Number>()
        {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue)
            {
                sliderTextA.setText("Slider A: " + propA.getValue());
                aQuadratPlusbQuadrat.setText(String.valueOf(("a*a + b*b = " + (propA.getValue() * propA.getValue() + propB.getValue() * propB.intValue()))));
            }
        });
        propB.addListener((ov, o, n) ->
        {
            sliderTextB.setText("Slider B: " + propB.getValue());
            aQuadratPlusbQuadrat.setText(String.valueOf(("a*a + b*b = " + (propA.getValue() * propA.getValue() + propB.getValue() * propB.intValue()))));
        });
    }

    public void initBidirectionalBinding()
    {
        sliderA.valueProperty().bindBidirectional(sliderB.valueProperty());
    }

    public static void main(String[] args)
    {
        launch(args);
    }

}
