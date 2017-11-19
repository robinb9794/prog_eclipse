package model;

public class Matrix {
	double[][] data;

	public Matrix(double[][] data) {
		this.data = data;
	}

	public static Vector multiply(Matrix ma, Vector vec) {
		int[] res = new int[ma.data[0].length];
		for (int i = 0; i < ma.data.length; i++) {
			for (int j = 0; j < ma.data[i].length; j++) {
				res[i] += ma.data[i][j] * vec.getI(j);
			}
		}
		Vector r = new Vector(res[0], res[1]);
		return r;
	}

	public static Matrix multiply(Matrix x, Matrix y) {
		double[][] p = new double[3][3];
		for (int i = 0; i < x.data.length; ++i) {
			for (int j = 0; j < x.data[i].length; j++) {
				for (int k = 0; k < 3; k++) {
					p[i][j] += +x.data[k][j] * y.data[i][k];
				}
			}
		}
		return new Matrix(p);
	}

	public static Matrix translate(int dx, int dy) {
		double[][] dM = { { 1, 0, Math.round(-dx) }, { 0, 1, Math.round(-dy) }, { 0, 0, 1 } };
		return new Matrix(dM);

	}

	public static Matrix rotate(int a) {
		double rad = -(Math.PI * a / 180);
		double d[][] = { { Math.cos(rad), -Math.sin(rad), 0 }, { Math.sin(rad), Math.cos(rad), 0 }, { 0, 0, 1 } };
		return new Matrix(d);
	}

	public static Matrix scale(double f) {
		double[][] d = { { 1 / f, 0, 0 }, { 0, 1 / f, 0 }, { 0, 0, 1 } };
		return new Matrix(d);
	}

	public static Matrix shearX(double sX) {
		double d[][] = { { 1, -sX, 0 }, { 0, 1, 0 }, { 0, 0, 1 } };
		return new Matrix(d);
	}

	public static Matrix shearY(double sY) {
		double d[][] = { { 1, 0, 0 }, { -sY, 1, 0 }, { 0, 0, 1 } };
		return new Matrix(d);
	}

	public static void print(Matrix ma, Vector vec) {
		for (int i = 0; i < ma.data.length; i++) {
			System.out.print("|\t");
			for (int j = 0; j < ma.data[i].length; j++) {
				System.out.print(ma.data[i][j] + "\t");
			}
			System.out.println("|\t" + vec.v[i]);
		}
	}
}
