<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ page import="entidades.Usuario" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="imgs/logo_Honse-nombre.png" type="image/png">
    <title>Préstamo - Banco Honse</title>
</head>
<style>
    body{
        margin: 0;
        background: #2C90AA;
        background: linear-gradient(0deg,rgba(44, 144, 170, 1) 0%, rgba(255, 252, 253, 1) 90%);
        background-repeat: no-repeat;
        background-size: cover;
        min-height: 100vh;
        font-family: system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
    }

    h1{
        text-align: center;
    }
    a{
        text-decoration: none;
    }
    .inicio{
        display: flex;
    }
    .inicio p{
        margin: auto;
    }
	#sectionPrestamos{
	width: 80%;
	margin: 2em auto;
	}
    .prestamos{
        background-color: rgba(255, 252, 253, 1);
        width: 100%;
        margin: 0 auto 3% auto;
        border-radius: 10px;
    }
    .prestamos h1{
        padding: 2% 0 1% 2%;
        text-align: left;
    }
    .pedir{    
        width: fit-content;
        padding:1em;
        margin: 0 auto;
        text-align:center;
    }
    .formulario {
        display: flex;
        flex-direction: column;
        gap: 3em;
        text-align:left;
    }
    .formulario .agrupar {
        display: flex;
        align-items: center;
        gap: 1em;
    }

    .formulario label {
        width: 10em;
        font-weight: bold;
    }
    #cuotas{
    	 width: 30em;
        height: 3em;
        min-width: 20em;
    	padding: 0.4em;
    	border-radius: 0.5em;
    	outline: none;
    	border: 1px solid darkgray;
    }

    .formulario input{
        width: 30em;
        height: 3em;
        min-width: 20em;
    	padding: 0.4em;
    	border-radius: 0.5em;
    	outline: none;
    	border: 1px solid darkgray;
    }
    #btnTransferir {
	    width: 60%;
	    margin: 0 auto;
	    padding: 1em;
	    border-radius: 0.5em;
	    cursor: pointer;
	    outline: none;
	    background-color: rgba(77, 180, 187, 0.637);
	    border: none;
	    transition: all 0.2s ease;
    }
    
    #btnTransferir:hover {
    	background-color: rgba(38, 117, 122, 0.637);	
    }
        #volver {
        margin-left: 10%;
        font-size: larger;
        color: white;
        background-color: #2C90AA;
        padding: 8px 15px;
        border-radius: 5px;
        display: inline-block;
        margin-top: 10px;
    }
	#infoCuenta{
    	width: 50%;
    	background-color:rgba(255, 252, 253, 1);
    	padding:1em;
    	border-radius:10px;
    	color:black;
    }
    #infoCuenta h3{
    font-weight: 400;
    }
    #infoCuenta span{
    	font-weight: 600;
    	color:blue;
    	font-size:1em;
    }
</style>
<body>
    <%@include file="./HeaderCliente.jsp" %>
       
     <%
     	String cbuSeleccionado = (String)request.getAttribute("cbuSeleccionado");
		String saldoCuentaSeleccionada = String.valueOf(request.getAttribute("saldoCuentaSeleccionada"));
		String numeroCuenta = String.valueOf(request.getAttribute("numeroCuenta"));
     %>
     
    <a href="ServletClientes?cbuSeleccionado=<%= cbuSeleccionado %>" id="volver">🡠 Volver</a>
    <main>
    		
    		<section id="sectionPrestamos">
                <div id="infoCuenta">
	    			<h3>Cuenta: <span><%= numeroCuenta%></span></h3>
	    			<h3>CBU: <span><%=cbuSeleccionado %></span> </h3>
	    			<h3>Saldo actual: <span>$<%=saldoCuentaSeleccionada %></span></h3>
	    		</div>
    			
	            <div class="prestamos">
	                <h1>Préstamo</h1>
	                <div class="pedir">
	                    <form action="ServletPrestamos" method="post" class="formulario form-confirm">
	                        <%
		                        Float montoPedido = (Float) request.getAttribute("montoPedido");
		                        Integer cuotas = (Integer) request.getAttribute("cuotas");
		                        Float montoTotal = (Float) request.getAttribute("montoTotal");
		                        Float valorCuota = (Float) request.getAttribute("valorCuota");

	                        %>
	                        <div class="agrupar">
	                            <label for="montoPedido">*Hasta $75.000.000*</label>
	                            <input type="text" onblur="this.form.submit()" value="<%= (montoPedido != null) ? montoPedido : "" %>" required pattern="^\d{1,8}(\.\d{1,2})?$" title="Solo números con hasta 2 decimales. Usar el punto (.) como separador decimal" id="montoPedido" name="montoPedido" placeholder="Monto a pedir">
	                        </div>
	                        <div class="agrupar">
	                        <label for="cuotas">Cuotas:</label>
	                            <select name="cuotas" id="cuotas" required onchange="this.form.submit()">
								  <% 
								   int[] opciones = {1, 2, 3, 6, 9, 12, 18, 24, 36, 48};
					               for (int o : opciones) { %>
					                <option value="<%=o%>" <%= (cuotas != null && cuotas == o) ? "selected" : "" %>>
					                    <%=o%> cuotas
					                </option>
					              <% } %>							
					            </select></div>
	                        <div class="agrupar">
	                            <label for="montoCuotas">Monto por cuota</label>
	                            <input type="text" value="<%= (valorCuota != null) ? valorCuota : "" %>" id="montoCuotas" name="montoCuotas"  readOnly>
	                        </div>
	                        <div class="agrupar">
	                            <label for="totalDevolver">Total a pagar</label>
	                            <input type="text" value="<%= (montoTotal != null) ? montoTotal: "" %>" id="totalDevolver" name="totalDevolver"  readOnly>
	                        </div>
	                        <input type="submit" value="Pedir préstamo" name="btnPedir" id="btnTransferir">
	                    
	                    	<!-- INPUTS OCULTOS PARA MANDAR TODO AL SERVLET -->
	                   		<input type="hidden" name="saldoCuentaSeleccionada" value="<%= saldoCuentaSeleccionada %>">
							<input type="hidden" name="numeroCuenta" value="<%= numeroCuenta %>">
							<input type="hidden" name="cbuSeleccionado" value="<%= cbuSeleccionado %>">
	                    </form> 
	                    <% if (request.getAttribute("ErrorMessage") != null) { %>
    						<p style="color: red;"><%= request.getAttribute("ErrorMessage") %></p>
						<% } %>
						
						<% if (request.getAttribute("PrestamoRealizado") != null) { %>
    						<p style="color: green;"><%= request.getAttribute("PrestamoRealizado") %></p>
						<% } %>
	                </div>
	            </div>
    		</section>
    </main>
    
    <script src="./ConfirmacionForm.js"></script>
</body>
</html>