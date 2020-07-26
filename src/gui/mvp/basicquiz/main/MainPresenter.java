package gui.mvp.basicquiz.main;

import gui.mvp.basicquiz.game.QuizPresenter;
import gui.mvp.basicquiz.overview.OverviewPresenter;
import javafx.scene.Parent;

public class MainPresenter
{

    private MainView mv;

    private OverviewPresenter op;

    private QuizPresenter qp;

    public MainPresenter()
    {

    }

    public void setCenter(Parent p)
    {
        mv.setCenter(p);

    }

    public void setMainView(MainView mv)
    {
        this.mv = mv;
    }

    public Parent getView()
    {
        return mv;
    }

    public void setOverviewPresenter(OverviewPresenter op)
    {
        this.op = op;
    }

    public void setQuizPresenter(QuizPresenter qp)
    {
        this.qp = qp;
    }

    public void setCenterQuiz()
    {
        qp.setStartQuestion();
        mv.setCenter(qp.getQuizView());
    }

    public void setReturnQuiz()
    {
        mv.setCenter(qp.getQuizView());
    }

    public void setCenterOverview()
    {
        mv.setCenter(op.getOverView());
    }

    public void addToOverview()
    {
        this.op.addOVContent();
    }

    public void clearOverView()
    {
        op.clearListView();
    }

}
