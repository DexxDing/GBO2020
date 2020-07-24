package gui.uebung11_2;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CubicCurve;
import javafx.stage.Stage;

public class Bezierkurve  extends Application{
	
	private BorderPane root;
	
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
		
		
		root = new BorderPane();
		root.setCenter(drawingPane);
		
		handleMouseDragged();
		
		cc.controlX1Property().bind(cp1.centerXProperty());
		cc.controlX2Property().bind(cp2.centerXProperty());
		cc.controlY1Property().bind(cp1.centerYProperty());
		cc.controlY2Property().bind(cp2.centerYProperty());
		cc.startXProperty().bind(start.centerXProperty());
		cc.startYProperty().bind(start.centerYProperty());
		cc.endXProperty().bind(end.centerXProperty());
		cc.endYProperty().bind(end.centerYProperty());
		
		cp1L.layoutXProperty().bind(cp1.centerXProperty());
		cp2L.layoutXProperty().bind(cp2.centerXProperty());
		cp1L.layoutYProperty().bind(cp1.centerYProperty());
		cp2L.layoutYProperty().bind(cp2.centerYProperty());
		
		drawingPane.getChildren().addAll(cc, start, end, cp1, cp2, cp1L, cp2L);
		
		
	}
	
	public void handleMouseDragged() {
		cp1.setOnMouseDragged(e -> {
			cp1.setCenterX(e.getX());
			cp1.setCenterY(e.getY());
		});
		cp2.setOnMouseDragged(e -> {
			cp2.setCenterX(e.getX());
			cp2.setCenterY(e.getY());
		});
		
		start.setOnMouseDragged(e -> {
			start.setCenterX(e.getX());
			start.setCenterY(e.getY());
		});
		end.setOnMouseDragged(e -> {
			end.setCenterX(e.getX());
			end.setCenterY(e.getY());
		});
		
		
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}

}
