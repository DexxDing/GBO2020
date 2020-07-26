package gui.mvp.basicquiztable.game;

import java.util.ArrayList;
import java.util.List;

import gui.mvp.basicquiztable.model.Question;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class QuizviewView extends VBox
{

    private QuizviewPresenter qp;

    private Text questionText;

    private ToggleGroup tg;

    private List<RadioButton> radioBtnList;

    private Button nextBtn;

    public void setQuizviewPresenter(QuizviewPresenter qp)
    {
        this.qp = qp;
    }

    public QuizviewView()
    {
        initView();
    }

    public void initView()
    {
        radioBtnList = new ArrayList<RadioButton>();
        tg = new ToggleGroup();
        questionText = new Text();
        nextBtn = new Button("=>");
        this.getChildren().add(questionText);
    }

    public void initRadioList(Question q)
    {
        RadioButton rb = new RadioButton(q.getQuestion());
        tg.getToggles().add(rb);
        radioBtnList.add(rb);
    }

    public void addToContainer()
    {
        for (RadioButton radioButton : radioBtnList)
        {
            this.getChildren().addAll(radioButton);
        }
        this.getChildren().add(nextBtn);
    }
}
