package Klausur_2016_2_nochmal_2;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Aufgabe3 {
	public static void main(String args[]) {
		new Controller();
	}
}

class Model {
	int width, height;
	int speed = 200;
	int size = 10;
	Vector<Double> x = new Vector<Double>();
	Vector<Double> y = new Vector<Double>();
	boolean openedDialog = false;
	boolean closedWindow = false;

	Model(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public void add() {
		x.add(Math.random());
		y.add(Math.random());
		if (x.size() > size) {
			x.remove(0);
			y.remove(0);
		}
	}
}

class View extends JFrame {
	Model m;
	JDialog dialog = new JDialog();

	View(Model m) {
		this.m = m;
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				m.closedWindow = true;
				dispose();
				if (m.openedDialog) {
					dialog.dispose();
				}
			}
		});
		add(new JComponent() {
			{
				setPreferredSize(new Dimension(400, 300));
			}

			public void paintComponent(Graphics g) {
				int s = m.x.size();
				if (s >= m.size) {
					int x = (int) (m.x.get(0) * getWidth());
					int y = (int) (m.y.get(0) * getHeight());
					for (int i = 1; i < m.size; ++i) {
						int x2 = (int) (m.x.get(i) * getWidth());
						int y2 = (int) (m.y.get(i) * getHeight());
						g.drawLine(x, y, x2, y2);
						x = x2;
						y = y2;
					}
				}
			}
		});

		dialog.setLayout(new FlowLayout());
		dialog.setModal(false);

		JButton schneller = new JButton("schneller");
		JButton langsamer = new JButton("langsamer");

		schneller.addActionListener(e -> {
			m.speed -= 10;
		});

		langsamer.addActionListener(e -> {
			m.speed += 10;
		});

		JSlider slider = new JSlider(JSlider.HORIZONTAL, 3, 15, m.size);
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				m.size = slider.getValue();
			}
		});

		dialog.add(schneller);
		dialog.add(langsamer);
		dialog.add(slider);
		dialog.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				m.openedDialog = false;
				dialog.dispose();
			}
		});
		dialog.pack();

		JMenuBar mb = new JMenuBar();
		JMenu menu = new JMenu("Datei");
		JMenuItem mi = new JMenuItem("Einstellungen");
		mi.addActionListener(e -> {
			if (m.openedDialog) {
				dialog.setVisible(true);
			} else {
				m.openedDialog = true;
				dialog.setVisible(true);
			}
		});
		menu.add(mi);
		mb.add(menu);
		setJMenuBar(mb);

		pack();
		setVisible(true);
	}
}

class Controller {
	Model m = new Model(500, 300);
	View v = new View(m);

	Controller() {
		while (!m.closedWindow) {
			m.add();
			v.repaint();
			try {
				Thread.sleep(m.speed);
			} catch (Exception e) {

			}
		}
	}
}
