package ar.edu.uade.tesis_grupo13.robot;

import framework.api.Dharma;
import javaclient2.structures.PlayerPose;
import ar.edu.uade.tesis_grupo13.robot.interfaces.DharmaRobot;

public class DharmaWrapper implements DharmaRobot{
	
	private Dharma dharma;
	
	public DharmaWrapper() {
		dharma = new Dharma();		
	}

	@Override
	public void detener() {		
		dharma.Stop();
	}

	@Override
	public void finalizar() {		
	}

	@Override
	public PlayerPose getCoordenadas() {
		PlayerPose pose = new PlayerPose();
		pose.setPx(dharma.getLatitude());
		pose.setPy(dharma.getLongitude());
		return pose;
	}

	@Override
	public void girarDerecha() {		
		dharma.GirarDer();
	}

	@Override
	public void girarIzquierda() {		
		dharma.GirarIzq();
	}

	@Override
	public void moverAdelante() {
		dharma.Adelante();
	}

	@Override
	public void moverAtras() {		
		dharma.Atras();
	}

}
