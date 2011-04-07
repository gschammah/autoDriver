package ar.edu.uade.tesis_grupo13.modelo.grafos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.jgrapht.GraphPath;
import org.jgrapht.Graphs;
import org.jgrapht.WeightedGraph;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import ar.edu.uade.tesis_grupo13.modelo.Coordenada;
import ar.edu.uade.tesis_grupo13.modelo.export.AristaXML;
import ar.edu.uade.tesis_grupo13.modelo.export.MapaXML;
import ar.edu.uade.tesis_grupo13.modelo.export.VerticeXML;

import com.thoughtworks.xstream.XStream;

public class Grafo {
	
	private WeightedGraph<Coordenada, DefaultWeightedEdge> grafo;		
	private Coordenada startPoint = null;
	private Coordenada endPoint = null;
	private Coordenada[][] matrizCoord;

	public Grafo(){}
		
	public void setMatrizCoord(Coordenada[][] matrizCoord) {
		this.matrizCoord = matrizCoord;
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
	
	public Set<Coordenada> getVertices() {
		return grafo.vertexSet();
	}
	
	public ArrayList<Arista> getAristas() {
		
		ArrayList<Arista> aristas = new ArrayList<Arista>();
		
		for (DefaultWeightedEdge edge: grafo.edgeSet()) {
			Arista a = new Arista();
			a.setDesde(grafo.getEdgeSource(edge));
			a.setHasta(grafo.getEdgeTarget(edge));
			a.setPeso(grafo.getEdgeWeight(edge));
			aristas.add(a);
		}
		
		return aristas;
		
	}
	
	
	public double getEdgeWeight(int x, int y, int x2, int y2) {
		return grafo.getEdgeWeight(grafo.getEdge(matrizCoord[y][x], matrizCoord[y2][x2]));
	}
		
	public VertexList calcularCamino(int x, int y, int x2, int y2) {		
		VertexList resultado = new VertexList();
		
		System.out.printf("Calculando camino entre x1: %d, y1: %d, x2: %d, y2: %d\n", x, y, x2, y2);
		
		DijkstraShortestPath<Coordenada, DefaultWeightedEdge> path = new DijkstraShortestPath<Coordenada, DefaultWeightedEdge>(grafo, matrizCoord[y][x], matrizCoord[y2][x2]);
		
		try {			
			
			resultado.addAll(Graphs.getPathVertexList(path.getPath()));
			
			for (Coordenada c : resultado) {
				System.out.println(c);
			}						
			
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

	public VertexList simplificarCamino(VertexList camino) {
		System.out.println(camino);
		boolean first = true;
		Coordenada previo = null;
		Coordenada actual = null;
		
		VertexList res = new VertexList();
		
		for (int i=0; i<camino.size(); i++) {
			
			actual = camino.get(i);
			
			if (first) {
				if (previo != null) {
					res.add(previo);
				}							
				first = false;
				previo = actual;
			} else {
				
				//dir vertical
				if (previo.getMatrizX() == actual.getMatrizX()) {
					while (previo.getMatrizX() == actual.getMatrizX() && i<camino.size()-1) {
						previo = actual;
						actual = camino.get(++i);																	
					}					
					first = true;
				} 
				//horizontal
				else if (previo.getMatrizY() == actual.getMatrizY() ) {
					while (previo.getMatrizY() == actual.getMatrizY() && i<camino.size()-1) {
						previo = actual;
						actual = camino.get(++i);																	
					}					
					first = true;
				}
				//diagonal
				else {
					//derecha
					if ((actual.getMatrizX() - previo.getMatrizX()) + (actual.getMatrizY() - previo.getMatrizY()) == 0) {
						 
						while ((actual.getMatrizX() - previo.getMatrizX()) + (actual.getMatrizY() - previo.getMatrizY()) == 0 && i<camino.size()-1) {							
							previo = actual;
							actual = camino.get(++i);																	
						}											
					}
					//izquierda
					else {
						
						while ((actual.getMatrizX() - previo.getMatrizX()) - (actual.getMatrizY() - previo.getMatrizY()) == 0 && i<camino.size()-1) {							
							previo = actual;
							actual = camino.get(++i);																	
						}
					}
					first = true;
				}
			}									
		}
		
		res.add(actual);
				
		return res;
	}
	
	public void setGrafo(WeightedGraph<Coordenada, DefaultWeightedEdge> grafo) {
		this.grafo = grafo;
	}

	public Coordenada getStartPoint() {
		return startPoint;
	}

	public Coordenada getEndPoint() {
		return endPoint;
	}
	
	public void exportar(String filename) {
		
		HashMap<Coordenada, Integer> vertices = new HashMap<Coordenada, Integer>();
		
		ArrayList<VerticeXML> vertex = new ArrayList<VerticeXML>();
		ArrayList<AristaXML> aristas = new ArrayList<AristaXML>();
		
		int i = 0;
		
		for (Coordenada vert: getVertices()) {
			vertices.put(vert, i++);
		}
		
		for (i = 0; i < matrizCoord[0].length; i++) {
			for (int j = 0; j < matrizCoord.length; j++) {
				Coordenada coord = matrizCoord[j][i];
				vertex.add(new VerticeXML(coord.getX(), coord.getY(), vertices.get(coord), i, j));
			}
		}
		
		
		for (Arista arista: getAristas()) {
			aristas.add(new AristaXML(vertices.get(arista.getDesde()), vertices.get(arista.getHasta()), arista.getPeso()));			
		}
		
		MapaXML map = new MapaXML(vertex, aristas, matrizCoord[0].length, matrizCoord.length);
		
		XStream xs = new XStream();
		xs.processAnnotations(VerticeXML.class);
		xs.processAnnotations(AristaXML.class);		
		xs.processAnnotations(MapaXML.class);
								
		try {
			xs.toXML(map, new FileWriter(new File(filename)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void importar(String filename) throws FileNotFoundException {
		System.out.println(filename); 
		MapaXML mapa = getObjFromXml(filename);
		loadXML(mapa);		
	}

	private void loadXML(MapaXML mapa) { 
		
		HashMap<Integer, Coordenada> vertices = new HashMap<Integer, Coordenada>();
		
		grafo = new SimpleWeightedGraph<Coordenada, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		matrizCoord = new Coordenada[mapa.getFilas()][mapa.getColumnas()];
		
		for (VerticeXML vert: mapa.getVertices()) {
			Coordenada coord = new Coordenada(vert.getLat(), vert.getLon());
			coord.setMatrizX(vert.getI());
			coord.setMatrizY(vert.getJ());
			vertices.put(vert.getId(), coord);
			grafo.addVertex(coord);
			matrizCoord[vert.getJ()][vert.getI()] = coord;
		}
		
		for (AristaXML ar: mapa.getAristas()) {
			grafo.addEdge(vertices.get(ar.getDesde()), vertices.get(ar.getHasta()));
			grafo.setEdgeWeight(grafo.getEdge(vertices.get(ar.getDesde()), vertices.get(ar.getHasta())), ar.getPeso());
			
		}
						
	}

	private MapaXML getObjFromXml(String filename) throws FileNotFoundException {
		XStream xs = new XStream();
		xs.processAnnotations(AristaXML.class);
		xs.processAnnotations(VerticeXML.class);
		xs.processAnnotations(MapaXML.class);
		
		return (MapaXML)xs.fromXML(new FileReader(new File(filename)));
	}
	
	private double getDistancia(Coordenada coord1, Coordenada coord2) {
		
		double lat = Math.abs(coord2.getX() - coord1.getX());
		double lon = Math.abs(coord2.getY() - coord1.getY());
		
		return Math.sqrt(Math.pow(lat, 2) + Math.pow(lon, 2));
	}
	
	public Coordenada getClosestCoord(double lat, double lon) {
		
		int tmpI = 0, tmpJ = 0;
		double minVal = 999999999;
		Coordenada coord1 = new Coordenada(lat, lon);
		
		for (int i = 0; i< matrizCoord[0].length; i++) {
			for (int j = 0; j < matrizCoord.length; j++) {
				if (getDistancia(coord1, matrizCoord[j][i]) < minVal) {
					minVal = getDistancia(coord1, matrizCoord[j][i]);
					tmpI = i;
					tmpJ = j;
				}
			}			
		}			
		
		System.out.printf("MatrizX: %d, MatrizY: %d\n", matrizCoord[tmpJ][tmpI].getMatrizX(), matrizCoord[tmpJ][tmpI].getMatrizY());
		System.out.printf("Lat: %f, Lon: %f\n", lat, lon);
		System.out.printf("LatG: %d, LonG: %d\n", tmpI, tmpJ);
		System.out.printf("LatG: %f, LonG: %f\n", matrizCoord[tmpJ][tmpI].getX(), matrizCoord[tmpJ][tmpI].getY());
		
		return matrizCoord[tmpJ][tmpI];
	}

	public boolean isLoaded() {		
		return this.matrizCoord != null;
	}


			
}
