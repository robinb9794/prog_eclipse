package model;

import java.util.ArrayList;

public class Model {
	public int m_Width, m_Height;
	public int[] m_Pix;
	public ArrayList<ImageToFade> m_originalImages = new ArrayList<ImageToFade>();
	public ArrayList<Integer> m_Indexes = new ArrayList<Integer>();
	public boolean m_XClicked = false;
	public final Object LOCK = new Object();

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
		ImageToFade img1 = m_originalImages.get(pic1);
		ImageToFade img2 = m_originalImages.get(pic2);

		int[] pix1 = img1.getGrappedPixels();
		int[] pix2 = img2.getGrappedPixels();

		for (int i = 0; i < m_Width * m_Height; i++) {
			m_Pix[i] = colorShuffle(pix1[i], pix2[i], p);
		}

	}
}
