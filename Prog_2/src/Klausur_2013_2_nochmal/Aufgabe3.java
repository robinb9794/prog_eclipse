package Klausur_2013_2_nochmal;

import java.awt.BorderLayout;
import java.awt.Color;
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
	boolean fill;
	int sliderValue;
	int rectX = width / 2, rectY = height / 2, rectWidth = 0, rectHeight = 0;

	Model(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public void setRect() {
		rectWidth = width / 100 * sliderValue;
		rectHeight = height / 100 * sliderValue;
		rectX = width / 2 - rectWidth / 2;
		rectY = height / 2 - rectHeight / 2;
	}
}

class View extends JFrame {
	Model m;
	MyComp comp = new MyComp();

	View(Model m) {
		this.m = m;
		setSize(m.width, m.height);
		setLocationRelativeTo(null);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		setLayout(new BorderLayout());

		JPanel panel = new JPanel(new FlowLayout());

		JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				m.sliderValue = slider.getValue();
				m.setRect();
				comp.repaint();
			}
		});
		panel.add(slider);

		JButton button = new JButton("ausfüllen");
		button.addActionListener(e -> {
			m.fill = true;
			comp.repaint();
		});
		panel.add(button);

		add(BorderLayout.CENTER, comp);
		add(BorderLayout.SOUTH, panel);

		setVisible(true);

	}

	class MyComp extends JComponent {
		public void paintComponent(Graphics g) {
			if (m.fill) {
				g.setColor(Color.RED);
				g.fillRect(m.rectX, m.rectY, m.rectWidth, m.rectHeight);
			} else {
				g.drawRect(m.rectX, m.rectY, m.rectWidth, m.rectHeight);
			}
		}
	}
}

class Controller {
	Model m = new Model(500, 300);
	View v = new View(m);
}
