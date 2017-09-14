package Klausur_2015_2_nochmal;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

public class Aufgabe3 {
	public static void main(String args[]) {
		new Controller();
	}
}

class Model {
	int width, height;
	Color c;
	boolean XClicked = false;

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
		Panel main = new Panel(new GridLayout(10, 10));
		for (int i = 0; i < 100; i++) {
			Panel p = new Panel();
			p.setBackground(Color.WHITE);
			new Thread() {
				public void run() {
					while (!m.XClicked) {
						if (p.getBackground() == Color.WHITE) {
							p.setBackground(Color.RED);
						} else if (p.getBackground() == Color.RED) {
							p.setBackground(Color.GREEN);
						} else if (p.getBackground() == Color.GREEN) {
							p.setBackground(Color.WHITE);
						}
						try {
							Thread.sleep(new Random().nextInt(800));
						} catch (InterruptedException e) {
						}
					}
				}
			}.start();
			main.add(p);
		}
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				m.XClicked = true;
				dispose();
			}
		});
		add(BorderLayout.CENTER, main);

		Panel south = new Panel(new FlowLayout());

		Button red = new Button("rot");
		red.addActionListener(e -> {
			m.c = Color.RED;
		});
		south.add(red);

		Button green = new Button("grün");
		green.addActionListener(e -> {
			m.c = Color.GREEN;
		});
		south.add(green);

		add(BorderLayout.SOUTH, south);

		setVisible(true);
	}
}

class Controller {
	Model m = new Model(500, 300);
	View v = new View(m);
}
