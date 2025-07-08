<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ page import="entidades.ClienteReporte" %>
    <%@ page import="entidades.PrestamosReporte" %>
    <%@ page import="entidades.CuotasReporte" %>
    <%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Reporte - Banco Honse</title>
    <style>
    	body {
      margin: 0;
      margin-bottom:3em;
      background: linear-gradient(
        0deg,
        rgba(44, 144, 170, 1) 0%,
        rgba(113, 190, 196, 0.616) 70%
      );
      font-family: system-ui, -apple-system, BlinkMacSystemFont, "Segoe UI",
        Roboto, Oxygen, Ubuntu, Cantarell, "Open Sans", "Helvetica Neue",
        sans-serif;
    }

        .reporte {
            width: 80%;
            margin: 0 auto;
            text-align: left;
        }

        
        #reporteCuotas{
        	background-color: white;
        	box-shadow: 15px 20px 10px rgba(2, 2, 2, 0.103);
        	padding: 1em;
        }

        #reporte1-form {
            background-color: white;
            display: flex;
            flex-direction: row;
            gap: 2em;
            padding: 1em;
            justify-content: space-between;
            box-shadow: 15px 20px 10px rgba(2, 2, 2, 0.103);
        }

        #reporte1-form input {
            
            padding: 0.4em;
            border-radius: 0.5em;
            outline: none;
            border: 1px solid darkgray;
        }
		
		#reporte1-form input[type=submit] {
        padding: .6em;
        border-radius: .5em;
        cursor: pointer;
        outline: none;
        background-color: rgba(77, 180, 187, 0.637);
        border: none;
        transition: all .2s ease;
        margin: 0 auto;
    	}
    	
	   #reporte1-form input[type=submit]:hover{
	        background-color: rgba(38, 117, 122, 0.637);
	    }
		
        #reporte2-table {
            background-color: white;
            width: 50%;
            text-align: center;
            box-shadow: 15px 20px 10px rgba(2, 2, 2, 0.103);
            border-collapse:collapse;
        }
        #reporte2-table thead{
        	background-color: #3a3a3a;
        	color:white;
        }
        
        #reporte3-table {
            background-color: white;
            width: 100%;
            text-align: center;
            box-shadow: 15px 20px 10px rgba(2, 2, 2, 0.103);
        }
		
		#reporteCuotas{
			width:50%;
		}
		
	#form-cuotas input[type="text"] {
	    min-width: 20em;
	    padding: 0.4em;
	    border-radius: 0.5em;
	    outline: none;
	    border: 1px solid darkgray;
	  }
	  #form-cuotas > input[type="submit"] {
	    margin: 0 auto;
	    padding: .5em;
	    border-radius: 0.5em;
	    cursor: pointer;
	    outline: none;
	    background-color: rgba(77, 180, 187, 0.637);
	    border: none;
	    transition: all 0.2s ease;
	    border:1px solid white;
	  }
	    #form-cuotas input[type="submit"]:hover {
    background-color: rgba(38, 117, 122, 0.637);
  }
  
  #tabla-cuotas thead {
    background-color: #343a40;
    color: white;
    position: sticky;
    top: 0;
    z-index: 1;
  }

  #tabla-cuotas th,
  #tabla-cuotas td {
    border: 1px solid #ddd;
    padding: 8px;
    min-width: 120px;
  }
    #listado-cuotas {
    background-color: white;
    width: auto%;
    margin: 0 auto;
    padding: 0em;
    box-shadow: 15px 20px 10px rgba(2, 2, 2, 0.103);
    overflow-x:scroll;
  }
	  
    </style>
    <script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
	<script src="https://cdn.datatables.net/1.13.4/js/jquery.dataTables.min.js"></script>
	<link rel="stylesheet" href="https://cdn.datatables.net/1.13.4/css/jquery.dataTables.min.css">
</head>
<body>
    <%@ include file="./HeaderAdmin.jsp" %> 
    <main>
        <div class="reporte">
            <h3>Transferencias totales entre fechas</h3>
            <form id="reporte1-form" method="post" action="ReportesServlet">
                <div>
                    <label>Desde</label>
                    <input type="date" name="dateDesde"
               			value="<%= (request.getAttribute("fechaDesde") != null) 
                        ? request.getAttribute("fechaDesde") 
                        : "2024-06-01" %>">
                </div>
                <div>
                    <label>Hasta</label>
                    <input type="date" name="dateHasta"
               			value="<%= (request.getAttribute("fechaHasta") != null) 
                        ? request.getAttribute("fechaHasta") 
                        : "2026-06-01" %>">
                </div>
                <div>
                	<input type="submit" value="Filtrar" name="btnFiltrarFechas"/>
                </div>
                <div>
                    <label>Cantidad Total de transferencias</label>
                    <input type="text" disabled value="<%= request.getAttribute("cantidadTransferencias") %>">
                </div>
            </form>
         </div>
         
         <div class="reporte">
		    <%
		        ClienteReporte clienteTransfer = (ClienteReporte) request.getAttribute("clienteTransfer");
		    %>
		
		    <h3>Cliente con más transferencias</h3>
		    <table id="reporte2-table" >
		        <thead>
		            <tr>
		                <th>Id Cliente</th>
		                <th>Cantidad de Transferencias</th>
		            </tr>
		        </thead>
		        <tbody>
		            <tr>
		                <td><%= clienteTransfer != null ? clienteTransfer.getId() : "N/A" %></td>
		                <td><%= clienteTransfer != null ? clienteTransfer.getCantidad() : "0" %></td>
		            </tr>
		        </tbody>
		    </table>
		</div>
        <br>
        <div class="reporte">
		    <%
		        ClienteReporte clientePrestamos = (ClienteReporte) request.getAttribute("clientePrestamos");
		    %>
		
		    <h3>Cliente que solicitó mas préstamos</h3>
		    <table id="reporte2-table" >
		        <thead>
		            <tr>
		                <th>Id Cliente</th>
		                <th>Cantidad de Préstamos</th>
		            </tr>
		        </thead>
		        <tbody>
		            <tr>
		                <td><%= clienteTransfer != null ? clientePrestamos.getId() : "N/A" %></td>
		                <td><%= clienteTransfer != null ? clientePrestamos.getCantidad() : "0" %></td>
		            </tr>
		        </tbody>
		    </table>
		</div>
       	<br>
        <div class="reporte">
        <h3>Estadísticas de los Préstamos</h3>
        <%
        	PrestamosReporte prestamosReporte = new PrestamosReporte();
        
        	prestamosReporte = (PrestamosReporte)request.getAttribute("prestamosReporte");

        	int porcentajeAprobado = prestamosReporte.getAprobado();
        	int porcentajeRechazado = prestamosReporte.getRechazado();
        	int porcentajePendiente = prestamosReporte.getPendiente();
        %>
        	<div id="reporteCuotas">
        	<h4>ACEPTADOS: % <%= porcentajeAprobado %></h4>
        	<h4>RECHAZADOS: % <%= porcentajeRechazado %></h4>
        	<h4>PENDIENTES: % <%= porcentajePendiente %></h4>
        	</div>
        </div>
         <div class="reporte">
         	<h3>Información de Cuotas</h3>
         	<div>
         	<form method="post" action="ReportesServlet" id="form-cuotas">
         		<input type="text" value="<%= request.getAttribute("dni")!=null ?request.getAttribute("dni"):"" %>" placeholder="DNI Cliente" name="DNI" required pattern="^\d{1,8}$" title="Solo números. Máximo 8 caractéres">
         		<input type="submit" value="Buscar Cliente" name="buscarCliente">
         	</form>
         	</div>
         	<br></br>
         	<div id="listado-cuotas">
         		<table id="tabla-cuotas">
         			<thead>
				        <tr>
				            <th>ID Préstamo</th>
				            <th>Cuenta</th>
				            <th>Fecha</th>
				            <th>Importe Pedido</th>
				            <th>Importe a Pagar</th>
				            <th>Total de Cuotas</th>
				            <th>Cuotas Pagadas</th>
				            <th>Monto Mensual</th>
				        </tr>
				    </thead>
				    <tbody>
				    <%
				    	ArrayList<CuotasReporte> lista = (ArrayList<CuotasReporte>) request.getAttribute("listaCuotas");
				    	if(lista != null){
				    		for(CuotasReporte cuota : lista){
				    			%>
				    			<tr>
				    			<td><%= cuota.getIdPrestamo() %></td>
				                <td><%= cuota.getCuenta() %></td>
				                <td><%= cuota.getFecha() %></td>
				                <td>$<%= cuota.getImportePedido() %></td>
				                <td>$<%= cuota.getImportePagar() %></td>
				                <td><%= cuota.getCuotas() %></td>
				                <td><%= cuota.getCuotasPagas() %></td>
				                <td>$<%= cuota.getMontoMensual() %></td>
				    			</tr>
				                <%
				    		}
				    	}
				    	else{
				    		%>
				    		<tr>
				    			<%
				    			for(int i = 0; i<8;i++){
				    				%>
				    				<td>-</td>
				    				<%
				    			}
				    			%>
				    		</tr>
				    		<% 
				    	}
				    %>
				    </tbody>
         		</table>
         	</div>
         </div>
    </main>
<script>
  $(document).ready(function() {
    $('#tabla-cuotas').DataTable({
      "pageLength": 8,
      "lengthChange": false,
      "searching": false,
      "language": {
        "url": "//cdn.datatables.net/plug-ins/1.13.4/i18n/es-ES.json"
      }
    });
  });
</script>
</body>
</html>