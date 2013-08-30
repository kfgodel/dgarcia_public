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
		return toUpperCase(unString);
	}

	private static final char LIM_ST_LCASE = 'a' - 1;
	private static final char LIM_N_LCASE = 'z' + 1;
	private static final char[] UC = { '\000', '\001', '\002', '\003', '\004', '\005', '\006', '\007', '\010', '\011',
			'\012', '\013', '\014', '\015', '\016', '\017', '\020', '\021', '\022', '\023', '\024', '\025', '\026',
			'\027', '\030', '\031', '\032', '\033', '\034', '\035', '\036', '\037', '\040', '\041', '\042', '\043',
			'\044', '\045', '\046', '\047', '\050', '\051', '\052', '\053', '\054', '\055', '\056', '\057', '\060',
			'\061', '\062', '\063', '\064', '\065', '\066', '\067', '\070', '\071', '\072', '\073', '\074', '\075',
			'\076', '\077', '\100', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
			'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '\133', '\134', '\135', '\136', '\137', '\140', 'A', 'B',
			'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
			'X', 'Y', 'Z', '\173', '\174', '\175', '\176', '\177' };

	/**
	 * Conversion a upper case, tomado de
	 * http://sourceforge.net/p/faststringutil/code/106/tree/src/com/baculsoft/lang/StringUtil.java
	 * 
	 * @param s
	 * @return s.toUpperCase();
	 * @see java.lang.String#toUpperCase()
	 */
	public static final String toUpperCase(final String s) {
		char[] c = null;
		int i = s.length();
		while (i-- > 0) {
			final char c1 = s.charAt(i);
			if (c1 > LIM_ST_LCASE && c1 < LIM_N_LCASE) {
				final char c2 = UC[c1];
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
			if (c1 > LIM_ST_LCASE && c1 < LIM_N_LCASE) {
				c[i] = UC[c1];
			}
		}
		return c == null ? s : new String(c);
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */

	@Override
	public boolean equals(final Object obj) {
		if (obj instanceof CaseInsensitiveStringKey) {
			final CaseInsensitiveStringKey that = (CaseInsensitiveStringKey) obj;
			final boolean esIgualTransformada = insensitiveCaseString.equals(that.insensitiveCaseString);
			return esIgualTransformada;
		}
		else if (obj instanceof CharSequence) {
			final CharSequence sequence = (CharSequence) obj;
			final String comparedString = sequence.toString();
			final boolean esIgualSinCase = insensitiveCaseString.equalsIgnoreCase(comparedString);
			return esIgualSinCase;
		}
		return false;
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
