<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ABML cuentas - Banco Honse</title>
    <style>
        body {
            margin: 0;
            background: linear-gradient(0deg,
                    rgba(44, 144, 170, 1) 0%,
                    rgba(113, 190, 196, 0.616) 70%);
            background-repeat: no-repeat;
            background-size: cover;
            height: 98vh;
            font-family: system-ui, -apple-system, BlinkMacSystemFont, "Segoe UI",
                Roboto, Oxygen, Ubuntu, Cantarell, "Open Sans", "Helvetica Neue",
                sans-serif;
            text-align: center;
        }

        /*
        header {
            display: flex;
            justify-content: space-between;
            width: 80%;
            margin: 1% auto;
        }

        h2 {
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
            background-color: white;
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

        h1 {
            text-align: center;
        }
*/
        .reporte {
            width: 80%;
            margin: 0 auto;
            text-align: left;
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
        <h2>Reportes </h2>

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
                <br>
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