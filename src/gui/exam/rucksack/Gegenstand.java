package gui.exam.rucksack;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;

public class Gegenstand
{
    private SimpleDoubleProperty gewicht = new SimpleDoubleProperty();

    private SimpleStringProperty name = new SimpleStringProperty();

    private CheckBox cb;

    public Gegenstand(Double gewicht, String name, CheckBox cb)
    {
        super();
        this.gewicht.setValue(gewicht);
        this.name.setValue(name);
        this.cb = new CheckBox();
    }

    public Double getGewicht()
    {
        return gewicht.getValue();
    }

    public void setGewicht(Double gewicht)
    {
        this.gewicht.setValue(gewicht);
        ;
    }

    public String getName()
    {
        return name.getValue();
    }

    public void setName(String name)
    {
        this.name.setValue(name);
    }

    public CheckBox getCb()
    {
        return cb;
    }

    public void setCb(CheckBox cb)
    {
        this.cb = cb;
    }
}
