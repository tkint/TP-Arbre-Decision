package fr.epsi.i4;

import java.util.AbstractMap;

import fr.epsi.i4.model.Node;
import javafx.util.Pair;

public class Main {

	public static void main(String[] args) {
		long time = System.currentTimeMillis();

		Node tree = new Node();

		generateData(tree);

		affiche(tree);

		tree.generateTree();
		tree.print();

		time = System.currentTimeMillis() - time;

		System.out.println("Programme exécuté en " + time / 1000f + " secondes");

		// tree.decide();

		// tree.decide(new Entry(0, 2, 2, 1, 1), tree);

		// tree.print();
	}

	public static void generateData(Node tree) {
		// tree.addEntry(new Entry(0, 0, 0, 0, 0));
		tree.addEntry(false, new AbstractMap.SimpleEntry<>("ciel", new Pair<>(0, "Soleil")),
				new AbstractMap.SimpleEntry<>("temperature", new Pair<>(0, "Normale")),
				new AbstractMap.SimpleEntry<>("humidite", new Pair<>(0, "Normale")),
				new AbstractMap.SimpleEntry<>("vent", new Pair<>(0, "Faible")));
		// tree.addEntry(new Entry(0, 0, 0, 1, 0));
		tree.addEntry(false, new AbstractMap.SimpleEntry<>("ciel", new Pair<>(0, null)),
				new AbstractMap.SimpleEntry<>("temperature", new Pair<>(0, null)),
				new AbstractMap.SimpleEntry<>("humidite", new Pair<>(0, null)),
				new AbstractMap.SimpleEntry<>("vent", new Pair<>(1, "Fort")));
		// tree.addEntry(new Entry(1, 0, 0, 0, 1));
		tree.addEntry(true, new AbstractMap.SimpleEntry<>("ciel", new Pair<>(1, "Couvert")),
				new AbstractMap.SimpleEntry<>("temperature", new Pair<>(0, null)),
				new AbstractMap.SimpleEntry<>("humidite", new Pair<>(0, null)),
				new AbstractMap.SimpleEntry<>("vent", new Pair<>(0, null)));
		// tree.addEntry(new Entry(2, 1, 0, 0, 1));
		tree.addEntry(true, new AbstractMap.SimpleEntry<>("ciel", new Pair<>(2, "Pluie")),
				new AbstractMap.SimpleEntry<>("temperature", new Pair<>(1, "Elevee")),
				new AbstractMap.SimpleEntry<>("humidite", new Pair<>(0, null)),
				new AbstractMap.SimpleEntry<>("vent", new Pair<>(0, null)));
		// tree.addEntry(new Entry(2, 2, 1, 0, 1));
		tree.addEntry(true, new AbstractMap.SimpleEntry<>("ciel", new Pair<>(2, null)),
				new AbstractMap.SimpleEntry<>("temperature", new Pair<>(2, "Basse")),
				new AbstractMap.SimpleEntry<>("humidite", new Pair<>(1, "Haute")),
				new AbstractMap.SimpleEntry<>("vent", new Pair<>(0, null)));
		// tree.addEntry(new Entry(2, 2, 1, 1, 0));
		tree.addEntry(false, new AbstractMap.SimpleEntry<>("ciel", new Pair<>(2, null)),
				new AbstractMap.SimpleEntry<>("temperature", new Pair<>(2, null)),
				new AbstractMap.SimpleEntry<>("humidite", new Pair<>(1, null)),
				new AbstractMap.SimpleEntry<>("vent", new Pair<>(1, null)));
		// tree.addEntry(new Entry(1, 2, 1, 1, 1));
		tree.addEntry(true, new AbstractMap.SimpleEntry<>("ciel", new Pair<>(1, null)),
				new AbstractMap.SimpleEntry<>("temperature", new Pair<>(2, null)),
				new AbstractMap.SimpleEntry<>("humidite", new Pair<>(1, null)),
				new AbstractMap.SimpleEntry<>("vent", new Pair<>(1, null)));
		// tree.addEntry(new Entry(0, 1, 0, 0, 0));
		tree.addEntry(false, new AbstractMap.SimpleEntry<>("ciel", new Pair<>(0, null)),
				new AbstractMap.SimpleEntry<>("temperature", new Pair<>(1, null)),
				new AbstractMap.SimpleEntry<>("humidite", new Pair<>(0, null)),
				new AbstractMap.SimpleEntry<>("vent", new Pair<>(0, null)));
		// tree.addEntry(new Entry(0, 2, 1, 0, 1));
		tree.addEntry(true, new AbstractMap.SimpleEntry<>("ciel", new Pair<>(0, null)),
				new AbstractMap.SimpleEntry<>("temperature", new Pair<>(2, null)),
				new AbstractMap.SimpleEntry<>("humidite", new Pair<>(1, null)),
				new AbstractMap.SimpleEntry<>("vent", new Pair<>(0, null)));
		// tree.addEntry(new Entry(2, 1, 1, 0, 1));
		tree.addEntry(true, new AbstractMap.SimpleEntry<>("ciel", new Pair<>(2, null)),
				new AbstractMap.SimpleEntry<>("temperature", new Pair<>(1, null)),
				new AbstractMap.SimpleEntry<>("humidite", new Pair<>(1, null)),
				new AbstractMap.SimpleEntry<>("vent", new Pair<>(0, null)));
		// tree.addEntry(new Entry(0, 1, 1, 1, 1));
		tree.addEntry(true, new AbstractMap.SimpleEntry<>("ciel", new Pair<>(0, null)),
				new AbstractMap.SimpleEntry<>("temperature", new Pair<>(1, null)),
				new AbstractMap.SimpleEntry<>("humidite", new Pair<>(1, null)),
				new AbstractMap.SimpleEntry<>("vent", new Pair<>(1, null)));
		// tree.addEntry(new Entry(1, 1, 0, 1, 1));
		tree.addEntry(true, new AbstractMap.SimpleEntry<>("ciel", new Pair<>(1, null)),
				new AbstractMap.SimpleEntry<>("temperature", new Pair<>(0, null)),
				new AbstractMap.SimpleEntry<>("humidite", new Pair<>(1, null)),
				new AbstractMap.SimpleEntry<>("vent", new Pair<>(1, null)));
		// tree.addEntry(new Entry(1, 0, 1, 0, 1));
		tree.addEntry(true, new AbstractMap.SimpleEntry<>("ciel", new Pair<>(1, null)),
				new AbstractMap.SimpleEntry<>("temperature", new Pair<>(0, null)),
				new AbstractMap.SimpleEntry<>("humidite", new Pair<>(1, null)),
				new AbstractMap.SimpleEntry<>("vent", new Pair<>(0, null)));
		// tree.addEntry(new Entry(2, 1, 0, 1, 0));
		tree.addEntry(false, new AbstractMap.SimpleEntry<>("ciel", new Pair<>(2, null)),
				new AbstractMap.SimpleEntry<>("temperature", new Pair<>(1, null)),
				new AbstractMap.SimpleEntry<>("humidite", new Pair<>(0, null)),
				new AbstractMap.SimpleEntry<>("vent", new Pair<>(1, null)));
	}

	public static void generateRandomData(Node tree, int quantity) {
		// for (int i = 0; i < quantity; i++) {
		// Entry entry =
		// new Entry(randomInt(0, 2), randomInt(0, 2), randomInt(0, 1), randomInt(0, 1), randomInt(0, 1));
		// tree.addEntry(entry);
		// }
	}

	public static int randomInt(int min, int max) {
		return min + (int) (Math.random() * ((max - min) + 1));
	}

	public static void affiche(Node tree) {
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

		System.out.println("--------------------------------------------");

		System.out.println(tree.getPlusPertinent());

		System.out.println("--------------------------------------------");
	}

	public static void afficheRatio(Node tree, String att) {
		for (int value : tree.getUniqueValues(att)) {
			System.out.println("Ratio de " + value + " sur " + att + " : " + tree.getRatio(value, att));
		}
		System.out.println("--------------------------------------------");
	}

	public static void afficheEntropie(Node tree, String att) {
		for (int value : tree.getUniqueValues(att)) {
			System.out.println("Entropie de " + value + " sur " + att + " : " + tree.entropie(value, att));
		}
		System.out.println("--------------------------------------------");
	}
}
