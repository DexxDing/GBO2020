package gui.mvp.basicquiz.game;

import gui.mvp.basicquiz.main.MainPresenter;
import gui.mvp.basicquiz.model.Model;
import javafx.scene.Parent;

public class QuizPresenter
{

    private int count = 0;

    private QuizView qv;

    private Model m;

    private QuizPresenter qp;

    private MainPresenter mp;

    public QuizPresenter()
    {

    }

    public void setQuizView(QuizView qv)
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

    public Parent getQuizView()
    {
        return qv;
    }

    public void setStartQuestion()
    {
        this.setCount(0);
        mp.clearOverView();
        qv.clearView();
        qv.enableNext();
        qv.setQuestion(m.getQuestionList().get(0).getQuestion());
        qv.setAnswers(m.getQuestionList().get(0).getPossibleAnswers());
    }

    public void showNextQuestion()
    {
        // TODO Fragen werden richtig angezeigt =A> ´Debug
        if (this.getCount() < m.getQuestionList().size())
        {
            System.out.println(getCount());
            this.m.setAnwersCount(m.getQuestionList().get(getCount()).getPossibleAnswers().length);
            this.m.setRightAnswers(m.getQuestionList().get(getCount()).getIndexOfCorrectAnswer());
            this.m.setQuestion(m.getQuestionList().get(getCount()).getQuestion());
            this.mp.addToOverview();

            if (this.getCount() < m.getQuestionList().size() - 1)
            {
                this.setCount(getCount() + 1);
                qv.clearView();
                qv.setQuestion(m.getQuestionList().get(getCount()).getQuestion());
                qv.setAnswers(m.getQuestionList().get(getCount()).getPossibleAnswers());
            }
            else
            {
                qv.handleEndeQuiz();
                this.setCount(1);
            }
        }
    }

    public int getCount()
    {
        return count;
    }

    public void setCount(int count)
    {
        this.count = count;
    }

    public void setReturnQuestion()
    {

    }

    public int getRightIndex()
    {
        return this.m.getQuestionList().get(this.getCount()).getIndexOfCorrectAnswer();
    }
}
