<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.List" %>
    <%@ page import="entidades.Prestamo" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Autorización préstamos - Banco Honse</title>
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
        min-height: 100dvh;
        text-align: center;
      }

      #filtrado{
        width: 30%;
        margin: 0 auto;
        padding: .5em;
        background-color: white;
        border-radius: .5em;
        margin-bottom: 3em;
        box-shadow: 15px 20px 10px rgba(2, 2, 2, 0.103);
      }
      #filtrado form {
        display: flex;
        flex-direction: column;
        gap: 1em;
      }
      .div-filtrado{
        display: flex;
        justify-content: space-between;
        align-items: center;
      }
      .div-filtrado input, .div-filtrado select{
        min-width: 50%;
        padding: 0.4em;
      border-radius: .5em;
      outline: none;
      border: 1px solid darkgray;
      }
      #submit {
        padding: .6em;
        border-radius: .5em;
        width: 40%;
        cursor: pointer;
        outline: none;
        background-color: rgba(77, 180, 187, 0.637);
        border: none;
        transition: all .2s ease;
        margin: 0 auto;
    }
    #submit:hover{
        background-color: rgba(38, 117, 122, 0.637);
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
      background-color: green;
    }
    #estado-td{
        display: flex;
        gap: .5em;
    }

    </style>
<script src="https://code.jquery.com/jquery-3.7.0.min.js"></script>
	<script src="https://cdn.datatables.net/1.13.4/js/jquery.dataTables.min.js"></script>
	<link rel="stylesheet" href="https://cdn.datatables.net/1.13.4/css/jquery.dataTables.min.css">
</head>
<body>
<%@ include file="./HeaderAdmin.jsp" %>
    <main>
        <h2>GESTION DE PRÉSTAMOS</h2>
        <div id="contenedor">
            <div id="filtrado">
            <%
            String estado="";
            String dni="";
            if(request.getAttribute("filtroEstado")!=null){
            	estado = (String)request.getAttribute("filtroEstado");
            }
            if(request.getAttribute("filtroDni")!=null){
            	dni = (String)request.getAttribute("filtroDni");
            }
            %>
                <form method="post" action="PrestamosServlet">
                    <div class="div-filtrado">
                        <label>Estado de Préstamo</label>
                        <select name="ddlEstado" id="">
                            <option value="" <%= ("".equals(estado)) ? "selected" : "" %>>Todos</option>
                            <option value="Pendiente" <%= ("Pendiente".equals(estado)) ? "selected" : "" %>>Pendiente</option>
                            <option value="Aprobado" <%= ("Aprobado".equals(estado)) ? "selected" : "" %> >Aprobado</option>
                            <option value="Rechazado" <%= ("Rechazado".equals(estado)) ? "selected" : "" %>>Rechazado</option>
                        </select>
                    </div>
                    <div class="div-filtrado">
                        <label>DNI Cliente</label>
                        <input type="text" name="txtDni" value="<%=dni%>">
                    </div>
                    <input id="submit" type="submit" name="btnFiltrar" value="Filtrar Préstamos">
                </form>

            </div>
            <div id="tabla">
                <table id="tabla-prestamos">
                    <thead>
                        <tr>
                            <th>ID Préstamo</th>
                            <th>Cuenta</th>
                            <th>DNI Cliente</th>
                            <th>Importe a pagar</th>
                            <th>Importe solicitado</th>
                            <th>Monto mensual</th>
                            <th>Plazo en cuotas</th>
                            <th>Fecha</th>
                            <th>Estado</th>
                            <th>Aceptar</th>
                            <th>Rechazar</th>
                        </tr>
                    </thead>
                    <tbody>
                    <%
                    List<Prestamo> listaPrestamos = (List<Prestamo>) request.getAttribute("listaPrestamos");
                    if(listaPrestamos != null){
                    	for(Prestamo prestamo : listaPrestamos){
                    %>
                    
                        <tr>
                            <td>
                                <input name="txtIdPrestamo" type="hidden" value="<%= prestamo.getIdPrestamo() %>">
                                <%= prestamo.getIdPrestamo() %>
                            </td>
                            <td>
                             <input type="hidden" value="<%= prestamo.getCuenta() %>">
                           		<%= prestamo.getCuenta()%>
                            </td>
                            <td>
                                <input type="hidden" value="<%= prestamo.getDni()%>">
                                <%= prestamo.getDni()%>
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
                                <input type="hidden" value="<%= prestamo.getMontoPorMes()%>" >
                            	<%= prestamo.getMontoPorMes() %>
                            </td>
                             <td>
                                <input type="hidden" value="<%= prestamo.getPlazoPagos() %>" >
                            	<%= prestamo.getPlazoPagos() %>
                            </td>
                             <td>
                                <input type="hidden" value="<%= prestamo.getFecha() %>" >
                            	<%= prestamo.getFecha()%>
                            </td>

                            <td id="estado-td">
                            
                            <input type="hidden" value="<%= prestamo.getEstado() %>">
                            <%= prestamo.getEstado()%>
                            </td>
                            
							<td>
							<%
							boolean pendiente = false;
							if("Pendiente".equals(prestamo.getEstado() )){
								pendiente = true;
							}
							%>
							<form method="post" class="form-confirm" action="PrestamosServlet">
							  <input name="txtIdPrestamo" type="hidden" value="<%= prestamo.getIdPrestamo() %>">
                               <input name="btnAutorizar" type="submit" value="Autorizar" style="<%= pendiente ? "background-color:green" : "background-color: grey; cursor:not-allowed" %>" class="btn" <%= pendiente? "" : "disabled" %>>				
							</form>
							</td>  
							<td>
							<form method="post" class="form-confirm" action="PrestamosServlet">
								<input name="txtIdPrestamo" type="hidden" value="<%= prestamo.getIdPrestamo() %>">
								<input name="btnRechazar" type="submit" value="Rechazar" class="btn" style="<%= pendiente ? "background-color:red" : "background-color: grey; cursor:not-allowed" %>"  <%= pendiente? "" : "disabled" %>>
							</form>
							</td>                          
                        </tr>
                    
                       <% } %>
                    <% }else{ %>
                          <tr>
  							<td>No hay prestamos para mostrar.</td>
  								<% for (int i = 1; i < 10; i++) { %>
   										 <td></td>
 								 <% } %>
						 </tr>
                    <% } %>
                    </tbody>
                </table>
            </div>
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