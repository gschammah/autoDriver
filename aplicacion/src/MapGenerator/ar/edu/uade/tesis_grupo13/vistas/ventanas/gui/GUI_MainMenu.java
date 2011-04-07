/*
 * Created by JFormDesigner on Mon Aug 23 19:11:01 ART 2010
 */

package ar.edu.uade.tesis_grupo13.vistas.ventanas.gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.File;
import java.text.DecimalFormat;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.LayoutStyle;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.SoftBevelBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ar.edu.uade.tesis_grupo13.controller.Controller_MainMenu;
import ar.edu.uade.tesis_grupo13.modelo.CoordenadaSoftware;
import ar.edu.uade.tesis_grupo13.vistas.imagenes.componentes.MapaComponent;
import ar.edu.uade.tesis_grupo13.vistas.ventanas.VistaMainMenu;

/**
 * @author Gabriel Schammah
 */
public class GUI_MainMenu extends JFrame {
		
	private static final long serialVersionUID = 1L;
		
	private VistaMainMenu vistaPadre;
	private DecimalFormat formatter;
	
	public GUI_MainMenu(VistaMainMenu vistaMainMenu) {
		vistaPadre = vistaMainMenu;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initComponents();
	}


	private void menuSalirActionPerformed(ActionEvent e) {
		((Controller_MainMenu)vistaPadre.getControlador()).salir();
	}
	
	private void abrirMapaActionPerformed(ActionEvent e) {		
		JFileChooser fc = new JFileChooser();
		fc.showOpenDialog(this);
		File selFile = fc.getSelectedFile();
		if (selFile != null) {
			((Controller_MainMenu)vistaPadre.getControlador()).cargarImagen(selFile.getAbsolutePath());
		}
	}

	private void menuGrillaActionPerformed(ActionEvent e) {
		((Controller_MainMenu)vistaPadre.getControlador()).toggleGrilla(menuGrilla.getState());
	}

	private void menuMapaOriginalActionPerformed(ActionEvent e) {
		((Controller_MainMenu)vistaPadre.getControlador()).toggleOriginal(menuMapaOriginal.getState());
	}

	private void menuMostrarGrafoActionPerformed(ActionEvent e) {
		((Controller_MainMenu)vistaPadre.getControlador()).toggleGrafo(menuMostrarGrafo.getState());
	}
	
	private void menuMostrarBordesActionPerformed(ActionEvent e) {
		((Controller_MainMenu)vistaPadre.getControlador()).toggleBordes(menuMostrarBordes.getState());
	}
	
	private void exportarMapaActionPerformed(ActionEvent e) {		
		((Controller_MainMenu)vistaPadre.getControlador()).exportarMapa();
	}

	private void imagePanelMouseClicked(MouseEvent e) {
		if (!e.isControlDown()) {
			if (e.getButton() == MouseEvent.BUTTON1) {
				((Controller_MainMenu)vistaPadre.getControlador()).setStartPoint(e.getX(), e.getY());
			} else {
				((Controller_MainMenu)vistaPadre.getControlador()).setEndPoint(e.getX(), e.getY());
			}
		} else if (e.getButton() == MouseEvent.BUTTON1) {
			((Controller_MainMenu)vistaPadre.getControlador()).showInfo(e.getX(), e.getY());
		}
	}

	private void imagePanelMouseMoved(MouseEvent e) {
		try {
			CoordenadaSoftware coord = ((Controller_MainMenu)vistaPadre.getControlador()).getGridCoord(e.getX(), e.getY());			
			setCoord(coord);
		} catch (NullPointerException exc) {}
	}

	private void btnCalcularRutaActionPerformed(ActionEvent e) {
		((Controller_MainMenu)vistaPadre.getControlador()).calcularRuta();
	}

	private void menuMostrarCaminoActionPerformed(ActionEvent e) {
		((Controller_MainMenu)vistaPadre.getControlador()).togglePath(menuMostrarCamino.getState());
	}
	
	private void spnGridSizeStateChanged(ChangeEvent e) {
		((Controller_MainMenu)vistaPadre.getControlador()).setGridSize((Integer) spnGridSize.getValue());
	}	

	private void imagePanelMouseWheelMoved(MouseWheelEvent e) {
		((Controller_MainMenu)vistaPadre.getControlador()).setZoomWheel(e.getUnitsToScroll());
	}

	private void sliZoomStateChanged(ChangeEvent e) {
		((Controller_MainMenu)vistaPadre.getControlador()).setZoom(sliZoom.getValue());
	}

	private void menuSetupActionPerformed(ActionEvent e) {
		((Controller_MainMenu)vistaPadre.getControlador()).showSettings();
	}

	private void menuConectarActionPerformed(ActionEvent e) {
		if (menuConectar.getState()) {
			try {
				//((Controller_MainMenu)vistaPadre.getControlador()).conectar();
				menuConectar.setText("Desconectar");
			} catch (Exception ex) {
				menuConectar.setState(false);
			}
			
		} else {
			((Controller_MainMenu)vistaPadre.getControlador()).desconectar();
			menuConectar.setText("Conectar");
		}
	}

	
	
	private void initComponents() {		
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		formatter = new DecimalFormat("#0.00");
		mainPanel = new JPanel();
		menuBar1 = new JMenuBar();
		menu1 = new JMenu();
		abrirMapa = new JMenuItem();
		exportarMapa = new JMenuItem();
		menuSalir = new JMenuItem();
		menu2 = new JMenu();
		menuGrilla = new JCheckBoxMenuItem();
		menuMapaOriginal = new JCheckBoxMenuItem();
		menuMostrarGrafo = new JCheckBoxMenuItem();
		menuMostrarBordes = new JCheckBoxMenuItem();
		menuMostrarCamino = new JCheckBoxMenuItem();
		menu3 = new JMenu();
		menuConectar = new JCheckBoxMenuItem();
		menuSetup = new JMenuItem();
		statusBar = new JPanel();
		lblCoord = new JLabel();
		lblStatus = new JLabel();
		scrollBarImage = new JScrollPane();
		imagePanel = new GUI_MainPanel();
		toolBar = new JToolBar();
		panel1 = new JPanel();
		btnCalcularRuta = new JButton();
		lblGridSize = new JLabel();
		spnGridSize = new JSpinner();
		sliZoom = new JSlider();
		lblZoom = new JLabel();
		lblZoomValue = new JLabel();
		scrollInfo = new JScrollPane();
		txtInfo = new JTextArea();

		//======== this ========
		setLayout(new GridLayout());

		//======== mainPanel ========
		{

			//======== menuBar1 ========
			{

				//======== menu1 ========
				{
					menu1.setText("Archivo");
					menu1.setMnemonic('A');

					//---- abrirMapa ----
					abrirMapa.setText("Abrir mapa...");
					abrirMapa.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_MASK));
					abrirMapa.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							abrirMapaActionPerformed(e);
						}
					});
					menu1.add(abrirMapa);
					menu1.addSeparator();
					
					//---- Exportar Mapa ----
					exportarMapa.setText("Exportar mapa...");					
					exportarMapa.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							exportarMapaActionPerformed(e);
						}
						
					});
					menu1.add(exportarMapa);
					menu1.addSeparator();

					//---- menuSalir ----
					menuSalir.setText("Salir");
					menuSalir.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							menuSalirActionPerformed(e);
						}
					});
					menu1.add(menuSalir);
				}
				menuBar1.add(menu1);

				//======== menu2 ========
				{
					menu2.setText("Mostrar");
					menu2.setMnemonic('O');

					//---- menuGrilla ----
					menuGrilla.setText("Grilla");
					menuGrilla.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, KeyEvent.CTRL_MASK));
					menuGrilla.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							menuGrillaActionPerformed(e);
						}
					});
					menu2.add(menuGrilla);

					//---- menuMapaOriginal ----
					menuMapaOriginal.setText("Mapa original");
					menuMapaOriginal.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_MASK));
					menuMapaOriginal.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							menuMapaOriginalActionPerformed(e);
						}
					});
					menu2.add(menuMapaOriginal);

					//---- menuMostrarGrafo ----
					menuMostrarGrafo.setText("Grafo");
					menuMostrarGrafo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, KeyEvent.CTRL_MASK));
					menuMostrarGrafo.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							menuMostrarGrafoActionPerformed(e);
						}
					});
					menu2.add(menuMostrarGrafo);

					//---- menuMostrarBordes ----
					menuMostrarBordes.setText("Bordes");
					menuMostrarBordes.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, KeyEvent.CTRL_MASK));
					menuMostrarBordes.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							menuMostrarBordesActionPerformed(e);
						}
					});
					menu2.add(menuMostrarBordes);

					//---- menuMostrarCamino ----
					menuMostrarCamino.setText("Camino");
					menuMostrarCamino.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_MASK));
					menuMostrarCamino.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							menuMostrarCaminoActionPerformed(e);
						}
					});
					menu2.add(menuMostrarCamino);
				}
				menuBar1.add(menu2);

				//======== menu3 ========
				{
					menu3.setText("Herramientas");
					menu3.setMnemonic('H');

					//---- menuConectar ----
					menuConectar.setText("Conectar");
					menuConectar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0));
					menuConectar.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							menuConectarActionPerformed(e);
						}
					});
					menu3.add(menuConectar);
					menu3.addSeparator();

					//---- menuSetup ----
					menuSetup.setText("Configuraci\u00f3n");
					menuSetup.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F12, 0));
					menuSetup.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							menuSetupActionPerformed(e);
						}
					});
					menu3.add(menuSetup);
				}
				menuBar1.add(menu3);
			}

			//======== statusBar ========
			{
				statusBar.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));

				//---- lblCoord ----
				lblCoord.setText("x:0 y:0");

				//---- lblStatus ----
				lblStatus.setText("Desconectado");

				GroupLayout statusBarLayout = new GroupLayout(statusBar);
				statusBar.setLayout(statusBarLayout);
				statusBarLayout.setHorizontalGroup(
					statusBarLayout.createParallelGroup()
						.addGroup(GroupLayout.Alignment.TRAILING, statusBarLayout.createSequentialGroup()
							.addComponent(lblCoord, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 508, Short.MAX_VALUE)
							.addComponent(lblStatus))
				);
				statusBarLayout.setVerticalGroup(
					statusBarLayout.createParallelGroup()
						.addGroup(statusBarLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
							.addComponent(lblCoord, GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
							.addComponent(lblStatus, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
				);
			}

			//======== scrollBarImage ========
			{

				//---- imagePanel ----
				imagePanel.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						imagePanelMouseClicked(e);
					}
				});
				imagePanel.addMouseMotionListener(new MouseMotionAdapter() {
					@Override
					public void mouseMoved(MouseEvent e) {
						imagePanelMouseMoved(e);
					}
				});
				imagePanel.addMouseWheelListener(new MouseWheelListener() {
					@Override
					public void mouseWheelMoved(MouseWheelEvent e) {
						imagePanelMouseWheelMoved(e);
					}
				});
				scrollBarImage.setViewportView(imagePanel);
			}

			//======== toolBar ========
			{
				toolBar.setFloatable(false);

				//======== panel1 ========
				{

					//---- btnCalcularRuta ----
					btnCalcularRuta.setText("Calcular Ruta");
					btnCalcularRuta.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							btnCalcularRutaActionPerformed(e);
						}
					});

					//---- lblGridSize ----
					lblGridSize.setText("Tama\u00f1o Grilla:");

					//---- spnGridSize ----
					spnGridSize.setModel(new SpinnerNumberModel(10, 5, 50, 5));
					spnGridSize.addChangeListener(new ChangeListener() {
						@Override
						public void stateChanged(ChangeEvent e) {
							spnGridSizeStateChanged(e);
						}
					});

					//---- sliZoom ----
					sliZoom.setMinimum(80);
					sliZoom.setMaximum(150);
					sliZoom.setSnapToTicks(true);
					sliZoom.setMajorTickSpacing(10);
					sliZoom.setValue(100);
					sliZoom.setMinorTickSpacing(10);
					sliZoom.addChangeListener(new ChangeListener() {
						@Override
						public void stateChanged(ChangeEvent e) {
							sliZoomStateChanged(e);
						}
					});

					//---- lblZoom ----
					lblZoom.setText("Zoom");

					//---- lblZoomValue ----
					lblZoomValue.setText("100%");

					GroupLayout panel1Layout = new GroupLayout(panel1);
					panel1.setLayout(panel1Layout);
					panel1Layout.setHorizontalGroup(
						panel1Layout.createParallelGroup()
							.addGroup(panel1Layout.createParallelGroup()
								.addGroup(panel1Layout.createSequentialGroup()
									.addComponent(btnCalcularRuta, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
									.addContainerGap(635, Short.MAX_VALUE)))
							.addGroup(panel1Layout.createSequentialGroup()
								.addGap(146, 146, 146)
								.addComponent(lblGridSize)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(spnGridSize, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(lblZoom)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(sliZoom, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(lblZoomValue)
								.addContainerGap(154, Short.MAX_VALUE))
					);
					panel1Layout.setVerticalGroup(
						panel1Layout.createParallelGroup()
							.addGroup(panel1Layout.createParallelGroup()
								.addGroup(panel1Layout.createSequentialGroup()
									.addComponent(btnCalcularRuta)
									.addGap(3, 3, 3)))
							.addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
								.addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
									.addComponent(lblZoomValue, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
									.addComponent(sliZoom, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
									.addGroup(panel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(lblGridSize, GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
										.addComponent(spnGridSize, GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
										.addComponent(lblZoom)))
								.addGap(3, 3, 3))
					);
				}
				toolBar.add(panel1);
			}

			//======== scrollInfo ========
			{

				//---- txtInfo ----
				txtInfo.setEditable(false);
				txtInfo.setBackground(Color.white);
				txtInfo.setWrapStyleWord(true);
				scrollInfo.setViewportView(txtInfo);
			}

			GroupLayout mainPanelLayout = new GroupLayout(mainPanel);
			mainPanel.setLayout(mainPanelLayout);
			mainPanelLayout.setHorizontalGroup(
				mainPanelLayout.createParallelGroup()
					.addGroup(mainPanelLayout.createSequentialGroup()
						.addGap(0, 0, 0)
						.addGroup(mainPanelLayout.createParallelGroup()
							.addComponent(toolBar, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 770, Short.MAX_VALUE)
							.addComponent(menuBar1, GroupLayout.DEFAULT_SIZE, 770, Short.MAX_VALUE)
							.addComponent(statusBar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGroup(GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
								.addComponent(scrollBarImage, GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(scrollInfo, GroupLayout.PREFERRED_SIZE, 272, GroupLayout.PREFERRED_SIZE))))
			);
			mainPanelLayout.setVerticalGroup(
				mainPanelLayout.createParallelGroup()
					.addGroup(mainPanelLayout.createSequentialGroup()
						.addComponent(menuBar1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGap(0, 0, 0)
						.addComponent(toolBar, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addGap(0, 0, 0)
						.addGroup(mainPanelLayout.createParallelGroup()
							.addComponent(scrollInfo, GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
							.addComponent(scrollBarImage, GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE))
						.addGap(0, 0, 0)
						.addComponent(statusBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
			);
		}
		add(mainPanel);
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JPanel mainPanel;
	private JMenuBar menuBar1;
	private JMenu menu1;
	private JMenuItem abrirMapa;
	private JMenuItem exportarMapa;
	private JMenuItem menuSalir;
	private JMenu menu2;
	private JCheckBoxMenuItem menuGrilla;
	private JCheckBoxMenuItem menuMapaOriginal;
	private JCheckBoxMenuItem menuMostrarGrafo;
	private JCheckBoxMenuItem menuMostrarBordes;
	private JCheckBoxMenuItem menuMostrarCamino;
	private JMenu menu3;
	private JCheckBoxMenuItem menuConectar;
	private JMenuItem menuSetup;
	private JPanel statusBar;
	private JLabel lblCoord;
	private JLabel lblStatus;
	private JScrollPane scrollBarImage;
	private GUI_MainPanel imagePanel;
	private JToolBar toolBar;
	private JPanel panel1;
	private JButton btnCalcularRuta;
	private JLabel lblGridSize;
	private JSpinner spnGridSize;
	private JSlider sliZoom;
	private JLabel lblZoom;
	private JLabel lblZoomValue;
	private JScrollPane scrollInfo;
	private JTextArea txtInfo;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
	
	public GUI_MainPanel getImagePanel() {
		return imagePanel;
	}
				
	public void addLayer(String layerName, MapaComponent image) {
		imagePanel.addLayer(layerName, image);
		if (layerName.equals("path")) {
			menuMostrarCamino.setState(true);
		}
	}
	
	public boolean hasLayer(String layerName) {
		return imagePanel.hasLayer(layerName);
	}

	public void removeLayer(String layerName) {
		imagePanel.removeLayer(layerName);
	}
	
	public void setCoord(CoordenadaSoftware coord) {		
		lblCoord.setText("X: " + formatter.format(coord.getX()) + "; Y: " + formatter.format(coord.getY()));		
	}


	public void clearLayers() {		
		imagePanel.clearLayers();
	}


	public void setInfoText(String txt) {		
		txtInfo.setText(txt);
	}


	public void setSliderSize(double val) {		
		sliZoom.setValue((int)val);
		lblZoomValue.setText((int)val + "%");
	}


	public void setStatus(String status) {
		lblStatus.setText(status);
	}
		
	
}
