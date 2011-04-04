package ar.edu.uade.tesis_grupo13.webServer.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.uade.tesis_grupo13.autodriver.Autodriver;
import ar.edu.uade.tesis_grupo13.exceptions.NullMapException;


@SuppressWarnings("serial")
public class RobotServlet extends HttpServlet {
	
	private double lat, lon;
	private Autodriver autoDriver;
    
    @Override
    public void init() throws ServletException {    	
    	super.init();
    	autoDriver = Autodriver.getInstance();    	
    }    
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {    	
    	
    	getServletConfig().getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        
        boolean irATarget = false;
        
        try {
	        lat = Double.parseDouble(request.getParameter("lat"));
	        lon = Double.parseDouble(request.getParameter("lon"));	        	       
        } catch (NumberFormatException e) {
        	irATarget = true;
        }
        
        try {
        	
	        if (irATarget) {
	        	autoDriver.irA();
	        } else {
	        	autoDriver.irA(lat, lon);
	        }
	        request.setAttribute("ok", true);
        } catch (NullMapException e) {
        	e.printStackTrace();
        }
        	
       doGet(request, response);
                
    }
}