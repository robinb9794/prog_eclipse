package Klausur_Probe_1;

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
	int counter = 0;
	Point p1, p2, p3;
	boolean canDraw = false;
	Color c;

	Model(int width, int height) {
		this.width = width;
		this.height = height;
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
			}
		});
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (m.counter % 3 == 0) {
					m.p1 = e.getPoint();
					++m.counter;
				} else if (m.counter % 3 == 1) {
					m.p2 = e.getPoint();
					++m.counter;
				} else {
					m.p3 = e.getPoint();
					++m.counter;
					m.canDraw = true;
					repaint();
				}
			}
		});

		Panel p = new Panel(new FlowLayout());

		Button rot = new Button("rot");
		Button gruen = new Button("grün");
		Button reset = new Button("reset");

		rot.addActionListener(e -> {
			m.c = Color.RED;
			repaint();
		});

		gruen.addActionListener(e -> {
			m.c = Color.GREEN;
			repaint();
		});

		reset.addActionListener(e -> {
			m.c = null;
			m.canDraw = false;
			m.p1 = null;
			m.p2 = null;
			m.p3 = null;
			m.counter = 0;
			repaint();
		});

		p.add(rot);
		p.add(gruen);
		p.add(reset);

		add(BorderLayout.SOUTH, p);

		setVisible(true);
	}

	public void paint(Graphics g) {
		if (m.canDraw) {
			if (m.c != null) {
				g.setColor(m.c);
			}
			g.drawLine(m.p1.x, m.p1.y, m.p2.x, m.p2.y);
			g.drawLine(m.p2.x, m.p2.y, m.p3.x, m.p3.y);
		}
	}
}

class Controller {
	Model m = new Model(500, 300);
	View v = new View(m);
}
