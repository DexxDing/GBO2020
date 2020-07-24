package gui.uebung10_2;


import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CubicCurve;
import javafx.stage.Stage;

public class Bezierkurve  extends Application{
	
	private Slider cp1SliderX, cp1SliderY, cp2SliderX, cp2SliderY;
	
	private BorderPane root;
	private VBox left, right;
	
	private Circle cp1, start, cp2, end;

	private Pane drawingPane;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		initView();
		primaryStage.setScene(new Scene(root,800, 400));
		primaryStage.setTitle("Bezier Kurve");
		primaryStage.show();
	}
	
	public Slider initSlider(double min, double max, double value) {
		Slider s = new Slider(min, max, value);
		s.setMajorTickUnit(10);
		s.setMinorTickCount(10);
		s.setShowTickLabels(true);
		s.setShowTickMarks(true);
		return s;
	}
	
	public void initView() {
		
		this.drawingPane = new Pane();
		
		cp1 = new Circle(50, 90, 4);
		cp1.setFill(Color.GREEN);
		Label cp1L =  new Label("Control Point 1");
		cp1L.setTextFill(Color.RED);
		Label cp2L =  new Label("Control Point 2");
		cp2L.setTextFill(Color.RED);
		cp2 = new Circle(250, 100, 4);
		cp2.setFill(Color.BLUE);
		
		start = new Circle(50, 50, 4);
		start.setFill(Color.RED);
		end = new Circle(350, 50, 4);
		end.setFill(Color.RED);
		
		CubicCurve cc = new CubicCurve();
		cc.setStartX(start.getCenterX());
		cc.setStartY(start.getCenterY());
		cc.setEndX(end.getCenterX());
		cc.setEndY(end.getCenterY());
		cc.setFill(null);
		cc.setStroke(Color.BLACK);
		
		
		cp1SliderX = initSlider(0, 300, 0);
		cp1SliderY = initSlider(0, 300, 160);
		cp1SliderY.setOrientation(Orientation.VERTICAL);
		cp2SliderX = initSlider(0, 300, 200);
		cp2SliderY = initSlider(0, 300, 200);
		cp2SliderY.setOrientation(Orientation.VERTICAL);
		
		root = new BorderPane();
		left = new VBox();
		left.getChildren().addAll(cp1SliderX, cp1SliderY);
		right= new VBox();
		right.getChildren().addAll(cp2SliderX, cp2SliderY);
		root.setLeft(left);
		root.setRight(right);
		root.setCenter(drawingPane);
		
		handleChangeCP1(); // Beispiel ChangeListener
//		cp1.centerXProperty().bind(cp1SliderX.valueProperty());
//		cp1.centerYProperty().bind(cp1SliderY.valueProperty());
		cp2.centerXProperty().bind(cp2SliderX.valueProperty());
		cp2.centerYProperty().bind(cp2SliderY.valueProperty());
		
		cc.controlX1Property().bind(cp1.centerXProperty());
		cc.controlX2Property().bind(cp2.centerXProperty());
		cc.controlY1Property().bind(cp1.centerYProperty());
		cc.controlY2Property().bind(cp2.centerYProperty());
		
		cp1L.layoutXProperty().bind(cp1.centerXProperty());
		cp2L.layoutXProperty().bind(cp2.centerXProperty());
		cp1L.layoutYProperty().bind(cp1.centerYProperty());
		cp2L.layoutYProperty().bind(cp2.centerYProperty());
		
		drawingPane.getChildren().addAll(cc, start, end, cp1, cp2, cp1L, cp2L);
	}
	
	
	// Variante mit ChangeListener
	public void handleChangeCP1() {
		cp1SliderX.valueProperty().addListener( e -> {
			cp1.setCenterX(cp1SliderX.getValue());
			System.out.println("X Value Control Point 1: " + cp1SliderX.getValue() );
		});
		cp1SliderY.valueProperty().addListener(e -> {
			cp1.setCenterY(cp1SliderY.getValue());
			System.out.println("X Value Control Point 2: " + cp1SliderY.getValue() );
		});
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}

}
