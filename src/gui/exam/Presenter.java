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

    public void deleteMatch(Match match)
    {
        if (match != null)
        {
            model.deleteMatch(match);
            view.removeMatch();
            view.updateScore(model.getAllScores());
        }
    }

    public void fillScoreList()
    {
        view.setScoreList(model.getAllScores());
    }

    public void fillMatchList()
    {
        view.setMatchList(model.getAllMatches());
    }

}
