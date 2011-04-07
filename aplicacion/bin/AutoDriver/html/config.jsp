<%@ page language="java" import="ar.edu.uade.tesis_grupo13.config.Conf" %>
<%
   Conf conf = Conf.getInstance();
%>

<HTML>
 <HEAD>
  <TITLE>autoDriver</TITLE>
  <style type="text/css">
        
        fieldset{
            width: 600px;
            margin: auto;   
        }
        
        h1, h2 {
            text-align: center;
        }
                
        label {
            width: 200px;
            display: block;
            float: left;
            text-align: right;
            padding-right: 9px;            
        }
        
        #submit {
            margin-top: 10px;
            float: right;
        }
  </style>
 </HEAD>
 <BODY>
  <H1>Configuraci&oacute;n autoDriver</H1> 
  
  <% if (request.getAttribute("saved") != null) { %>
  <h2>Configuración guardada exitosamente</h2>
  <% }%>
  
  <form enctype="multipart/form-data" method="POST" action="/config">
	  <fieldset>
	    <legend>Datos</legend>
	    <label for="speed">Velocidad:</label>
	    <input id="speed" name="speed" value="<%=conf.getSpeed() %>"></input> <br />
	    <label for="turnSpeed">Velocidad de giro:</label>
	    <input id="turnSpeed" name="turnSpeed" value="<%=conf.getTurnSpeed() %>"></input><br />
	    <label for="diffMagnitude">Diferencia de magnitud:</label>
	    <input id="diffMagnitude" name="diffMagnitude" value="<%=conf.getDiffMagnitude() %>"></input><br/>
	    <label for="ubicacionMapa">Ubicación del mapa (XML):</label>
        <input id="ubicacionMapa" name="ubicacionMapa" value="<%=conf.getMapa() %>"></input><br/>
	    <label for="archivo">Archivo XML:</label>
        <input name="file" id="archivo" type="file"></input><br/>
	    <div>&nbsp;</div>
	    <label for="robotName">Nombre del robot:</label>
	    <input id="robotName" name="robotName" value="<%=conf.getRobotName() %>"></input>
	     (stage s&oacute;lamente)<br />
	    <label for="targetName">Nombre de los target:</label>
	    <input id="targetName" name="targetName" value="<%=conf.getTargetName() %>"></input>
	     (stage s&oacute;lamente)<br />
	     <input id="submit" type="submit" value="Guardar" />    
	  </fieldset>	  
  </form>
  
 </BODY>
</HTML>