package Klausur_2013_2_nochmal_2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
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
	int rectWidth = 0, rectHeight = 0;
	boolean fill = false;

	Model(int width, int height) {
		this.width = width;
		this.height = height;
	}
}

class View extends JFrame {
	Model m;
	Comp comp = new Comp();

	View(Model m) {
		this.m = m;
		setPreferredSize(new Dimension(m.width, m.height));
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});

		JPanel south = new JPanel(new FlowLayout());

		JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				m.rectWidth = getWidth() / 100 * slider.getValue();
				m.rectHeight = getHeight() / 100 * slider.getValue();
				comp.repaint();
			}
		});
		south.add(slider);

		JButton fill = new JButton("ausfüllen");
		fill.addActionListener(e -> {
			m.fill = !m.fill;
			comp.repaint();
		});
		south.add(fill);

		add(BorderLayout.CENTER, comp);
		add(BorderLayout.SOUTH, south);

		pack();
		setVisible(true);
	}

	class Comp extends JComponent {
		@Override
		public void paintComponent(Graphics g) {
			if (m.fill) {
				g.setColor(Color.RED);
				g.fillRect(getWidth() / 2 - m.rectWidth / 2, getHeight() / 2 - m.rectHeight / 2, m.rectWidth, m.rectHeight);
			} else {
				g.drawRect(getWidth() / 2 - m.rectWidth / 2, getHeight() / 2 - m.rectHeight / 2, m.rectWidth, m.rectHeight);
			}
		}
	}
}

class Controller {
	Model m = new Model(500, 300);
	View v = new View(m);
}
