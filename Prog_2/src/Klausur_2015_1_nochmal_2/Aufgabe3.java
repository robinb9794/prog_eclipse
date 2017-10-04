package Klausur_2015_1_nochmal_2;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.Point;
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
	int width, height;
	Point p1, p2;
	int counter = 0;
	int x, y, oWidth, oHeight;
	Color c;
	boolean isClicked = false;
	boolean isOpen = true;

	Model(int width, int height) {
		this.width = width;
		this.height = height;
	}

	void setOval() {
		int a = p2.x > p1.x ? p2.x - p1.x : p1.x - p2.x;
		int b = p2.y > p1.y ? p2.y - p1.y : p1.y - p2.y;
		int c = (int) (Math.sqrt(a * a + b * b));

		x = p1.x - c;
		y = p1.y - c;
		oWidth = 2 * c;
		oHeight = 2 * c;
	}
}

class View extends Frame {
	Model m;

	View(Model m) {
		this.m = m;
		setSize(m.width, m.height);
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
				m.isOpen = false;
			}
		});
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (m.counter % 2 == 0) {
					m.p1 = e.getPoint();
					m.counter++;
				} else {
					m.p2 = e.getPoint();
					m.counter++;
					m.setOval();
					repaint();
				}
			}
		});

		Panel p = new Panel(new FlowLayout());

		Button red = new Button("rot");
		red.addActionListener(e -> {
			m.c = Color.RED;
			m.isClicked = false;
			repaint();
		});
		p.add(red);

		Button green = new Button("grün");
		green.addActionListener(e -> {
			m.c = Color.GREEN;
			m.isClicked = false;
			repaint();
		});
		p.add(green);

		Button ss = new Button("start/stop");
		ss.addActionListener(e -> {
			new Thread() {
				public void run() {
					m.isClicked = !m.isClicked;
					if (m.isClicked) {
						while (m.isOpen) {
							if (m.c == Color.RED) {
								m.c = Color.GREEN;
							} else {
								m.c = Color.RED;
							}
							repaint();
							try {
								Thread.sleep(200);
							} catch (InterruptedException e) {
							}
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
		if (m.counter > 1) {
			if (m.c == null) {
				g.drawOval(m.x, m.y, m.oWidth, m.oHeight);
			} else {
				g.setColor(m.c);
				g.fillOval(m.x, m.y, m.oWidth, m.oHeight);
			}

		}
	}
}

class Controller {
	Model m = new Model(500, 300);
	View v = new View(m);
}
