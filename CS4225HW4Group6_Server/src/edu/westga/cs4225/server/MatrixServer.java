package edu.westga.cs4225.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The MatrixServer class directs the flow of how the server handles and
 * processed incoming data from the client.
 * 
 * @author Brandon Walker, Kevin Flynn, Luke Whaley
 *
 */
public class MatrixServer {

	private static final int NUMBER_OF_THREADS = 6;

	private ServerSocket server;
	private int port;

	private ExecutorService pool;

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
		this.port = port;
		this.pool = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
	}

	/**
	 * Starts the MatrixServer to listen for incoming transmissions.
	 * 
	 * @precondition none
	 * @postcondition the server starts
	 * 
	 * @throws IOException if an I/O error occurs.
	 */
	public void start() throws IOException {
		this.server = new ServerSocket(this.port);
		while (true) {
			Socket client = this.server.accept();
			this.pool.execute(new Response(client));
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
			this.pool.shutdown();
		} catch (IOException e) {
			System.out.println("The server is already closed.");
		}
	}
}
