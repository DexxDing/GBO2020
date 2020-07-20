package gui.country.combo;

public class Country
{
    private String name;

    private String capital;

    private long people;

    private long area;

    private int density;

    public Country(String name, String capital, long people, long area)
    {
        this.name = name;
        this.capital = capital;
        this.people = people;
        this.area = area;
        this.density = (int) (this.people / this.area);
    }

    public int getDensity()
    {
        return density;
    }

    public void setDensity(int density)
    {
        this.density = density;
    }

    public String getName()
    {
        return name;
    }

    public String getCapital()
    {
        return capital;
    }

    public long getPeople()
    {
        return people;
    }

    public long getArea()
    {
        return area;
    }

    // Methode zu ergänzen (welche und wie?)
    @Override
    public String toString()
    {
        return getName();
    }
}
