package edu.westga.cs4225.model;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * This class stores two Files. These files
 * are entered as filepaths by the user through
 * the command prompt. This class reports any errors
 * for invalid files.
 * 
 * @author Luke Whaley
 *
 */
public class UserFilePrompt implements AutoCloseable {
	
	private Scanner prompt;
	
	private File inputFile;
	private File outputFile;
	
	/**
	 * Creates a new UserFilePrompt object.
	 * 
	 * @precondition none
	 * @postcondition getInputFile() == null && getOutputFile() == null
	 */
	public UserFilePrompt() {
		this.inputFile = null;
		this.outputFile = null;
		this.prompt = new Scanner(System.in);
	}
	
	@Override
	public void close() {
		this.prompt.close();
	}
	
	/**
	 * Prompts the user to enter a filepath. This filepath will 
	 * be used to load the data input file.
	 * 
	 * @precondition (new File({inputfilepath}).exists() && (new File({inputfilepath}).canRead()
	 * @postcondition getInputFile() != null
	 */
	public void promptForInputFile() {
		System.out.print("Input Filepath: ");
		String filepath = this.prompt.nextLine();
		File inputFile = new File(filepath);
		if (inputFile.exists() && inputFile.canRead()) {
			this.inputFile = inputFile;
		} else {
			throw new IllegalArgumentException("Invalid Input File");
		}
	}
	
	/**
	 * Prompts the user to enter a filepath. This filepath will 
	 * be used to load the output file that the data is saved to.
	 * 
	 * @precondition none
	 * @postcondition getOutputFile() != null
	 */
	public void promptForOutputFile() {
		System.out.print("Output Filepath: ");
		String filepath = this.prompt.nextLine();
		File outputFile = new File(filepath);
		try {
			outputFile.createNewFile();
			this.outputFile = outputFile;
		} catch (IOException | SecurityException e) {
			throw new IllegalArgumentException("Invalid Output File");
		}
	}
	
	/**
	 * Gets the input file.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the input file.
	 */
	public File getInputFile() {
		return this.inputFile;
	}
	
	/**
	 * Gets the output file.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the output file.
	 */
	public File getOutputFile() {
		return this.outputFile;
	}

}
