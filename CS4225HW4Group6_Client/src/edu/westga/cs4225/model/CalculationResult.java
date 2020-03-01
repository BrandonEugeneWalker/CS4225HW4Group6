package edu.westga.cs4225.model;

import java.io.Serializable;
import java.time.Duration;

/**
 * Data class that stores the information to be sent back to the client when a
 * matrix multiplication has finished.
 * 
 * @author Brandon Walker, Kevin Flynn, Luke Whaley
 *
 */
public class CalculationResult implements Serializable {

	private static final long serialVersionUID = 7204124233605083148L;

	private Matrix matrix;
	private Duration duration;

	/**
	 * Creates a new CalculationResult object.
	 * 
	 * @precondition matrix != null && duration != null
	 * @postcondition getMatrix().equals(matrix) && getDuration().equals(duration)
	 * 
	 * @param matrix   the matrix product.
	 * @param duration the time it took to calculate the matrix product.
	 */
	public CalculationResult(Matrix matrix, Duration duration) {
		if (matrix == null) {
			throw new IllegalArgumentException("matrix should not be null.");
		}
		if (duration == null) {
			throw new IllegalArgumentException("duration should not be negative.");
		}

		this.matrix = matrix;
		this.duration = duration;
	}

	/**
	 * Gets the matrix product.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the matrix product.
	 */
	public Matrix getMatrix() {
		return this.matrix;
	}

	/**
	 * Gets the duration it took for the matrix product to be calculated.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the duration it took for the matrix product to be calculated.
	 */
	public Duration getDuration() {
		return this.duration;
	}

}
