package model;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Model {
	private int m_Width, m_Height;
	private ArrayList<Image> m_selectedImages = new ArrayList<Image>();
	private boolean m_XClicked = false;
	private int[] m_Pix;
	private int m_imgWidth, m_imgHeight;
	private final Object LOCK = new Object();
	private boolean m_openedHistogram=false;
	private JLabel[] m_histoValues = new JLabel[4*4*4];

	public Model(int width, int height) {
		this.m_Width = width;
		this.m_Height = height;
		this.m_imgWidth = width / 4;
		this.m_imgHeight = height / 4;
		this.m_Pix = new int[m_imgWidth * m_imgHeight];
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

	public int[] getM_Pix() {
		return m_Pix;
	}

	public void setM_Pix(int[] m_Pix) {
		this.m_Pix = m_Pix;
	}

	public int getM_imgWidth() {
		return m_imgWidth;
	}

	public void setM_imgWidth(int m_imgWidth) {
		this.m_imgWidth = m_imgWidth;
	}

	public int getM_imgHeight() {
		return m_imgHeight;
	}

	public void setM_imgHeight(int m_imgHeight) {
		this.m_imgHeight = m_imgHeight;
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

	public void fade(MemoryImageSource imgSrc, JPanel pCenter, Image img) {
		try {
			int imgIndex = 0;

			while (getM_selectedImages().size() > 0 && !isM_XClicked()) {
				int shift = 255;
				imgIndex = ++imgIndex % getM_selectedImages().size();
				System.out.println("fading image " + imgIndex);
				int[] imgPixel = convertToPixels(m_selectedImages.get(imgIndex));

				while (shift >= 100 && getM_selectedImages().size() > 0 && !isM_XClicked()) {
					for (int j = 0; j < getM_imgWidth(); j++) {
						for (int k = 0; k < getM_imgHeight(); k++) {
							int index = j + getM_imgWidth() * k;
							int pixel = imgPixel[index];
							int comparison = shift << 24;
							pixel = pixel ^ comparison;
							getM_Pix()[index] = pixel;
						}
					}

					imgSrc.newPixels();
					if (pCenter.getGraphics() != null) {
						pCenter.getGraphics().drawImage(img, 0, 0, pCenter.getWidth(), pCenter.getHeight(), null);
					}

					shift -= 2;
					System.out.println("alpha for image "+imgIndex+" is now " + shift);

					Thread.sleep(50);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean isM_openedHistogram() {
		return m_openedHistogram;
	}

	public void setM_openedHistogram(boolean m_openedHistogram) {
		this.m_openedHistogram = m_openedHistogram;
	}

	public JLabel[] getM_histoValues() {
		return m_histoValues;
	}

	public void setM_histoValues(JLabel[] m_histoValues) {
		this.m_histoValues = m_histoValues;
	}
}
