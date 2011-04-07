package ar.edu.uade.tesis_grupo13.webServer.servlets;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.uade.tesis_grupo13.autodriver.Autodriver;
import ar.edu.uade.tesis_grupo13.config.Conf;

@SuppressWarnings("serial")
public class ConfigServlet extends HttpServlet {
	
	private Autodriver autoDriver;
	
	@Override
	public void init() throws ServletException {		
		super.init();
		autoDriver = Autodriver.getInstance();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	
		try {
			autoDriver.loadConfig();
			getServletConfig().getServletContext().getRequestDispatcher("/config.jsp").forward(req, resp);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		Conf conf = Conf.getInstance();
		conf.setSpeed(Float.parseFloat(req.getParameter("speed")));
		conf.setTurnSpeed(Float.parseFloat(req.getParameter("turnSpeed")));
		conf.setDiffMagnitude(Integer.parseInt(req.getParameter("diffMagnitude")));
		conf.setRobotName(req.getParameter("robotName"));
		conf.setTargetName(req.getParameter("targetName"));
		conf.setMapa(req.getParameter("ubicacionMapa"));				
		
		autoDriver.saveConfig();
		
		File f = (File) req.getAttribute("file");
		if (f != null) {
	        autoDriver.guardarMapa(f);
	        autoDriver.cargarMapa();
		}
		
		req.setAttribute("saved", true);		
		
		doGet(req, resp);
	}
	
}
