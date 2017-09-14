package uebung07.aufgabe02;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class UniqueNumberOperator {

	enum mode {
		readFile, random, writeFile
	}

	int[] numbers = null;
	int anzahlElemente = 0;
	mode modus = null;
	File file = new File(this.getClass().getResource("").getPath()
			.replace("bin", "src")
			+ "/testData.txt");
	BufferedReader in = null;
	FileWriter fw = null;
	BufferedWriter bw = null;
	String zeile = null;

	UniqueNumberOperator(int anzahlElemente, mode modus) throws IOException {

//		System.out.println(file.getAbsolutePath());
		this.anzahlElemente = anzahlElemente+1;
		this.modus = modus;
		numbers = new int[anzahlElemente * 2];
		for (int i = 0; i < numbers.length; ++i) {
			numbers[i] = i;
		}

		if (modus == mode.writeFile) {
			if (!file.exists()) {
				file.createNewFile();
			}
			fw = new FileWriter(file.getAbsoluteFile());
			bw = new BufferedWriter(fw);

			for (int i = 0; i < anzahlElemente; ++i) {
				int rand = (int) (Math.random() * (anzahlElemente*2));
				while (numbers[rand] == -1) {
					rand = (int) (Math.random() * (anzahlElemente*2));
				}
				int temp = numbers[rand];
				numbers[rand] = -1;
				bw.write(temp + "\n");
			}
			bw.close();
		}

		if (modus == mode.readFile || modus == mode.writeFile)
			in = new BufferedReader(new FileReader(file));
	}

	int getUniqueInt() throws IOException {
		if (modus == mode.random) {
			int rand = (int) (Math.random() * (anzahlElemente*2));
			while (numbers[rand] == -1) {
				rand = (int) (Math.random() * (anzahlElemente*2));
			}
			int temp = numbers[rand];
			numbers[rand] = -1;
			return temp;
		} else {
			zeile = in.readLine();

			if (zeile != null) {
				return Integer.parseInt(zeile);
			} else
				return -1;
		}
	}
}
