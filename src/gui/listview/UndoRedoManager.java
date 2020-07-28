package gui.listview;

import java.util.ArrayList;
import java.util.List;

public class UndoRedoManager
{
    private List<UndoRedoActions> actionList;

    private int pointer;

    public UndoRedoManager()
    {
        actionList = new ArrayList<UndoRedoActions>();
        pointer = 0;
    }

    public void add(UndoRedoActions interfaceAction)
    {
        for (int i = actionList.size() - 1; i >= pointer; i--)
        {
            actionList.remove(i); // löschen der ActionList Elemente nach undo
        }
        pointer++;
        actionList.add(interfaceAction);
    }

    public void undo()
    {
        if (pointer > 0)
        {
            pointer--; // bei undo zuerst pointer nach links schieben, er ist
                       // immernoch auf dem n+1 Element also auf n (letztes
                       // Element).
            actionList.get(pointer).undo();
        }
    }

    public void redo()
    {
        if (actionList.size() > pointer)
        {
            actionList.get(pointer).redo();
            pointer++;
        }
    }

    public void delete(int index)
    {
        if (index > 0)
        {
            pointer = index - 1;
        }
        actionList.remove(index);
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
