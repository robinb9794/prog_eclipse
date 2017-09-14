package uebung01.aufgabe02;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.MemoryImageSource;

import javax.swing.JFrame;

// Aufgabe 2: 
// Erweitern Sie Ihr Programm aus Aufgabe 1, so dass die Mausposition im 
// Fenster eine f�nfte Farbe definiert und der Farbverlauf von den Ecken zur Maus 
// verl�uft. Der Farbverlauf soll neu berechnet werden, wenn die Maus eine neue 
// Position erh�lt (Tipp: MausMotionEvent). 

public class Aufgabe02 extends JFrame {

	int frameWidth = 600;
	int frameHeight = 600;
	int mouseRadius = 50;
	Color linksOben = Color.yellow, rechtsOben = Color.blue,
			linksUnten = Color.red, rechtsUnten = Color.green,
			mouseColor = Color.black;
	int[] pix;
	MemoryImageSource mis;
	Image img;
	Point mousePosition = new Point(0, 0);

	Aufgabe02() {
		setSize(frameWidth, frameHeight);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		paintPic();

		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				Aufgabe02.this.frameWidth = getWidth();
				Aufgabe02.this.frameHeight = getHeight();
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

	// Die Schleifen iterieren ueber jeden Pixel und errechnen die Mischfarbe
	// mit Hilfe der colorShuffle-Methode, zusaetzlich wird an der ... hier koennte ihr text stehen
	public void paintPic() {
		pix = new int[frameHeight * frameWidth];
		mis = new MemoryImageSource(frameWidth, frameHeight, pix, 0, frameWidth);
		img = createImage(mis);

		for (int y = 0; y < frameHeight; ++y) {
			for (int x = 0; x < frameWidth; ++x) {

				Point pixCoords = new Point(x, y);
				//double distance = pixCoords.distance(mousePosition);

				//if (distance > mouseRadius)
					//distance = mouseRadius;
				
					double p1 = (100/(mousePosition.x+1))*x;
					double p2 = (100/(getWidth()-mousePosition.x))*(getWidth()-x);
					double p3 = (100/getWidth())*x;
					double p4 = (100/(mousePosition.x+1))*x;
					double p5 = (100/(getWidth()-mousePosition.x))*(getWidth()-x);
					double p6 = (100/getWidth())*x;
					double p7 =	((100/getHeight())*y*mousePosition.y);
					
					int color1 = colorShuffle(linksOben.getRGB(),mouseColor.getRGB(),(int)p1);
					int color2 = colorShuffle(mouseColor.getRGB(),rechtsOben.getRGB(),(int)p2);
					int color12= colorShuffle(color1,color2,(int)p3);
					
					int color3 = colorShuffle(linksUnten.getRGB(),mouseColor.getRGB(),(int)p4);
					int color4 = colorShuffle(mouseColor.getRGB(),rechtsUnten.getRGB(),(int)p5);
					int color34= colorShuffle(color3,color4,(int)p6);
					
					int color1234 =  colorShuffle(color12,color34,(int)p7);

					
					pix[x + (frameWidth * y)] =color1234;
							
				
				
//				pix[x + (frameWidth * y)]= 
//						
//						colorShuffle(
//						colorShuffle(
//						colorShuffle(linksOben.getRGB(),mouseColor.getRGB(),mousePosition.x*100/getWidth()),
//						colorShuffle(rechtsOben.getRGB(),mouseColor.getRGB(),mousePosition.x*100/getWidth())
//						,mousePosition.x*100/getWidth()),
//						
//						colorShuffle(
//						colorShuffle(linksUnten.getRGB(),mouseColor.getRGB(),mousePosition.x*100/getWidth()),
//						colorShuffle(rechtsUnten.getRGB(),mouseColor.getRGB(),mousePosition.x*100/getWidth())
//						,mousePosition.x*100/getWidth()), mousePosition.y*100/getHeight());
//				
				

//				pix[x + (frameWidth * y)] = 
//						colorShuffle(mouseColor.getRGB(),colorShuffle(
//								
//								(colorShuffle(linksOben.getRGB(),rechtsOben.getRGB(), x * 100/ frameWidth)),
//								(colorShuffle(linksUnten.getRGB(),rechtsUnten.getRGB(), x * 100/ frameWidth))
//								
//								, y * 100/frameHeight), (int) distance * 100/ mouseRadius);
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
		new Aufgabe02();
	}
}
