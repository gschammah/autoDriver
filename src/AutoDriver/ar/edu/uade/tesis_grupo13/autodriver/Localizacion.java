package ar.edu.uade.tesis_grupo13.autodriver;

import javaclient2.structures.PlayerPose;
import ar.edu.uade.tesis_grupo13.modelo.Coordenada;
import ar.edu.uade.tesis_grupo13.robot.DharmaStage;
import ar.edu.uade.tesis_grupo13.robot.interfaces.DharmaRobot;

public class Localizacion {
					
	private DharmaRobot dharma;
	private double anguloActual = 0;
	private PlayerPose posicionActual;
	private PlayerPose ultimaPosicion;
	
	
	public Localizacion(DharmaRobot dharma) {			
		this.dharma = dharma;
	}
	
	public double getAnguloActual() {
		return anguloActual;
	}
	
	public void setAnguloActual(double anguloActual) {
		this.anguloActual = anguloActual;
	}	
	

	public PlayerPose updateCoordenadas(){						
		
		ultimaPosicion = posicionActual;
		posicionActual = dharma.getCoordenadas();
		//System.err.println("Coordenadas: " + posicionActual.getPx() + ", " + posicionActual.getPy());
													
		return posicionActual;		
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
	
	public PlayerPose getPosicionActual() {
		return posicionActual;
	}

	public void setPosicionActual(float x, float y, float a) {
		if (dharma instanceof DharmaStage) {
			PlayerPose pose = new PlayerPose();
			pose.setPx(x);
			pose.setPy(y);
			pose.setPa(a);
			((DharmaStage) dharma).setPosicionActual(pose);
		}
	}

	public void setTarget(int i, Coordenada coord) {
		if (dharma instanceof DharmaStage) {
			((DharmaStage) dharma).setTarget(i, coord);
		}		
	}

	public PlayerPose getTarget(int i) {
		if (dharma instanceof DharmaStage) {
			return ((DharmaStage) dharma).getTarget(i);
		}
		return null;
	}

}
