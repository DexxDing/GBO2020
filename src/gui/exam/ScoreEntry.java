package gui.exam;

class ScoreEntry
{
    private String team;

    private int score, gesamtTore, gesamtGegentore, gesamtSiege,
                    gesamtUnentschieden, gesamtNiederlagen;

    public ScoreEntry(String team, int goalsActive, int goalsPassive)
    {
        if (team == null || team.length() == 0)
        {
            throw new IllegalArgumentException("Ungültige Team-Kennung");
        }
        if (goalsActive < 0 || goalsPassive < 0)
        {
            throw new IllegalArgumentException("Negative Torezahl");
        }
        this.team = team;
        update(goalsActive, goalsPassive);
    }

    private void update(int gA, int gP)
    {
        if (gA > gP) // gewonnen
        {
            score += 3;
            gesamtTore += gA;
            gesamtGegentore += gP;
            gesamtSiege++;
            ;
        }
        else if (gA == gP) // unentschieden
        {
            score += 1;
            gesamtTore += gA;
            gesamtGegentore += gP;
            gesamtUnentschieden++;
        }
        else if(gA < gP) { // Lose
        	gesamtNiederlagen++;
        	gesamtTore += gA;
        	gesamtGegentore += gP;
        }
    }

    public void add(ScoreEntry se)
    {
        if (!team.equals(se.team))
        {
            return;
        }
        score += se.score;
        
        gesamtTore += se.gesamtTore;
        gesamtGegentore += se.gesamtGegentore;
        gesamtSiege += se.gesamtSiege;
        gesamtUnentschieden += se.gesamtUnentschieden;
        
    }

    public void subtract(ScoreEntry se)
    {
        if (!team.equals(se.team))
        {
            return;
        }
        score -= se.score;
        
        gesamtTore -= se.gesamtTore;
        gesamtGegentore -= se.gesamtGegentore;
        gesamtSiege -= se.gesamtSiege;
        gesamtUnentschieden -= se.gesamtUnentschieden;
    }

    public String getTeam()
    {
        return team;
    }

    public int getScore()
    {
        return score;
    }

    @Override
    public boolean equals(Object o)
    {
        if (!(o instanceof ScoreEntry))
        {
            return false;
        }
        return team.equals(((ScoreEntry) o).team);
    }

    public String toString()
    {
        return team + ": " + score + "(" + gesamtTore + " : " + gesamtGegentore + " " + gesamtSiege + "/" + gesamtUnentschieden + "/" + gesamtNiederlagen + ")";
    }

}
