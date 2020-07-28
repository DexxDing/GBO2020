package gui.exam.drawing;

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

    public void add(UndoRedoActions a)
    {
        for (int i = actionList.size() - 1; i >= pointer; i--)
        {
            actionList.remove(i);
            System.out.println("Remove Size " + actionList.size() + " \n Pointer: " + pointer);
        }
        pointer++;
        actionList.add(a);
        System.out.println("Size: " + actionList.size() + "/n Pointer: " + pointer);
    }

    public void undo()
    {
        if (pointer > 0)
        {
            pointer--;
            actionList.get(pointer).undo();
            System.out.println("Size: " + actionList.size() + "/n Pointer: " + pointer);
        }
    }

    public void redo()
    {
        if (actionList.size() > pointer)
        {
            actionList.get(pointer).redo();
            pointer++;
            System.out.println("Size: " + actionList.size() + "/n Pointer: " + pointer);
        }
    }

    public void clear()
    {
        for (int i = actionList.size() - 1; i >= 0; i--)
        {
            actionList.remove(i);
        }
        pointer = 0;
    }

}
