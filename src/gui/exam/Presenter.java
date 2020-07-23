package gui.exam;

import javafx.scene.Parent;

public class Presenter
{
    private Model model;

    private View view;

    public void setModel(Model m)
    {
        this.model = m;
    }

    public void setView(View v)
    {
        this.view = v;
    }

    public Parent getView()
    {
        return this.view;
    }

    public void addNewMatch(Match match)
    {
        view.addMatch(match);
        model.addMatch(match);
        view.updateScore(model.getAllScores());
    }

}
