package gui.listview;

import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class View extends BorderPane
{

    private ListView<Person> lv;

    private Presenter p;

    private ToolBar footer, header;

    private Button add, delete, update, commitUpdate, commitInsert, undo, redo;

    private Stage updateStage = new Stage();

    private Stage insertStage = new Stage();

    private TextField nameTF, vornameTF, ageTF;

    private Label nameL, vornameL, ageL;

    private MenuBar menuBar;

    private Menu file;

    private MenuItem exit;

    private UndoRedoManager manager = new UndoRedoManager();

    public void initDropdownMenu()
    {
        menuBar = new MenuBar(); // hier nur Menutems
        ContextMenu contextMenu = new ContextMenu();
        CustomMenuItem customMenuItem = new CustomMenuItem(new javafx.scene.shape.Rectangle(20, 20, Color.BLUE));
        Separator separator = new Separator(Orientation.HORIZONTAL);
        CustomMenuItem customMenuItem2 = new CustomMenuItem(separator);
        exit = new MenuItem("Exit");
        exit.setAccelerator(new KeyCodeCombination(KeyCode.ESCAPE));
        contextMenu.getItems().addAll(customMenuItem, customMenuItem2, exit);
        Label l = new Label("Open Context Menu");
        l.setContextMenu(contextMenu);
        header.getItems().add(l);
    }

    public View(Presenter p)
    {
        this.p = p;
    }

    public void initListView(ObservableList<Person> personOL)
    {
        lv = new ListView<>(personOL);
    }

    public void initView()
    {
        commitInsert = new Button("Hinzufügen");
        commitUpdate = new Button("Bestätige Änderung");
        initToolbar();
        initDropdownMenu();
        p.fillListView();
        onUpdate();
        onDelete();
        onInsert();
        showInsert();
        handleUndo();
        handleRedo();
        this.setTop(header);
        this.setCenter(lv);
        this.setBottom(footer);
    }

    public void initToolbar()
    {
        add = new Button("Neue Person");
        delete = new Button("Entferne Person");
        update = new Button("Ändere Person");
        undo = new Button("Undo");
        redo = new Button("Redo");
        this.footer = new ToolBar(add, delete, update);
        this.header = new ToolBar(undo, redo);
    }

    public void onUpdate()
    {
        lv.getSelectionModel().selectedItemProperty().addListener((ov, o, n) ->
        {
            System.out.println(n);
            if (n != null)
            {

                if (lv.getSelectionModel().getSelectedItems().size() > 0)
                {
                    Person p = lv.getSelectionModel().getSelectedItem();

                    handleShowUpdate(p.getNameValue(), p.getVornamenameValue(), p.getAgeValue());
                    update.setOnAction(e ->
                    {
                        showUDStage();
                    });
                }

            }
            else
            {
                System.out.println("Bitte ein Element selektieren!");
            }
        });
    }

    private void showUDStage()
    {
        if (!updateStage.isShowing())
        {
            getUpdateStage().show();
        }
    }

    private void showInsertStage()
    {
        if (!insertStage.isShowing())
        {
            getInsertStage().show();
        }
    }

    public void showInsert()
    {
        add.setOnAction(e ->
        {
            showInsertStage();
        });
    }

    private void handleShowUpdate(String name, String vorname, Integer age)
    {
        this.nameL = new Label("Name");
        this.vornameL = new Label("Vorname");
        this.ageL = new Label("Alter:");
        this.nameTF = new TextField(name);
        this.vornameTF = new TextField(vorname);
        this.ageTF = new TextField(String.valueOf(age));
        this.commitUpdate.setOnAction(e ->
        {
            System.out.println("Index" + lv.getSelectionModel().getSelectedIndex());
            p.updatePerson(lv.getSelectionModel().getSelectedIndex(), nameTF.getText(), vornameTF.getText(), Integer.valueOf(ageTF.getText()));
            getUpdateStage().close();
        });
    }

    public void onDelete()
    {
        delete.setOnAction(e ->
        {
            handleDelete();
        });
    }

    private void handleDelete()
    {
        if (lv.getSelectionModel().getSelectedItems().size() > 0)
        {
            // p.handleDelete(lv.getSelectionModel().getSelectedIndex());
            manager.delete(lv.getSelectionModel().getSelectedIndex());
        }
    }

    public Stage getUpdateStage()
    {
        VBox root = new VBox();
        root.getChildren().addAll(nameL, nameTF, vornameL, vornameTF, ageL, ageTF, commitUpdate);
        updateStage.setScene(new Scene(root));
        updateStage.setTitle("Update");
        return updateStage;
    }

    public Stage getInsertStage()
    {
        VBox root = new VBox();
        this.nameL = new Label("Name");
        this.vornameL = new Label("Vorname");
        this.ageL = new Label("Alter:");
        this.nameTF = new TextField();
        this.vornameTF = new TextField();
        this.ageTF = new TextField();
        ageTF.setPromptText("Insert Age Value");
        vornameTF.setPromptText("Insert Surname");
        nameTF.setPromptText("Insert Name");
        root.getChildren().addAll(nameL, nameTF, vornameL, vornameTF, ageL, ageTF, commitInsert);
        insertStage.setScene(new Scene(root));
        insertStage.setTitle("Update");
        return insertStage;
    }

    public void onInsert()
    {
        commitInsert.setOnAction(e ->
        {
            handleInsert();
        });
    }

    public void handleInsert()
    {
        if (!nameTF.getText().isEmpty() && !vornameTF.getText().isEmpty() && ageTF.getText().matches("[0-9]*") && !ageTF.getText().isEmpty())
        {
            Person person = new Person(nameTF.getText(), vornameTF.getText(), Integer.valueOf(ageTF.getText()));
            p.insertPerson(person);
            Action action = new Action(p, this, person); // erstelle beim
                                                         // hinzufügen neuer
                                                         // Personen ein neues
                                                         // Action Obj mit der
                                                         // Person
            manager.addAction(action);// ADD Action Objekt
            getInsertStage().close();
        }
    }

    public void handleUndo()
    {
        undo.setOnAction(e ->
        {
            System.out.println("Undo Action");
            manager.undo();
        });
    }

    public void handleRedo()
    {
        redo.setOnAction(e ->
        {
            System.out.println("Redo Action");
            manager.redo();
        });
    }

}
