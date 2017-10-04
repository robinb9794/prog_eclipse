package Klausur_2015_2;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Aufgabe3 {
	public static void main(String args[]) {
		new Controller();
	}
}

class Model {
	Color c;
	boolean xIsClicked = false;

	Model() {

	}
}

class View extends Frame {
	Model m;

	View(Model m) {
		this.m = m;
		setSize(500, 500);
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
				m.xIsClicked = true;
			}
		});

		Panel panelCenter = new Panel(new GridLayout(10, 10));
		for (int i = 0; i < 100; i++) {
			Panel p = new Panel();
			new Thread() {
				public void run() {
					while (!m.xIsClicked) {
						int time = (int) (Math.random() * 800);
						if (p.getBackground() == Color.WHITE) {
							p.setBackground(Color.RED);
						} else if (p.getBackground() == Color.RED) {
							p.setBackground(Color.GREEN);
						} else {
							p.setBackground(Color.WHITE);
						}
						try {
							Thread.sleep(time);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}.start();
			panelCenter.add(p);
		}
		add(BorderLayout.CENTER, panelCenter);

		Panel panelSouth = new Panel(new FlowLayout());

		Button green = new Button("grün");
		green.addActionListener(e -> {
			m.c = Color.GREEN;
		});
		panelSouth.add(green);

		Button red = new Button("rot");
		red.addActionListener(e -> {
			m.c = Color.RED;
		});
		panelSouth.add(red);

		add(BorderLayout.SOUTH, panelSouth);
		setVisible(true);

	}
}

class Controller {
	Model m = new Model();
	View v = new View(m);
}
