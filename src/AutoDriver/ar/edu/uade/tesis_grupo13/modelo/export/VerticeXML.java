package ar.edu.uade.tesis_grupo13.modelo.export;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("vertice")
public class VerticeXML {

	
	@XStreamAsAttribute()
	private int i;
	@XStreamAsAttribute()
	private int j;
	@XStreamAsAttribute()
	private double lat;
	@XStreamAsAttribute()
	private double lon;
	@XStreamAsAttribute()
	private int id;
	
	public double getLat() {
		return lat;
	}
	
	public void setLat(double lat) {
		this.lat = lat;
	}
	
	public double getLon() {
		return lon;
	}
	
	public void setLon(double lon) {
		this.lon = lon;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public VerticeXML(double lat, double lon, int id, int i, int j) {	
		this.lat = lat;
		this.lon = lon;
		this.id = id;
		this.i = i;
		this.j = j;
	}

	public int getI() {
		return i;
	}

	public int getJ() {
		return j;
	}
	
		
}
