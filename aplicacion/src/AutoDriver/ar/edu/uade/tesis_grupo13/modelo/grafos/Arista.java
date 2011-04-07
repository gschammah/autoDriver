package ar.edu.uade.tesis_grupo13.modelo.grafos;

import ar.edu.uade.tesis_grupo13.modelo.Coordenada;


public class Arista {

	private Coordenada desde;
	private Coordenada hasta;
	private double peso;
	
	public Coordenada getDesde() {
		return desde;
	}
	
	public void setDesde(Coordenada desde) {
		this.desde = desde;
	}
	
	public Coordenada getHasta() {
		return hasta;
	}
	
	public void setHasta(Coordenada hasta) {
		this.hasta = hasta;
	}
	
	public double getPeso() {
		return peso;
	}
	
	public void setPeso(double d) {
		this.peso = d;
	}
	
}
