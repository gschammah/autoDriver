package ar.edu.uade.tesis_grupo13.modelo.export;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("grafo")
public class MapaXML {
	
	@XStreamAsAttribute
	private int filas;
	@XStreamAsAttribute
	private int columnas;
	@XStreamImplicit
	private List<AristaXML> aristas;
	@XStreamImplicit
	private List<VerticeXML> vertices;
	
	
	public MapaXML(ArrayList<VerticeXML> vertex, ArrayList<AristaXML> aristas, int columnas, int filas) {
		this.aristas = aristas;
		this.vertices = vertex;
		this.filas = filas;
		this.columnas = columnas;
	}


	public List<AristaXML> getAristas() {
		return aristas;
	}


	public void setAristas(List<AristaXML> aristas) {
		this.aristas = aristas;
	}


	public List<VerticeXML> getVertices() {
		return vertices;
	}


	public int getFilas() {
		return filas;
	}


	public int getColumnas() {
		return columnas;
	}


	public void setVertices(List<VerticeXML> vertices) {
		this.vertices = vertices;
	}
	
	
}
