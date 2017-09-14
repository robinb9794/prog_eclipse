package Klausur_2016_1_nochmal;

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
	Point p1, p2;
	int counter = 0;
	int x, y, width, height;
	boolean canDraw;
	boolean buttonIsClicked = false;
	boolean isOval = false;

	public void setRect() {
		x = p1.x < p2.x ? p1.x : p2.x;
		y = p1.y < p2.y ? p1.y : p2.y;
		width = p2.x > p1.x ? p2.x - p1.x : p1.x - p2.x;
		height = p2.y > p1.y ? p2.y - p1.y : p1.y - p2.y;
		canDraw = true;
	}
}

class View extends Frame {
	Model m;

	View(Model m) {
		this.m = m;
		setSize(400, 300);
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				dispose();
				if (m.buttonIsClicked) {
					m.buttonIsClicked = false;
				}
			}
		});
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				if (m.counter % 2 == 0) {
					m.p1 = me.getPoint();
					++m.counter;
				} else {
					m.p2 = me.getPoint();
					++m.counter;
					m.setRect();
					repaint();
				}
			}
		});

		Button btn = new Button("start/stop");
		btn.addActionListener(e -> {
			if (m.buttonIsClicked) {
				m.buttonIsClicked = false;
				m.isOval = false;
			} else {
				m.buttonIsClicked = true;
			}
			new Thread() {
				public void run() {
					while (m.buttonIsClicked) {
						if (m.isOval) {
							m.isOval = false;
						} else {
							m.isOval = true;
						}
						m.canDraw = true;
						repaint();
						try {
							Thread.sleep(200);
						} catch (InterruptedException e) {
						}
					}
				}
			}.start();
		});
		add(BorderLayout.SOUTH, btn);

		setVisible(true);
	}

	public void paint(Graphics g) {
		if (m.canDraw) {
			if (m.isOval) {
				g.drawOval(m.x, m.y, m.width, m.height);
			} else {
				g.drawRect(m.x, m.y, m.width, m.height);
			}
			m.canDraw = false;
		}
	}
}

class Controller {
	Model m = new Model();
	View v = new View(m);

	Controller() {

	}
}
