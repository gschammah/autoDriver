package ar.edu.uade.tesis_grupo13.tools;

import java.awt.image.BufferedImage;

import ar.edu.uade.tesis_grupo13.modelo.Imagen;

public class ProcesamientoImagenes {

	public static Imagen jpg2Imagen(BufferedImage img) {

		int w = img.getWidth();
		int h = img.getHeight();

		int[][] matriz = new int[w][h];

		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {

				matriz[j][i] = img.getRGB(j, i);				
			}
		}		
		
		return new Imagen(w, h, matriz);
	}

}
