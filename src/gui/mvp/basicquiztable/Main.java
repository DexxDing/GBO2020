package gui.mvp.basicquiztable;

import java.io.IOException;

import gui.mvp.basicquiztable.game.QuizviewPresenter;
import gui.mvp.basicquiztable.game.QuizviewView;
import gui.mvp.basicquiztable.main.MainPresenter;
import gui.mvp.basicquiztable.main.MainView;
import gui.mvp.basicquiztable.model.Model;
import gui.mvp.basicquiztable.overview.OverviewPresenter;
import gui.mvp.basicquiztable.overview.OverviewView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application
{
    private static Model m;

    private MainPresenter mp;

    private MainView mv;

    private OverviewView ov;

    private OverviewPresenter op;

    private QuizviewView qv;

    private QuizviewPresenter qp;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        initMVP();
        primaryStage.setTitle("Basic Quiz with TableView");
        primaryStage.setScene(new Scene(mp.getView()));
        primaryStage.show();
    }

    public void initMVP()
    {
        mp = new MainPresenter();
        mv = new MainView();
        ov = new OverviewView();
        op = new OverviewPresenter();
        qv = new QuizviewView();
        qp = new QuizviewPresenter();

        // wire mvp connections
        mv.setMainPresenter(mp);
        mp.setMainView(mv);
        mp.setOverviewPresenter(op);
        mp.setQuestionPresenter(qp);

        qv.setQuizviewPresenter(qp);
        qp.setQuizviewView(qv);
        qp.setMainPresenter(mp);
        qp.setModel(m);
        qp.fillRadioButtons();
        qv.addToContainer();

        ov.setOverviewPresenter(op);
        op.setOverviewView(ov);
        op.setMainPresenter(mp);
        op.setModel(m);

        qp.showQuizView();

    }

    public static void main(String[] args) throws IOException
    {
        m = ModelInitializer.initModel(args[0]);

        launch(args);
    }

}
