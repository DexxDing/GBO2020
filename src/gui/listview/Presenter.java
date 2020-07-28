package gui.listview;

import javafx.scene.Parent;

public class Presenter
{

    private Model m;

    private View v;

    public void setModel(Model m)
    {
        this.m = m;
    }

    public void setView(View v)
    {
        this.v = v;
    }

    public Parent getView()
    {
        return this.v;
    }

    public void fillListView()
    {
        v.initListView(m.getPersonOL());
    }

    public void handleDelete(int index)
    {
        m.getPersonOL().remove(index);
    }

    public void updatePerson(int index, String name, String vorname, Integer age)
    {
        Person element = new Person(name, vorname, age);
        m.getPersonOL().remove(index);
        m.getPersonOL().add(index, element);
    }

    public void insertPerson(Person person)
    {
        Person p = person;
        m.getPersonOL().add(p);
    }

    public void delete(Person person)
    {
        m.getPersonOL().remove(person);
    }

    public void add(Person person)
    {
        m.getPersonOL().add(person);
    }
}
