package ar.edu.uade.tesis_grupo13.autodriver;

import javaclient2.structures.PlayerPose;
import ar.edu.uade.tesis_grupo13.config.Conf;
import ar.edu.uade.tesis_grupo13.robot.Localizacion;
import ar.edu.uade.tesis_grupo13.robot.Robot;
import ar.edu.uade.tesis_grupo13.tools.CalculosMatematicos;

public class Autodriver {
	
	Robot robot;
	Localizacion loc;	

	public Autodriver (Robot robot) {
		this.robot = robot;
		this.loc = robot.getGPS();
	}

	public double getDireccion() {
		System.err.println("Obteniendo direccion...");
		loc.updateCoordenadas();
		this.avanzar(Conf.diffMagnitude);						
		return loc.getAnguloActual();		
	}
	
	public void avanzar(int cm) {
		System.err.print("Avanzando " + cm + " cm. ");
		robot.moverAdelante((int) Math.round((cm/Conf.speedReal)*1000));
		loc.updateCoordenadas();
		
		double x = loc.getLatActual() - loc.getLatAnt();
		double y = loc.getLongActual() - loc.getLongAnt();				
		double anguloActual = CalculosMatematicos.calcAngulo(x, y);
		
		loc.setAnguloActual(anguloActual);				
	}
	
	public Localizacion getLoc() {
		return loc;
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
		robot.girarDerecha((int) Math.round((grados/Math.toDegrees(Conf.turnSpeedReal))*1000));		
	}
	
	private void girarIzquierda(double grados) {
		System.err.print("Girando Izq. " + grados + " grados. ");
		robot.girarIzquierda((int) Math.round((grados/Math.toDegrees(Conf.turnSpeedReal))*1000));		
	}


	public Robot getRobot(){
		return this.robot;
	}

	public void close() {
		System.err.println("Finalizado");
		robot.finalizar();		
	}

	public void posicionarRobot(float x, float y, float a) {
		PlayerPose pose = new PlayerPose();
		pose.setPx(x);
		pose.setPy(y);
		pose.setPa(a);
		loc.setPosicionActual(pose);
		
	}	
	
	public void retroceder(int cm) {		
		robot.moverAtras((int) Math.round((cm/Conf.speedReal)*1000));
		loc.updateCoordenadas();
	}

	public void irA(double lat, double lon) {			
		
		boolean dirCorrecta = false;
		double x = lat - loc.getLatActual();
		double y = lon - loc.getLongActual();				
		double anguloDestino = CalculosMatematicos.calcAngulo(x, y);		
		
		if (Math.abs(x * y) >= 0 && Math.abs(x * y) <= 0.1 && y < 0) {			
				anguloDestino += 180; //cambio el sentido			 
		}
		
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
			irA(lat, lon);
		} else if (distancia > 200 && dirCorrecta) {
			distancia = distancia / 2;
			avanzar(distancia);
			irA(lat, lon);
		} else {			
			avanzar(distancia);
		}
		
		System.err.println("Dirigiendose a: " + lat + ", " + lon);
		
		
	}

	public void inicializar() {
		System.err.println("Inicializando...");
		this.getDireccion();		
	}

}
