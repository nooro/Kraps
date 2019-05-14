package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import exception.LostConnectionException;

public class ClientHandler extends Thread {
	
	private DataInputStream inputStream;
	private DataOutputStream outputStream;
	private String inputMessage;
	private String outputMessage;
	private Socket clientSocket;
	private int id;
	
	public ClientHandler(Socket clientSocket, int id) {
		this.clientSocket = clientSocket;
		
		try {
			this.outputStream = new DataOutputStream(clientSocket.getOutputStream());
			this.inputStream = new DataInputStream(clientSocket.getInputStream());
			this.id = id;
			Logger.log("New client connected with ID " + id + ". (Clients connected: " + (Server.clientsConnected.size()+1) + ")");
		} catch (IOException e) {
			Logger.log(new LostConnectionException(clientSocket.getInetAddress()));
		}	
	}
	
	public void run() {
		while(!Thread.interrupted()) {
			try {
				inputMessage = inputStream.readUTF();
				if(inputMessage.equals("EXIT")) {
					disconnect();
				}
				else {
					String[] splittedInput = inputMessage.split("~");
					
				}
			} catch (IOException exception) {
				Logger.log(exception);
			}
		}
	}
	
	private void disconnect() {
		Server.clientsConnected.remove(id);
		Logger.log("Client with ID " + id + " disconnected. (Clients connected: " + (Server.clientsConnected.size()+1) + ")");
		interrupt();
	}
	
	
}
