package gui.uebung7_1;

public class Presenter
{
    private View view;

    private Model model;

    public Presenter()
    {
    }

    public void setView(View view)
    {
        this.view = view;
    }

    public void setModel(Model model)
    {
        this.model = model;
    }

    public void login(String loginName, String password)
    {
        if (loginName.isEmpty())
        {
            view.showInputError();
        }
        else if (model.isOkay(loginName, password))
        {
            view.handleOkayLoginProtocol();
            view.resetInput();
            view.showOkayMessage();
        }
        else
        {
            view.handleFalseLoginProtocol();
            if (view.getFails() == 2)
            {
                view.toMuchFails();
            }
            else
            {
                view.showLoginError();
            }
        }
    }

    public void delete()
    {
        view.handleDelete();
    }
}
