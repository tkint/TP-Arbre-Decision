package fr.epsi.i4;

import fr.epsi.i4.model.Entry;
import fr.epsi.i4.model.Node;

public class Main {

    public static void main(String[] args) {
        long time = System.currentTimeMillis();

        Node tree = new Node();

        generateData(tree);
//        generateRandomData(tree, 1000000);
        tree.generateTree();
        tree.print();

        time = System.currentTimeMillis() - time;

        System.out.println("Programme exécuté en " + time / 1000f + " secondes");

        //		tree.decide();
        		tree.decide(new Entry(1, 3, 6, 8, 1), tree);
        //		tree.print();
    }

    public static void generateData(Node tree) {
        tree.addAttribut("Ciel", "Soleil");
        tree.addValueToAttribut("Ciel", "Couvert");
        tree.addValueToAttribut("Ciel", "Pluie");

        tree.addAttribut("Temp", "Basse");
        tree.addValueToAttribut("Temp", "Haute");
        tree.addValueToAttribut("Temp", "Pole Nord");

        tree.addAttribut("Humidité", "Normale");
        tree.addValueToAttribut("Humidité", "Eleve");

        tree.addAttribut("Vent", "Faible");
        tree.addValueToAttribut("Vent", "Fort");

        tree.addEntry(new Entry(0, 3, 6, 8, 0));
        tree.addEntry(new Entry(0, 3, 6, 9, 0));
        tree.addEntry(new Entry(1, 3, 6, 8, 1));
        tree.addEntry(new Entry(2, 4, 6, 8, 1));
        tree.addEntry(new Entry(2, 5, 7, 8, 1));
        tree.addEntry(new Entry(2, 5, 7, 9, 0));
        tree.addEntry(new Entry(1, 5, 7, 9, 1));
        tree.addEntry(new Entry(0, 4, 6, 8, 0));
        tree.addEntry(new Entry(0, 5, 7, 8, 1));
        tree.addEntry(new Entry(2, 4, 7, 8, 1));
        tree.addEntry(new Entry(0, 4, 7, 9, 1));
        tree.addEntry(new Entry(1, 4, 6, 9, 1));
        tree.addEntry(new Entry(1, 3, 7, 8, 1));
        tree.addEntry(new Entry(2, 4, 6, 9, 0));
    }

    public static void generateRandomData(Node tree, int quantity) {
        tree.addAttribut("Ciel", "Soleil");
        tree.addValueToAttribut("Ciel", "Couvert");
        tree.addValueToAttribut("Ciel", "Pluie");

        tree.addAttribut("Temp", "Basse");
        tree.addValueToAttribut("Temp", "Haute");
        tree.addValueToAttribut("Temp", "Pole Nord");

        tree.addAttribut("Humidité", "Normale");
        tree.addValueToAttribut("Humidité", "Eleve");

        tree.addAttribut("Vent", "Faible");
        tree.addValueToAttribut("Vent", "Fort");
        for (int i = 0; i < quantity; i++) {
            Entry entry
                    = new Entry(randomInt(0, 2), randomInt(3, 5), randomInt(6, 7), randomInt(7, 8), randomInt(0, 1));
            tree.addEntry(entry);
        }
    }

    public static int randomInt(int min, int max) {
        return min + (int) (Math.random() * ((max - min) + 1));
    }

    public static void affiche(Node tree) {
        afficheEntropie(tree, tree.getIndexOfAttribut("ciel"));
        afficheEntropie(tree, tree.getIndexOfAttribut("temperature"));
        afficheEntropie(tree, tree.getIndexOfAttribut("humidite"));
        afficheEntropie(tree, tree.getIndexOfAttribut("vent"));

        afficheRatio(tree, tree.getIndexOfAttribut("ciel"));
        afficheRatio(tree, tree.getIndexOfAttribut("temperature"));
        afficheRatio(tree, tree.getIndexOfAttribut("humidite"));
        afficheRatio(tree, tree.getIndexOfAttribut("vent"));

        System.out.println("Pertinence de ciel : " + tree.pertinence(tree.getIndexOfAttribut("ciel")));
        System.out.println("Pertinence de temperature : " + tree.pertinence(tree.getIndexOfAttribut("temperature")));
        System.out.println("Pertinence de humidite : " + tree.pertinence(tree.getIndexOfAttribut("humidite")));
        System.out.println("Pertinence de vent : " + tree.pertinence(tree.getIndexOfAttribut("vent")));

        System.out.println("--------------------------------------------");

        System.out.println(tree.getPlusPertinent2());

        System.out.println("--------------------------------------------");
    }

//	public static void afficheRatio(Node tree, String att) {
//		for (int value : tree.getUniqueValues(att)) {
//			System.out.println("Ratio de " + value + " sur " + att + " : " + tree.getRatio(value, att));
//		}
//		System.out.println("--------------------------------------------");
//	}
    public static void afficheRatio(Node tree, Integer att) {
        for (int value : tree.getUniqueValues(att)) {
            System.out.println("Ratio de " + value + " sur " + att + " : " + tree.getRatio(value, att));
        }
        System.out.println("--------------------------------------------");
    }

//	public static void afficheEntropie(Node tree, String att) {
//		for (int value : tree.getUniqueValues(att)) {
//			System.out.println("Entropie de " + value + " sur " + att + " : " + tree.entropie(value, att));
//		}
//		System.out.println("--------------------------------------------");
//	}
    public static void afficheEntropie(Node tree, Integer att) {
        for (int value : tree.getUniqueValues(att)) {
            System.out.println("Entropie de " + value + " sur " + att + " : " + tree.entropie(value, att));
        }
        System.out.println("--------------------------------------------");
    }
}
