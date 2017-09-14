package Klausur_2015_1_nochmal;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
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
	int clickerCounter = 0;
	int x, y, o_width, o_height;
	boolean canDraw;
	boolean threadIsRunning;
	Point p1, p2;
	Color c;

	Model(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public void setOval() {
		int a = p2.x > p1.x ? p2.x - p1.x : p1.x - p2.x;
		int b = p2.y > p1.y ? p2.y - p1.y : p1.y - p2.y;
		int c = (int) (Math.sqrt(a * a + b * b));

		this.x = p1.x - c;
		this.y = p1.y - c;
		this.o_width = 2 * c;
		this.o_height = 2 * c;

		this.canDraw = true;
	}
}

class View extends Frame {
	Model m;

	View(Model m) {
		this.m = m;
		setSize(m.width, m.height);
		setLocationRelativeTo(null);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
				if (m.threadIsRunning) {
					m.threadIsRunning = false;
				}
			}
		});
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				++m.clickerCounter;
				if (m.clickerCounter % 2 == 0) {
					m.p2 = e.getPoint();
					m.setOval();
					repaint();
				} else {
					m.p1 = e.getPoint();
				}
			}
		});

		MenuBar mb = new MenuBar();
		Menu menu = new Menu("Einstellungen");
		MenuItem red = new MenuItem("rot");
		MenuItem green = new MenuItem("grün");
		MenuItem ss = new MenuItem("start/stop");

		red.addActionListener(e -> {
			m.c = Color.RED;
			repaint();
		});

		green.addActionListener(e -> {
			m.c = Color.GREEN;
			repaint();
		});

		ss.addActionListener(e -> {
			new Thread() {
				public void run() {
					m.threadIsRunning = !m.threadIsRunning;
					while (m.threadIsRunning) {
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
			}.start();
		});

		menu.add(red);
		menu.add(green);
		menu.add(ss);
		mb.add(menu);
		setMenuBar(mb);

		setVisible(true);
	}

	public void paint(Graphics g) {
		if (m.canDraw) {
			if (m.c != null) {
				g.setColor(m.c);
				g.fillOval(m.x, m.y, m.o_width, m.o_height);
			} else {
				g.drawOval(m.x, m.y, m.o_width, m.o_height);
			}

		}
	}
}

class Controller {
	Model m = new Model(600, 400);
	View v = new View(m);

	Controller() {

	}
}
