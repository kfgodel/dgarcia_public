package ar.dgarcia.http.client.api;

/**
 * Esta interfaz representa el punto de salida a internet a través de requests HTTP.<br>
 * Las implementaciones de esta interfaz dependerán de la plataforma donde se ejecute pero deben
 * encargarse de hacer llegar los requests desde el código cliente al servidor y devolver su
 * respuesta en forma síncrona a la invocación de este método
 * 
 * @author D. García
 */
public interface HttpResponseProvider {

	/**
	 * Envía el request indicado, con el método HTTP que corresponda y devuelve la respuesta del
	 * servidor o un error si se produjo un error de comunicación
	 * 
	 * @param request
	 *            El request enviado
	 * @return La respuesta recibida
	 * @throws ConnectionProblemException
	 *             Si se produce un error de conectividad que impide la comunicación
	 */
	public StringResponse sendRequest(StringRequest request) throws ConnectionProblemException;

}
