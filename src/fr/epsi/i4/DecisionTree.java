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
		initAttributs();
		initData();
	}

	private static void initAttributs() {

	}

	private static void initData() {
		tree = new Node();
		readDataFromFile();
	}

	private static void initDirectory() {
		File dir = new File(config.getDirectory());
		try {
			Files.createDirectories(dir.toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void addValueToData(String attributName, String value) {

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
				tree.getData().add(Entry.fromText(line));
			}
		} catch (IOException e) {
			System.out.println("Aucune données");
		}
	}

	public static void writeDataToFile() {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(getFilePath(dataFileName), "UTF-8");
			for (Entry entry : tree.getData()) {
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

	public static String decide(Entry entry) {
		return tree.decide(entry);
	}
}