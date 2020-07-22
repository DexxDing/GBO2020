package gui.mvp.basicquiz.overview;

import gui.mvp.basicquiz.main.MainPresenter;
import gui.mvp.basicquiz.model.Model;
import gui.mvp.basicquiz.model.Question;
import javafx.collections.ObservableList;
import javafx.scene.Parent;

public class OverviewPresenter
{

    private Model m;

    private OverviewView ov;

    private MainPresenter mp;

    private int answers = 0;

    private int rightAnswers = 0;

    public OverviewPresenter()
    {

    }

    public void setModel(Model m)
    {
        this.m = m;
    }

    public void setOverviewView(OverviewView ov)
    {
        this.ov = ov;
    }

    public void resetCounts()
    {
        this.setAnswers(0);
        this.setRightAnswers(0);
    }

    public int getAnswers()
    {
        return answers;
    }

    public void setAnswers(int answers)
    {
        this.answers = answers;
    }

    public int getRightAnswers()
    {
        return rightAnswers;
    }

    public void setRightAnswers(int rightAnswers)
    {
        this.rightAnswers = rightAnswers;
    }

    public void setMainPresenter(MainPresenter mp)
    {
        this.mp = mp;
    }

    public Parent getOverView()
    {
        return this.ov;
    }

    public void addOVContent()
    {
        ov.addListElement(m.getQuestion(), m.getAnwersCount(), m.getRightAnswers());
    }

    public void clearListView()
    {
        ov.clearContent();
    }

    public ObservableList<Question> getQuestionList()
    {
        // TODO Auto-generated method stub
        return (ObservableList<Question>) m.getQuestionList();
    }

}
