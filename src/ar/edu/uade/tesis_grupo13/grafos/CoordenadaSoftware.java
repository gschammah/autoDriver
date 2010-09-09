package ar.edu.uade.tesis_grupo13.grafos;

public class CoordenadaSoftware extends Coordenada {

	public CoordenadaSoftware(double x, double y) {
		super(x, y);		
		procesarCoordReales();
	}

	private void procesarCoordReales() {
		this.setMatrizX((int) Math.floor(this.getX()/10));
		this.setMatrizY((int) Math.floor(this.getY()/10));		
	}
	
	@Override
	public void setX(double x) {	
		super.setX(x);
		procesarCoordReales();
	}
	
	@Override
	public void setY(double y) {	
		super.setY(y);
		procesarCoordReales();
	}
	
}
