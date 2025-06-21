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
      background: linear-gradient(
        0deg,
        rgba(44, 144, 170, 1) 0%,
        rgba(113, 190, 196, 0.616) 70%
      );
      background-repeat: no-repeat;
      background-size: cover;
      height: 98vh;
      font-family: system-ui, -apple-system, BlinkMacSystemFont, "Segoe UI",
        Roboto, Oxygen, Ubuntu, Cantarell, "Open Sans", "Helvetica Neue",
        sans-serif;
    }

    header{
        display: flex;
        justify-content: space-between;
        width: 80%;
        margin: 1% auto;
    }

    h2{
        text-align: center;
    }

    main {
      width: 100%;
      margin: 0 auto;   
      padding: 1em;
    }

    #content {
      width: 80%;
      margin: 0 auto;
      display: flex;
      flex-direction: column;
      gap: 2em;
    }

    form {
        background-color:white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 5px #ccc;
        }

        label {
            display: block;
            margin-top: 10px;
            font-weight: bold;
        }

        input[type="date"] {
            padding: 6px;
            width: 100%;
            box-sizing: border-box;
            margin-top: 5px;
        }

        button {
            margin-top: 20px;
            padding: 10px 20px;
            background-color: #0077cc;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        button:hover {
            background-color: #005fa3;
        }

        table {
            border-collapse: collapse;
            width: 100%;
            margin-top: 30px;
            background: white;
            border-radius: 6px;
            overflow: hidden;
            box-shadow: 0 0 5px #bbb;
            text-align: center;
        }

    h1{
        text-align: center;
    }

    </style>
</head>
<body>
    <%@ include file="./HeaderAdmin.jsp" %> 
    <main>
        <h2>Reportes </h2>

        <label>Monto total de Ingresos y Egresos</label>
        <table>
        <tr>
            <th>Fecha Inicio</th>
            <th>Fecha Fin</th>
            <th>Total Ingresos</th>
            <th>Total Egresos</th>
        </tr>
        <tr>
            <td><input type="date" name="FechaInicioIE"></td>
            <td><input type="date" name="FechaFinIE"></td>
            <td>*Insertar datos de la BD*</td>
            <td>*Insertar datos de la BD*</td>
        </tr>
    </table>

    <label>Transferencias totales entre fechas</label>
    <table>
        <tr>
            <th>Fecha Inicio</th>
            <th>Fecha Fin</th>
            <th>Transferencias totales</th>
        </tr>
        <tr>
            <td><input type="date" name="FechaInicioTF"></td>
            <td><input type="date" name="FechaFinTF"></td>
            <td> *Insertar datos de la BD* </td>
        </tr>
    </table>

    <label>Cliente con mayor actividad</label>
    <table>
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
    
    <label>Pago de cuotas</label>
    <table>
        <tr>
            <th>Id cliente</th>
            <th>Id préstamo</th>
            <th>Numero de cuota</th>
            <th>Fecha de pago</th>
            <th>Estado</th>
        </tr>
        <tr>
            <td><select id="IdCliente"></select>
            <br>*Cargar con datos de la BD*</td>
            <td>*Insertar datos de la BD*</td>
            <td>*Insertar datos de la BD*</td>
            <td>*Insertar datos de la BD*</td>
            <td>*Insertar datos de la BD*</td>
        </tr>
    </table>
    </main>
    <footer>
        <h1>Banco Honse, siempre con vos.</h1>
    </footer>
</body>
</html>