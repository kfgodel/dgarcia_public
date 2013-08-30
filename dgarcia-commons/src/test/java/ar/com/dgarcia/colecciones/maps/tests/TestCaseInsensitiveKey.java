/**
 * Created on: Aug 30, 2013 1:50:17 AM by: Dario L. Garcia
 * 
 * <a rel="license" href="http://creativecommons.org/licenses/by/2.5/ar/"><img
 * alt="Creative Commons License" style="border-width:0"
 * src="http://i.creativecommons.org/l/by/2.5/ar/88x31.png" /></a><br />
 * <span xmlns:dc="http://purl.org/dc/elements/1.1/"
 * href="http://purl.org/dc/dcmitype/InteractiveResource" property="dc:title"
 * rel="dc:type">Bean2Bean</span> by <a xmlns:cc="http://creativecommons.org/ns#"
 * href="https://sourceforge.net/projects/bean2bean/" property="cc:attributionName"
 * rel="cc:attributionURL">Dar&#237;o Garc&#237;a</a> is licensed under a <a rel="license"
 * href="http://creativecommons.org/licenses/by/2.5/ar/">Creative Commons Attribution 2.5 Argentina
 * License</a>.<br />
 * Based on a work at <a xmlns:dc="http://purl.org/dc/elements/1.1/"
 * href="https://bean2bean.svn.sourceforge.net/svnroot/bean2bean"
 * rel="dc:source">bean2bean.svn.sourceforge.net</a>
 */
package ar.com.dgarcia.colecciones.maps.tests;

import junit.framework.Assert;

import org.junit.Test;

import ar.com.dgarcia.colecciones.maps.impl.CaseInsensitiveStringKey;

/**
 * Esta clase testea parte del comportamiento del {@link CaseInsensitiveStringKey}
 * 
 * @author dgarcia
 */
public class TestCaseInsensitiveKey {

	@Test
	public void deberiaDarComoIgualesClavesVacias() {
		final CaseInsensitiveStringKey clave1 = CaseInsensitiveStringKey.create(new String(""));
		final CaseInsensitiveStringKey clave2 = CaseInsensitiveStringKey.create(new String(""));
		Assert.assertEquals("Deberian ser iguales con cadenas vacias", clave1, clave2);

		final CaseInsensitiveStringKey clave3 = CaseInsensitiveStringKey.create(new String(""));
		final String clave4 = new String("");
		Assert.assertEquals("Deberian ser iguales con cadenas vacias", clave3, clave4);
	}

	@Test
	public void deberiaDarComoIgualesDosKeysAunqueTenganDistintoCase() {
		final CaseInsensitiveStringKey clave1 = CaseInsensitiveStringKey.create(new String("holaPepito"));
		final CaseInsensitiveStringKey clave2 = CaseInsensitiveStringKey.create(new String("hOlaPepIto"));
		Assert.assertEquals("Deberian ser iguales con cantidad par de caracteres", clave1, clave2);

		final CaseInsensitiveStringKey clave3 = CaseInsensitiveStringKey.create(new String("holaPepitos"));
		final CaseInsensitiveStringKey clave4 = CaseInsensitiveStringKey.create(new String("hOlaPepItoS"));
		Assert.assertEquals("Deberian ser iguales con cantidad impar de caracteres", clave3, clave4);
	}

	@Test
	public void deberiaDarComoIgualComparadoConUnStringDeDistintoCase() {
		final CaseInsensitiveStringKey clave1 = CaseInsensitiveStringKey.create(new String("holaPepito"));
		final String clave2 = new String("hOlaPepIto");
		Assert.assertEquals("Deberian ser iguales con cantidad par de caracteres", clave1, clave2);

		final CaseInsensitiveStringKey clave3 = CaseInsensitiveStringKey.create(new String("holaPepitos"));
		final String clave4 = new String("hOlaPepItoS");
		Assert.assertEquals("Deberian ser iguales con cantidad impar de caracteres", clave3, clave4);
	}
}
