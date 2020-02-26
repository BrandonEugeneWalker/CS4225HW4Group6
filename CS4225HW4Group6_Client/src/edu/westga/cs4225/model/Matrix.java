package edu.westga.cs4225.model;

import java.io.Serializable;

/**
 * The Matrix class is a representation of a 2 dimensional matrix of m rows and k columns.
 * The matrix class consists of the name of a matrix, its number of rows/columns, and its values.
 * Initially the values in a matrix are empty.
 * 
 * @author Brandon Walker, Kevin Flynn, Luke Whaley
 * @version February 26, 2020
 *
 */
public class Matrix implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private char matrixName;
	
	private int numberOfRows;
	
	private int numberOfColumns;
	
	private int[][] matrixValues;
	
	/**
	 * Creates a new instance of a Matrix class.
	 * 
	 * @precondition name must be A or B; rows and columns must be greater than zero
	 * @postcondition a new Matrix is created
	 * 
	 * @param name the name of the matrix
	 * @param rows the number of rows
	 * @param columns the number of columns
	 */
	public Matrix(char name, int rows, int columns) {
		if (name != 'A' || name != 'B') {
			throw new IllegalArgumentException("The name of a Matrix must be either A or B.");
		}
		if (rows <= 0) {
			throw new IllegalArgumentException("The number of rows in a matrix must be greater than zero.");
		}
		if (columns <= 0) {
			throw new IllegalArgumentException("The number of columns in a matrix must be greater than zero.");
		}
		this.matrixName = name;
		this.numberOfRows = rows;
		this.numberOfColumns = columns;
		this.matrixValues = new int[rows][columns];
	}

}
