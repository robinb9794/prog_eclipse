package Klausur_2011_1;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Aufgabe2 {
	public static void main(String args[]) {
		new Controller();
	}
}

class Model {
	int width, height;
	Image[] images;
	int index = 0;

	Model(int width, int height, Image[] images) {
		this.width = width;
		this.height = height;
		this.images = images;
	}

	public void setIndex() {
		if (index == -1) {
			index = images.length - 1;
		} else if (index == images.length) {
			index = 0;
		}
	}
}

class View extends Frame {
	Model m;

	View(Model m) {
		this.m = m;
		setSize(m.width, m.height);
		setLocationRelativeTo(null);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				m.width = getWidth();
				m.height = getHeight();
			}
		});
		setLayout(new BorderLayout());

		Panel p = new Panel(new FlowLayout());
		Button prev = new Button("prev");
		prev.addActionListener(e -> {
			m.index--;
			m.setIndex();
			repaint();
		});
		p.add(prev);

		Button next = new Button("next");
		next.addActionListener(e -> {
			m.index++;
			m.setIndex();
			repaint();
		});
		p.add(next);

		add(BorderLayout.SOUTH, p);
		setVisible(true);
	}

	public void paint(Graphics g) {
		g.drawImage(m.images[m.index], 0, 0, m.width, m.height, null);
	}
}

class Controller {
	Controller() {
		Image[] images = new Image[9];
		for (int i = 0; i < 9; i++) {
			try {
				images[i] = ImageIO.read(new File("img/" + (i + 1) + ".jpg"));
			} catch (IOException e) {
			}
		}
		Model m = new Model(500, 500, images);
		View v = new View(m);
	}
}
