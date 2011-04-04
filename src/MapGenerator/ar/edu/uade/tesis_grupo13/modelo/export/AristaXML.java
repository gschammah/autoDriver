package ar.edu.uade.tesis_grupo13.modelo.export;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("arista")
public class AristaXML {

	@XStreamAsAttribute()
	private int desde;
	@XStreamAsAttribute()
	private int hasta;
	@XStreamAsAttribute()
	private double peso;
	
	public int getDesde() {
		return desde;
	}
	
	public void setDesde(int desde) {
		this.desde = desde;
	}
	
	public int getHasta() {
		return hasta;
	}
	
	public void setHasta(int hasta) {
		this.hasta = hasta;
	}
	
	public AristaXML(int desde, int hasta, double peso) {		
		this.desde = desde;
		this.hasta = hasta;
		this.peso = peso;
	}

	public double getPeso() {
		return peso;
	}
	
	public void setPeso(double d) {
		this.peso = d;
	}
	
}
