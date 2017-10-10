package model;

import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.image.PixelGrabber;
import java.util.ArrayList;

public class Model{
	private int m_Width, m_Height;
	private ArrayList<Image> m_selectedImages = new ArrayList<Image>();
	private boolean m_XClicked=false;
	private int[] m_Pix;
	private int m_imgWidth, m_imgHeight;
	
	public Model(int width, int height){
		this.m_Width=width;
		this.m_Height=height;
		this.m_imgWidth=width/4;
		this.m_imgHeight=height/4;
		this.m_Pix=new int[m_imgWidth*m_imgHeight];
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

	public ArrayList<Image> getM_selectedImages() {
		return m_selectedImages;
	}

	public void setM_selectedImages(ArrayList<Image> m_selectedImages) {
		this.m_selectedImages = m_selectedImages;
	}
	
	public boolean isM_XClicked() {
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
	      int width = img.getWidth(null);
	      int height = img.getHeight(null);
	      int[] pixel = new int[width * height];

	      PixelGrabber pg = new PixelGrabber(img, 0, 0, width, height, pixel, 0, width);
	      try {
	        pg.grabPixels();
	      } catch (InterruptedException e) {
	        throw new IllegalStateException("Error: Interrupted Waiting for Pixels");
	      }
	      if ((pg.getStatus() & ImageObserver.ABORT) != 0) {
	        throw new IllegalStateException("Error: Image Fetch Aborted");
	      }
	      return pixel;
	    }
}
