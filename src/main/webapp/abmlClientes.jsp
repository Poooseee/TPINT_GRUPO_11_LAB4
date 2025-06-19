<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ABML clientes - Banco Honse</title>
<link rel="styleSheet" href="estilos.css">

<style>

body{
        margin: 0;
        background-color: rgb(219, 220, 221);
        font-family: system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
    }
    a{
        text-decoration: none;
    }
    h1{
        text-align: center;
        color: rgba(49, 49, 49, 0.527);
    }
    nav{
        display: flex;
        text-align: center;
        justify-content: space-between;
        padding: 1% 2.5%;
        margin: 0 1%;
        background-color: rgb(255, 255, 255);
    }
    nav a{
        color: black;
        transition: transform 0.3s ease-in-out;
    }
    main{
        background-color: rgb(255, 255, 255);
        margin: 3.5% 1%;
        
    }
    main p{
        padding-left: 1%;
    }
    main h2{
        padding-left: 1%;
    }
     nav a:hover{
        transform: scale(1.2);
        color: #007BFF;
    }

    .contenedor{
    
      max-width: 1200px;
      margin: auto;
      background: #fff;
      padding: 20px;
      border-radius: 8px;
    
    }
    form {
      display: grid;
      grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
      gap: 16px;
     
    }

     label {
      display: block;
      margin-bottom: 5px;
      
    }

      input,
    select {
      width: 100%;
      padding: 8px;
      border: 1px solid #ccc;
      border-radius: 4px;
    }

     button {
      padding: 10px 20px;
      border: none;
      background-color: #007bff;
      color: white;
      border-radius: 4px;
      cursor: pointer;
      transition: background 0.3s ease;
    }

    button:hover {
      background-color: #0056b3;
    }

    @media (max-width: 768px) {
      form {
        grid-template-columns: 1fr;
      }
    }

     .full-width {
      grid-column: 1 / -1;
    }

    .contenedor-tabla{
         overflow-x: auto;
      background: #fff;
      padding: 15px;
      border-radius: 6px;
    }

    table {
      width: 100%;
      border-collapse: collapse;
      text-align: center;
    }

     thead {
      background-color: #343a40;
      color: white;
      position: sticky;
      top: 0;
      z-index: 1;
    }

    th, td {
      border: 1px solid #ddd;
      padding: 8px;
      min-width: 120px;
    }

    input[type="text"],
    input[type="date"],
    select {
      width: 100%;
      padding: 4px;
       border: 1px solid #ccc;  
      border-radius: 4px;
    }

    input[type="submit"] {
      padding: 6px 10px;
      border: none;
      border-radius: 4px;
      cursor: pointer;
      color: white;
    }

    input[type="submit"].btn-warning {
      background-color: #ffc107;
    }

    input[type="submit"].btn-danger {
      background-color: #dc3545;
    }


</style>

</head>
<body>
<header>
        <a href="menuAdmin.jsp"><h1>Administrador</h1></a>
        <nav>
            <a href="abmlClientes.jsp">Clientes</a>
            <a href="abmlCuentas.jsp">Cuentas</a>
            <a href="autorizacionPrest.jsp">Préstamos</a>
            <a href="reporte.jsp">Reporte</a>
        </nav>
    </header>
    <main>
        <div class="contenedor">
          <form>
             <div>
                 <label for="txtDni">DNI</label>
                 <input type="text" id="txtDni" name="txtDni">
             </div>
             <div>
                <label for="txtCuil">CUIL</label>
                <input type="text" id="txtCuil" name="txtCuil">
             </div>
              <div>
        <label for="txtNombre">Nombre</label>
        <input type="text" id="txtNombre" name="txtNombre">
      </div>
      <div>
        <label for="txtApellido">Apellido</label>
        <input type="text" id="txtApellido" name="txtApellido">
      </div>
      <div>
        <label for="ddlSexo">Sexo</label>
        <select id="ddlSexo" name="ddlSexo">
          <option selected>Seleccione</option>
          <option>Masculino</option>
          <option>Femenino</option>
        </select>
      </div>
      <div>
        <label for="ddlNacionalidad">Nacionalidad</label>
        <select id="ddlNacionalidad" name="ddlNacionalidad">
          <option selected>Seleccione</option>
        </select>
      </div>
      <div>
        <label for="ddlProvincia">Provincia</label>
        <select id="ddlProvincia" name="ddlProvincia">
          <option selected>Seleccione</option>
        </select>
      </div>
      <div>
        <label for="ddlLocalidad">Localidad</label>
        <select id="ddlLocalidad" name="ddlLocalidad">
          <option selected>Seleccione</option>
        </select>
      </div>
      <div>
        <label for="txtFechaDeNacimiento">Fecha de nacimiento</label>
        <input type="date" id="txtFechaDeNacimiento" name="txtFechaDeNacimiento">
      </div>
      <div>
        <label for="txtCorreo">Correo</label>
        <input type="email" id="txtCorreo" name="txtCorreo">
      </div>
      <div>
        <label for="txtTelefono">Teléfonos</label>
        <input type="text" id="txtTelefono" name="txtTelefono">
      </div>
      <div>
        <label for="txtUsuario">Usuario</label>
        <input type="text" id="txtUsuario" name="txtUsuario">
      </div>
      <div>
        <label for="txtContraseña">Contraseña</label>
        <input type="password" id="txtContraseña" name="txtContraseña">
      </div>
      <div>
        <label for="txtContraseña2">Confirmar Contraseña</label>
        <input type="password" id="txtContraseña2" name="txtContraseña2">
      </div>
      <div class="full-width">
        <button type="submit">Crear</button>
      </div>
          </form>
        </div>

        <div class="contenedor-tabla">
            <table>
                <thead>
                 <tr>
                    <th>Dni</th>
                    <th>CUIL</th>
                    <th>Nombre</th>
                    <th>Apellido</th>
                    <th>Sexo</th>
                    <th>Fecha de Nacimiento</th>
                    <th>Nacionalidad</th>
                    <th>Provincia</th>
                    <th>Localidad</th>
                    <th>Direccion</th>
                    <th>Correo</th>
                    <th>Telefonos</th>
                    <th>Usuario</th>
                    <th>Contraseña</th>
                    <th>Modificar</th>
                    <th>Eliminar</th>
                    
                 </tr>
                </thead>
                <tbody>
                  <tr>
                     <td>      
                      <input  type="text" name="dni" value="123456789">
                     </td>
                     <td>      
                        <input  type="text" name="cuil" value="0123456789">
                      </td>
                      <td>      
                        <input  type="text" name="nombre" value="nombre" >
                      </td>
                      <td>      
                          <input  type="text" name="apellido" value="apellido" >
                       </td>
                        <td>      
                            <input  type="text" name="sexo" value="sexo" >
                        </td>
                        <td>      
                            <input  type="date" name="fechaNacimiento" value="2/10/2004" >
                        </td>
                        <td>
                            <select  name="ddlPais" >
             
                             </select>
                        </td>
                        <td>
                            <select  name="ddlProvincia" >
             
                             </select>
                        </td>
                        <td>
                            <select  name="ddlLocalidad" >
             
                            </select>
                        </td>
                        <td>      
                            <input  type="text" name="direccion" value="echevrria 2343" >
                        </td>
                        <td>      
                            <input  type="text" name="correo" value="correo@gmail.com" >
                        </td>
                        <td>      
                            <input  type="text" name="telefono" value="telefono">
                        </td>
                        <td>      
                            <input  type="text" name="usuario" value="nombreUsuario" >
                        </td>
                        <td>      
                            <input  type="text" name="contraseña" value="contraseña" >
                        </td>
                        <td><input type="submit" name="btnModificar" class="btn btn-warning" value="Modificar"></td>
                        <td><input type="submit" name="btnEliminar" class="btn btn-danger" value="Eliminar"></td>
      
                  </tr>
                </tbody>
            </table>

        </div>
    </main>
</body>
</html>