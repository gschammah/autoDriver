package ar.edu.uade.tesis_grupo13.modelo;

import java.awt.Dimension;

import ar.edu.uade.tesis_grupo13.config.Config;

public class CoordenadaSoftware extends Coordenada {
	
	private Dimension mapSize, imageSize;	
	private Config config;		
	
	public CoordenadaSoftware(double x, double y, Dimension mapSize, Dimension imageSize) {
		super(x, y);
		this.config = MapMaker.getInstance().getConfig();
		this.mapSize = mapSize;
		this.imageSize = imageSize;
		this.matrizX = (int)x;
		this.matrizY = (int)y;
		this.setX(x);
		this.setY(y);
	}
	
	public void setMapSize(Dimension mapSize) {
		this.mapSize = mapSize;
	}

	@Override
	public void setX(double x) {		
		this.x = (x * mapSize.width / (imageSize.width * config.zoom) - mapSize.width / 2) / 10;		
	}
	
	@Override
	public void setY(double y) {		
		this.y = -(y * mapSize.height / (imageSize.height * config.zoom) - mapSize.height / 2) / 10;	
	}
	
	
	@Override
	public int getMatrizX() {
		int imageWidth = imageSize.width;
		int w = (int) (config.gridSize * Math.ceil((double)imageWidth/ (double)config.gridSize));
		
		int offsetLeft = (w - imageWidth) / 2;
				
		int val = (int) Math.floor((this.matrizX + offsetLeft)/config.gridSize/config.zoom) - config.startLat / 2;		
		return val;
	}
	
	@Override
	public int getMatrizY() {

		int imageHeight = imageSize.height;
		int h = (int) (config.gridSize * Math.ceil((double)imageHeight / (double)config.gridSize)) - config.startLong / 2;
		
		int offsetTop = (h - imageHeight) / 2;
		int val = (int) Math.floor((this.matrizY + offsetTop)/config.gridSize/config.zoom);			
		return val;
	}
		
	
}
