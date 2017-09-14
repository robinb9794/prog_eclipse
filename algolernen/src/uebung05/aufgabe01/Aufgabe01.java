package uebung05.aufgabe01;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;

import javax.swing.JComponent;
import javax.swing.JFrame;

// Aufgabe 1: 
// Implementieren Sie in Java eine Klassenmethode 
// drawCircle(Graphics g,int x0,int y0,int r, 
// Color cNorth, Color cEast, 
// Color cSouth, Color cWest) 
// die im Graphikkontext g einen Kreis umden Punkt (x0,y0) mit dem Radius r 
// zeichnet. Dabei soll der Kreis einen Farbverlauf aufzeigen. Die vier 
// übergebenen Farben definieren die Farben oben und unten, links und rechts. Die 
// Klassenmethode soll das Verfahren von Bresenham verwenden. 

public class Aufgabe01 extends JFrame {

	public Aufgabe01() {
		setSize(600, 600);
		final Color CN=Color.black;
		final Color CE=Color.yellow;
		final Color CS=Color.green;
		final Color CW=Color.blue;

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		add(new JComponent() {
			@Override
			public void paint(Graphics g) {
				
				if (getWidth() < getHeight())
					drawCircle(g, getWidth() / 2, getHeight() / 2,
							getWidth() / 2 - 10,CN,CE,CS,CW);
				else
					
					drawCircle(g, getWidth() / 2, getHeight() / 2,
							getHeight() / 2 - 10,CN,CE,CS,CW);
					
			}
		});

		setVisible(true);
	}

	public static void drawCircle(Graphics g, int x0, int y0, int r, Color CN, Color CE, Color CS, Color CW) {
		Color CNE=new Color (colorShuffle(CN.getRGB(),CE.getRGB(),50));
		Color CES=new Color (colorShuffle(CE.getRGB(),CS.getRGB(),50));
		Color CSW=new Color (colorShuffle(CS.getRGB(),CW.getRGB(),50));
		Color CNW=new Color (colorShuffle(CN.getRGB(),CW.getRGB(),50));
		double p=0;
		int y = 0;
		int x = r;
		int F = -r;
		int dy = 1;
		int dyx = -2 * r + 3;
		while (y <= x) {
			
			p=((double)y/(double)x)*100.0;
			setPixel(g, x0, y0, x, y,CN, CE,CS,CW,CNE,CES,CSW,CNW,(int)p);
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

	public static void setPixel(Graphics g, int x0, int y0, int x, int y,Color CN,Color CE,Color CS,Color CW,Color CNE,Color CES,Color CSW,Color CNW, int p) {
		Color cEnd=new Color(colorShuffle(CE.getRGB(),CNE.getRGB(),p));	
		
		g.setColor(new Color(colorShuffle(CW.getRGB(),CNW.getRGB(),p)));
		g.drawLine(x0 - x, y0 + y, x0 - x, y0 + y); // p_1
		
		g.setColor(new Color(colorShuffle(CE.getRGB(),CES.getRGB(),p)));
		g.drawLine(x0 + x, y0 - y, x0 + x, y0 - y); // p_2
		
		g.setColor(new Color(colorShuffle(CW.getRGB(),CSW.getRGB(),p)));
		g.drawLine(x0 - x, y0 - y, x0 - x, y0 - y); // p_3
		
		g.setColor(new Color(colorShuffle(CN.getRGB(),CNE.getRGB(),p)));
		g.drawLine(x0 + y, y0 + x, x0 + y, y0 + x); // p_4
		
		g.setColor(new Color(colorShuffle(CN.getRGB(),CNW.getRGB(),p)));
		g.drawLine(x0 - y, y0 + x, x0 - y, y0 + x); // p_5
		
		g.setColor(new Color(colorShuffle(CS.getRGB(),CES.getRGB(),p)));
		g.drawLine(x0 + y, y0 - x, x0 + y, y0 - x); // p_6
		
		g.setColor(new Color(colorShuffle(CS.getRGB(),CSW.getRGB(),p)));
		g.drawLine(x0 - y, y0 - x, x0 - y, y0 - x); // p_7
		
	}


	// drawLine aus uebung04 aufgabe02
	public static void drawLine(Graphics g, int x0, int y0, int x1, int y1,
			int cStart, int cEnd) {

		final int dx = Math.abs(x0 - x1);
		final int dy = Math.abs(y0 - y1);
		final int sgnDx = x0 < x1 ? 1 : -1;
		final int sgnDy = y0 < y1 ? 1 : -1;
		int shortD, longD, incXshort, incXlong, incYshort, incYlong;
		if (dx > dy) {
			shortD = dy;
			longD = dx;
			incXlong = sgnDx;
			incXshort = 0;
			incYlong = 0;
			incYshort = sgnDy;
		} else {
			shortD = dx;
			longD = dy;
			incXlong = 0;
			incXshort = sgnDx;
			incYlong = sgnDy;
			incYshort = 0;
		}
		int d = longD / 2, x = x0, y = y0;

		double fullLength = Point2D.distance(x0, y0, x1, y1);

		for (int i = 0; i <= longD; ++i) {
			double part = Point2D.distance(x0, y0, x, y);
			double tempP = (part / fullLength) * 100;
			System.out.println(tempP);
			g.setColor(new Color(colorShuffle(cStart, cEnd, (int) tempP)));
			g.drawLine(x, y, x, y);
			x += incXlong;
			y += incYlong;
			d += shortD;
			if (d >= longD) {
				d -= longD;
				x += incXshort;
				y += incYshort;
			}
		}
	}
	
	// Die colorShuffle-Methode nutzt die singleShuffle-Methode um die
	// einzelnen Farbanteile red, green, blue aus zwei uebergebenener Farben zu
	// extrahieren, um einen Mischwert in Abhaengigkeit eines Prozentsatzes p zu
	// generieren und gibt diesen zurueck
	static int colorShuffle(int i1, int i2, int p) {
		int red = singleShuffle((i1 >> 16) & 255, (i2 >> 16) & 255, p);
		int green = singleShuffle((i1 >> 8) & 255, (i2 >> 8) & 255, p);
		int blue = singleShuffle((i1) & 255, (i2) & 255, p);
		return (255 << 24) | (red << 16) | (green << 8) | blue;
	}

	// Die singleShuffle-Methode gibt den Mischwert zweier Ganzzahlen in
	// Abhaengigkeit eines Prozentsatzes zurueck
	static int singleShuffle(int i1_part, int i2_part, int p) {
		return i1_part + (i2_part - i1_part) * p / 100;
	}

	public static void main(String[] args) {
		new Aufgabe01();
	}
}
