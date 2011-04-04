<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>autoDriver</title>
<style>

	h2 {
		width: 800px;
		color: navy;
	}
	legend {
		font-size: 17px;
		font-weight: bold;
	}
	fieldset {
		width: 400px;	
		margin: auto;
	}
	label {
		float: left;
		width: 100px;	
	}
	body {
		font-family: "Trebuchet MS"
	}
	
	h2 {
		margin: auto;
		margin-bottom: 80px;	
	}
	
	h3 {
	   text-align: center;
	}
	
	button {
		float: right;
		margin-right: 65px;
		margin-top: 20px;
	}
		
</style>
</head>
<body>
	<div>
	<h2>autoDriver: Sistema de viaje (origen/destino) con trayectoria óptima y detección de obstáculos, guiado por GPS para ambientes externos.</h2>
	
	<% if (request.getAttribute("ok") != null) { %>
	  <h3>Orden aceptada correctamente</h3>
	<% }%>
	
	<form method="post" action="/">
		<fieldset>
			<legend>Datos de entrada</legend>			
			<label for="lat">Latitud:</label>
			<input name="lat" id="lat"></input><br/>
			<label for="lon">Longitud:</label>
			<input name="lon" id="lon"></input>
			<br></br>
			<button>IR</button>
		</fieldset>
	</form>
	</div>
</body>
</html>