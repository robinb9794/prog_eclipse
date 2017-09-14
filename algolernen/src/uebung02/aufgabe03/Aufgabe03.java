package uebung02.aufgabe03;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.util.Vector;

import javax.swing.JFrame;

// Aufgabe 3: 
// Schreiben Sie ein Programm, das eine beliebigeAnzahl von Bildern einliest und 
// dann von einem Bild zu nächsten überblendet.Das letzte Bild wird in das erste 
// Bild überblendet. 

public class Aufgabe03 extends JFrame {

	Vector<Image> imageVec;
	int[] m_pix1;
	int[] m_pix2;
	int[] m_mixipixi;
	int width = 800;
	int height = 600;

	Aufgabe03(Vector<Image> imageVec) {
		this.imageVec = imageVec;
		m_pix1 = new int[width * height];
		m_pix2 = new int[width * height];
		m_mixipixi = new int[width * height];
		setSize(width, height);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		animate();
	}

	void animate() {
		while (true) {
			for (int i = 0; i < imageVec.size(); ++i) {
				
				if (i == imageVec.size()-1)
					init(imageVec.size()-1, 0);
				else
					init(i, i + 1);
				for (int j = 0; j < 100; ++j) {
					shuffle(j);
					Image img = createImage(new MemoryImageSource(width,height,m_mixipixi, 0, width));
					img.flush();
					getGraphics().drawImage(img,0,0,width, height, this);
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
					}
				}	
			}
		}
	}

	void init(int i1, int i2) {
		System.out.println(i1+" "+i2);
		PixelGrabber grab1 = new PixelGrabber(imageVec.get(i1).getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, width,
				height, m_pix1, 0, width);
		PixelGrabber grab2 = new PixelGrabber(imageVec.get(i2).getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, width,
				height, m_pix2, 0, width);
		try {
			grab1.grabPixels();
			grab2.grabPixels();
		} catch (InterruptedException e) {
		}
	}

	void shuffle(int p) {
		for (int i = 0; i < m_pix1.length; i++) {
			m_mixipixi[i]=compPix(m_pix1[i],m_pix2[i],p);
		}
	}

	private int compColor(int x1, int x2, int p) {
		return x1 + (x2 - x1) * p / 100;
	}

	private int compPix(int pix1, int pix2, int p) {
		final int RED = compColor((pix1 >> 16) & 0xff, (pix2 >> 16) & 0xff, p);
		final int GREEN = compColor((pix1 >> 8) & 0xff, (pix2 >> 8) & 0xff, p);
		final int BLUE = compColor(pix1 & 0xff, pix2 & 0xff, p);
		return 0xff000000 | (RED << 16) | (GREEN << 8) | BLUE;
	}

	public static void main(String[] args) {

		Vector<Image> imageVec = new Vector<Image>();

		imageVec.add(Toolkit.getDefaultToolkit().getImage(
				"../algolernen/src/images/pony.png"));
		imageVec.add(Toolkit.getDefaultToolkit().getImage(
				"../algolernen/src/images/1.jpg"));
		imageVec.add(Toolkit.getDefaultToolkit().getImage(
				"../algolernen/src/images/2.jpg"));
		imageVec.add(Toolkit.getDefaultToolkit().getImage(
				"../algolernen/src/images/3.jpg"));
		imageVec.add(Toolkit.getDefaultToolkit().getImage(
				"../algolernen/src/images/4.jpg"));
		imageVec.add(Toolkit.getDefaultToolkit().getImage(
				"../algolernen/src/images/5.jpg"));

		new Aufgabe03(imageVec);
	}
}
