package Klausur_2014_1;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Aufgabe3 {
	public static void main(String args[]) {
		new Controller();
	}
}

class Model {
	int counter = 0;
	int width, height;
	int[] xPoints = new int[4];
	int[] yPoints = new int[4];
	Point p1, p2, p3, p4;
	Color c;
	boolean colorIsClicked = false;
	boolean ssIsClicked = false;

	Model(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public void toArray() {
		int[] xPoints = { p1.x, p2.x, p3.x, p4.x };
		int[] yPoints = { p1.y, p2.y, p3.y, p4.y };

		for (int i = 0; i < 4; i++) {
			this.xPoints[i] = xPoints[i];
			this.yPoints[i] = yPoints[i];
		}
	}
}

class View extends Frame {
	Model m;

	View(Model m) {
		this.m = m;
		setSize(m.width, m.height);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
				if (m.ssIsClicked) {
					m.ssIsClicked = false;
				}
			}
		});

		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (m.counter % 4 == 0) {
					m.p1 = e.getPoint();
				} else if (m.counter % 4 == 1) {
					m.p2 = e.getPoint();
				} else if (m.counter % 4 == 2) {
					m.p3 = e.getPoint();
				} else if (m.counter % 4 == 3) {
					m.p4 = e.getPoint();
					m.toArray();
				}
				++m.counter;

				repaint();
			}
		});

		Panel p = new Panel(new FlowLayout());

		Button red = new Button("rot");
		red.addActionListener(e -> {
			m.c = Color.RED;
			m.colorIsClicked = true;
			repaint();
		});
		p.add(red);

		Button green = new Button("grün");
		green.addActionListener(e -> {
			m.c = Color.GREEN;
			m.colorIsClicked = true;
			repaint();
		});
		p.add(green);

		Button ss = new Button("start/stop");
		ss.addActionListener(e -> {
			m.ssIsClicked = !m.ssIsClicked;
			new Thread() {
				public void run() {
					while (m.ssIsClicked) {
						if (m.c == Color.RED) {
							m.c = Color.GREEN;
						} else {
							m.c = Color.RED;
						}
						repaint();
						try {
							Thread.sleep(200);
						} catch (Exception e) {
						}
					}
				}
			}.start();
		});
		p.add(ss);

		add(BorderLayout.SOUTH, p);

		setVisible(true);
	}

	public void paint(Graphics g) {
		if (m.counter > 3) {
			Polygon p = new Polygon(m.xPoints, m.yPoints, 4);
			if (m.colorIsClicked) {
				g.setColor(m.c);
				g.fillPolygon(p);
			} else {
				g.drawPolygon(p);
			}
		}
	}
}

class Controller {
	Model m = new Model(500, 400);
	View v = new View(m);

	Controller() {

	}
}
