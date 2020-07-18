package gui.pizza;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Pizza extends Application
{
    private GridPane root;

    private TextArea display;

    private Configuration config = null;

    private List<CheckBox> toppingNameCheckBoxList;

    private ToggleGroup toggleGroup = null;

    private List<RadioButton> sizeNameRadioButtonList;

    private Button bestellen;

    private static Map<String, String> parameterMap = null;

    private CheckBox showGridLines;

    private String zutaten;

    public String getZutaten()
    {
        return zutaten;
    }

    public void setZutaten(String zutaten)
    {
        this.zutaten = zutaten;
    }

    private String groesse;

    public String getGroesse()
    {
        return groesse;
    }

    public void setGroesse(String groesse)
    {
        this.groesse = groesse;
    }

    private DecimalFormat decimalFormat = new DecimalFormat("#0.00");

    private int gesamtPreis;

    public int getGesamtPreis()
    {
        return gesamtPreis;
    }

    public void setGesamtPreis(int gesamtPreis)
    {
        this.gesamtPreis = gesamtPreis;
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        root = new GridPane();
        root.setPadding(new Insets(10));
        parameterMap = getParameters().getNamed();
        this.config = ParameterConverter.createConfiguration(parameterMap);
        initCheckBoxes();
        initRadioBoxes();
        initBestellButton();
        initTextArea();
        handleGridLinesVisible();
        onBestellen();
        primaryStage.setTitle("Lieferando");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void initCheckBoxes()
    {
        toppingNameCheckBoxList = new ArrayList<>();
        for (String s : this.config.getToppingNames())
        {
            CheckBox cb = new CheckBox(s);
            toppingNameCheckBoxList.add(cb);
        }
        int count = 0;
        for (int i = 0; i < toppingNameCheckBoxList.size(); i++)
        {
            for (int j = 0; j <= 1; j++)
            {
                if (count < toppingNameCheckBoxList.size())
                {
                    root.add(toppingNameCheckBoxList.get(count), j, i);
                    GridPane.setMargin(toppingNameCheckBoxList.get(count), new Insets(5));
                }
                count++;
            }
        }
        for (int i = 0; i <= config.getNumberOfDefaultToppings() - 1; i++)
        {
            toppingNameCheckBoxList.get(i).setSelected(true);
            toppingNameCheckBoxList.get(i).setDisable(true);
        }
    }

    public void initRadioBoxes()
    {
        toggleGroup = new ToggleGroup();
        sizeNameRadioButtonList = new ArrayList<RadioButton>();
        for (String s : config.getSizeNames())
        {
            RadioButton rb = new RadioButton(s);
            toggleGroup.getToggles().addAll(rb);
            sizeNameRadioButtonList.add(rb);
        }
        sizeNameRadioButtonList.get(0).setSelected(true);
        int count = 0;
        for (RadioButton rb : sizeNameRadioButtonList)
        {
            if (count < config.getSizeNames().length)
            {
                root.add(rb, count, 6);
                GridPane.setMargin(sizeNameRadioButtonList.get(count), new Insets(5));
                count++;
            }
        }
    }

    public void initBestellButton()
    {
        bestellen = new Button("Bestellen!");
        root.add(bestellen, 0, 7);
    }

    public void initTextArea()
    {
        display = new TextArea();
        display.setId("bestelltext");
        display.setEditable(false);
        root.add(display, 0, 8, 3, 3);
    }

    public void handleGridLinesVisible()
    {
        showGridLines = new CheckBox("show GridLines");
        root.gridLinesVisibleProperty().bind(showGridLines.selectedProperty());
        root.add(showGridLines, 3, 0);
    }

    public void onBestellen()
    {
        bestellen.setOnAction(e ->
        {
            handleBestellung();
        });
    }

    private void handleBestellung()
    {
        StringBuilder sb = new StringBuilder();
        String top = "Sie haben eine Pizza bestellt.\n";
        this.groesse = "Die Größe ist: ";
        this.zutaten = "Zutaten ";
        this.gesamtPreis = 0;

        for (CheckBox cb : toppingNameCheckBoxList)
        {
            if (cb.isSelected())
            {
                setZutaten(this.zutaten.concat(cb.getText() + ", "));
                setGesamtPreis(getGesamtPreis() + config.getToppingPrices()[toppingNameCheckBoxList.indexOf(cb)]);
            }
        }
        this.zutaten = this.zutaten.substring(0, zutaten.lastIndexOf(",")) + "\n";

        for (RadioButton rb : sizeNameRadioButtonList)
        {
            if (rb.isSelected())
            {
                setGroesse(this.groesse.concat(rb.getText() + "\n"));
                setGesamtPreis(getGesamtPreis() + config.getSizePrices()[sizeNameRadioButtonList.indexOf(rb)]);
            }
        }
        sb.append(top + zutaten + groesse + "Der Preis beträgt: " + decimalFormat.format(((double) this.gesamtPreis) / 100) + "€.\nVielen Dank.");
        display.setText(sb.toString());
    }

    // zum Testen einfach die Kommando Zeilen Argumente übergeben
    public static void main(String[] args)
    {
        for (String s : args)
        {
            System.out.println(s);
        }
        launch(args);
    }

}
