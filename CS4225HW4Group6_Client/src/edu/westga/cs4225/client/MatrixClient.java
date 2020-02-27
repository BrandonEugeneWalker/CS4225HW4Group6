package edu.westga.cs4225.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import edu.westga.cs4225.model.Matrix;

public class MatrixClient {

	private Socket client;
	
	private String host;
	private int port;
	
	public MatrixClient(String host, int port) {
		if (host == null) {
			throw new IllegalArgumentException("host should not be null.");
		}
		if (port < 0) {
			throw new IllegalArgumentException("port should not be negative.");
		}
		
		this.host = host;
		this.port = port;
		this.client = null;
	}
	
	public Matrix start(Matrix firstMatrix, Matrix secondMatrix) throws IOException, ClassNotFoundException {
		Matrix matricesProduct = null;
		this.client = new Socket(this.host, this.port);
		try (ObjectOutputStream outgoing = new ObjectOutputStream(this.client.getOutputStream());
			 ObjectInputStream incoming = new ObjectInputStream(this.client.getInputStream())) {
			System.out.println("Sending First Matrix.");
			outgoing.writeObject(firstMatrix);
		} finally {
			this.client.close();
		}
		
		this.client = new Socket(this.host, this.port);
		try (ObjectOutputStream outgoing = new ObjectOutputStream(this.client.getOutputStream());
			 ObjectInputStream incoming = new ObjectInputStream(this.client.getInputStream())) {
			System.out.println("Sending Second Matrix.");
			outgoing.writeObject(secondMatrix);
			
			matricesProduct = (Matrix) incoming.readObject();
		} finally {
			this.client.close();
		}
		
		return matricesProduct;
	}
}
