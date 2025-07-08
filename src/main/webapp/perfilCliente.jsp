<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="imgs/logo_Honse-nombre.png" type="image/png">
    <title>Perfil - Banco Honse</title>
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
    #volver{
        margin-left: 1%;
        font-size: larger;
    }
    .perfilCliente{
        background-color: rgba(255, 252, 253, 1);
        width: 80%;
        margin: 0 auto 3% auto;
        border-radius: 10px;
        height: 70vh;
    }
    .perfilCliente h1{
        padding: 2% 0 1% 2%;
        text-align: left;
    }
    .datos{
        display: flex;
    }
    #imgPerfil{
        width: 25em;
    }
    .info{
        display: flex;
        flex-direction: column;
    }
    .info input{
        width: 20em;
        height: 3em;
    }
    .info label{
        width: 10em;
        font-weight: bold;
    }
    .info .grupo{
        display: flex;
        align-items: center;
        gap: 1em;
        margin: 1em auto;
    }
</style>
<body>
    <header>
        <div class="inicio">
            <a href="menuCliente.jsp"><img src="imgs/logo_Honse-sinNombre.png" alt="logoBanco" id="logoBanco"></a>
            <p>Hola, *introducir nombre*</p>    
        </div>
    </header>
    <a href="menuCliente.jsp" id="volver">🡠 Volver</a>
    <main>
            <div class="perfilCliente">
                <h1>Perfil</h1>
                <div class="datos">
                    <div class="ftPerfil">
                        <img src="imgs/logoPerfilDefault.png" alt="imgPerfil" id="imgPerfil">
                    </div>
                    <div class="info">
                        <div class="grupo">
                            <label for="nombre">Nombre:</label>
                            <input type="text" id="nombre" name="nombre" placeholder="*Ingresar nombre de la BD*" disabled>
                            <label for="apellido">Apellido:</label>
                            <input type="text" id="apellido" name="apellido" placeholder="*Ingresar apellido de la BD*" disabled>
                        </div>
                        <div class="grupo">
                            <label for="fechaNac">Fecha de nacimiento:</label>
                            <input type="text" id="fechaNac" name="fechaNac" placeholder="*Ingresar fechaNac de la BD*" disabled>
                            <label for="sexo">Sexo:</label>
                            <input type="text" id="sexo" name="sexo" placeholder="*Ingresar sexo de la BD*" disabled>
                        </div>
                        <div class="grupo">
                            <label for="nacionalidad">Nacionalidad:</label>
                            <input type="text" id="nacionalidad" name="nacionalidad" placeholder="*Ingresar nacionalidad de la BD*" disabled>
                            <label for="provincia">Provincia:</label>
                            <input type="text" id="provincia" name="provincia" placeholder="*Ingresar provincia de la BD*" disabled>
                        </div>
                        <div class="grupo">
                            <label for="localidad">Localidad:</label>
                            <input type="text" id="localidad" name="localidad" placeholder="*Ingresar localidad de la BD*" disabled>
                            <label for="domicilio">Domicilio:</label>
                            <input type="text" id="domicilio" name="domicilio" placeholder="*Ingresar domicilio de la BD*" disabled>
                        </div>
                        <div class="grupo">
                            <label for="mail">Correo electrónico:</label>
                            <input type="text" id="mail" name="mail" placeholder="*Ingresar mail de la BD*" disabled>
                            <label for="tel">Número de teléfono:</label>
                            <input type="text" id="tel" name="tel" placeholder="*Ingresar tel de la BD*" disabled>
                        </div>
                    </div>
                </div>
            </div>
    </main>
    <footer>
        <h1>Banco Honse, siempre con vos.</h1>
    </footer>
</body>
</html>