package fr.epsi.i4.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tkint on 23/11/2017.
 */
public class Tree {

	public List<Integer> ciels;

	public List<Integer> temperatures;

	public List<Integer> humidites;

	public List<Integer> vents;

	public List<Integer> jouers;

	private Node tree;

	private List<Entry> data;

	public Tree() {
		data = new ArrayList<>();

		ciels = new ArrayList<>();
		temperatures = new ArrayList<>();
		humidites = new ArrayList<>();
		vents = new ArrayList<>();
		jouers = new ArrayList<>();
	}
/*
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
        double pOui = 0;
        double pNon = 0;
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

        if (pOui == 0 || pNon == 0) {
            return 0d;
        }

        pOui /= data.size();
        pNon /= data.size();

        return -pOui * log2(pOui) - pNon * log2(pNon);
    }

    public double entropie() {
        double pOui = 0;
        double pNon = 0;

        for (Entry entry : data) {
            if (entry.getJouer() == 0) {
                pNon++;
            } else {
                pOui++;
            }
        }
        if (pOui == 0 || pNon == 0) {
            return 0d;
        }

        pOui /= data.size();
        pNon /= data.size();

        return -pOui * log2(pOui) - pNon * log2(pNon);
    }

    private double log2(double x) {
        if (x == 0) {
            return 0d;
        }
        return Math.log(x) / Math.log(2.);
    }

//    public Double pertinence(String att) {
//        Double total = null;
//        for (int v : getUniqueValues(att)) {
//            if (total == null) {
//                total = partPertinence(v, att);
//            } else {
//                total -= partPertinence(v, att);
//            }
//        }
//        return total;
//    }
    public Double pertinence(String att) {
        Double total = entropie();
        for (int v : getUniqueValues(att)) {
            total -= partPertinence(v, att);
        }
        return total;
    }

    private double partPertinence(int value, String att) {
        return getRatio(value, att) * entropie(value, att);
    }

    public double getRatio(int value, String att) {
        double nb = 0d;
        Field field = null;
        List<Integer> values = new ArrayList<>();
        try {
            field = this.getClass().getField(att + "s");

            values = (List<Integer>) field.get(this);

            for (int v : values) {
                if (v == value) {
                    nb++;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return nb / values.size();
    }

    public Set<Integer> getUniqueValues(String att) {
        Set<Integer> values = new HashSet<>();
        Field field = null;
        try {
            field = this.getClass().getField(att + "s");

            values.addAll((List<Integer>) field.get(this));
        } catch (Exception e) {
            System.out.println(e);
        }

        return values;
    }
*/
}
