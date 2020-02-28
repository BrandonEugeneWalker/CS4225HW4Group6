package edu.westga.cs4225.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.Duration;
import java.time.Instant;
import java.util.LinkedList;
import java.util.Queue;

import edu.westga.cs4225.model.CalculationResult;
import edu.westga.cs4225.model.Matrix;
import edu.westga.cs4225.model.MatrixMath;

/**
 * The MatrixServer class directs the flow of how the server handles and
 * processed incoming data from the client.
 * 
 * @author Brandon Walker, Kevin Flynn, Luke Whaley
 *
 */
public class MatrixServer {

	private ServerSocket server;
	private Socket client;

	private int port;

	private Queue<Matrix> matrices;

	/**
	 * Creates a new instance of a MatrixServer class.
	 * 
	 * @precondition port cannot be negative
	 * @postcondition the MatrixServer is created.
	 * @param port the port for the server to listen on
	 */
	public MatrixServer(int port) {
		if (port < 0) {
			throw new IllegalArgumentException("port should not be negative.");
		}

		this.server = null;
		this.client = null;
		this.port = port;
		this.matrices = new LinkedList<Matrix>();
	}

	/**
	 * Starts the MatrixServer to listen for incoming transmissions.
	 * 
	 * @precondition none
	 * @postcondition the server starts
	 * 
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void start() throws IOException, ClassNotFoundException {
		this.server = new ServerSocket(this.port);
		while (true) {
			this.client = this.server.accept();
			try (ObjectInputStream incoming = new ObjectInputStream(this.client.getInputStream());
					ObjectOutputStream outgoing = new ObjectOutputStream(this.client.getOutputStream())) {
				this.matrices.add((Matrix) incoming.readObject());

				if (this.matrices.size() == 2) {
					Matrix firstMatrix = this.matrices.remove();
					Matrix secondMatrix = this.matrices.remove();
					
					Instant start = Instant.now();
					Matrix matricesProduct = MatrixMath.multiply(firstMatrix, secondMatrix);
					Instant end = Instant.now();
					
					CalculationResult result = new CalculationResult(matricesProduct, Duration.between(start, end));
					outgoing.writeObject(result);
				}
			} finally {
				this.client.close();
			}
		}
	}
	
	/**
	 * Closes this MatrixServer.
	 * 
	 * @precondition none
	 * @postcondition the server is closed.
	 */
	public void close() {
		try {
			this.server.close();
		} catch (IOException e) {
			System.out.println("The server is already closed.");
		}
	}
}
