package fr.epsi.i4;

import fr.epsi.i4.model.Entry;
import fr.epsi.i4.model.Tree;

public class Main {

    public static void main(String[] args) {
        Tree tree = new Tree();

        tree.addEntry(new Entry(0, 0, 0, 0, 0));
        tree.addEntry(new Entry(0, 0, 0, 1, 0));
        tree.addEntry(new Entry(1, 0, 0, 0, 1));
        tree.addEntry(new Entry(2, 1, 0, 0, 1));
        tree.addEntry(new Entry(2, 2, 1, 0, 1));
        tree.addEntry(new Entry(2, 2, 1, 1, 0));
        tree.addEntry(new Entry(1, 2, 1, 1, 1));
        tree.addEntry(new Entry(0, 1, 0, 0, 0));
        tree.addEntry(new Entry(0, 2, 1, 0, 1));
        tree.addEntry(new Entry(2, 1, 1, 0, 1));
        tree.addEntry(new Entry(0, 1, 1, 1, 1));
        tree.addEntry(new Entry(1, 1, 0, 1, 1));
        tree.addEntry(new Entry(1, 0, 1, 0, 1));
        tree.addEntry(new Entry(2, 1, 0, 1, 0));

        System.out.println(tree.entropie(0, "ciel"));
    }
}
