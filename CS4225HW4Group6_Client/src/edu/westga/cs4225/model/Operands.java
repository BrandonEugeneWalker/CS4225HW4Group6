package edu.westga.cs4225.model;

import java.io.Serializable;

/**
 * The operands class stores the two given matrixes in a way that is easily sent over a connection.
 * @author Brandon Walker, Kevin Flynn, Luke Whaley
 *
 */
public class Operands implements Serializable {
	
	private static final long serialVersionUID = 9206457667407986520L;
	
	private Matrix leftOperand;
	private Matrix rightOperand;
	
	/**
	 * Creates a new Operands object.
	 * 
	 * @precondition leftOperand != null && rightOperand != null
	 * @postcondition getLeftOperand().equals(leftOperand) && getRightOperand().equals(rightOperand)
	 * 
	 * @param leftOperand the left operand matrix.
	 * @param rightOperand the right operand matrix.
	 */
	public Operands(Matrix leftOperand, Matrix rightOperand) {
		if (leftOperand == null) {
			throw new IllegalArgumentException("leftOperand should not be null.");
		}
		if (rightOperand == null) {
			throw new IllegalArgumentException("rightOperand should not be null.");
		}
		
		this.leftOperand = leftOperand;
		this.rightOperand = rightOperand;
	}
	
	/**
	 * Gets the left operand matrix.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the left operand matrix.
	 */
	public Matrix getLeftOperand() {
		return this.leftOperand;
	}
	
	/**
	 * Gets the right operand matrix.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @return the right operand matrix.
	 */
	public Matrix getRightOperand() {
		return this.rightOperand;
	}
}
