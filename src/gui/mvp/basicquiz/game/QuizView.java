package gui.mvp.basicquiz.game;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.VBox;

public class QuizView extends VBox
{
    private Label questionL;

    private RadioButton answerRB;

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
        }
    }

    public void setAnswers(String[] answerArray)
    {
        for (String s : answerArray)
        {
            this.getChildren().add(new RadioButton(s));
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
            qp.showNextQuestion();
            System.out.println("durch");
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
        nextBtn.setDisable(true);
    }
}
