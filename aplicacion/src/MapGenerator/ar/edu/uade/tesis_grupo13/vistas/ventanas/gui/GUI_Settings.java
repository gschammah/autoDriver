/*
 * Created by JFormDesigner on Mon Sep 13 17:41:50 ART 2010
 */

package ar.edu.uade.tesis_grupo13.vistas.ventanas.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ar.edu.uade.tesis_grupo13.vistas.ventanas.VistaSettings;

import com.jgoodies.forms.factories.Borders;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

/**
 * @author Gabriel Schammah
 */
public class GUI_Settings extends JDialog {
		
	private static final long serialVersionUID = 1L;
	private VistaSettings vistaPadre;
	
	public GUI_Settings(VistaSettings vistaSettings) {
		super();		
		vistaPadre = vistaSettings;
		initComponents();
	}

	public GUI_Settings(Dialog owner) {
		super(owner);
		initComponents();
	}

	private void okButtonActionPerformed(ActionEvent e) {		
		vistaPadre.saveSettings();
	}
	
	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		dialogPane = new JPanel();
		contentPanel = new JPanel();
		lblServer = new JLabel();
		txtServer = new JTextField();
		lblPort = new JLabel();
		txtPort = new JTextField();
		buttonBar = new JPanel();
		okButton = new JButton();
		cancelButton = new JButton();
		CellConstraints cc = new CellConstraints();

		//======== this ========
		setTitle("Configuraci\u00f3n");
		setModal(true);
		setResizable(false);
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		//======== dialogPane ========
		{
			dialogPane.setBorder(Borders.DIALOG_BORDER);
			dialogPane.setLayout(new BorderLayout());

			//======== contentPanel ========
			{
				contentPanel.setLayout(new FormLayout(
					"default, $lcgap, 64dlu",
					"2*(default, $lgap), default"));

				//---- lblServer ----
				lblServer.setText("Servidor:");
				contentPanel.add(lblServer, cc.xy(1, 1));
				contentPanel.add(txtServer, cc.xy(3, 1));

				//---- lblPort ----
				lblPort.setText("Puerto:");
				contentPanel.add(lblPort, cc.xy(1, 3));
				contentPanel.add(txtPort, cc.xy(3, 3));
			}
			dialogPane.add(contentPanel, BorderLayout.CENTER);

			//======== buttonBar ========
			{
				buttonBar.setBorder(Borders.BUTTON_BAR_GAP_BORDER);
				buttonBar.setLayout(new FormLayout(
					"$glue, $button, $rgap, $button",
					"pref"));

				//---- okButton ----
				okButton.setText("OK");
				okButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						okButtonActionPerformed(e);
					}
				});
				buttonBar.add(okButton, cc.xy(2, 1));

				//---- cancelButton ----
				cancelButton.setText("Cancelar");
				buttonBar.add(cancelButton, cc.xy(4, 1));
			}
			dialogPane.add(buttonBar, BorderLayout.SOUTH);
		}
		contentPane.add(dialogPane, BorderLayout.CENTER);
		setSize(300, 195);
		setLocationRelativeTo(getOwner());
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	private JPanel dialogPane;
	private JPanel contentPanel;
	private JLabel lblServer;
	private JTextField txtServer;
	private JLabel lblPort;
	private JTextField txtPort;
	private JPanel buttonBar;
	private JButton okButton;
	private JButton cancelButton;
	// JFormDesigner - End of variables declaration  //GEN-END:variables

	public String getServerText() {
		return txtServer.getText();
	}

	public void setServer(String server) {		
		txtServer.setText(server);
	}
	
	public int getPort() {
		return new Integer(txtPort.getText());
	}

	public void setPort(int port) {		
		txtPort.setText(new Integer(port).toString());
	}
}
