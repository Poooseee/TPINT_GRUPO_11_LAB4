<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ page import="entidades.Usuario" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="imgs/logo_Honse-nombre.png" type="image/png">
    <title>Transferencia - Banco Honse</title>
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
    #logoBanco{
        width: 75px;
    }
    #imgPerfil{
        width: 75px;
        border-radius: 100%;
    }
    #volver{
        margin-left: 1%;
        font-size: larger;
    }
    #sectionTransferencia{
    	width:80%;
    	margin:2em auto;
    }
    .transferencias{
        background-color: rgba(255, 252, 253, 1);
        width: 100%;
        margin: 0 auto 3% auto;
        border-radius: 10px;
        height: 70vh;
    }
    .transferencias h1{
        padding: 2% 0 1% 2%;
        text-align: left;
    }
    .transferir{    
        height: 80%;
        width: fit-content;
        margin: 8% auto;
    }
    .formulario {
        display: flex;
        flex-direction: column;
        gap: 3em;
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
    #btnTransferir {
	    width: 100%;
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
    		<section id="sectionTransferencia">
	    		<div id="infoCuenta">
	    			<h3>Cuenta: <span><%= numeroCuenta%></span></h3>
	    			<h3>CBU: <span><%=cbuSeleccionado %></span> </h3>
	    			<h3>Saldo actual: <span>$<%=saldoCuentaSeleccionada %></span></h3>
	    		</div>
	            <div class="transferencias">
	                <h1>Transferencia</h1>
	                <div class="transferir" style="text-align:center;">
	                    <form action="ServletTransferencias" method="post" class="formulario form-confirm">
	                        <input type="text" required pattern="^\d{1,20}$" title="Solo números. 20 caracteres máximo" id="nCta" name="cuentaDestino" placeholder="Número de cuenta">
	                        <input type="text" id="importe" required title="Solo números con hasta 2 decimales. Usar el punto (.) como separador decimal" pattern="^\d+(\.\d{1,2})?" name="importe" placeholder="Importe">
	                        <input type="submit" value="Transferir" name="btnTransferir" id="btnTransferir">
	                   		
	                   		<!-- INPUTS OCULTOS PARA MANDAR TODO AL SERVLET -->
	                   		<input type="hidden" name="saldoCuentaSeleccionada" value="<%= saldoCuentaSeleccionada %>">
							<input type="hidden" name="numeroCuenta" value="<%= numeroCuenta %>">
							<input type="hidden" name="cbuSeleccionado" value="<%= cbuSeleccionado %>">
	                    </form> 
	                    <% if (request.getAttribute("ErrorMessage") != null) { %>
    						<p style="color: red;"><%= request.getAttribute("ErrorMessage") %></p>
						<% } %>
						
						<% if (request.getAttribute("TransferenciaRealizada") != null) { %>
    						<p style="color: green;"><%= request.getAttribute("TransferenciaRealizada") %></p>
						<% } %>
	                </div>
	            </div>
    		</section>
    </main>
    <footer>
        <h1>Banco Honse, siempre con vos.</h1>
    </footer>
    <script src="./ConfirmacionForm.js"></script>
</body>
</html>