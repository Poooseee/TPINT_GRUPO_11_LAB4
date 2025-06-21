<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
</head>
<body>
<%@ include file="./HeaderAdmin.jsp" %>
    <main>
        <h2>GESTION DE PRÉSTAMOS</h2>
        <div id="contenedor">
            <div id="filtrado">
                <form>
                    <div class="div-filtrado">
                        <label>Estado de Préstamo</label>
                        <select name="ddlEstado" id="">
                            <option value="Todos">Todos</option>
                            <option value="Pendiente">Pendiente</option>
                            <option value="Aprobado">Aprobado</option>
                            <option value="Rechazado">Rechazado</option>
                        </select>
                    </div>
                    <div class="div-filtrado">
                        <label>DNI Cliente</label>
                        <input type="text" name="DNI" value="45813799">
                    </div>
                    <input id="submit" type="submit" value="Filtrar Préstamos">
                </form>

            </div>
            <div id="tabla">
                <table>
                    <thead>
                        <tr>
                            <th>ID Préstamo</th>
                            <th>DNI Cliente</th>
                            <th>Importe a pagar</th>
                            <th>Importe solicitado</th>
                            <th>Monto mensual</th>
                            <th>Plazo en cuotas</th>
                            <th>Fecha</th>
                            <th>Estado</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>
                                <input type="text" value="Obtener DB" disabled>
                            </td>
                            <td>
                                <input type="text" value="45813799" disabled>
                            </td>
                             <td>
                                <input type="text" value="200.000" disabled>
                            </td>
                             <td>
                                <input type="text" value="150.000" disabled>
                            </td>
                             <td>
                                <input type="text" value="2.000" disabled>
                            </td>
                             <td>
                                <input type="text" value="10" disabled>
                            </td>
                             <td>
                                <input type="date" disabled>
                            </td>
                            <td id="estado-td">
                                <select name="ddlEstadoTabla" id="">
                                    <option value="Pendiente">Pendiente</option>
                                    <option value="Aprobado">Aprobado</option>
                                    <option value="Rechazado">Rechazado</option>
                                </select>
                                <input type="submit" value="Guardar" class="btn">
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>

    </main>
</body>
</html>