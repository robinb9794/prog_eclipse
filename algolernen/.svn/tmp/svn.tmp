package uebung01.aufgabe01;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.MemoryImageSource;

import javax.swing.JFrame;

// Aufgabe 1: 
// Schreiben Sie ein Java Programm, dass ein Fenster öffnet, vier unterschiedliche 
// Farben für die vier Ecken des Fensters definiert und einen Farbverlauf über die 
// vier Farben durchführt. 

public class Aufgabe01 extends JFrame {

	int frameWidth = 600;
	int frameHeight = 600;
	MemoryImageSource mis;
	Image img;
	int[] pix;

	Aufgabe01() {
		setSize(frameWidth, frameHeight);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		paintPic();

		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				Aufgabe01.this.frameWidth = getWidth();
				Aufgabe01.this.frameHeight = getHeight();
			}
		});

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				System.exit(0);
			}
		});
	}

	// Die Schleifen iterieren ueber jeden Pixel und errechnen die Mischfarbe
	// mit Hilfe der colorShuffle-Methode
	public void paintPic() {
		pix = new int[frameHeight * frameWidth];
		mis = new MemoryImageSource(frameWidth, frameHeight, pix, 0, frameWidth);
		img = createImage(mis);

		for (int y = 0; y < frameHeight; ++y) {
			for (int x = 0; x < frameWidth; ++x) {

				pix[x + (frameWidth * y)] = colorShuffle(
						(colorShuffle(Color.red.getRGB(), Color.green.getRGB(),
								x * 100 / frameWidth)),
						(colorShuffle(Color.blue.getRGB(),
								Color.yellow.getRGB(), x * 100 / frameWidth)),
						y * 100 / frameHeight);
			}
		}

		img.flush();
		getGraphics().drawImage(img, 0, 0, this);
	}

	// Die colorShuffle-Methode nutzt die singleShuffle-Methode um die
	// einzelnen Farbanteile red, green, blue aus zwei uebergebenener Farben zu
	// extrahieren, um einen Mischwert in Abhaengigkeit eines Prozentsatzes p zu
	// generieren und gibt diesen zurueck
	int colorShuffle(int i1, int i2, int p) {
		int red = singleShuffle((i1 >> 16) & 255, (i2 >> 16) & 255, p);
		int green = singleShuffle((i1 >> 8) & 255, (i2 >> 8) & 255, p);
		int blue = singleShuffle((i1) & 255, (i2) & 255, p);
		return (255 << 24) | (red << 16) | (green << 8) | blue;
	}

	// Die singleShuffle-Methode gibt den Mischwert zweier Ganzzahlen in
	// Abhaengigkeit eines Prozentsatzes zurueck
	int singleShuffle(int i1_part, int i2_part, int p) {
		return i1_part + (i2_part - i1_part) * p / 100;
	}

	@Override
	public void update(Graphics g) {
		paintPic();
	}

	@Override
	public void paint(Graphics g) {
	}

	public static void main(String[] args) {
		new Aufgabe01();
	}
}
