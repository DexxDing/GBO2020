package gui.exam.barchart;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Probabilities extends Application
{
    private Label nLabel, pLabel;

    private Pane drawRoot = new Pane();

    private Text indexText, anzeigeText;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        BorderPane root = new BorderPane();

        HBox nControl = new HBox(10);
        Label ln = new Label("n: ");
        ln.setFont(Font.font(null, FontWeight.BOLD, 20));
        Slider nSlider = new Slider(1, 50, 0);
        nLabel = new Label();
        nLabel.setFont(Font.font(null, FontWeight.BOLD, 20));
        nControl.getChildren().addAll(ln, nSlider, nLabel);

        HBox pControl = new HBox(10);
        Label lp = new Label("p: ");
        lp.setFont(Font.font(null, FontWeight.BOLD, 20));
        Slider pSlider = new Slider(0, 1, 0);
        pLabel = new Label();
        pLabel.setFont(Font.font(null, FontWeight.BOLD, 20));
        pControl.getChildren().addAll(lp, pSlider, pLabel);

        CheckBox poisson = new CheckBox("Poisson");
        poisson.setFont(Font.font(null, FontWeight.BOLD, 20));

        VBox controls = new VBox(10);
        controls.getChildren().addAll(nControl, pControl, poisson);
        controls.setPadding(new Insets(10));
        root.setBottom(controls);

        nSlider.valueProperty().addListener((obs, oldValue, newValue) ->
        {
            update((int) nSlider.getValue(), pSlider.getValue(), poisson.isSelected());
        });
        pSlider.valueProperty().addListener((obs, oldValue, newValue) ->
        {
            update((int) nSlider.getValue(), pSlider.getValue(), poisson.isSelected());
        });
        poisson.selectedProperty().addListener((obs, oldValue, newValue) ->
        {
            update((int) nSlider.getValue(), pSlider.getValue(), poisson.isSelected());
        });

        drawRoot.widthProperty().addListener((ov, o, n) ->
        {
            update((int) nSlider.getValue(), pSlider.getValue(), poisson.isSelected());
        });

        drawRoot.heightProperty().addListener((ov, o, n) ->
        {
            update((int) nSlider.getValue(), pSlider.getValue(), poisson.isSelected());
        });

        root.setCenter(drawRoot); // zeichenflaeche adden

        primaryStage.setTitle("REFERENZ");
        primaryStage.setScene(new Scene(root, 900, 600));
        primaryStage.show();
        // damit die Balken direkt beim Programmstart sichtbar sind
        pSlider.setValue(0.5);
        nSlider.setValue(10);
        poisson.setSelected(false);
    }

    private void update(int n, double p, boolean selected)
    {
        drawRoot.getChildren().clear();

        p = ((int) (p * 1000)) / 1000.00;

        nLabel.setText("n = " + n);
        pLabel.setText("p = " + p);

        double[] resultBinom = Computation.computeBinomProbs(n, p);
        double[] resultPoisson = Computation.computePoissonProbs(n, p);
        double drawWith = drawRoot.getWidth() - 60;
        double effectiveDrawWith = (drawRoot.getWidth() - 60 - (4 * (n)));
        double efftiveDrawHeight = drawRoot.getHeight() - 60;
        int maxRectangles = n + 1;

        double widthRectangle = (effectiveDrawWith / maxRectangles);

        for (int i = 0; i <= n; i++)
        {
            anzeigeText = new Text();
            anzeigeText.setX(effectiveDrawWith / 2 - anzeigeText.getBoundsInLocal().getWidth() / 2);
            anzeigeText.setY(0);
            anzeigeText.setTextOrigin(VPos.TOP);

            indexText = new Text(String.valueOf(i));
            indexText.setTextOrigin(VPos.TOP);
            indexText.setX(30 + (i * 4) + (i * widthRectangle + widthRectangle / 2) - (indexText.getBoundsInLocal().getWidth() / 2));

            final int finalIndex = i;
            double xPos = 30 + ((4 + widthRectangle) * i);
            double yPos = drawRoot.getHeight() - 30;
            double h = 0;
            if (resultBinom[i] >= 1)
            {
                h = efftiveDrawHeight;
            }
            else
            {
                h = efftiveDrawHeight * resultBinom[i];
            }
            indexText.setY(efftiveDrawHeight + 30);
            drawRoot.getChildren().add(indexText);
            Rectangle rectangleBinom = new Rectangle(xPos, yPos - h, widthRectangle, h);
            drawRoot.getChildren().addAll(rectangleBinom);
            rectangleBinom.setOnMouseEntered(e ->
            {
                anzeigeText.setText("Binom(" + finalIndex + ")" + " = " + resultBinom[finalIndex]);
                this.drawRoot.getChildren().add(anzeigeText);
                System.out.println("Entered Binom");
            });
            rectangleBinom.setOnMouseExited(e ->
            {
                System.out.println("Exit Binom");
                drawRoot.getChildren().remove(anzeigeText);
            });

            // Mittelwert
            Line mittelwert = new Line(30 + (p * drawWith), yPos, 30 + (p * drawWith), 30);
            mittelwert.setStroke(Color.RED);
            mittelwert.setStrokeWidth(4);

            drawRoot.getChildren().add(mittelwert);

            if (selected)
            {
                h = resultPoisson[i] * efftiveDrawHeight;
                double widthGreenRectangle = widthRectangle / 3;
                Rectangle rectanglePoisson = new Rectangle(xPos + (widthRectangle / 2) - widthGreenRectangle / 2, yPos - h, widthGreenRectangle, h);
                rectanglePoisson.setFill(Color.LIGHTGREEN);
                rectanglePoisson.setOnMouseEntered(e ->
                {
                    System.out.println("Entered Poisson");
                    anzeigeText.setText("Poisson(" + finalIndex + ")" + " = " + resultPoisson[finalIndex]);
                    this.drawRoot.getChildren().add(anzeigeText);
                });
                rectanglePoisson.setOnMouseExited(e ->
                {
                    System.out.println("Exit Poisson");
                    drawRoot.getChildren().remove(anzeigeText);
                });
                drawRoot.getChildren().add(rectanglePoisson);
            }
        }
    }

    public static void main(String[] args)
    {
        launch(args);
    }

}