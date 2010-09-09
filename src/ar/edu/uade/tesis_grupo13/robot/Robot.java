package ar.edu.uade.tesis_grupo13.robot;

import javaclient2.PlayerClient;
import javaclient2.Position2DInterface;
import javaclient2.SimulationInterface;
import javaclient2.structures.PlayerConstants;
import ar.edu.uade.tesis_grupo13.config.Conf;
import ar.edu.uade.tesis_grupo13.connection.RobotConnection;

public class Robot extends RobotConnection {

	private PlayerClient robot;
	private Position2DInterface posi;
	private SimulationInterface simu;
	private static Localizacion loc;
 
	
	public Robot() {
		
		this.initialize();		
		
	}
	
	public void initialize() {
		robot = super.getConnection();
		posi = robot.requestInterfacePosition2D(0, PlayerConstants.PLAYER_OPEN_MODE);		
		simu = robot.requestInterfaceSimulation(0, PlayerConstants.PLAYER_OPEN_MODE);
		
		robot.runThreaded (-1, -1);
	}
		
	
	public void moverAdelante(int segundos) {
		posi.setSpeed (Conf.speed, 0);
		System.err.println(segundos + " ms.");
		this.sleep(segundos);		
	}
	
	public void moverAtras(int segundos) {
		posi.setSpeed (-Conf.speed, 0);
		System.err.println(segundos + " ms.");
		this.sleep(segundos);
	}
	
	public void girarIzquierda(int segundos) {
		posi.setSpeed (0, Conf.turnSpeed);
		System.err.println(segundos + " ms.");
		this.sleep(segundos);
	}
	
	public void girarDerecha(int segundos) {
		posi.setSpeed (0, -Conf.turnSpeed);
		System.err.println(segundos + " ms.");
		this.sleep(segundos);
	}
			
	public void sleep(int segundos) {
		if (segundos > 0) {
			try { Thread.sleep (segundos); } catch (Exception e) { }
			detener();
		}		
	}

	public void detener() {
		posi.setSpeed (0, 0);
	}

	public void finalizar() {
		robot.close();
	}

	public Localizacion getGPS() {				
		
		if (loc == null) {
			loc = new Localizacion(simu);
		}
		
		return loc;	
	}
		
					                            	    		 
	
	
}
