package gui.mvp.basicquiztable.main;

import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;

public class MainView extends BorderPane
{

    private MainPresenter mp;

    private Button startBtn, returnBtn, overviewBtn;

    private ToolBar toolBar;

    public void setMainPresenter(MainPresenter mp)
    {
        this.mp = mp;
    }

    public MainView()
    {
        initToolbar();
    }

    public void onStart()
    {
        startBtn.setOnAction(e ->
        {
            mp.setCenterQuiz();
        });
    }

    public void initToolbar()
    {
        startBtn = new Button("Quiz starten!");
        returnBtn = new Button("Quiz fortsetzen!");
        overviewBtn = new Button("Überblick!");
        toolBar = new ToolBar(startBtn, returnBtn, overviewBtn);
        this.setTop(toolBar);
    }

}
