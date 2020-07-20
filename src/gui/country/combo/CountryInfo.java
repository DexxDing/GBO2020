package gui.country.combo;

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
public class CountryInfo extends Application
{
    private ComboBox<Country> comboBox;

    private GridPane root;

    private CheckBox exactCheckBox;

    private DecimalFormat formatter1 = new DecimalFormat("##,###,###");

    private DecimalFormat formatter2 = new DecimalFormat("#,###,###");

    private DecimalFormat formatter3 = new DecimalFormat("###,###");

    private DecimalFormat formatter4 = new DecimalFormat("##,###");

    private DecimalFormat formatter5 = new DecimalFormat("#,###");

    private ObservableList<Country> countryList = FXCollections.observableArrayList();

    private Label landL, hauptstadtL, einwohnerL, flaecheL, dichteL;

    private TextField landTF, hauptstadtTF, einwohnerTF, flaecheTF;

    private Button addBtn, deleteBtn;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        root = new GridPane();
        // root.setGridLinesVisible(true);
        initComboBox();
        addToContainer();
        initCheckBox();
        handleLandSelection();
        comboBox.getSelectionModel().selectFirst();
        initLabels();
        Scene scene = new Scene(root, 800, 240);
        primaryStage.setTitle("Länder-Informationen");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        initButton();
        initTextFields();
        handleHinzufuegen();
        handleLandSelection();
        handleDelete();
    }

    public void initButton()
    {
        addBtn = new Button("Hinzuf\u00fcgen");
        addBtn.setId("add");
        deleteBtn = new Button("L\u00f6schen");
        deleteBtn.setId("delete");
        root.add(addBtn, 4, 8);
        root.add(deleteBtn, 0, 9);
    }

    public void initComboBox()
    {
        Country kanada = new Country("Kanada", "Ottawa", 34278406, 9984670);
        Country luxemburg = new Country("Luxemburg", "Luxemburg", 511840, 2586);
        Country china = new Country("China", "Peking", 1349585838, 9571302);
        Country cookinseln = new Country("Cookinseln", "Avarua", 18631, 242);
        Country blabla = new Country("Taka-Tuka-Land", "Säbelweiler", 467, 25);
        countryList.addAll(kanada, luxemburg, china, cookinseln, blabla);
        comboBox = new ComboBox<Country>();
        comboBox.setId("countrySelector");
        comboBox.getItems().addAll(countryList);
    }

    public void addToContainer()
    {
        root.add(comboBox, 0, 0);
        GridPane.setMargin(comboBox, new Insets(5));
    }

    public void initCheckBox()
    {
        exactCheckBox = new CheckBox("exakte Angaben");
        exactCheckBox.setId("exactValues");
        root.add(exactCheckBox, 0, 2);
        GridPane.setMargin(exactCheckBox, new Insets(5));
        exactCheckBox.selectedProperty().addListener((ov, o, n) ->
        {
            if (countryList.size() > 0)
            {

                if (n)
                {
                    setExactText();
                }
                else
                {
                    setRoundText();
                }
            }
        });
    }

    public void handleDelete()
    {
        deleteBtn.setOnAction(e ->
        {
            System.out.println("Country List size = " + countryList.size());
            if (countryList.size() > 0)
            {
                countryList.remove(comboBox.getSelectionModel().getSelectedIndex());
                comboBox.getItems().clear();
                comboBox.getItems().addAll(countryList);
                comboBox.getSelectionModel().selectLast();
            }
            else if (countryList.isEmpty())
            {
                comboBox.setPromptText("Keine L\u00e4nder vorhanden");
                initEmptyLabels();
            }
        });
    }

    public void initLabels()
    {
        List<Label> labelList = new ArrayList<Label>();
        landL = new Label(comboBox.getSelectionModel().getSelectedItem().getName());
        landL.setId("countryName");
        labelList.add(landL);
        hauptstadtL = new Label(comboBox.getSelectionModel().getSelectedItem().getCapital());
        hauptstadtL.setId("capital");
        labelList.add(hauptstadtL);
        einwohnerL = new Label(getRoundFormat(comboBox.getSelectionModel().getSelectedItem().getPeople()));
        einwohnerL.setId("population");
        labelList.add(einwohnerL);
        flaecheL = new Label(getRoundFormat(comboBox.getSelectionModel().getSelectedItem().getArea()));
        flaecheL.setId("area");
        labelList.add(flaecheL);
        dichteL = new Label(getRoundFormat(Long.valueOf(comboBox.getSelectionModel().getSelectedItem().getDensity())));
        dichteL.setId("density");
        labelList.add(dichteL);
        for (int i = 0; i < labelList.size(); i++)
        {
            root.add(labelList.get(i), 0, i + 3);
            GridPane.setMargin(labelList.get(i), new Insets(5));
        }
    }

    public void initEmptyLabels()
    {
        landL.setText("Land: ");
        hauptstadtL.setText("Hauptstadt: ");
        einwohnerL.setText("Einwohner: ");
        flaecheL.setText("Fläche (in qkm): ");
        dichteL.setText("Bevölkerungsdichte (in Personen pro qkm)");
    }

    public void handleLandSelection()
    {
        comboBox.valueProperty().addListener((ov, o, n) ->
        {
            try
            {
                if (exactCheckBox.isSelected() && countryList.size() > 0 && o != n)
                {
                    setExactText();
                }
                else if (!exactCheckBox.isSelected() && countryList.size() > 0 && o != n)
                {
                    setRoundText();
                }
            }
            catch (Exception nullPointer)
            {

            }
        });
    }

    public void setRoundText()
    {
        landL.setText(comboBox.getSelectionModel().getSelectedItem().getName());
        hauptstadtL.setText(comboBox.getSelectionModel().getSelectedItem().getCapital());
        einwohnerL.setText(getRoundFormat(comboBox.getSelectionModel().getSelectedItem().getPeople()));
        flaecheL.setText(getRoundFormat(comboBox.getSelectionModel().getSelectedItem().getArea()));
        dichteL.setText(getRoundFormat(Long.valueOf(comboBox.getSelectionModel().getSelectedItem().getDensity())));
    }

    public void setExactText()
    {
        try
        {
            landL.setText(comboBox.getSelectionModel().getSelectedItem().getName());
            hauptstadtL.setText(comboBox.getSelectionModel().getSelectedItem().getCapital());
            einwohnerL.setText(getExactFormat(comboBox.getSelectionModel().getSelectedItem().getPeople()));
            flaecheL.setText(getExactFormat(comboBox.getSelectionModel().getSelectedItem().getArea()));
            dichteL.setText("" + (Long.valueOf(comboBox.getSelectionModel().getSelectedItem().getDensity())));
        }
        catch (NullPointerException np)
        {
            // TODO: handle exception
        }
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
            formattedValue = formatter3.format(Math.round(((double) value / 1000)) * 1000);
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
        landTF.setId("countryField");
        hauptstadtTF = new TextField();
        hauptstadtTF.setId("capitalField");
        hauptstadtTF.setPromptText("Hauptstadt");
        einwohnerTF = new TextField();
        einwohnerTF.setId("populationField");
        einwohnerTF.setPromptText("Einwohner");
        flaecheTF = new TextField();
        flaecheTF.setId("areaField");
        flaecheTF.setPromptText("Fläche");
        root.add(landTF, 0, 8);
        root.add(hauptstadtTF, 1, 8);
        root.add(einwohnerTF, 2, 8);
        root.add(flaecheTF, 3, 8);
    }

    public void resetTextFields()
    {
        landTF.setText("");
        hauptstadtTF.setText("");
        einwohnerTF.setText("");
        flaecheTF.setText("");
    }

    public void handleHinzufuegen()
    {
        addBtn.setOnAction(e ->
        {
            if (landTF.getText() != null && hauptstadtTF != null && einwohnerTF.getText() != null && flaecheTF.getText() != null)
            {
                this.countryList.add(new Country(landTF.getText(), hauptstadtTF.getText(), Long.valueOf(einwohnerTF.getText()), Long.valueOf(flaecheTF.getText())));
                comboBox.getItems().clear();
                comboBox.getItems().addAll(countryList);
                resetTextFields();
                comboBox.getSelectionModel().selectLast();
            }
        });
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
