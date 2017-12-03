package fr.epsi.i4.model;

import javafx.util.Pair;

import java.lang.reflect.Field;
import java.util.*;

import static fr.epsi.i4.utils.ConsoleColors.*;

/**
 * Created by tkint on 23/11/2017.
 */
public class Node {

    private String value;

    private List<Branch> children;

    private List<Entry> data;

    private HashMap<String, HashMap<Integer, String>> references;

    public Node() {
        this.children = new ArrayList<>();
        this.data = new ArrayList<>();
        this.references = new HashMap<>();
    }

    public Node(Integer valueToKeep, String att, List<Entry> data) {
        this();
        Field field = null;
        try {
            field = Entry.class.getField(att);

            for (Entry entry : data) {
                if (field.get(entry).equals(valueToKeep)) {
                    addEntry(entry);
                }
            }
        } catch (Exception e) {
            System.out.println("Node: " + e);
        }
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<Branch> getChildren() {
        return children;
    }

    public void setChildren(List<Branch> children) {
        this.children = children;
    }

    public Branch addChild(Branch branch) {
        children.add(branch);
        return branch;
    }

    public Entry addEntry(HashMap<String, Pair<Integer, String>> newEntry, boolean result) {
        List<Integer> entryValues = new ArrayList<>();
        newEntry.forEach((key, value) -> {
            if (references.get(key) == null) {
                HashMap<Integer, String> keyValue = new HashMap();
                keyValue.put(value.getKey(), value.getValue());
                references.put(key, keyValue);
            } else {
                // TODO: Si la reference est vide, il faut créer le HashMap et lui ajouter la nouvelle valeur
                // TODO: Sinon, simplement ajouter la nouvelle valeur
            }
//                value.forEach((subKey, subValue) -> {
//                    if (references.get(key).get(subKey).isEmpty()) {
//                        references.get(key).put(subKey, subValue);
//                    }
//                    entryValues.add(subKey);
//                });
        });

        Entry entry = new Entry(entryValues, result);
        data.add(entry);

        return entry;
    }

    public Entry addEntry(Entry entry) {
        try {
            data.add(entry);
        } catch (OutOfMemoryError e) {
            System.out.println(data.size());
            throw e;
        }
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
            System.out.println("entropie: " + e);
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
            field = Entry.class.getField(att);

            for (Entry entry : data) {
                values.add((Integer) field.get(entry));
            }

            for (int v : values) {
                if (v == value) {
                    nb++;
                }
            }
        } catch (Exception e) {
            System.out.println("getRatio: " + e);
        }

        return nb / values.size();
    }

    public Set<Integer> getUniqueValues(String att) {
        Set<Integer> uniqueValues = new HashSet<>();
        Field field = null;
        try {
            field = Entry.class.getField(att);

            for (Entry entry : data) {
                uniqueValues.add((Integer) field.get(entry));
            }
        } catch (Exception e) {
            System.out.println("getUniqueValues: " + e);
        }

        return uniqueValues;
    }

    public String getPlusPertinent() {
        Double pertinence = 0d;
        Field fieldPlusPertinent = null;

        try {
            Field[] fields = Entry.class.getFields();
            for (int i = 0; i < fields.length - 1; i++) {
                Double p = pertinence(fields[i].getName());
                if (p > pertinence) {
                    pertinence = p;
                    fieldPlusPertinent = fields[i];
                }
            }
        } catch (Exception e) {
            System.out.println("getPlusPertinent: " + e);
        }

        if (fieldPlusPertinent == null) {
            return null;
        }

        return fieldPlusPertinent.getName();
    }

    public String getOuiNon() {
        double pOui = 0;
        double pNon = 0;

        for (Entry entry : data) {
            if (entry.getJouer() == 0) {
                pNon++;
            } else {
                pOui++;
            }
        }

        if (pOui > pNon) {
            return "Oui";
        }
        return "Non";
    }

    public void regenerateTree() {
        setChildren(new ArrayList<>());
        generateTree();
    }

    public void generateTree() {
        String plusPertinent = getPlusPertinent();
        if (plusPertinent != null) {
            setValue(plusPertinent);
            for (Integer value : getUniqueValues(plusPertinent)) {
                Branch branch = addChild(new Branch(String.valueOf(value)));
                Node child = branch.setChild(new Node(value, plusPertinent, data));
                child.generateTree();
            }
        } else {
            setValue(getOuiNon());
        }
    }

    public String getStringValue(Integer value, String att) {
        String stringValue = null;

        switch (att) {
            case "ciel":
                switch (value) {
                    case 0:
                        stringValue = "soleil";
                        break;
                    case 1:
                        stringValue = "couvert";
                        break;
                    case 2:
                        stringValue = "pluie";
                        break;
                }
                break;
            case "temperature":
                switch (value) {
                    case 0:
                        stringValue = "chaud";
                        break;
                    case 1:
                        stringValue = "doux";
                        break;
                    case 2:
                        stringValue = "froid";
                        break;
                }
                break;
            case "humidite":
                switch (value) {
                    case 0:
                        stringValue = "elevee";
                        break;
                    case 1:
                        stringValue = "normale";
                        break;
                }
                break;
            case "vent":
                switch (value) {
                    case 0:
                        stringValue = "faible";
                        break;
                    case 1:
                        stringValue = "fort";
                        break;
                }
                break;
        }

        return stringValue;
    }

    public void print() {
        print("#", true, "");
    }

    private void print(String prefix, boolean isTail, String branchValue) {
        boolean ouiNon = value.toLowerCase().equals("oui") || value.toLowerCase().equals("non");
        String txt = prefix;
        txt += branchValue != "" ? isTail ? "└── " : "├── " : " ── ";
        txt += branchValue != "" ? BLUE + branchValue + RESET + " => " : BLUE;
        txt += ouiNon ? GREEN : RED;
        txt += value.toUpperCase() + RESET;
        System.out.println(txt);
        txt = prefix;
        txt += isTail ? "     " : "│    ";
        for (int i = 0; i < children.size() - 1; i++) {
            branchValue = getStringValue(Integer.valueOf(children.get(i).getValue()), getValue());
            branchValue += " (" + children.get(i).getValue() + ")";
            children.get(i).getChild().print(txt, false, branchValue.toUpperCase());
        }
        if (children.size() > 0) {
            branchValue = getStringValue(Integer.valueOf(children.get(children.size() - 1).getValue()), getValue());
            branchValue += " (" + children.get(children.size() - 1).getValue() + ")";
            children.get(children.size() - 1).getChild().print(txt, true, branchValue.toUpperCase());
        }
    }

    public void decide() {
        Scanner input = new Scanner(System.in);
        System.out.println(getValue());
        if (getChildren().size() > 0) {
            for (Branch child : getChildren()) {
                System.out.print(getStringValue(Integer.valueOf(child.getValue()), getValue()));
                System.out.print("(" + child.getValue() + ") ");
            }
            System.out.println("");
            int i = input.nextInt();
            for (Branch child : getChildren()) {
                if (Integer.valueOf(child.getValue()).equals(i)) {
                    child.getChild().decide();
                }
            }
        }
    }

    public void decide(Entry entry, Node root) {
        if (getChildren().size() == 0) {
            System.out.println(getValue());
            acceptOrRefuse(entry, root);
        } else {
            Field field = null;
            try {
                boolean trouve = false;
                field = Entry.class.getField(getValue());
                for (Branch child : getChildren()) {
                    if (Integer.valueOf(child.getValue()).equals(field.get(entry))) {
                        child.getChild().decide(entry, root);
                        trouve = true;
                    }
                }
                if (!trouve) {
                    System.out.println("Nouveau paramètre! " + field.get(entry));
                    root.addEntry(entry.clone(0));
                    root.addEntry(entry.clone(1));
                    root.regenerateTree();
                    root.decide(entry, root);
                }
            } catch (Exception e) {
                System.out.println("decide: " + e);
            }
        }
    }

    public void acceptOrRefuse(Entry entry, Node root) {
        Scanner input = new Scanner(System.in);
        System.out.println("Acceptez-vous la décision? [o/n]");
        String choice = input.next();

        if (choice.equals("o")) {
            System.out.println("Choix accepté, génial!");
            if (getValue().toLowerCase().equals("oui")) {
                entry.setJouer(1);
            } else {
                entry.setJouer(0);
            }
            root.addEntry(entry);
        } else {
            System.out.println("Choix refusé, regénération");
            if (getValue().toLowerCase().equals("oui")) {
                entry.setJouer(0);
            } else {
                entry.setJouer(1);
            }
            root.addEntry(entry);
            root.regenerateTree();
        }
    }
}
