package uebung06;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JComponent;
import javax.swing.JFrame;

// Aufgabe 1: 
// Optimieren Sie Ihre Implementierung zu �bung 2 Aufgabe 2, indem Sie f�r das 
// Finden von �hnlichen Farben die aus der Vorlesung vorgestellte 
// mehrdimensionale Approximation verwenden. Verwenden Sie f�r das Sortieren 
// den Quicksortoder bin�re B�ume. 

public class Aufgabe01 extends JFrame {

	final int w = 800;
	final int h = 700;

	Aufgabe01() throws Exception {
		setLayout(new GridLayout(2, 1));
		setSize(w, h);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		
		final Image image = new DateiAuswahl().bildAuswahl();

		MediaTracker mt = new MediaTracker(this);
		mt.addImage(image, 0);
		mt.waitForAll();

		add(new JComponent() {
			@Override
			public void paintComponent(Graphics g) {
				g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
			}
		});
		
		setVisible(true);
		
		ImageColorReduction icr = new ImageColorReduction();
		long time = System.currentTimeMillis();
		final Image img = icr.imageColorReduction(image, 0.8, this);
		System.out.println("das hat "+(System.currentTimeMillis()-time)+" ms gedauert...");
		
		add(new JComponent() {
			@Override
			public void paintComponent(Graphics g) {
				g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
			}
		});
		
		validate();
		repaint();
	}

	public static void main(String[] args) throws Exception {
		new Aufgabe01();
	}
}
