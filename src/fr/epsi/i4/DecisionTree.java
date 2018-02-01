package fr.epsi.i4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;

import fr.epsi.i4.model.Entry;
import fr.epsi.i4.model.Node;

public class DecisionTree {

	private static final String dataFileName = "data.txt";

	private static Node tree;

	private static Config config;

	public static void init(Config config) {
		DecisionTree.config = config;
		initDirectory();
		initData();
	}

	private static void initData() {
		tree = new Node(config);
		readDataFromFile();
		tree.generateTree();
	}

	private static void initDirectory() {
		File dir = new File(config.getDirectory());
		try {
			Files.createDirectories(dir.toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void print() {
		tree.print();
	}

	public static void regenerateTree() {
		tree.regenerateTree();
	}

	public static void save() {
		writeDataToFile();
	}

	private static String getFilePath(String fileName) {
		return config.getDirectory() + "/" + fileName;
	}

	public static void readDataFromFile() {
		try {
			File f = new File(getFilePath(dataFileName));
			BufferedReader bufferedReader = new BufferedReader(new FileReader(f));
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				tree.getEntries().add(Entry.fromText(line));
			}
		} catch (IOException e) {
			System.out.println("Aucune données");
		}
	}

	public static void writeDataToFile() {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(getFilePath(dataFileName), "UTF-8");
			for (Entry entry : tree.getEntries()) {
				writer.println(entry.toText());
			}
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}

	public static String decide(String... params) {
		Entry entry = entryFromParams(params);
		int decision = tree.decide(entry);
		return config.getDecisions().get(decision);
	}

	public static void addData(String... params) {
		if (params.length == config.getAttributs().size() + 1) {
			Entry entry = entryFromParams(params);
			tree.addEntry(entry);
		}
	}

	private static Entry entryFromParams(String... params) {
		Integer[] values;
		if (params.length == config.getAttributs().size()) {
			values = new Integer[params.length + 1];
		} else {
			values = new Integer[params.length];
		}
		for (int i = 0; i < params.length; i++) {
			if (params.length > config.getAttributs().size() && i == params.length - 1) {
				values[i] = config.getDecisionByName(params[i]);
			} else {
				values[i] = config.getIndexOfValue(i, params[i]);
			}
		}
		return new Entry(values);
	}
}