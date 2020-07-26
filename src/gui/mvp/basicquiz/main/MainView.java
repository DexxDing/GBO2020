package gui.mvp.basicquiz.main;

import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;

public class MainView extends BorderPane
{
    private ToolBar toolBar;

    private Button startBtn, returnBtn, overviewBtn;

    private MainPresenter mp;

    public MainView()
    {
        initToolBar();
        this.setTop(toolBar);
        handleStart();
        handleOverview();
        handleReturn();
    }

    public void initToolBar()
    {
        toolBar = new ToolBar();
        this.startBtn = new Button("Quiz starten!");
        this.returnBtn = new Button("Quiz fortsetzen!");
        this.overviewBtn = new Button("Überblick!");
        this.overviewBtn.setId("overview");

        toolBar.getItems().addAll(startBtn, returnBtn, overviewBtn);
    }

    public void handleStart()
    {
        startBtn.setOnAction(e ->
        {
            mp.setCenterQuiz();
        });
    }

    public void handleReturn()
    {
        returnBtn.setOnAction(e ->
        {
            mp.setReturnQuiz();
        });
    }

    public void handleOverview()
    {
        overviewBtn.setOnAction(e ->
        {
            mp.setCenterOverview();
        });
    }

    public void setMainPresenter(MainPresenter mp)
    {
        this.mp = mp;
    }

}
