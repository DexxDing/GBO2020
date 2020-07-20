package gui.mvp.vocabtrainer;

import javafx.scene.Parent;

public class Presenter
{
    private View v;

    private Model m;

    private int cnt = 0;

    public void setView(View view)
    {
        this.v = view;
    }

    public void setModel(Model model)
    {
        this.m = model;
    }

    public Parent getView()
    {
        return v;
    }

    public void setVocab()
    {
        if (getCnt() < m.getEnglishL().size())
        {
            v.setWord(m.getVocabMap().get(m.getEnglishL().get(getCnt())));
        }
        else
        {
            System.out.println("Finish all questions");
        }
        setCnt(cnt + 1);
    }

    public void checkRightAnswer(String s)
    {
        System.out.println(s.toLowerCase().trim());
        if (getCnt() < m.getEnglishL().size() && m.getEnglishL().get(getCnt() - 1).toLowerCase().trim().equals(s.toLowerCase().trim()))
        {
            setVocab();
            v.setRightAnswer();
        }
        else
        {
            v.setWrongAnswer();
        }
    }

    private void setCnt(int i)
    {
        this.cnt = i;
    }

    private int getCnt()
    {
        return this.cnt;
    }

}
