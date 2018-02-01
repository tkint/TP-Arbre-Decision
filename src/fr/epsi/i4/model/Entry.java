package fr.epsi.i4.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tkint on 23/11/2017.
 */
public class Entry {

	private Integer result;

	private List<Integer> values;

	public Entry() {
		values = new ArrayList<>();
	}

	public Entry(Integer... values) {
		this.values = new ArrayList<>();
		for (int i = 0; i < values.length - 1; i++) {
			this.values.add(values[i]);
		}
		result = values[values.length - 1];
	}

	public Entry(int result, List<Integer> values) {
		this.result = result;
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

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public List<Integer> getValues() {
		return values;
	}

	public void setValues(List<Integer> values) {
		this.values = values;
	}

	public void addParam(Integer param) {
		values.add(param);
	}

	public String toText() {
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < values.size(); i++) {
			str.append(values.get(i)).append(",");
		}
		str.append(result);
		return str.toString();
	}
}
