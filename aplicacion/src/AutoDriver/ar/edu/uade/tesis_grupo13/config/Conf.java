package ar.edu.uade.tesis_grupo13.config;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

@XStreamAlias("Config")
public class Conf {
	
	private float speed; //aprox. 50cm/seg
	private float speedReal; //50cm/seg
	private float turnSpeed; //aprox. 45 deg/seg
	private double turnSpeedReal;
	private String robotName;
	private int diffMagnitude; //1 metro	
	private String targetName;
	private String mapa;

	@XStreamOmitField
	private static Conf instance = null;

	public float getSpeed() {
		return speed;
	}

	public float getSpeedReal() {
		return speedReal;
	}

	public float getTurnSpeed() {
		return turnSpeed;
	}

	public double getTurnSpeedReal() {
		return turnSpeedReal;
	}

	public String getRobotName() {
		return robotName;
	}

	public int getDiffMagnitude() {
		return diffMagnitude;
	}

	public String getTargetName() {
		return targetName;
	}
	
	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public void setSpeedReal(float speedReal) {
		this.speedReal = speedReal;
	}

	public void setTurnSpeed(float turnSpeed) {
		this.turnSpeed = turnSpeed;
	}

	public void setTurnSpeedReal(double turnSpeedReal) {
		this.turnSpeedReal = turnSpeedReal;
	}

	public void setRobotName(String robotName) {
		this.robotName = robotName;
	}

	public void setDiffMagnitude(int diffMagnitude) {
		this.diffMagnitude = diffMagnitude;
	}

	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}

	public static void setInstance(Conf instance) {
		Conf.instance = instance;
	}
		
	public static Conf getInstance() {
		if (instance == null) {
			instance = new Conf();
		}
		return instance;
	}

	public String getMapa() {		
		return mapa;
	}
	
	public void setMapa(String mapa) {		
		this.mapa = mapa;
	}
	
}
