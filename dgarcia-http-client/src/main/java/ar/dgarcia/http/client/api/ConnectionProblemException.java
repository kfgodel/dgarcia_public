package ar.dgarcia.http.client.api;

/**
 * Esta excepción se produce si hay algun error de IO en la comunicación con servidores externos
 * 
 * @author D. García
 */
public class ConnectionProblemException extends RuntimeException {
	private static final long serialVersionUID = 554190391285402694L;

	public ConnectionProblemException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public ConnectionProblemException(final String message) {
		super(message);
	}

	public ConnectionProblemException(final Throwable cause) {
		super(cause);
	}

}
