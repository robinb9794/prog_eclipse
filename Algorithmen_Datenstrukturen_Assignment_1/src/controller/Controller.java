package controller;

import model.Matrix;
import model.Model;
import view.GUI;

public class Controller implements Runnable {
	Model mod;
	GUI view;

	public Controller() {
		mod = new Model(500, 500);
		view = new GUI(mod);
	}

	public void start() {
		new Thread(this).start();
	}

	public void run() {
		while (!view.closedFrame && view.choseImages) {
			try {
				synchronized (mod.LOCK) {
					while(!view.closedFrame){
						while (!view.closedFrame && mod.TASK==null) {
							System.out.println("waiting for actions...");
							mod.TASK=null;
							view.running=false;
							mod.LOCK.wait();
							if(mod.selectedIndexes.size()>0){
								mod.insertInArray(mod.originalImages.get(mod.selectedIndexes.get(0)).imgPix, mod.trgPix);
							}
							view.repaint();
						}						
						view.running=true;	
						int counter = 0, srcImgIndex, bgImgIndex;
						while (mod.selectedIndexes.size() > 1 && mod.TASK!=null) {
							srcImgIndex = mod.selectedIndexes.get(counter % mod.selectedIndexes.size());
							bgImgIndex = mod.selectedIndexes.get((counter + 1) % mod.selectedIndexes.size());
							switch(mod.TASK){
							case FADE:
								System.out.println("fading image " + srcImgIndex + " and " + bgImgIndex);
								for (int p = 0; p <= 100; p += 2) {
									mod.shuffle(p, srcImgIndex, bgImgIndex);
									view.repaint();
									Thread.sleep(50);
								}
								++counter;
								break;
//							case LENS:
//								System.out.println("lensing...");
//								firstImgIndex = mod.selectedIndexes.get(0);
//								secondImgIndex = mod.selectedIndexes.get(1);
//								mod.lens(firstImgIndex, secondImgIndex);
//								view.repaint();
//								mod.LOCK.wait();
//								break;
							case MOVE:
								while(mod.p1==null && mod.p2==null){
									System.out.println("waiting for two clicks...");
									view.waitingForClicks=true;
									mod.LOCK.wait();
									if(mod.p1!=null && mod.p2!=null){
										mod.setStartAndEnd();
										mod.srcImg = mod.originalImages.get(mod.selectedIndexes.get(0));
										mod.bgImg = mod.originalImages.get(mod.selectedIndexes.get(1));
										mod.insertInArray(mod.srcImg.imgPix, mod.srcPix);
										mod.insertInArray(mod.bgImg.imgPix, mod.trgPix);
									}																		
								}
								view.waitingForClicks=false;
								System.out.println("click to move");
								mod.LOCK.wait();
								if(mod.p1!=null && mod.p2!=null){
									System.out.println("moving...");
									mod.morphMatrix = Matrix.translate(mod.morphX, mod.morphY);
									mod.morph2();
									view.repaint();
								}								
								break;
							case ROTATE:
								break;
							case SCALE:
								break;
							case SHEAR:
								break;	
							case DRAW_LINES:
								break;
							case DRAW_CIRCLE:
								break;
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
