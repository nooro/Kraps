package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import database.DBConnector;
import database.QueryExecutor;
import exception.LostConnectionException;
import exception.MissingDBConnectorException;
import exception.NoDBConnectionException;
import log.Logger;

public class ClientHandler extends Thread {
	
	private DataInputStream inputStream;
	private DataOutputStream outputStream;
	private String[] input;
	private String output;
	private int id;
	
	private DBConnector dbConnector;
	private QueryExecutor db;
	
	
	public ClientHandler(Socket clientSocket, int id) {
		try {
			this.outputStream = new DataOutputStream(clientSocket.getOutputStream());
			this.inputStream = new DataInputStream(clientSocket.getInputStream());
			this.id = id;
			Logger.log("New client connected with ID " + id + ". (Clients connected: " + (Server.clientsConnected.size()+1) + ")");
		} catch (IOException e) {
			Logger.log(new LostConnectionException(clientSocket.getInetAddress()));
		}
		
		try {
			this.dbConnector = new DBConnector();
			this.dbConnector.openConnection("jdbc:mysql://localhost:3306/kraps?useUnicode=yes&characterEncoding=UTF-8", "root", "");
			this.db = new QueryExecutor(dbConnector.getConnection());
		} catch (NoDBConnectionException | MissingDBConnectorException exception) {
			Logger.log(exception);
		}
	}
	
	
	public void run() {
		while(!Thread.interrupted()) {
			try {
				input = inputStream.readUTF().split("/");
				handleUserQuery(input);
			} catch (IOException exception) {
				Logger.log(exception);
			}
		}
	}
	
	
	private void handleUserQuery(String[] userQuery) {
		if(userQuery[0].equals("EXIT")) {
			disconnect();
		}
		else if(userQuery[0].equals("register")) {
			//TODO Transfer photo from the client and save it locally.
			String userDrivingLicensephotoURL = "...";
			output = db.register(userQuery, userDrivingLicensephotoURL);
			try {
				outputStream.writeUTF(output);
			} catch (IOException exception) {
				Logger.log(exception);
			}
		}
		else if(userQuery[0].equals("login")) {
			output = db.login(userQuery);
			try {
				outputStream.writeUTF(output);
			} catch (IOException exception) {
				Logger.log(exception);
			}
		}
	}
	
	
	private void disconnect() {
		Server.clientsConnected.remove(id);
		Logger.log("Client with ID " + id + " disconnected. (Clients connected: " + (Server.clientsConnected.size()) + ")");
		interrupt();
	}
	
	
}
