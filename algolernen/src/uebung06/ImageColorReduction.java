package uebung06;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Image;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Vector;

import zusatz.approximation.approximate3D.Point3D;

public class ImageColorReduction {
	Point3D[] xSortiert, ySortiert, zSortiert, menge;

	public Image imageColorReduction(Image image, double reductionFactor,
			Frame owner) throws Exception {
		int w = image.getWidth(owner);
		int h = image.getHeight(owner);

		int[] pixArray = new int[w * h];

		PixelGrabber pg = new PixelGrabber(image, 0, 0, w, h, pixArray, 0, w);
		pg.grabPixels();

		HashMap<Integer, ColorInfoObject> colorHashMap = new HashMap<Integer, ColorInfoObject>();

		// --------- ColorInfoObjekte einfügen
		for (int i = 0; i < pixArray.length; ++i) {
			ColorInfoObject obj = colorHashMap.get(pixArray[i]);
			if (obj == null) {
				colorHashMap.put(pixArray[i], obj = new ColorInfoObject(
						pixArray[i]));
			}
			obj.occurence.add(i);
		}

		Vector<ColorInfoObject> occuranceSort = new Vector<ColorInfoObject>(
				colorHashMap.values());

		Collections.sort(occuranceSort, new Comparator<ColorInfoObject>() {
			@Override
			public int compare(ColorInfoObject o1, ColorInfoObject o2) {
				Integer links = o1.occurence.size();
				Integer rechts = o2.occurence.size();
				return links.compareTo(rechts);
			}
		});
		final int CUT = (int) (occuranceSort.size() * reductionFactor);

		menge = new Point3D[occuranceSort.size() - CUT + 1];

		for (int i = 0; i < menge.length; ++i) {
			menge[i] = new Point3D(
					(new Color(occuranceSort.get(i + CUT - 1).color)).getRed(),
					(new Color(occuranceSort.get(i + CUT - 1).color).getGreen()),
					(new Color(occuranceSort.get(i + CUT - 1).color).getBlue()));
		}

		// sortieren
		java.util.Arrays.sort(menge, new Comparator<Point3D>() {
			@Override
			public int compare(Point3D o1, Point3D o2) {
				return ((Integer) (o1.x)).compareTo(o2.x);
			}
		});
		xSortiert = menge.clone();

		java.util.Arrays.sort(menge, new Comparator<Point3D>() {
			@Override
			public int compare(Point3D o1, Point3D o2) {
				return ((Integer) (o1.y)).compareTo(o2.y);
			}
		});
		ySortiert = menge.clone();

		java.util.Arrays.sort(menge, new Comparator<Point3D>() {
			@Override
			public int compare(Point3D o1, Point3D o2) {
				return ((Integer) (o1.z)).compareTo(o2.z);
			}
		});
		zSortiert = menge.clone();

		for (int i = 0; i <= CUT; ++i) {

			Point3D toApproximate = new Point3D(new Color(
					occuranceSort.get(i).color).getRed(), new Color(
					occuranceSort.get(i).color).getGreen(), new Color(
					occuranceSort.get(i).color).getBlue());

			Point3D res = approximate3D(toApproximate);

			occuranceSort.get(i).color = (new Color(res.x, res.y, res.z))
					.getRGB();
		}

		int[] reducedPix = new int[w * h];
		for (int i = 0; i < occuranceSort.size(); ++i) {
			for (int j = 0; j < occuranceSort.get(i).occurence.size(); ++j) {
				reducedPix[occuranceSort.get(i).occurence.get(j)] = occuranceSort
						.get(i).color;
			}
		}

		MemoryImageSource m = new MemoryImageSource(w, h, reducedPix, 0, w);
		final Image img = owner.createImage(m);
		img.flush();
		return img;
	}

	// --------------------------------------------------------------------------------
	private Point3D approximate3D(Point3D gesucht) {
		// binsearch bezüglich der X-Projektion
		double xRange, yRange, zRange;
		int[] xSortiertInt = new int[xSortiert.length];

		for (int i = 0; i < xSortiert.length; i++) {
			xSortiertInt[i] = xSortiert[i].x;
		}
		int middleX = binsearch(xSortiertInt, gesucht.x, 0,
				xSortiertInt.length - 1);

		if (!gefunden) {
			if (gesucht.x < xSortiertInt[middleX]) {
				double distanceRight = xSortiert[middleX].distance(gesucht);
				if (middleX > 0) {
					double distanceLeft = xSortiert[middleX - 1]
							.distance(gesucht);
					if (distanceLeft <= distanceRight) {
						xRange = distanceLeft;
						middleX = middleX - 1;
					} else {
						xRange = distanceRight;
					}
				} else {
					xRange = distanceRight;
				}
			} else {
				double distanceLeft = xSortiert[middleX].distance(gesucht);

				if (middleX < xSortiertInt.length - 1) {
					double distanceRight = xSortiert[middleX + 1]
							.distance(gesucht);

					if (distanceLeft <= distanceRight) {
						xRange = distanceLeft;
					} else {
						xRange = distanceRight;
						middleX = middleX + 1;
					}
				} else {
					xRange = distanceLeft;
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
			if (gesucht.y < ySortiertInt[middleY]) {
				double distanceRight = ySortiert[middleY].distance(gesucht);
				if (middleY > 0) {
					double distanceLeft = ySortiert[middleY - 1]
							.distance(gesucht);
					if (distanceLeft <= distanceRight) {
						yRange = distanceLeft;
						middleY = middleY - 1;
					} else {
						yRange = distanceRight;
					}
				} else {
					yRange = distanceRight;
				}
			} else {
				double distanceLeft = ySortiert[middleY].distance(gesucht);

				if (middleY < ySortiertInt.length - 1) {
					double distanceRight = ySortiert[middleY + 1]
							.distance(gesucht);

					if (distanceLeft <= distanceRight) {
						yRange = distanceLeft;
					} else {
						yRange = distanceRight;
						middleY = middleY + 1;
					}
				} else {
					yRange = distanceLeft;
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
			if (gesucht.z < xSortiertInt[middleZ]) {
				double distanceRight = zSortiert[middleZ].distance(gesucht);
				if (middleZ > 0) {
					double distanceLeft = zSortiert[middleZ - 1]
							.distance(gesucht);
					if (distanceLeft <= distanceRight) {
						zRange = distanceLeft;
						middleZ = middleZ - 1;
					} else {
						zRange = distanceRight;
					}
				} else {
					zRange = distanceRight;
				}
			} else {
				double distanceLeft = zSortiert[middleZ].distance(gesucht);

				if (middleZ < xSortiertInt.length - 1) {
					double distanceRight = zSortiert[middleZ + 1]
							.distance(gesucht);

					if (distanceLeft <= distanceRight) {
						zRange = distanceLeft;
					} else {
						zRange = distanceRight;
						middleZ = middleZ + 1;
					}
				} else {
					zRange = distanceLeft;
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

		double xDist = shortestRange;
		// wir laufen im x array nach rechts
		for (int i = 1; (middleX + i) < xSortiert.length; ++i) {
			if (xSortiert[middleX + i].x <= (gesucht.x + xDist)) {
				double distance = xSortiert[middleX + i].distance(gesucht);
				if (distance < xDist) {
					xDist = distance;
					nearestPoint = new Point3D(xSortiert[middleX + i].x,
							xSortiert[middleX + i].y, xSortiert[middleX + i].z);
				}
			} else {
				break;
			}

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
			} else {
				break;
			}
		}
		// // wir laufen im y array nach rechts
		// for (int i = 0; (middleY + i) < ySortiert.length; ++i) {
		// if (ySortiert[middleY + i].y <= (gesucht.y + shortestRange)) {
		// double distance = ySortiert[middleY + i].distance(gesucht);
		// if (distance < shortestRange) {
		// shortestRange = distance;
		// nearestPoint = new Point3D(ySortiert[middleY + i].x,
		// ySortiert[middleY + i].y, ySortiert[middleY + i].z);
		// }
		// } else
		// break;
		// }
		//
		// // wir laufen im y array nach links
		// for (int i = 0; (middleY - i) >= 0; ++i) {
		// if (ySortiert[middleY - i].y >= (ySortiert[middleY].y -
		// shortestRange)) {
		// double distance = ySortiert[middleY - i].distance(gesucht);
		// if (distance < shortestRange) {
		// shortestRange = distance;
		// nearestPoint = new Point3D(ySortiert[middleY - i].x,
		// ySortiert[middleY - i].y, ySortiert[middleY - i].z);
		// }
		// } else
		// break;
		// }
		// // wir laufen im z array nach rechts
		// for (int i = 0; (middleZ + i) < zSortiert.length; ++i) {
		// if (zSortiert[middleZ + i].z <= (gesucht.z + shortestRange)) {
		// double distance = zSortiert[middleZ + i].distance(gesucht);
		// if (distance < shortestRange) {
		// shortestRange = distance;
		// nearestPoint = new Point3D(zSortiert[middleZ + i].x,
		// zSortiert[middleZ + i].y, zSortiert[middleZ + i].z);
		// }
		// } else
		// break;
		// }
		//
		// // wir laufen im z array nach links
		// for (int i = 0; (middleZ - i) >= 0; ++i) {
		// if (zSortiert[middleZ - i].z >= (zSortiert[middleZ].z -
		// shortestRange)) {
		// double distance = zSortiert[middleZ - i].distance(gesucht);
		// if (distance < shortestRange) {
		// shortestRange = distance;
		// nearestPoint = new Point3D(zSortiert[middleZ - i].x,
		// zSortiert[middleZ - i].y, zSortiert[middleZ - i].z);
		// }
		// } else
		// break;
		// }
		return nearestPoint;
	}

	// ----------------------------------------------------------------------------
	boolean gefunden = false;

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
}
