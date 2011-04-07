package ar.edu.uade.tesis_grupo13.vistas.imagenes.componentes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import ar.edu.uade.tesis_grupo13.modelo.CoordenadaSoftware;

public class Box extends ImagenRenderizable {
	
	private CoordenadaSoftware coord;
	private Color color;
	private static Box instance;	

	public Box(CoordenadaSoftware coord, Color color) {
		super();
		System.out.print(coord.getMatrizX());
		System.out.println(", " + coord.getMatrizY());
		this.coord = coord;		
		this.color = color;		
		render();
	}
	
	public static Box getInstance(CoordenadaSoftware coord, Color color) {	
		instance = new Box(coord, color);		
		return instance;
	}

	public void render() {
				
		BufferedImage imagen = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = imagen.createGraphics();
		
		g.setColor(color);
		System.err.printf("Box: X: %d, Y: %d, size: %d\n", coord.getMatrizX(), coord.getMatrizY(), config.gridSize);
		int x = (int) (coord.getMatrizX() * config.gridSize);
		int y = (int) (coord.getMatrizY() * config.gridSize);
		int w = (int) (config.gridSize);
		int h = (int) (config.gridSize);
		g.fillRect(x, y, w, h);
		
		buffer = imagen;	
	}
	
	@Override
	public void repaint() {
		if (ImagenRenderizable.changeType.equals("gridSize")) {			
			super.repaint();
		}
	}
		
}
