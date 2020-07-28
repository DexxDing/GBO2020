package gui.listview;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Model
{
    ObservableList<Person> personOL = FXCollections.observableArrayList();

    public ObservableList<Person> getPersonOL()
    {
        return personOL;
    }

    public void setPersonOL(ObservableList<Person> personOL)
    {
        this.personOL = personOL;
    }

    public void addPerson(Person p)
    {
        getPersonOL().add(p);
    }

}
