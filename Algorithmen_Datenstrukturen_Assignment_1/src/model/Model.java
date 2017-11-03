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
	public int frameWidth, frameHeight;
	public int startX, startY, endX, endY;
	public int morphX = 0, morphY = 0;
	public int[] srcPix, trgPix;
	public ArrayList<OriginalImage> originalImages = new ArrayList<OriginalImage>();
	public ArrayList<Integer> selectedIndexes = new ArrayList<Integer>();
	public final Object LOCK = new Object();
	public Point p1, p2;
	public Task TASK;
	public Matrix morphMatrix;
	public OriginalImage srcImg, bgImg;

	public Model(int width, int height) {
		this.frameWidth = width;
		this.frameHeight = height;
		this.srcPix = new int[width * height];
		this.trgPix = new int[width * height];
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
		OriginalImage img1 = originalImages.get(pic1);
		OriginalImage img2 = originalImages.get(pic2);

		int[] pix1 = img1.imgPix;
		int[] pix2 = img2.imgPix;

		for (int i = 0; i < frameWidth * frameHeight; i++) {
			trgPix[i] = colorShuffle(pix1[i], pix2[i], p);
		}

	}

	public void createHistogram() {
		Writer writer;
		try {
			OriginalImage img = originalImages.get(0);
			HashMap<String, Integer> histogram = new HashMap<String, Integer>();

			for (int i = 0; i < frameWidth * frameHeight; i++) {
				String key = Integer.toBinaryString(img.imgPix[i]);
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
	//
	// public void lens(int index1, int index2) {
	// OriginalImage img1 = originalImages.get(index1);
	// OriginalImage img2 = originalImages.get(index2);
	//
	// int[] pix1 = img1.imgPix;
	// int[] pix2 = img2.imgPix;
	//
	// for (int x = 0; x < frameWidth; x++) {
	// for (int y = 0; y < frameHeight; y++) {
	// final int IDX = y * frameWidth + x;
	// final int X_DIFF = p1.x - x;
	// final int Y_DIFF = p1.y - y;
	// final int VAL = (X_DIFF * X_DIFF + Y_DIFF * Y_DIFF) / 100;
	// final int MAX_VAL = VAL > 100 ? 100 : VAL;
	// trgPix[IDX] = colorShuffle(pix1[IDX], pix2[IDX], MAX_VAL);
	// }
	// }
	// }

	public void morph() {
		System.out.println("startX " + startX + ", endX " + endX + ", startY " + startY + ", endY " + endY);
		for (int x = 0; x < frameWidth; x++) {
			for (int y = 0; y < frameHeight; y++) {
				Vector trgVec = new Vector(x,y);
				Vector srcVec = Matrix.multiply(morphMatrix, trgVec);
				int srcIndex = srcVec.getY() * frameWidth + srcVec.getX();
				if (0<=srcVec.getY() && srcVec.getY()<frameHeight && 0<=srcVec.getX() && srcVec.getX()<frameWidth) {
					trgPix[y * frameWidth + x] = srcImg.imgPix[srcIndex];
				} else {
					trgPix[y * frameWidth + x] = bgImg.imgPix[y * frameWidth + x];
					//trgPix[y * frameWidth + x] = 0xffff;
				}
			}
		}
	}
	
	public void morph2() {
		System.out.println("startX " + startX + ", endX " + endX + ", startY " + startY + ", endY " + endY);
		for (int x = startX; x < endX; x++) {
			for (int y = startY; y < endY; y++) {
				Vector trgVec = new Vector(x,y);
				Vector srcVec = Matrix.multiply(morphMatrix, trgVec);
				int srcIndex = srcVec.getY() * frameWidth + srcVec.getX();
				trgPix[trgVec.getY() * frameWidth + trgVec.getX()] = srcPix[srcIndex];
			}
		}
	}

	public void insertInArray(int[] src, int[] trg) {
		for (int x = 0; x < frameWidth; x++) {
			for (int y = 0; y < frameHeight; y++) {
				trg[y * frameWidth + x] = src[y * frameWidth + x];
			}
		}
	}

	public void setStartAndEnd() {
		startX = p1.x < p2.x ? p1.x : p2.x;
		startY = p1.y < p2.y ? p1.y : p2.y;
		endX = p2.x > p1.x ? p2.x : p1.x;
		endY = p2.y > p1.y ? p2.y : p1.y;
		
	}
}
