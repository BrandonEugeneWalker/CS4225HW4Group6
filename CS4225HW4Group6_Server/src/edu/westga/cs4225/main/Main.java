package edu.westga.cs4225.main;

import java.io.IOException;

import edu.westga.cs4225.server.MatrixServer;

public class Main {

	private static final int PORT = 4225;
	
	public static void main(String[] args) {
		MatrixServer server = new MatrixServer(PORT);
		try {
			System.out.println("Server Starting..");
			server.start();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}
}
