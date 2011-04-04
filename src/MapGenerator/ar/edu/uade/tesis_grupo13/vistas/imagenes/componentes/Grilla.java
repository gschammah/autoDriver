package ar.edu.uade.tesis_grupo13.vistas.imagenes.componentes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Grilla extends ImagenRenderizable {
	
	private static Grilla instance;	
		
	public Grilla(){
		super();
		render();
	}
	
	public static Grilla getInstance() {
		if (instance == null) {
			instance = new Grilla();
		}
		return instance;
	}

	public void render() {																
				        
	        BufferedImage imagen = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);	        
	        
			Graphics2D g = imagen.createGraphics();
			Color red = Color.RED;			
			g.setColor(new Color(red.getRed(), red.getGreen(), red.getBlue(), 180));													
	
			for (int x=0; x<=w; x += config.gridSize) {
				g.drawLine(x, 0, x, h);
				System.err.println(x);
			}
			for (int y=0; y<=h; y += config.gridSize) {
				g.drawLine(0, y, w, y);								
			}																	
			
	        buffer = imagen;
			
	}		
	
	@Override
	protected void updateAbsSize() {	
		super.updateAbsSize();		
	}
	
}
