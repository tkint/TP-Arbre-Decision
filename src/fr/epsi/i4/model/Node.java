package fr.epsi.i4.model;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by tkint on 23/11/2017.
 */
public class Node {

	public List<Integer> ciels;

	public List<Integer> temperatures;

	public List<Integer> humidites;

	public List<Integer> vents;

	public List<Integer> jouers;

	private String value;

	private List<Branch> children;

	private List<Entry> data;

	public Node() {
		this.children = new ArrayList<>();
		this.data = new ArrayList<>();

		this.ciels = new ArrayList<>();
		this.temperatures = new ArrayList<>();
		this.humidites = new ArrayList<>();
		this.vents = new ArrayList<>();
		this.jouers = new ArrayList<>();
	}

	public Node(String value) {
		this();
		this.value = value;
	}

	public Node(Integer valueToKeep, String att, List<Entry> data) {
		this();
		Field field = null;
		try {
			field = Entry.class.getField(att);

			for (Entry entry : data) {
				if (field.get(entry).equals(valueToKeep)) {
					addEntry(entry);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public List<Branch> getChildren() {
		return children;
	}

	public void setChildren(List<Branch> children) {
		this.children = children;
	}

	public Branch addChild(Branch branch) {
		children.add(branch);
		return branch;
	}

	public Entry addEntry(Entry entry) {
		ciels.add(entry.getCiel());
		temperatures.add(entry.getTemperature());
		humidites.add(entry.getHumidite());
		vents.add(entry.getVent());
		jouers.add(entry.getJouer());

		data.add(entry);

		return entry;
	}

	public double entropie(int value, String att) {
		double pOui = 0;
		double pNon = 0;

		Field field = null;
		try {
			field = Entry.class.getField(att);

			for (Entry entry : data) {
				if (((Integer) field.get(entry)) == value) {
					if (entry.getJouer() == 0) {
						pNon++;
					} else {
						pOui++;
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		if (pOui == 0 || pNon == 0) {
			return 0d;
		}

		pOui /= data.size();
		pNon /= data.size();

		return -pOui * log2(pOui) - pNon * log2(pNon);
	}

	public double entropie() {
		double pOui = 0;
		double pNon = 0;

		for (Entry entry : data) {
			if (entry.getJouer() == 0) {
				pNon++;
			} else {
				pOui++;
			}
		}
		if (pOui == 0 || pNon == 0) {
			return 0d;
		}

		pOui /= data.size();
		pNon /= data.size();

		return -pOui * log2(pOui) - pNon * log2(pNon);
	}

	private double log2(double x) {
		if (x == 0) {
			return 0d;
		}
		return Math.log(x) / Math.log(2.);
	}

	//    public Double pertinence(String att) {
	//        Double total = null;
	//        for (int v : getUniqueValues(att)) {
	//            if (total == null) {
	//                total = partPertinence(v, att);
	//            } else {
	//                total -= partPertinence(v, att);
	//            }
	//        }
	//        return total;
	//    }

	public Double pertinence(String att) {
		Double total = entropie();
		for (int v : getUniqueValues(att)) {
			total -= partPertinence(v, att);
		}
		return total;
	}

	private double partPertinence(int value, String att) {
		return getRatio(value, att) * entropie(value, att);
	}

	public double getRatio(int value, String att) {
		double nb = 0d;
		Field field = null;
		List<Integer> values = new ArrayList<>();
		try {
			field = this.getClass().getField(att + "s");

			values = (List<Integer>) field.get(this);

			for (int v : values) {
				if (v == value) {
					nb++;
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		return nb / values.size();
	}

	public Set<Integer> getUniqueValues(String att) {
		Set<Integer> uniqueValues = new HashSet<>();
		Field field = null;
		try {
			field = this.getClass().getField(att + "s");

			uniqueValues.addAll((List<Integer>) field.get(this));
		} catch (Exception e) {
			System.out.println(e);
		}

		return uniqueValues;
	}

	public String getPlusPertinent() {
		Double pertinence = 0d;
		Field fieldPlusPertinent = null;

		try {
			Field[] fields = Entry.class.getFields();
			for (int i = 0; i < fields.length - 1; i++) {
				Double p = pertinence(fields[i].getName());
				if (p > pertinence) {
					pertinence = p;
					fieldPlusPertinent = fields[i];
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		if (fieldPlusPertinent == null) {
			return null;
		}

		return fieldPlusPertinent.getName();
	}

	// TODO: Ajouter les conditions d'arrêt
	// TODO: Ajouter l'appel à generateTree de l'enfant
	public Node generateTree() {
		String plusPertinent = getPlusPertinent();
		setValue(plusPertinent);
		for (Integer value : getUniqueValues(plusPertinent)) {
			Branch branch = addChild(new Branch(Integer.toString(value)));
			branch.setChild(new Node(value, plusPertinent, data));
		}
		return this;
	}
}
