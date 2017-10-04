package Klausur_Probe_Daniel;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

import javax.swing.*;

public class Aufgabe3 {
	public static void main(String args[]) {
		new Controller();
	}
}

class Model {
	int width, height;
	int counter = 0;
	Color c;
	Point p1, p2;

	Model(int width, int height) {
		this.width = width;
		this.height = height;
	}
}

class View extends JFrame {
	Model m;
	MyComp comp = new MyComp();

	View(Model m) {
		this.m = m;
		setPreferredSize(new Dimension(m.width, m.height));
		setLayout(new BorderLayout());
		add(BorderLayout.CENTER, comp);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (m.counter % 2 == 0) {
					m.counter++;
					m.p1 = e.getPoint();
				} else {
					m.counter++;
					m.p2 = e.getPoint();
					comp.revalidate();
					comp.repaint();
				}
			}
		});

		JMenuBar mb = new JMenuBar();
		JMenu menu = new JMenu("RGB");
		JMenuItem rot = new JMenuItem("rot");
		JMenuItem gruen = new JMenuItem("grün");
		JMenuItem blau = new JMenuItem("blau");

		rot.addActionListener(e -> {
			m.c = Color.RED;
		});

		gruen.addActionListener(e -> {
			m.c = Color.GREEN;
		});

		blau.addActionListener(e -> {
			m.c = Color.BLUE;
		});

		menu.add(rot);
		menu.add(gruen);
		menu.add(blau);

		mb.add(menu);
		setJMenuBar(mb);

		pack();
		setVisible(true);
	}

	class MyComp extends JComponent {
		public void paintComponent(Graphics g) {
			if (m.counter > 1) {
				if (m.c != null) {
					g.setColor(m.c);
				}
				g.drawLine(m.p1.x, m.p1.y, m.p2.x, m.p2.y);
			}
		}
	}
}

class Controller {
	Model m = new Model(500, 500);
	View v = new View(m);
}
