<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="entidades.Cliente, java.text.SimpleDateFormat" %>
<%
    // Obtener el cliente del request
    Cliente cliente = (Cliente) request.getAttribute("cliente");
    
    // Formateador de fecha
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    String fechaNacimiento = "";
    if(cliente != null && cliente.getFechaNacimiento() != null) {
        fechaNacimiento = sdf.format(cliente.getFechaNacimiento());
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="imgs/logo_Honse-nombre.png" type="image/png">
    
    <title>Perfil - Banco Honse</title>
</head>
<style>
    body {
        margin: 0;
        background: #2C90AA;
        background: linear-gradient(0deg, rgba(44, 144, 170, 1) 0%, rgba(255, 252, 253, 1) 90%);
        background-repeat: no-repeat;
        background-size: cover;
        min-height: 98vh;
        font-family: system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
    }
    
   
    
    h1 {
        text-align: center;
    }
    
    a {
        text-decoration: none;
        color: #2C90AA;
    }
    
    .inicio {
        display: flex;
        align-items: center;
        gap: 15px;
    }
    
    #logoBanco {
        width: 75px;
    }
    
    #volver {
        margin-left: 10%;
        font-size: larger;
        color: white;
        background-color: #2C90AA;
        padding: 8px 15px;
        border-radius: 5px;
        display: inline-block;
        margin-top: 10px;
    }
    
    .perfilCliente {
        background-color: rgba(255, 252, 253, 1);
        width: 80%;
        margin: 20px auto;
        border-radius: 10px;
        padding: 20px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    }
    
    .perfilCliente h1 {
        padding: 0 0 20px 0;
        text-align: left;
        border-bottom: 1px solid #eee;
        margin-bottom: 20px;
    }
    
    .datos {
        display: flex;
        gap: 40px;
    }
    
    .ftPerfil {
        flex: 1;
        display: flex;
        justify-content: center;
        align-items: flex-start;
    }
    
    #imgPerfil {
        width: 200px;
        height: 200px;
        object-fit: cover;
        border-radius: 50%;
        border: 3px solid #2C90AA;
    }
    
    .info {
        flex: 2;
        display: flex;
        flex-direction: column;
        gap: 15px;
    }
    
    .grupo {
        display: flex;
        align-items: center;
        gap: 15px;
        margin-bottom: 15px;
    }
    
    .grupo label {
        width: 150px;
        font-weight: bold;
        color: #333;
    }
    
    .grupo input {
        flex: 1;
        padding: 10px;
        border: 1px solid #ddd;
        border-radius: 4px;
        background-color: #f9f9f9;
    }
    
    .grupo-doble {
        display: flex;
        gap: 20px;
    }
    
    .grupo-doble .subgrupo {
        flex: 1;
        display: flex;
        align-items: center;
        gap: 15px;
    }
    
    .grupo-simple {
        display: flex;
        align-items: center;
        gap: 15px;
    }
    
    .grupo-simple input {
        flex: 1;
    }
    
    footer {
        text-align: center;
        margin-top: 30px;
        padding: 20px;
        color: white;
    }
</style>
<body>
    <%@ include file="./HeaderCliente.jsp" %>
    <a href="ServletClientes" id="volver">🡠 Volver</a>
    <main>
        <div class="perfilCliente">
            <h1>Perfil</h1>
            <div class="datos">
                <div class="ftPerfil">
                    <img src="${pageContext.request.contextPath}/imgs/logoPerfilDefault.png" alt="imgPerfil" id="imgPerfil">
                </div>
                <div class="info">
                    <div class="grupo">
                        <label>Nombre:</label>
                        <input type="text" value="<%= cliente != null ? cliente.getNombre() : "" %>" disabled>
                        <label>Apellido:</label>
                        <input type="text" value="<%= cliente != null ? cliente.getApellido() : "" %>" disabled>
                    </div>
                    <div class="grupo">
                        <label>DNI:</label>
                        <input type="text" value="<%= cliente != null ? cliente.getDNI() : "" %>" disabled>
                        <label>CUIL:</label>
                        <input type="text" value="<%= cliente != null ? cliente.getCUIL() : "" %>" disabled>
                    </div>
                    <div class="grupo">
                        <label>Fecha Nacimiento:</label>
                        <input type="text" value="<%= fechaNacimiento %>" disabled>
                        <label>Sexo:</label>
                        <input type="text" value="<%= cliente != null ? cliente.getSexo() : "" %>" disabled>
                    </div>
                    <div class="grupo">
                        <label>Nacionalidad:</label>
                        <input type="text" value="<%= cliente != null && cliente.getNacionalidad() != null ? cliente.getNacionalidad().getNombre() : "" %>" disabled>
                        <label>País Residencia:</label>
                        <input type="text" value="<%= cliente != null && cliente.getPais() != null ? cliente.getPais().getNombre() : "" %>" disabled>
                    </div>
                    <div class="grupo">
                        <label>Provincia:</label>
                        <input type="text" value="<%= cliente != null && cliente.getProvincia() != null ? cliente.getProvincia().getNombre() : "" %>" disabled>
                        <label>Localidad:</label>
                        <input type="text" value="<%= cliente != null && cliente.getLocalidad() != null ? cliente.getLocalidad().getNombre() : "" %>" disabled>
                    </div>
                    <div class="grupo">
                        <label>Domicilio:</label>
                        <input type="text" value="<%= cliente != null ? cliente.getDomicilio() : "" %>" disabled>
                    </div>
                    <div class="grupo">
                        <label>Email:</label>
                        <input type="text" value="<%= cliente != null ? cliente.getEmail() : "" %>" disabled>
                        <label>Teléfono:</label>
                        <input type="text" value="<%= cliente != null ? cliente.getTelefono() : "" %>" disabled>
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