package edu.westga.cs4225.reader;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

import edu.westga.cs4225.model.Matrix;
import edu.westga.cs4225.model.Operands;

/**
 * This class handles reading a file containing matrix data.
 * @author Brandon Walker, Kevin Flynn, Luke Whaley
 *
 */
public class MatrixFileReader {

	/**
	 * Reads the matrix data from the given file.
	 * @precondition the file cannot be null
	 * @param file the file to read from
	 * @return the matrix data as operands
	 */
	public Operands readFile(File file) {
		if (file == null) {
			throw new IllegalArgumentException("The file to read from cannot be null!");
		}
		ArrayList<Matrix> matrixCollection = new ArrayList<Matrix>();
		String allLines = MatrixFileReader.readLineByLineJava8(file.getAbsolutePath());
		String[] matrixes = allLines.split("\n\n");
		String firstLine = matrixes[0];
		matrixes[0] = firstLine + "\n";
		
		if (matrixes.length > 2) {
			throw new IllegalArgumentException("There are too many matrixes in this file");
		}
		
		for (int i = 0; i < matrixes.length; i++) {
			Matrix matrix = this.buildMatrix(matrixes[i]);
			matrixCollection.add(matrix);
		}		
		return new Operands(matrixCollection.get(0), matrixCollection.get(1));
	}

	
	private Matrix buildMatrix(String line) {
		int count = -1;
		Matrix matrix = null;
		String[] lines = line.split("[\\r\\n]+");
		for (String currLine : lines) {
			if (count == -1) {		
				String[] info = currLine.split(" ");
				char letter = info[0].charAt(0);
				int rows = Integer.parseInt(info[1].trim());
				int collumns = Integer.parseInt(info[3].trim());
				Matrix matrixValue = new Matrix(letter, rows, collumns);
				matrix = matrixValue;
				int size = lines.length - 1;
				String[] newLines = new String[size];
				System.arraycopy(lines, 1, newLines, 0, size);
				lines = newLines;
				
			} else {
				int[] currMatrix = this.createIntArray(currLine);
				if (currMatrix.length != matrix.getNumberOfColumns()) {
					throw new IllegalArgumentException("Invalid amount collumns");
				}
				
				for (int i = 0; i < currMatrix.length; i++) {
					int value = currMatrix[i];
					matrix.setMatrixCellValue(count, i, value);
				}
			}
			count++;
		}
		if (count > matrix.getNumberOfRows()) {
			throw new IllegalArgumentException("Invalid amount Rows");
		}
		
		
		return matrix;
	}
	
	private int[] createIntArray(String line) {
		String[] numberStrs = line.split(",");
		int[] numbers = new int[numberStrs.length];
		for (int i = 0; i < numberStrs.length; i++) {
			numbers[i] = Integer.parseInt(numberStrs[i].trim());
		}
		return numbers;
	}
	
	

	private static String readLineByLineJava8(String filePath) {
		StringBuilder contentBuilder = new StringBuilder();
		try (Stream<String> stream = Files.lines(Paths.get(filePath), StandardCharsets.UTF_8)) {
			stream.forEach(s -> contentBuilder.append(s).append("\n"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return contentBuilder.toString();
	}
	
	

}
