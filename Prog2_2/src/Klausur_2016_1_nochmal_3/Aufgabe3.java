package Klausur_2016_1_nochmal_3;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.*;

public class Aufgabe3 {
	public static void main(String args[]) {
		new Controller();
	}
}

class Model {
	int width, height;
	int counter = 0;
	int rectX, rectY, rectW, rectH;
	Point p1, p2;
	boolean canDraw = false;
	boolean start = false;
	boolean oval = false;

	Model(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public void setRect() {
		if (counter > 1) {
			rectX = p1.x < p2.x ? p1.x : p2.x;
			rectY = p1.y < p2.y ? p1.y : p2.y;
			rectW = p2.x > p1.x ? p2.x - p1.x : p1.x - p2.x;
			rectH = p2.y > p1.y ? p2.y - p1.y : p1.y - p2.y;
			canDraw = true;
		}

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
			}
		});
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (m.counter % 2 == 0) {
					m.counter++;
					m.p1 = e.getPoint();
				} else {
					m.counter++;
					m.p2 = e.getPoint();
					m.setRect();
					repaint();
				}
			}
		});

		Button ss = new Button("start/stop");
		ss.addActionListener(e -> {
			new Thread() {
				public void run() {
					m.start = !m.start;
					while (m.start) {
						m.oval = !m.oval;
						repaint();
						try {
							Thread.sleep(200);
						} catch (InterruptedException e) {
						}
					}
				}
			}.start();
		});
		add(BorderLayout.SOUTH, ss);

		setVisible(true);
	}

	public void paint(Graphics g) {
		if (m.canDraw) {
			if (m.oval) {
				g.drawOval(m.rectX, m.rectY, m.rectW, m.rectH);
			} else {
				g.drawRect(m.rectX, m.rectY, m.rectW, m.rectH);
			}
		}
	}
}

class Controller {
	Model m = new Model(500, 300);
	View v = new View(m);
}
