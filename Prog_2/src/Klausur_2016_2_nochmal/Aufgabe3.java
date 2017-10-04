package Klausur_2016_2_nochmal;

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

public class Aufgabe3 {
	public static void main(String args[]) {
		new Controller();
	}
}

class Model {
	Vector<Double> x = new Vector<Double>();
	Vector<Double> y = new Vector<Double>();
	int speed = 200;
	int elements = 10;
	boolean openedDialog = false;
	boolean clickedX = false;

	public void add() {
		x.add(Math.random());
		y.add(Math.random());
		if (x.size() > elements) {
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
		add(new JComponent() {
			{
				setPreferredSize(new Dimension(400, 300));
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
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
				m.clickedX = true;
				if (m.openedDialog) {
					dialog.dispose();
				}
			}
		});

		JMenuBar menubar = new JMenuBar();
		JMenu menu = new JMenu("Datei");
		JMenuItem menuitem = new JMenuItem("Einstellungen");
		menuitem.addActionListener(e -> {
			if (!m.openedDialog) {
				dialog.setLayout(new FlowLayout());
				dialog.setModal(false);
				dialog.addWindowListener(new WindowAdapter() {
					public void windowClosing(WindowEvent we) {
						dialog.dispose();
						m.openedDialog = false;
					}
				});
				JButton schneller = new JButton("schneller");
				JButton langsamer = new JButton("langsamer");
				JSlider slider = new JSlider(3, 15, m.elements);

				schneller.addActionListener(e2 -> {
					m.speed -= 10;
				});

				langsamer.addActionListener(e3 -> {
					m.speed += 10;
				});

				slider.addChangeListener(e4 -> {
					m.elements = slider.getValue();
				});

				dialog.add(schneller);
				dialog.add(langsamer);
				dialog.add(slider);
				dialog.pack();
				dialog.setVisible(true);

				m.openedDialog = true;
			} else {
				dialog.setVisible(true);
			}
		});
		menu.add(menuitem);
		menubar.add(menu);
		setJMenuBar(menubar);
		pack();
		setVisible(true);
	}
}

class Controller {
	Model m = new Model();
	View v = new View(m);

	Controller() {
		while (!(m.clickedX)) {
			m.add();
			v.repaint();
			try {
				Thread.sleep(m.speed);
			} catch (Exception e) {
			}
		}
	}
}
