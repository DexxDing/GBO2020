package gui.mvp.basicquiztable.model;

import java.util.ArrayList;
import java.util.List;

public class Model
{
    private Question q;

    private int tryCount;

    private int rightCount;

    private List<Question> questionL;

    public Model()
    {
        questionL = new ArrayList<Question>();
    }

    public void addQuestion(Question q)
    {
        this.q = q;
        questionL.add(q);
        for (Question s : questionL)
        {
            System.out.println(s.getQuestion());
        }
    }

    public Question getQ()
    {
        return q;
    }

    public void setQ(Question q)
    {
        this.q = q;
    }

    public List<Question> getQuestionL()
    {
        return questionL;
    }

    public void setQuestionL(List<Question> questionL)
    {
        this.questionL = questionL;
    }

    public int getRightCount()
    {
        return rightCount;
    }

    public void setRightCount(int rightCount)
    {
        this.rightCount = rightCount;
    }

    public int getTryCount()
    {
        return tryCount;
    }

    public void setTryCount(int tryCount)
    {
        this.tryCount = tryCount;
    }

}
