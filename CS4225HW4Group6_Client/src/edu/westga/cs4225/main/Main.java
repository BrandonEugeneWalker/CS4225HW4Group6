package edu.westga.cs4225.main;

import java.io.IOException;

import edu.westga.cs4225.client.MatrixClient;
import edu.westga.cs4225.model.Matrix;
import edu.westga.cs4225.model.MatrixDisplay;

public class Main {

	private static final String HOST = "localhost";
	private static final int PORT = 4225;
	
	public static void main(String[] args) {
		int r1 = 2, c1 = 3;
        int r2 = 3, c2 = 2;
        int[][] firstMatrix = { {3, -2, 5}, {3, 0, 4} };
        int[][] secondMatrix = { {2, 3}, {-9, 0}, {0, 4} };
        Matrix firstMatrixObject = new Matrix(firstMatrix);
        Matrix secondMatrixObject = new Matrix(secondMatrix);
        
		
		//Matrix matrixC = MatrixMath.multiply(testMatrix, testMatrix);
        
        MatrixClient client = new MatrixClient(HOST, PORT);
        try {
			System.out.println("Client Starting...");
        	Matrix productMatrix = client.start(firstMatrixObject, secondMatrixObject);
			String consoleDisplay = MatrixDisplay.buildMatrixConsoleFormat(productMatrix);
			System.out.println(consoleDisplay);
			String csvDisplay = MatrixDisplay.buildMatrixCsvFormat(productMatrix);
			System.out.println(csvDisplay);
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}
	
}
