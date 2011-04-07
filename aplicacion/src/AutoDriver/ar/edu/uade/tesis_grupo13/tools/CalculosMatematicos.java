package ar.edu.uade.tesis_grupo13.tools;


public class CalculosMatematicos {

	public static double calcAngulo(double adyacente, double opuesto) {							
		double angle = Math.toDegrees(Math.atan(Math.abs(opuesto/adyacente)));
		angle = roundToDecimals(angle, 2);
		
		int cuadrante = CalculosMatematicos.calcularCuadrante(adyacente, opuesto);
		int offset = (cuadrante - 1) * 90;
		
		if (offset > 0) {
			
			//si esta en el segundo o cuarto cuadrante, calculo el complemento
			if (cuadrante == 2 || cuadrante == 4) {
				angle = offset + 90 - angle;
			} else {
				angle = offset + angle;
			}									
		}
		
		return angle;
	}
	
	public static double calcHipotenusa(double a, double c) {
		double hipo = Math.sqrt(Math.pow(a, 2) + Math.pow(c, 2));		
		return hipo;
	}
	
	public static double roundToDecimals(double d, int c) {
		int temp=(int)((d*Math.pow(10,c)));
		return (((double)temp)/Math.pow(10,c));
	}

	public static int calcularCuadrante(double x, double y) {
		
		int cuadrante = 0;
		
		if (x > 0 && y > 0) {
			cuadrante = 1;
		}  else if (x < 0 && y > 0) {
			cuadrante = 2;
		} else if (x < 0 && y < 0) {
			cuadrante = 3;
		} else if (x > 0 && y < 0) {
			cuadrante = 4;
		}
		
		return cuadrante;
		
	}
	
}
