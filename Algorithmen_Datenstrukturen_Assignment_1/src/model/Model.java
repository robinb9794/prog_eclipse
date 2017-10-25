package model;

import java.awt.Point;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Model {
	public int m_Width, m_Height;
	public int[] m_Pix;
	public ArrayList<OriginalImage> m_originalImages = new ArrayList<OriginalImage>();
	public ArrayList<Integer> m_Indexes = new ArrayList<Integer>();
	public boolean m_XClicked = false;
	public final Object LOCK = new Object();
	public Point m_lensPoint, m_morphPoint_1, m_morphPoint_2;

	public Model(int width, int height) {
		this.m_Width = width;
		this.m_Height = height;
		this.m_Pix = new int[width * height];
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

	public void shuffle(int p, int pic1, int pic2) {
		OriginalImage img1 = m_originalImages.get(pic1);
		OriginalImage img2 = m_originalImages.get(pic2);

		int[] pix1 = img1.getGrappedPixels();
		int[] pix2 = img2.getGrappedPixels();

		for (int i = 0; i < m_Width * m_Height; i++) {
			m_Pix[i] = colorShuffle(pix1[i], pix2[i], p);
		}

	}

	public void createHistogram() {
		Writer writer;
		try {
			OriginalImage img = m_originalImages.get(0);
			HashMap<String, Integer> histogram = new HashMap<String, Integer>();

			for (int i = 0; i < m_Width * m_Height; i++) {
				String key = Integer.toBinaryString(img.getGrappedPixels()[i]);
				int value = 1;
				if (!histogram.containsKey(key)) {
					histogram.put(key, value);
				} else {
					value = histogram.get(key);
					histogram.replace(key, value + 1);
				}
			}

			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("histogram.txt"), "UTF-8"));
			for (Map.Entry e : histogram.entrySet()) {
				writer.write("0b" + e.getKey() + "\t:\t" + e.getValue() + "\n");
			}
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void lens(int pic1, int pic2) {
		OriginalImage img1 = m_originalImages.get(pic1);
		OriginalImage img2 = m_originalImages.get(pic2);

		int[] pix1 = img1.getGrappedPixels();
		int[] pix2 = img2.getGrappedPixels();

		for (int x = 0; x < m_Width; x++) {
			for (int y = 0; y < m_Height; y++) {
				final int IDX = y * m_Width + x;
				final int X_DIFF = m_lensPoint.x - x;
				final int Y_DIFF = m_lensPoint.y - y;
				final int VAL = (X_DIFF * X_DIFF + Y_DIFF * Y_DIFF) / 100;
				final int MAX_VAL = VAL > 100 ? 100 : VAL;
				m_Pix[IDX] = colorShuffle(pix1[IDX], pix2[IDX], MAX_VAL);
			}
		}
	}
	
	public void morph(int n){
		
	}
}
