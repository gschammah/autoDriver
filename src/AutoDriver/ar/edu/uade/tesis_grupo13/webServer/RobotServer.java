package ar.edu.uade.tesis_grupo13.webServer;

import org.apache.jasper.servlet.JspServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.servlets.MultiPartFilter;

import ar.edu.uade.tesis_grupo13.webServer.servlets.ConfigServlet;
import ar.edu.uade.tesis_grupo13.webServer.servlets.RobotServlet;

public class RobotServer {

	private Server server;

	public void createServer() {
		
		server = new Server(9090);	
		
		//servlet para subir archivos
		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
		context.setContextPath("/");	 
	    context.addServlet(new ServletHolder(new RobotServlet()),"/");	    
	    	    
        //filtro para procesar multipart data forms
        FilterHolder filter = new FilterHolder(MultiPartFilter.class);
        filter.setInitParameter("deleteFiles", "true");
        //servlet.setAttribute("javax.servlet.context.tempdir", new File("tmp"));        
                
        //le asigno el filtro al servlet de configuracion
        context.addFilter(filter, "/config", 1);
        
        //servlet para configuracion
        context.setResourceBase("html");
	    context.addServlet(ConfigServlet.class,"/config");
	    context.addServlet(JspServlet.class, "*.jsp");        	    	    	           
 							        
        server.setHandler(context);	   
	 	
	}
	
	public Server getServer() {
		return server;
	}

	public RobotServer() {		
		createServer();		
	}

	public void start() throws Exception {		
		server.start();
	}
	
	public void stop() throws Exception {
		server.stop();
	}
	
	
}
