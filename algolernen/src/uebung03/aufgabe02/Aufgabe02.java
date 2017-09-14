package uebung03.aufgabe02;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;

import javax.swing.JComponent;
import javax.swing.JFrame;

import uebung03.aufgabe02.DateiAuswahl;

// Aufgabe 2: 
// Schreiben Sie ein Java Programm, dass ein Bild einliest und mittels der 
// Matrizenrechnung eine Transformation des Bildes berechnet, bei dem in zwei 
// Bildpunkten Strudel wie in der Vorlesung entstehen.

public class Aufgabe02 extends JFrame {

	Point mousePosition = new Point(0, 0);
	Point swirlPoint1;
	Point swirlPoint2;
	int[] m_pix, m_pix_ori;
	Image imgResult;
	Matrix m;
	int w = 800, h = 800;

	public Aufgabe02() {

		// -----------------------------------------------------------------------------
		// FrameStuff
		// -----------------------------------------------------------------------------
		setSize(w, h);
		setLayout(new GridLayout());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		Image image = new DateiAuswahl().bildAuswahl().getScaledInstance(w, h,
				Image.SCALE_SMOOTH);

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
				if (swirlPoint1 == null){
					swirlPoint1 = mousePosition;
					System.out.println("erste position: "+swirlPoint1);
				}
				else if (swirlPoint2 == null){
					swirlPoint2 = mousePosition;
//					swirlPoint2 = new Point(w/2,h/2);
					System.out.println("zweite position: "+swirlPoint1);	
				}

				if (swirlPoint1 != null && swirlPoint2 != null) {
					System.out.println("mache whirl");
					whirl(swirlPoint1, swirlPoint2);
					swirlPoint1 = null;
					swirlPoint2 = null;
				}
			}
		});

		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				if (Aufgabe02.this.getMousePosition().x < Aufgabe02.this
						.getWidth()
						&& Aufgabe02.this.getMousePosition().y < Aufgabe02.this
								.getHeight()) {
					Aufgabe02.this.mousePosition = Aufgabe02.this
							.getMousePosition();

					mousePosition.setLocation(mousePosition.x
							- getInsets().left, mousePosition.y
							- getInsets().top);
				}
			}
		});

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				System.exit(0);
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

	public void whirl(Point whirlPoint1, Point whirlPoint2) {
		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				Point pxPos = new Point(x, y);
				double distance = pxPos.distance(whirlPoint1.x, whirlPoint1.y);
				double distance2 = pxPos.distance(whirlPoint2.x, whirlPoint2.y);

				Vector v = new Vector(x, y);
				m = null;

				newMatrix(Matrix.translation(-whirlPoint1.x, -whirlPoint1.y));
				double alpha = 2 * Math.PI / ((distance + 15) / 15);
				newMatrix(Matrix.rotate(-alpha));
				newMatrix(Matrix.translation(whirlPoint1.x, whirlPoint1.y));

				newMatrix(Matrix.translation(-whirlPoint2.x, -whirlPoint2.y));
				double alpha2 = 2 * Math.PI / ((distance2 + 15) / 15);
				newMatrix(Matrix.rotate(alpha2));
				newMatrix(Matrix.translation(whirlPoint2.x, whirlPoint2.y));

				v = Matrix.multiplyMV(m, v);

				if (v.x() >= 0 && v.y() >= 0 && v.x() < w && v.y() < h) {
					int i1 = v.y();
					int i2 = v.x();
					int d = w * i1 + i2;
					int tmp = m_pix_ori[d];
					m_pix[y * w + x] = tmp;
				}
			}
		}
		imgResult.flush();
		repaint();
	}

	public void transformImage(Matrix trans) {
		for (int i = 0; i < w; ++i) {
			for (int j = 0; j < h; ++j) {
				Vector k = new Vector(i, j);
				Vector v = Matrix.multiplyMV(trans, k);
				m_pix[w * j + i] = 0xffffffff;
				if (v.x() >= 0 && v.y() >= 0 && v.x() < w && v.y() < h) {
					int x = Math.round(v.y());
					int y = Math.round(v.x());
					m_pix[w * j + i] = m_pix_ori[w * y + x];
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
		new Aufgabe02();
	}
}
