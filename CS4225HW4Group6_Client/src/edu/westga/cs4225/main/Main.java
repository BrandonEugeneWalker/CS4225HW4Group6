package edu.westga.cs4225.main;

import edu.westga.cs4225.model.Matrix;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int r1 = 2, c1 = 3;
        int r2 = 3, c2 = 2;
        int[][] firstMatrix = { {3, -2, 5}, {3, 0, 4} };
        int[][] secondMatrix = { {2, 3}, {-9, 0}, {0, 4} };
        Matrix matrix = new Matrix(firstMatrix);
        int[][] product = matrix.multiplyMatrices(secondMatrix, r1, c1, c2);
        matrix.displayProduct(product);
	}
	
}
