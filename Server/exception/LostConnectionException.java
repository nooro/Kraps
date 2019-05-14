package exception;

import java.net.InetAddress;

public class LostConnectionException extends Exception {
	
	private static final long serialVersionUID = 8792802022477750235L;
	
	public LostConnectionException(InetAddress ipAddress) {
		super("Connection with the client (" + ipAddress.toString() + ") is lost.");
	}

}