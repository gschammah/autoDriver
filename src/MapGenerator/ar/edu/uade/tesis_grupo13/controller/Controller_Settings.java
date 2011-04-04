package ar.edu.uade.tesis_grupo13.controller;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import ar.edu.uade.tesis_grupo13.MVCframework.controlador.Controlador;
import ar.edu.uade.tesis_grupo13.modelo.Settings;
import ar.edu.uade.tesis_grupo13.vistas.ventanas.VistaSettings;

import com.thoughtworks.xstream.XStream;

public class Controller_Settings extends Controlador {

	private static Controller_Settings instance;
	private VistaSettings vista;
	private final String filename = "settings.xml";

	public Controller_Settings(Settings settings, VistaSettings vista) {
		super(settings, vista);
		instance = this;
		this.vista = vista;
		_loadXML(this.filename);
	}
	
	public void saveSettings(Settings settings) {		
		setModelo(settings);		
		_saveXML(settings, this.filename);
		vista.hide();
	}

	private void _saveXML(Settings settings, String path) {
		XStream xstream = new XStream();
		xstream.processAnnotations(Settings.class);
		
		try {			
			xstream.toXML(settings, new FileWriter(new File(path)));						
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private void _loadXML(String path) {
		XStream xstream = new XStream();
		xstream.processAnnotations(Settings.class);
		
		Settings settings;
		try {
			settings = (Settings) xstream.fromXML(new FileReader(new File(path)));
			this.setModelo(settings);
			vista.setSettings(settings);
		} catch (FileNotFoundException e) { 
			e.printStackTrace();
		}		
		
	}

	public void show() {
		_loadXML(this.filename);
		vista.show();		
	}

	public static Controller_Settings getInstance() {		
		return instance;
	}

					
}
