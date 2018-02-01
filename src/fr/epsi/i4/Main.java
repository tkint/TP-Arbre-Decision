package fr.epsi.i4;

public class Main {

	public static void main(String... args) {
		Config config = new Config("./test");

		config.addAttribut("Ciel", "Soleil", "Couvert", "Pluie");
		config.addAttribut("Température", "Basse", "Haute", "Pole Nord");
		config.addAttribut("Humidité", "Normale", "Elevée");
		config.addAttribut("Vent", "Faible", "Fort");

		config.addDecision("Jouer");
		config.addDecision("Rester");

		DecisionTree.init(config);

		DecisionTree.addData("Pluie", "Basse", "Normale", "Faible", "Jouer");

		DecisionTree.regenerateTree();

		DecisionTree.print();

		String decision = DecisionTree.decide("Pluie", "Haute", "Normale", "Faible");
		System.out.println(decision);

		decision = DecisionTree.decide("Pluie", "Basse", "Normale", "Faible");
		System.out.println(decision);

		// DecisionTree.save();
	}
}
