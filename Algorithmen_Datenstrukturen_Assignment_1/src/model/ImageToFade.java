package model;

import java.awt.Image;
import java.awt.image.PixelGrabber;
import java.io.File;

import javax.imageio.ImageIO;

public class ImageToFade {
	private PixelGrabber m_Grabber;
	private int[] m_Pix;
	private Image m_Img;
	
	public ImageToFade(File file, int width, int height){		
		try {
			this.m_Img = ImageIO.read(file).getScaledInstance(width, height, Image.SCALE_SMOOTH);
			this.m_Pix = new int[width*height];
			this.m_Grabber = new PixelGrabber(this.m_Img,0,0,width,height,m_Pix,0,width);
			this.m_Grabber.grabPixels();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int[] getGrappedPixels(){
		return m_Pix;
	}
}
