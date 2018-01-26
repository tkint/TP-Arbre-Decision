package fr.epsi.i4.model;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tkint on 23/11/2017.
 */
public class Entry {

    // 0, 1, 2
    // SOLEIL, COUVERT, PLUIE
    public Integer ciel;
    // CHAUD, DOUX, FROID
    public Integer temperature;
    // ELEVEE, NORMALE
    public Integer humidite;
    // FAIBLE, FORT
    public Integer vent;
    // NON, OUI
    public Integer jouer;

    private int result;

    private List<Integer> params;

    public Entry() {
        params = new ArrayList<>();
    }

    public Entry(Integer ciel, Integer temperature, Integer humidite, Integer vent, Integer jouer) {
        this.ciel = ciel;
        this.temperature = temperature;
        this.humidite = humidite;
        this.vent = vent;
        this.result = jouer;

        params = new ArrayList<>();
        params.add(ciel);
        params.add(temperature);
        params.add(humidite);
        params.add(vent);
    }

    public Entry(int result, List<Integer> params) {
        this.result = result;
        this.params = params;
    }

    public Integer getCiel() {
        return ciel;
    }

    public void setCiel(Integer ciel) {
        this.ciel = ciel;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    public Integer getHumidite() {
        return humidite;
    }

    public void setHumidite(Integer humidite) {
        this.humidite = humidite;
    }

    public Integer getVent() {
        return vent;
    }

    public void setVent(Integer vent) {
        this.vent = vent;
    }

    public Integer getJouer() {
        return jouer;
    }

    public void setJouer(Integer jouer) {
        this.jouer = jouer;
    }

    public Entry clone() {
        return clone(jouer);
    }

    public Entry clone(Integer jouer) {
        return new Entry(ciel, temperature, humidite, vent, jouer);
    }

    public int isResult() {
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

    public void StringToObject(String readLine) {
        String[] atts = readLine.split(",");
        for (int i = 0; i < atts.length - 1; i++) {
            params.add(Integer.valueOf(atts[i]));
        }
        result = Integer.valueOf(atts[atts.length - 1]);
    }

    @Override
    public String toString() {
        String s = "";
        for (Integer i : params) {
            s += i + ",";
        }
        s += result;
        return s;
    }

}
