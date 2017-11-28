package fr.epsi.i4;

import fr.epsi.i4.model.Entry;
import fr.epsi.i4.model.Node;

public class Main {

    public static void main(String[] args) {
        Node root = new Node();

        root.addEntry(new Entry(0, 0, 0, 0, 0));
        root.addEntry(new Entry(0, 0, 0, 1, 0));
        root.addEntry(new Entry(1, 0, 0, 0, 1));
        root.addEntry(new Entry(2, 1, 0, 0, 1));
        root.addEntry(new Entry(2, 2, 1, 0, 1));
        root.addEntry(new Entry(2, 2, 1, 1, 0));
        root.addEntry(new Entry(1, 2, 1, 1, 1));
        root.addEntry(new Entry(0, 1, 0, 0, 0));
        root.addEntry(new Entry(0, 2, 1, 0, 1));
        root.addEntry(new Entry(2, 1, 1, 0, 1));
        root.addEntry(new Entry(0, 1, 1, 1, 1));
        root.addEntry(new Entry(1, 1, 0, 1, 1));
        root.addEntry(new Entry(1, 0, 1, 0, 1));
        root.addEntry(new Entry(2, 1, 0, 1, 0));

        afficheEntropie(root, "ciel");
        afficheEntropie(root, "temperature");
        afficheEntropie(root, "humidite");
        afficheEntropie(root, "vent");

        afficheRatio(root, "ciel");
        afficheRatio(root, "temperature");
        afficheRatio(root, "humidite");
        afficheRatio(root, "vent");

        System.out.println("Pertinence de ciel : " + root.pertinence("ciel"));
        System.out.println("Pertinence de temperature : " + root.pertinence("temperature"));
        System.out.println("Pertinence de humidite : " + root.pertinence("humidite"));
        System.out.println("Pertinence de vent : " + root.pertinence("vent"));
    }

    public static void afficheRatio(Node node, String att) {
        for (int value : node.getUniqueValues(att)) {
            System.out.println("Ratio de " + value + " sur " + att + " : " + node.getRatio(value, att));
        }
        System.out.println("--------------------------------------------");
    }

    public static void afficheEntropie(Node node, String att) {
        for (int value : node.getUniqueValues(att)) {
            System.out.println("Entropie de " + value + " sur " + att + " : " + node.entropie(value, att));
        }
        System.out.println("--------------------------------------------");
    }
}
