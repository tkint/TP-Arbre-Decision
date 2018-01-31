package fr.epsi.i4;

import java.util.ArrayList;
import java.util.List;

public class Config {

	private String directory;

	private List<Attribut> attributs;

	private List<String> resultats;

	public Config(String directory) {
		this.directory = directory;
		this.attributs = new ArrayList<>();
		this.resultats = new ArrayList<>();
	}

	public List<String> getResultats() {
		return resultats;
	}

	public List<Attribut> getAttributs() {
		return attributs;
	}

	public Attribut getAttributByIndex(int attributIndex) {
		return attributs.get(attributIndex);
	}

	public void addResultat(String resultat) {
		this.resultats.add(resultat);
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

	public Integer getResultatByName(String resultatName) {
		Integer resultat = null;
		int i = 0;
		while (i < resultats.size() && resultat == null) {
			if (resultats.get(i).equals(resultatName)) {
				resultat = i;
			}
			i++;
		}
		return resultat;
	}

	public Attribut getAttributByName(String attributName) {
		Attribut attribut = null;
		int i = 0;
		while (i < attributs.size() && attribut == null) {
			if (attributs.get(i).getName().equals(attributName)) {
				attribut = attributs.get(i);
			}
			i++;
		}
		return attribut;
	}
}
