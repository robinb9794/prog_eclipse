package zusatz.approximation.approximate2D;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.Comparator;

public class PointApproximation {

	boolean gefunden;

	public PointApproximation() {
		Point[] punkte = new Point[200];

		for(int i = 0; i < punkte.length; i++) {
			punkte[i] = new Point((int) (Math.random() * 1000),// Fülle das
																// Punkte [] mit
																// Random-Testwerten
					(int) (Math.random() * 1000));
		}

		// punkte[100] = new Point(205, 305);
		Point gesucht = new Point(210, 300);
		Point res = approximate2D(punkte, gesucht);

		System.out.println("gesuchter Punkt:" + gesucht.x + "|" + gesucht.y + ")");
		System.out.println("approximierter Punkt: (" + res.x + "|" + res.y + ")");
		System.out.println("mit der Distanz : " + (gesucht.distance(res)));
	}

	public Point approximate2D(Point[] punkte, Point gesucht) {

		// sortieren
		Point[] xSortiert, ySortiert;
		java.util.Arrays.sort(punkte, new Comparator<Point>() {
			@Override
			public int compare(Point o1, Point o2) {
				return ((Integer) (o1.y)).compareTo(o2.y);
			}
		});
		ySortiert = punkte.clone();
		java.util.Arrays.sort(punkte, new Comparator<Point>() {
			@Override
			public int compare(Point o1, Point o2) {
				return ((Integer) (o1.x)).compareTo(o2.x);
			}
		});
		xSortiert = punkte.clone();

		// binsearch bezüglich der X-Projektion
		double xRange, yRange;
		int[] xSortiertInt = new int[xSortiert.length];

		for(int i = 0; i < xSortiert.length; i++) {
			xSortiertInt[i] = xSortiert[i].x;
		}
		int middleX = binsearch(xSortiertInt, gesucht.x, 0, xSortiertInt.length - 1);

		if(!gefunden) {
			if(gesucht.x < xSortiertInt[middleX]) {
				double distanceLeft = Point2D.distance(xSortiert[middleX].x, xSortiert[middleX].y, gesucht.x, gesucht.y);
				double distanceRight = distanceLeft;

				if(middleX - 1 >= 0) {
					distanceRight = Point2D.distance(xSortiert[middleX - 1].x, xSortiert[middleX - 1].y, gesucht.x, gesucht.y);
				}
				if(distanceLeft <= distanceRight) {
					xRange = distanceLeft;
				}
				else {
					xRange = distanceRight;
					middleX = middleX - 1;
				}
			}
			else {
				double distanceLeft = Point2D.distance(xSortiert[middleX].x, xSortiert[middleX].y, gesucht.x, gesucht.y);
				double distanceRight = Point2D.distance(xSortiert[middleX + 1].x, xSortiert[middleX + 1].y, gesucht.x, gesucht.y);

				if(distanceLeft <= distanceRight) {
					xRange = distanceLeft;
				}
				else {
					xRange = distanceRight;
					middleX = middleX + 1;
				}
			}
		}
		else {
			xRange = Point2D.distance(xSortiert[middleX].x, xSortiert[middleX].y, gesucht.x, gesucht.y);
		}

		// binsearch bezüglich der Y-Projektion
		int[] ySortiertInt = new int[ySortiert.length];

		for(int i = 0; i < ySortiert.length; i++) {
			ySortiertInt[i] = ySortiert[i].y;
		}

		int middleY = binsearch(ySortiertInt, gesucht.y, 0, ySortiertInt.length - 1);

		if(!gefunden) {
			if(gesucht.y < ySortiertInt[middleY]) {

				double distanceLeft = Point2D.distance(ySortiert[middleY].x, ySortiert[middleY].y, gesucht.x, gesucht.y);
				double distanceRight = distanceLeft;

				if(middleY - 1 >= 0) {
					distanceRight = Point2D.distance(ySortiert[middleY - 1].x, ySortiert[middleY - 1].y, gesucht.x, gesucht.y);
				}
				if(distanceLeft <= distanceRight) {
					yRange = distanceLeft;
				}
				else {
					yRange = distanceRight;
					middleY = middleY - 1;
				}
			}
			else {
				double distanceLeft = Point2D.distance(ySortiert[middleY].x, ySortiert[middleY].y, gesucht.x, gesucht.y);
				double distanceRight = Point2D.distance(ySortiert[middleY + 1].x, ySortiert[middleY + 1].y, gesucht.x, gesucht.y);

				if(distanceLeft <= distanceRight) {
					yRange = distanceLeft;
				}
				else {
					yRange = distanceRight;
					middleY = middleY + 1;
				}
			}
		}
		else {
			yRange = Point2D.distance(ySortiert[middleY].x, ySortiert[middleY].y, gesucht.x, gesucht.y);
		}

		double startRange = 0;
		Point nearestPoint = new Point();
		if(xRange <= yRange) {
			startRange = xRange;
			nearestPoint.x = xSortiert[middleX].x;
			nearestPoint.y = xSortiert[middleX].y;
		}
		else {
			startRange = yRange;
			nearestPoint.x = ySortiert[middleY].x;
			nearestPoint.y = ySortiert[middleY].y;

		}

		double shortestRange = startRange;

		// wir laufen im x array nach rechts
		for(int i = 1; (middleX + i) < xSortiert.length; ++i) {

			if(xSortiert[middleX + i].x <= (gesucht.x + shortestRange)) {
				double distance = Point2D.distance(xSortiert[middleX + i].x, xSortiert[middleX + i].y, gesucht.x, gesucht.y);
				if(distance < shortestRange) {
					shortestRange = distance;
					nearestPoint = new Point(xSortiert[middleX + i].x, xSortiert[middleX + i].y);
				}
			}
			else
				break;
		}

		// wir laufen im x array nach links
		for(int i = 0; (middleX - i) >= 0; ++i) {
			if(xSortiert[middleX - i].x >= (gesucht.x - shortestRange)) {

				double distance = Point2D.distance(xSortiert[middleX - i].x, xSortiert[middleX - i].y, gesucht.x, gesucht.y);

				if(distance < shortestRange) {
					shortestRange = distance;
					nearestPoint = new Point(xSortiert[middleX - i].x, xSortiert[middleX - i].y);
				}
			}
			else
				break;
		}
		System.out.println(nearestPoint.x + " | " + nearestPoint.y);
		// wir laufen im y array nach rechts
		for(int i = 0; (middleY + i) < ySortiert.length; ++i) {
			if(ySortiert[middleY + i].y <= (gesucht.y + shortestRange)) {
				double distance = Point2D.distance(ySortiert[middleY + i].x, ySortiert[middleY + i].y, gesucht.y, gesucht.y);
				if(distance < shortestRange) {
					shortestRange = distance;
					nearestPoint = new Point(ySortiert[middleY + i].x, ySortiert[middleY + i].y);
				}
			}
			else
				break;
		}

		// wir laufen im y array nach links
		for(int i = 0; (middleY - i) >= 0; ++i) {
			if(ySortiert[middleY - i].y >= (ySortiert[middleY].y - shortestRange)) {
				double distance = Point2D.distance(ySortiert[middleY - i].x, ySortiert[middleY - i].y, gesucht.x, gesucht.y);
				if(distance < shortestRange) {
					shortestRange = distance;
					nearestPoint = new Point(ySortiert[middleY - i].x, ySortiert[middleY - i].y);
				}
			}
			else
				break;
		}
		System.out.println(nearestPoint.x + " | " + nearestPoint.y);
		return nearestPoint;
	}

	public int binsearch(int[] a, int gesucht, int linkerRand, int rechterRand) {
		int iL = linkerRand;
		int iR = rechterRand;
		int MIDDLE = 0;
		while(iL <= iR) {
			MIDDLE = (iL + iR) / 2;
			final int RES = Integer.compare(a[MIDDLE], gesucht);
			if(RES == 0) {
				gefunden = true;
				return MIDDLE;
			}
			else if(RES < 0)
				iL = MIDDLE + 1;
			else
				iR = MIDDLE - 1;
		}
		gefunden = false;
		return MIDDLE;
	}

	public static void main(String[] args) {
		new PointApproximation();
	}
}
