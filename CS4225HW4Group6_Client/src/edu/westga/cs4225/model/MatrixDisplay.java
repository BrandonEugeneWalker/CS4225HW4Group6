package edu.westga.cs4225.model;

/**
 * Handles building strings for displaying/writing a matrix to a file.
 * 
 * @author Brandon Walker, Kevin Flynn, Luke Whaley
 *
 */
public class MatrixDisplay {

	/**
	 * Builds a string representation of a Matrix that can be used to write to the
	 * console in a nice to look at way.
	 * 
	 * @precondition the input cannot be null.
	 * @param inputMatrix the input matrix
	 * @return the string representation of the matrix.
	 */
	public static String buildMatrixConsoleFormat(Matrix inputMatrix) {
		StringBuilder outputBuilder = new StringBuilder();
		int maxLength = MatrixDisplay.findLongestValue(inputMatrix);

		outputBuilder.append(MatrixDisplay.buildHeaderLine(inputMatrix));

		for (int i = 0; i < inputMatrix.getNumberOfRows(); i++) {
			outputBuilder.append("[");
			for (int j = 0; j < inputMatrix.getNumberOfColumns(); j++) {
				int results = inputMatrix.getMatrixCellValue(i, j);
				outputBuilder.append(MatrixDisplay.paddingNumbers(results, maxLength));
				if (j != inputMatrix.getNumberOfColumns() - 1) {
					outputBuilder.append("  ");
				}

			}
			outputBuilder.append("]" + System.lineSeparator());
		}
		return outputBuilder.toString();
	}

	/**
	 * Builds a string representation of a Matrix that can be used to write to a CSV
	 * file that the program can use.
	 * 
	 * @precondition the input cannot be null.
	 * @param inputMatrix the input matrix
	 * @return the string representation of the matrix.
	 */
	public static String buildMatrixCsvFormat(Matrix inputMatrix) {
		StringBuilder outputBuilder = new StringBuilder();
		outputBuilder.append(MatrixDisplay.buildHeaderLine(inputMatrix));

		for (int i = 0; i < inputMatrix.getNumberOfRows(); i++) {
			for (int j = 0; j < inputMatrix.getNumberOfColumns(); j++) {
				int results = inputMatrix.getMatrixCellValue(i, j);
				outputBuilder.append(results);
				if (j != inputMatrix.getNumberOfColumns() - 1) {
					outputBuilder.append(", ");
				}
			}
			outputBuilder.append(System.lineSeparator());
		}
		return outputBuilder.toString();
	}

	private static String paddingNumbers(int input, int maxLength) {
		StringBuilder outputBuilder = new StringBuilder();
		if (input == maxLength) {
			outputBuilder.append(input);
		} else {
			int inputLength = String.valueOf(input).length();
			int paddingCount = maxLength - inputLength;
			String padding = " ".repeat(paddingCount);
			outputBuilder.append(padding + input);
		}
		return outputBuilder.toString();
	}

	private static int findLongestValue(Matrix inputMatrix) {
		int maxLength = 0;
		for (int i = 0; i < inputMatrix.getNumberOfRows(); i++) {
			for (int j = 0; j < inputMatrix.getNumberOfColumns(); j++) {
				int results = inputMatrix.getMatrixCellValue(i, j);
				int resultLength = String.valueOf(results).length();
				if (resultLength > maxLength) {
					maxLength = resultLength;
				}
			}
		}
		return maxLength;
	}

	private static String buildHeaderLine(Matrix inputMatrix) {
		StringBuilder outputBuilder = new StringBuilder();
		char name = inputMatrix.getMatrixName();
		int numberOfRows = inputMatrix.getNumberOfRows();
		int numberOfColumns = inputMatrix.getNumberOfColumns();

		outputBuilder.append(name);
		outputBuilder.append(": ");
		outputBuilder.append(numberOfRows);
		outputBuilder.append(" x ");
		outputBuilder.append(numberOfColumns);
		outputBuilder.append(System.lineSeparator());
		return outputBuilder.toString();
	}

}
