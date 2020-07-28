package gui.treeview;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application
{

    private Model m;

    private View v;

    private Presenter p;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        initMVP();
        primaryStage.setTitle("Person");
        primaryStage.setScene(new Scene(p.getView()));
        primaryStage.show();
    }

    public void initMVP()
    {
        p = new Presenter();
        m = new Model();
        v = new View();
        v.setPresenter(p);
        p.setView(v);
        p.setModel(m);

        v.initTreeView();
        v.addItems();

    }

    public static void main(String[] args)
    {
        launch(args);
    }

}
