package Funktion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Polygon;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Funktion extends Frame {
	float range;
	int size;
	static HochZwei hz;
	static HochDrei hd;

	public Funktion(FunktionInterface fi, FunktionInterface fi2) {
		setRange(10);
		setWindowSize(600);
		setSize(size, size);
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});

		setVisible(true);
		repaint();
	}

	public void paint(Graphics g) {
		Insets ins = getInsets();
		g.drawLine(size / 2, ins.top + ins.bottom, size / 2, size - ins.bottom - ins.top);
		g.drawLine(ins.top + ins.bottom, size / 2, size - ins.top - ins.bottom, size / 2);

		Polygon p_hz = new Polygon();
		Polygon p_hd = new Polygon();

		int area = size / (2 * (int) range);
		int i = 0;
		for (float x = -range; x <= range; x += range / 10) {
			int j = (i++ * area);
			double y_hz = hz.getY(x);
			p_hz.addPoint(j, (size / 2 - (3 * (int) y_hz)));

			double y_hd = hd.getY(x);
			p_hd.addPoint(j, (size / 2 - (3 * (int) y_hd)));
		}
		g.setColor(Color.BLUE);
		g.drawPolyline(p_hz.xpoints, p_hz.ypoints, p_hz.npoints);

		g.setColor(Color.RED);
		g.drawPolyline(p_hd.xpoints, p_hd.ypoints, p_hd.npoints);
	}

	void setWindowSize(int size) {
		this.size = size;
	}

	void setRange(float f) {
		range = f;
	}

	public static void main(String args[]) {
		hz = new HochZwei();
		hd = new HochDrei();
		Funktion f = new Funktion(hz, hd);
	}

}

class HochZwei implements FunktionInterface {

	@Override
	public double getY(float x) {
		return x * x;
	}

}

class HochDrei implements FunktionInterface {

	@Override
	public double getY(float x) {
		return x * x * x;
	}

}
