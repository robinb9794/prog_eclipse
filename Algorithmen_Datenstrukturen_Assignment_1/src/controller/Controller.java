package controller;

import model.Model;
import view.Crossfade;

public class Controller implements Runnable {
	Model m_Mod;
	Crossfade m_View;

	public Controller() {
		m_Mod = new Model(500, 500);
		m_View = new Crossfade(m_Mod);	
	}
	
	public void start(){
		new Thread(this).start();
	}

	public void run() {
		while (!m_Mod.m_XClicked) {
			try {
				synchronized (m_Mod.LOCK) {
					while (!(m_Mod.m_Indexes.size() > 1) && !m_Mod.m_XClicked) {
						m_View.clear();
						System.out.println("waiting for images...");
						m_Mod.LOCK.wait();
					}
				}
				if (!m_Mod.m_XClicked) {
					System.out.println("let's fade...");
					int counter = 0;
					int firstImgIndex, secondImgIndex;

					while (!m_Mod.m_XClicked && m_Mod.m_Indexes.size() > 1) {
							firstImgIndex = m_Mod.m_Indexes.get(counter%m_Mod.m_Indexes.size());
							secondImgIndex = m_Mod.m_Indexes.get((counter+1)%m_Mod.m_Indexes.size());
							System.out.println("fading image "+firstImgIndex+" and "+secondImgIndex);
							for(int p = 0; p<=100; p+=2){
								m_Mod.shuffle(p, firstImgIndex, secondImgIndex);
								m_View.repaint();
								Thread.sleep(50);
							}
							++counter;

					}

				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
