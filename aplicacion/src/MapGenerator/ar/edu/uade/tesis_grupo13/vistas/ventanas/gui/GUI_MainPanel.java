package ar.edu.uade.tesis_grupo13.vistas.ventanas.gui;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import ar.edu.uade.tesis_grupo13.config.Config;
import ar.edu.uade.tesis_grupo13.modelo.MapMaker;
import ar.edu.uade.tesis_grupo13.vistas.imagenes.componentes.MapaComponent;
import ar.edu.uade.tesis_grupo13.vistas.ventanas.VistaMainMenu;

public class GUI_MainPanel extends JLabel {
		
	private static final long serialVersionUID = 1L;
	private VistaMainMenu vistaPadre;
		
	private Dimension imageSize;
		
	private HashMap<String, MapaComponent> layers;
	private HashMap<String, ImageIcon> layerBuffer;
	private String[] layerOrder = {"mapaGrillado", "base", "bordes", "grafo", "path", "startPoint", "endPoint", "grid"}; 		
	private String[] noBuffer = {"startPoint", "endPoint"};	
	
	public GUI_MainPanel(){
		super();
		setVerticalAlignment(TOP);
		layers = new HashMap<String, MapaComponent>();
		layerBuffer = new HashMap<String, ImageIcon>();	
	}
	
	private String getLayerString() {								
		
		ArrayList<String> keys = new ArrayList<String>(layers.keySet());		
		
		if (keys.size() > 0) { 
		
			Collections.sort(keys);
			StringBuilder result = new StringBuilder(keys.get(0));
			
			for (int i=1; i<keys.size(); i++) {
				result.append("+").append(keys.get(i));
			}
			
			return result.toString();
		} else {
			return null;
		}
	}
	
	public void clearBuffer(){
		layerBuffer.clear();		
	}

	public void redibujar() {
		
		String layerString = getLayerString();
		String layerStringNoBF = getLayerStringNoBuffer();			
				
		if (layerBuffer.containsKey(layerString) && layerString.equals(layerStringNoBF)) {		
			setIcon(layerBuffer.get(layerString));
			
		} else if (layers.size() > 0) {
			
			Config config = ((MapMaker)(vistaPadre.getControlador().getModelo())).getConfig();
			
			int width = (int) (imageSize.width * config.zoom);
			int height = (int) (imageSize.height * config.zoom);
			
			BufferedImage tempImg = new BufferedImage(imageSize.width, imageSize.height, BufferedImage.TYPE_INT_ARGB);
			Graphics2D gTemp = tempImg.createGraphics();
			gTemp.fillRect(0, 0, imageSize.width, imageSize.height);
			
			for (String layer: layerOrder) {													
				if (layers.containsKey(layer)) {
					MapaComponent c = layers.get(layer);
					gTemp.drawImage(c.getFromBuffer(), c.getOffsetLeft(), c.getOffsetTop(), null);														
				}					
			}
			
			BufferedImage tempImg2 = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics2D gTemp2 = tempImg2.createGraphics();
			gTemp2.drawImage(tempImg, 0, 0, width, height, null);
			
			
			layerBuffer.put(layerStringNoBF, new ImageIcon(tempImg2));
			setIcon(layerBuffer.get(layerStringNoBF));
			
			gTemp.dispose();
			gTemp2.dispose();
		}
		
	}

	private String getLayerStringNoBuffer() {
		ArrayList<String> keys = new ArrayList<String>(layers.keySet());
		List<String> noBF = Arrays.asList(noBuffer);
		
		if (keys.size() > 0) { 
		
			Collections.sort(keys);
			StringBuilder result = new StringBuilder(keys.get(0));					
			
			for (int i=1; i<keys.size(); i++) {				
				if (!noBF.contains(keys.get(i))) {					
					result.append("+").append(keys.get(i));
				}
			}
			
			return result.toString();
		} else {
			return null;
		}
	}
	
	public void addLayer(String layerName, MapaComponent image) {
		if (layerName.equals("mapaGrillado")) {
			imageSize = new Dimension(image.getWidth(), image.getHeight());			
		}
		layers.put(layerName, image);
		redibujar();
	}

	public void removeLayer(String layerName) {		
		layers.remove(layerName);
		redibujar();
	}

	public Dimension getImageSize() {
		return imageSize;
	}

	public void setImageSize(Dimension imageSize) {
		this.imageSize = imageSize;
	}

	public void clearLayers() {		
		layerBuffer.clear();		
		for (String key: layers.keySet()) {			
			layers.get(key).repaint();
		}		
		redibujar();		
	}

	public boolean hasLayer(String layerName) {
		return layers.containsKey(layerName);
	}
	
	public void setVistaPadre(VistaMainMenu vistaPadre) {
		this.vistaPadre = vistaPadre;
	}

		
	
}
