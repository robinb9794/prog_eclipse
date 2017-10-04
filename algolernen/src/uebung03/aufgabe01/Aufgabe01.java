package uebung03.aufgabe01;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;

import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JRadioButtonMenuItem;

import uebung03.aufgabe01.DateiAuswahl;
import uebung03.aufgabe01.Matrix;

// Aufgabe 1: 
// Schreiben Sie ein Java Programm, das ein Bild einliest und für unterschiedliche 
// Teile des Bildes unterschiedliche Transformationen durchführt, so dass das 
// Ergebnisbild inhomogen erscheint. Verwenden Sie dazu die Matrizenrechnung. 
//  Hierzu ist eine Klasse zuerstellen, die Matrizen und Vektoren darstellen kann 
// und miteinander mittels der Matrizenmultiplikation verknüpfen kann. 
// Beispiel: unten links wird eine Translation durchgeführt, während oben rechts 
// eine Rotation, oben links eine Skalierung und rechts einex-Scherung stattfindet. 

public class Aufgabe01 extends JFrame {

	public enum Functions {
		scheren, rotieren, translation, skalieren
	}

	Point mousePosition, imagePosition = new Point(0, 0);
	Functions selectedFunction;
	int[] m_pix, m_pix_ori;
	Image imgResult;
	Matrix m;
	int w = 800, h = 600;

	public Aufgabe01() {
		// -----------------------------------------------------------------------------
		// FrameStuff
		// -----------------------------------------------------------------------------
		setSize(w, h);
		setLayout(new GridLayout());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		Image image = new DateiAuswahl().bildAuswahl().getScaledInstance(w, h, Image.SCALE_SMOOTH);

		m_pix = new int[w * h];
		m_pix_ori = new int[w * h];
		PixelGrabber p = new PixelGrabber(image, 0, 0, w, h, m_pix_ori, 0, w);
		try {
			p.grabPixels();
		} catch (Exception e) {

		}
		for (int i = 0; i < m_pix.length; ++i)
			m_pix[i] = m_pix_ori[i];
		imgResult = createImage(new MemoryImageSource(w, h, m_pix, 0, w));

		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("menu");

		JRadioButtonMenuItem scheren = new JRadioButtonMenuItem("scheren");
		JRadioButtonMenuItem rotieren = new JRadioButtonMenuItem("rotieren");
		JRadioButtonMenuItem skalieren = new JRadioButtonMenuItem("skalieren");
		JRadioButtonMenuItem translation = new JRadioButtonMenuItem(
				"translation");

		final ButtonGroup group = new ButtonGroup();

		group.add(scheren);
		group.add(rotieren);
		group.add(translation);
		group.add(skalieren);

		scheren.setSelected(true);
		selectedFunction = Functions.scheren;

		menu.add(scheren);
		menu.add(rotieren);
		menu.add(translation);
		menu.add(skalieren);
		menuBar.add(menu);
		setJMenuBar(menuBar);

		add(new JComponent() {
			@Override
			public void paintComponent(Graphics g) {
				g.drawImage(imgResult, 0, 0, getWidth(), getHeight(), this);
			}

		});

		setVisible(true);

		// -----------------------------------------------------------------------------
		// Listener
		// -----------------------------------------------------------------------------
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				switch (selectedFunction) {
				case scheren:
					newMatrix(Matrix.multiplyMM(Matrix.shearX(0.1),
							Matrix.shearY(0.1)));
					transformImage(m);

					break;
				case rotieren:
						newMatrix(Matrix.translation(-mousePosition.x, -mousePosition.y));
						newMatrix(Matrix.rotate(0.1));
						newMatrix(Matrix.translation(mousePosition.x, mousePosition.y));
						imagePosition = mousePosition;

						transformImage(m);	
					
					break;
				case translation:
					System.out.println();
					System.out.println("Bildposition|| x: " + imagePosition.x
							+ " | y: " + imagePosition.y);
					System.out.println("Mausposition|| x: " + mousePosition.x
							+ " | y: " + mousePosition.y);
					System.out.println("Verschiebung|| x: "
							+ (Math.abs(mousePosition.x) - Math
									.abs(imagePosition.x))
							+ " | y: "
							+ (Math.abs(mousePosition.y) - Math
									.abs(imagePosition.y)));

					newMatrix(Matrix.translation(
							Math.abs(mousePosition.x)
									- Math.abs(imagePosition.x),
							Math.abs(mousePosition.y)
									- Math.abs(imagePosition.y)));
					imagePosition = mousePosition;
					transformImage(m);

					break;
				case skalieren:
					newMatrix(Matrix.scale(2, 2));
					transformImage(m);

					break;
				}
			}
		});

		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				if (Aufgabe01.this.getMousePosition().x < Aufgabe01.this
						.getWidth()
						&& Aufgabe01.this.getMousePosition().y < Aufgabe01.this
								.getHeight()) {
					Aufgabe01.this.mousePosition = Aufgabe01.this
							.getMousePosition();
				}
			}
		});

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				System.exit(0);
			}
		});

		scheren.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Aufgabe01.this.selectedFunction = Functions.scheren;
			}
		});

		skalieren.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Aufgabe01.this.selectedFunction = Functions.skalieren;
			}
		});

		rotieren.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Aufgabe01.this.selectedFunction = Functions.rotieren;
			}
		});

		translation.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Aufgabe01.this.selectedFunction = Functions.translation;
			}
		});

	}

	// -----------------------------------------------------------------------------
	// Methoden
	// -----------------------------------------------------------------------------
	void newMatrix(Matrix a) {
		if (m != null)
			m = Matrix.multiplyMM(m, a);
		else
			m = a;
	}

	public void transformImage(Matrix trans) {
		for (int i =0 ; i < w; ++i) {
			for (int j = 0; j < h; ++j) {
				Vector k = new Vector(i, j);
				Vector v = Matrix.multiplyMV(trans, k);
				m_pix[w * j + i] = 0xffffffff;
				if (v.x() >= 0 && v.y() >= 0 && v.x() < w && v.y() < h) {
					m_pix[w * j + i] = m_pix_ori[w * v.y() + v.x()];
				}
			}
		}

		imgResult.flush();
		repaint();
	}

	// -----------------------------------------------------------------------------
	// main
	// -----------------------------------------------------------------------------
	public static void main(String[] args) {
		new Aufgabe01();
	}
}
