package gui.treeview;

import javafx.collections.ObservableList;
import javafx.scene.Parent;

public class Presenter
{

    private View v;

    private Model m;

    public Presenter()
    {
    }

    public void setView(View v)
    {
        this.v = v;
    }

    public void setModel(Model m)
    {
        this.m = m;
    }

    void test()
    {
        System.out.println("vom presenter hallo");
    }

    public Parent getView()
    {
        return this.v;
    }

    public ObservableList<Person> getList()
    {
        return m.getOl();
    }
}
