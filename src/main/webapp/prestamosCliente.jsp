<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="imgs/logo_Honse-nombre.png" type="image/png">
    <title>Préstamo - Banco Honse</title>
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
    .prestamos{
        background-color: rgba(255, 252, 253, 1);
        width: 80%;
        margin: 0 auto 3% auto;
        border-radius: 10px;
        height: 70vh;
    }
    .prestamos h1{
        padding: 2% 0 1% 2%;
        text-align: left;
    }
    .pedir{    
        height: 80%;
        width: fit-content;
        margin: 5% auto;
    }
    .formulario {
        display: flex;
        flex-direction: column;
        gap: 3em;
    }
    .formulario .agrupar {
    display: flex;
    align-items: center;
    gap: 1em;
    }

    .formulario label {
    width: 10em;
    font-weight: bold;
    }
    .formulario input[type="text"] {
    width: 25em;
    height: 2em;
    }
    .formulario input{
        width: 25em;
        height: 2em;
        margin: auto;
    }
</style>
<body>
    <header>
        <div class="inicio">
            <a href="menuCliente.jsp"><img src="imgs/logo_Honse-sinNombre.png" alt="logoBanco" id="logoBanco"></a>
            <p>Hola, *introducir nombre*</p>    
        </div>
        <div class="perfil">
            <a href="perfilCliente.jsp"><img src="imgs/logoPerfilDefault.png" alt="imgPerfil" id="imgPerfil"></a>
        </div>
    </header>
    <a href="menuCliente.jsp" id="volver">🡠 Volver</a>
    <main>
            <div class="prestamos">
                <h1>Préstamo</h1>
                <div class="pedir">
                    <form action="prestamosCliente.jsp" method="post" class="formulario">
                        <div class="agrupar">
                            <label for="montoPedido">*Hasta $75.000.000*</label>
                            <input type="text" id="montoPedido" name="montoPedido" placeholder="Monto a pedir">
                        </div>
                        <div class="agrupar">
                            <label for="cantCuotas">*Máximo 48 cuotas*</label>
                            <input type="text" id="cantCuotas" name="cantCuotas" placeholder="Cantidad de cuotas">
                        </div>
                        <div class="agrupar">
                            <label for="totalDevolver">Total a pagar</label>
                            <input type="text" id="totalDevolver" name="totalDevolver" placeholder="*Insertar total a pagar*">
                        </div>
                        <div class="agrupar">
                            <label for="montoCuotas">Monto por cuota</label>
                            <input type="text" id="montoCuotas" name="montoCuotas" placeholder="*Insertar valor de cada cuota*">
                        </div>
                        <input type="submit" value="Pedir préstamo" name="btnPedir" id="btnPedir">
                    </form> 
                </div>
            </div>
    </main>
    <footer>
        <h1>Banco Honse, siempre con vos.</h1>
    </footer>
</body>
</html>