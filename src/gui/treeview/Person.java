package gui.treeview;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Person
{

    private SimpleStringProperty nameProperty = new SimpleStringProperty();

    private SimpleIntegerProperty ageProperty = new SimpleIntegerProperty();

    public Person()
    {
        // TODO Auto-generated constructor stub
    }

    public Person(String name, Integer age)
    {
        super();
        this.nameProperty.setValue(name);
        this.ageProperty.setValue(age);
    }

    public SimpleStringProperty getNameProperty()
    {
        return nameProperty;
    }

    public void setNameProperty(String nameProperty)
    {
        this.nameProperty.setValue(nameProperty);
    }

    public SimpleIntegerProperty getAgeProperty()
    {
        return ageProperty;
    }

    public void setAgeProperty(Integer ageProperty)
    {
        this.ageProperty.setValue(ageProperty);
    }

    public String getName()
    {
        return nameProperty.getValue();
    }

    public Integer getAge()
    {
        return ageProperty.getValue();
    }

    @Override
    public String toString()
    {
        // TODO Auto-generated method stub
        return "Name: " + getName() + " Alter: " + getAge();
    }

}
