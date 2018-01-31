package fr.epsi.i4.model;

/**
 * Created by tkint on 23/11/2017.
 */
public class Branch {

	private Integer valueIndex;

	private Node node;

	public Branch(Integer valueIndex) {
		this.valueIndex = valueIndex;
	}

	public Branch(Integer valueIndex, Node node) {
		this.valueIndex = valueIndex;
		this.node = node;
	}

	public Integer getValueIndex() {
		return valueIndex;
	}

	public void setValueIndex(Integer valueIndex) {
		this.valueIndex = valueIndex;
	}

	public Node getNode() {
		return node;
	}

	public Node setChild(Node child) {
		this.node = child;
		return child;
	}
}
