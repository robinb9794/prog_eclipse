package Klausur_2016_2;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
		Controller c = new Controller();
	}
}

class Model {
	Vector<Double> x = new Vector<Double>();
	Vector<Double> y = new Vector<Double>();
	int speed = 200;
	int polySize = 10;
	boolean flag = false;
	boolean run = true;

	public void add() {
		x.add(Math.random());
		y.add(Math.random());
		if (x.size() > polySize) {
			x.remove(0);
			y.remove(0);
		}
	}
}

class View extends JFrame implements ActionListener {
	Model m;
	JDialog dialog;

	View(Model m) {
		this.m = m;

		JMenuBar menubar = new JMenuBar();
		JMenu menu = new JMenu("Datei");
		JMenuItem einstellungen = new JMenuItem("Einstellungen");
		einstellungen.addActionListener(this);
		menu.add(einstellungen);
		menubar.add(menu);
		setJMenuBar(menubar);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				m.run = false;
				dispose();
				if (m.flag == true) {
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
				if (s > 0) {
					int x = (int) (m.x.get(0) * getWidth());
					int y = (int) (m.y.get(0) * getHeight());
					for (int i = 1; i < m.polySize; ++i) {
						try {
							int x2 = (int) (m.x.get(i) * getWidth());
							int y2 = (int) (m.y.get(i) * getHeight());
							g.drawLine(x, y, x2, y2);
							x = x2;
							y = y2;
						} catch (Exception e) {

						}

					}
				}
			}
		});
		pack();
		setVisible(true);
	}

	public void myDialog() {
		dialog = new JDialog();
		dialog.setTitle("Einstellungen");
		dialog.setSize(400, 100);
		dialog.setModal(false);
		dialog.setLayout(new FlowLayout());

		JButton btnSlower = new JButton("langsamer");
		btnSlower.addActionListener(e -> {
			m.speed += 10;
		});
		dialog.add(btnSlower);

		JButton btnFaster = new JButton("schneller");
		btnFaster.addActionListener(e -> {
			m.speed -= 10;
		});
		dialog.add(btnFaster);

		JSlider slider = new JSlider(JSlider.HORIZONTAL, 3, 15, m.polySize);
		slider.addChangeListener(e -> {
			JSlider source = (JSlider) e.getSource();
			m.polySize = (int) source.getValue();
		});
		slider.setMajorTickSpacing(3);
		slider.setMinorTickSpacing(1);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		dialog.add(slider);

		dialog.pack();
		dialog.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (m.flag == false) {
			myDialog();
			m.flag = true;
		} else {
			dialog.setVisible(true);
		}
	}
}

class Controller {
	Model m = new Model();
	View v = new View(m);

	Controller() {
		while (m.run) {
			m.add();
			v.repaint();
			try {
				Thread.sleep(m.speed);
			} catch (Exception e) {

			}
		}
	}
}