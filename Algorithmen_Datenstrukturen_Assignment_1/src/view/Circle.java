package view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.MemoryImageSource;
import javax.swing.JComponent;

public class Circle{
	int[] pix;
	MemoryImageSource imgSrc;
	Image img;
	int x0,y0;
	int r,w,h;
	int[] col1 = new int[3];
	int[] col2 = new int[3];
	JComponent comp;
	
	public Circle(int x0, int y0, int r, JComponent comp){
		this.x0=x0;
		this.y0=y0;
		this.r=r;
		this.w=2*r;
		this.h=2*r;
		this.comp=comp;
		this.pix=new int[w*h];
		this.imgSrc = new MemoryImageSource(w, h, pix, 0, w);
		this.imgSrc.setAnimated(true);
		img=comp.createImage(imgSrc);
	}
	
	public void reRun(){
		for (int i = 0; i < r*2; ++i) {
			final int P = 100 * i / w;
			final int COL = 0xff000000 | compColor(col1[0], col2[0], P) << 16 | compColor(col1[1], col2[1], P) << 8
					| compColor(col1[2], col2[2], P);
			for (int j = 0; j < h; ++j) {
				pix[i + w * j] = COL;
			}
		}
		imgSrc.newPixels();
	}
	
	private int compColor(int x1, int x2, int p) {
		return x1 + (x2 - x1) * p / 100;
	}
	
	public void draw() {
		int y = 0;
		int x = r;
		int F = -r;
		int dy = 1;
		int dyx = -2 * r + 3;
		while (y <= x) {
			setPixel(comp.getGraphics(), x0, y0, x, y);
			++y;
			dy += 2;
			dyx += 2;
			if (F > 0) {
				F += dyx;
				--x;
				dyx += 2;
			} else {
				F += dy;
			}
		}
	}

	public void setPixel(Graphics g, int x0, int y0, int x, int y) {
		g.drawLine(x0 + x, y0 + y, x0 + x, y0 + y); // p_0
		g.drawLine(x0 - x, y0 + y, x0 - x, y0 + y); // p_1
		g.drawLine(x0 + x, y0 - y, x0 + x, y0 - y); // p_2
		g.drawLine(x0 - x, y0 - y, x0 - x, y0 - y); // p_3
		g.drawLine(x0 + y, y0 + x, x0 + y, y0 + x); // p_4
		g.drawLine(x0 - y, y0 + x, x0 - y, y0 + x); // p_5
		g.drawLine(x0 + y, y0 - x, x0 + y, y0 - x); // p_6
		g.drawLine(x0 - y, y0 - x, x0 - y, y0 - x); // p_7
	}
}