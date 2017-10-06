package model;

import java.awt.Image;
import java.util.ArrayList;

public class Model {
	private int m_Width, m_Height;
	private Image m_clickedImage;
	private ArrayList<Image> m_clickedImages;
	
	public Model(int width, int height){
		this.m_Width=width;
		this.m_Height=height;
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

	public Image getM_clickedImage() {
		return m_clickedImage;
	}

	public void setM_clickedImage(Image m_clickedImage) {
		this.m_clickedImage = m_clickedImage;
	}
}
