package uebung04.aufgabe01;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.geom.Point2D;

import javax.swing.JFrame;

// Aufgabe 1: 
// Implementieren Sie in Java eine Klassenmethode 

// drawLine(Graphics g, 
// int x1,int y1,int x2,int y2, 
// Color cStart,Color cEnd) 

// die im Graphikkontext g eine Linie von (x1,y1)nach (x2,y2)zeichnet und 
// dabei einen Farbverlauf von der Farbe cStartzur Farbe cEndannimmt. 
// Wichtig: die Linie soll von einem beliebigen Startpunkt zu einem beliebigen 
// Endpunkt gezeichnet werden können. Testen Sie, ob senkrechte und 
// waagerechte Linien, auch von links nachrechts und von rechts nach links, von 
// oben nach unten und auch von unten nachoben gezeichnet werden können. Die 
// Klassenmethode soll das Verfahren von Bresenham verwenden. 

public class Aufgabe01 extends JFrame {

	Aufgabe01() {
		setSize(400, 400);

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		add(new Component() {
			@Override
			public void paint(Graphics g) {
				drawLine(g, 0, 0, getWidth(), getHeight(),
						Color.yellow.getRGB(), Color.MAGENTA.getRGB());
			}
		});

		setVisible(true);
	}

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
