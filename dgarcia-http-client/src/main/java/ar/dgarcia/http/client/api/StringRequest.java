package ar.dgarcia.http.client.api;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import ar.com.dgarcia.lang.strings.ToString;

/**
 * Esta clase representa un request muy simple generado con strings.<br>
 * Si existen parámetros adicionales como post, se considera un request POST
 * 
 * @author D. García
 */
public class StringRequest {

	private String baseUrl;
	public static final String baseUrl_FIELD = "baseUrl";

	private Map<String, String> postParameters;
	public static final String postParameters_FIELD = "postParameters";
	private Map<String, String> getParameters;
	public static final String getParameters_FIELD = "getParameters";

	/**
	 * Devuelve la URL usada como base para la generación de la URL GET completa. Esta URL será la
	 * parte inicial del request a la que se le agregará los parámetros adicionales GET como parte
	 * de la URL completa
	 * 
	 * @return La URL a utilizar como base
	 */
	public String getBaseUrl() {
		return baseUrl;
	}

	public void setUrl(final String url) {
		if (!url.startsWith("http")) {
			// Si después permitimos otro tipo de protocolos habría que sacar está excepción
			throw new IllegalArgumentException(
					"La URL debe empezar con http o https, sino el cliente apache no sabe cual es el esquema de: "
							+ url);
		}
		this.baseUrl = url;
	}

	public Map<String, String> getPostParameters() {
		if (postParameters == null) {
			postParameters = new LinkedHashMap<String, String>();
		}
		return postParameters;
	}

	public void setParameters(final Map<String, String> parameters) {
		this.postParameters = parameters;
	}

	public static StringRequest create(final String url) {
		final StringRequest request = new StringRequest();
		request.setUrl(url);
		return request;
	}

	public Map<String, String> getGetParameters() {
		if (getParameters == null) {
			getParameters = new LinkedHashMap<String, String>();
		}
		return getParameters;
	}

	public void setGetParameters(final Map<String, String> getParameters) {
		this.getParameters = getParameters;
	}

	public void setPostParameters(final Map<String, String> postParameters) {
		this.postParameters = postParameters;
	}

	/**
	 * Indica si este request debe ser hecho por post al ser enviado
	 * 
	 * @return true si el request tiene parámetros en el cuerpo del post, y por lo tanto no pueden
	 *         incluirse en la URL del get
	 */
	public boolean isPostRequest() {
		return !this.getPostParameters().isEmpty();
	}

	/**
	 * Devuelve la URL para usar con el método GET, utilizando los parámetros adicionales además de
	 * la URL base
	 * 
	 * @return LA URL completa para un request GET
	 */
	public String getCompleteGetUrl() {
		if (getGetParameters().isEmpty()) {
			// Si no hay parámetros adicionales, la URL completa es la base
			return this.getBaseUrl();
		}
		final String completeGetUrl = completeUrl(getBaseUrl(), getGetParameters());
		return completeGetUrl;
	}

	/**
	 * Genera una versión completa de la url pasada agregandole los parámetros indicados
	 * 
	 * @param baseUrl
	 *            La parte inicial de la URL
	 * @param additionalParams
	 *            Parámetros a agregar en la URL
	 * @return El path completo como URL, escapeando los valores pasados
	 */
	private String completeUrl(final String baseUrl, final Map<String, String> additionalParams) {
		final StringBuilder builder = new StringBuilder(baseUrl);
		boolean isFirstParameter = !baseUrl.contains(HttpProtocol.PARAMS_START_DELIMITER);
		if (isFirstParameter) {
			builder.append(HttpProtocol.PARAMS_START_DELIMITER);
		}
		final Set<Entry<String, String>> parameters = additionalParams.entrySet();
		for (final Entry<String, String> parameterEntry : parameters) {
			final String parameterName = parameterEntry.getKey();
			final String parameterValue = parameterEntry.getValue();
			if (isFirstParameter) {
				isFirstParameter = false;
			} else {
				builder.append(HttpProtocol.PARAM_DELIMITER);
			}
			builder.append(parameterName);
			builder.append(HttpProtocol.PARAM_VALUE_DELIMITER);
			builder.append(parameterValue);
		}
		return builder.toString();
	}

	/**
	 * Agrega el parámetro indicado con su valor adaptándolos para que sean parte de una URL válida
	 * (escapeando espacios por ejemplo)
	 * 
	 * @param name
	 *            El nombre del parámetro
	 * @param value
	 *            El valor del parámetro
	 */
	public void addGetParameter(final String name, final String value) {
		addEscapedParameterTo(getGetParameters(), name, value);
	}

	/**
	 * Agrega el parametro indicado en el mapa, escapeando el valor para que sea ǘalido en el
	 * request
	 * 
	 * @param mapToAddTo
	 *            EL mapa de valores en el que se agregará
	 * @param name
	 *            El nombre del parámetro
	 * @param value
	 *            El valor del parámetro
	 */
	private void addEscapedParameterTo(final Map<String, String> mapToAddTo, final String name, final String value) {
		final String normalizedName = HttpProtocol.normalize(name);
		final String normalizedValue = HttpProtocol.normalize(value);
		mapToAddTo.put(normalizedName, normalizedValue);
	}

	/**
	 * Agrega el parámetro indicado a este request en el cuerpo de parámetros post
	 * 
	 * @param name
	 *            El nombre del parámetro
	 * @param value
	 *            El valor del parámetro
	 */
	public void addPostParameter(final String name, final String value) {
		getPostParameters().put(name, value);
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	
	public String toString() {
		final ToString helper = ToString.de(this).add("completeUrl", getCompleteGetUrl());
		if (!getPostParameters().isEmpty()) {
			final Set<Entry<String, String>> postParameters = getPostParameters().entrySet();
			for (final Entry<String, String> postParameter : postParameters) {
				final String paramName = postParameter.getKey();
				final String parameterValue = postParameter.getValue();
				helper.add(paramName, parameterValue);
			}
		}
		return helper.toString();
	}
}
