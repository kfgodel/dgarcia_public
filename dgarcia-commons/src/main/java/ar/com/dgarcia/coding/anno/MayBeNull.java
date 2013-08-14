/**
 * Created on: Jun 30, 2010 12:52:28 PM by: Dario L. Garcia
 * 
 * <a rel="license" href="http://creativecommons.org/licenses/by/3.0/"><img
 * alt="Creative Commons License" style="border-width:0"
 * src="http://i.creativecommons.org/l/by/3.0/88x31.png" /></a><br />
 * <span xmlns:dct="http://purl.org/dc/terms/" href="http://purl.org/dc/dcmitype/Text"
 * property="dct:title" rel="dct:type">Agents</span> by <a xmlns:cc="http://creativecommons.org/ns#"
 * href="http://sourceforge.net/projects/agents/" property="cc:attributionName"
 * rel="cc:attributionURL">Dario Garcia</a> is licensed under a <a rel="license"
 * href="http://creativecommons.org/licenses/by/3.0/">Creative Commons Attribution 3.0 Unported
 * License</a>.<br />
 * Based on a work at <a xmlns:dct="http://purl.org/dc/terms/"
 * href="https://agents.svn.sourceforge.net/svnroot/agents"
 * rel="dct:source">agents.svn.sourceforge.net</a>.
 * 
 * Copyright (C) 2010 Dario L. Garcia
 */
package ar.com.dgarcia.coding.anno;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Este annotation es utilizado como herramienta de comunicación entre programadores en el código
 * para indicar que un valor puede ser null.<br>
 * <br>
 * - <b>En un field</b>: Indica que puede tener null y la clase no fallará en ese caso.<br>
 * - <b>En el retorno de un método</b>: Indica que el método podría retornar null y se debe tratar
 * el caso.<br>
 * - <b>En un parámetros</b>: Indica que el método acepta null como valor posible y no falla si se
 * pasa null.<br>
 * - <b>En una variable local</b>: No tiene mucho sentido pero se deja abierta la posibilidad de
 * uso, para indicar que puede ser null en caso de que sea un código complejo.
 * 
 * @author D. García
 */
@Documented
@Retention(RetentionPolicy.SOURCE)
@Target({ ElementType.FIELD, ElementType.LOCAL_VARIABLE, ElementType.PARAMETER, ElementType.METHOD })
public @interface MayBeNull {

}
