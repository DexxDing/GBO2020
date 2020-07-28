package gui.listview;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application
{
    private Model m;

    private Presenter p;

    private View v;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        primaryStage.setTitle("ListView Example");
        primaryStage.setScene(new Scene(initMVP().getView()));
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }

    public Presenter initMVP()
    {
        m = new Model();
        p = new Presenter();
        v = new View(p);

        p.setModel(m);
        p.setView(v);

        v.initView();

        return p;
    }

}
