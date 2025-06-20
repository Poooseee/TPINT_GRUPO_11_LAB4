<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="imgs/logo_Honse-nombre.png" type="image/png">
    <title>Menú Cliente - Banco Honse</title>
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
    header{
        display: flex;
        justify-content: space-between;
        width: 80%;
        margin: 1% auto;
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
    .saldo{
        margin: 10% auto 0 auto;
        width: 75%;
        height: 20vh;
        background-color: rgba(255, 252, 253, 1);
        border-radius: 10px;
    }
    .saldoSuperior{
        display: flex;
        justify-content: space-between;
    }
    .saldoSuperior a{
        margin: 0.5% 1% 0 0;
    }
    .saldoSuperior h2{
        padding-left: 1%;
    }
    .saldoInferior{
        display: flex;
        margin-top: 1%;
    }
    .saldoInferior p{
        padding-left: 1%;
        padding-right: 4%;
    }
    .btnsAcciones{
        margin: 1% auto 10% auto;
        width: 75%;
        border-radius: 10px;
    }
    .btnsAccionesImgs{
        display: flex;
        justify-content: space-around;
    }
    .btnsAcciones a{
        background-color: rgba(255, 252, 253, 1);
        padding: 1%;
        border-radius: 100%;
    }
    .btnsAcciones img{
        width: 96px;
    }
    .btnsAccionesTxt{
        display: flex;
        justify-content: space-around;
        margin-right: 1%;
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
    <main>
        <div class="saldo">
            <div class="saldoSuperior">
                <h2>Saldo disponible:</h2>
                <a href="movimientosCliente.jsp">Ir a movimientos</a>
            </div>
            <div class="saldoInferior">
                <p>*Ingresar saldo de la cuenta en la BD*</p>
                <button><img src="imgs/logoOcultar.png" alt="logoOcultar"></button>    
            </div>
        </div>
        <div class="btnsAcciones">
            <div class="btnsAccionesImgs">
                <a href="transferenciasCliente.jsp"><img src="imgs/logoTransferencia.png" alt="logoTransferencia"></a>
                <a href="prestamosCliente.jsp"><img src="imgs/logoPrestamo.png" alt="logoPrestamo"></a>
            </div>
            <div class="btnsAccionesTxt">
                <h3>Transferencia</h3>
                <h3>Préstamo</h3>
            </div>
        </div>
    </main>
    <footer>
        <h1>Banco Honse, siempre con vos.</h1>
    </footer>
</body>
</html>