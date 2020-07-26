package gui.gridpane.dragdrop;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application
{
    private VBox root;

    private List<String> answersList = new ArrayList<>();

    private GridPane sourceGrid, targetGrid;

    private Text questionText = new Text("Was verbinden Sie mit GBO? (in alphabetischer Reihenfolge).");

    private String swapString;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        initAnswersList();
        initSourceGridPane();
        initTargetGridPane();
        initRoot();
        VBox.setVgrow(sourceGrid, Priority.ALWAYS);
        VBox.setVgrow(targetGrid, Priority.ALWAYS);
        primaryStage.setTitle("Drag and Drop");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    private void initTargetGridPane()
    {
        targetGrid = new GridPane();
        targetGrid.setGridLinesVisible(true);
        for (int i = 0; i < answersList.size(); i++)
        {
            Label targetLabel = new Label("                                        ");
            targetGrid.add(targetLabel, 0, i);
            GridPane.setHgrow(targetLabel, Priority.ALWAYS);
            ;
            GridPane.setVgrow(targetLabel, Priority.ALWAYS);
            ;
            targetLabel.setOnDragDetected(e ->
            {
                handleDragDetected(e);
            });
            targetLabel.setOnDragOver(e ->
            {
                handleDragOver(e);
            });
            targetLabel.setOnDragDropped(e ->
            {
                handleDragDropped(e);
            });
            targetLabel.setOnDragDone(e ->
            {
                handleDragDone(e);
            });
        }
    }

    private void initSourceGridPane()
    {
        sourceGrid = new GridPane();
        sourceGrid.setGridLinesVisible(true);
        for (int i = 0; i < answersList.size(); i++)
        {
            Label sourceLabel = new Label(answersList.get(i));
            sourceGrid.add(sourceLabel, i, 0);
            GridPane.setHgrow(sourceLabel, Priority.ALWAYS);
            GridPane.setVgrow(sourceLabel, Priority.ALWAYS);
            GridPane.setHalignment(sourceLabel, HPos.CENTER);
            sourceLabel.setOnDragDetected(e ->
            {
                handleDragDetected(e);
            });
            sourceLabel.setOnDragOver(e ->
            {
                handleDragOver(e);
            });
            sourceLabel.setOnDragDropped(e ->
            {
                handleDragDropped(e);
            });
            sourceLabel.setOnDragDone(e ->
            {
                handleDragDone(e);
            });
        }
    }

    private void handleDragDetected(MouseEvent e)
    {
        Label source = (Label) e.getSource();
        Dragboard dragBoard = source.startDragAndDrop(TransferMode.ANY);
        ClipboardContent clipboardContent = new ClipboardContent();
        if (source != null)
        {
            clipboardContent.putString(source.getText());
        }
        dragBoard.setContent(clipboardContent);
    }

    private void handleDragOver(DragEvent e)
    {
        Label target = (Label) e.getSource();
        Dragboard d = e.getDragboard();
        if (e.getGestureSource() != target && d.hasString())
        {
            e.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        }
    }

    private void handleDragDropped(DragEvent e)
    {
        Label target = (Label) e.getSource();
        Dragboard db = e.getDragboard();
        boolean success = false;
        if (db.hasString())
        {
            swapString = target.getText();
            target.setText(db.getString());
            success = true;
        }
        e.setDropCompleted(success);
    }

    private void handleDragDone(DragEvent e)
    {
        Label source = (Label) e.getSource();
        source.setText(swapString);
    }

    public void initAnswersList()
    {
        answersList.add("Aufstoﬂen");
        answersList.add("Bauchschmerzen");
        answersList.add("Durchfall");
    }

    private void initRoot()
    {
        questionText.setFill(Color.RED);
        VBox.setMargin(questionText, new Insets(10));
        root = new VBox(10);
        root.getChildren().addAll(questionText, sourceGrid, targetGrid);
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
