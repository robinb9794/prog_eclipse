package uebung05.aufgabe03;

// Aufgabe 3: 
// Implementieren Sie in Java eine Klassenmethode 
// fillCircle(Graphics g,int x0,int y0,int r, 
// Color cEast, Color cWest) 
// die im Graphikkontext g einen ausgefüllten Kreis um den Punkt (x0,y0) mit dem 
// Radius r zeichnet. Dabei soll es einen Farbverlauf von der Farbe "cEast" (rechts) 
// zu der Farbe "cWest" (links) geben. Die Klassenmethode soll das Verfahren von 
// Bresenham verwenden. 

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Point2D;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class Aufgabe03 extends JFrame {

	public Aufgabe03() {
		setSize(600, 600);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		final Color CE = Color.yellow;
		final Color CW = Color.red;

		add(new JComponent() {
			@Override
			public void paint(Graphics g) {
				if (getWidth() < getHeight())
					drawCircle(g, getWidth() / 2, getHeight() / 2,
							getWidth() / 2 - 10, CE, CW);
				else
					drawCircle(g, getWidth() / 2, getHeight() / 2,
							getHeight() / 2 - 10, CE, CW);
			}
		});

		setVisible(true);
	}

	public static void drawCircle(Graphics g, int x0, int y0, int r, Color CW,
			Color CE) {
		int y = 0;
		int x = r;
		int F = -r;
		int dy = 1;
		int dyx = -2 * r + 3;
		while (y <= x) {
			setPixel(g, x0, y0, x, y, CW, CE, r);
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

	public static void setPixel(Graphics g, int x0, int y0, int x, int y,
			Color CE, Color CW, int r) {

		Point linksOben = new Point(x0 - y, y0 - x);
		Point rechtsOben = new Point(x0 + y, y0 - x);
		Point linksUnten = new Point(x0 - y, y0 + x);
		Point rechtsUnten = new Point(x0 + y, y0 + x);
		Point mitteLinksOben = new Point(x0 - x, y0 - y);
		Point mitteRechtsOben = new Point(x0 + x, y0 - y);
		Point mitteLinksUnten = new Point(x0 - x, y0 + y);
		Point mitteRechtsUnten = new Point(x0 + x, y0 + y);

		double distance = mitteLinksOben.distance(mitteRechtsOben);
		double p = (((2 * r) - (distance)) / (2 * r)) * 50;
		int start =CE.getRGB();// colorShuffle(CE.getRGB(), CW.getRGB(), (int) p);
		int end =CW.getRGB();// colorShuffle(CW.getRGB(), CE.getRGB(), (int) p);
		drawLine(g, mitteLinksOben, mitteRechtsOben, start, end);

		distance = mitteLinksUnten.distance(mitteRechtsUnten);
		p = (((2 * r) - (distance)) / (2 * r)) * 50;
		//start = colorShuffle(CE.getRGB(), CW.getRGB(), (int) p);
		//end = colorShuffle(CW.getRGB(), CE.getRGB(), (int) p);
		drawLine(g, mitteLinksUnten, mitteRechtsUnten, start, end);

		distance = linksUnten.distance(rechtsUnten);
		p = (((2 * r) - (distance)) / (2 * r)) * 50;
		//start = colorShuffle(CE.getRGB(), CW.getRGB(), (int) p);
		//end = colorShuffle(CW.getRGB(), CE.getRGB(), (int) p);
		drawLine(g, linksUnten, rechtsUnten, start, end);

		distance = linksOben.distance(rechtsOben);
		p = (((2 * r) - (distance)) / (2 * r)) * 50;
		//start = colorShuffle(CE.getRGB(), CW.getRGB(), (int) p);
		//end = colorShuffle(CW.getRGB(), CE.getRGB(), (int) p);
		drawLine(g, linksOben, rechtsOben, start, end);
	}

	// drawLine aus uebung04 aufgabe02
	public static void drawLine(Graphics g, Point start, Point end, int cStart,
			int cEnd) {

		final int dx = Math.abs(start.x - end.x);
		final int dy = Math.abs(start.y - end.y);
		final int sgnDx = start.x < end.x ? 1 : -1;
		final int sgnDy = start.y < end.y ? 1 : -1;
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
		int d = longD / 2, x = start.x, y = start.y;

		double fullLength = Point2D.distance(start.x, start.y, end.x, end.y);

		for (int i = 0; i <= longD; ++i) {
			double part = Point2D.distance(start.x, start.y, x, y);
			double tempP = (part / fullLength) * 100;
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
		new Aufgabe03();
	}
}
