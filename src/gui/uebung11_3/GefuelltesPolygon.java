package gui.uebung11_3;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

public class GefuelltesPolygon extends Application
{
    private Pane pane;

    private Polygon polygon;

    private List<Circle> controllPoints;

    private List<Label> labelList;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        pane = new Pane();
        initControllPoints();
        primaryStage.setScene(new Scene(pane, 400, 400));
        primaryStage.setTitle("Gefülltes Polygon");
        primaryStage.show();
    }

    protected void initControllPoints()
    {
        controllPoints = new ArrayList<Circle>();
        labelList = new ArrayList<Label>();
        polygon = new Polygon();

        for (int i = 1; i <= 10; i++)
        {
            controllPoints.add(new Circle(i * 10, i * 20, 8));
            labelList.add(new Label(String.valueOf(i)));
            labelList.get(i - 1).layoutXProperty().bind(controllPoints.get(i - 1).centerXProperty());
            labelList.get(i - 1).layoutYProperty().bind(controllPoints.get(i - 1).centerYProperty());
        }
        for (Circle circle : controllPoints)
        {
            circle.setFill(Color.RED);
            handleDragged(circle);
            pane.getChildren().add(circle);
            polygon.getPoints().addAll(circle.getCenterX(), circle.getCenterY());
        }
        for (Label label : labelList)
        {
            pane.getChildren().add(label);
        }
        polygon.setStroke(Color.BLACK);
        polygon.setStrokeWidth(2d);
        polygon.setFill(Color.AQUA);

        pane.getChildren().add(polygon);
    }

    public void refresh()
    {
        polygon.getPoints().clear();
        for (Circle circle : controllPoints)
        {
            polygon.getPoints().addAll(circle.getCenterX(), circle.getCenterY());
        }
    }

    public void handleDragged(Circle c)
    {
        c.setOnMouseDragged(e ->
        {
            c.setCenterX(e.getX());
            c.setCenterY(e.getY());
            refresh();
        });
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
