package ar.edu.uade.tesis_grupo13.MVCframework.modelo;

import ar.edu.uade.tesis_grupo13.MVCframework.vista.Vista;

public abstract class Modelo {

	protected Vista vista = null;		

	public void setVista(Vista v) {
		vista = v;
	}

}
