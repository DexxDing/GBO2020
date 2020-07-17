package gui.calculator;

import javax.script.ScriptException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Controller
{

    @FXML
    private TextField textfieldID;

    @FXML
    private Button clearID;

    @FXML
    private Button deleteID;

    @FXML
    private Button kommaID;

    @FXML
    private Button gleichID;

    @FXML
    private Button klammerAufID;

    @FXML
    private Button klammerZuID;

    @FXML
    private Button siebenID;

    @FXML
    private Button achtID;

    @FXML
    private Button neunID;

    @FXML
    private Button vierID;

    @FXML
    private Button fünfID;

    @FXML
    private Button sechsID;

    @FXML
    private Button einsID;

    @FXML
    private Button zweiID;

    @FXML
    private Button dreiID;

    @FXML
    private Button addID;

    @FXML
    private Button subID;

    @FXML
    private Button multID;

    @FXML
    private Button divID;

    @FXML
    private Button nullID;

    @FXML
    public void onClear()
    {
        textfieldID.clear();
    }

    public void handleButton(ActionEvent e)
    {
        Button btn = (Button) e.getSource();
        textfieldID.setText(textfieldID.getText() + btn.getText());
    }

    public void calculate()
    {
        Computation computation = new Computation();
        try
        {
            textfieldID.setText(textfieldID.getText() + "=>" + computation.evaluate(textfieldID.getText()));
        }
        catch (ScriptException e)
        {
            this.textfieldID.setText(this.textfieldID.getText() + "=>FEHLER");
        }
    }

    @FXML // wenn man die Methode oder ein Attribut private deklarieren möchte,
          // muss man die Annotation angeben!
    private void onDelte()
    {
        String s = null;
        if (textfieldID.getLength() > 0)
        {
            s = textfieldID.getText(0, textfieldID.getLength() - 1);
        }
        else
        {
            return;
        }
        textfieldID.setText(s);
    }
}
