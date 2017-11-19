package controller;

import model.Model;
import view.GUI;

public class Controller implements Runnable {
	Model mod;
	GUI view;

	public Controller() {
		mod = new Model(600, 600);
		view = new GUI(mod);
	}

	public void start() {
		new Thread(this).start();
	}

	public void run() {
		while (!mod.closedFrame && mod.choseImages) {
			try {
				synchronized (mod.LOCK) {
					mod.setCompValues(view.comp.getWidth(), view.comp.getHeight());
					mod.grapPixels();
					mod.setMemImg();
					while (!mod.closedFrame) {
						System.out.println("waiting to create histogram...");
						view.repaint();
						mod.LOCK.wait();
						if(mod.selectedIndexes.size() > 0) {
							mod.insertInArray(mod.originalImages.get(mod.selectedIndexes.get(0)).imgPix,
									mod.trgPix);
						} else{
							int[] n = new int[mod.compWidth * mod.compHeight];
							mod.insertInArray(n, mod.trgPix);
						}
						view.repaint();
					}
				}

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
