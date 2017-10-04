package uebung05.aufgabe02;

// Aufgabe 2: 
// Implementieren Sie in Java eine Klassenmethode 
// fillCircle(Graphics g,int x0,int y0,int r) 
// die im Graphikkontext g einen ausgefüllten Kreis um den Punkt (x0,y0) mit dem 
// Radius r zeichnet. Die Klassenmethodesoll das Verfahren von Bresenham 
// verwenden. 

import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class Aufgabe02 extends JFrame {
	public Aufgabe02() {
		setSize(600, 600);

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		add(new JComponent() {
			@Override
			public void paint(Graphics g) {
				if (getWidth() > getHeight())
					drawCircle(g, getWidth() / 2, getHeight() / 2,
							getHeight() / 2 - 10);
				else
					drawCircle(g, getWidth() / 2, getHeight() / 2,
							getWidth() / 2 - 10);
			}
		});

		setVisible(true);
	}

	public static void drawCircle(Graphics g, int x0, int y0, int r) {
		double p = 0;
		int y = 0;
		int x = r;
		int F = -r;
		int dy = 1;
		int dyx = -2 * r + 3;
		while (y <= x) {
			p = ((double) y / (double) x) * 100.0;
			setPixel(g, x0, y0, x, y);
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

	public static void setPixel(Graphics g, int x0, int y0, int x, int y) {
		Point linksOben = new Point(x0 - y, y0 - x);
		Point rechtsOben = new Point(x0 + y, y0 - x);
		Point linksUnten = new Point(x0 - y, y0 + x);
		Point rechtsUnten = new Point(x0 + y, y0 + x);
		Point mitteLinksOben = new Point(x0 - x, y0 - y);
		Point mitteRechtsOben = new Point(x0 + x, y0 - y);
		Point mitteLinksUnten = new Point(x0 - x, y0 + y);
		Point mitteRechtsUnten = new Point(x0 + x, y0 + y);
		
		g.drawLine(linksOben.x, linksOben.y,rechtsOben.x, rechtsOben.y);
		g.drawLine(mitteLinksOben.x, mitteLinksOben.y, mitteRechtsOben.x, mitteRechtsOben.y);
		g.drawLine(mitteLinksUnten.x,mitteLinksUnten.y, mitteRechtsUnten.x,mitteRechtsUnten.y);
		g.drawLine(linksUnten.x,linksUnten.y,rechtsUnten.x,rechtsUnten.y);
	}

	public static void main(String[] args) {
		new Aufgabe02();
	}
}
