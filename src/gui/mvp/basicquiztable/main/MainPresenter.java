package gui.mvp.basicquiztable.main;

import gui.mvp.basicquiztable.game.QuizviewPresenter;
import gui.mvp.basicquiztable.overview.OverviewPresenter;
import javafx.scene.Parent;

public class MainPresenter
{

    private MainView mv;

    private OverviewPresenter op;

    private QuizviewPresenter qp;

    public Parent getView()
    {
        return mv;
    }

    public void setMainView(MainView mv)
    {
        this.mv = mv;
    }

    public void setOverviewPresenter(OverviewPresenter op)
    {
        this.op = op;
    }

    public void setQuestionPresenter(QuizviewPresenter qp)
    {
        this.qp = qp;
    }

    public void setCenter(Parent p)
    {
        mv.setCenter(p);
    }

    public void setCenterQuiz()
    {
        this.mv.setCenter(qp.getView());
    }

}
