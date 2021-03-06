package fr.epsi.i4.model;

import java.lang.reflect.Field;

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

    public Entry(Integer ciel, Integer temperature, Integer humidite, Integer vent, Integer jouer) {
        this.ciel = ciel;
        this.temperature = temperature;
        this.humidite = humidite;
        this.vent = vent;
        this.jouer = jouer;
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
}
