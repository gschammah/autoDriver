package ar.edu.uade.tesis_grupo13.vistas.imagenes.componentes;

import java.awt.image.BufferedImage;

import ar.edu.uade.tesis_grupo13.config.Config;
import ar.edu.uade.tesis_grupo13.modelo.MapMaker;
import ar.edu.uade.tesis_grupo13.modelo.Mapa;

public abstract class ImagenRenderizable implements MapaComponent {
	
	public static int width;
	public static int height;	
	public BufferedImage buffer;
	public static Mapa map;
	public static String changeType;
	protected Config config;
	protected int w;
	protected int h;
	protected int offsetLeft = 0, offsetTop = 0;
		
	public ImagenRenderizable() {
		config = MapMaker.getInstance().getConfig();
		updateAbsSize();	
	}	

	protected void updateAbsSize() {		
		w = (int) (config.gridSize * Math.ceil((double)width / (double)config.gridSize));
		h = (int) (config.gridSize * Math.ceil((double)height / (double)config.gridSize));
		offsetLeft = -(w - width) / 2;
		offsetTop = -(h - height) / 2;		
	}

	public int getWidth() {
		return width;
	}
		
	public int getHeight() {
		return height;
	}	
				
	public void repaint() {
		ImagenRenderizable.map = config.getModelo().getMapa();
		updateAbsSize();
		render();
	}
	
	public BufferedImage getFromBuffer() {			
		return buffer;
	}
	
	public int getOffsetLeft() {
		return offsetLeft;
	}

	public int getOffsetTop() {
		return offsetTop;
	}
	
	
}
