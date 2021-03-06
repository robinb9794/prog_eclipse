package uebung04.aufgabe03;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JComponent;
import javax.swing.JFrame;

// Aufgabe 3: 
// Implementieren Sie in Java eine Klassenmethode 
// drawEdge(Graphics g, 
// int x1,int y1,int w1,int h1, 
// int x2,int y2,int w2,int h2) 
// die vom Rechteck (x1,y1,w1,h1) zum Rechteck (x2,y2,w2,h2) im 
// Grafikkontext geinen Pfeil zeichnet (wird f�r die Aufgaben 12, 13 und 14 
// ben�tigt). 

public class Aufgabe03 extends JFrame {

	Point point1, point2;

	Aufgabe03() {
		setSize(800, 600);

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		add(new JComponent() {
			@Override
			public void paintComponent(Graphics g) {
				if(point1!=null&&point2!=null){
					drawArrow(point1.x-50, point1.y-50, point2.x-50, point2.y-50, 50, 50, g);
				}
			}
		});
		
		setVisible(true);

		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				Aufgabe03.this.point2 = Aufgabe03.this.getMousePosition();
				repaint();
				
			}
		});

		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				Aufgabe03.this.point1 = Aufgabe03.this.getMousePosition();
				repaint();
			}
		});
	}

	public void drawArrow(int x1,int y1,int x2,int y2,int w,int h,Graphics g){
		Point [] rect1={new Point(x1,y1),new Point(x1+w,y1),new Point(x1,y1+h),new Point((x1+w),(y1+h))};
		Point [] rect2={new Point(x2,y2),new Point(x2+w,y2),new Point(x2,y2+h),new Point((x2+w),(y2+h))};
		Point start = null;
		Point end = null;
		
		g.drawRect(x1,y1,w,h);
		g.drawRect(x2,y2,w,h);
		
		double shortestDist=getWidth()*getHeight();
		
		for(int i=0;i<rect1.length;i++){
			for(int j=0;j<rect2.length;j++){
				double tempDist=rect1[i].distance(rect2[j]);
				if(tempDist<shortestDist){
					shortestDist=tempDist;
					start = rect1[i];
					end   = rect2[j];
				}
			}
		}
		g.drawLine(start.x,start.y, end.x, end.y);
		g.fillOval(end.x-5, end.y-5, 10, 10);
		
	}

	public static void main(String[] args) {
		new Aufgabe03();

	}
}
