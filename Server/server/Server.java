package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import exception.*;
import log.Logger;

public class Server extends Thread {
	
	private ServerSocket serverSocket = null;
	private Socket clientSocket = null;
	private int activePort;
	private int newClientID;
	protected static HashMap<Integer, ClientHandler> clientsConnected;
	
	public Server(int port) throws BusyPortException {
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			serverSocket = null;
			throw new BusyPortException(port);
		}
		activePort = port;
		newClientID = 0;
		clientsConnected = new HashMap<Integer, ClientHandler>();
	}
	
	public void run() {
		Logger.log("Server started and listening at port " + activePort + ".");
		
		while(!Thread.interrupted()) {
			try {
				clientSocket = serverSocket.accept();

				if(!clientSocket.getInetAddress().toString().equals("/127.0.0.1")) {
					synchronized (clientSocket) {
						clientsConnected.put(newClientID, new ClientHandler(clientSocket, newClientID));						
						clientsConnected.get(newClientID).start();
						newClientID++;
					}
				}
			} 
			catch (IOException acceptException) {
				Logger.log("Error: Could not accept new client - " + acceptException.getMessage());
				this.stopServer();	
			}
		}
		
		this.stopServer();
	}
	
	private void stopServer() {
		
		for (HashMap.Entry<Integer, ClientHandler> client : clientsConnected.entrySet()) {
			client.getValue().interrupt();
		}
		clientsConnected.clear();
		
		try {
			serverSocket.close();
			Logger.log("Server stopped.");
		} catch (IOException exception) {
			Logger.log("Error: Could not close the server socket.");
		}
	}
	
}