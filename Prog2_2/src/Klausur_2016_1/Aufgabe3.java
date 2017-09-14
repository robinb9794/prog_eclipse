package Klausur_2016_1;

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
	Point p1 = null, p2 = null;
	int m_Width, m_Height;
	boolean isOval;
	boolean isClicked;
	int counter = 0;

	Model(int width, int height) {
		m_Width = width;
		m_Height = height;
	}
}

class View extends Frame {
	Model m;

	View(Model m) {
		this.m = m;

		setSize(m.m_Width, m.m_Height);
		setLayout(new BorderLayout());

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
				m.isClicked = false;
			}
		});

		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (m.counter == 0 && !m.isClicked) {
					++m.counter;
					m.p1 = e.getPoint();
				} else if (m.counter == 1 && !m.isClicked) {
					m.p2 = e.getPoint();
					m.counter = 0;
					repaint();
				}
			}
		});

		Button ss = new Button("start/stop");
		ss.addActionListener(e -> {
			new Thread() {
				public void run() {
					if (!m.isClicked) {
						m.isClicked = true;
					} else {
						m.isClicked = false;
					}
					while (m.isClicked) {
						if (m.isOval) {
							m.isOval = false;
						} else {
							m.isOval = true;
						}
						repaint();
						try {
							Thread.sleep(200);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}.start();

		});

		add(BorderLayout.SOUTH, ss);
		setVisible(true);
	}

	public void paint(Graphics g) {
		if (m.p1 != null && m.p2 != null) {
			int x = m.p1.x < m.p2.x ? m.p1.x : m.p2.x;
			int y = m.p1.y < m.p2.y ? m.p1.y : m.p2.y;
			int width = m.p2.x > m.p1.x ? m.p2.x - m.p1.x : m.p1.x - m.p2.x;
			int height = m.p2.y > m.p1.y ? m.p2.y - m.p1.y : m.p1.y - m.p2.y;

			if (m.isOval) {
				g.drawOval(x, y, width, height);
			} else {
				g.drawRect(x, y, width, height);
			}
		}

	}
}

class Controller {
	Model m;
	View v;

	Controller() {
		m = new Model(400, 300);
		v = new View(m);
	}
}
