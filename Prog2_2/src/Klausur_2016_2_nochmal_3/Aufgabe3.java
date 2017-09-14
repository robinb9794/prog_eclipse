package Klausur_2016_2_nochmal_3;

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
	Vector<Double> x = new Vector<Double>();
	Vector<Double> y = new Vector<Double>();
	boolean XClicked = false;
	boolean dialogOpened = false;
	int speed = 200;
	int elements = 10;

	public void add() {
		x.add(Math.random());
		y.add(Math.random());
		if (x.size() > elements) {
			x.remove(0);
			y.remove(0);
		}
	}

	Model(int width, int height) {
		this.width = width;
		this.height = height;
	}
}

class View extends JFrame {
	Model m;
	JDialog dialog = new JDialog();

	View(Model m) {
		this.m = m;
		setLocationRelativeTo(null);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				m.XClicked = true;
				if (m.dialogOpened) {
					dialog.dispose();
				}
				dispose();
			}
		});
		add(new JComponent() {
			{
				setPreferredSize(new Dimension(m.width, m.height));
			}

			public void paintComponent(Graphics g) {
				int s = m.x.size();
				if (s >= m.elements) {
					int x = (int) (m.x.get(0) * getWidth());
					int y = (int) (m.y.get(0) * getHeight());
					for (int i = 1; i < m.elements; ++i) {
						int x2 = (int) (m.x.get(i) * getWidth());
						int y2 = (int) (m.y.get(i) * getHeight());
						g.drawLine(x, y, x2, y2);
						x = x2;
						y = y2;
					}
				}
			}
		});

		JMenuBar mb = new JMenuBar();
		JMenu menu = new JMenu("Datei");
		JMenuItem mi = new JMenuItem("Einstellungen");

		mi.addActionListener(e -> {
			if (m.dialogOpened) {
				dialog.setVisible(true);
			} else {
				m.dialogOpened = true;
				dialog.setModal(false);
				dialog.setLayout(new FlowLayout());

				JButton langsamer = new JButton("langsamer");
				JButton schneller = new JButton("schneller");

				langsamer.addActionListener(e2 -> {
					m.speed += 10;
				});

				schneller.addActionListener(e2 -> {
					m.speed -= 10;
					if (m.speed < 0) {
						m.speed = 1;
					}
				});

				JSlider slider = new JSlider(JSlider.HORIZONTAL, 3, 15, 10);
				slider.addChangeListener(new ChangeListener() {
					public void stateChanged(ChangeEvent e) {
						m.elements = slider.getValue();
					}
				});

				dialog.add(langsamer);
				dialog.add(schneller);
				dialog.add(slider);

				dialog.pack();
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
		while (!m.XClicked) {
			m.add();
			v.repaint();
			try {
				Thread.sleep(m.speed);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
