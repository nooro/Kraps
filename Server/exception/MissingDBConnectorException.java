package exception;

public class MissingDBConnectorException extends Exception {

	private static final long serialVersionUID = 956256884075109470L;
	
	public MissingDBConnectorException() {
		super("The database connector driver is missing.");
	}
}