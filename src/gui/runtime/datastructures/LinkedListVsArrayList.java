package gui.runtime.datastructures;
/**
 * 
 * @author bennet
 * In dieser Klasse wird ein Laufzeitvergleich zwischen 
 * einer ArrayList sowie einer LinkedListed durchgeführt
 * dabei werden die folgenden Aktionen ins Verhältnis gesetzt.
 * 
 * o Einfügen am Anfang der Liste
 * o Einfügen am Ende der Liste
 * o Löschen des ersten Elements der Liste
 * o Löschen des ersten Elements der Liste
 * o Zugreifen auf das erste Element der Liste
 * o Zugreifen auf das letzte Element der Liste
 * o Zugreifen auf das mittlere Element der Liste
 *
 */

import java.util.ArrayList;
import java.util.LinkedList;

public class LinkedListVsArrayList
{
    private static LinkedList<Integer> linkedList = new LinkedList<>();

    private static ArrayList<Integer> arrayList = new ArrayList<>();

    public static void fillArrayList()
    {
        for (int i = 0; i < 100000; i++)
        {
            arrayList.add(i);
        }
    }

    public static void fillLinkedList()
    {
        for (int i = 0; i < 100000; i++)
        {
            linkedList.add(i);
        }
    }

    public static void deleteElement(boolean first, boolean last, ArrayList<Integer> al, LinkedList<Integer> ll)
    {
        if (al != null && first && al.size() > 0)
        {
            al.remove(0);
        }
        else if (ll != null && first && ll.size() > 0)
        {
            ll.remove(0);
        }
        else if (al != null && last && al.size() > 0)
        {
            al.remove(al.size() - 1);
        }
        else if (ll != null && last && ll.size() > 0)
        {
            ll.remove(ll.size() - 1);
        }
    }

    public static void addElement(int value, boolean first, boolean last, ArrayList<Integer> al, LinkedList<Integer> ll)
    {
        if (al != null && first)
        {
            al.add(0, value);
        }
        else if (ll != null && last)
        {
            ll.add(0, value);
        }
    }

    public static void getElement(boolean first, boolean middle, boolean last, ArrayList<Integer> al, LinkedList<Integer> ll)
    {
        if (al != null && middle && al.size() > 0)
        {
            al.get(al.size() / 2);
        }
        else if (ll != null && middle && ll.size() > 0)
        {
            ll.get(ll.size() / 2);
        }
        else if (al != null && first && al.size() > 0)
        {
            al.get(0);
        }
        else if (ll != null && first && ll.size() > 0)
        {
            ll.get(0);
        }
        else if (al != null && last && al.size() > 0)
        {
            al.get(al.size() - 1);
        }
        else if (ll != null && last && ll.size() > 0)
        {
            ll.get(ll.size() - 1);
        }
        else
        {
            return;
        }
    }

    public static void main(String[] args)
    {
        fillArrayList();
        fillLinkedList();
        long start, end, time;
        start = System.currentTimeMillis();
        // getElement(false, true, false, null, linkedList);
        deleteElement(false, true, arrayList, null);
        end = System.currentTimeMillis();
        time = end - start;
        System.out.println("Runtime: " + time + " ms");

    }
}
