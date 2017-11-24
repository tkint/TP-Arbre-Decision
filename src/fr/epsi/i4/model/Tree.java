package fr.epsi.i4.model;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tkint on 23/11/2017.
 */
public class Tree {

    private Node tree;
    private List<Entry> data;

    private List<Integer> ciels;
    private List<Integer> temperatures;
    private List<Integer> humidites;
    private List<Integer> vents;
    private List<Integer> jouers;

    public Tree() {
        data = new ArrayList<>();

        ciels = new ArrayList<>();
        temperatures = new ArrayList<>();
        humidites = new ArrayList<>();
        vents = new ArrayList<>();
        jouers = new ArrayList<>();
    }

    public Entry addEntry(Entry entry) {
        ciels.add(entry.getCiel());
        temperatures.add(entry.getTemperature());
        humidites.add(entry.getHumidite());
        vents.add(entry.getVent());
        jouers.add(entry.getJouer());

        data.add(entry);

        return entry;
    }


    public double entropie(int value, String att) {
        int pOui = 0;
        int pNon = 0;
        Field field = null;
        try {
            field = Entry.class.getField(att);

            for (Entry entry : data) {
                if (((Integer) field.get(entry)) == value) {
                    if (entry.getJouer() == 0) {
                        pNon++;
                    } else {
                        pOui++;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return -pOui * log2(pOui) - pNon * log2(pNon);

    }

    private double log2(int x) {
        return Math.log10(x) / Math.log(2.);
    }
}
