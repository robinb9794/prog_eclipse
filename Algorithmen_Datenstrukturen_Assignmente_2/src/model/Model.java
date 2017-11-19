package model;

import java.awt.image.MemoryImageSource;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Model {
	public int frameWidth, frameHeight;
	public int compWidth, compHeight;
	public int[] srcPix, trgPix;
	public HashSet<Integer> valRed = new HashSet<Integer>();
	public HashSet<Integer> valGreen = new HashSet<Integer>();
	public HashSet<Integer> valBlue = new HashSet<Integer>();
	public int approxValue=50;
	public ArrayList<OriginalImage> originalImages = new ArrayList<OriginalImage>();
	public ArrayList<Integer> selectedIndexes = new ArrayList<Integer>();
	public final Object LOCK = new Object();
	public OriginalImage srcImg, bgImg;
	public boolean closedFrame, choseImages;
	public MemoryImageSource srcMemImg, trgMemImg;

	public Model(int width, int height) {
		this.frameWidth = width;
		this.frameHeight = height;

	}

	public void createHistogram() {
		Writer writer;
		try {
			long start=System.currentTimeMillis();
			OriginalImage img = originalImages.get(0);
			HashMap<String, Integer> histogram = new HashMap<String, Integer>();

			for (int i = 0; i < compWidth * compHeight; i++) {
				String key = binaryToDec(Integer.toBinaryString(img.imgPix[i]));
				int value = 1;
				if (!histogram.containsKey(key)) {
					histogram.put(key, value);
				} else {
					value = histogram.get(key);
					histogram.replace(key, value + 1);
				}
			}

			writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream("histogram_" + selectedIndexes.get(0) + ".txt"), "UTF-8"));
			for (Map.Entry e : histogram.entrySet()) {
				writer.write(e.getKey() + " \t:\t" + e.getValue() + "\n");
			}
			writer.close();
			System.out.println("created histogram in "+(System.currentTimeMillis()-start)+"ms");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String binaryToDec(String bin) {
		String b = "", g = "", r = "";
		for (int i = 0; i < 8; i++) {
			r = r + bin.charAt(8 + i);
			g = g + bin.charAt(8 + i + 8);
			b = b + bin.charAt(8 + i + 2 * 8);
		}
		return new Integer(Integer.parseInt(r, 2)).toString() + " " + new Integer(Integer.parseInt(g, 2)).toString()
				+ " " + new Integer(Integer.parseInt(b, 2)).toString();
	}

	public void insertInArray(int[] src, int[] trg) {
		for (int x = 0; x < compWidth; x++) {
			for (int y = 0; y < compHeight; y++) {
				trg[y * compWidth + x] = src[y * compWidth + x];
			}
		}
	}

	public void setCompValues(int width, int height) {
		compWidth = width;
		compHeight = height;
		this.srcPix = new int[compWidth * compHeight];
		this.trgPix = new int[compWidth * compHeight];
		System.out.println(compWidth + "  " + compHeight);
	}

	public void grapPixels() {
		for (OriginalImage oImg : originalImages) {
			oImg.setPixels(compWidth, compHeight);
		}
	}

	public void setMemImg() {
		srcMemImg = new MemoryImageSource(compWidth, compHeight, srcPix, 0, compWidth);
		srcMemImg.setAnimated(true);
		trgMemImg = new MemoryImageSource(compWidth, compHeight, trgPix, 0, compWidth);
		trgMemImg.setAnimated(true);
	}

	public boolean checkHisto() {
		File f = new File("histogram_" + selectedIndexes.get(0) + ".txt");
		return f.exists() && !f.isDirectory() ? true : false;
	}

	private int singleShuffle(int part1, int part2, int p) {
		return part1 + (part2 - part1) * p / 100;
	}

	private int colorShuffle(int pix1, int pix2, int p) {
		int red = singleShuffle((pix1 >> 16) & 255, (pix2 >> 16) & 255, p);
		int green = singleShuffle((pix1 >> 8) & 255, (pix2 >> 8) & 255, p);
		int blue = singleShuffle((pix1) & 255, (pix2) & 255, p);
		return (255 << 24) | (red << 16) | (green << 8) | blue;
	}

	public void prepareApprox() {
		try {
			BufferedReader reader = new BufferedReader(
					new FileReader("./histogram_" + selectedIndexes.get(0) + ".txt"));
			String line;
			long start = System.currentTimeMillis();
			while ((line = reader.readLine()) != null) {
				String[] values = line.split(" ");
				valRed.add(Integer.parseInt(values[0]));
				valGreen.add(Integer.parseInt(values[1]));
				valBlue.add(Integer.parseInt(values[2]));
			}
			sort(toArray(valRed), 0, valRed.size() - 1);
			sort(toArray(valGreen), 0, valGreen.size() - 1);
			sort(toArray(valBlue), 0, valBlue.size() - 1);
			System.out.println("RGB values sorted in "+(System.currentTimeMillis()-start)+"ms");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void sort(int[] values, int left, int right) {
		if (left < right) {
			final int MIDDLE = (left + right) / 2;
			sort(values, left, MIDDLE);
			sort(values, MIDDLE + 1, right);

			int[] tmp = new int[right - left + 1];

			for (int i = left; i <= MIDDLE; ++i) {
				tmp[i - left] = values[i];
			}
			for (int i = MIDDLE + 1; i <= right; ++i) {
				tmp[tmp.length - i + MIDDLE] = values[i];
			}

			int l = 0;
			int r = tmp.length - 1;
			for (int i = left; i <= right; ++i) {
				values[i] = tmp[l] <= tmp[r] ? tmp[l++] : tmp[r--];
			}
		}
	}

	private int[] toArray(HashSet<Integer> set) {
		int[] values = new int[set.size()];
		int i = 0;
		for (Integer v : set) {
			values[i++] = v;
		}
		return values;
	}

}
