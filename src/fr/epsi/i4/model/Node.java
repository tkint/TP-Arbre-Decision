package fr.epsi.i4.model;

import static fr.epsi.i4.utils.ConsoleColors.BLUE;
import static fr.epsi.i4.utils.ConsoleColors.GREEN;
import static fr.epsi.i4.utils.ConsoleColors.RED;
import static fr.epsi.i4.utils.ConsoleColors.RESET;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import javafx.util.Pair;

/**
 * Created by tkint on 23/11/2017.
 */
public class Node {

	private String value;

	private List<Branch> children;

	private List<Entry> data;

	private HashMap<String, HashMap<Integer, String>> references;

	public Node() {
		this.children = new ArrayList<>();
		this.data = new ArrayList<>();
		this.references = new HashMap<>();
	}

	private Node(Integer valueToKeep, String att, Node parent) {
		this();

		for (Entry entry : parent.data) {
			if (entry.getValues().get(att).equals(valueToKeep)) {
				this.data.add(entry);
				for (Map.Entry<String, Integer> e : entry.getValues().entrySet()) {
					addReference(new AbstractMap.SimpleEntry<>(e.getKey(), new Pair<>(e.getValue(), parent.references
							.get(e.getKey())
							.get(e.getValue()))));
				}
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

	public HashMap<String, HashMap<Integer, String>> getReferences() {
		return references;
	}

	public void setReferences(HashMap<String, HashMap<Integer, String>> references) {
		this.references = references;
	}

	public Branch addChild(Branch branch) {
		children.add(branch);
		return branch;
	}

	public void addReference(Map.Entry<String, Pair<Integer, String>> entry) {
		// Si le champ de reference n'existe pas
		if (!references.containsKey(entry.getKey()) || references.get(entry.getKey()) == null) {
			HashMap<Integer, String> keyValue = new HashMap();
			keyValue.put(entry.getValue().getKey(), entry.getValue().getValue());
			references.put(entry.getKey(), keyValue);
			// Si la reference n'existe pas
		} else if (!references.get(entry.getKey()).containsKey(entry.getValue().getKey()) || references.get(entry
				.getKey()).get(entry.getValue().getKey()) == null) {
			references.get(entry.getKey()).put(entry.getValue().getKey(), entry.getValue().getValue());
		}
	}

	public Entry addEntry(boolean result, HashMap<String, Pair<Integer, String>> newEntry) {
		HashMap<String, Integer> entryValues = new HashMap<>();
		newEntry.forEach((key, value) -> {
			addReference(new AbstractMap.SimpleEntry<>(key, new Pair<>(value.getKey(), value.getValue())));
			entryValues.put(key, value.getKey());
		});

		Entry entry = new Entry(entryValues, result);
		data.add(entry);

		return entry;
	}

	public Entry addEntry(boolean result, Map.Entry<String, Pair<Integer, String>>... entryKeyValues) {
		HashMap<String, Integer> entryValues = new HashMap<>();

		for (Map.Entry<String, Pair<Integer, String>> entry : entryKeyValues) {
			addReference(entry);
			entryValues.put(entry.getKey(), entry.getValue().getKey());
		}

		Entry entry = new Entry(entryValues, result);
		data.add(entry);

		return entry;
	}

	public double entropie(Integer value, String att) {
		double pYes = 0;
		double pNo = 0;

		for (Entry entry : data) {
			if ((entry.getValues().containsKey(att) && entry.getValues().get(att).equals(value)) ^ (value == null
					&& att == null)) {
				if (entry.getResult()) {
					pYes++;
				} else {
					pNo++;
				}
			}
		}

		if (pYes == 0 || pNo == 0) {
			return 0d;
		}

		pYes /= data.size();
		pNo /= data.size();

		return -pYes * log2(pYes) - pNo * log2(pNo);
	}

	private double log2(double x) {
		if (x == 0) {
			return 0d;
		}
		return Math.log(x) / Math.log(2.);
	}

	public Double pertinence(String att) {
		Double total = entropie(null, null);
		for (Integer v : getUniqueValues(att)) {
			total -= partPertinence(v, att);
		}
		return total;
	}

	private double partPertinence(Integer value, String att) {
		return getRatio(value, att) * entropie(value, att);
	}

	public double getRatio(Integer value, String att) {
		double count = (double) references.get(att).keySet().stream().filter(key -> key.equals(value)).count();

		return count / getUniqueValues(att).size();
	}

	public Set<Integer> getUniqueValues(String att) {
		Set<Integer> uniqueValues = new HashSet<>();

		references.get(att).forEach((key, value) -> uniqueValues.add(key));

		return uniqueValues;
	}

	public String getPlusPertinent() {
		String fieldPlusPertinent = null;

		Double pertinence = 0d;
		Double p;

		for (Map.Entry<String, HashMap<Integer, String>> entry : references.entrySet()) {
			p = pertinence(entry.getKey());
			if (p > pertinence) {
				fieldPlusPertinent = entry.getKey();
				pertinence = p;
			}
		}

		return fieldPlusPertinent;
	}

	private String getOuiNon() {
		Pair<Integer, Integer> yesNo = countYesNo();

		if (yesNo.getKey() > yesNo.getValue()) {
			return "Oui";
		}
		return "Non";
	}

	private Pair<Integer, Integer> countYesNo() {
		Integer yes = 0;
		Integer no = 0;

		for (Entry entry : data) {
			if (entry.getResult()) {
				yes++;
			} else {
				no++;
			}
		}

		return new Pair<>(yes, no);
	}

	public void generateTree() {
		String plusPertinent = getPlusPertinent();
		if (plusPertinent != null) {
			setValue(plusPertinent);
			for (Integer value : getUniqueValues(plusPertinent)) {
				Branch branch = addChild(new Branch(String.valueOf(value)));
				Node child = branch.setChild(new Node(value, plusPertinent, this));
				child.generateTree();
			}
		} else {
			setValue(getOuiNon());
		}
	}

	private String getStringValue(Integer value, String att) {
		if (references.get(att) == null || references.get(att).isEmpty()) {
			System.out.println("Value not found");
			return null;
		}
		return references.get(att).get(value);
	}

	public void print() {
		print("#", true, "");
	}

	private void print(String prefix, boolean isTail, String branchValue) {
		boolean ouiNon = value.toLowerCase().equals("oui") || value.toLowerCase().equals("non");
		String txt = prefix;
		txt += !branchValue.equals("") ? isTail ? "└── " : "├── " : " ── ";
		txt += !branchValue.equals("") ? BLUE + branchValue + RESET + " => " : BLUE;
		txt += ouiNon ? GREEN : RED;
		txt += value.toUpperCase() + RESET;
		System.out.println(txt);
		txt = prefix;
		txt += isTail ? "     " : "│    ";
		for (int i = 0; i < children.size() - 1; i++) {
			branchValue = getStringValue(Integer.valueOf(children.get(i).getValue()), getValue());
			branchValue += " (" + children.get(i).getValue() + ")";
			children.get(i).getChild().print(txt, false, branchValue.toUpperCase());
		}
		if (children.size() > 0) {
			branchValue = getStringValue(Integer.valueOf(children.get(children.size() - 1).getValue()), getValue());
			branchValue += " (" + children.get(children.size() - 1).getValue() + ")";
			children.get(children.size() - 1).getChild().print(txt, true, branchValue.toUpperCase());
		}
	}

	public void decide() {
		Scanner input = new Scanner(System.in);
		System.out.println(getValue());
		if (getChildren().size() > 0) {
			for (Branch child : getChildren()) {
				System.out.print(getStringValue(Integer.valueOf(child.getValue()), getValue()));
				System.out.print("(" + child.getValue() + ") ");
			}
			System.out.println("");
			int i = input.nextInt();
			for (Branch child : getChildren()) {
				if (Integer.valueOf(child.getValue()).equals(i)) {
					child.getChild().decide();
				}
			}
		}
	}
}
