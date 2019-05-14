package exception;

public class NoDBConnectionException extends Exception {

	private static final long serialVersionUID = 4498479719241695347L;
	
	public NoDBConnectionException() {
		super("The database can not be accessed.");
	}
	
}