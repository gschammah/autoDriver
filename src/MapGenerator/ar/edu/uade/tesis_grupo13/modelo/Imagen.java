package ar.edu.uade.tesis_grupo13.modelo;

public class Imagen {

	private int width;
	private int height;
	private int[][] rgbMatrix;
	
	public Imagen(int w, int h, int[][] matriz) {
		width = w;
		height = h;
		rgbMatrix = matriz;
	}

	public int getWidth() {
		return width;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public int[][] getRgbMatrix() {
		return rgbMatrix;
	}
	
	public void setRgbMatrix(int[][] rgbMatrix) {
		this.rgbMatrix = rgbMatrix;
	} 
	
}
