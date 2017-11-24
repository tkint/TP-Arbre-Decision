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

        afficheEntropie(tree, "ciel");
        afficheEntropie(tree, "temperature");
        afficheEntropie(tree, "humidite");
        afficheEntropie(tree, "vent");

        afficheRatio(tree, "ciel");
        afficheRatio(tree, "temperature");
        afficheRatio(tree, "humidite");
        afficheRatio(tree, "vent");

        System.out.println("Pertinence de ciel : " + tree.pertinence("ciel"));
        System.out.println("Pertinence de temperature : " + tree.pertinence("temperature"));
        System.out.println("Pertinence de humidite : " + tree.pertinence("humidite"));
        System.out.println("Pertinence de vent : " + tree.pertinence("vent"));
    }

    public static void afficheRatio(Tree tree, String att) {
        for (int value : tree.getUniqueValues(att)) {
            System.out.println("Ratio de " + value + " sur " + att + " : " + tree.getRatio(value, att));
        }
        System.out.println("--------------------------------------------");
    }

    public static void afficheEntropie(Tree tree, String att) {
        for (int value : tree.getUniqueValues(att)) {
            System.out.println("Entropie de " + value + " sur " + att + " : " + tree.entropie(value, att));
        }
        System.out.println("--------------------------------------------");
    }
}
