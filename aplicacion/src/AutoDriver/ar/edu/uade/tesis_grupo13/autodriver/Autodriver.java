package ar.edu.uade.tesis_grupo13.autodriver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import ar.edu.uade.tesis_grupo13.config.Conf;
import ar.edu.uade.tesis_grupo13.exceptions.NullMapException;
import ar.edu.uade.tesis_grupo13.robot.Robot;
import ar.edu.uade.tesis_grupo13.robot.interfaces.DharmaRobot;
import ar.edu.uade.tesis_grupo13.webServer.RobotServer;

import com.thoughtworks.xstream.XStream;

public class Autodriver {
	
	private Robot robot;	
	private RobotServer server;
	private Conf conf;
	private static Autodriver instance;	
	
	
	/*
	 * Singleton para poder ser accedida
	 * desde el servlet
	 */
	public static Autodriver getInstance() {
		if (instance == null) {
			instance = new Autodriver();
		} 
		return instance;
	}
	
	/*
	 * Constructor
	 */
	private Autodriver () {							
		
		try {
			loadConfig();
			robot = new Robot();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public void cargarMapa() throws FileNotFoundException {		
		robot.cargarGrafo(conf.getMapa());		
	}		
	
	public void loadConfig() throws FileNotFoundException {
		XStream xs = new XStream();
		xs.processAnnotations(Conf.class);
		Conf.setInstance((Conf) xs.fromXML(new FileReader(new File("cfg/config.xml"))));
		this.conf = Conf.getInstance();		
	}	
	
	public void saveConfig() throws IOException {
		XStream xs = new XStream();
		xs.processAnnotations(Conf.class);
		xs.toXML(Conf.getInstance(), new FileWriter(new File("cfg/config.xml")));
	}			
		
	public void finalizar() {
		System.err.println("Finalizado");
		robot.finalizar();		
	}
	
	public void startWebServer() throws Exception {		
		server = new RobotServer();
		server.start();
	}
	
	public void setDharma(DharmaRobot dharma) {		
		robot.setDharma(dharma);		
	}

	public void guardarMapa(File f) {
		f.renameTo(new File(conf.getMapa()));
		System.err.println("Mapa guardado exitosamente.");
	}
	
	public void irA() throws NullMapException {				
		robot.irA();
	}
	
	public void irA(double lat, double lon) throws NullMapException {			
		robot.irA(lat, lon);
	}

}
