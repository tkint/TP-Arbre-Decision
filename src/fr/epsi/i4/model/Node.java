package fr.epsi.i4.model;

import static fr.epsi.i4.utils.ConsoleColors.BLUE;
import static fr.epsi.i4.utils.ConsoleColors.GREEN;
import static fr.epsi.i4.utils.ConsoleColors.RED;
import static fr.epsi.i4.utils.ConsoleColors.RESET;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tkint on 23/11/2017.
 */
public class Node {

	private String value;

	private List<Branch> children;

	private List<Entry> data;

	private static LinkedHashMap<String, List<Integer>> attributs = new LinkedHashMap<>();

	private static List<String> values = new ArrayList<>();

	private static Integer id = 0;

	public Node() {
		this.children = new ArrayList<>();
		this.data = new ArrayList<>();
	}

	public Node(Integer valueToKeep, Integer att, List<Entry> data) {
		this();
		for (Entry entry : data) {
			if (entry.getParams().size() > att && entry.getParams().get(att).equals(valueToKeep)) {
				addEntry(entry);
			}
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

	public List<Entry> getData() {
		return data;
	}

	public void setData(List<Entry> data) {
		this.data = data;
	}

	public static LinkedHashMap<String, List<Integer>> getAttributs() {
		return attributs;
	}

	public static void setAttributs(LinkedHashMap<String, List<Integer>> attributs) {
		Node.attributs = attributs;
	}

	public static List<String> getValues() {
		return values;
	}

	public static void setValues(List<String> values) {
		Node.values = values;
	}

	public static Integer getId() {
		return id;
	}

	public static void setId(Integer id) {
		Node.id = id;
	}

    public Branch addChild(Branch branch) {
		children.add(branch);
		return branch;
	}

	public Entry addEntry(Entry entry) {
		try {
			data.add(entry);
		} catch (OutOfMemoryError e) {
			System.out.println(data.size());
			throw e;
		}
		return entry;
	}

	public double entropie(int value, Integer att) {
		double pG = 0;
		double pH = 0;
		double pD = 0;
		double pB = 0;

		for (Entry entry : data) {
			if (entry.getParams().size() < att + 1) {
				return 0d;
			} else if (entry.getParams().get(att) == value) {
				switch (entry.getResult()) {
					case 0:
						pG++;
						break;
					case 1:
						pH++;
						break;
					case 2:
						pD++;
						break;
					case 3:
						pB++;
						break;
				}
			}
		}

		// if (pOui == 0 || pNon == 0) {
		// return 0d;
		// }
		pG /= data.size();
		pH /= data.size();
		pD /= data.size();
		pB /= data.size();

		// return -pOui * log2(pOui) - pNon * log2(pNon);
		return -pG * log2(pG) - pH * log2(pH) - pD * log2(pD) - pB * log2(pB);
	}

	public double entropie() {
		double pG = 0;
		double pH = 0;
		double pD = 0;
		double pB = 0;

		for (Entry entry : data) {
			switch (entry.getResult()) {
				case 0:
					pG++;
					break;
				case 1:
					pH++;
					break;
				case 2:
					pD++;
					break;
				case 3:
					pB++;
					break;
			}
		}
		// if (pOui == 0 || pNon == 0) {
		// return 0d;
		// }

		pG /= data.size();
		pH /= data.size();
		pD /= data.size();
		pB /= data.size();

		// return -pOui * log2(pOui) - pNon * log2(pNon);
		return -pG * log2(pG) - pH * log2(pH) - pD * log2(pD) - pB * log2(pB);
	}

	private double log2(double x) {
		if (x == 0) {
			return 0d;
		}
		return Math.log(x) / Math.log(2.);
	}

	public Double pertinence(Integer att) {
		Double total = entropie();
		for (int v : getUniqueValues(att)) {
			total -= partPertinence(v, att);
		}
		return total;
	}

	private double partPertinence(int value, Integer att) {
		return getRatio(value, att) * entropie(value, att);
	}

	public double getRatio(int value, Integer att) {
		double nb = 0d;

		for (Entry entry : data) {
			if (entry.getParams().size() < att + 1) {
				return 0d;
			} else if (entry.getParams().get(att) == value) {
				nb++;
			}
		}

		if (data.size() == 0) {
			return 0d;
		}
		return nb / data.size();
	}

	public List<Integer> getUniqueValues(Integer att) {
		Iterator<String> itr;
		itr = attributs.keySet().iterator();
		for (int i = 0; i < att; i++) {
			itr.next();
		}
		List<Integer> uniqueValues = attributs.get(itr.next());

		return uniqueValues;
	}

	public Integer getPlusPertinent() {
		Double pertinence = 0d;
		Integer fieldPlusPertinent = -1;
		for (int i = 0; i < attributs.size(); i++) {
			Double p = pertinence(i);
			if (p > pertinence) {
				pertinence = p;
				fieldPlusPertinent = i;
			}
		}

		if (fieldPlusPertinent == -1) {
			return null;
		}

		return fieldPlusPertinent;
	}

	public String getOuiNon() {
		double pG = 0;
		double pH = 0;
		double pD = 0;
		double pB = 0;

		for (Entry entry : data) {
			switch (entry.getResult()) {
				case 0:
					pG++;
					break;
				case 1:
					pH++;
					break;
				case 2:
					pD++;
					break;
				case 3:
					pB++;
					break;
			}
		}

		if (pG >= pH && pG >= pD && pG >= pB) {
			return "Aller à Gauche";
		}
		if (pH >= pG && pH >= pD && pH >= pB) {
			return "Aller à Haut";
		}
		if (pD >= pH && pD >= pG && pD >= pB) {
			return "Aller à Droite";
		}
		if (pB >= pH && pB >= pD && pB >= pG) {
			return "Aller à Bas";
		}
		return "Merde !!!";
	}

	public void regenerateTree() {
		setChildren(new ArrayList<>());
		generateTree();
	}

	public void generateTree() {
		Integer plusPertinent = getPlusPertinent();
		if (plusPertinent != null) {
			int index = 0;
			String key = "";
			for (Map.Entry<String, List<Integer>> entry : attributs.entrySet()) {
				if (index == plusPertinent) {
					key = entry.getKey();
				}
				index++;
			}

			setValue(key);
			for (Integer value : getUniqueValues(plusPertinent)) {
				Branch branch = addChild(new Branch(String.valueOf(value)));
				Node child = branch.setChild(new Node(value, plusPertinent, data));
				child.generateTree();
			}
		} else {
			setValue(getOuiNon());
		}
	}

	public String getStringValue(Integer value) {
		String stringValue = values.get(value);

		return stringValue;
	}

	public void print() {
		print("#", true, "");
	}

	private void print(String prefix, boolean isTail, String branchValue) {
		boolean ouiNon = value.toLowerCase().equals("oui") || value.toLowerCase().equals("non");
		String txt = prefix;
		txt += branchValue != "" ? isTail ? "└── " : "├── " : " ── ";
		txt += branchValue != "" ? BLUE + branchValue + RESET + " => " : BLUE;
		txt += ouiNon ? GREEN : RED;
		txt += value.toUpperCase() + RESET;
		System.out.println(txt);
		txt = prefix;
		txt += isTail ? "     " : "│    ";
		for (int i = 0; i < children.size() - 1; i++) {
			branchValue = getStringValue(Integer.valueOf(children.get(i).getValue()));
			// branchValue += " (" + children.get(i).getValue() + ")";
			children.get(i).getChild().print(txt, false, branchValue.toUpperCase());
		}
		if (children.size() > 0) {
			branchValue = getStringValue(Integer.valueOf(children.get(children.size() - 1).getValue()));
			branchValue += " (" + children.get(children.size() - 1).getValue() + ")";
			children.get(children.size() - 1).getChild().print(txt, true, branchValue.toUpperCase());
		}
	}

	public String decide(Entry entry) {
		if (getChildren().size() == 0) {
			return value;
		} else {
			for (Branch child : getChildren()) {
				if (entry.getParams().contains(Integer.valueOf(child.getValue()))) {
					return child.getChild().decide(entry);
				}
			}
		}
		return null;
	}

	public Integer getIndexOfAttribut(String att) {
		List<String> keys = new ArrayList<String>(attributs.keySet());
		for (String s : keys) {
			if (att.equals(s)) {
				return keys.indexOf(s);
			}
		}
		return null;
	}
}
