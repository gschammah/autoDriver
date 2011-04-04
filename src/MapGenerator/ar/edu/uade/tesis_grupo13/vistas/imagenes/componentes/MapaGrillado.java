package ar.edu.uade.tesis_grupo13.vistas.imagenes.componentes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class MapaGrillado extends ImagenRenderizable {		
	
	private static MapaGrillado instance;	
	
	public MapaGrillado() {
		super();
		render();
	}
		
	
	public static MapaGrillado getInstance() {
		if (instance ==  null) {
			instance = new MapaGrillado();
		}
		return instance;
	}

	public void render() {
				
		BufferedImage imagen = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = imagen.createGraphics();
		

		for (int y = 0; y < map.getMatrizParedes().length; y++) {
			for (int x = 0; x < map.getMatrizParedes()[0].length; x++) {
				// Me fijo si no soy parte de una pared
				if (map.getMatrizParedes()[y][x]) {
					g.setColor(Color.BLACK);
				} else {
					g.setColor(Color.WHITE);
				}
				g.fillRect((x * config.gridSize), (y * config.gridSize), config.gridSize, config.gridSize);
			}
		}				
		
		buffer = imagen; 			
	}
	
	
}
