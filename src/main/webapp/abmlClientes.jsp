<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ABML clientes - Banco Honse</title>

<style>
    body {
      margin: 0;
      background: linear-gradient(
        0deg,
        rgba(44, 144, 170, 1) 0%,
        #cfcdcd 70%
      );
      font-family: system-ui, -apple-system, BlinkMacSystemFont, "Segoe UI",
        Roboto, Oxygen, Ubuntu, Cantarell, "Open Sans", "Helvetica Neue",
        sans-serif;
    }

    main {
      width: 100%;
      margin: 0 auto;
      padding-top: 8em;

    }

    
    .contenedor-tabla {
    	width:90%;
    	margin: 0 auto;
        overflow-x: auto;
        padding: 1em;
        background: #fff;
        margin-bottom:5em;
        box-shadow: 15px 20px 10px rgba(2, 2, 2, 0.103);
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

    th,
    td {
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
      border-radius: .5em;
      cursor: pointer;
      color: white;
    }

    .btn-warning {
      background-color: #c2830f;
    }

    .btn-danger {
      background-color: #dc3545;
    }

    #div-crear-cliente {
      width: 80%;
      margin: 0 auto 5em auto;

    }

    #div-crear-cliente form div {
      width: 100%;
    }
    #div-crear-cliente form {
      display: grid;
      max-width: 100%;
      grid-template-columns: 1fr 1fr;
      grid-template-rows: 1fr;
      gap: 1em;
      padding: 1em;
     background-color: white;
     box-shadow: 15px 20px 10px rgba(2, 2, 2, 0.103);
    }
    .form-lado {
      display: flex;
      flex-direction: column;
      gap: 1em;
      
    }
    .form-lado > div {
      display: flex;
      align-items: center;
      gap: 1em;
    }
    form label {
      width: 15em;
    }
    form input,
    form select {
      flex: 1;
      padding: 0.4em;
      border-radius: .5em;
      outline: none;
      border: 1px solid darkgray;
    }
    .form-submit{
          grid-column: 1 / -1; 
          display: flex;
          justify-content: center;
          
    }

    .form-submit button{
        padding: 1em;
        margin-top: 2em;
        border-radius: .5em;
        width: 20%;
        cursor: pointer;
        outline: none;
        background-color: rgba(77, 180, 187, 0.637);
        border: none;
        transition: all .2s ease;
    }
    .form-submit button:hover{
        background-color: rgba(38, 117, 122, 0.637);
    }
    h2{
        text-align: center;
    }
  </style>

</head>


  <body>
  <%@ include file="./HeaderAdmin.jsp" %> 
    <main>
      <div id="div-crear-cliente">
        <h2>DAR DE ALTA</h2>

        <form>
          <div class="form-lado">
            <div>
              <label for="txtDni">DNI</label>
              <input type="text" id="txtDni" name="txtDni" />
            </div>

            <div>
              <label for="txtCuil">CUIL</label>
              <input type="text" id="txtCuil" name="txtCuil" />
            </div>

            <div>
              <label for="txtNombre">Nombre</label>
              <input type="text" id="txtNombre" name="txtNombre" />
            </div>

            <div>
              <label for="txtApellido">Apellido</label>
              <input type="text" id="txtApellido" name="txtApellido" />
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
          </div>

          <div class="form-lado">
            <div>
              <label for="ddlLocalidad">Localidad</label>
              <select id="ddlLocalidad" name="ddlLocalidad">
                <option selected>Seleccione</option>
              </select>
            </div>

            <div>
              <label for="txtFechaDeNacimiento">Fecha de nacimiento</label>
              <input
                type="date"
                id="txtFechaDeNacimiento"
                name="txtFechaDeNacimiento"
              />
            </div>

            <div>
              <label for="txtCorreo">Correo</label>
              <input type="email" id="txtCorreo" name="txtCorreo" />
            </div>

            <div>
              <label for="txtTelefono">Teléfonos</label>
              <input type="text" id="txtTelefono" name="txtTelefono" />
            </div>

            <div>
              <label for="txtUsuario">Usuario</label>
              <input type="text" id="txtUsuario" name="txtUsuario" />
            </div>

            <div>
              <label for="txtContraseña">Contraseña</label>
              <input type="password" id="txtContraseña" name="txtContraseña" />
            </div>

            <div>
              <label for="txtContraseña2">Confirmar Contraseña</label>
              <input
                type="password"
                id="txtContraseña2"
                name="txtContraseña2"
              />
            </div>
          </div>

          <div class="form-submit">
            <button type="submit">Crear</button>
          </div>
        </form>
      </div>

      <h2>LISTADO Y MODIFICACION</h2>
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
                <input type="text" name="dni" value="123456789" disabled/>
              </td>
              <td>
                <input type="text" name="cuil" value="0123456789" disabled />
              </td>
              <td>
                <input type="text" name="nombre" value="nombre" />
              </td>
              <td>
                <input type="text" name="apellido" value="apellido" />
              </td>
              <td>
                <input type="text" name="sexo" value="sexo" />
              </td>
              <td>
                <input type="date" name="fechaNacimiento" value="2/10/2004" />
              </td>
              <td>
                <select name="ddlPais"></select>
              </td>
              <td>
                <select name="ddlProvincia"></select>
              </td>
              <td>
                <select name="ddlLocalidad"></select>
              </td>
              <td>
                <input type="text" name="direccion" value="echevrria 2343" />
              </td>
              <td>
                <input type="text" name="correo" value="correo@gmail.com" />
              </td>
              <td>
                <input type="text" name="telefono" value="telefono" />
              </td>
              <td>
                <input type="text" name="usuario" value="nombreUsuario" disabled />
              </td>
              <td>
                <input type="text" name="contraseña" value="contraseña" />
              </td>
              <td>
                <input
                  type="submit"
                  name="btnModificar"
                  class="btn btn-warning"
                  value="Modificar"
                />
              </td>
              <td>
                <input
                  type="submit"
                  name="btnEliminar"
                  class="btn btn-danger"
                  value="Eliminar"
                />
              </td>
            </tr>
          </tbody>
        </table>
      </div>

    </main>
  </body>
</html>