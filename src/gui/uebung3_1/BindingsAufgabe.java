package gui.uebung3_1;

import javafx.beans.property.SimpleIntegerProperty;

public class BindingsAufgabe
{
    private static SimpleIntegerProperty prop1, prop2, prop3, prop4;

    public BindingsAufgabe()
    {
    }

    public static void initProperties()
    {
        prop1 = new SimpleIntegerProperty();
        prop2 = new SimpleIntegerProperty();
        prop3 = new SimpleIntegerProperty();
        prop4 = new SimpleIntegerProperty();
    }

    public static void bindPropertiesUnidirectional(SimpleIntegerProperty... props)
    {
        for (int i = 0; i < props.length - 1; i++)
        {
            props[i].bind(props[i + 1]);
        }
    }

    public static void bindPropertiesBidirectional(SimpleIntegerProperty... props)
    {
        for (int i = 0; i < props.length - 1; i++)
        {
            props[i].bindBidirectional(props[i + 1]);
        }
    }

    public static void oneChild_twoParents(SimpleIntegerProperty child, SimpleIntegerProperty mother, SimpleIntegerProperty father)
    {
        child.bind(mother);
        child.bind(father);
    }

    public static void mother_twoChilds(SimpleIntegerProperty mother, SimpleIntegerProperty daughter, SimpleIntegerProperty son)
    {
        daughter.bind(mother);
        son.bind(mother);
    }

    /**
     * Damit das Beispiel funktioniert die jeweiligen Aufgabenteile ein und alle
     * anderen außer init() auskommentieren
     * 
     * @param args
     */
    public static void main(String[] args)
    {
        BindingsAufgabe.initProperties();
        // BindingsAufgabe.bindPropertiesUnidirectional(prop1, prop2, prop3,
        // prop4);
        // System.out.println("a) Versuchen Sie ein Beispiel zu
        // programmieren,\n" + "in dem vier unidirektionale Properties (z.B. vom
        // Typ" + "SimpleIntegerProperty)\nin einer Reihe gekoppelt werden! Ist
        // das" + "möglich? Falls ja: Welche\r\n" + "Properties können Sie durch
        // Aufruf" + "der Methode set noch ändern?");
        // System.out.println("###########################################################################################");
        // System.out.println("#Bound unidirectional: prop1 -> prop2 -> prop3
        // ->" + "{prop4:root} only undepend -> changeable#");
        // prop4.set(5);
        // System.out.println("#\tFirst Property:" + prop1.getValue());
        // System.out.println("#\tSecond Property:" + prop2.getValue());
        // System.out.println("#\tThird Property:" + prop3.getValue());
        // System.out.println("#\tFourth Property:" + prop4.getValue());
        // System.out.println("###########################################################################################");

        // System.out.println("b) Versuchen Sie ein Beispiel zu programmieren,
        // in dem eine Property von mindestens zwei anderen\r\n" + "Properties
        // abhängig ist! Ist das möglich?");
        // System.out.println("###########################################################################################");
        // BindingsAufgabe.oneChild_twoParents(prop1, prop2, prop3);
        // prop2.set(4); // setze mutter
        // prop3.set(3); // setze vater
        // System.out.println("#Child Value: " + prop1.getValue());
        // System.out.println("#Antwort: Diese Art von Kopplung funktioniert
        // nicht! er
        // nimmt nur die letzte Kopplung war,\n also die von seinem Vater
        // Knoten.");
        // System.out.println("###########################################################################################");

        // System.out.println("c) Versuchen Sie ein Beispiel zu programmieren,
        // in dem eine Property ihre Änderungen an\r\n" + "mindestens zwei
        // andere Properties weiterleitet! Ist das möglich?");
        // System.out.println("###########################################################################################");
        // BindingsAufgabe.mother_twoChilds(prop1, prop2, prop3);
        // prop1.set(200);
        // System.out.println("#Tochter: " + prop2.getValue());
        // System.out.println("#Sohn: " + prop3.getValue());
        // System.out.println("#Antwort: Ja, Tochter sowie Sohn bekommen von
        // ihrer Mutter values (ein Parent Node, zwei Child Nodes)");
        // System.out.println("###########################################################################################");

        // System.out.println("d) Versuchen Sie ein Beispiel zu programmieren,
        // in dem vier unidirektionale Properties (z.B. vom\r\n" + "Typ
        // SimpleIntegerProperty) in einem Kreis gekoppelt werden! Ist das
        // möglich? Falls ja: Welche\r\n" + "Properties können Sie durch Aufruf
        // der Methode set noch ändern?");
        // bindPropertiesUnidirectional(prop1, prop2, prop3, prop4, prop1);
        // System.out.print("\nAntwort: Nein man kann keinen Unidirektionalen
        // Kopplungskreis schließen, endlosschleife\n Exception in thread
        // \"main\" java.lang.StackOverflowError");
        // System.out.println("###########################################################################################");

        // System.out.println("#e) Lösen die Teilaufgabe a für den Fall der
        // bidirektionalen Kopplung!");
        // bindPropertiesBidirectional(prop1, prop2, prop3);
        // prop2.setValue(222);
        // System.out.println("# property 1: " + prop1.getValue());
        // System.out.println("#Antwort: In der Bidirektionalen Kopplung ist es
        // egal an
        // welcher Stelle wir nach der Kopplung den Wert ändern, \n#anders als
        // bei der unidirektionalen Kopplung, bei der nur der Parent verändert
        // werden darf.\n#Bei der bidirektionalen Kopplung ist jedes Node Parent
        // und Child zugleich.");
        // System.out.println("###########################################################################################");

        // System.out.println("d) Lösen die Teilaufgabe d für den Fall der
        // bidirektionalen Kopplung!");
        // BindingsAufgabe.bindPropertiesBidirectional(prop1, prop2, prop3,
        // prop4, prop1);
        // prop3.set(23);
        // System.out.println("Property 4: " + prop4.getValue());
        // System.out.println("Antwort: Die Bidirektionale Koplung im Kreis
        // funktioniert,
        // denn hier ist jedes Element Child und Parent.");
        // System.out.println("###########################################################################################");
    }

}
