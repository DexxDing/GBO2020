package gui.treeview;

import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

/**
 * Merke TreeItem itemName, anstatt TreeItem<Typ> itemName sorgt dafür das nicht
 * nur Parent Objekte rein dürfen
 * 
 * @author bennet
 *
 */
public class View extends TreeView<String>
{

    private View v;

    private Presenter p;

    public void setPresenter(Presenter p)
    {
        this.p = p;
    }

    public View(Presenter p)
    {
        this.p = p;
    }

    public View()
    {
    }

    public void initTreeView()
    {
        p.test();
        this.setRoot(new TreeItem<String>("Root"));

        TreeItem p1 = new TreeItem<>("Personen Objekte");
        p1.getChildren().add(new TreeItem<>(new Person("Peter", 23)));
        p1.getChildren().add(new TreeItem<>(new Person("Gustav", 13)));
        p1.getChildren().add(new TreeItem<>(new Person("Jonas", 24)));
        p1.getChildren().add(new TreeItem<>(new Person("Tim", 43)));
        p1.getChildren().add(new TreeItem<>(new Person("Adrian", 53)));
        this.getRoot().getChildren().add(p1);

        // Ohne Generics beim TreeItem kann Typ kontrolle nicht erfolgen somit
        // alle Objects rein kommen!s
        TreeItem p2 = new TreeItem<String>("MensaPlan"); // inner Root
        TreeItem monday = new TreeItem<String>("Montag");
        monday.getChildren().add(new TreeItem<String>("Schnitzel"));
        TreeItem tuesday = new TreeItem<String>("Dienstag");
        tuesday.getChildren().add(new TreeItem<String>("Spaghetti"));
        TreeItem wednessday = new TreeItem<String>("Mittwoch");
        wednessday.getChildren().add(new TreeItem<String>("Lasagne"));
        TreeItem thursday = new TreeItem<String>("Donnerstag");
        thursday.getChildren().add(new TreeItem<String>("Gyros"));
        TreeItem friday = new TreeItem<String>("Freitag");
        friday.getChildren().add(new TreeItem<String>("Salat"));
        p2.getChildren().addAll(monday, tuesday, wednessday, thursday, friday);
        this.getRoot().getChildren().add(p2);

    }

    public void addItems()
    {
        for (Person person : p.getList())
        {
            TreeItem ti = new TreeItem("Person");
            TreeItem name = new TreeItem("name");
            TreeItem age = new TreeItem("age");
            name.getChildren().add(new TreeItem<String>(person.getName()));
            age.getChildren().add(new TreeItem<Integer>(person.getAge()));
            ti.getChildren().addAll(name, age);

            this.getRoot().getChildren().addAll(ti);
        }
    }
}
