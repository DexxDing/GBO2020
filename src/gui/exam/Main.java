package gui.exam;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application
{
    private Model m;

    private Presenter p;

    private View v;

    private DialogView dv;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        m = new Model();
        v = new View();
        p = new Presenter();

        v.setPresenter(p);
        p.setModel(m);
        p.setView(v);

        primaryStage.setTitle("Ergebnisse");
        primaryStage.setScene(new Scene(v));
        primaryStage.show();
    }

    public Presenter getPresenter()
    {
        m = new Model();
        v = new View();
        p = new Presenter();

        v.setPresenter(p);
        p.setModel(m);
        p.setView(v);

        return p;
    }

    public static void main(String[] args)
    {
        launch(args);
    }

}
