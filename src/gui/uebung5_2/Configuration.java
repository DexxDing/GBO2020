package gui.uebung5_2;

public class Configuration
{

    private String[] sizeNames;

    private int[] sizePrices;

    private String[] toppingNames;

    private int[] toppingPrices;

    private int numberOfDefaultToppings;

    public Configuration()
    {

    }

    public Configuration(String[] sizeNames, int[] sizePrices, String[] toppingNames, int[] toppingPrices, int numberOfDefaultToppings)
    {
        this.sizeNames = sizeNames;
        this.sizePrices = sizePrices;
        this.toppingNames = toppingNames;
        this.toppingPrices = toppingPrices;
        this.numberOfDefaultToppings = numberOfDefaultToppings;
    }

    public String[] getSizeNames()
    {
        return sizeNames;
    }

    public int[] getSizePrices()
    {
        return sizePrices;
    }

    public String[] getToppingNames()
    {
        return toppingNames;
    }

    public int[] getToppingPrices()
    {
        return toppingPrices;
    }

    public int getNumberOfDefaultToppings()
    {
        return numberOfDefaultToppings;
    }
}
