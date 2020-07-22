package gui.graphics.sinus;

import javafx.scene.Parent;

public class SinusPresenter
{
    private SinusModel m;

    private SinusView v;

    public void setModel(SinusModel m)
    {
        this.m = m;
    }

    public void setView(SinusView v)
    {
        this.v = v;
    }

    public SinusPresenter()
    {

    }

    public Parent getView()
    {
        return v;
    }

    public void setFormelText()
    {
        v.setFormelText(m.toString());
    }

    public void setModel(double amplitude, double frequenz, double zoom, double phase)
    {
        m = new SinusModel(amplitude, frequenz, zoom, phase);
    }
}
