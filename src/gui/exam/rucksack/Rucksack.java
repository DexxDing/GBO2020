package gui.exam.rucksack;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Rucksack extends Application
{

    private SplitPane splitPane;

    private TextField nameTF, gewichtTF;

    private TableView<Gegenstand> tv;

    private Stage secondStage;

    private Button addBtn, deleteBtn;

    private Pane drawingPane;

    private BorderPane root;

    private Button hinzufügen = new Button("Hinzufügen");

    private Rectangle emptyBar, fullBar;

    private final static double GESAMTGEWICHT = 500;

    private static double aktuellesGewicht;

    private ObservableList<Gegenstand> ol = FXCollections.observableArrayList(new Gegenstand(20d, "Bier", new CheckBox()), new Gegenstand(140d, "Essen", new CheckBox()), new Gegenstand(250d, "Bier", new CheckBox()), new Gegenstand(120d, "Essen", new CheckBox()), new Gegenstand(210d, "Bier", new CheckBox()), new Gegenstand(101d, "Essen", new CheckBox()), new Gegenstand(220d, "Bier", new CheckBox()), new Gegenstand(102d, "Essen", new CheckBox()));

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        root = new BorderPane();
        splitPane = new SplitPane();
        initTable();
        initPane();
        addToSplitPane();
        root.setCenter(splitPane);
        root.setBottom(addBtn);
        handleSecondStage();
        onPressedHinzufügen();
        primaryStage.setTitle("Rucksack Klausur");
        primaryStage.setScene(new Scene(root, 400, 500));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public void addToSplitPane()
    {
        splitPane.getItems().add(tv);
        splitPane.getItems().add(drawingPane);
    }

    public void initTable()
    {
        tv = new TableView<Gegenstand>(ol);
        TableColumn<Gegenstand, String> nameCol = new TableColumn<>("Name");
        TableColumn<Gegenstand, Double> gewichtCol = new TableColumn<>("Gewicht");
        TableColumn<Gegenstand, CheckBox> checkCol = new TableColumn<>("Auswahl");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        gewichtCol.setCellValueFactory(new PropertyValueFactory<>("gewicht"));
        checkCol.setCellValueFactory(new PropertyValueFactory<>("cb"));

        tv.getColumns().addAll(nameCol, gewichtCol, checkCol);
    }

    public void initPane()
    {
        addBtn = new Button("Add Gegenstand");
        nameTF = new TextField();
        gewichtTF = new TextField();

        drawingPane = new Pane();
        emptyBar = new Rectangle(0, 0, drawingPane.getWidth(), drawingPane.getHeight());
        emptyBar.setFill(null);
        emptyBar.setStroke(Color.BLACK);
        emptyBar.setStrokeWidth(5d);

        fullBar = new Rectangle(0, 0, 200, 200);
        drawingPane.getChildren().addAll(emptyBar, fullBar);

        emptyBar.widthProperty().bind(drawingPane.widthProperty());
        emptyBar.heightProperty().bind(drawingPane.heightProperty());

        fullBar.setX(0);
        fullBar.setY(500);
        System.out.println(emptyBar.getHeight());
        fullBar.setFill(Color.GREEN);

        handleChanges();
    }

    public void handleChanges()
    {
        for (Gegenstand g : ol)
        {
            g.getCb().selectedProperty().addListener((ov, o, n) ->
            {
                if (n)
                {
                    if (aktuellesGewicht + g.getGewicht() < GESAMTGEWICHT)
                    {
                        fullBar.setY(fullBar.getY() - g.getGewicht());
                        fullBar.setHeight(fullBar.getHeight() + g.getGewicht());
                        aktuellesGewicht += g.getGewicht();
                    }
                }
                else
                {
                    System.out.println("abziehen: " + g.getGewicht());
                    fullBar.setY(fullBar.getY() + g.getGewicht());
                    fullBar.setHeight(fullBar.getHeight() - g.getGewicht());
                    aktuellesGewicht -= g.getGewicht();
                }
                refreshCheckBox();
                System.out.println("Name: " + g.getName() + " " + g.getGewicht() + " aktuellGw " + aktuellesGewicht);
            });
        }
    }

    private void refreshCheckBox()
    {
        for (Gegenstand gegenstand : ol)
        {
            if (!gegenstand.getCb().isSelected())
            {
                if (gegenstand.getGewicht() > GESAMTGEWICHT - aktuellesGewicht)
                {
                    gegenstand.getCb().setDisable(true);
                }
            }
            if (gegenstand.getCb().isDisabled())
            {
                if (gegenstand.getGewicht() < GESAMTGEWICHT - aktuellesGewicht)
                {
                    gegenstand.getCb().setDisable(false);
                }
            }
        }
    }

    public void handleSecondStage()
    {
        addBtn.setOnAction(e ->
        {
            secondStage = new Stage();
            VBox vbox = new VBox(10);
            vbox.getChildren().addAll(new Label("Name"), nameTF, new Label("Gewicht: "), gewichtTF, hinzufügen);
            secondStage.setTitle("Füge Gegenstand hinzu");
            secondStage.setScene(new Scene(vbox));
            secondStage.show();

        });
    }

    public void onPressedHinzufügen()
    {
        hinzufügen.setOnAction(e ->
        {
            ol.add(new Gegenstand(Double.valueOf(gewichtTF.getText()), nameTF.getText(), new CheckBox()));
            handleChanges();
        });
    }

    public static void main(String[] args)
    {
        launch(args);
    }

}
