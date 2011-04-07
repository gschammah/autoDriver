package ar.edu.uade.tesis_grupo13.connection;

import javaclient2.PlayerClient;
import javaclient2.PlayerException;


/*
 * 
 * Clase estatica que devuelve un objeto del tipo 
 * PlayerClient
 * 
 * */

public class RobotConnection {
	
	public static PlayerClient client = null;
	
	public static PlayerClient getConnection(){

		// Conexion al Servidor Player, puerto 6665
		if (client == null) {
			try {
				client = new PlayerClient ("localhost", 6665);
			} catch (PlayerException e) {			
				System.err.println ("Error conectando a Player: Esta el servidor Player corriendo?");
				System.exit (1);			
			}
		} 
        
		return client;
	}
	
}
