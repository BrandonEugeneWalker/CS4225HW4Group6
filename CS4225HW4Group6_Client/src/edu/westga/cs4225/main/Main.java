package edu.westga.cs4225.main;

import java.io.File;
import java.io.IOException;

import edu.westga.cs4225.client.MatrixClient;
import edu.westga.cs4225.model.Matrix;
import edu.westga.cs4225.model.MatrixDisplay;
import edu.westga.cs4225.model.Operands;
import edu.westga.cs4225.model.UserFilePrompt;
import edu.westga.cs4225.reader.MatrixFileReader;
import edu.westga.cs4225.reader.MatrixFileSaver;

public class Main {

	private static final String HOST = "localhost";
	private static final int PORT = 4225;
	
	public static void main(String[] args) {
		File inputFile, outputFile;
		try (UserFilePrompt prompt = new UserFilePrompt()) {
			prompt.promptForInputFile();
			prompt.promptForOutputFile();
			inputFile = prompt.getInputFile();
			outputFile = prompt.getOutputFile();
		} catch (IllegalArgumentException e) {
			System.err.println(e.getMessage());
			System.exit(1);
		}
        
		MatrixFileReader reader = new MatrixFileReader();
		Operands operands = reader.ReadFile(inputFile);
        MatrixClient client = new MatrixClient(HOST, PORT);
        try {
			System.out.println("Client Starting...");
			
        	Matrix productMatrix = client.start(operands);
			String consoleDisplay = MatrixDisplay.buildMatrixConsoleFormat(productMatrix);
			System.out.println(consoleDisplay);
			String csvDisplay = MatrixDisplay.buildMatrixCsvFormat(productMatrix);
			System.out.println(csvDisplay);
			MatrixFileSaver.saveMatrix(outputFile, productMatrix);
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}
	
}
