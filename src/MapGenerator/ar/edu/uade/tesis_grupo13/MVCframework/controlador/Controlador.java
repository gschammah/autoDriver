/*
 * Ingenieria de Sistemas II - 1C2009
 * Marzo/2009
 * 
 * Ejercicio 14 - Persistencia simple (cliente)
 *  
 */
package ar.edu.uade.tesis_grupo13.MVCframework.controlador;

import ar.edu.uade.tesis_grupo13.MVCframework.modelo.Modelo;
import ar.edu.uade.tesis_grupo13.MVCframework.vista.Vista;


public abstract class Controlador {
	Vista vista;
	Modelo modelo;	
	
	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
		this.modelo.setVista(vista);
	}

	protected Controlador(Modelo mod, Vista vis) {
		vista = vis;		
		if (mod != null) {
			setModelo(mod);
		}
		vista.addControlador(this);
	}	
	
	public Vista getVista() {
		return vista;
	}
	
	public Modelo getModelo() {
		return modelo;
	}
	
}
