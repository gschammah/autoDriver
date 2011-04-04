package ar.edu.uade.tesis_grupo13.modelo;

/*
 *  Esta clase contiene una matriz donde cada celda [i,j]
 *  representa un sector del mapa. Si se le asigna true, 
 *  ese sector es un sector "no transitable". 
 *  Ademas contiene una funcion isBorder, que determina si
 *  el sector [i, j] es proximo a un sector "no transitable"
 *  o no.
 */
public class Mapa {
		
	private boolean[][] matrizParedes;
		
	public Mapa(boolean[][] matrizParedes) {
		this.matrizParedes = matrizParedes;		
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
