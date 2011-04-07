package ar.edu.uade.tesis_grupo13.config;

import java.util.Observable;

import ar.edu.uade.tesis_grupo13.modelo.MapMaker;
import ar.edu.uade.tesis_grupo13.vistas.imagenes.componentes.ImagenRenderizable;

public class Config extends Observable {

	public int gridSize = 10;
	public double zoom = 1;
	public static Config instance;
	private MapMaker modelo;
	public int startLat = 0;
	public int startLong = 0;


	public Config(MapMaker mapMaker) {
		modelo = mapMaker;
		addObserver(modelo.getVista());
	}	
	
	public void setGridSize(int gridSize) {
		ImagenRenderizable.changeType = "gridSize";
		this.gridSize = gridSize;
		modelo.regenerarMatrizParedes();		
		
		if (modelo.getGrafo().getEndPoint() != null && modelo.getGrafo().getStartPoint() != null) {			
			modelo.setCamino(modelo.getGrafo().calcularCamino());
		}
		
		this.update();
	}
	
	public MapMaker getModelo() {
		return modelo;
	}

	public void setZoom(double zoom) {
		ImagenRenderizable.changeType = "zoom";
		this.zoom = zoom;		
		this.update();
	}
	
	public void autoZoom(int zoom) {
		if (zoom < 0 && this.zoom < 1.5) {
			ImagenRenderizable.changeType = "zoom";
			this.zoom += .1;
			this.update();
		} else if (zoom > 0 && this.zoom > .8) {
			ImagenRenderizable.changeType = "zoom";
			this.zoom -= .1;
			this.update();
		}		
	}
	
	private void update() {
		setChanged();
		notifyObservers();
	}
	
	
}
