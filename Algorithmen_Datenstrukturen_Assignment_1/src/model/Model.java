package model;

import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.util.ArrayList;

public class Model {
	private int m_Width, m_Height;
	private ArrayList<Image> m_selectedImages = new ArrayList<Image>();
	private ArrayList<Image> m_originalImages = new ArrayList<Image>();
	private ArrayList<Integer> m_Indexes = new ArrayList<Integer>();
	private boolean m_XClicked = false;
	private final Object LOCK = new Object();

	public Model(int width, int height) {
		this.m_Width = width;
		this.m_Height = height;
	}

	public int getM_Width() {
		return m_Width;
	}

	public void setM_Width(int m_Width) {
		this.m_Width = m_Width;
	}

	public int getM_Height() {
		return m_Height;
	}

	public void setM_Height(int m_Height) {
		this.m_Height = m_Height;
	}

	public synchronized ArrayList<Image> getM_selectedImages() {
		return m_selectedImages;
	}

	public void setM_selectedImages(ArrayList<Image> m_selectedImages) {
		this.m_selectedImages = m_selectedImages;
	}

	public synchronized boolean isM_XClicked() {
		return m_XClicked;
	}

	public void setM_XClicked(boolean m_xClicked) {
		m_XClicked = m_xClicked;
	}

	public int[] convertToPixels(Image img) {
		final int W = img.getWidth(null);
		final int H = img.getHeight(null);
		final int[] P = new int[W * H];

		PixelGrabber pg = new PixelGrabber(img, 0, 0, W, H, P, 0, W);
		try {
			pg.grabPixels();
		} catch (InterruptedException e) {
			throw new IllegalStateException("Error: Interrupted Waiting for Pixels");
		}
		if ((pg.getStatus() & ImageObserver.ABORT) != 0) {
			throw new IllegalStateException("Error: Image Fetch Aborted");
		}
		return P;
	}

	public Object getLOCK() {
		return LOCK;
	}

	public MemoryImageSource fade(int imgIndex, int shift) {
		try {
			int[] imgPixel = convertToPixels(m_originalImages.get(imgIndex));
			int imgWidth = m_originalImages.get(imgIndex).getWidth(null);
			int imgHeight = m_originalImages.get(imgIndex).getHeight(null);
				
			for (int j = 0; j < imgWidth; j++) {
				for (int k = 0; k < imgHeight; k++) {
					int index = j + imgWidth * k;
					int pixel = imgPixel[index];
					int comparison = shift << 24;
					pixel = pixel ^ comparison;
					imgPixel[index] = pixel;
				}
			}

			System.out.println("alpha for image " + imgIndex + " is now " + shift);

			return new MemoryImageSource(imgWidth, imgHeight, imgPixel, 0, imgWidth);
			
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<Image> getM_originalImages() {
		return m_originalImages;
	}

	public void setM_originalImages(ArrayList<Image> m_originalImages) {
		this.m_originalImages = m_originalImages;
	}

	public ArrayList<Integer> getM_Indexes() {
		return m_Indexes;
	}

	public void setM_Indexes(ArrayList<Integer> m_Indexes) {
		this.m_Indexes = m_Indexes;
	}
}
