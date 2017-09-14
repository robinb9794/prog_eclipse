package Klausur_2014_2;

import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Panel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Aufgabe3 {
	public static void main(String args[]) {
		new Controller();
	}
}

class Model {
	Image[] images = new Image[9];
	int m_width, m_height;
	String clickedPanel_1, clickedPanel_2;
	int clickerCounter = 0;
	boolean menuIsClicked = false;
	int pos1, pos2;

	Model(Image[] images, int width, int height) {
		for (int i = 0; i < 9; i++) {
			this.images[i] = images[i];
		}
		m_width = width;
		m_height = height;
	}

	public void setClickedPanel_1(String clickedPanel_1) {
		this.clickedPanel_1 = clickedPanel_1;
	}

	public void setClickedPanel_2(String clickedPanel_2) {
		this.clickedPanel_2 = clickedPanel_2;
	}
}

class View extends Frame {
	Model m;
	Panel[] panels = new Panel[9];

	View(Model m) {
		this.m = m;
		setSize(600, 600);
		setLocationRelativeTo(null);
		setLayout(new GridLayout(3, 3));
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
				if (m.menuIsClicked) {
					m.menuIsClicked = false;
				}
			}
		});

		for (int i = 0; i < 9; i++) {
			Panel panel = new Panel();
			ImageIcon icon = new ImageIcon(
					m.images[i].getScaledInstance(m.m_width / 3, m.m_height / 3, Image.SCALE_SMOOTH));
			JLabel label = new JLabel(icon);
			panel.add(label);
			panel.setName("panel" + i);
			panel.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if (m.clickerCounter % 2 == 0) {
						m.setClickedPanel_1(panel.getName());
						++m.clickerCounter;
					} else if (m.clickerCounter % 2 == 1) {
						m.setClickedPanel_2(panel.getName());
						++m.clickerCounter;
						switchPanels();
					}

				}
			});
			panels[i] = panel;
			add(panel);
		}

		MenuBar menubar = new MenuBar();
		Menu menu = new Menu("Einstellungen");
		MenuItem menuitem = new MenuItem("Action");
		menuitem.addActionListener(e -> {
			new Thread() {
				public void run() {
					if (!m.menuIsClicked) {
						m.menuIsClicked = true;
					} else {
						m.menuIsClicked = false;
					}
					while (m.menuIsClicked) {
						m.pos1 = (int) (Math.random() * 9);
						m.pos2 = (int) (Math.random() * 9);
						switchPanels();
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

				}
			}.start();
		});
		menu.add(menuitem);
		menubar.add(menu);
		setMenuBar(menubar);

		setVisible(true);
	}

	public void switchPanels() {
		if (m.menuIsClicked) {
			int pos_panel1 = m.pos1;
			int pos_panel2 = m.pos2;

			Panel ptemp = panels[m.pos1];
			panels[m.pos1] = panels[m.pos2];
			panels[m.pos2] = ptemp;
		} else {
			String p1 = m.clickedPanel_1.split("l")[1];
			String p2 = m.clickedPanel_2.split("l")[1];

			int pos_panel1 = Integer.parseInt(p1);
			int pos_panel2 = Integer.parseInt(p2);

			Panel ptemp = panels[pos_panel1];
			panels[pos_panel1] = panels[pos_panel2];
			panels[pos_panel2] = ptemp;
		}

		for (int i = 0; i < 9; i++) {
			add(panels[i]);
		}
		validate();
		repaint();
	}
}

class Controller {
	Model m;
	View v;

	Controller() {
		Image[] images = new Image[9];
		for (int i = 1; i < 10; i++) {
			try {
				images[i - 1] = ImageIO.read(new File("img/" + i + ".jpg"));
			} catch (IOException e) {
			}
		}
		m = new Model(images, 600, 600);
		v = new View(m);
	}
}
