package Klausur_2016_1_nochmal_2;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.Graphics;
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
	int counter = 0;
	int rectX, rectY, rectWidth, rectHeight;
	Point p1, p2;
	boolean clicked = false;
	boolean oval = false;
	boolean draw = false;

	Model(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public void setRect() {
		rectX = p1.x < p2.x ? p1.x : p2.x;
		rectY = p1.y < p2.y ? p1.y : p2.y;
		rectWidth = p2.x > p1.x ? p2.x - p1.x : p1.x - p2.x;
		rectHeight = p2.y > p1.y ? p2.y - p1.y : p1.y - p2.y;
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
				if (m.clicked) {
					m.clicked = false;
				}
			}
		});
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (m.counter % 2 == 0) {
					m.p1 = e.getPoint();
				} else {
					m.p2 = e.getPoint();
					m.setRect();
					m.draw = true;
					repaint();
				}
				++m.counter;
			}
		});

		Button ss = new Button("start/stop");
		ss.addActionListener(e -> {
			m.clicked = !m.clicked;
			new Thread() {
				public void run() {
					while (m.clicked) {
						m.oval = !m.oval;
						repaint();
						try {
							Thread.sleep(200);
						} catch (InterruptedException e) {
						}
					}
					m.oval = false;
				}
			}.start();
		});
		add(BorderLayout.SOUTH, ss);

		setVisible(true);
	}

	public void paint(Graphics g) {
		if (m.draw) {
			if (m.oval) {
				g.drawOval(m.rectX, m.rectY, m.rectWidth, m.rectHeight);
			} else {
				g.drawRect(m.rectX, m.rectY, m.rectWidth, m.rectHeight);
			}
		}
	}
}

class Controller {
	Model m = new Model(500, 300);
	View v = new View(m);
}
