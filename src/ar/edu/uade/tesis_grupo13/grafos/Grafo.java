package ar.edu.uade.tesis_grupo13.grafos;

import java.util.Iterator;
import java.util.Set;

import org.jgrapht.Graphs;
import org.jgrapht.WeightedGraph;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

public class Grafo {
	
	private WeightedGraph<Coordenada, DefaultWeightedEdge> grafo;
	private Coordenada[][] matrizCoord;
	private Mapa mapa;
	private Coordenada startPoint = null;
	private Coordenada endPoint = null;

	public Grafo(boolean[][] matrizParedes){
		this.mapa = new Mapa(matrizParedes, this);
		grafo = new SimpleWeightedGraph<Coordenada, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		generaGrafo();
	}

	private void generaGrafo() {		
		generaVertices();
		generaAristas();
	}
	
	public Mapa getMapa() {
		return mapa;
	}
	
	public VertexList getVertices(Coordenada coord) {
		return getVertices(coord.getMatrizX(), coord.getMatrizY());
	}

	public VertexList getVertices(int x, int y) {
		
		VertexList coordenadas = new VertexList();		
		
		Set<DefaultWeightedEdge> aristas = grafo.edgesOf(matrizCoord[y][x]);
		Iterator<DefaultWeightedEdge> itr = aristas.iterator();		
		
		while (itr.hasNext()) {			
			DefaultWeightedEdge edge = itr.next();
			coordenadas.add(grafo.getEdgeTarget(edge));
			coordenadas.add(grafo.getEdgeSource(edge));
		}
		
		return coordenadas;
	}
	
	
	public double getEdgeWeight(int x, int y, int x2, int y2) {
		return grafo.getEdgeWeight(grafo.getEdge(matrizCoord[y][x], matrizCoord[y2][x2]));
	}

	private void generaVertices() {				
		
		int width = 200; //ancho del mapa en cm
		int height = 200; //alto del mapa en cm
		
		double xMin = -width / 20;
		double yMin = -height / 20;
		
		int filas = mapa.getMatrizParedes().length;
		int columnas = mapa.getMatrizParedes()[0].length;
		
		double intervaloX = new Float(width) / columnas / 10;
		double intervaloY = new Float(height) / filas / 10;
		
		matrizCoord = new Coordenada[filas][columnas];
		
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				matrizCoord[i][j] = new Coordenada(xMin + j * intervaloX, yMin + i * intervaloY);
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
	
	public VertexList calcularCamino(int x, int y, int x2, int y2) {		
		VertexList resultado = new VertexList();
		
		DijkstraShortestPath<Coordenada, DefaultWeightedEdge> d = new DijkstraShortestPath<Coordenada, DefaultWeightedEdge>(grafo, matrizCoord[y][x], matrizCoord[y2][x2]);
		try {
			resultado.addAll(Graphs.getPathVertexList(d.getPath()));
		} catch (NullPointerException e) {}
		
		return resultado;		
	}

	public void setStartPoint(Coordenada coord) {		
		startPoint = coord;
	}
	
	public void setEndPoint(Coordenada coord) {		
		endPoint = coord;
	}

	public VertexList calcularCamino() {		
		return calcularCamino(startPoint.getMatrizX(), startPoint.getMatrizY(), endPoint.getMatrizX(), endPoint.getMatrizY());	
	}

	public Coordenada getStartPoint() {
		return startPoint;
	}

	public Coordenada getEndPoint() {
		return endPoint;
	}
			
}
