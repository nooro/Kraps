import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import exception.BusyPortException;
import log.Logger;
import server.Server;

public class Main {
	
	private static Server server;
	private static Scanner scan;
	
	public static void main(String[] args) {
		Logger.open();
		
		startServer();
	
		scan = new Scanner(System.in);
		while(!scan.next().equals("STOP"))
			;
		scan.close();
		
		server.interrupt();
		
		try {
			new Socket("localhost", 2345).close();
			server.join();
		} catch (IOException | InterruptedException exception) {
			Logger.log(exception);
		}
		
		Logger.close();
	}
	
	private static void startServer() {
		try {
			server = new Server(2345);
			server.start();
		} 
		catch (BusyPortException exception) {
			Logger.log(exception);
		}
	}
	
}