package edu.westga.cs4225.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

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

	private Matrix[] matrices;

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
		this.matrices = new Matrix[2];
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
		int matricesIndex = 0;
		while (matricesIndex < 2) {
			this.client = this.server.accept();
			try (ObjectInputStream incoming = new ObjectInputStream(this.client.getInputStream());
					ObjectOutputStream outgoing = new ObjectOutputStream(this.client.getOutputStream())) {
				this.matrices[matricesIndex] = (Matrix) incoming.readObject();
				matricesIndex++;

				if (matricesIndex == 2) {
					Matrix matricesProduct = MatrixMath.multiply(this.matrices[0], this.matrices[1]);
					outgoing.writeObject(matricesProduct);
				}
			} finally {
				this.client.close();
			}
		}
		this.server.close();
	}
}
