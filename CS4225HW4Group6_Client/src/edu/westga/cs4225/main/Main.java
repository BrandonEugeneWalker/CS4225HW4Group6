package edu.westga.cs4225.main;

import java.io.File;
import java.io.IOException;

import edu.westga.cs4225.client.MatrixClient;
import edu.westga.cs4225.model.Matrix;
import edu.westga.cs4225.model.MatrixDisplay;
import edu.westga.cs4225.model.Operands;
import edu.westga.cs4225.reader.MatrixFileReader;
import edu.westga.cs4225.reader.MatrixFileSaver;

/**
 * The entry point into the application.
 * 
 * @author Brandon Walker, Kevin Flynn, Luke Whaley
 *
 */
public class Main {

	private static final String HOST = "localhost";
	private static final int PORT = 4225;

	/**
	 * Starts the application.
	 * 
	 * @precondition none
	 * @postcondition the program is running
	 * 
	 * @param args NOT_USED
	 */
	public static void main(String[] args) {
		File inputFile = new File(args[0]);
		File outputFile = new File(args[1]);
		if (!inputFile.exists() || !inputFile.canRead()) {
			System.err.println("Invalid Input File..");
			System.exit(1);
		}
		try {
			outputFile.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		MatrixFileReader reader = new MatrixFileReader();
		Operands operands = reader.readFile(inputFile);
		MatrixClient client = new MatrixClient(HOST, PORT);
		try {
			System.out.println("Client Starting...");

			Matrix productMatrix = client.start(operands);
			String performanceStats = client.getResults();

			System.out.println(performanceStats);
			if (productMatrix.getNumberOfColumns() <= 30) {
				String consoleDisplay = MatrixDisplay.buildMatrixConsoleFormat(productMatrix);
				System.out.println(consoleDisplay);
			}

			MatrixFileSaver.saveMatrixResults(outputFile, productMatrix, performanceStats);
			String fileOutputResults = "The results of the operation were saved at: " + System.lineSeparator()
					+ outputFile.getAbsolutePath();

		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}

}
