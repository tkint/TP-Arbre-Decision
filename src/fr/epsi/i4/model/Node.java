package fr.epsi.i4.model;

import static fr.epsi.i4.utils.ConsoleColors.BLUE;
import static fr.epsi.i4.utils.ConsoleColors.GREEN;
import static fr.epsi.i4.utils.ConsoleColors.RED;
import static fr.epsi.i4.utils.ConsoleColors.RESET;

import java.util.ArrayList;
import java.util.List;

import fr.epsi.i4.Config;

/**
 * Created by tkint on 23/11/2017.
 */
public class Node {

	private int attributIndex;

	private List<Branch> branches;

	private List<Entry> entries;

	private Config config;

	public Node(Config config) {
		this.config = config;
		this.branches = new ArrayList<>();
		this.entries = new ArrayList<>();
	}

	public Node(Config config, Integer valueToKeep, Integer att, List<Entry> entries) {
		this(config);
		for (Entry entry : entries) {
			if (entry.getValues().size() > att && entry.getValues().get(att).equals(valueToKeep)) {
				addEntry(entry);
			}
		}
	}

	public int getAttributIndex() {
		return attributIndex;
	}

	public void setAttributIndex(int attributIndex) {
		this.attributIndex = attributIndex;
	}

	public List<Branch> getBranches() {
		return branches;
	}

	public void setBranches(List<Branch> branches) {
		this.branches = branches;
	}

	public List<Entry> getEntries() {
		return entries;
	}

	public void setEntries(List<Entry> entries) {
		this.entries = entries;
	}

	public Branch addBranch(Branch branch) {
		branches.add(branch);
		return branch;
	}

	public Entry addEntry(Entry entry) {
		entries.add(entry);
		return entry;
	}

	public double entropie(int attributIndex, int valueIndex) {
		double[] p = new double[config.getResultats().size()];
		for (int i = 0; i < p.length; i++) {
			p[i] = 0;
		}

		for (Entry entry : entries) {
			if (attributIndex + 1 > entry.getValues().size()) {
				return 0d;
			} else if (entry.getValues().get(attributIndex) == valueIndex) {
				p[entry.getResult()]++;
			}
		}

		for (int i = 0; i < p.length; i++) {
			p[i] /= entries.size();
		}

		double entropie = 0;

		for (int i = 0; i < p.length; i++) {
			entropie = entropie - (p[i] * log2(p[i]));
		}

		return entropie;
	}

	public double entropie() {
		double[] p = new double[config.getResultats().size()];
		for (int i = 0; i < p.length; i++) {
			p[i] = 0;
		}

		for (Entry entry : entries) {
			p[entry.getResult()]++;
		}

		for (int i = 0; i < p.length; i++) {
			p[i] /= entries.size();
		}

		double entropie = 0;

		for (int i = 0; i < p.length; i++) {
			entropie = entropie - (p[i] * log2(p[i]));
		}

		return entropie;
	}

	private double log2(double x) {
		if (x == 0) {
			return 0d;
		}
		return Math.log(x) / Math.log(2.);
	}

	/**
	 * Retourne la pertinence d'un attribut
	 * 
	 * @param attributIndex
	 * @return
	 */
	public Double pertinence(int attributIndex) {
		double pertinence = entropie();
		for (int valueIndex = 0; valueIndex < config.getAttributByIndex(attributIndex)
				.getValues().length; valueIndex++) {
			pertinence -= partPertinence(attributIndex, valueIndex);
		}
		return pertinence;
	}

	/**
	 * Retourne la pertinence d'une valeur d'un attribut
	 * 
	 * @param attributIndex
	 * @param valueIndex
	 * @return
	 */
	private double partPertinence(int attributIndex, int valueIndex) {
		return getRatio(attributIndex, valueIndex) * entropie(attributIndex, valueIndex);
	}

	/**
	 * Retourne le ratio d'une valeur d'un attribut sur les entries du noeud courant
	 * 
	 * @param attributIndex
	 * @param valueIndex
	 * @return
	 */
	public double getRatio(int attributIndex, int valueIndex) {
		double count = 0d;

		// Pour chaque entry
		for (Entry entry : entries) {
			if (attributIndex + 1 > entry.getValues().size()) {
				return 0d;
			} else if (entry.getValues().get(attributIndex) == valueIndex) {
				count++;
			}
		}

		if (entries.size() == 0) {
			return 0d;
		}

		return count / entries.size();
	}

	public Integer getPlusPertinent() {
		Double pertinence = 0d;
		Integer plusPertinent = -1;
		for (int i = 0; i < config.getAttributs().size(); i++) {
			Double p = pertinence(i);
			if (p > pertinence) {
				pertinence = p;
				plusPertinent = i;
			}
		}

		if (plusPertinent == -1) {
			return null;
		}

		return plusPertinent;
	}

	public int getResultat() {
		List<Integer> p = new ArrayList<>();

		for (int i = 0; i < config.getResultats().size(); i++) {
			p.add(0);
		}

		for (Entry entry : entries) {
			p.set(entry.getResult(), p.get(entry.getResult()) + 1);
		}

		int max = 0;
		int maxIndex = 0;
		for (int i = 0; i < p.size(); i++) {
			if (p.get(i) > max) {
				max = p.get(i);
				maxIndex = i;
			}
		}

		return maxIndex;
	}

	/**
	 * Regenerate tree
	 */
	public void regenerateTree() {
		setBranches(new ArrayList<>());
		generateTree();
	}

	/**
	 * Generate tree
	 */
	public void generateTree() {
		Integer plusPertinent = getPlusPertinent();
		if (plusPertinent != null) {
			setAttributIndex(plusPertinent);
			for (int valueIndex = 0; valueIndex < config.getAttributByIndex(plusPertinent)
					.getValues().length; valueIndex++) {
				Branch branch = addBranch(new Branch(valueIndex));
				Node child = branch.setChild(new Node(config, valueIndex, plusPertinent, entries));
				child.generateTree();
			}
		} else {
			setAttributIndex(getResultat());
		}
	}

	public int decide(Entry entry) {
		if (getBranches().size() == 0) {
			return attributIndex;
		} else {
			for (Branch branch : getBranches()) {
				if (entry.getValues().get(attributIndex).equals(branch.getValueIndex())) {
					return branch.getNode().decide(entry);
				}
			}
		}
		return -1;
	}

	public void print() {
		print("#", true, "");
	}

	private void print(String prefix, boolean isTail, String branchValue) {
		boolean result = branches.isEmpty();
		String txt = prefix;
		txt += !branchValue.equals("") ? isTail ? "└── " : "├── " : " ── ";
		txt += !branchValue.equals("") ? BLUE + branchValue + RESET + " => " : BLUE;
		txt += result ? GREEN + config.getResultats().get(attributIndex) : RED + config.getAttributByIndex(
				attributIndex).getName();
		txt += " (" + attributIndex + ") " + RESET;
		System.out.println(txt);
		txt = prefix;
		txt += isTail ? "     " : "│    ";
		for (int i = 0; i < branches.size() - 1; i++) {
			branchValue = config.getValue(attributIndex, branches.get(i).getValueIndex());
			branchValue += " (" + branches.get(i).getValueIndex() + ")";
			branches.get(i).getNode().print(txt, false, branchValue);
		}
		if (branches.size() > 0) {
			branchValue = config.getValue(attributIndex, branches.get(branches.size() - 1).getValueIndex());
			branchValue += " (" + branches.get(branches.size() - 1).getValueIndex() + ")";
			branches.get(branches.size() - 1).getNode().print(txt, true, branchValue);
		}
	}
}
