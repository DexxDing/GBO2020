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
        if (gA > gP)
        {
            score += 3;
            setGesamtTore(gesamtTore += gA);
            ;
        }
        else if (gA == gP)
        {
            score += 1;
            gesamtTore += gA;
        }
    }

    public void add(ScoreEntry se)
    {
        if (!team.equals(se.team))
        {
            return;
        }
        score += se.score;
        // gesamtTore = se.gesamtTore;
    }

    public void subtract(ScoreEntry se)
    {
        if (!team.equals(se.team))
        {
            return;
        }
        score -= se.score;
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
        return team + ": " + score + "(" + getGesamtGegentore() + " : " + +getGesamtGegentore() + "/" + getGesamtUnentschieden() + "/" + getGesamtNiederlagen() + ")";
    }

    public int getGesamtTore()
    {
        return gesamtTore;
    }

    public void setGesamtTore(int gesamtTore)
    {
        this.gesamtTore = gesamtTore;
    }

    public int getGesamtGegentore()
    {
        return gesamtGegentore;
    }

    public void setGesamtGegentore(int gesamtGegentore)
    {
        this.gesamtGegentore = gesamtGegentore;
    }

    public int getGesamtSiege()
    {
        return gesamtSiege;
    }

    public void setGesamtSiege(int gesamtSiege)
    {
        this.gesamtSiege = gesamtSiege;
    }

    public int getGesamtUnentschieden()
    {
        return gesamtUnentschieden;
    }

    public void setGesamtUnentschieden(int gesamtUnentschieden)
    {
        this.gesamtUnentschieden = gesamtUnentschieden;
    }

    public int getGesamtNiederlagen()
    {
        return gesamtNiederlagen;
    }

    public void setGesamtNiederlagen(int gesamtNiederlagen)
    {
        this.gesamtNiederlagen = gesamtNiederlagen;
    }

}
