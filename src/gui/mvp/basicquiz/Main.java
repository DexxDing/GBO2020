package gui.mvp.basicquiz;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import gui.mvp.basicquiz.game.QuizPresenter;
import gui.mvp.basicquiz.game.QuizView;
import gui.mvp.basicquiz.main.MainPresenter;
import gui.mvp.basicquiz.main.MainView;
import gui.mvp.basicquiz.model.Model;
import gui.mvp.basicquiz.overview.OverviewPresenter;
import gui.mvp.basicquiz.overview.OverviewView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application
{

    private static Path dataDir = Paths.get("").toAbsolutePath();

    private static String pathname;

    private Model m;

    private MainPresenter mp;

    private MainView mv;

    private QuizPresenter qp;

    private QuizView qv;

    private OverviewPresenter op;

    private OverviewView ov;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        primaryStage.setTitle("Quiz");
        primaryStage.setScene(new Scene(getMVP().getView(), 400, 600));
        primaryStage.show();
    }

    public MainPresenter getMVP() throws IOException
    {

        m = ModelInitializer.initModel(pathname);

        mp = new MainPresenter();
        mv = new MainView();

        qp = new QuizPresenter();
        qv = new QuizView();

        op = new OverviewPresenter();
        ov = new OverviewView();

        mv.setMainPresenter(mp);
        mp.setMainView(mv);
        mp.setOverviewPresenter(op);
        mp.setQuizPresenter(qp);

        op.setOverviewView(ov);
        op.setModel(m);
        op.setMainPresenter(mp);
        ov.setOverviewPresenter(op);

        qp.setQuizView(qv);
        qp.setModel(m);
        qp.setMainPresenter(mp);
        qv.setQuestionPresenter(qp);

        return mp;
    }

    public static void main(String[] args) throws IOException
    {
        pathname = (dataDir + args[0]);
        System.out.println(pathname);
        launch(args);
    }

}
