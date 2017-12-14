package fr.epsi.i4.model;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tkint on 23/11/2017.
 */
public class Entry {

    private boolean result;
    
    private List<Integer> params;

    public Entry() {
        this.params = new ArrayList<>();
    }

    public Entry(boolean result, int... values) {
        this.result = result;
        this.params = new ArrayList<>();
        for (int v : values) {
            this.params.add(v);
        }
    }

    public Entry(boolean result, List<Integer> params) {
        this.result = result;
        this.params = params;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public List<Integer> getParams() {
        return params;
    }

    public void setParams(List<Integer> params) {
        this.params = params;
    }
    
    public void addParam(Integer param){
        params.add(param);
    }
    
    public void StringToObject(String readLine){
        String[] atts = readLine.split(",");
        for (int i = 0; i < atts.length - 1; i++){
            params.add(Integer.valueOf(atts[i]));
        }
        if (atts[atts.length - 1].equals("false")){
            result = false;
        } else {
            result = true;
        }
    }

    @Override
    public String toString() {
        String s ="";
        for(Integer i : params){
            s +=  i + ",";
        }
        s += result;
        return s;
    }
    
    
}
