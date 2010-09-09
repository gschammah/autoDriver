package ar.edu.uade.tesis_grupo13.grafos;


public class Mapa {
		
	private boolean[][] matrizParedes;
	private Grafo grafo;
	
	public Mapa(boolean[][] matrizParedes, Grafo grafo) {
		this.matrizParedes = matrizParedes;
		this.grafo = grafo;
	}

	public Grafo getGrafo() {
		return grafo;
	}					
		
	public boolean isBorder(int x, int y) {			
		
		if (x >= matrizParedes[0].length || y >= matrizParedes.length) {
			return false;
		}
		
		if (matrizParedes[y][x]) {
			return false;
		}		 
		
		int startx = x-1;
		int endx = x+1;
		int starty = y-1;
		int endy = y+1;
		
		if (startx < 0) {startx = 0;}
		if (starty < 0) {starty = 0;}
		if (endx == matrizParedes[0].length) {endx = x;}
		if (endy == matrizParedes.length) {endy = y;}					
		
		for (int j = starty; j <= endy; j++) {
			for (int i = startx; i <= endx; i++) {				
				if (matrizParedes[j][i]) {
					return true;
				}
			}
		}		
		
		return false;
	}
	
	
	public boolean[][] getMatrizParedes() {
		return matrizParedes;
	}
	
	public void setMatrizParedes(boolean[][] matrizParedes) {
		this.matrizParedes = matrizParedes;
	}

	
}
