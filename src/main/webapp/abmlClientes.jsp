<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="entidades.Pais" %>
<%@ page import="entidades.Provincia" %>
<%@ page import="entidades.Localidad" %>
<%@ page import="entidades.Cliente" %>
<%@ page import= "java.sql.Date"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css"
  href="https://cdn.datatables.net/1.13.4/css/jquery.dataTables.min.css">
<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
<script type="text/javascript" charset="utf8"
  src="https://cdn.datatables.net/1.13.4/js/jquery.dataTables.min.js"></script>
<title>ABML clientes - Banco Honse</title>

<style>
    body {
      margin: 0;
      background: linear-gradient(
        0deg,
        rgba(44, 144, 170, 1) 0%,
        rgba(113, 190, 196, 0.616) 70%
      );
      font-family: system-ui, -apple-system, BlinkMacSystemFont, "Segoe UI",
        Roboto, Oxygen, Ubuntu, Cantarell, "Open Sans", "Helvetica Neue",
        sans-serif;
    }

    main {
      width: 100%;
      margin: 0 auto;
    }

    
    .contenedor-tabla {
    	width:90%;
    	margin: 0 auto;
        overflow-x: auto;
        padding: 1em;
        background: #fff;
        margin-bottom:5em;
        box-shadow: 15px 20px 10px rgba(2, 2, 2, 0.103);
    }

    table {
      width: 100%;
      border-collapse: collapse;
      text-align: center;
    }

    thead {
      background-color: #343a40;
      color: white;
      position: sticky;
      top: 0;
      z-index: 1;
    }

    th,
    td {
      border: 1px solid #ddd;
      padding: 1em;
      margin:0 auto;
      min-width: 120px;
    }

    input[type="text"],
    input[type="date"],
    select {
      width: 100%;
      padding: 4px;
      border: 1px solid #ccc;
      border-radius: 4px;
    }

    input[type="submit"] {
      padding: 6px 10px;
      border: none;
      border-radius: .5em;
      cursor: pointer;
      color: white;
    }

    .btn-warning {
      background-color: #c2830f;
    }

    .btn-danger {
      background-color: #dc3545;
    }

    #div-crear-cliente {
      width: 80%;
      margin: 0 auto 5em auto;

    }

    #div-crear-cliente form div {
      width: 100%;
    }
    #div-crear-cliente form {
      display: grid;
      max-width: 100%;
      grid-template-columns: 1fr 1fr;
      grid-template-rows: 1fr;
      gap: 1em;
      padding: 1em;
     background-color: white;
     box-shadow: 15px 20px 10px rgba(2, 2, 2, 0.103);
    }
    .form-lado {
      display: flex;
      flex-direction: column;
      gap: 1em;
      
    }
    .form-lado > div {
      display: flex;
      align-items: center;
      gap: 1em;
    }
    form label {
      width: 15em;
    }
    form input,
    form select {
      flex: 1;
      padding: 0.4em;
      border-radius: .5em;
      outline: none;
      border: 1px solid darkgray;
    }
    .form-submit{
          grid-column: 1 / -1; 
          display: flex;
          justify-content: center;
          
    }
    .form-submit button{
        padding: 1em;
        margin-top: 2em;
        border-radius: .5em;
        width: 20%;
        cursor: pointer;
        outline: none;
        background-color: rgba(77, 180, 187, 0.637);
        border: none;
        transition: all .2s ease;
    }
    .form-submit button:hover{
        background-color: rgba(38, 117, 122, 0.637);
    }
    h2{
        text-align: center;
    }
    
    #div-filtrar{
     width: 80%;
      margin: 0 auto 5em auto;
    }
      #div-filtrar form div {
      width: 100%;
    }
    #div-filtrar form {
      display: grid;
      max-width: 100%;
      /*grid-template-columns: 1fr 1fr 1fr;*/
      grid-template-rows: 1fr;
      gap: 1em;
      padding: 1em;
     background-color: white;
     box-shadow: 15px 20px 10px rgba(2, 2, 2, 0.103);
    }
    .form-filter{
     display: grid;
  grid-template-columns: repeat(3,1fr);
  gap: 1em;
    }
  </style>

</head>


  <body>
  <%@ include file="./HeaderAdmin.jsp" %> 
    <main>
      <div id="div-crear-cliente">
        <h2>DAR DE ALTA</h2>

        <form method="post" action="abmlClientesServlet" class="form-confirm" id="form-agregar-cliente">
          <div class="form-lado">
            <div>
              <label for="txtDni">DNI</label>
              <%
          	  String dniIngresado = "";
              if (request.getAttribute("dniIngresado") != null) {
            	  dniIngresado = request.getAttribute("dniIngresado").toString();
				}
   			  if(dniIngresado == null || dniIngresado == ""){
                    %>
			<input type="text" id="txtDni" pattern="^\d{1,8}$" title="Solo números. Máximo 8 caractéres" name="txtDni" 
			placeholder="Ingresar DNI" required /><% }else{%>
			<input type="text" id="txtDni" pattern="^\d{1,8}$" title="Solo números. Máximo 8 caractéres" name="txtDni" 
			value="<%=dniIngresado%>" required /><%} %>   
            </div>
            <div>
              <label for="txtCuil">CUIL</label>
             <%
          	  String cuilIngresado = "";
              if (request.getAttribute("cuilIngresado") != null) {
            	  cuilIngresado = request.getAttribute("cuilIngresado").toString();
				}
             if(cuilIngresado == null || cuilIngresado == ""){
                    %>
			<input type="text" id="txtCuil"  pattern="^\d{1,11}$" title="Solo números. Máximo 11 caractéres" name="txtCuil" 
			placeholder="Ingresar CUIL" required /><% }else{%>
			<input type="text" id="txtCuil"  pattern="^\d{1,11}$" title="Solo números. Máximo 11 caractéres" name="txtCuil"
			 value="<%= cuilIngresado %>" required /><%} %>   
            </div>

            <div>
              <label for="txtNombre">Nombre</label>
             <%
          	  String nombreIngresado = "";
              if (request.getAttribute("nombreIngresado") != null) {
            	  nombreIngresado = request.getAttribute("nombreIngresado").toString();
				}
             if(nombreIngresado == null || nombreIngresado == ""){
                    %>
			<input type="text" id="txtNombre" title ="Solo Letras. Máximo 30 caractéres" pattern="^[A-Za-z\s]{1,30}$" name="txtNombre" placeholder="Ingresar nombre" required /><% }else{%>
			<input type="text" id="txtNombre" title ="Solo Letras. Máximo 30 caractéres" pattern="^[A-Za-z\s]{1,30}$" name="txtNombre" value="<%= nombreIngresado%>" required /><%} %>
            </div>

            <div>
              <label for="txtApellido">Apellido</label>
              <%
          	  String apellidoIngresado = "";
              if (request.getAttribute("apellidoIngresado") != null) {
            	  apellidoIngresado = request.getAttribute("apellidoIngresado").toString();
				}
             if(apellidoIngresado == null || apellidoIngresado == ""){
                    %>
			<input type="text" id="txtApellido" title ="Solo Letras. Máximo 30 caractéres" pattern="^[A-Za-z\s]{1,30}$" name="txtApellido" placeholder="Ingresar apellido" required/><% }else{%>
			<input type="text" id="txtApellido" title ="Solo Letras. Máximo 30 caractéres" pattern="^[A-Za-z\s]{1,30}$" name="txtApellido" value="<%= apellidoIngresado %>" required/><%} %>
            </div>

            <div>
				<label for="ddlSexo">Sexo</label>
				<select id="ddlSexo" name="ddlSexo" required>
				<%
				    String sexoSeleccionado = "";
				    if (request.getAttribute("sexoSeleccionado") != null) {
				        sexoSeleccionado = request.getAttribute("sexoSeleccionado").toString();
				    }
				
				    if (sexoSeleccionado == null || sexoSeleccionado.equals("")) {
				%>
				    <option value="" selected>Seleccione</option>
				    <option value="Masculino">Masculino</option>
				    <option value="Femenino">Femenino</option>
				<%
				    } else {
				%>
				    <option value="<%= sexoSeleccionado %>" selected><%= sexoSeleccionado %></option>
				    <option value="">Seleccione</option>
				<%
				        if (sexoSeleccionado.equals("Masculino")) {
				%>
				    <option value="Femenino">Femenino</option>
				<%
				        } else if (sexoSeleccionado.equals("Femenino")) {
				%>
				    <option value="Masculino">Masculino</option>
				<%
				        }
				    }
				%>
				</select>
            </div>

            <div>
              <label for="ddlNacionalidad">Nacionalidad</label>
              <select id="ddlNacionalidad" required name="ddlNacionalidad" >
              <option value="">Seleccione</option>
  				<%
				    List<Pais> listaNacionalidades = (List<Pais>) request.getAttribute("listaPaises");
            		Pais nacionalidadSeleccionada = new Pais();
            		  // si hay una nacionalidad seleccionada la guardo sino queda en null
                  	if (request.getAttribute("nacionalidadSeleccionada") != null) {
                  		nacionalidadSeleccionada = (Pais) request.getAttribute("nacionalidadSeleccionada");
                  	}
				    if (listaNacionalidades != null) {
				        for (Pais p : listaNacionalidades) {
				        	boolean selected = false;
				        	// si hay una nacionalidad previamente seleccionada... busco que pais es 
				        	if(nacionalidadSeleccionada.getNacionalidad() != null && nacionalidadSeleccionada.getNacionalidad() != ""){
				        		// pregunto si coincide con el pais de la vuelta actual, y lo notifico 
				        		if(nacionalidadSeleccionada.getNacionalidad().equals(p.getNacionalidad())){
				        			selected = true;
				        		}
				        	}		
				        	// si coinciden la pongo como selected
				  %>
				      <option <%=selected? "selected" : "" %> value="<%= p.getNacionalidad() %>"><%= p.getNacionalidad() %></option>
				  <%
				        }
				    }
				  %>
              </select>
            </div>
            <div>
              <label for="ddlPais">País</label>
              <select id="ddlPais" required  name="ddlPais" onchange="document.getElementById('form-agregar-cliente').submit();">
              <option value="">Seleccione</option>
              <%
              	List<Pais> listaPaises = (List<Pais>) request.getAttribute("listaPaises");
            	String paisSeleccionado = "";
            	if (request.getAttribute("paisSeleccionado") != null) {
            	    paisSeleccionado = request.getAttribute("paisSeleccionado").toString();
            	}  
				    if (listaPaises != null) {
				        for (Pais p : listaPaises) {
				        	boolean selected = false;
				        	// si hay un pais previamente seleccionado
				        	if(paisSeleccionado != null && paisSeleccionado != ""){
				        		if(paisSeleccionado.equals(p.getNombre())){
				        			selected = true;
				        		}
				        	}
				  %>
				      <option <%=selected? "selected" : "" %> value="<%= p.getNombre() %>"><%= p.getNombre() %></option>
				  <%
				        }
				    }
				  %>
              </select>
            </div>
            <div>
              <label for="ddlProvincia">Provincia</label>
              <select id="ddlProvincia" required name="ddlProvincia" onchange="document.getElementById('form-agregar-cliente').submit();">
              <option value="">Seleccione</option>
              <%
              	List<Provincia> listaProvincias = (List<Provincia>) request.getAttribute("listaProvinciasAlta");
            	String provinciaSeleccionada = "";
            	if (request.getAttribute("provSeleccionada") != null) {
            	    provinciaSeleccionada = request.getAttribute("provSeleccionada").toString();
            	}
				    if (listaProvincias != null) {
				        for (Provincia p : listaProvincias) {
				        	boolean selected = false;
				        	if(provinciaSeleccionada != null && provinciaSeleccionada != ""){
				        		if(provinciaSeleccionada.equals(p.getNombre())){
				        			selected = true;
				        		}
				        	}
				  %>
				      	<option <%=selected? "selected" : "" %> value="<%= p.getNombre() %>"><%= p.getNombre() %></option>

				  <%
				        }
				    }
				  %>
              </select>
            </div>
          </div>

          <div class="form-lado">
            <div>
              <label for="ddlLocalidad">Localidad</label>
              <select id="ddlLocalidad" required  name="ddlLocalidad">
              <option value="">Seleccione</option>
              <%
              	List<Localidad> listaLocalidades = (List<Localidad>) request.getAttribute("listaLocalidadesAlta");
            	String localidadSeleccionada = "";
                if (request.getAttribute("localidadSeleccionada") != null) {
               	    localidadSeleccionada = request.getAttribute("localidadSeleccionada").toString();
               	}
				    if (listaLocalidades != null) {
				        for (Localidad l : listaLocalidades) {
				        	boolean selected = false;
				        	if(localidadSeleccionada != null && localidadSeleccionada != ""){
				        		if(localidadSeleccionada.equals(l.getNombre())){
				        			selected = true;
				        		}
				        	}
				  %>
				      <option <%=selected? "selected" : "" %> value="<%= l.getNombre() %>"><%= l.getNombre() %></option>
				  <%
				        }
				    }
				  %>
              </select>
            </div>
            
            <div>
              <label for="txtDomicilio">Domicilio</label>
              <%
          	  String domicilioIngresado = "";
              if (request.getAttribute("domicilioIngresado") != null) {
            	  domicilioIngresado = request.getAttribute("domicilioIngresado").toString();
				}
             if(domicilioIngresado == null || domicilioIngresado == ""){
                    %>
			<input type="text" title ="Solo Letras. Máximo 50 caractéres" pattern="^[A-Za-z\s\d]{1,50}$" id="txtDomicilio" name="txtDomicilio" placeholder="Ingresar domicilio" required /><% }else{%>
			<input type="text" title ="Solo Letras. Máximo 50 caractéres" pattern="^[A-Za-z\s\d]{1,50}$" id="txtDomicilio" name="txtDomicilio" value="<%= domicilioIngresado %> " required /><%} %>
            </div>
            <div>
              <label for="txtFechaDeNacimiento">Fecha de nacimiento</label>
              <%
              Date fechaIngresada = null;
              if (request.getAttribute("fechaIngresada") != null) {
            	    fechaIngresada = (Date) request.getAttribute("fechaIngresada");
				}
             if(fechaIngresada == null){
                    %>
				<input type="date" id="txtFechaDeNacimiento" name="txtFechaDeNacimiento" required/><% }else{%>
				<input type="date" id="txtFechaDeNacimiento" name="txtFechaDeNacimiento" value="<%= fechaIngresada %>" required/><%} %>
            </div>

            <div>
              <label for="txtCorreo">Correo</label>
              <%
          	  String correoIngresado = "";
              if (request.getAttribute("correoIngresado") != null) {
            	  correoIngresado = request.getAttribute("correoIngresado").toString();
				}
             if(correoIngresado == null || correoIngresado == ""){
                    %>
			<input type="email" title ="Máximo 30 caractéres" type="email" id="txtCorreo" name="txtCorreo" placeholder="Ingresar correo" required /><% }else{%>
			<input type="email" title ="Máximo 30 caractéres" type="email" id="txtCorreo" name="txtCorreo" value="<%= correoIngresado%>" required /><%} %>
            </div>

            <div>
              <label for="txtTelefono">Teléfonos</label>
              <%
          	  String telefonoIngresado = "";
              if (request.getAttribute("telefonoIngresado") != null) {
            	  telefonoIngresado = request.getAttribute("telefonoIngresado").toString();
				}
             if(telefonoIngresado == null || telefonoIngresado == ""){
                    %>
			<input type="text" title="Solo números. Máximo 15 caractéres" id="txtTelefono" pattern="^\d{1,15}$" title="Solo números. Máximo 15 caractéres" name="txtTelefono" placeholder="Ingresar teléfono" required/><% }else{%>
			<input type="text" title="Solo números. Máximo 15 caractéres" id="txtTelefono" pattern="^\d{1,15}$" title="Solo números. Máximo 15 caractéres" name="txtTelefono" value="<%= telefonoIngresado%>" required/><%} %>
            </div>

            <div>
              <label for="txtUsuario">Usuario</label>
              <%
          	  String usuarioIngresado = "";
              if (request.getAttribute("usuarioIngresado") != null) {
            	  usuarioIngresado = request.getAttribute("usuarioIngresado").toString();
				}
             if(usuarioIngresado == null || usuarioIngresado == ""){
                    %>
			<input type="text" id="txtUsuario" pattern="^[^\s]{1,20}$" title="Máximo 20 caractéres. Sin espacios en blanco" name="txtUsuario" placeholder="Ingresar usuario" required/><% }else{%>
			<input type="text" id="txtUsuario" pattern="^[^\s]{1,20}$" title="Máximo 20 caractéres. Sin espacios en blanco" name="txtUsuario" value="<%= usuarioIngresado%>" required/><%} %>  
            </div>

            <div>
              <label for="txtContrasenia">Contraseña</label>
			  <input type="password" id="txtContrasenia" pattern="^[^\s]{1,16}$" title="Máximo 16 caractéres. Sin espacios en blanco" name="txtContrasenia" placeholder="Ingresar contraseña" required/>  
            </div>

            <div>
              <label for="txtContrasenia2">Confirmar Contraseña</label>
			  <input type="password" id="txtContrasenia2" pattern="^[^\s]{1,16}$" title="Máximo 16 caractéres. Sin espacios en blanco" name="txtContrasenia2" placeholder="Repetir contraseña" required/>
            </div>
          </div>

          <div class="form-submit">
            <button type="submit" name="btnAgregarCliente">Crear</button>
          </div>
        </form>

		<!-- CONDICIONAL MENSAJE DE ERROR -->
		<% if (request.getAttribute("mensajeError") != null) { %>
		    <div id="div-agregar-error">
		        <%= request.getAttribute("mensajeError") %>
		   </div>
		<% } %>
		<!-- CONDICIONAL INSERTAR EN LA DB -->
		<%
		int filas = 0; 
		if (request.getAttribute("cantFilas") != null) {
			filas = Integer.parseInt(request.getAttribute("cantFilas").toString()); 
		
		%>
		
		<%
			if (filas == 1) {
		%>
		    <div style = "color:green;" id="div-agregado-exito">
		         Cliente agregado con éxito.
		    </div>
		<%
			} else {
		%>
		    <div style = "color:red;" id="div-error-agregado">
		         Hubo un ERROR al agregar el cliente.
		    </div>
		<%
			}
		}
		%>
      </div>

		<!-- FILTRAR CLIENTES -->
		<h2>FILTRAR CLIENTES</h2>
		<div id="div-filtrar">
		  <form method="post" action="abmlClientesServlet">
		    <div class="form-filter">
		      <!-- DNI -->
		      <div>
		        <label for="txtDni">DNI</label>
		        <input type="text" id="txtDniFiltro" name="txtDniFiltro" placeholder="Ingresar DNI a buscar" 
		          value="<%= request.getParameter("txtDniFiltro") != null ? request.getParameter("txtDniFiltro") : "" %>" />
		      </div>
		
		      <!-- CUIL -->
		      <div>
		        <label for="txtCuil">CUIL</label>
		        <input type="text" id="txtCuilFiltro" name="txtCuilFiltro" placeholder="Ingresar CUIL a buscar" 
		          value="<%= request.getParameter("txtCuilFiltro") != null ? request.getParameter("txtCuilFiltro") : "" %>" />
		      </div>
		
		      <!-- Nombre -->
		      <div>
		        <label for="txtNombre">Nombre</label>
		        <input type="text" id="txtNombreFiltro" name="txtNombreFiltro" placeholder="Ingresar nombre a buscar" 
		          value="<%= request.getParameter("txtNombreFiltro") != null ? request.getParameter("txtNombreFiltro") : "" %>" />
		      </div>
		
		      <!-- Apellido -->
		      <div>
		        <label for="txtApellido">Apellido</label>
		        <input type="text" id="txtApellidoFiltro" name="txtApellidoFiltro" placeholder="Ingresar apellido a buscar" 
		          value="<%= request.getParameter("txtApellidoFiltro") != null ? request.getParameter("txtApellidoFiltro") : "" %>" />
		      </div>
		
		      <!-- Sexo -->
		      <div>
		        <label for="ddlSexo">Sexo</label>
		        <select id="ddlSexoFiltro" name="ddlSexoFiltro">
		          <option value="" <%= (request.getParameter("ddlSexoFiltro")==null || request.getParameter("ddlSexoFiltro").isEmpty()) ? "selected" : "" %>>Todos</option>
		          <option value="Masculino" <%= "Masculino".equals(request.getParameter("ddlSexoFiltro")) ? "selected" : "" %>>Masculino</option>
		          <option value="Femenino" <%= "Femenino".equals(request.getParameter("ddlSexoFiltro")) ? "selected" : "" %>>Femenino</option>
		        </select>
		      </div>

		      <!-- Fecha de nacimiento -->
		      <div>
		        <label for="txtFechaDeNacimiento">Fecha de nacimiento</label>
		        <input type="date" id="txtFechaDeNacimientoFiltro" name="txtFechaDeNacimientoFiltro"
		          value="<%= request.getParameter("txtFechaDeNacimientoFiltro") != null ? request.getParameter("txtFechaDeNacimientoFiltro") : "" %>" />
		      </div>
		    </div>
		
		    <div class="form-submit">
		      <button type="submit" name="btnFiltrar">Filtrar</button>
		    </div>
		  </form>
		</div>
      
      <!-- LISTADO DE CLIENTES  -->
      
       <h2>LISTADO, MODIFICACION Y ELIMINACIÓN</h2>
      <div class="contenedor-tabla">
        <table class="tabla-clientes">
          <thead>
            <tr>
              <th>Dni</th>
              <th>CUIL</th>
              <th>Nombre</th>
              <th>Apellido</th>
              <th>Sexo</th>
              <th>Fecha de Nacimiento</th>
              <th>Nacionalidad</th>
              <th>País</th>
              <th>Provincia</th>
              <th>Localidad</th>
              <th>Direccion</th>
              <th>Correo</th>
              <th>Telefonos</th>
              <th>Usuario</th>
              <th>Contraseña</th>
              <th>Modificar</th>
              <th>Eliminar</th>
            </tr>
          </thead>
<tbody>
<%
    List<Cliente> listaClientes = (List<Cliente>) request.getAttribute("listaClientes");
    if (listaClientes != null && !listaClientes.isEmpty()) {
        for (Cliente c : listaClientes) {
        	if(c.getBaja()!=1)
        	{
%>
  <tr>
<form method="post" class="form-confirm" action="abmlClientesServlet">
    <td><input type="text" value="<%= c.getDNI() %>" name="listDNI" readonly /></td>
    <td><input type="text" value="<%= c.getCUIL() %>" name="listCUIL" readonly /></td>
    <td><input type="text" value="<%= c.getNombre() %>" name="listNombre" /></td>
    <td><input type="text" value="<%= c.getApellido() %>" name="listApellido"/></td>
    
    <td><select name="listSexo" id="listSexo">
    <option value="<%= c.getSexo() %>" selected><%= c.getSexo() %></option>
    <%if(c.getSexo().equals("Femenino")){ %>
    <option value="Masculino">Masculino</option><%}else{ %>
    <option value="Femenino">Femenino</option><%} %>
    </select></td>
    
    <td><input type="date" value="<%= c.getFechaNacimiento() %>" name="listFecha"/></td>
    
    <td><input type="text" value="<%= c.getNacionalidad().getNacionalidad() %>"name="listNacionalidad" readonly/></td>
    
    <td><input type="text" value="<%= c.getPais().getNombre() %>" name="listPais" readonly/></td>
    
    <td><input type="text" value="<%= c.getProvincia().getNombre() %>" name="listProvincia" readonly/></td>
    
    <td><input type="text" value="<%= c.getLocalidad().getNombre() %>" name="listLocalidad" readonly/></td>
    
    <td><input type="text" value="<%= c.getDomicilio() %>" name="listDireccion" /></td>
    <td><input type="text" value="<%= c.getEmail() %>" name="listEmail" /></td>
    <td><input type="text" value="<%= c.getTelefono() %>" name="listTelefono"/></td>
    <td><input type="text" value="<%= c.getNick() %>" readonly name="listNick"/></td>
    <td><input type="text" value="<%= c.getPassword() %>" name="listPassword" /></td>
    <td><input type="submit" class="btn btn-warning" value="Modificar" name="btnModificar" /></td>
    <td><input type="submit" class="btn btn-danger" value="Eliminar" name="btnEliminarCliente" /></td>
</form>
  </tr>
<%
        	}
        }
    } else {
%>
<tr>
  <td style="text-align:center;">No hay clientes para mostrar.</td>
  <% for (int i = 1; i < 17; i++) { %>
    <td></td>
  <% } %>
</tr>
<% } %>
<!-- CONDICIONAL ELIMINAR EN LA DB -->
		<%
		int filasE = 0; 
		if (request.getAttribute("cantFilasE") != null) {
			filasE = Integer.parseInt(request.getAttribute("cantFilasE").toString()); 
		
		%>
		
		<%
			if (filasE == 1) {
		%>
		    <div style = "color:green;" id="div-agregado-exito">
		         Cliente eliminado con éxito.
		    </div>
		<%
			} else {
		%>
		    <div style = "color:red;" id="div-error-agregado">
		         Hubo un ERROR al eliminar el cliente.
		    </div>
		<%
			}
		}
		%>
<!-- CONDICIONAL MODIFICAR EN LA DB -->		
<%
		 boolean modificado;
		if (request.getAttribute("resultadoModificar") != null) {
			modificado = (boolean)request.getAttribute("resultadoModificar"); 
		
		%>
		
		<%
			if (modificado) {
		%>
		    <div style = "color:green;" id="div-agregado-exito">
		         Cliente modificado con éxito.
		    </div>
		<%
			} else {
		%>
		    <div style = "color:red;" id="div-error-agregado">
		         Hubo un ERROR al modificar el cliente.
		    </div>
		<%
			}
		}
		%>
</tbody>
        </table>
      </div>
    <script src="./ConfirmacionForm.js"></script>
	<script>
  $(document).ready(function() {
    $('.tabla-clientes').DataTable({
      "pageLength": 8,
      "lengthChange": false,
      "searching": false, // Oculta el campo de búsqueda
      "language": {
        "url": "//cdn.datatables.net/plug-ins/1.13.4/i18n/es-ES.json"
      }
    });
  });
</script>
    </main>
  </body>
</html>