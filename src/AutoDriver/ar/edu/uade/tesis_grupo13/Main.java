package ar.edu.uade.tesis_grupo13;

import java.io.FileNotFoundException;

import org.xmlpull.v1.XmlPullParserException;

import com.thoughtworks.xstream.io.StreamException;

import javaclient2.structures.PlayerPose;
import ar.edu.uade.tesis_grupo13.autodriver.Autodriver;
import ar.edu.uade.tesis_grupo13.config.Conf;
import ar.edu.uade.tesis_grupo13.exceptions.NullMapException;
import ar.edu.uade.tesis_grupo13.robot.DharmaStage;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
				
		Autodriver ad = Autodriver.getInstance();		
		ad.setDharma(new DharmaStage());		
		
		try {			
			ad.startWebServer();
			ad.cargarMapa();						
		}
		catch (FileNotFoundException e) {
			System.err.println("Mapa no encontrado: " + Conf.getInstance().getMapa());			
		}
		catch (XmlPullParserException e) {
			System.err.println("Error al parsear XML: " + e.getMessage());						
		}		
		catch (StreamException e) {
			System.err.println("Error al parsear XML: " + e.getMessage());						
		}
		catch (NullMapException e) {
			System.err.println("Error al leer grafo: " + e.getMessage());
		}
		catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			System.exit(1);
		}											
		

	}

}
