package ar.edu.uade.tesis_grupo13.modelo;

import org.jgrapht.WeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import ar.edu.uade.tesis_grupo13.modelo.grafos.Grafo;

public class GeneradorGrafo {
	
	private Mapa mapa;
	private Grafo g;
	private WeightedGraph<Coordenada, DefaultWeightedEdge> grafo;
	
	/*
	 * Matriz de coordenadas, contiene las coordenadas para cada
	 * sector del mapa.
	 */
	private Coordenada[][] matrizCoord;

	public GeneradorGrafo(boolean[][] matrizParedes, Grafo g){
		this.mapa = new Mapa(matrizParedes);
		this.g = g;
		
		grafo = new SimpleWeightedGraph<Coordenada, DefaultWeightedEdge>(DefaultWeightedEdge.class);		
		generaGrafo();
	}

	private void generaGrafo() {		
		generaVertices();
		generaAristas();
		g.setGrafo(grafo);
		g.setMatrizCoord(matrizCoord);
	}
	
	public Mapa getMapa() {
		return mapa;
	}

	public void setMapa(Mapa mapa) {
		this.mapa = mapa;
	}

	private void generaVertices() {				
		
		int width = 200; //ancho del mapa en cm
		int height = 200; //alto del mapa en cm
		
		double xMin = -width / 20;
		double yMin = height / 20;
		
		int filas = mapa.getMatrizParedes().length;
		int columnas = mapa.getMatrizParedes()[0].length;
		
		double intervaloX = new Float(width) / columnas / 10;
		double intervaloY = new Float(height) / filas / 10;
		
		matrizCoord = new Coordenada[filas][columnas];
		
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				matrizCoord[i][j] = new Coordenada(xMin + j * intervaloX, yMin - i * intervaloY);
				matrizCoord[i][j].setMatrizX(j);
				matrizCoord[i][j].setMatrizY(i);
				grafo.addVertex(matrizCoord[i][j]);
			}
		}						
	}
	
	private void generaAristas() {
		
		boolean[][] matriz = mapa.getMatrizParedes();
		
		for (int y = 0; y < matrizCoord.length; y++) {
			for (int x = 0; x < matrizCoord[0].length; x++) {																										
				//Me fijo si no soy parte de una pared
				if (!matriz[y][x]) {
					
					double weight = 1;
					
					//Me fijo si el cuadro de la derecha no es parte de una pared
					if ( x < matriz[0].length - 1 && !matriz[y][x+1] ) {						
						if (mapa.isBorder(x, y) || mapa.isBorder(x+1, y)) { weight = 5;}
						grafo.addEdge(matrizCoord[y][x], matrizCoord[y][x+1]);						
						grafo.setEdgeWeight(grafo.getEdge(matrizCoord[y][x], matrizCoord[y][x+1]), weight);																	
					}		
					
					weight = 1;
					
					//Me fijo si el cuadro de abajo no es parte de una pared
					if ( y < matriz.length - 1 && !matriz[y+1][x] ) {
						if (mapa.isBorder(x, y) || mapa.isBorder(x, y+1)) { weight = 5;}
						grafo.addEdge(matrizCoord[y][x], matrizCoord[y+1][x]);
						grafo.setEdgeWeight(grafo.getEdge(matrizCoord[y][x], matrizCoord[y+1][x]), weight);
					}
					
					weight = 1.5;
					
					//Me fijo si el cuadro diagonal derecho de abajo no es parte de una pared
					if ( x < matriz[0].length - 1 && y < matriz.length - 1 && !matriz[y+1][x+1] ) {
						if (mapa.isBorder(x, y) || mapa.isBorder(x+1, y+1)) { weight = 7.5;}
						grafo.addEdge(matrizCoord[y][x], matrizCoord[y+1][x+1]);
						grafo.setEdgeWeight(grafo.getEdge(matrizCoord[y][x], matrizCoord[y+1][x+1]), weight);
					}
					
					weight = 1.5;
					
					//Me fijo si el cuadro diagonal izquierdo de abajo no es parte de una pared
					if ( x > 0  && y < matriz.length - 1 && !matriz[y+1][x-1] ) {
						if (mapa.isBorder(x, y) || mapa.isBorder(x-1, y+1)) { weight = 7.5;}						
						grafo.addEdge(matrizCoord[y][x], matrizCoord[y+1][x-1]);
						grafo.setEdgeWeight(grafo.getEdge(matrizCoord[y][x], matrizCoord[y+1][x-1]), weight);
					}
					
				}													
			}
		}		
	}

}
