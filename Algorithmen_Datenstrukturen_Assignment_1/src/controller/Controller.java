package controller;

import model.Model;
import view.GUI;

public class Controller implements Runnable {
	Model m_Mod;
	GUI m_View;

	public Controller() {
		m_Mod = new Model(500, 500);
		m_View = new GUI(m_Mod);
	}

	public void start() {
		new Thread(this).start();
	}

	public void run() {
		while (!m_Mod.m_XClicked && m_View.m_choseImages) {
			try {
				synchronized (m_Mod.LOCK) {
					while(!m_Mod.m_XClicked){
						while (!(m_Mod.m_Indexes.size() > 1) && !m_Mod.m_XClicked) {
							System.out.println("waiting for at least two selected images...");
							m_View.m_Fade=false;
							m_View.m_Lens=false;
							m_Mod.LOCK.wait();
						}
						int counter = 0;
						int firstImgIndex, secondImgIndex;
						while (m_Mod.m_Indexes.size() > 1) {
							firstImgIndex = m_Mod.m_Indexes.get(counter % m_Mod.m_Indexes.size());
							secondImgIndex = m_Mod.m_Indexes.get((counter + 1) % m_Mod.m_Indexes.size());
							if (m_View.m_Fade && !m_View.m_Lens) {
								System.out.println("fading image " + firstImgIndex + " and " + secondImgIndex);
								for (int p = 0; p <= 100; p += 2) {
									m_Mod.shuffle(p, firstImgIndex, secondImgIndex);
									m_View.repaint();
									Thread.sleep(50);
								}
								++counter;
							} else if (!m_View.m_Fade && m_View.m_Lens) {
								firstImgIndex = m_Mod.m_Indexes.get(0);
								secondImgIndex = m_Mod.m_Indexes.get(1);
								m_Mod.lens(firstImgIndex, secondImgIndex);
								System.out.println("[x="+((int)m_Mod.m_lensPoint.getX())+",y="+((int)m_Mod.m_lensPoint.getY())+"]");
								m_View.repaint();
								m_Mod.LOCK.wait();
							}
						}
					}
					
				}

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
