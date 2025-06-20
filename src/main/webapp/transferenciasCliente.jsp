<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="imgs/logo_Honse-nombre.png" type="image/png">
    <title>Transferencia - Banco Honse</title>
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
    .transferencias{
        background-color: rgba(255, 252, 253, 1);
        width: 80%;
        margin: 0 auto 3% auto;
        border-radius: 10px;
        height: 70vh;
    }
    .transferencias h1{
        padding: 2% 0 1% 2%;
        text-align: left;
    }
    .transferir{    
        height: 80%;
        width: fit-content;
        margin: 8% auto;
    }
    .formulario {
        display: flex;
        flex-direction: column;
        gap: 3em;
    }
    .formulario input{
        width: 25em;
        height: 2em;
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
            <div class="transferencias">
                <h1>Transferencia</h1>
                <div class="transferir">
                    <form action="transferenciasCliente.jsp" method="post" class="formulario">
                        <input type="text" id="nCta" name="nCta" placeholder="Número de cuenta">
                        <input type="text" id="importe" name="importe" placeholder="Importe">
                        <input type="submit" value="Transferir" name="btnTransferir" id="btnTransferir">
                    </form> 
                </div>
            </div>
    </main>
    <footer>
        <h1>Banco Honse, siempre con vos.</h1>
    </footer>
</body>
</html>