/**
 * 08/07/2012 11:46:26 Copyright (C) 2011 Darío L. García
 * 
 * <a rel="license" href="http://creativecommons.org/licenses/by/3.0/"><img
 * alt="Creative Commons License" style="border-width:0"
 * src="http://i.creativecommons.org/l/by/3.0/88x31.png" /></a><br />
 * <span xmlns:dct="http://purl.org/dc/terms/" href="http://purl.org/dc/dcmitype/Text"
 * property="dct:title" rel="dct:type">Software</span> by <span
 * xmlns:cc="http://creativecommons.org/ns#" property="cc:attributionName">Darío García</span> is
 * licensed under a <a rel="license" href="http://creativecommons.org/licenses/by/3.0/">Creative
 * Commons Attribution 3.0 Unported License</a>.
 */
package ar.com.dgarcia.colecciones.maps.impl;

/**
 * Esta clase representa una key de tipo String utilizada en un mapa case-insensitive.<br>
 * Esta instancia toma un string como base para establecer relacion de igualdad con otros strings
 * sin tomar en cuenta el case
 * 
 * @author D. García
 */
public class CaseInsensitiveStringKey implements Comparable<CaseInsensitiveStringKey> {

	private String originalString;
	private String insensitiveCaseString;

	public static CaseInsensitiveStringKey create(final String unString) {
		final CaseInsensitiveStringKey name = new CaseInsensitiveStringKey();
		name.originalString = unString;
		name.insensitiveCaseString = convertToInsensitiveCaseRepresentation(unString);
		return name;
	}

	/**
	 * Crea una versión en upper case que se utilizará como base de esta instancia para las
	 * comparaciones.<br>
	 * La version en upper tendrá todas las letras sin acento en mayusculas.<br>
	 * Si la cadena pasada ya tiene todo en mayusculas, se devuelve la misma instancia.
	 * 
	 * @param unString
	 *            El texto original
	 * @return La versión insensitive
	 */
	public static String convertToInsensitiveCaseRepresentation(final String unString) {
		return toLowerCase(unString);
	}

	private static final char LIM_ST_UCASE = 'A' - 1;
	private static final char LI_N_UCASE = 'Z' + 1;
	private static final char[] LC = { '\000', '\001', '\002', '\003', '\004', '\005', '\006', '\007', '\010', '\011',
			'\012', '\013', '\014', '\015', '\016', '\017', '\020', '\021', '\022', '\023', '\024', '\025', '\026',
			'\027', '\030', '\031', '\032', '\033', '\034', '\035', '\036', '\037', '\040', '\041', '\042', '\043',
			'\044', '\045', '\046', '\047', '\050', '\051', '\052', '\053', '\054', '\055', '\056', '\057', '\060',
			'\061', '\062', '\063', '\064', '\065', '\066', '\067', '\070', '\071', '\072', '\073', '\074', '\075',
			'\076', '\077', '\100', '\141', '\142', '\143', '\144', '\145', '\146', '\147', '\150', '\151', '\152',
			'\153', '\154', '\155', '\156', '\157', '\160', '\161', '\162', '\163', '\164', '\165', '\166', '\167',
			'\170', '\171', '\172', '\133', '\134', '\135', '\136', '\137', '\140', '\141', '\142', '\143', '\144',
			'\145', '\146', '\147', '\150', '\151', '\152', '\153', '\154', '\155', '\156', '\157', '\160', '\161',
			'\162', '\163', '\164', '\165', '\166', '\167', '\170', '\171', '\172', '\173', '\174', '\175', '\176',
			'\177' };

	/**
	 * Implementacion rapida y sin localizacion para lower case tomada de
	 * http://sourceforge.net/p/faststringutil/code/106/tree/src/com/baculsoft/lang/StringUtil.java
	 * 
	 * @param s
	 * @return String.toLowerCase()
	 * @see java.lang.String#toLowerCase()
	 */
	public static final String toLowerCase(final String s) {
		char[] c = null;
		int i = s.length();
		while (i-- > 0) {
			final char c1 = s.charAt(i);
			if (c1 > LIM_ST_UCASE && c1 < LI_N_UCASE) {
				final char c2 = LC[c1];
				if (c1 != c2) {
					c = s.toCharArray();
					c[i] = c2;
					break;
				}
			}
		}
		char c1;
		while (i-- > 0) {
			c1 = c[i];
			c[i] = toLowerChar(c1);
		}
		return c == null ? s : new String(c);
	}

	/**
	 * Devuelve la version lower del caracter pasado usando la tabla
	 * 
	 * @param caracter
	 *            El caracter a obtener como lower
	 * @return El mismo char si es lower o no es alfa. El char modificado si es un alfa en upper
	 */
	public static char toLowerChar(final char caracter) {
		if (caracter > LIM_ST_UCASE && caracter < LI_N_UCASE) {
			return LC[caracter];
		}
		return caracter;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */

	@Override
	public boolean equals(final Object obj) {
		if (obj instanceof CaseInsensitiveStringKey) {
			final CaseInsensitiveStringKey that = (CaseInsensitiveStringKey) obj;
			final boolean esIgualTransformada = internalEqual(insensitiveCaseString, that.insensitiveCaseString, false);
			return esIgualTransformada;
		}
		else if (obj instanceof CharSequence) {
			final CharSequence sequence = (CharSequence) obj;
			final String comparedString = sequence.toString();
			final boolean esIgualSinCase = internalEqual(insensitiveCaseString, comparedString, true);
			return esIgualSinCase;
		}
		return false;
	}

	/**
	 * Version a medida del equals que intenta optimizar la comparacion no siguiendo un orden linea
	 * (las cadenas usadas como key suelen diferir al principio, o al final, pudiendo tener sufijos
	 * o prefijos compartidos).<br>
	 * Al comparar desde los extremos primero, se evita recorrer todo para encontrar diferencias al
	 * final (o al principio).
	 * 
	 * @param thisString
	 *            Una cadena a comparar que ya esta transformadad
	 * @param thatString
	 *            La otra cadena a comparar que tambien esta transformada
	 * @param ignoreCase
	 *            indica si se debe ignorar el case de la segunda cadena al comparar con la primera
	 * @return true si tienen los mismos chars
	 */
	private static boolean internalEqual(final String thisString, final String thatString, final boolean ignoreCase) {
		if (thisString == thatString) {
			return true;
		}
		final int longitud = thisString.length();
		if (longitud != thatString.length()) {
			return false;
		}
		// Uso dos indices para ir desde los extremos al centro
		int indiceFinal = longitud - 1;
		int indiceInicial = 0;

		while (indiceInicial < indiceFinal) {
			// Chequeamos el caracter del extremo final
			if (!internalEqualCharAt(thisString, thatString, indiceFinal, ignoreCase)) {
				// Ya sabemos que no son iguales porque difieren en el char
				return false;
			}

			// Chequeamos el caracter del extremo inicial
			if (!internalEqualCharAt(thisString, thatString, indiceInicial, ignoreCase)) {
				// Ya sabemos que no son iguales porque difieren en el char
				return false;
			}

			indiceInicial++;
			indiceFinal--;
		}
		// Si es longitud impar me va a quedar el caracter del medio por chequear
		if (indiceInicial == indiceFinal) {
			if (!internalEqualCharAt(thisString, thatString, indiceInicial, ignoreCase)) {
				// No es el mismo caracter
				return false;
			}
		}
		return true;
	}

	/**
	 * Compara el caracter del indice indicado en ambas cadenas para determinar si representan el
	 * mismo caracter
	 * 
	 * @param thisString
	 *            La cadena de referencia
	 * @param thatString
	 *            La cadena comparada
	 * @param indiceComparado
	 *            El indice del caracter a comparar
	 * @param ignoreCase
	 *            true si la segunda cadena puede pasarse a lower el caracter comparado
	 * @return true si representan el mismo caracter, false si sin distintos
	 */
	private static boolean internalEqualCharAt(final String thisString, final String thatString,
			final int indiceComparado, final boolean ignoreCase) {
		final char thisChar = thisString.charAt(indiceComparado);
		final char thatChar = thatString.charAt(indiceComparado);
		if (thisChar == thatChar) {
			// Nada maś que verificar
			return true;
		}
		// No es el mismo char
		if (!ignoreCase) {
			// No podemos ignorar la diferencia
			return false;
		}
		// Vemos si es el mismo char al pasar a lower
		final char thatLowerChar = toLowerChar(thatChar);
		// Si despues de pasar a lower, hay diferencia entonces no son iguales
		return thisChar == thatLowerChar;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */

	@Override
	public int hashCode() {
		return insensitiveCaseString.hashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */

	@Override
	public String toString() {
		return insensitiveCaseString;
	}

	public String getOriginalString() {
		return originalString;
	}

	public String getInsensitiveCaseString() {
		return insensitiveCaseString;
	}

	/**
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */

	public int compareTo(final CaseInsensitiveStringKey that) {
		final int comparacion = this.insensitiveCaseString.compareTo(that.insensitiveCaseString);
		return comparacion;
	}

}
