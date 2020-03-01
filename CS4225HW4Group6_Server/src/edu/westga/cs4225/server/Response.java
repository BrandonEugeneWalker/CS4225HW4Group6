package edu.westga.cs4225.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.Duration;
import java.time.Instant;

import edu.westga.cs4225.model.CalculationResult;
import edu.westga.cs4225.model.Matrix;
import edu.westga.cs4225.model.MatrixMath;
import edu.westga.cs4225.model.Operands;

/**
 * Handles the responsibility of responding to a client request.
 * 
 * @author Brandon Walker, Kevin Flynn, Luke Whaley
 *
 */
public class Response implements Runnable {

	private Socket client;

	/**
	 * Creates a new Response object for the specified client.
	 * 
	 * @precondition client != null
	 * @postcondition none
	 * 
	 * @param client the client to issue a response to.
	 */
	public Response(Socket client) {
		if (client == null) {
			throw new IllegalArgumentException("client should not be null.");
		}

		this.client = client;
	}

	/**
	 * Runs the server response.
	 * 
	 * @precondition none
	 * @postcondition the response is running
	 */
	@Override
	public void run() {
		try (ObjectInputStream incoming = new ObjectInputStream(this.client.getInputStream());
				ObjectOutputStream outgoing = new ObjectOutputStream(this.client.getOutputStream())) {
			Operands operands = (Operands) incoming.readObject();

			Matrix firstMatrix = operands.getLeftOperand();
			Matrix secondMatrix = operands.getRightOperand();

			Instant start = Instant.now();
			Matrix matricesProduct = MatrixMath.multiply(firstMatrix, secondMatrix);
			Instant end = Instant.now();

			CalculationResult result = new CalculationResult(matricesProduct, Duration.between(start, end));
			outgoing.writeObject(result);
		} catch (IOException | ClassNotFoundException e) {
			System.out.println(Thread.currentThread().getName() + ": There was an error making the socket connection.");
		} finally {
			try {
				this.client.close();
			} catch (IOException e) {
				System.out.println(Thread.currentThread().getName() + "'s client is already closed.");
			}
		}
	}

}
