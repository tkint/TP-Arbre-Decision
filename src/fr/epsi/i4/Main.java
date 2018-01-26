package fr.epsi.i4;

import fr.epsi.i4.model.Entry;
import fr.epsi.i4.model.Node;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) {
        long time = System.currentTimeMillis();

        Node tree = new Node();

//        generateRandomData(tree, 1000000);
//        generateData(tree);
//        try {
//            tree.writeFile();
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (UnsupportedEncodingException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        tree.addAttribut("Ciel", "Soleil");
//        tree.addValueToAttribut("Ciel", "Couvert");
//        tree.addValueToAttribut("Ciel", "Pluie");
//
//        tree.addAttribut("Temp", "Basse");
//        tree.addValueToAttribut("Temp", "Haute");
//        tree.addValueToAttribut("Temp", "Pole Nord");
//
//        tree.addAttribut("HumiditÃ©", "Normale");
//        tree.addValueToAttribut("HumiditÃ©", "Eleve");
//
//        tree.addAttribut("Vent", "Faible");
//        tree.addValueToAttribut("Vent", "Fort");

        tree.readFile();
        tree.readFileAttribut();
        tree.readFileValue();
//        generateRandomData(tree, 1000000);
        tree.generateTree();
        tree.print();

        time = System.currentTimeMillis() - time;

        System.out.println("Programme exÃ©cutÃ© en " + time / 1000f + " secondes");

        tree.decide();
//        tree.addAttribut("Lune", "Pleine");
//        Entry test = new Entry(1, 3, 6, 8, 1);
//        test.getParams().add(tree.getIndexOfValue("Pleine"));
//        tree.decide(test, tree);

//        tree.addValueToAttribut("Vent", "Inexistant");
//        Entry test = new Entry(1, 3, 6, tree.getIndexOfValue("Inexistant"), 1);
//        tree.decide(test, tree);
//        tree.decide(new Entry(1, 3, 6, 8, 1), tree);
        //		tree.print();
    }

    public static void generateData(Node tree) {
        tree.addAttribut("Actuel", "Safe"); //0
        tree.addValueToAttribut("Actuel", "Odeur");//1
        tree.addValueToAttribut("Actuel", "Brise");//2
        tree.addValueToAttribut("Actuel", "Odeur et Brise");//3
//
        tree.addAttribut("Gauche", "Safe");//4
        tree.addValueToAttribut("Gauche", "Puit possible");//5
        tree.addValueToAttribut("Gauche", "Wumpus possible");//6
        tree.addValueToAttribut("Gauche", "Puit");//7
        tree.addValueToAttribut("Gauche", "Wumpus");//8
//
        tree.addAttribut("Droite", "Safe");//9
        tree.addValueToAttribut("Droite", "Puit possible");//10
        tree.addValueToAttribut("Droite", "Wumpus possible");//11
        tree.addValueToAttribut("Droite", "Puit");//12
        tree.addValueToAttribut("Droite", "Wumpus");//13

        tree.addAttribut("Haut", "Safe");//14
        tree.addValueToAttribut("Haut", "Puit possible");//15
        tree.addValueToAttribut("Haut", "Wumpus possible");//16
        tree.addValueToAttribut("Haut", "Puit");//17
        tree.addValueToAttribut("Haut", "Wumpus");//18
        
        tree.addAttribut("Bas", "Safe");//19
        tree.addValueToAttribut("Bas", "Puit possible");//20
        tree.addValueToAttribut("Bas", "Wumpus possible");//21
        tree.addValueToAttribut("Bas", "Puit");//22
        tree.addValueToAttribut("Bas", "Wumpus");//23
//
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
//
//    public static void generateRandomData(Node tree, int quantity) {
//        tree.addAttribut("Ciel", "Soleil");
//        tree.addValueToAttribut("Ciel", "Couvert");
//        tree.addValueToAttribut("Ciel", "Pluie");
//
//        tree.addAttribut("Temp", "Basse");
//        tree.addValueToAttribut("Temp", "Haute");
//        tree.addValueToAttribut("Temp", "Pole Nord");
//
//        tree.addAttribut("HumiditÃ©", "Normale");
//        tree.addValueToAttribut("HumiditÃ©", "Eleve");
//
//        tree.addAttribut("Vent", "Faible");
//        tree.addValueToAttribut("Vent", "Fort");
//        for (int i = 0; i < quantity; i++) {
//            Entry entry
//                    = new Entry(randomInt(0, 2), randomInt(3, 5), randomInt(6, 7), randomInt(7, 8), randomInt(0, 1));
//            tree.addEntry(entry);
//        }
//    }
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

        System.out.println(tree.getPlusPertinent());

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
