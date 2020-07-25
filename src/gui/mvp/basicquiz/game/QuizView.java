package gui.mvp.basicquiz.game;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

public class QuizView extends VBox
{
    private Label questionL;

    private List<RadioButton> radioBtnList;

    private ToggleGroup tGroup;

    private QuizPresenter qp;

    private Button nextBtn;

    public QuizView()
    {
        initNodes();
        addNodes();
        handleNext();
    }

    private void addNodes()
    {
        this.getChildren().add(questionL);
    }

    public void initNodes()
    {
        questionL = new Label();
        questionL.setId("question");
        nextBtn = new Button("=>");
    }

    public void setQuestionPresenter(QuizPresenter qp)
    {
        this.qp = qp;
    }

    public void setQuestion(String question)
    {
        if (question != null)
        {
            questionL.setText(question);
            this.getChildren().add(questionL);
            questionL.setId("question");
        }
    }

    public void setAnswers(String[] answerArray)
    {
        radioBtnList = new ArrayList<RadioButton>();
        tGroup = new ToggleGroup();
        for (String s : answerArray)
        {
            RadioButton radioButton = new RadioButton(s);
            radioBtnList.add(radioButton);
            this.getChildren().add(radioButton);
            tGroup.getToggles().add(radioButton);
        }
        this.getChildren().add(nextBtn);
    }

    public void clearView()
    {
        this.getChildren().clear();
    }

    public void handleNext()
    {
        nextBtn.setOnAction(e ->
        {
            for (RadioButton rButton : radioBtnList)
            {
                if (rButton.isSelected())
                {

                    if (rButton.getText().equals(radioBtnList.get(qp.getRightIndex()).getText()))
                    {
                        qp.showNextQuestion();
                    }
                }
            }
        });
    }

    public void enableNext()
    {
        nextBtn.setDisable(false);
    }

    public void handleEndeQuiz()
    {
        clearView();
        questionL.setText("Ende des Quiz\n \n");
        this.getChildren().addAll(questionL, nextBtn);
        questionL.setId("question");
        nextBtn.setDisable(true);
    }
}
