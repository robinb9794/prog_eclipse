package controller;

import model.Model;
import view.GUI;

public class Controller extends Thread{
	private Model m_Mod;
	private GUI m_View;
	public Controller(){
		m_Mod = new Model(500,500);
		m_View = new GUI(m_Mod);
		
		new Thread(this).start();
	}
	
	public void run() {
		while (!m_Mod.isM_XClicked()) {
			try {
				synchronized (m_Mod.getLOCK()) {
					while (!(m_Mod.getM_selectedImages().size() > 0) && !m_Mod.isM_XClicked()) {
						m_View.m_pCenter.getGraphics().clearRect(0, 0, m_View.m_pCenter.getWidth(), m_View.m_pCenter.getHeight());
						System.out.println("waiting for images...");
						m_Mod.getLOCK().wait();
					}
				}
				if (!m_Mod.isM_XClicked()) {
					System.out.println("congrats, you've selected images! Now let's fade...");
					m_Mod.fade(m_View.m_imgSrc, m_View.m_pCenter, m_View.m_Img);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
