package gui.uebung6_1;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * 
 * @author bennet
 * @category Aufgabe: In dieser Aufgabe soll gezeigt werden, dass es sinnvoll
 *           sein kann, zu einer ComboBox nicht Strings, sondern Objekte eines
 *           anderen Typs hinzuzufügen.
 */
public class LaenderInformation extends Application
{
    private ComboBox<Country> comboBox;

    private GridPane root;

    private CheckBox exactCB;

    private DecimalFormat formatter1 = new DecimalFormat("##,###,###");

    private DecimalFormat formatter2 = new DecimalFormat("#,###,###");

    private DecimalFormat formatter3 = new DecimalFormat("###,###");

    private DecimalFormat formatter4 = new DecimalFormat("##,###");

    private DecimalFormat formatter5 = new DecimalFormat("#,###");

    private ObservableList<Country> countryList = FXCollections.observableArrayList();

    private Label landL, hauptstadtL, einwohnerL, flaecheL, dichteL;

    private TextField landTF, hauptstadtTF, einwohnerTF, flaecheTF;

    private Button addLandBtn;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        root = new GridPane();
        // root.setGridLinesVisible(true);
        initComboBox();
        addToContainer();
        initExactCB();
        handleLandSelection();
        initLabels();
        Scene scene = new Scene(root, 800, 220);
        primaryStage.setTitle("Länder-Informationen");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        initButton();
        handleLandSelection();
        initTextFields();
        handleHinzufuegen();
    }

    public void initButton()
    {
        addLandBtn = new Button("Hinzufügen");
        root.add(addLandBtn, 4, 8);
    }

    public void initComboBox()
    {
        Country kanada = new Country("Kanada", "Ottawa", 34278406, 9984670);
        Country luxemburg = new Country("Luxemburg", "Luxemburg", 511804, 2586);
        countryList.addAll(kanada, luxemburg);
        comboBox = new ComboBox<Country>();
        comboBox.getItems().addAll(countryList);
    }

    public void addToContainer()
    {
        root.add(comboBox, 0, 0);
        GridPane.setMargin(comboBox, new Insets(5));
    }

    public void initExactCB()
    {
        exactCB = new CheckBox("exakte Angaben");
        root.add(exactCB, 0, 2);
        GridPane.setMargin(exactCB, new Insets(5));
    }

    public void initLabels()
    {
        List<Label> labelList = new ArrayList<Label>();
        landL = new Label("Land: ");
        labelList.add(landL);
        hauptstadtL = new Label("Hauptstadt: ");
        labelList.add(hauptstadtL);
        einwohnerL = new Label("Einwohner: ");
        labelList.add(einwohnerL);
        flaecheL = new Label("Fläche (in qkm):");
        labelList.add(flaecheL);
        dichteL = new Label("Bevölkerungsdichte (in Personen pr qkm): ");
        labelList.add(dichteL);
        for (int i = 0; i < labelList.size(); i++)
        {
            root.add(labelList.get(i), 0, i + 3);
            GridPane.setMargin(labelList.get(i), new Insets(5));
        }
    }

    public void handleLandSelection()
    {
        comboBox.setOnAction(e ->
        {
            if (exactCB.isSelected() && countryList.size() > 0)
            {
                landL.setText("Land: " + (comboBox.getSelectionModel().getSelectedItem().getName()));
                hauptstadtL.setText("Hauptstadt: " + (comboBox.getSelectionModel().getSelectedItem().getCapital()));
                einwohnerL.setText("Einwohner: " + (getExactFormat(comboBox.getSelectionModel().getSelectedItem().getPeople())));
                flaecheL.setText("Fläche (in qkm): " + (getExactFormat(comboBox.getSelectionModel().getSelectedItem().getArea())));
                dichteL.setText("Bevölkerungsdichte (in Personen pr qkm): " + (getExactFormat(Long.valueOf(comboBox.getSelectionModel().getSelectedItem().getDensity()))));
            }
            else if (!exactCB.isSelected() && countryList.size() > 0)
            {
                landL.setText("Land: " + (comboBox.getSelectionModel().getSelectedItem().getName()));
                hauptstadtL.setText("Hauptstadt: " + (comboBox.getSelectionModel().getSelectedItem().getCapital()));
                einwohnerL.setText("Einwohner: " + (getRoundFormat(comboBox.getSelectionModel().getSelectedItem().getPeople())));
                flaecheL.setText("Fläche (in qkm): " + (getRoundFormat(comboBox.getSelectionModel().getSelectedItem().getArea())));
                dichteL.setText("Bevölkerungsdichte (in Personen pr qkm): " + (getRoundFormat(Long.valueOf(comboBox.getSelectionModel().getSelectedItem().getDensity()))));
            }
        });
    }

    public String getExactFormat(Long value)
    {
        String formattedValue = null;
        if (value > 9999999)
        {
            formattedValue = formatter1.format(value);
        }
        else if (value > 999999)
        {
            formattedValue = formatter2.format(value);
        }
        else if (value > 99999)
        {
            formattedValue = formatter3.format(value);
        }
        else if (value > 9999)
        {
            formattedValue = formatter4.format(value);
        }
        else if (value > 999)
        {
            formattedValue = formatter5.format(value);
        }
        else
        {
            formattedValue = "" + value;
        }
        return formattedValue;
    }

    public String getRoundFormat(Long value)
    {

        String formattedValue = null;
        if (value > 9999999)
        {
            formattedValue = formatter1.format(Math.round(((double) value / 1000000)));
            formattedValue = formattedValue + " Mil";
        }
        else if (value > 999999)
        {
            formattedValue = formatter2.format(Math.round(((double) value / 100000)));
            formattedValue = formattedValue + " Mil";
        }
        else if (value > 99999)
        {
            formattedValue = formatter3.format(Math.round(((double) value / 1000)));
            formattedValue = formattedValue + " Mil";
        }
        else if (value > 9999)
        {
            formattedValue = formatter4.format(Math.round(((double) value / 1000)) * 1000);
            ;
        }
        else if (value > 999)
        {
            formattedValue = formatter5.format(Math.round(((double) value / 1000)) * 1000);
        }
        else if (value > 99)
        {
            formattedValue = value % 10 < 4 ? "" + (value - 1) : "" + (value + 1);
        }
        else
        {
            formattedValue = "" + value;
        }
        return formattedValue;
    }

    public void initTextFields()
    {
        landTF = new TextField();
        landTF.setPromptText("Land");
        hauptstadtTF = new TextField();
        hauptstadtTF.setPromptText("Hauptstadt");
        einwohnerTF = new TextField();
        einwohnerTF.setPromptText("Einwohner");
        flaecheTF = new TextField();
        flaecheTF.setPromptText("Fläche");
        root.add(landTF, 0, 8);
        root.add(hauptstadtTF, 1, 8);
        root.add(einwohnerTF, 2, 8);
        root.add(flaecheTF, 3, 8);
    }

    public void handleHinzufuegen()
    {
        addLandBtn.setOnAction(e ->
        {
            if (landTF.getText() != null && hauptstadtTF != null && einwohnerTF.getText() != null && flaecheTF.getText() != null)
            {
                countryList.add(new Country(landTF.getText(), hauptstadtTF.getText(), Long.valueOf(einwohnerTF.getText()), Long.valueOf(flaecheTF.getText())));
                comboBox.getItems().clear();
                comboBox.getItems().addAll(countryList);
            }
        });
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
