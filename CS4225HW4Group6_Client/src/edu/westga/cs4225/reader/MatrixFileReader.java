package edu.westga.cs4225.reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.westga.cs4225.model.Matrix;

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
	@SuppressWarnings("resource")
	public Matrix ReadFile(File file) {
		if (file == null) {
			throw new IllegalArgumentException("The file to read from cannot be null!");
		}
		Scanner sc;
		Matrix matrix = null;
		try {
			sc = new Scanner(file);
			int count = -1;
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				if (count == -1) {
					String[] info = line.split(" ");
					char letter = info[0].charAt(0);
					int rows = Integer.parseInt(info[1].trim());
					int collumns = Integer.parseInt(info[3].trim());
					Matrix m = new Matrix(letter, rows, collumns);
					matrix = m;
				} else {
					if (count > matrix.getNumberOfRows()) {
						return null;
					}
					int[] currMatrix = this.createIntArray(line);
					for (int i = 0; i < currMatrix.length; i++) {
						int value = currMatrix[i];
						matrix.setMatrixCellValue(count, i, value);
					}
				}
				count++;
			}
			if (count < matrix.getNumberOfRows()) {
				return null;
			}
		} catch (FileNotFoundException e) {
			return null;
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

}
