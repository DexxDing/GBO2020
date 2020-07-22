package gui.graphics.sinus;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class SinusView extends BorderPane
{

    private SinusPresenter p;

    private Pane drawingPane;

    private Label formel;

    private Label amplitudeL, frequenzL, phaseL, zoomL;

    private Slider ampSlider, freqSlider, phaseSlider, zoomSlider;

    private VBox sliderBox;

    private Line xAchse, yAchse;

    public void setPresenter(SinusPresenter p)
    {
        this.p = p;
    }

    public SinusView()
    {
        this.setWidth(400);
        this.setHeight(400);
        initView();
        initCoordinateSystem();
        changeWindowSize();
        initSlider();
        initSliderBox();
        addNodes();
        System.out.println(drawingPane.getWidth() + " Breite" + drawingPane.getHeight() + " Hoehe");
    }

    public void initView()
    {
        sliderBox = new VBox(10);
        amplitudeL = new Label("Amplitude: \t");
        frequenzL = new Label("Frequenz: \t");
        phaseL = new Label("Phase: \t");
        zoomL = new Label("Zoom: \t");

        drawingPane = new Pane();
        formel = new Label("adas");
    }

    public void initSlider()
    {
        ampSlider = initSlider(-6, 6, 0);
        ampSlider.setId("amplitude");
        freqSlider = initSlider(0, 40, 0);
        freqSlider.setId("frequency");
        phaseSlider = initSlider(-10, 10, 0);
        phaseSlider.setId("phase");
        zoomSlider = initSlider(10, 210, 10);
        zoomSlider.setId("zoom");
    }

    public Slider initSlider(double min, double max, double value)
    {
        Slider s = new Slider(min, max, value);
        s.setShowTickLabels(true);
        s.setShowTickMarks(true);
        return s;
    }

    public void initSliderBox()
    {
        sliderBox.getChildren().add(new HBox(amplitudeL, ampSlider));
        sliderBox.getChildren().add(new HBox(frequenzL, freqSlider));
        sliderBox.getChildren().add(new HBox(phaseL, phaseSlider));
        sliderBox.getChildren().add(new HBox(zoomL, zoomSlider));
    }

    public void addNodes()
    {
        this.setPadding(new Insets(10));
        this.setTop(formel);
        this.setCenter(drawingPane);
        this.setBottom(sliderBox);
    }

    public void changeWindowSize()
    {
        xAchse.endXProperty().bind(this.widthProperty());
        yAchse.endYProperty().bind(this.heightProperty());
    }

    public void initCoordinateSystem()
    {
        xAchse = new Line(0, this.getHeight() / 2, this.getWidth(), this.getHeight() / 2);
        yAchse = new Line(this.getWidth() / 2, 0, this.getWidth() / 2, this.getHeight());
        xAchse.setFill(Color.RED);
        yAchse.setFill(Color.GREEN);
        drawingPane.getChildren().addAll(xAchse, yAchse);
    }

}
