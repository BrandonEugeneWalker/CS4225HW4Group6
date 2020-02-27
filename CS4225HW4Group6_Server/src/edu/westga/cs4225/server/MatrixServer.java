package edu.westga.cs4225.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import edu.westga.cs4225.model.Matrix;
import edu.westga.cs4225.model.MatrixMath;

public class MatrixServer {
	
	private ServerSocket server;
	private Socket client;
	
	private int port;
	
	private Matrix[] matrices;
	
	public MatrixServer(int port) {
		if (port < 0) {
			throw new IllegalArgumentException("port should not be negative.");
		}
		
		this.server = null;
		this.client = null;
		this.port = port;
		this.matrices = new Matrix[2];
	}
	
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
