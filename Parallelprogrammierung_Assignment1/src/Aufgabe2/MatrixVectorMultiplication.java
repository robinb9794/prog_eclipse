package Aufgabe2;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class MatrixVectorMultiplication extends RecursiveAction {
	double[][] matrix;
	double[] vector;
	double[] result;
	int startIndex;
	int length;

	public static void main(String args[]) {

		double[][] matrix = getTestMatrix(4);
		double[] vector = getTestVector(matrix.length);
		double[] result = new double[vector.length];

		print(matrix, vector);
		System.out.println("Starting process...\n");

		int pools = 4;

		ForkJoinPool pool = new ForkJoinPool(pools);
		
		doIt(pool, matrix, vector, result);

		System.out.println("\nDone! The final result:\n");

		// result ausgeben
		for (int i = 0; i < result.length; i++) {
			System.out.println("| " + result[i] + " |");
		}
	}

	/**
	 * Zum Ausgeben der Matrix, des Vektors und der Multiplikation. Dient nur
	 * zur Übersicht.
	 * 
	 * @param matrix
	 * @param vector
	 */
	static void print(double[][] matrix, double[] vector) {
		int space = matrix.length + 1;
		System.out.println("(nur für die Übersicht) Vector * Matrix :");

		// Vektor ausgeben
		for (int i = 0; i < vector.length; i++) {
			for (int j = 0; j < space; j++) {
				System.out.print("\t");
			}
			System.out.println("| " + vector[i] + " |");
		}
		System.out.println();

		// Matrix ausgeben
		for (int i = 0; i < matrix.length; i++) {
			System.out.print("| ");
			for (int j = 0; j < matrix[i].length; j++) {
				System.out.print(matrix[i][j] + "\t");
			}
			System.out.print(" |\t");
			// Vector*Matrix ausgeben
			double res = 0;
			for (int k = 0; k < vector.length; k++) {
				res += vector[k] * matrix[i][k];
			}
			System.out.println("| " + res + " |");
			res = 0;
		}
		System.out.println("_____________________________________________\n");
	}

	static double[][] getTestMatrix(int dim) {
		double[][] matrix = new double[dim][dim];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				matrix[i][j] = (int) (Math.random() * 4 + 1);
			}
		}
		return matrix;
	}

	static double[] getTestVector(int dim) {
		double[] vector = new double[dim];
		for (int i = 0; i < vector.length; i++) {
			vector[i] = (int) (Math.random() * 3 + 1);
		}
		return vector;
	}

	static void doIt(ForkJoinPool pool, final double[][] matrix, final double[] vector, final double[] result) {

		MatrixVectorMultiplication mvm = new MatrixVectorMultiplication(matrix, vector, result, 0, matrix.length);
		pool.invoke(mvm);
	}

	public MatrixVectorMultiplication(final double[][] matrix, final double[] vector, final double[] result,
			final int startIndex, final int length) {
		this.matrix = matrix;
		this.vector = vector;
		this.result = result;
		this.startIndex = startIndex;
		this.length = length;
	}

	@Override
	/**
	 * Jeder Thread bekommt eine Zeile der Matrix (startIndex) zum
	 * Multiplizieren mit dem Vektor.
	 */
	protected void compute() {
		if ((length - startIndex) == 1) {
			System.out.println(Thread.currentThread() + " is calculating row " + startIndex + "...");
			int j = 0; // Vektor-Index
			for (int i = startIndex; i < startIndex + result.length; i++) {
				result[startIndex] += vector[j] * matrix[startIndex][j];
				++j;
			}
		} else {

			int mid = (startIndex + length) / 2; // Die Matrix wird immer wieder
													// halbiert

			MatrixVectorMultiplication left = new MatrixVectorMultiplication(matrix, vector, result, startIndex, mid);
			MatrixVectorMultiplication right = new MatrixVectorMultiplication(matrix, vector, result, mid, length);
			left.fork();
			right.fork();
			left.join();
			right.join();
		}
	}

}
