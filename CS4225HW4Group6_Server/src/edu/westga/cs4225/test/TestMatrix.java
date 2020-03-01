package edu.westga.cs4225.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs4225.model.Matrix;
import edu.westga.cs4225.model.MatrixMath;

/**
 * Tests the functionality of the Matrix class and the MatrixMath class.
 * 
 * @author Brandon Walker, Kevin Flynn, Luke Whaley
 *
 */
class TestMatrix {

	@Test
	void testMatrixConstructor() {
		Matrix testMatrix = new Matrix('A', 3, 3);
		assertEquals('A', testMatrix.getMatrixName());
		assertEquals(3, testMatrix.getNumberOfColumns());
		assertEquals(3, testMatrix.getNumberOfRows());
	}

	@Test
	void testMatrixGetAndSetCell() {
		Matrix testMatrix = new Matrix('A', 3, 3);
		int setCount = 1;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				testMatrix.setMatrixCellValue(i, j, setCount);
				setCount++;
			}
		}

		int getCount = 1;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				int results = testMatrix.getMatrixCellValue(i, j);
				assertEquals(getCount, results);
				getCount++;
			}
		}
	}

	/**
	 * 3 x 3 matrix of values [1 2 3] [4 5 6] [7 8 9]
	 * 
	 * Multiplied with itself gives the results. [30 36 42] [66 81 96] [102 126 150]
	 */
	@Test
	void testMatrixMathMultiply() {
		Matrix testMatrix = new Matrix('A', 3, 3);
		int setCount = 1;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				testMatrix.setMatrixCellValue(i, j, setCount);
				setCount++;
			}
		}

		Matrix matrixC = MatrixMath.multiply(testMatrix, testMatrix);
		assertEquals('C', matrixC.getMatrixName());

		int result00 = matrixC.getMatrixCellValue(0, 0);
		int result01 = matrixC.getMatrixCellValue(0, 1);
		int result02 = matrixC.getMatrixCellValue(0, 2);
		int result10 = matrixC.getMatrixCellValue(1, 0);
		int result11 = matrixC.getMatrixCellValue(1, 1);
		int result12 = matrixC.getMatrixCellValue(1, 2);
		int result20 = matrixC.getMatrixCellValue(2, 0);
		int result21 = matrixC.getMatrixCellValue(2, 1);
		int result22 = matrixC.getMatrixCellValue(2, 2);

		assertEquals(30, result00);
		assertEquals(36, result01);
		assertEquals(42, result02);
		assertEquals(66, result10);
		assertEquals(81, result11);
		assertEquals(96, result12);
		assertEquals(102, result20);
		assertEquals(126, result21);
		assertEquals(150, result22);
	}

}
