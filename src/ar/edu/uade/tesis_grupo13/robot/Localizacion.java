package ar.edu.uade.tesis_grupo13.robot;

import javaclient2.SimulationInterface;
import javaclient2.structures.PlayerPose;
import ar.edu.uade.tesis_grupo13.config.Conf;

public class Localizacion {
		
	private SimulationInterface simu;
	private PlayerPose posicionActual;
	private PlayerPose ultimaPosicion;
	private double anguloActual = 0;
	
	public double getAnguloActual() {
		return anguloActual;
	}
	
	public void setAnguloActual(double anguloActual) {
		this.anguloActual = anguloActual;
	}

	public Localizacion(SimulationInterface si) {														
		simu = si;				
	}

	private void waitForData() {
		while (!simu.isPose2DReady()) {
			try { Thread.sleep (100); } catch (Exception e) { }
		}		
	}

	public PlayerPose updateCoordenadas(){		
		simu.get2DPose (Conf.robotName);
		
		this.waitForData();
		ultimaPosicion = posicionActual;
		posicionActual = simu.getSimulationPose2D().getPose();
		//System.err.println("Coordenadas: " + posicionActual.getPx() + ", " + posicionActual.getPy());
													
		return posicionActual;		
	}
	
	
	public PlayerPose getTarget(int i){		
		simu.get2DPose (Conf.targetName + i);		
		this.waitForData();				 										
		return simu.getSimulationPose2D().getPose();		
	}
	
	
	public float getLatActual(){
		return posicionActual.getPx();
	}
	
	public float getLongActual(){
		return posicionActual.getPy();
	}
	
	public float getLatAnt(){
		return ultimaPosicion.getPx();
	}
	
	public float getLongAnt(){
		return ultimaPosicion.getPy();
	}
	
	public void printCoordenates() {
		System.out.println("lat:" + getLatActual() + ", long:" + getLongActual());
	}

	public void setPosicionActual(PlayerPose p) {
		simu.set2DPose(Conf.robotName, p);		
	}

}
