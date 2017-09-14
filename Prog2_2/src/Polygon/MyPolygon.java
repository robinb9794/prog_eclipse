package Polygon;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Vector;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MyPolygon {
	public static void main(String args[]) {
		Controller c = new Controller();
	}
}

class Model {
	Vector<Double> x = new Vector<Double>();
	Vector<Double> y = new Vector<Double>();
	Color cBg, cLine;
	int speed = 200;
	int size = 3;

	Model(Color bg, Color line) {
		cBg = bg;
		cLine = line;
	}

	public void add() {
		x.add(Math.random());
		y.add(Math.random());
		if (x.size() > size) {
			for (int i = 0; i < x.size() - size; i++) {
				x.remove(i);
				y.remove(i);
			}
		}
	}
}

class View extends JFrame {
	Model m;

	View(Model m) {
		this.m = m;
		getContentPane().setBackground(m.cBg);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menubar = new JMenuBar();

		JMenu geschw = new JMenu("Geschwindigkeit");

		JMenuItem schneller = new JMenuItem("schneller");
		schneller.addActionListener(e -> {
			m.speed -= 30;
		});
		geschw.add(schneller);

		JMenuItem langsamer = new JMenuItem("langsamer");
		langsamer.addActionListener(e -> {
			m.speed += 30;
		});
		geschw.add(langsamer);

		JMenu laenge = new JMenu("Länge");

		JMenuItem laenge3 = new JMenuItem("3");
		laenge3.addActionListener(e -> {
			m.size = 3;
		});
		laenge.add(laenge3);

		JMenuItem laenge7 = new JMenuItem("7");
		laenge7.addActionListener(e -> {
			m.size = 7;
		});
		laenge.add(laenge7);

		JMenuItem laenge10 = new JMenuItem("10");
		laenge10.addActionListener(e -> {
			m.size = 10;
		});
		laenge.add(laenge10);

		JMenuItem laenge30 = new JMenuItem("30");
		laenge30.addActionListener(e -> {
			m.size = 30;
		});
		laenge.add(laenge30);

		menubar.add(laenge);
		menubar.add(geschw);
		setJMenuBar(menubar);

		add(new JComponent() {
			{
				setPreferredSize(new Dimension(400, 300));
			}

			public void paintComponent(Graphics g) {
				int s = m.x.size();
				if (s > 0) {
					int x = (int) (m.x.get(0) * getWidth());
					int y = (int) (m.y.get(0) * getHeight());
					for (int i = 1; i < m.x.size(); ++i) {
						int x2 = (int) (m.x.get(i) * getWidth());
						int y2 = (int) (m.y.get(i) * getHeight());
						g.setColor(m.cLine);
						g.drawLine(x, y, x2, y2);
						x = x2;
						y = y2;
					}
				}
			}
		});
		pack();
		setVisible(true);
	}
}

class Controller {
	Model m = new Model(Color.DARK_GRAY, Color.WHITE);
	View v = new View(m);

	Controller() {
		while (true) {
			m.add();
			v.repaint();
			try {
				Thread.sleep(m.speed);
			} catch (Exception e) {

			}
		}
	}
}