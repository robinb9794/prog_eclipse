package model;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.File;

public class OriginalImage {
	public PixelGrabber grabber;
	public int[] imgPix;
	public File file;
	public Image img;

	public OriginalImage(BufferedImage img) {
		this.img = img;
	}

	public void setPixels(int width, int height) {
		try {
			this.img=scale(width,height);
			this.imgPix = new int[width * height];
			this.grabber = new PixelGrabber(this.img, 0, 0, width, height, this.imgPix, 0, width);
			this.grabber.grabPixels();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private BufferedImage scale(int width, int height) {
		Image tmp = this.img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = resized.createGraphics();
		g2d.drawImage(tmp, 0, 0, null);
		g2d.dispose();
		return resized;
	}
}
