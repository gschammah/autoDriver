package ar.edu.uade.tesis_grupo13.modelo;

public class Coordenada {
	
	protected double x, y;
	protected int matrizX, matrizY;

	public Coordenada(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	
	public void setMatrizX(int matrizX) {
		this.matrizX = matrizX;
	}

	public int getMatrizX() {
		return matrizX;
	}

	public int getMatrizY() {
		return matrizY;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void setMatrizY(int matrizY) {
		this.matrizY = matrizY;
	}
	
	@Override
	public String toString() {		
		return "X: " + this.getMatrizX() + ", Y: " + this.getMatrizY();
	}

}
