package ar.edu.uade.tesis_grupo13.robot.interfaces;

import javaclient2.structures.PlayerPose;

public interface DharmaRobot {

	/*
	 * Mueve el robot hacia adelante indefinidamente
	 */
	public void moverAdelante();
	
	/*
	 * Mueve el robot hacia atrás indefinidamente
	 */
	public void moverAtras();
	
	/*
	 * Gira al robot hacia la izquierda indefinidamente
	 */
	public void girarIzquierda();
	
	/*
	 * Gira al robot hacia la derecha indefinidamente
	 */
	public void girarDerecha();
	
	/*
	 * Detiene al robot
	 */
	public void detener();
	
	/*
	 * Obtiene las coordenadas
	 */
	public PlayerPose getCoordenadas();
	
	/*
	 * Cierra conexion
	 */
	public void finalizar();
			
	
	
}
