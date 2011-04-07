package ar.edu.uade.tesis_grupo13.vistas.ventanas;

import ar.edu.uade.tesis_grupo13.MVCframework.vista.Vista;
import ar.edu.uade.tesis_grupo13.controller.Controller_Settings;
import ar.edu.uade.tesis_grupo13.modelo.Settings;
import ar.edu.uade.tesis_grupo13.vistas.ventanas.gui.GUI_Settings;

public class VistaSettings extends Vista {

	private GUI_Settings vistaGrafica;
 

	public VistaSettings() {
		vistaGrafica = new GUI_Settings(this);										
	}			
			
	public void salir() {		
		vistaGrafica.dispose();
	}
	
	public void show() {
		vistaGrafica.setVisible(true);
	}

	public void saveSettings() {
		Settings settings = new Settings();
		settings.setServer(vistaGrafica.getServerText());
		settings.setPort(vistaGrafica.getPort());
		((Controller_Settings)getControlador()).saveSettings(settings);		
	}

	public void hide() {		 
		vistaGrafica.setVisible(false);
	}

	public void setSettings(Settings settings) {		
		vistaGrafica.setServer(settings.getServer());
		vistaGrafica.setPort(settings.getPort());
	}
	
}
