package gui.listview;

import java.util.ArrayList;
import java.util.List;

public class UndoRedoManager
{
    private List<UndoRedoActions> actionList;

    private int pointer;

    private boolean deleted = false;

    private int deltePointer = 0;

    public UndoRedoManager()
    {
        actionList = new ArrayList<UndoRedoActions>();
        pointer = 0;
    }

    public void addAction(UndoRedoActions interfaceAction)
    {
        for (int i = actionList.size() - 1; i >= pointer; i--)
        {
            actionList.remove(i);
        }
        pointer++;
        actionList.add(interfaceAction);
    }

    public void undo()
    {
        if (pointer > 0)
        {
            pointer--;
            actionList.get(pointer).undo();
            System.out.println("actionList size: " + actionList.size() + " Pointer Pos: " + pointer);
        }
    }

    public void redo()
    {
        try
        {
            if (actionList.size() > pointer || actionList.size() > deltePointer)
            {
                if (!deleted)
                {
                    actionList.get(pointer).redo();
                    pointer++;
                    System.out.println("actionList size: " + actionList.size() + " Pointer Pos: " + pointer);
                }
                else if (deleted)
                {
                    actionList.get(deltePointer).redo();
                    System.out.println("Redo an Stelle der geloeschten Einheit.");
                    deleted = false;
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("redo fail");
        }
    }

    public void delete(int index)
    {
        deleted = true;
        deltePointer = index;
        actionList.get(index).delete();
        System.out.println("Delte Action");
        System.out.println("actionList size: " + actionList.size() + " Delete Pointer " + " Pos: " + deltePointer);
    }

    public void clear()
    {
        for (int i = 0; i < actionList.size() - 1; i++)
        {
            actionList.remove(i);
        }
        pointer = 0;
    }
}
