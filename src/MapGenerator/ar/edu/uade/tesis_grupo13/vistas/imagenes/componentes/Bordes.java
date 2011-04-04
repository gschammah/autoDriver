package ar.edu.uade.tesis_grupo13.vistas.imagenes.componentes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Bordes extends ImagenRenderizable {
		
	private static Bordes instance;	

	public Bordes() {
		super();
		render();		
	}

	public static Bordes getInstance() {
		if (instance ==  null) {
			instance = new Bordes();			
		}
		return instance;
	}
	
	public void render() {
		
		BufferedImage imagen = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = imagen.createGraphics();
		
		g.setColor(Color.ORANGE);

		for (int y = 0; y < map.getMatrizParedes().length; y++) {
			for (int x = 0; x < map.getMatrizParedes()[0].length; x++) {
				// Me fijo si no soy parte de una pared
				if (map.isBorder(x, y)) {
					g.fillRect((x * config.gridSize), (y * config.gridSize), config.gridSize, config.gridSize);
				} 				
			}
		}				
		
		buffer = imagen;	
	}

}
