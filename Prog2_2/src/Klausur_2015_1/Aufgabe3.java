package Klausur_2015_1;

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
	int m_width, m_height;
	Point m_p1, m_p2;
	int m_counter = 0;
	Color m_c;
	boolean m_isStartStop;

	Model(int width, int height) {
		m_width = width;
		m_height = height;
	}
}

class View extends Frame {
	Model m;

	View(Model m) {
		this.m = m;
		setSize(m.m_width, m.m_height);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
				m.m_isStartStop = false;
			}
		});

		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (m.m_counter % 2 == 0) {
					m.m_p1 = e.getPoint();
					++m.m_counter;
				} else {
					m.m_p2 = e.getPoint();
					++m.m_counter;
					repaint();
				}
			}
		});

		MenuBar menubar = new MenuBar();
		Menu menu = new Menu("Einstellungen");

		MenuItem mi_rot = new MenuItem("rot");
		mi_rot.addActionListener(e -> {
			m.m_c = Color.RED;
			m.m_counter = 2;
			repaint();
		});
		menu.add(mi_rot);

		MenuItem mi_gruen = new MenuItem("grün");
		mi_gruen.addActionListener(e -> {
			m.m_c = Color.GREEN;
			m.m_counter = 2;
			repaint();
		});
		menu.add(mi_gruen);

		MenuItem mi_ss = new MenuItem("start/stop");
		mi_ss.addActionListener(e -> {
			m.m_isStartStop = true;
			new Thread() {
				public void run() {
					while (m.m_isStartStop) {
						if (m.m_c == Color.RED) {
							m.m_c = Color.GREEN;
						} else if (m.m_c == Color.GREEN) {
							m.m_c = Color.RED;
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
		menu.add(mi_ss);

		menubar.add(menu);
		setMenuBar(menubar);

		setVisible(true);
	}

	public void paint(Graphics g) {
		if (m.m_counter % 2 == 0 && m.m_counter > 0) {
			int a = m.m_p2.y > m.m_p1.y ? m.m_p2.y - m.m_p1.y : m.m_p1.y - m.m_p2.y;
			int b = m.m_p2.x > m.m_p1.x ? m.m_p2.x - m.m_p1.x : m.m_p1.x - m.m_p2.x;
			int cradius = (int) (Math.sqrt(a * a + b * b));

			int x = m.m_p1.x - cradius;
			int y = m.m_p1.y - cradius;

			if (m.m_c != null) {
				g.setColor(m.m_c);
				g.fillOval(x, y, cradius * 2, cradius * 2);
			} else {
				g.drawOval(x, y, cradius * 2, cradius * 2);
			}
		}
	}
}

class Controller {
	Model m;
	View v;

	Controller() {
		m = new Model(400, 400);
		v = new View(m);
	}
}
