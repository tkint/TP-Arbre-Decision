package fr.epsi.i4;

import java.util.ArrayList;
import java.util.List;

public class Config {

	private String directory;

	private List<Attribut> attributs;

	private List<String> decisions;

	public Config(String directory) {
		this.directory = directory;
		this.attributs = new ArrayList<>();
		this.decisions = new ArrayList<>();
	}

	public List<String> getDecisions() {
		return decisions;
	}

	public List<Attribut> getAttributs() {
		return attributs;
	}

	public Attribut getAttributByIndex(int attributIndex) {
		return attributs.get(attributIndex);
	}

	public void addDecision(String decision) {
		this.decisions.add(decision);
	}

	public void addAttribut(String attributName, String... values) {
		this.attributs.add(new Attribut(attributName, values));
	}

	public String getDirectory() {
		return directory;
	}

	public int getIndexOfValue(int attributIndex, String valueName) {
		return attributs.get(attributIndex).getValueByName(valueName);
	}

	public String getValue(int attributIndex, int valueIndex) {
		return attributs.get(attributIndex).getValues()[valueIndex];
	}

	public Integer getDecisionByName(String decisionName) {
		Integer decision = null;
		int i = 0;
		while (i < decisions.size() && decision == null) {
			if (decisions.get(i).equals(decisionName)) {
				decision = i;
			}
			i++;
		}
		return decision;
	}
}
