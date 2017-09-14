package Klausur_2012_1;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Bubble {
	public static void main(String args[]) {
		new Controller();
	}
}

class Model {
	int x, y;
	int width, height;
	boolean clicked = false;
	int speed = 200;

	Model(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public void setXY() {
		x = (int) (Math.random() * width);
		y = (int) (Math.random() * height);
	}
}

class View extends Frame {
	Model m;

	View(Model m) {
		this.m = m;
		setSize(m.width, m.height);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				new Thread() {
					public void run() {
						new Controller();
					}
				}.start();

			}
		});
		addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				m.width = getWidth();
				m.height = getHeight();
			}
		});
		MenuBar mb = new MenuBar();
		Menu menu = new Menu("Einstellungen");
		for (int i = 50; i < 991; i += 20) {
			String s = String.valueOf(i);
			MenuItem mi = new MenuItem(s);
			mi.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					m.speed = Integer.parseInt(mi.getLabel().toString());
				}
			});
			menu.add(mi);
		}
		mb.add(menu);
		setMenuBar(mb);
		setVisible(true);
	}

	public void paint(Graphics g) {
		g.drawOval(m.x, m.y, 20, 20);
	}
}

class Controller {
	Model m = new Model(400, 300);
	View v = new View(m);

	Controller() {
		while (!m.clicked) {
			m.setXY();
			v.repaint();
			try {
				Thread.sleep(m.speed);
			} catch (InterruptedException e) {
			}
		}
	}
}
