package exception;

public class BusyPortException extends Exception {
	
	private static final long serialVersionUID = -2185073123875545798L;

	public BusyPortException(int port) {
		super("Selected port (" + port + ") is already taken.");
	}
}
