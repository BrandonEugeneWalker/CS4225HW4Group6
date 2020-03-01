package edu.westga.cs4225.reader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import edu.westga.cs4225.model.Matrix;
import edu.westga.cs4225.model.MatrixDisplay;

/**
 * This class handles writing the results of the program to a file.
 * 
 * @author Brandon Walker, Kevin Flynn, Luke Whaley
 *
 */
public class MatrixFileSaver {

	/**
	 * Saves the results of the program to the given file.
	 * 
	 * @precondition the matrix cannot be null; the output cannot be null
	 * @param outputFile the file to write to
	 * @param matrix     the matrix results
	 * @param programStats the statistics about the operations performance
	 * @throws IOException
	 */
	public static void saveMatrixResults(File outputFile, Matrix matrix, String programStats) throws IOException {
		if (matrix == null) {
			throw new IllegalArgumentException("The matrix to save cannot be null.");
		}
		if (outputFile == null) {
			throw new IllegalArgumentException("The output file cannot be null.");
		}
		String matrixString = MatrixDisplay.buildMatrixConsoleFormat(matrix);

		try (FileWriter matrixWriter = new FileWriter(outputFile)) {
			matrixWriter.write(programStats + System.lineSeparator());
			matrixWriter.write(matrixString);
			matrixWriter.flush();
		}
	}
}
