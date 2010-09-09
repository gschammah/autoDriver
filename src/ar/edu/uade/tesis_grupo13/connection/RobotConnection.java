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

		// Connect to the Player server and request access to Position
		if (client == null) {
			try {
				client = new PlayerClient ("localhost", 6665);
			} catch (PlayerException e) {			
				System.err.println ("Error connecting to Player: ");
				System.err.println ("    [ " + e.toString() + " ]");
				System.exit (1);			
			}
		} 
        
		return client;
	}
	
}
