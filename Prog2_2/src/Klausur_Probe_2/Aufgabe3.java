package Klausur_Probe_2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class Aufgabe3 {
	public static void main(String args[]) {
		new Controller();
	}
}

class Model {
	int width, height;
	boolean rect = true;
	boolean XClicked = false;
	Color c = Color.RED;

	Model(int width, int height) {
		this.width = width;
		this.height = height;
	}
}

class View extends JFrame {
	Model m;
	Comp comp = new Comp();

	View(Model m) {
		this.m = m;
		setPreferredSize(new Dimension(m.width, m.height));
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				m.XClicked = true;
				dispose();
			}
		});

		add(BorderLayout.CENTER, comp);

		pack();
		setVisible(true);
	}

	class Comp extends JComponent {
		public void paintComponent(Graphics g) {
			if (m.c != null) {
				g.setColor(m.c);
			}
			if (m.rect) {
				g.drawRect(getWidth() / 2 - 50, getHeight() / 2 - 50, 100, 100);
			} else {
				g.drawOval(getWidth() / 2 - 50, getHeight() / 2 - 50, 100, 100);
			}
		}
	}
}

class Controller {
	Model m = new Model(500, 500);
	View v = new View(m);

	Controller() {
		new Thread() {
			public void run() {
				while (!m.XClicked) {
					m.rect = !m.rect;

					v.comp.repaint();
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
					}
				}
			}
		}.start();

		new Thread() {
			public void run() {
				while (!m.XClicked) {
					if (m.c == Color.RED) {
						m.c = Color.GREEN;
					} else {
						m.c = Color.RED;
					}
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
					}
				}
			}
		}.start();
	}
}
