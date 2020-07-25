package gui.graphics.sinus;

public class SinusModel
{
    private double amplitude;

    private double frequenz;

    private double zoom;

    private double phase;

    // Hier noch das Format angeben (#.##);

    public SinusModel(double amplitude, double frequenz, double zoom, double phase)
    {
        super();
        this.amplitude = amplitude;
        this.frequenz = frequenz;
        this.zoom = zoom;
        this.phase = phase;
    }

    public SinusModel()
    {
        // TODO Auto-generated constructor stub
    }

    public double getAmplitude()
    {
        return amplitude;
    }

    public void setAmplitude(double amplitude)
    {
        this.amplitude = amplitude;
    }

    public double getFrequenz()
    {
        return frequenz;
    }

    public void setFrequenz(double frequenz)
    {
        this.frequenz = frequenz;
    }

    public double getZoom()
    {
        return zoom;
    }

    public void setZoom(double zoom)
    {
        this.zoom = zoom;
    }

    public double getPhase()
    {
        return phase;
    }

    public void setPhase(double phase)
    {
        this.phase = phase;
    }

    @Override
    public String toString()
    {
        return getAmplitude() + " * sin(" + getFrequenz() + " * " + getZoom() + " + " + getPhase() + " )";
    }

}
