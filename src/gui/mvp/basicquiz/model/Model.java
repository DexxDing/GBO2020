package gui.mvp.basicquiz.model;

import java.util.ArrayList;
import java.util.List;

public class Model
{
    private List<Question> questionList;

    private int rightAnswers = 0;

    private int anwersCount = 0;

    private String question;

    public Model()
    {
        questionList = new ArrayList<>();
    }

    public void addQuestion(Question q)
    {
        questionList.add(q);
    }

    public List<Question> getQuestionList()
    {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList)
    {
        this.questionList = questionList;
    }

    public int getRightAnswers()
    {
        return rightAnswers;
    }

    public void setRightAnswers(int rightAnswers)
    {
        this.rightAnswers = rightAnswers;
    }

    public int getAnwersCount()
    {
        return anwersCount;
    }

    public void setAnwersCount(int anwersCount)
    {
        this.anwersCount = anwersCount;
    }

    public void setQuestion(String question)
    {
        this.question = question;
    }

    public String getQuestion()
    {
        return question;
    }

}
