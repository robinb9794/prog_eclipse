package controller;

import model.Matrix;
import model.Model;
import model.Task;
import view.Circle;
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
					view.setMemImg();
					while(!mod.closedFrame){
						while (!mod.closedFrame && mod.TASK==null) {
							System.out.println("waiting for actions...");
							view.repaint();
							mod.LOCK.wait();
							if(mod.pixelSaved){
								mod.insertInArray(mod.bgImg.imgPix, mod.srcPix);
							}else if(mod.selectedIndexes.size()>0 && !mod.pixelSaved){
								mod.insertInArray(mod.originalImages.get(mod.selectedIndexes.get(0)).imgPix, mod.trgPix);
							}else if(mod.selectedIndexes.size()==0){
								int[] n = new int[mod.compWidth*mod.compHeight];
								mod.insertInArray(n, mod.trgPix);
							}
							view.repaint();
						}
						mod.stopClicked=false;
						mod.running=true;	
						int counter = 0, srcImgIndex, bgImgIndex;
						while (mod.selectedIndexes.size() >= 1 && mod.TASK!=null) {
							srcImgIndex = mod.selectedIndexes.get(counter % mod.selectedIndexes.size());
							bgImgIndex = mod.selectedIndexes.get((counter + 1) % mod.selectedIndexes.size());
							mod.srcImg = mod.originalImages.get(srcImgIndex);
							mod.bgImg = mod.originalImages.get(bgImgIndex);
							switch(mod.TASK){
							case FADE:
								if(mod.TASK==Task.FADE && !mod.stopClicked && mod.running){
									System.out.println("fading image " + srcImgIndex + " and " + bgImgIndex);
									for (int p = 0; p <= 100; p += 2) {
										mod.fade(p);
										view.repaint();
										Thread.sleep(50);
									}
									++counter;
								}								
								break;
							case LENS:
								System.out.println("lensing...");
								mod.srcImg=mod.originalImages.get(mod.selectedIndexes.get(0));
								mod.bgImg=mod.originalImages.get(mod.selectedIndexes.get(1));
								mod.lens();
								view.repaint();
								mod.running=false;
								mod.LOCK.wait();
								break;
							case TRANSLATE:
								while(mod.p1==null && mod.p2==null){
									System.out.println("waiting for two clicks...");
									mod.waitingForClicks=true;
									mod.LOCK.wait();
									if(mod.p1!=null && mod.p2!=null && !mod.stopClicked){
										mod.setStartAndEnd();
										mod.srcImg = mod.originalImages.get(mod.selectedIndexes.get(0));
										mod.bgImg = mod.originalImages.get(mod.selectedIndexes.get(1));
									}																		
								}
								mod.running=false;
								mod.waitingForClicks=false;
								System.out.println("click to move");
								mod.LOCK.wait();
								if(mod.p1!=null && mod.p2!=null && !mod.stopClicked){
									System.out.println("moving...");
									mod.morphMatrix = Matrix.translate(mod.translX, mod.translY);
									mod.morph();
									mod.translX+=5;
									mod.translY+=5;
									view.repaint();
								}								
								break;
							case ROTATE:
								while(mod.p1==null && mod.p2==null ){
									System.out.println("waiting for two clicks...");
									mod.waitingForClicks=true;
									mod.LOCK.wait();
									if(mod.p1!=null && mod.p2!=null && !mod.stopClicked){
										mod.setStartAndEnd();
										mod.srcImg = mod.originalImages.get(mod.selectedIndexes.get(0));
										mod.bgImg = mod.originalImages.get(mod.selectedIndexes.get(1));
									}																		
								}
								mod.running=false;
								mod.waitingForClicks=false;
								System.out.println("click to rotate");
								mod.LOCK.wait();
								if(mod.p1!=null && mod.p2!=null && !mod.stopClicked){
									System.out.println("rotating...");
									Matrix translMa = Matrix.translate(-(mod.startX+((mod.endX-mod.startX)/2)),-(mod.startY+((mod.endY-mod.startY)/2)));									
									Matrix rotation = Matrix.rotate(mod.rotateA);
									Matrix translF = Matrix.translate(mod.startX+((mod.endX-mod.startX)/2),mod.startY+((mod.endY-mod.startY)/2));
									Matrix multi = Matrix.multiply(rotation, translMa);
									mod.morphMatrix = Matrix.multiply(translF,multi);									
									mod.morph();
									mod.rotateA+=5;
									view.repaint();
								}	
								break;
							case SCALE:
								while(mod.p1==null && mod.p2==null){
									System.out.println("waiting for two clicks...");
									mod.waitingForClicks=true;
									mod.LOCK.wait();
									if(mod.p1!=null && mod.p2!=null && !mod.stopClicked){
										mod.setStartAndEnd();
										mod.srcImg = mod.originalImages.get(mod.selectedIndexes.get(0));
										mod.bgImg = mod.originalImages.get(mod.selectedIndexes.get(1));
									}																		
								}
								mod.running=false;
								mod.waitingForClicks=false;
								System.out.println("click to scale");
								mod.LOCK.wait();
								if(mod.p1!=null && mod.p2!=null && !mod.stopClicked){
									System.out.println("scaling...");
									Matrix translMa = Matrix.translate(-(mod.startX+((mod.endX-mod.startX)/2)),-(mod.startY+((mod.endY-mod.startY)/2)));									
									Matrix scale = Matrix.scale(mod.scaleF);
									Matrix translF = Matrix.translate(mod.startX+((mod.endX-mod.startX)/2),mod.startY+((mod.endY-mod.startY)/2));
									Matrix multi = Matrix.multiply(scale, translMa);
									mod.morphMatrix = Matrix.multiply(translF,multi);	
									mod.morph();
									mod.scaleF+=0.3;
									view.repaint();
								}	
								break;
							case SHEARX:
								while(mod.p1==null && mod.p2==null){
									System.out.println("waiting for two clicks...");
									mod.waitingForClicks=true;
									mod.LOCK.wait();
									if(mod.p1!=null && mod.p2!=null && !mod.stopClicked){
										mod.setStartAndEnd();
										mod.srcImg = mod.originalImages.get(mod.selectedIndexes.get(0));
										mod.bgImg = mod.originalImages.get(mod.selectedIndexes.get(1));
									}																		
								}
								mod.running=false;
								mod.waitingForClicks=false;
								System.out.println("click to shear X");
								mod.LOCK.wait();
								if(mod.p1!=null && mod.p2!=null && !mod.stopClicked){
									System.out.println("shearing...");
									Matrix translMa = Matrix.translate(-mod.startX,-mod.startY);									
									Matrix shear = Matrix.shearX(mod.shearX);
									Matrix translF = Matrix.translate(mod.startX,mod.startY);
									Matrix multi = Matrix.multiply(shear, translMa);
									mod.morphMatrix = Matrix.multiply(translF,multi);
									mod.morph();
									mod.shearX+=0.1;
									view.repaint();
								}	
								break;	
							case SHEARY:
								while(mod.p1==null && mod.p2==null){
									System.out.println("waiting for two clicks...");
									mod.waitingForClicks=true;
									mod.LOCK.wait();
									if(mod.p1!=null && mod.p2!=null && !mod.stopClicked){
										mod.setStartAndEnd();
										mod.srcImg = mod.originalImages.get(mod.selectedIndexes.get(0));
										mod.bgImg = mod.originalImages.get(mod.selectedIndexes.get(1));
									}																		
								}
								mod.running=false;
								mod.waitingForClicks=false;
								System.out.println("click to shear Y");
								mod.LOCK.wait();
								if(mod.p1!=null && mod.p2!=null && !mod.stopClicked){
									System.out.println("shearing...");
									Matrix translMa = Matrix.translate(-mod.startX,-mod.startY);									
									Matrix rotation = Matrix.shearY(mod.shearY);
									Matrix translF = Matrix.translate(mod.startX,mod.startY);
									Matrix multi = Matrix.multiply(rotation, translMa);
									mod.morphMatrix = Matrix.multiply(translF,multi);
									mod.morph();
									mod.shearY+=0.1;
									view.repaint();
								}	
								break;	
							case DRAW_LINES:
								while(mod.p1==null && mod.p2==null && !mod.stopClicked){
									System.out.println("waiting for two clicks...");
									mod.waitingForClicks=true;
									mod.LOCK.wait();																	
								}
								if(mod.TASK==Task.DRAW_LINES && mod.p1!=null && mod.p2!=null && !mod.stopClicked){
									System.out.println("drawing line...");
									view.comp.drawLine(view.comp.getGraphics(), mod.p1.x, mod.p1.y, mod.p2.x, mod.p2.y);
									mod.p1=null;
									mod.p2=null;
								}
							case DRAW_CIRCLES:
								while(mod.p1==null && !mod.stopClicked){
									System.out.println("waiting for one click...");
									mod.waitingForClicks=true;
									mod.LOCK.wait();																	
								}
								if(mod.TASK==Task.DRAW_CIRCLES && mod.p1!=null && !mod.stopClicked){
									System.out.println("drawing circle...");
									view.c = new Circle(mod.p1.x,mod.p1.y,50,view.comp);
									if(view.colorFade==null){
										view.createColorFade();
									}else{
										view.colorFade.setVisible(true);
									}
									view.c.draw();
									mod.p1=null;
									mod.waitingForClicks=false;
									mod.firstClicked=false;
								}
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
