package gui.exam.drawing;

import javafx.scene.shape.Shape;

public class Action implements UndoRedoActions
{
    private Main m = new Main();

    private Shape s;

    public Action(Main m, Shape s)
    {
        this.s = s;
        this.m = m;
    }

    @Override
    public void undo()
    {
        m.delete(s);
    }

    @Override
    public void redo()
    {
        m.add(s);
    }

}
