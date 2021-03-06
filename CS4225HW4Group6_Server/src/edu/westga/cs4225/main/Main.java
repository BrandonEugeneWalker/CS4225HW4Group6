package edu.westga.cs4225.main;

import java.io.IOException;

import edu.westga.cs4225.server.MatrixServer;

/**
 * The entry point of the server.
 * @author Brandon Walker, Kevin Flynn, Luke Whaley
 *
 */
public class Main {

	private static final int PORT = 4225;
	
	/**
	 * Starting point for the Matrix multiplication. The server
	 * can be exited with CTRL+C.
	 * 
	 * @param args the supplied program arguments.
	 */
	public static void main(String[] args) {
		MatrixServer server = new MatrixServer(PORT);
		Runtime.getRuntime().addShutdownHook(new Thread(() -> server.close()));
		try {
			System.out.println("Server Starting..");
			server.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
