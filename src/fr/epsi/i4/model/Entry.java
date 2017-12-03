package fr.epsi.i4.model;

import java.util.HashMap;

/**
 * Created by tkint on 23/11/2017.
 */
public class Entry {

    private HashMap<String, Integer> values;

    private boolean result;


    public Entry(HashMap<String, Integer> values, boolean result) {
        this.values = values;
        this.result = result;
    }

    public HashMap<String, Integer> getValues() {
        return values;
    }

    public void setValues(HashMap<String, Integer> values) {
        this.values = values;
    }

    public boolean getResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}
