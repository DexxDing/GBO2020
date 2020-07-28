package gui.treeview;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Model
{
    private ObservableList<Person> ol = FXCollections.observableArrayList();

    public ObservableList<Person> getOl()
    {
        return ol;
    }

    public void setOl(ObservableList<Person> ol)
    {
        this.ol = ol;
    }

    public void addPerson(Person p)
    {
        this.ol.add(p);
    }

    public Model()
    {
        initPersonList();
    }

    public void initPersonList()
    {
        for (int i = 0; i < 10; i++)
        {
            addPerson(new Person("Name:" + i, i + 10));
        }
    }

}
