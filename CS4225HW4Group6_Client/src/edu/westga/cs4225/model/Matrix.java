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

	private static final String THE_COLUMN_TO_SET_CANNOT_BE_GREATER_THAN_THE_COLUMN_SIZE = "The column to set cannot be greater than the column size.";

	private static final String THE_ROW_TO_SET_CANNOT_BE_GREATER_THAN_THE_ROW_SIZE = "The row to set cannot be greater than the row size.";

	private static final String THE_COLUMN_TO_SET_CANNOT_BE_NEGATIVE = "The column to set cannot be negative.";

	private static final String THE_ROW_TO_SET_CANNOT_BE_NEGATIVE = "The row to set cannot be negative.";

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
	
	
	/**
	 * Creates a new instance of a Matrix class
	 */
	public Matrix(int[][] matrix) {
		this.matrixValues = matrix;
	}
	
	/**
	 * Sets the value of the m row k column cell.
	 * 
	 * @precondition the row/column cannot be negative or greater than the size of the row/columns.
	 * @postcondition the value is set
	 * 
	 * @param mRow the row to set the value for
	 * @param kColumn the column to set the value for
	 * @param value the value to set
	 */
	public void setMatrixCellValue(int mRow, int kColumn, int value) {
		if (mRow < 0) {
			throw new IllegalArgumentException(THE_ROW_TO_SET_CANNOT_BE_NEGATIVE);
		}
		if (kColumn < 0) {
			throw new IllegalArgumentException(THE_COLUMN_TO_SET_CANNOT_BE_NEGATIVE);
		}
		if (mRow >= this.numberOfRows) {
			throw new IllegalArgumentException(THE_ROW_TO_SET_CANNOT_BE_GREATER_THAN_THE_ROW_SIZE);
		}
		if (kColumn >= this.numberOfColumns) {
			throw new IllegalArgumentException(THE_COLUMN_TO_SET_CANNOT_BE_GREATER_THAN_THE_COLUMN_SIZE);
		}
		this.matrixValues[mRow][kColumn] = value;
	}
	
	public int getMatrixCellValue(int mRow, int kColumn) {
		if (mRow < 0) {
			throw new IllegalArgumentException(THE_ROW_TO_SET_CANNOT_BE_NEGATIVE);
		}
		if (kColumn < 0) {
			throw new IllegalArgumentException(THE_COLUMN_TO_SET_CANNOT_BE_NEGATIVE);
		}
		if (mRow >= this.numberOfRows) {
			throw new IllegalArgumentException(THE_ROW_TO_SET_CANNOT_BE_GREATER_THAN_THE_ROW_SIZE);
		}
		if (kColumn >= this.numberOfColumns) {
			throw new IllegalArgumentException(THE_COLUMN_TO_SET_CANNOT_BE_GREATER_THAN_THE_COLUMN_SIZE);
		}
		return this.matrixValues[mRow][kColumn];
	}
	
	public Matrix multiplyMatrix(Matrix matrixB) {
		return null;
	}
	
	/**
	 * 
	 * @param firstMatrix
	 * @param secondMatrix
	 * @param r1
	 * @param c1
	 * @param c2
	 * @return
	 */
    public int[][] multiplyMatrices(int[][] secondMatrix, int r1, int c1, int c2) {
        int[][] product = new int[r1][c2];
        for(int i = 0; i < r1; i++) {
            for (int j = 0; j < c2; j++) {
                for (int k = 0; k < c1; k++) {
                    product[i][j] += this.matrixValues[i][k] * secondMatrix[k][j];
                }
            }
        }

        return product;
    }
    
    
    
    
    
    /**
     * Displays the product entered
     * 
     * @param product
     */
    public void displayProduct(int[][] product) {
        System.out.println("Product of two matrices is: ");
        for(int[] row : product) {
            for (int column : row) {
                System.out.print(column + "    ");
            }
            System.out.println();
        }
    }
	

}
