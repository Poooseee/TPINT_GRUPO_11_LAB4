<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="entidades.Usuario" %>
<%@ page import="entidades.Movimiento" %>
<%@ page import="entidades.Cuenta" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>



<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="imgs/logo_Honse-nombre.png" type="image/png">
    <link rel="stylesheet" type="text/css"
  	href="https://cdn.datatables.net/1.13.4/css/jquery.dataTables.min.css">
	<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
	<script type="text/javascript" charset="utf8"
  src="https://cdn.datatables.net/1.13.4/js/jquery.dataTables.min.js"></script>
    <title>Movimientos - Banco Honse</title>
    
    <style>

    body{
        margin: 0;
        background: #2C90AA;
        background: linear-gradient(0deg,rgba(44, 144, 170, 1) 0%, rgba(255, 252, 253, 1) 90%);
        background-repeat: no-repeat;
        background-size: cover;
        min-height: 100dvh;
        font-family: system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
    }
    main{
    width:90%;
    margin:0 auto;
    }
    a{
        text-decoration: none;
    }
    h1{
        text-align: center;
    }
    button{
        all: unset;
        cursor: pointer;
    }

        
        #informacion {
            display: flex;
            flex-direction: row;
            align-items: center;
            width: 30%;
            padding: 0 1em;
        }
        
        #logo img {
            height: 5em;
            width: auto;
            margin-right: 1em;
        }
        
        #admin-interaccion {
            display: flex;
            flex-direction: column;
            justify-content: center;
            gap: 0.3em;
        }
        
        #nombre-admin {
            display: flex;
            align-items: center;
            gap: 0.3em;
        }
        
        #nombre-admin a, #nombre-admin p {
            margin: 0;
            font-size: 1em;
            color: #333;
            text-decoration: none;
        }
        
        #nombre-admin a:hover, #cerrarSesion:hover {
            text-decoration: underline;
        }
        
	    #imgPerfil{
	        width: 75px;
	        border-radius: 100%;
	    }
        
        #admin-interaccion a {
            text-decoration: none;
            color: black;
        }
        
        .contenedor-tabla {
            width: 100%;
            margin: 2em auto;
            overflow-x: auto;
            padding: 1em;
            background: #fff;
            margin-bottom: 5em;
            box-shadow: 15px 20px 10px rgba(2, 2, 2, 0.103);
            border-radius: 10px;
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
        
        th, td {
            border: 1px solid #ddd;
            padding: 1em;
            margin: 0 auto;
            min-width: 120px;
        }
        
        tbody tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        
        tbody tr:hover {
            background-color: #e9ecef;
        }
        
        .no-movimientos {
            text-align: center;
            padding: 2em;
            color: #666;
            font-style: italic;
        }
        
        .filtrado {
            display: flex;
            margin: 1em auto;
            width: 100%;
            justify-content: space-around;
            background: white;
            padding: 1em;
            border-radius: 8px;
            box-shadow: 0 4px 6px rgba(0,0,0,0.1);
        }
        .filtradoCuenta{
        	width:100%;
        	margin:0 auto;
        	display:flex;
        	justify-content: flex-start;
        }
        .cuentafiltrado{
            width: min-content;
			padding:1em;
            background-color: white;
            border-radius: 8px;
            box-shadow: 0 4px 6px rgba(0,0,0,0.1);
        }
        .cuentafiltrado form{
            display:flex;
            flex-direction:row;
            gap:1em;
        }
        .cuentafiltrado select {
       		 padding: 0.5em;
            border-radius: 4px;
            border: 1px solid #ddd;
        }
        .filtrado form {
            display: flex;
            justify-content: space-around;
            width: 100%;
            align-items: center;
            flex-wrap: wrap;
            gap: 1em;
        }
        
        .filtrado label {
            margin-right: 0.5em;
            font-weight: 500;
        }
        
        .filtrado select, .filtrado input {
            padding: 0.5em;
            border-radius: 4px;
            border: 1px solid #ddd;
        }
        
        #btnFiltrar {
            padding: 0.5em 1.5em;
            background-color: #2C90AA;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        
        #btnFiltrar:hover {
            background-color: #1a6a7e;
        }
        
        #volver {
	        margin-left: 1em;
	        font-size: larger;
	        color: white;
	        background-color: #2C90AA;
	        padding: 8px 15px;
	        border-radius: 5px;
	        display: inline-block;
	        margin-top: 10px;
    	}
        
        h1 {
	        padding: 1em 0 0 1em;
	        text-align: left;
	        color: #343a40;
        }
        
            footer {
	        width: 100%;
	        text-align: center;
	        padding: 1em 0;
	        color: white;
	        position: relative;
	        bottom: 0;
	        margin-top: 2em;
	    }
	    
	    footer h1 {
		    text-align: center;
		    margin: 0;
		    padding: 0.5em 0;
		    font-size: 1.5em;
		    width: 100%;
	    }
    </style>
</head>
<body>

    <%@ include file="./HeaderCliente.jsp" %>
    <%
		ArrayList <Cuenta> cuentas = new ArrayList<>(); 
		if(request.getAttribute("cuentasCliente") != null)
			 cuentas = (ArrayList<Cuenta>) request.getAttribute("cuentasCliente");
		
		String cbuSeleccionado = (String) request.getAttribute("cbuSeleccionado");

	    List<Object[]> movimientosConCuentas = (List<Object[]>) request.getAttribute("movimientosConCuentas");
		
	   	
	%>
    <a href="ServletClientes?cbuSeleccionado=<%= cbuSeleccionado %>" id="volver">🡠 Volver</a>
    
    <main>
        <h1>Movimientos</h1>
        	
        	<div class="filtradoCuenta">
        		<div class="cuentafiltrado">
	        	<form action="ServletMovimientos" method="post">
	        			<p>Cuenta</p>
	        		    <select id="selectCuentas" name="cbuSeleccionado" onchange="this.form.submit()">
					        <%
					           
					            if(cuentas != null && !cuentas.isEmpty()){
					                for(Cuenta cuenta : cuentas){
					                    String cbu = cuenta.getCbu();
					                    int numeroCuenta = cuenta.getNumero();
					                    boolean seleccionado = cbuSeleccionado != null && cbuSeleccionado.equals(cbu);
					        %>	
					        			
					                    <option value="<%=cbu%>" <%=seleccionado ? "selected" : "" %>>
					                        <%= numeroCuenta + " - " + cbu %>
					                    </option>
					        <%
					                }
					            } else {
					        %>
					                <option value="-">Aún tiene cuentas</option>
					        <%
					            }
					        %>
					    </select>
			     
	        	</form>
        		</div>
        	</div>
        <div class="filtrado">
            <form action="ServletMovimientos?cbuSeleccionado=<%=cbuSeleccionado %>" method="post">

                <label for="fecha">Fecha:</label>
                <input type="date" id="fecha" name="fecha">
                
                <label for="importe">Importe:</label>
                <select id="importe" name="importe">
                	<option value="">Todos los importes</option>
                    <option value="0a10k">Hasta $10.000</option>
                    <option value="10ka50k">De $10.000 hasta $50.000</option>
                    <option value="50ka100k">De $50.000 hasta $100.000</option>
                    <option value="+100k">Desde $100.000</option>
                </select>
                
                <label for="tipo">Tipo:</label>
				<select id="tipo" name="tipo" class="form-control">
				    <option value="">Todos los tipos</option>
				    <option value="1" ${param.tipo == '1' ? 'selected' : ''}>Alta de cuenta</option>
				    <option value="2" ${param.tipo == '2' ? 'selected' : ''}>Alta de un préstamo</option>
				    <option value="3" ${param.tipo == '3' ? 'selected' : ''}>Pago de préstamo</option>
				    <option value="4" ${param.tipo == '4' ? 'selected' : ''}>Transferencia</option>
				</select>
                
                <input type="submit" value="Filtrar" name="btnFiltrar" id="btnFiltrar">
            </form>
        </div>
        
        <div class="contenedor-tabla">
            <table class="tabla-clientes">
			    <thead>
			        <tr>
			            <th>Fecha</th>
			            <th>Nro de Cuenta</th>
			            <th>CBU</th>
			            <th>Detalle</th>
			            <th>Importe</th>
			            <th>Tipo de movimiento</th>
			        </tr>
			    </thead>
			    <tbody>
			        <% if(movimientosConCuentas != null && !movimientosConCuentas.isEmpty()) { 
			            for(Object[] fila : movimientosConCuentas) { 
			                Movimiento m = (Movimiento)fila[0];
			                Cuenta c = (Cuenta)fila[1];
			            %>
			            <tr>
			                <td><%= m.getFecha() %></td>
			                <td><%= m.getNumeroCuenta() %></td>
			                <td><%= c.getCbu() %></td>
			                <td><%= m.getDetalle() %></td>
			                <td>$<%= String.format("%,.2f", m.getImporte()) %></td>
			                <td><%= m.getTipo().getDescripcion() %></td>
			            </tr>
			        <%  } 
			           } %>
			    </tbody>
			</table>
        </div>
    </main>
    
    <footer>
    <h1>Banco Honse, siempre con vos.</h1>
	</footer>
    
    <!-- Script de DataTables -->
    <script>
	  $(document).ready(function() {
	    $('.tabla-clientes').DataTable({
	      "pageLength": 8,
	      "lengthChange": false,
	      "searching": false,
	      "language": {
	        "url": "//cdn.datatables.net/plug-ins/1.13.4/i18n/es-ES.json",
	        "emptyTable": "<div class='no-movimientos'>No se encontraron movimientos</div>"
	      }
	    });
	  });
	</script>
</body>
</html>