package gui.uebung11_1;


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class Freihandzeichnen extends Application{
	
	
	private BorderPane root;
	private ComboBox<Integer> groesseCB;
	private ObservableList<Integer> groesseOL = FXCollections.observableArrayList();
	private ObservableList<String> colorOL = FXCollections.observableArrayList();
	private ComboBox<String> colorCB;
	private Button loeschen;
	private HBox hbox;
	private Pane pane;
	private Path path;
	
	private StringConverter<Color> converter = new StringConverter<Color>() {
    	
    	@Override
    	public Color fromString(String string) {
    		Color color = null;
    		if (string.equals("Red")) {
    			color = Color.RED;
    		}
    		if (string.equals("Green")) {
    			color = Color.GREEN;
    		}
    		if (string.equals("Blue")) {
    			color = Color.BLUE;
    		}
    		return color;
    	}
    	
    	@Override
    	public String toString(Color c) {
    		String s = null;
    		if(c == Color.RED) {
    			s = "Red";
    		}
    		if(c == Color.GREEN) {
    			s = "Green";
    		}
    		if(c == Color.BLUE) {
    			s = "Blue";
    		}
    		return s;
    	}
    };
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		root = new BorderPane();
		hbox = new HBox(10);
		initView();
		initGroessen();
		initColors();
		initClipping();
		handleZeichnen();
		handleDragged();
		handleDelete();
		primaryStage.setScene(new Scene(root, 400, 400));
		primaryStage.show();
	}
	
	public void initView() {
		pane = new Pane();
		path = new Path();
		groesseCB = new ComboBox<>();
		colorCB = new ComboBox<>();
		loeschen = new Button("Löschen");
		hbox.getChildren().addAll(groesseCB, colorCB, loeschen);
		root.setTop(hbox);
		root.setCenter(pane);
	}
	
	public void initGroessen() {
		for(int i = 1; i <= 10; i ++) {
			groesseOL.add(i);
		}
		groesseCB.getItems().addAll(groesseOL);
		groesseCB.getSelectionModel().selectFirst();
	}
	
	public void initColors() {
		colorOL.addAll("Red", "Green", "Blue");
		colorCB.getItems().addAll(colorOL);
		colorCB.getSelectionModel().selectFirst();
	}
	
	public void initClipping() {
		Rectangle rectangleClip = new Rectangle();
		rectangleClip.widthProperty().bind(pane.widthProperty());
		rectangleClip.heightProperty().bind(pane.heightProperty());
		pane.setClip(rectangleClip);
	}
	
	public void handleZeichnen() {
		pane.setOnMousePressed(e -> {
			onPressed(e);
		});
	}
	
	public void onPressed(MouseEvent e) {
		path = new Path();
		path.setStrokeWidth((double) groesseCB.getSelectionModel().getSelectedItem());
		path.setStroke(converter.fromString(colorCB.getSelectionModel().getSelectedItem()));
		path.getElements().add(new MoveTo(e.getX(), e.getY()));
		pane.getChildren().add(path);
	}
	
	public void handleDragged() {
		pane.setOnMouseDragged(e ->{
			path.getElements().add(new LineTo(e.getX(), e.getY()));
		});
	}
	
	public void handleDelete() {
		loeschen.setOnAction(e ->{
			onDelete();
		});
	}
	
	public void onDelete() {
		pane.getChildren().clear();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
