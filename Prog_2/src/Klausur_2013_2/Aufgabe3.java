package Klausur_2013_2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Aufgabe3 {
	public static void main(String args[]) {
		Controller c = new Controller();
	}
}

class Model {
	int m_width, m_height;
	int m_rectX, m_rectY;
	boolean m_isFilled;

	Model(int width, int height) {
		m_width = width;
		m_height = height;
	}
}

class View extends JFrame {
	Model m;
	Comp comp = new Comp();

	View(Model m) {
		this.m = m;
		setSize(m.m_width, m.m_height);
		setLayout(new BorderLayout());
		add(BorderLayout.CENTER, comp);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		JPanel panel = new JPanel(new FlowLayout());

		JSlider slider = new JSlider(0, 100, 0);
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				m.m_rectX = getWidth() / 100 * slider.getValue();
				m.m_rectY = getHeight() / 100 * slider.getValue();
				comp.repaint();
			}
		});

		JButton button = new JButton("ausfüllen");
		button.addActionListener(e -> {
			m.m_isFilled = !m.m_isFilled;
			comp.repaint();
		});

		panel.add(slider);
		panel.add(button);
		add(BorderLayout.SOUTH, panel);

		setVisible(true);
	}

	class Comp extends JComponent {
		public void paintComponent(Graphics g) {
			if (m.m_isFilled) {
				g.setColor(Color.RED);
				g.fillRect(getWidth() / 2 - m.m_rectX / 2, getHeight() / 2 - m.m_rectY / 2, m.m_rectX, m.m_rectY);
			} else {
				g.setColor(null);
				g.drawRect(getWidth() / 2 - m.m_rectX / 2, getHeight() / 2 - m.m_rectY / 2, m.m_rectX, m.m_rectY);
			}

		}
	}
}

class Controller {
	Model m;
	View v;

	Controller() {
		m = new Model(500, 400);
		v = new View(m);
	}

	public void simulate() {

	}
}