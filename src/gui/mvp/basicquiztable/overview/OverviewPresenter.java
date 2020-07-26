package gui.mvp.basicquiztable.overview;

import gui.mvp.basicquiztable.main.MainPresenter;
import gui.mvp.basicquiztable.model.Model;

public class OverviewPresenter
{

    private OverviewView ov;

    private MainPresenter mp;

    private Model m;

    public void setOverviewView(OverviewView ov)
    {
        this.ov = ov;
    }

    public void setMainPresenter(MainPresenter mp)
    {
        this.mp = mp;
    }

    public void setModel(Model m)
    {
        this.m = m;
    }

}
