package gui.listview;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Person
{

    private SimpleStringProperty name = new SimpleStringProperty();

    private SimpleStringProperty vornamename = new SimpleStringProperty();

    private SimpleIntegerProperty age = new SimpleIntegerProperty();

    public Person(String name, String vorname, Integer age)
    {
        super();
        this.name.setValue(name);
        this.vornamename.setValue(vorname);
        this.age.setValue(age);
    }

    public SimpleStringProperty getName()
    {
        return name;
    }

    public String getNameValue()
    {
        return name.getValue();
    }

    public void setName(SimpleStringProperty name)
    {
        this.name = name;
    }

    public SimpleStringProperty getVornamename()
    {
        return vornamename;
    }

    public String getVornamenameValue()
    {
        return vornamename.getValue();
    }

    public void setVornamename(SimpleStringProperty vornamename)
    {
        this.vornamename = vornamename;
    }

    public SimpleIntegerProperty getAge()
    {
        return age;
    }

    public Integer getAgeValue()
    {
        return age.getValue();
    }

    public void setAge(SimpleIntegerProperty age)
    {
        this.age = age;
    }

    @Override
    public String toString()
    {
        // TODO Auto-generated method stub
        return "Nachname: " + getNameValue() + " Vorname: " + getVornamenameValue() + " Alter: " + getAgeValue();
    }
}
