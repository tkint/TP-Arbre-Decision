package fr.epsi.i4.model;

/**
 * Created by tkint on 23/11/2017.
 */
public class Branch {

    private String value;
    private Node child;

    public Branch(String value) {
        this.value = value;
    }

    public Branch(String value, Node child) {
        this.value = value;
        this.child = child;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Node getChild() {
        return child;
    }

    public Node setChild(Node child) {
        this.child = child;
        return child;
    }

    public void display() {
        System.out.print(value + " | ");
    }
}
