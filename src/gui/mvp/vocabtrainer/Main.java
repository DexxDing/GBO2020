package gui.mvp.vocabtrainer;

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
        primaryStage.setTitle("Vokabel-Training");
        primaryStage.setScene(new Scene(initMVP().getView(), 400, 200));
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
        v = new View();
        v.setPresenter(p);
        p.setView(v);
        p.setModel(m);
        p.setVocab();

        return p;
    }
}
