package ar.edu.uade.tesis_grupo13;

import ar.edu.uade.tesis_grupo13.controller.Controller_MainMenu;
import ar.edu.uade.tesis_grupo13.controller.Controller_Settings;
import ar.edu.uade.tesis_grupo13.modelo.MapMaker;
import ar.edu.uade.tesis_grupo13.vistas.ventanas.VistaMainMenu;
import ar.edu.uade.tesis_grupo13.vistas.ventanas.VistaSettings;

public class Main_GUI {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//inicializo configuracion
		new Controller_Settings(null, new VistaSettings());
		
		//creo modelo y vista
		MapMaker mapa = MapMaker.getInstance();		
		Controller_MainMenu main = new Controller_MainMenu(mapa, new VistaMainMenu());
		
		//cargo imagen inicial
		main.cargarImagen("mapas/autolab.png");

	}

}
