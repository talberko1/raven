package org.afterblue.raven.networking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public abstract class RavenClient {
	private Socket socket;

	public RavenClient() {
		socket = new Socket();
	}

	public void connect(String ip, int port) {
		try {
			socket.connect(new InetSocketAddress(ip, port));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public void send(Object data) {
		try {
			ObjectOutputStream stream = new ObjectOutputStream(socket.getOutputStream());
			stream.writeObject(data);
			stream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public Object receive() {
		ObjectInputStream stream;
		Object data = null;
		try {
			stream = new ObjectInputStream(socket.getInputStream());
			data = stream.readObject();
			stream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
		}
		return data;

	}

	public void disconnect() {
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
