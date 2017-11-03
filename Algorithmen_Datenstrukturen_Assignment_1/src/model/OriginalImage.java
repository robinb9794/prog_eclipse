package model;

import java.awt.Image;
import java.awt.image.PixelGrabber;
import java.io.File;
import javax.imageio.ImageIO;

public class OriginalImage {
	public PixelGrabber grabber;
	public int[] imgPix;
	public Image img;
	
	public OriginalImage(File file, int width, int height){		
		try {
			this.img = ImageIO.read(file).getScaledInstance(width, height, Image.SCALE_SMOOTH);
			this.imgPix = new int[width*height];
			this.grabber = new PixelGrabber(this.img,0,0,width,height,imgPix,0,width);
			this.grabber.grabPixels();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
