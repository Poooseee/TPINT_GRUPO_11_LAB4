<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
            min-width: 20em;
            padding: 0.4em;
            border-radius: 0.5em;
            outline: none;
            border: 1px solid darkgray;
        }

        #reporte2-table {
            background-color: white;
            width: 100%;
            text-align: center;
            box-shadow: 15px 20px 10px rgba(2, 2, 2, 0.103);
        }
        #reporte3-table {
            background-color: white;
            width: 100%;
            text-align: center;
            box-shadow: 15px 20px 10px rgba(2, 2, 2, 0.103);
        }

    </style>
</head>
<body>
    <%@ include file="./HeaderAdmin.jsp" %> 
    <main>
        <div class="reporte">
            <h3>Transferencias totales entre fechas</h3>
            <form id="reporte1-form">
                <div>
                    <label>Fecha Inicio</label>
                    <input type="date">
                </div>
                <div>
                    <label>Fecha Fin</label>
                    <input type="date">
                </div>
                <div>
                    <label>Cantidad Total de transferencias</label>
                    <input type="text" disabled value="CANTIDAD">
                </div>
            </form>
         </div>
         
          <div class="reporte">
            <h3>Cliente con mayor actividad</h3>
            <table id="reporte2-table">
                <tr>
                	<th>Id Cliente</th>
                    <th>Cantidad de Transferencias</th>
                    <th>Cantidad de Préstamos</th>
                </tr>
                <tr>
                  <td>*Insertar datos de la BD*</td>
                    <td>*Insertar datos de la BD*</td>
                    <td>*Insertar datos de la BD*</td>
                </tr>
              </table>
       	</div>
        <br>
        <div class="reporte">
        	<div id="reporteCuotas">
                <form>
                    <label>Porcentaje de cuotas ACEPTADAS</label>
                    <input type="text" disabled value="25%">
                </form>
                <br>
                <form>
                    <label>Porcentaje de cuotas RECHAZADAS</label>
                    <input type="text" disabled value="25%">
                </form>
                <br>
                <form>
                    <label>Porcentaje de cuotas PENDIENTES</label>
                    <input type="text" disabled value="25%">
                </form>
        	</div>
        </div>
        <div class="reporte">
            <h3>Pago de cuotas</h3>
            <table id="reporte3-table">
                <tr>
                    <th>Id cliente</th>
                    <th>Id préstamo</th>
                    <th>Numero de cuota</th>
                    <th>Fecha de pago</th>
                    <th>Estado</th>
                </tr>
                <tr>
                    <td><select id="IdCliente"></select>
                        <br>*Cargar con datos de la BD*
                    </td>
                    <td>*Insertar datos de la BD*</td>
                    <td>*Insertar datos de la BD*</td>
                    <td>*Insertar datos de la BD*</td>
                    <td>*Insertar datos de la BD*</td>
                </tr>
                </table>
                </div>
         
            
    </main>

</body>
</html>