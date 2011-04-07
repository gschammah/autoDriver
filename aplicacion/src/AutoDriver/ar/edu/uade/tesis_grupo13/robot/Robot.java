package ar.edu.uade.tesis_grupo13.robot;

import java.io.FileNotFoundException;

import javaclient2.structures.PlayerPose;

import ar.edu.uade.tesis_grupo13.autodriver.Localizacion;
import ar.edu.uade.tesis_grupo13.config.Conf;
import ar.edu.uade.tesis_grupo13.exceptions.NullMapException;
import ar.edu.uade.tesis_grupo13.modelo.Coordenada;
import ar.edu.uade.tesis_grupo13.modelo.grafos.Grafo;
import ar.edu.uade.tesis_grupo13.modelo.grafos.VertexList;
import ar.edu.uade.tesis_grupo13.robot.interfaces.DharmaRobot;
import ar.edu.uade.tesis_grupo13.tools.CalculosMatematicos;


public class Robot {
	
	private DharmaRobot dharma;	
	private static Localizacion loc;
	private Conf conf;
	private Grafo grafo;
	
	public Robot() {	
		conf = Conf.getInstance();
		this.grafo = new Grafo();
	}
	
	public void setDharma(DharmaRobot dharma) {
		this.dharma = dharma;
		loc = new Localizacion(dharma);
	}			
			
			
	public void esperar(int segundos) {
		if (segundos > 0) {
			try { Thread.sleep(segundos); } catch (Exception e) { }
			dharma.detener();
		}		
	}	

	public void finalizar() {		
		dharma.finalizar();
	}
		
	public void avanzar(int cm) {
		int segundos = (int) Math.round((cm/conf.getSpeedReal())*1000);
		System.err.print("Avanzando " + cm + " cm. ");
		dharma.moverAdelante();		
		this.esperar(segundos);
		
		loc.updateCoordenadas();
		
		double x = loc.getLatActual() - loc.getLatAnt();
		double y = loc.getLongActual() - loc.getLongAnt();				
		double anguloActual = CalculosMatematicos.calcAngulo(x, y);
		
		loc.setAnguloActual(anguloActual);				
	}
	
	public double getDireccion() {
		System.err.println("Obteniendo direccion...");
		loc.updateCoordenadas();
		this.avanzar(conf.getDiffMagnitude());						
		return loc.getAnguloActual();		
	}
	
	public void posicionarDireccion(double grados) {		
		double angulo = grados - loc.getAnguloActual();
		
		if (Math.abs(angulo) > 180) {
			int signo = 1;
			if (angulo > 0) {
				signo = -1;
			}
			angulo = (180 - (Math.abs(angulo) - 180)) * signo;			
		}
		
		if (angulo > 0) {
			this.girarIzquierda(angulo);
		} else {
			this.girarDerecha(-angulo);
		}
		loc.setAnguloActual(grados);
	}
	
	private void girarDerecha(double grados) {
		System.err.print("Girando Der. " + grados + " grados. ");
		int segundos = (int) Math.round((grados/Math.toDegrees(conf.getTurnSpeedReal()))*1000);
		dharma.girarDerecha();		
		this.esperar(segundos);
	}
	
	private void girarIzquierda(double grados) {
		System.err.print("Girando Izq. " + grados + " grados. ");
		int segundos = ((int) Math.round((grados/Math.toDegrees(conf.getTurnSpeedReal()))*1000));
		dharma.girarIzquierda();		
		this.esperar(segundos);
	}
	
	public void irALatLon(double lat, double lon) {			
		
		boolean dirCorrecta = false;
		double x = lat - loc.getLatActual();
		double y = lon - loc.getLongActual();				
		double anguloDestino = CalculosMatematicos.calcAngulo(x, y);				
		
		if (Math.abs(x * y) >= 0 && Math.abs(x * y) <= 0.1 && y < 0) {			
				anguloDestino += 180; //cambio el sentido			 
		}
		
		loc.setTarget(2, new Coordenada(lat, lon));
		
		if (Math.abs(loc.getAnguloActual() - anguloDestino) < 5) {
			//Si tengo que doblar menos de 5 grados, voy en la dir correcta y sigo de largo
			dirCorrecta = true;
		} else {			
			posicionarDireccion(anguloDestino);
		}
		
		int distancia = (int) Math.floor(CalculosMatematicos.calcHipotenusa(x, y)*100);
		
		if (distancia > 100 && !dirCorrecta) {
			distancia = 100;
			avanzar(distancia);
			irALatLon(lat, lon);
		} else if (distancia > 200 && dirCorrecta) {
			distancia = distancia / 2;
			avanzar(distancia);
			irALatLon(lat, lon);
		} else {			
			avanzar(distancia);
		}
		
		System.err.println("Dirigiendose a: " + lat + ", " + lon);
		
		
	}

	public void inicializar() {
		System.err.println("Inicializando...");
		esperar(2000);
		this.getDireccion();		
	}
	
	public void irA(double lat, double lon) throws NullMapException {				
		
		//Detecta orientacion si es la primer orden		
		if (loc.getPosicionActual() == null) {			
			this.inicializar();
		}
		
		//Detecto si tengo un mapa cargado
		if (!grafo.isLoaded()) {
			throw new NullMapException("No hay ningun mapa cargado");
		}
		
	 	grafo.setStartPoint(grafo.getClosestCoord(loc.getLatActual(), loc.getLongActual()));
		grafo.setEndPoint(grafo.getClosestCoord(lat, lon));
		
		VertexList v = grafo.calcularCamino();
		v = grafo.simplificarCamino(v);
		
		for (Coordenada coord : v) {
			System.out.printf("Yendo a: %f, %f\n", coord.getX(), coord.getY());			
			irALatLon(coord.getX(), coord.getY());
		}
		
	}

	public void cargarGrafo(String mapa) throws FileNotFoundException {
		grafo.importar(mapa);		
	}

	public void irA() throws NullMapException {
		PlayerPose target = loc.getTarget(1);
		irA(target.getPx(), target.getPy());		
	}




	
	
}
