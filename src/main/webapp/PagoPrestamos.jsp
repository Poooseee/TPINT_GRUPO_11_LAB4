<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ page import="java.util.List" %>
    <%@ page import="entidades.Prestamo" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <title>Prestamos - Banco Honse</title>
 
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
    h1{
    text-align:center;
    }
     /* TABLA DE PRESTAMOS*/
    #tabla{
        background-color: white;
        width: 90%;
        margin: 0 auto;
        padding: 1em;
        box-shadow: 15px 20px 10px rgba(2, 2, 2, 0.103);
        overflow-x:scroll;
        margin-bottom: 3em;
      }
      #tabla table {
          border-collapse: collapse;
          text-align: center;
          width:100%;
        }

    #tabla thead {
      background-color: #343a40;
      color: white;
      position: sticky;
      top: 0;
      z-index: 1;
    }

    #tabla th,
    #tabla td {
      border: 1px solid #ddd;
      padding: 8px;
      min-width: 120px;
    }

    #tabla input[type="text"],
    #tabla input[type="date"],
    #tabla select {
      width: 100%;
      padding: 4px;
      border: 1px solid #ccc;
      border-radius: 4px;
    }

    #tabla input[type="submit"] {
      padding: 6px 10px;
      border: none;
      border-radius: .5em;
      cursor: pointer;
      color: white;
      background-color: orange;
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
        text-decoration:none;
    }
    </style>
    <script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
	<script src="https://cdn.datatables.net/1.13.4/js/jquery.dataTables.min.js"></script>
	<link rel="stylesheet" href="https://cdn.datatables.net/1.13.4/css/jquery.dataTables.min.css">
</head>
<body>
	 <%@ include file="./HeaderCliente.jsp" %>
	 <main>
	 <%
	 	String cbuSeleccionado = (String)request.getAttribute("cbuSeleccionado");
	 %>
	 <a href="ServletClientes?cbuSeleccionado=<%= cbuSeleccionado %>" id="volver">🡠 Volver</a>
	 <h1>Tus préstamos</h1>
	 
	             <div id="tabla">
                <table id="tabla-prestamos">
                    <thead>
                        <tr>
                            <th>ID Préstamo</th>
                            <th>Importe a pagar</th>
                            <th>Importe solicitado</th>
                            <th>Plazo en cuotas</th>
                            <th>Fecha</th>
                            <th>Detalle</th>
                        </tr>
                    </thead>
                    <tbody>
                    <%
                    List<Prestamo> listaPrestamos = (List<Prestamo>) request.getAttribute("listado");
                    if(listaPrestamos != null){
                    	for(Prestamo prestamo : listaPrestamos){
                    %>
                        <tr>
                            <td>
                                <input name="txtIdPrestamo" type="hidden" value="<%= prestamo.getIdPrestamo() %>">
                                <%= prestamo.getIdPrestamo() %>
                            </td>
                            
                             <td>
                                <input type="hidden" value="<%= prestamo.getImportePagar() %>" >
                            	<%= prestamo.getImportePagar() %>
                            </td>
                             <td>
                                <input type="hidden" value="<%= prestamo.getImportePedido() %>" >
                            	<%= prestamo.getImportePedido() %>
                            </td>

                             <td>
                                <input type="hidden" value="<%= prestamo.getPlazoPagos() %>" >
                            	<%= prestamo.getPlazoPagos() %>
                            </td>
                             <td>
                                <input type="hidden" value="<%= prestamo.getFecha() %>" >
                            	<%= prestamo.getFecha()%>
                            </td>

                            <td>
                            	<form action="PagoPrestamoServlet?cbuSeleccionado=<%= cbuSeleccionado %>" method="post">
                            		<input type="hidden" name="idPrestamo"  value=<%= prestamo.getIdPrestamo()%>>
                            		<input type="submit" name="btnDetalle" value="Ver detalle">
                            	</form>
                            </td>
                      
                        </tr>

                       <% } %>
                    <% }else{ %>
                          <tr>
  							<td>No hay prestamos para mostrar.</td>
  								<% for (int i = 1; i < 6; i++) { %>
   										 <td></td>
 								 <% } %>
						 </tr>
                    <% } %>
                    </tbody>
                </table>
            </div>
	 </main>
	 
<script src="./ConfirmacionForm.js"></script>
    <script>
  $(document).ready(function() {
    $('#tabla-prestamos').DataTable({
      "pageLength": 4,
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