package ar.dgarcia.http.client.api;

import ar.com.dgarcia.lang.strings.ToString;

/**
 * Esta clase representa una respuesta HTTP donde sólo importa el contenido de la misma como String.<br>
 * Adicionalmente se incluye el código de status para validaciones adicionales en la deteccion de
 * error
 * 
 * @author D. García
 */
public class StringResponse {

	private int status;
	public static final String status_FIELD = "status";
	private String content;
	public static final String content_FIELD = "content";

	/**
	 * Indica si esta respuesta fue correcta según el que responde
	 * 
	 * @return true si el status de esta respuesta es 200
	 */
	public boolean hasOkStatus() {
		return HttpProtocol.HTTP_OK_STATUS == status;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(final int status) {
		this.status = status;
	}

	public String getContent() {
		return content;
	}

	public void setContent(final String content) {
		this.content = content;
	}

	public static StringResponse create(final int status, final String responseContent) {
		final StringResponse response = new StringResponse();
		response.status = status;
		response.content = responseContent;
		return response;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	
	public String toString() {
		return ToString.de(this).add(status_FIELD, status).add(content_FIELD, content).toString();
	}
}
