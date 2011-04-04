package ar.edu.uade.tesis_grupo13.modelo;

import java.awt.Color;
import java.awt.Dimension;

import ar.edu.uade.tesis_grupo13.MVCframework.modelo.Modelo;
import ar.edu.uade.tesis_grupo13.MVCframework.vista.Vista;
import ar.edu.uade.tesis_grupo13.config.Config;
import ar.edu.uade.tesis_grupo13.modelo.grafos.Grafo;
import ar.edu.uade.tesis_grupo13.modelo.grafos.VertexList;
import ar.edu.uade.tesis_grupo13.vistas.ventanas.VistaMainMenu;

public class MapMaker extends Modelo{
	
	private Imagen imagen;
	private boolean[][] matrizParedes;
	private Grafo grafo;
	private Config config;
	private VertexList camino;
	private static MapMaker instance;
	private Mapa mapa;
	public Mapa getMapa() {
		return mapa;
	}

	/*
	 * Tamaño del mapa en cm
	 */
	private Dimension mapSize = new Dimension(200, 200);
	
	public MapMaker() {
		grafo = new Grafo();
	}
	
	public VistaMainMenu getVista() {
		return (VistaMainMenu) vista;
	}
	
	@Override
	public void setVista(Vista v) {		
		super.setVista(v);
		config = new Config(this);
	}
	
	public static MapMaker getInstance() {
		if (instance == null) {
			instance = new MapMaker();
		}
		return instance;
	}

	public Grafo getGrafo() {
		return grafo;
	}	
	
	private void generarMatrizParedes() {
		
		int w = imagen.getWidth();
		int h = imagen.getHeight();				
		
		matrizParedes = new boolean[(int)Math.ceil(h/config.gridSize)+1][(int)Math.ceil(w/config.gridSize)+1];
		System.out.println(matrizParedes[0].length + ", " + matrizParedes.length);

		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				
				int pixel = imagen.getRgbMatrix()[j][i];
								
				if (!matrizParedes[(int)Math.floor(i/config.gridSize)][(int)Math.floor(j/config.gridSize)] && pixel == Color.BLACK.getRGB()) {
					matrizParedes[(int)Math.floor(i/config.gridSize)][(int)Math.floor(j/config.gridSize)] = true;
				}
				
			}
		}

	}
	
	public boolean[][] getMatrizParedes() {
		return matrizParedes;
	}

	public Imagen getImagen() {
		return imagen;
	}
	
	public void regenerarMatrizParedes() {
		this.generarMatrizParedes();
		GeneradorGrafo graphGen = new GeneradorGrafo(matrizParedes, grafo);		
		this.mapa = graphGen.getMapa();
	}

	public void setImagen(Imagen imagen) {
		this.imagen = imagen;
		regenerarMatrizParedes();
	}

	public Config getConfig() {
		return config;
	}

	public void setCamino(VertexList camino) {
		this.camino = camino;
	}	
	
	public VertexList getCamino() {
		return this.camino;
	}

	public Dimension getMapSize() {
		return mapSize;
	}

	public void setMapSize(Dimension mapSize) {
		this.mapSize = mapSize;
	}

	
}
