package gui.exam.drawing;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

public class Main extends Application
{
    private Line line;

    private Circle circle;

    private Rectangle rectangle;

    private Pane drawingPane;

    private BorderPane root;

    private ToolBar tb;

    private ToggleGroup tg;

    private RadioButton kreis, linie, rechteck;

    private Button deleteBtn, protocolBtn, undo, redo;

    private Label countLabel;

    private TextArea ta = new TextArea();

    private Stage protocolStage = new Stage();

    private HBox hbox = new HBox(10);

    private UndoRedoManager manager = new UndoRedoManager();

    private int linien, rechtecke, kreise, gesamt;

    public int getGesamt()
    {
        return gesamt;
    }

    public void setGesamt(int gesamt)
    {
        this.gesamt = gesamt;
    }

    public int getLinien()
    {
        return linien;
    }

    public void setLinien(int linien)
    {
        this.linien = linien;
    }

    public int getRechtecke()
    {
        return rechtecke;
    }

    public void setRechtecke(int rechtecke)
    {
        this.rechtecke = rechtecke;
    }

    public int getKreise()
    {
        return kreise;
    }

    public void setKreise(int kreise)
    {
        this.kreise = kreise;
    }

    private double startX, startY;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        root = new BorderPane();
        initPane();
        initToolbar();
        initLabels();
        showProt();
        addToContainer();
        initDelete();
        setClipping();
        onPressed();
        onDragged();
        onReleased();
        initUndoRedo();
        primaryStage.setTitle("Zeichnen von Formen");
        primaryStage.setScene(new Scene(root, 400, 400));
        primaryStage.show();
    }

    private void initUndoRedo()
    {
        undo = new Button("undo");
        redo = new Button("redo");
        undo.setOnAction(e ->
        {
            manager.undo();
        });
        redo.setOnAction(e -> manager.redo());
        tb.getItems().add(undo);
        tb.getItems().add(redo);

    }

    public static void main(String[] args)
    {
        launch(args);
    }

    public void initPane()
    {
        drawingPane = new Pane();
    }

    public void addToContainer()
    {
        root.setTop(tb);
        root.setCenter(drawingPane);
        hbox.getChildren().addAll(countLabel, protocolBtn);
        root.setBottom(hbox);

    }

    public void onPressed()
    {
        handlePressed();
    }

    private void handlePressed()
    {
        drawingPane.setOnMousePressed(e ->
        {
            startX = e.getX();
            startY = e.getY();
            if (linie.isSelected())
            {
                line = new Line(e.getX(), e.getY(), e.getX(), e.getY());
                drawingPane.getChildren().add(line);
            }
            if (kreis.isSelected())
            {
                circle = new Circle(e.getX(), e.getY(), 0);
                circle.setFill(null);
                drawingPane.getChildren().add(circle);
            }
            if (rechteck.isSelected())
            {
                rectangle = new Rectangle(e.getX(), e.getY(), 0, 0);
                rectangle.setFill(null);
                drawingPane.getChildren().add(rectangle);
            }

        });
    }

    public void onDragged()
    {
        handleDragged();
    }

    private void handleDragged()
    {
        drawingPane.setOnMouseDragged(e ->
        {
            double endX = e.getX();
            double endY = e.getY();

            if (linie.isSelected())
            {
                handleStroke(line, Color.GRAY, 0.5);
                line.setEndX(e.getX());
                line.setEndY(e.getY());
            }
            if (rechteck.isSelected())
            {

                handleStroke(rectangle, Color.GRAY, 0.5);

                double b = endX - startX;
                double h = endY - startY;
                if (h > 0)
                {
                    rectangle.setY(startY);
                    rectangle.setHeight(h);
                }
                if (b > 0)
                {

                    rectangle.setX(Math.abs(startX));
                    rectangle.setWidth(b);
                }
                if (b < 0)
                {
                    rectangle.setX(Math.abs(endX));
                    rectangle.setWidth(-b);
                }
                if (h < 0)
                {
                    rectangle.setY(Math.abs(endY));
                    rectangle.setHeight(-h);
                }
            }
            if (kreis.isSelected())
            {
                double a = e.getX() - startX;
                double b = e.getY() - startY;
                double c = Math.sqrt((a * a) + (b * b)); // c -> Radius
                circle.setRadius(c);
                handleStroke(circle, Color.GRAY, 0.5);
            }
        });
    }

    public void onReleased()
    {
        handleReleased();
    }

    private void handleReleased()
    {
        drawingPane.setOnMouseReleased(e ->
        {
            if (linie.isSelected())
            {
                handleStroke(line, Color.BLACK, 2d);
                setLinien(getLinien() + 1);
                ta.setText(ta.getText().concat(line.toString() + "\n"));
                Action action = new Action(this, (Shape) line);
                manager.add(action);

            }
            if (rechteck.isSelected())
            {
                handleStroke(rectangle, Color.BLACK, 2d);
                setRechtecke(getRechtecke() + 1);
                ta.setText(ta.getText().concat(rectangle.toString() + "\n"));
                Action action = new Action(this, (Shape) rectangle);
                manager.add(action);
            }
            if (kreis.isSelected())
            {
                handleStroke(circle, Color.BLACK, 2d);
                setKreise(getKreise() + 1);
                ta.setText(ta.getText().concat(circle.toString() + "\n"));
                Action action = new Action(this, (Shape) circle);
                manager.add(action);
            }
            setGesamt(getLinien() + getKreise() + getRechtecke());
            countLabel.setText("Linien: " + getLinien() + " Rechtecke: " + getRechtecke() + " Kreise: " + getKreise() + " Gesamt: " + getGesamt());
        });
    }

    public void handleStroke(Shape s, Color c, double width)
    {
        s.setStroke(c);
        s.setStrokeWidth(width);
    }

    public void initToolbar()
    {
        kreis = new RadioButton("Kreis");
        linie = new RadioButton("Linie");
        rechteck = new RadioButton("Rechteck");
        linie.setSelected(true);
        tg = new ToggleGroup();
        tg.getToggles().addAll(linie, kreis, rechteck);
        tb = new ToolBar(linie, kreis, rechteck);
    }

    public void setClipping()
    {
        Rectangle clip = new Rectangle();
        clip.widthProperty().bind(drawingPane.widthProperty());
        clip.heightProperty().bind(drawingPane.heightProperty());
        drawingPane.setClip(clip);
    }

    public void initDelete()
    {
        deleteBtn = new Button("Löschen");
        deleteBtn.setOnAction(e ->
        {
            drawingPane.getChildren().clear();
            resetAllCounter();
            manager.clear();
        });
        tb.getItems().add(deleteBtn);
    }

    public void initLabels()
    {
        countLabel = new Label("Linien: " + getLinien() + " Rechtecke: " + getRechtecke() + " Kreise: " + getKreise() + " Gesamt: " + getGesamt());
    }

    public void resetAllCounter()
    {
        setLinien(0);
        setRechtecke(0);
        setKreise(0);
        setGesamt(0);
        countLabel.setText("Linien: " + getLinien() + " Rechtecke: " + getRechtecke() + " Kreise: " + getKreise() + " Gesamt: " + getGesamt());
        ta.clear();
    }

    public Stage protocolStage()
    {
        Pane pane = new AnchorPane();
        ta.setEditable(false);
        pane.getChildren().add(ta);
        protocolStage.setScene(new Scene(pane));
        return protocolStage;
    }

    public void showProt()
    {
        protocolBtn = new Button("Protocol");
        protocolBtn.setOnAction(e ->
        {
            if (!protocolStage.isShowing())
            {
                protocolStage().showAndWait();
            }
        });
    }

    public void delete(Shape s)
    {
        drawingPane.getChildren().remove(s);
    }

    public void add(Shape s)
    {
        drawingPane.getChildren().add(s);
    }
}
