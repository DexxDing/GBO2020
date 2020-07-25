package gui.graphics.sinus;

import javafx.scene.Parent;

public class SinusPresenter
{
    private SinusModel model;

    private SinusView view;

    public void setModel(SinusModel m)
    {
        this.model = m;
    }

    public void setView(SinusView v)
    {
        this.view = v;
    }

    public SinusPresenter()
    {

    }

    public Parent getView()
    {
        return view;
    }

    public void setFormelText()
    {
        view.setFormelText(model.toString());
    }

    public void setModel(double amplitude, double frequenz, double zoom, double phase)
    {
        model = new SinusModel(amplitude, frequenz, zoom, phase);
    }

    public double setErgebnisSinusFnct(int i)
    {
        return ((this.model.getAmplitude() * Math.sin(this.model.getFrequenz() * -((i - this.view.getDarwingPane().getWidth() / 2) / this.model.getZoom()) + this.model.getPhase())) * this.model.getZoom() + this.view.getDarwingPane().getHeight() / 2);
    }
}
