<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LogIn - Banco Honse</title>
</head>
<style>
    body{
        text-align: center;
        background: linear-gradient(
        0deg,
        rgba(44, 144, 170, 1) 0%,
        rgba(113, 190, 196, 0.616) 70%
      );
      
       font-family: system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
    }
    main{
      min-height: 98dvh;
      display:flex;
      flex-direction: column;
      justify-content:center;
    }
    h1{
        color: black;
    }
    #form-login{
        background-color: rgb(255, 255, 255);
        width: 30%;
        margin: 0 auto;
        box-shadow: 15px 20px 10px rgba(32, 32, 32, 0.212);
        text-align: left;
        display: flex;
        flex-direction: column;
        place-items: center;
    }
    #div-form{
        width: 80%;
        display: flex;
        flex-direction: column;
        padding: 1rem;  
    }

    #div-form input{
        padding: 1em;
        border-radius: .5em;
        border: 1px solid rgb(187, 187, 187);
        margin-top: .5em;
    }
    #div-form input:focus{
        border: 1px solid rgb(77, 180, 187);
        outline: none;
    }
    #submit-button{
        padding: 1em;
        margin: 1em 0 2em 0;
        border-radius: .5em;
        width: 20%;
        cursor: pointer;
        outline: none;
        background-color: rgba(77, 180, 187, 0.637);
        border: none;
        transition: all .2s ease;
    }
    #submit-button:hover{
        background-color: rgba(38, 117, 122, 0.637);
    }
</style>
<body>
<main>
	<h1>Login</h1>
    <form id="form-login" action="">
        <div id="div-form">
            <label>Nombre de Usuario</label>
            <input type="text" placeholder="Juan123">
        </div>
        <div id="div-form">
            <label>Contraseña</label>
            <input type="password" placeholder="Contraseña muy segura">
        </div>
        <input id="submit-button" type="submit" value="Ingresar">
    </form>
    <br>
    <a href="">¿No tiene una cuenta? Registrese aquí</a>
</main>
</body>
</html>