package gui.draganddrop;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DragAndDrop extends Application
{
    private BorderPane root;

    private SplitPane splitPane;

    private Label sourceLable, targetLabel;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        init();
        primaryStage.setScene(new Scene(root, 400, 400));
        primaryStage.setTitle("Drag And Drop");
        primaryStage.show();
        handleDragDetected();
        handleDragDropped();
        handleDragOver();
        handleDragDone();
    }

    public void init()
    {
        root = new BorderPane();
        splitPane = new SplitPane();
        VBox leftSide = new VBox(10);
        sourceLable = new Label("SRC: Drag Me!");
        sourceLable.setScaleX(2.0);
        sourceLable.setScaleY(2.0);
        VBox.setVgrow(sourceLable, Priority.ALWAYS);
        sourceLable.setAlignment(Pos.TOP_RIGHT);
        leftSide.getChildren().add(sourceLable);
        VBox rightSide = new VBox(10);
        targetLabel = new Label("DST: Drop Here!");
        targetLabel.setScaleX(2.0);
        targetLabel.setScaleY(2.0);
        rightSide.getChildren().add(targetLabel);
        splitPane.getItems().addAll(leftSide, rightSide);
        root.setCenter(splitPane);
    }

    public void handleDragDetected()
    {
        sourceLable.setOnDragDetected(e ->
        {
            onDragDetected(e);
        });
    }

    public void handleDragDone()
    {
        sourceLable.setOnDragDone(e ->
        {
            onDragDone(e);
        });
    }

    public void handleDragOver()
    {
        targetLabel.setOnDragOver(e ->
        {
            onDragOver(e);
        });
    }

    public void handleDragDropped()
    {
        targetLabel.setOnDragDropped(e ->
        {
            onDragDropped(e);
        });
    }

    // initialisieren von DragBoard Clipboard und dragboard bekommt das
    // clipboardContent gesetzt.
    private void onDragDetected(MouseEvent e)
    {
        Label source = (Label) e.getSource();
        Dragboard dragboard = source.startDragAndDrop(TransferMode.MOVE);
        ClipboardContent clipboardContent = new ClipboardContent();
        clipboardContent.putString(source.getText());
        dragboard.setContent(clipboardContent);
    }

    // Wenn man drüber ist Checken ob Source ungleich Target ist, und Source
    // einen String hat.
    private void onDragOver(DragEvent e)
    {
        Label target = (Label) e.getSource();
        if (e.getGestureSource() != target && e.getDragboard().hasString())
        {
            e.acceptTransferModes(TransferMode.MOVE);
        }
    }

    // Swapped Source und Target Daten.
    private void onDragDropped(DragEvent e)
    {
        Label target = (Label) e.getSource();
        Dragboard dragboard = e.getDragboard();
        boolean done = false;
        if (dragboard.hasString())
        {
            target.setText(dragboard.getString());
            done = true;
        }
        e.setDropCompleted(done);
    }

    // Löscht den Source Text nach Dragg.
    private void onDragDone(DragEvent e)
    {
        Label source = ((Label) e.getSource());
        if (e.getTransferMode() == TransferMode.MOVE)
        {
            source.setText("");
        }
    }

    public static void main(String[] args)
    {
        launch(args);
    }

}
