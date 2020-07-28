package gui.listview;

public class Action implements UndoRedoActions
{

    private View view;

    private Person person;

    private Presenter presenter;

    public Action(Presenter presenter, View view, Person person)
    {
        super();
        this.view = view;
        this.presenter = presenter;
        this.person = person;
    }

    @Override
    public void undo()
    {
        presenter.delete(this.person);
    }

    @Override
    public void redo()
    {
        presenter.add(this.person);
    }

}
