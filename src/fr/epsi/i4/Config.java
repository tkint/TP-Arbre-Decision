package fr.epsi.i4;

import java.util.ArrayList;
import java.util.List;

public class Config {

	private String directory;

	private List<Attribut> attributs;

	public Config(String directory) {
		this.directory = directory;
		this.attributs = new ArrayList<>();
	}

	public void addAttribut(String attributName, String... values) {
		this.attributs.add(new Attribut(attributName, values));
	}

	public String getDirectory() {
		return directory;
	}

	public boolean doesAttributExist(String attributName) {
		boolean exists = false;
		int i = 0;
		while (i < attributs.size() && !exists) {
			if (attributs.get(i).getName().equals(attributName)) {
				exists = true;
			}
			i++;
		}
		return exists;
	}
}
