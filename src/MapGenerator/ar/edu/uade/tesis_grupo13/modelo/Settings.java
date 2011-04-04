package ar.edu.uade.tesis_grupo13.modelo;

import ar.edu.uade.tesis_grupo13.MVCframework.modelo.Modelo;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("Settings")
public class Settings extends Modelo{
	
	private String server;
	private int port;

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

}
