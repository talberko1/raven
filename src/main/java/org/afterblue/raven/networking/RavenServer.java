package org.afterblue.raven.networking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public abstract class RavenServer {
	protected ServerSocket server;
	protected List<Socket> connections;
	protected boolean running;

	public RavenServer(int port) throws IOException {
		server = new ServerSocket(port);
		connections = new ArrayList<Socket>();
	}

	public abstract void onStart();

	public abstract void whileRunning();

	public abstract void onClose();

	public void broadcast(Object data) throws IOException {
		for (Socket client : connections) {
			send(client, data);
		}
	}

	public void send(Socket client, Object data) throws IOException {
		ObjectOutputStream stream = new ObjectOutputStream(client.getOutputStream());
		stream.writeObject(data);
		stream.close();
	}

	public void handleNewConnection() throws IOException {
		Socket client = server.accept();
		connections.add(client);
	}

	public void handleClientDisconnected(Socket client) {
		connections.remove(client);
	}

	public Object receive(Socket client) throws IOException, ClassNotFoundException {
		ObjectInputStream stream = new ObjectInputStream(client.getInputStream());
		Object data = stream.readObject();
		stream.close();
		return data;
	}

	protected void run() throws IOException {
		onStart(); // Override
		while (running) {
			whileRunning(); // Override
		}
		onClose(); // Override
	}

	public void stop() {
		running = false;
	}
}
