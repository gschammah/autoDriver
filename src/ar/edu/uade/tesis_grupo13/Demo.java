package ar.edu.uade.tesis_grupo13;

import javaclient2.structures.PlayerPose;
import ar.edu.uade.tesis_grupo13.autodriver.Autodriver;
import ar.edu.uade.tesis_grupo13.robot.Robot;


public class Demo {

	public void start() {
		
		    	        
		System.setProperty ("PlayerClient.debug", "true");
    	
		Autodriver ad = new Autodriver(new Robot());		
		//ad.posicionarRobot(-2f, -1f, (float)Math.toRadians(155));
		ad.getRobot().sleep(2000);
		
		ad.inicializar();
		
		PlayerPose target = ad.getLoc().getTarget(1);		
		//PlayerPose target2 = ad.getLoc().getTarget(2);
		
		ad.irA(target.getPx(), target.getPy());		
		
		//ad.irA(-2, 3);		
		//ad.irA(-4, 2);		
				
		//ad.posicionarDireccion(180);
				
		ad.close();
		
	}

}
