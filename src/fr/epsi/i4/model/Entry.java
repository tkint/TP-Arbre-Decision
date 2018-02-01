package fr.epsi.i4.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tkint on 23/11/2017.
 */
public class Entry {

	private List<Integer> values;

	private Integer decision;

	public Entry(Integer... values) {
		this.values = new ArrayList<>();
		for (int i = 0; i < values.length - 1; i++) {
			this.values.add(values[i]);
		}
		decision = values[values.length - 1];
	}

	public Entry(int decision, List<Integer> values) {
		this.decision = decision;
		this.values = values;
	}

	public static Entry fromText(String line) {
		List<Integer> intAtts = new ArrayList<>();
		String[] stringAtts = line.split(",");
		for (String stringAtt : stringAtts) {
			intAtts.add(Integer.valueOf(stringAtt));
		}
		return new Entry(intAtts.toArray(new Integer[stringAtts.length]));
	}

	public Integer getDecision() {
		return decision;
	}

	public void setDecision(Integer decision) {
		this.decision = decision;
	}

	public List<Integer> getValues() {
		return values;
	}

	public void setValues(List<Integer> values) {
		this.values = values;
	}

	public String toText() {
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < values.size(); i++) {
			str.append(values.get(i)).append(",");
		}
		str.append(decision);
		return str.toString();
	}
}
