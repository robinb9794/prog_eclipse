package zusatz.approximation.approximate3D;


import java.util.Comparator;

public class Approximate3D {
	boolean gefunden;

	public Approximate3D() {
		Point3D[] punkte = new Point3D[3];

//		for (int i = 0; i < punkte.length; i++) {
//			punkte[i] = new Point3D((int) (Math.random() * 1000),// Fülle das
//																	// Punkte []
//																	// mit
//																	// Random-Testwerten
//					(int) (Math.random() * 1000), (int) (Math.random() * 1000));
//		}
//		punkte[punkte.length-1] = new Point3D(2, 3, 10);
//		System.out.println(punkte[punkte.length-1]);
		
		punkte[0] = new Point3D(1,0, 0);
		punkte[1]= new Point3D(0, 5, 0);
		punkte[2] = new Point3D(0, 0, 20);
		
		Point3D gesucht = new Point3D(1, 5, 10);
		Point3D res = approximate3D(punkte, gesucht);

		System.out.println("gesuchter Punkt:" + gesucht.x + "|" + gesucht.y
				+ "|" + gesucht.z);
		System.out.println("approximierter Punkt: (" + res.x + "|" + res.y
				+ "|" + res.z + ")");
		System.out.println("mit der Distanz : " + (gesucht.distance(res)));
	}

	public Point3D approximate3D(Point3D[] punkte, Point3D gesucht) {

		// sortieren
		Point3D[] xSortiert, ySortiert, zSortiert;
		java.util.Arrays.sort(punkte, new Comparator<Point3D>() {
			@Override
			public int compare(Point3D o1, Point3D o2) {
				return ((Integer) (o1.x)).compareTo(o2.x);
			}
		});
		xSortiert = punkte.clone();

		java.util.Arrays.sort(punkte, new Comparator<Point3D>() {
			@Override
			public int compare(Point3D o1, Point3D o2) {
				return ((Integer) (o1.y)).compareTo(o2.y);
			}
		});
		ySortiert = punkte.clone();

		java.util.Arrays.sort(punkte, new Comparator<Point3D>() {
			@Override
			public int compare(Point3D o1, Point3D o2) {
				return ((Integer) (o1.z)).compareTo(o2.z);
			}
		});
		zSortiert = punkte.clone();

		// binsearch bezüglich der X-Projektion
		double xRange, yRange, zRange;
		int[] xSortiertInt = new int[xSortiert.length];

		for (int i = 0; i < xSortiert.length; i++) {
			xSortiertInt[i] = xSortiert[i].x;
		}
		int middleX = binsearch(xSortiertInt, gesucht.x, 0,
				xSortiertInt.length - 1);

		if (!gefunden) {
			System.out.println("x nich gefunden");
			if (gesucht.x < xSortiertInt[middleX]) {
				double distanceLeft = xSortiert[middleX].distance(gesucht);
				double distanceRight = distanceLeft;

				if (middleX - 1 >= 0) {
					distanceRight = xSortiert[middleX - 1].distance(gesucht);
				}
				if (distanceLeft <= distanceRight) {
					xRange = distanceLeft;
				} else {
					xRange = distanceRight;
					middleX = middleX - 1;
				}
			} else {
				double distanceLeft = xSortiert[middleX].distance(gesucht);
				double distanceRight = xSortiert[middleX + 1].distance(gesucht);

				if (distanceLeft <= distanceRight) {
					xRange = distanceLeft;
				} else {
					xRange = distanceRight;
					middleX = middleX + 1;
				}
			}
		} else {
			xRange = xSortiert[middleX].distance(gesucht);
		}

		// binsearch bezüglich der Y-Projektion
		int[] ySortiertInt = new int[ySortiert.length];

		for (int i = 0; i < ySortiert.length; i++) {
			ySortiertInt[i] = ySortiert[i].y;
		}

		int middleY = binsearch(ySortiertInt, gesucht.y, 0,
				ySortiertInt.length - 1);

		if (!gefunden) {
			System.out.println("y nich gefunden");
			if (gesucht.y < ySortiertInt[middleY]) {

				double distanceLeft = ySortiert[middleY].distance(gesucht);
				double distanceRight = distanceLeft;

				if (middleY - 1 >= 0) {
					distanceRight = ySortiert[middleY - 1].distance(gesucht);
				}
				if (distanceLeft <= distanceRight) {
					yRange = distanceLeft;
				} else {
					yRange = distanceRight;
					middleY = middleY - 1;
				}
			} else {
				double distanceLeft = ySortiert[middleY].distance(gesucht);
				double distanceRight = ySortiert[middleY + 1].distance(gesucht);

				if (distanceLeft <= distanceRight) {
					yRange = distanceLeft;
				} else {
					yRange = distanceRight;
					middleY = middleY + 1;
				}
			}
		} else {
			yRange = ySortiert[middleY].distance(gesucht);
		}

		// binsearch bezüglich der Z-Projektion
		int[] zSortiertInt = new int[zSortiert.length];

		for (int i = 0; i < zSortiert.length; i++) {
			zSortiertInt[i] = zSortiert[i].z;
		}
		int middleZ = binsearch(zSortiertInt, gesucht.z, 0,
				zSortiertInt.length - 1);

		if (!gefunden) {
			System.out.println("z nich gefunden");
			if (gesucht.z < zSortiertInt[middleZ]) {
				double distanceLeft = zSortiert[middleZ].distance(gesucht);
				double distanceRight = distanceLeft;

				if (middleZ - 1 >= 0) {
					distanceRight = zSortiert[middleZ - 1].distance(gesucht);
				}
				if (distanceLeft <= distanceRight) {
					zRange = distanceLeft;
				} else {
					zRange = distanceRight;
					middleZ = middleZ - 1;
				}
			} else {
				double distanceLeft = zSortiert[middleZ].distance(gesucht);
				double distanceRight = zSortiert[middleZ + 1].distance(gesucht);

				if (distanceLeft <= distanceRight) {
					zRange = distanceLeft;
				} else {
					zRange = distanceRight;
					middleZ = middleZ + 1;
				}
			}
		} else {
			zRange = zSortiert[middleZ].distance(gesucht);
		}

		double startRange = 0;
		Point3D nearestPoint = new Point3D();

		if (xRange < yRange) {
			startRange = xRange;
			nearestPoint.x = xSortiert[middleX].x;
			nearestPoint.y = xSortiert[middleX].y;
			nearestPoint.z = xSortiert[middleX].z;
		} else {
			startRange = yRange;
			nearestPoint.x = ySortiert[middleY].x;
			nearestPoint.y = ySortiert[middleY].y;
			nearestPoint.z = ySortiert[middleY].z;
		}
		if (zRange < startRange) {
			startRange = zRange;
			nearestPoint.x = zSortiert[middleZ].x;
			nearestPoint.y = zSortiert[middleZ].y;
			nearestPoint.z = zSortiert[middleZ].z;
		}

		double shortestRange = startRange;
		System.out.println(startRange);
		
		
		
		double xDist=shortestRange;
		// wir laufen im x array nach rechts
		for (int i = 1; (middleX + i) < xSortiert.length; ++i) {
			if (xSortiert[middleX + i].x <= (gesucht.x + xDist)) {
				double distance = xSortiert[middleX + i].distance(gesucht);
				if (distance < xDist) {
					xDist = distance;
					nearestPoint = new Point3D(xSortiert[middleX + i].x,
							xSortiert[middleX + i].y, xSortiert[middleX + i].z);
				}
			} else
				break;
		}
		
		// wir laufen im x array nach links
		for (int i = 0; (middleX - i) >= 0; ++i) {
			if (xSortiert[middleX - i].x >= (gesucht.x - shortestRange)) {

				double distance = xSortiert[middleX - i].distance(gesucht);

				if (distance < shortestRange) {
					shortestRange = distance;
					nearestPoint = new Point3D(xSortiert[middleX - i].x,
							xSortiert[middleX - i].y, xSortiert[middleX - i].z);
				}
			} else
				break;
		}
		System.out.println("nearest Punkt nach x :" + nearestPoint.x + "|" + nearestPoint.y
				+ "|" + nearestPoint.z);
		// wir laufen im y array nach rechts
		for (int i = 0; (middleY + i) < ySortiert.length; ++i) {
			if (ySortiert[middleY + i].y <= (gesucht.y + shortestRange)) {
				double distance = ySortiert[middleY + i].distance(gesucht);
				if (distance < shortestRange) {
					shortestRange = distance;
					nearestPoint = new Point3D(ySortiert[middleY + i].x,
							ySortiert[middleY + i].y, ySortiert[middleY + i].z);
				}
			} else
				break;
		}

		// wir laufen im y array nach links
		for (int i = 0; (middleY - i) >= 0; ++i) {
			if (ySortiert[middleY - i].y >= (ySortiert[middleY].y - shortestRange)) {
				double distance = ySortiert[middleY - i].distance(gesucht);
				if (distance < shortestRange) {
					shortestRange = distance;
					nearestPoint = new Point3D(ySortiert[middleY - i].x,
							ySortiert[middleY - i].y, ySortiert[middleY - i].z);
				}
			} else
				break;
		}
		System.out.println("nearest Punkt nach y :" + nearestPoint.x + "|" + nearestPoint.y
				+ "|" + nearestPoint.z);
		// wir laufen im z array nach rechts
		for (int i = 0; (middleZ + i) < zSortiert.length; ++i) {
			if (zSortiert[middleZ + i].z <= (gesucht.z + shortestRange)) {
				double distance = zSortiert[middleZ + i].distance(gesucht);
				if (distance < shortestRange) {
					shortestRange = distance;
					nearestPoint = new Point3D(zSortiert[middleZ + i].x,
							zSortiert[middleZ + i].y, zSortiert[middleZ + i].z);
				}
			} else
				break;
		}

		// wir laufen im z array nach links
		for (int i = 0; (middleZ - i) >= 0; ++i) {
			if (zSortiert[middleZ - i].z >= (zSortiert[middleZ].z - shortestRange)) {
				double distance = zSortiert[middleZ - i].distance(gesucht);
				if (distance < shortestRange) {
					shortestRange = distance;
					nearestPoint = new Point3D(zSortiert[middleZ - i].x,
							zSortiert[middleZ - i].y, zSortiert[middleZ - i].z);
				}
			} else
				break;
		}
		System.out.println("nearest Punkt nach z :" + nearestPoint.x + "|" + nearestPoint.y
				+ "|" + nearestPoint.z);
		return nearestPoint;
	}

	public int binsearch(int[] a, int gesucht, int linkerRand, int rechterRand) {
		int iL = linkerRand;
		int iR = rechterRand;
		int MIDDLE = 0;
		while (iL <= iR) {
			MIDDLE = (iL + iR) / 2;
			final int RES = Integer.compare(a[MIDDLE], gesucht);
			if (RES == 0) {
				gefunden = true;
				return MIDDLE;
			} else if (RES < 0)
				iL = MIDDLE + 1;
			else
				iR = MIDDLE - 1;
		}
		gefunden = false;
		return MIDDLE;
	}

	public static void main(String[] args) {
		new Approximate3D();
	}
}
