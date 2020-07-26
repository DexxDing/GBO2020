package gui.stichpunkte1;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Properties
{
    private SimpleFloatProperty floatProperty = new SimpleFloatProperty();

    private SimpleIntegerProperty intProperty = new SimpleIntegerProperty();

    private SimpleStringProperty stringProperty = new SimpleStringProperty();

    private SimpleDoubleProperty doubleProperty = new SimpleDoubleProperty();

    private ObservableList<String> stringOL = FXCollections.observableArrayList();

    public SimpleFloatProperty getFloatProperty()
    {
        return floatProperty;
    }

    public void setFloatProperty(Float value)
    {
        this.floatProperty.set(value);
    }

    public SimpleIntegerProperty getIntProperty()
    {
        return intProperty;
    }

    public void setIntProperty(Integer value)
    {
        this.intProperty.setValue(value);
    }

    public SimpleStringProperty getStringProperty()
    {
        return stringProperty;
    }

    public void setStringProperty(String value)
    {
        this.stringProperty.setValue(value);
    }

    public SimpleDoubleProperty getDoubleProperty()
    {
        return doubleProperty;
    }

    public void setDoubleProperty(double value)
    {
        this.doubleProperty.set(value);
    }

    public static void main(String[] args)
    {
        Properties properties = new Properties();

        properties.getDoubleProperty().addListener(new ChangeListener<Number>()
        {
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue)
            {
                System.out.println("old value: " + oldValue + " new value: " + newValue);
                properties.setStringProperty(String.valueOf(newValue));
                System.out.println(properties.getStringProperty());
                properties.stringOL.add(properties.getStringProperty().getValue());
            }
        });

        properties.getFloatProperty().addListener((ov, o, n) ->
        {
            System.out.println("Floatproperty oldV: " + o + " Floatproperty: " + n);
        });
        properties.setFloatProperty(2.4f);

        for (double i = 0; i < 5; i++)
        {
            properties.getDoubleProperty().set(i);
        }
        for (String s : properties.stringOL)
        {
            System.out.println(s + " ObservableList");
        }
        if (properties.stringOL.get(3).equals("4.0"))
        {
            System.out.println("Reicht zum bestehen!");
            properties.stringOL.remove(0);
        }
        for (String s : properties.stringOL)
        {
            System.out.println(s + " ObservableList nach Löschen");
        }
    }
}
