package ar.edu.uade.tesis_grupo13.vistas.imagenes;

import java.awt.image.BufferedImage;
import java.util.HashMap;

import ar.edu.uade.tesis_grupo13.modelo.Mapa;
import ar.edu.uade.tesis_grupo13.vistas.imagenes.componentes.Bordes;
import ar.edu.uade.tesis_grupo13.vistas.imagenes.componentes.Grafo;
import ar.edu.uade.tesis_grupo13.vistas.imagenes.componentes.Grilla;
import ar.edu.uade.tesis_grupo13.vistas.imagenes.componentes.Imagen;
import ar.edu.uade.tesis_grupo13.vistas.imagenes.componentes.ImagenRenderizable;
import ar.edu.uade.tesis_grupo13.vistas.imagenes.componentes.MapaComponent;
import ar.edu.uade.tesis_grupo13.vistas.imagenes.componentes.MapaGrillado;
import ar.edu.uade.tesis_grupo13.vistas.imagenes.componentes.Path;

public class GeneradorImagenes {
	
	private int width;
	private int height;	
	private HashMap<String, MapaComponent> layers;	
	
	public GeneradorImagenes(int w, int h, Mapa map) {
		this.width = w;
		this.height = h;		
		this.layers = new HashMap<String, MapaComponent>();
		ImagenRenderizable.width = w;
		ImagenRenderizable.height = h;
		ImagenRenderizable.map = map;
	}

	public GeneradorImagenes(BufferedImage imagen, Mapa map) {
		this.width = imagen.getWidth();
		this.height = imagen.getHeight();		
		this.layers = new HashMap<String, MapaComponent>();
		this.layers.put("imagen", Imagen.getInstance(imagen));
		ImagenRenderizable.width = this.width;
		ImagenRenderizable.height = this.height;
		ImagenRenderizable.map = map;
	}

	public MapaComponent getGrid() {
		if (layers.get("grid") == null) {
			layers.put("grid", Grilla.getInstance());
		}
		return layers.get("grid");
	}
	
	public MapaComponent getBordes() {
		if (layers.get("bordes") == null) {
			layers.put("bordes", Bordes.getInstance());
		}
		return layers.get("bordes");
	}
	
	public void setMap(Mapa mapa) {		
		ImagenRenderizable.map = mapa;		
	}

	public MapaComponent getMapaGrillado() {
		if (layers.get("mapaGrillado") == null) {
			layers.put("mapaGrillado", MapaGrillado.getInstance());
		}
		return layers.get("mapaGrillado");
	}

	public MapaComponent getGrafo() {
		if (layers.get("grafo") == null) {
			layers.put("grafo", Grafo.getInstance());
		}
		return layers.get("grafo");
	}

	public MapaComponent getPath() {		
		layers.put("path", Path.getInstance());		
		return layers.get("path");
	}
	
	public MapaComponent getImagen() {		
		return layers.get("imagen");
	}
	
	public void clearBuffer() {
		MapaComponent tmp = layers.get("imagen");
		layers.clear();
		layers.put("imagen", tmp);
	}

}
