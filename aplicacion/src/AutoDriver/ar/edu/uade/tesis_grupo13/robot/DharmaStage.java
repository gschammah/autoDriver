package ar.edu.uade.tesis_grupo13.robot;

import javaclient2.PlayerClient;
import javaclient2.Position2DInterface;
import javaclient2.SimulationInterface;
import javaclient2.structures.PlayerConstants;
import javaclient2.structures.PlayerPose;
import ar.edu.uade.tesis_grupo13.config.Conf;
import ar.edu.uade.tesis_grupo13.connection.RobotConnection;
import ar.edu.uade.tesis_grupo13.modelo.Coordenada;
import ar.edu.uade.tesis_grupo13.robot.interfaces.DharmaRobot;

/*
 * Controla al robot mediante el simulador
 */
public class DharmaStage implements DharmaRobot {
	
	private PlayerClient playerClient;
	private Position2DInterface posi;
	private SimulationInterface simu;
	private Conf conf;
	
	public DharmaStage() {
		conf = Conf.getInstance();
		playerClient = RobotConnection.getConnection();
					
		posi = playerClient.requestInterfacePosition2D(0, PlayerConstants.PLAYER_OPEN_MODE); 
		simu = playerClient.requestInterfaceSimulation(0, PlayerConstants.PLAYER_OPEN_MODE);
		
		playerClient.runThreaded(-1, -1);			
	}

	@Override
	public void detener() {
		posi.setSpeed (0, 0);		
	}

	@Override
	public void girarDerecha() {
		posi.setSpeed (0, -conf.getTurnSpeed());
	}

	@Override
	public void girarIzquierda() {
		posi.setSpeed (0, conf.getTurnSpeed());		
	}

	@Override
	public void moverAdelante() {
		posi.setSpeed (conf.getSpeed(), 0);		
	}

	@Override
	public void moverAtras() {
		posi.setSpeed (-conf.getSpeed(), 0);		
	}
	
	public PlayerPose getCoordenadas() {
		simu.get2DPose (conf.getRobotName());
		this.waitForData();
		return simu.getSimulationPose2D().getPose();
	}
	
	private void waitForData() {
		while (!simu.isPose2DReady()) {
			try { Thread.sleep (100); } catch (Exception e) { }
		}		
	}
	
	public void setTarget(int i, Coordenada coord) {
		PlayerPose pose = new PlayerPose();
		pose.setPx((float) coord.getX());
		pose.setPy((float) coord.getY());
		simu.set2DPose(conf.getTargetName() + i, pose);
	}
	
	public void setPosicionActual(PlayerPose p) {
		simu.set2DPose(conf.getRobotName(), p);		
	}
	
	public PlayerPose getTarget(int i){		
		simu.get2DPose (conf.getTargetName() + i);		
		this.waitForData();				 										
		return simu.getSimulationPose2D().getPose();		
	}
	
	@Override
	public void finalizar() {
		playerClient.close();
	}

}
