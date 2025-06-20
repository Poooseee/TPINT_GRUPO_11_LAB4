<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Movimientos - Banco Honse</title>
</head>
<style>
    body{
        margin: 0;
        background: #2C90AA;
        background: linear-gradient(0deg,rgba(44, 144, 170, 1) 0%, rgba(255, 252, 253, 1) 90%);
        background-repeat: no-repeat;
        background-size: cover;
        height: 98vh;
        font-family: system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
    }
    header{
        display: flex;
        justify-content: space-between;
        width: 80%;
        margin: 1% auto;
    }
    h1{
        padding: 2% 0 1% 2%;
    }
    a{
        text-decoration: none;
    }
    table{
        margin: auto;
    }
    table, tr, td{
        border: 1px solid black;
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
    .movimientos{
        background-color: rgba(255, 252, 253, 1);
        width: 80%;
        margin: auto;
        border-radius: 10px;
        height: 70vh;
    }
    .filtrado{
        display: flex;
        margin: 0 auto 2% auto;
        width: 95%;
        justify-content: space-around;
    }
    .filtrado form{
        display: flex;
        justify-content: space-around;
        width: 95%;
    }
    .filtrado label{
        margin: auto 0 auto 3%;
    }
    #btnFiltrar {
        margin-left: 5%;
    }
</style>
<body>
    <header>
        <div class="inicio">
            <a href="menuCliente.jsp"><img src="D:/PC/Desktop/UTN/Lab. de comp 4/TP INTEGRADOR/REPOSITORIO/TPINT_GRUPO_11_LAB4/src/main/webapp/imgs/logo_Honse-sinNombre.png" alt="logoBanco" id="logoBanco"></a>
            <p>Hola, *introducir nombre*</p>    
        </div>
        <div class="perfil">
            <a href="perfilCliente.jsp"><img src="D:/PC/Desktop/UTN/Lab. de comp 4/TP INTEGRADOR/REPOSITORIO/TPINT_GRUPO_11_LAB4/src/main/webapp/imgs/logoPerfilDefault.png" alt="imgPerfil" id="imgPerfil"></a>
        </div>
    </header>
    <a href="menuCliente.jsp" id="volver">🡠 Volver</a>
    <div class="movimientos">
        <h1>Movimientos</h1>
        <div class="filtrado">
            <p>Filtrar por:</p>
            <form action="movimientosCliente.jsp" method="post">
                <label for="fecha">Fecha:</label>
                <input type="date" id="fecha" name="fecha">
                <label for="nCta">Número de cuenta:</label>
                <input type="text" id="nCta" name="nCta">
                <label for="importe">Importe:</label>
                <select id="importe" name="importe">
                <option value="0a10k">Hasta $10.000</option>
                <option value="10ka50k">De $10.000 hasta $50.000</option>
                <option value="50ka100k">De $50.000 hasta $100.000</option>
                <option value="+100k">Desde $100.000</option>
                </select>
                <label for="tipo">Tipo:</label>
                <select id="tipo" name="tipo">
                <option value="altaCta">Alta de cuenta</option>
                <option value="altaPrest">Alta de un préstamo</option>
                <option value="pagoPrest">Pago de préstamo</option>
                <option value="transferencia">Transferencia</option>
                </select>
                <input type="submit" value="Filtrar" name="btnFiltrar" id="btnFiltrar">
            </form> 
        </div>
        <table>
            <tr>
                <td>Fecha</td>
                <td>Número de cuenta</td>
                <td>Detalle</td>
                <td>Importe</td>
                <td>Tipo de movimiento</td>
            </tr>
            <tr>
                <td>*Insertar datos de la BD*</td>
                <td>*Insertar datos de la BD*</td>
                <td>*Insertar datos de la BD*</td>
                <td>*Insertar datos de la BD*</td>
                <td>*Insertar datos de la BD*</td>
            </tr>
            <tr>
                <td>*Insertar datos de la BD*</td>
                <td>*Insertar datos de la BD*</td>
                <td>*Insertar datos de la BD*</td>
                <td>*Insertar datos de la BD*</td>
                <td>*Insertar datos de la BD*</td>
            </tr>
            <tr>
                <td>*Insertar datos de la BD*</td>
                <td>*Insertar datos de la BD*</td>
                <td>*Insertar datos de la BD*</td>
                <td>*Insertar datos de la BD*</td>
                <td>*Insertar datos de la BD*</td>
            </tr>
            <tr>
                <td>*Insertar datos de la BD*</td>
                <td>*Insertar datos de la BD*</td>
                <td>*Insertar datos de la BD*</td>
                <td>*Insertar datos de la BD*</td>
                <td>*Insertar datos de la BD*</td>
            </tr>
            <tr>
                <td>*Insertar datos de la BD*</td>
                <td>*Insertar datos de la BD*</td>
                <td>*Insertar datos de la BD*</td>
                <td>*Insertar datos de la BD*</td>
                <td>*Insertar datos de la BD*</td>
            </tr>
        </table>
    </div>
</body>
</html>