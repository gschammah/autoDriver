package ar.edu.uade.tesis_grupo13.vistas.ventanas;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import ar.edu.uade.tesis_grupo13.MVCframework.vista.Vista;
import ar.edu.uade.tesis_grupo13.modelo.MapMaker;
import ar.edu.uade.tesis_grupo13.vistas.imagenes.componentes.MapaComponent;
import ar.edu.uade.tesis_grupo13.vistas.ventanas.gui.GUI_MainMenu;

public class VistaMainMenu extends Vista implements Observer {

	private GUI_MainMenu vistaGrafica;


	public VistaMainMenu() {

		vistaGrafica = new GUI_MainMenu(this);
		vistaGrafica.getImagePanel().setVistaPadre(this);
		vistaGrafica.setTitle("AutoDriver");
		vistaGrafica.setSize(1024, 768);
		vistaGrafica.setExtendedState(vistaGrafica.getExtendedState() | JFrame.MAXIMIZED_BOTH);
	
		this.centrarVista(vistaGrafica);	
		vistaGrafica.setVisible(true);		
	}
		
	public void setImage(MapaComponent img) {
		vistaGrafica.addLayer("base", img);
	}		

	public void salir() {		
		vistaGrafica.dispose();
	}

	public void removeLayer(String layer) {
		vistaGrafica.removeLayer(layer);	
	}
	
	public void addLayer(String layer, MapaComponent img) {
		vistaGrafica.addLayer(layer, img);	
	}

	public void clearLayers() {
		vistaGrafica.clearLayers();
	}

	public void setInfoText(String txt) {		
		vistaGrafica.setInfoText(txt);
	}

	public void showErrorPopup(String msg) {
		JOptionPane.showMessageDialog(vistaGrafica, msg, "Error", JOptionPane.ERROR_MESSAGE);		
	}

	public void clearBuffer() {
		vistaGrafica.getImagePanel().clearLayers();		
	}

	public boolean hasLayer(String layerName) {
		return vistaGrafica.hasLayer(layerName);		
	}

	@Override
	public void update(Observable o, Object arg) {
		vistaGrafica.clearLayers();
		vistaGrafica.setSliderSize(((MapMaker)getControlador().getModelo()).getConfig().zoom * 100);
	}

	public void setStatus(String status) {		
		vistaGrafica.setStatus(status);
	}	

}
