package gui.cutcopypaste;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CutCopyPaste extends Application
{
    private VBox root;

    private Label l1, l2;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        l1 = new Label("Label 1");
        l2 = new Label("Label 2");
        root = new VBox(10);
        root.getChildren().addAll(l1, l2);

        ContextMenu contextMenu1 = new ContextMenu();
        CustomMenuItem customMenuItem = new CustomMenuItem(new Label("Label1")); // koennte
        SeparatorMenuItem sep1 = new SeparatorMenuItem();
        MenuItem cut1 = new MenuItem("Cut");
        cut1.setOnAction(e -> copyLabel(l1, true));
        MenuItem copy1 = new MenuItem("Copy");
        copy1.setOnAction(e -> copyLabel(l1, false));
        MenuItem paste1 = new MenuItem("Paste");
        paste1.setOnAction(e -> pasteLabel(l1));
        contextMenu1.getItems().addAll(customMenuItem, sep1, cut1, copy1, paste1);
        l1.setContextMenu(contextMenu1);

        ContextMenu contextMenu2 = new ContextMenu();
        CustomMenuItem customMenuItem2 = new CustomMenuItem(new Label("Label2"));
        SeparatorMenuItem sep2 = new SeparatorMenuItem();
        MenuItem cut2 = new MenuItem("Cut");
        cut2.setOnAction(e -> copyLabel(l2, true));
        MenuItem copy2 = new MenuItem("Copy");
        copy2.setOnAction(e -> copyLabel(l2, false));
        MenuItem paste2 = new MenuItem("Paste");
        paste2.setOnAction(e -> pasteLabel(l2));
        contextMenu2.getItems().addAll(customMenuItem2, sep2, cut2, copy2, paste2);
        l2.setContextMenu(contextMenu2);

        primaryStage.setTitle("Cut Copy Paste");
        primaryStage.setScene(new Scene(root, 400, 400));
        primaryStage.show();
    }

    private void pasteLabel(Label l12)
    {
        Clipboard clipBoard = Clipboard.getSystemClipboard();
        if (clipBoard.hasString())
        {
            l12.setText(clipBoard.getString());
        }

    }

    private void copyLabel(Label l12, boolean delete)
    {
        Clipboard clipBoard = Clipboard.getSystemClipboard();
        ClipboardContent content = new ClipboardContent();
        content.putString(l12.getText());
        clipBoard.setContent(content);
        if (delete)
        {
            l12.setText("");
        }

    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
