package gui.graphics.sinus;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application
{
    private SinusModel m;

    private SinusPresenter p;

    private SinusView v;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        primaryStage.setTitle("Sinus");
        primaryStage.setScene(new Scene(getMVP().getView()));
        primaryStage.show();
    }

    public SinusPresenter getMVP()
    {

        m = new SinusModel();
        p = new SinusPresenter();
        v = new SinusView();

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
