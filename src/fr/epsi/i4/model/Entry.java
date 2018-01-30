package fr.epsi.i4.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tkint on 23/11/2017.
 */
public class Entry {

	private int result;

	private List<Integer> params;

	public Entry() {
		params = new ArrayList<>();
	}

	public Entry(Integer... params) {
		this.params = new ArrayList<>();
		for (int i = 0; i < params.length - 1; i++) {
			this.params.add(params[i]);
		}
		result = params[params.length - 1];
	}

	public Entry(int result, List<Integer> params) {
		this.result = result;
		this.params = params;
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

	public List<Integer> getParams() {
		return params;
	}

	public void setParams(List<Integer> params) {
		this.params = params;
	}

	public void addParam(Integer param) {
		params.add(param);
	}

	public String toText() {
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < params.size(); i++) {
			str.append(params).append(",");
		}
		str.append(result);
		return str.toString();
	}
}
