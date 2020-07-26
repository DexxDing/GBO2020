package gui.mvp.basicquiztable.game;

import gui.mvp.basicquiztable.main.MainPresenter;
import gui.mvp.basicquiztable.model.Model;
import gui.mvp.basicquiztable.model.Question;
import javafx.scene.Node;

public class QuizviewPresenter
{

    private QuizviewView qv;

    private MainPresenter mp;

    private Model m;

    public QuizviewPresenter()
    {
    }

    public void setQuizviewView(QuizviewView qv)
    {
        this.qv = qv;
    }

    public void setMainPresenter(MainPresenter mp)
    {
        this.mp = mp;
    }

    public void setModel(Model m)
    {
        this.m = m;
    }

    public void fillRadioButtons()
    {
        for (Question q : m.getQuestionL())
        {
            qv.initRadioList(q);
        }
    }

    public void showQuizView()
    {
        mp.setCenter(qv);
    }

    public Node getView()
    {
        return qv;
    }
}
