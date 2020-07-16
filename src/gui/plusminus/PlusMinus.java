package gui.plusminus;

import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PlusMinus extends Application{
	
	private static SimpleIntegerProperty count = new SimpleIntegerProperty();
	private Button plus, minus;
	private Label counterL;
	private VBox root;
	
	@Override
	public void start(Stage stage) throws Exception {
		init();
		initContainer();
		initCount();
		onIncrement();
		onDecrement();
		stage.setScene(new Scene(root, 300, 90));
		stage.setTitle("PlusMinus");
		stage.show();
	}

	public void initContainer() {
		root = new VBox(10);
		root.getChildren().add(plus);
		root.getChildren().add(counterL);
		root.getChildren().add(minus);
	}
	
	public void init() {
		plus = new Button("+");
		minus = new Button("-");
		counterL = new Label(String.valueOf(count.getValue()));
		
		plus.setId("plus");
		minus.setId("minus");
		counterL.setId("counterL");
	}
	
	public void handleIncrement() {
		PlusMinus.count.set(count.get() + 1);
	}
	
	public void handleDrecrement() {
		PlusMinus.count.set(count.get() - 1);
	}
	
	public void onIncrement() {
		plus.setOnAction(e ->{
			handleIncrement();
			myChangeListener();
		});
	}
	
	public void onDecrement() {
		minus.setOnAction(e ->{
			handleDrecrement();
			myChangeListener();
		});
	}
	
	public void initCount() {
		count.set(0);
		myChangeListener();
	}
	
	public void myChangeListener() {
		count.addListener((ov, o, n) -> {
			counterL.setText(String.valueOf(n));
		});
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
