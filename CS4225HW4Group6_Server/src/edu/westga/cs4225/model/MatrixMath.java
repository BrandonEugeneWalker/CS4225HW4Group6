package edu.westga.cs4225.model;

/**
 * 
 * This class handles math problems for Matrix objects.
 * 
 * @author Brandon Walker, Kevin Flynn, Luke Whaley
 *
 */
public class MatrixMath {

	/**
	 * Multiplies two matrixes and returns the resulting matrix.
	 * @precondition the matrixes cannot be null
	 * 
	 * @param matrixA
	 * @param matrixB
	 * @return
	 */
    public static Matrix multiply(Matrix matrixA, Matrix matrixB) {
    	if (matrixA == null) {
    		throw new IllegalArgumentException("Matrix A cannot be null!");
    	}
    	if (matrixB == null) {
    		throw new IllegalArgumentException("Matrix B cannot be null!");
    	}
    	
    	Matrix matrixC = new Matrix('C', matrixA.getNumberOfRows(), matrixB.getNumberOfColumns());
    	for (int i = 0; i < matrixA.getNumberOfRows(); i++) {
    		for (int j = 0; j < matrixB.getNumberOfColumns(); j++) {
    			for (int k = 0; k < matrixA.getNumberOfColumns(); k++) {
    				int value = matrixA.getMatrixCellValue(i, k) * matrixB.getMatrixCellValue(k, j);
    				value += matrixC.getMatrixCellValue(i, j);
    				matrixC.setMatrixCellValue(i, j, value);
    			}
    		}
    	}
    	return matrixC;
    }
    
}
