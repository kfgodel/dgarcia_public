/**
 * 01/01/2007 21:36:19
 * Copyright (C) 2007  Dario L. Garcia
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA 
 */
package ar.com.dgarcia.spaceconf.tests.modelo;

import java.util.ArrayList;
import java.util.List;

import ar.com.dgarcia.spaceconf.SpaceConfiguration;

/**
 * 
 * @author D. Garcia 
 */
public class PublicadorResponsable implements Publicador {

	/**
	 * Publicaciones realizadas
	 */
	private List<String> publicaciones = new ArrayList<String>();
	
	/**
	 * @see ar.com.dgarcia.spaceconf.tests.modelo.Publicador#getPublicaciones()
	 */
	public List<String> getPublicaciones() {
		return this.publicaciones;
	}

	/**
	 * @see ar.com.dgarcia.spaceconf.tests.modelo.Publicador#publicar(java.lang.String)
	 */
	public void publicar(String texto) {
		EscritorAutorizador autorizador = SpaceConfiguration.getInstance().findInSameSpaceAs(this, EscritorAutorizador.class);
		if(autorizador.acepta(texto)){
			this.publicaciones.add(texto);
			System.out.println(this + ":" + texto);
		}else{
			System.out.println("Texto rechazado");
		}
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	
	public String toString() {
		return this.getClass().getSimpleName();
	}

}
