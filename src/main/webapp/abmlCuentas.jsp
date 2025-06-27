<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
       <%@ page import="java.util.ArrayList" %>
       <%@ page import="entidades.TipoCuenta" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ABML cuentas - Banco Honse</title>
	<style>
  body {
    margin: 0;
    margin-bottom: 3em;
    background: linear-gradient(
      0deg,
      rgba(44, 144, 170, 1) 0%,
      rgba(113, 190, 196, 0.616) 70%
    );
    font-family: system-ui, -apple-system, BlinkMacSystemFont, "Segoe UI",
      Roboto, Oxygen, Ubuntu, Cantarell, "Open Sans", "Helvetica Neue",
      sans-serif;
    text-align: center;
  }

  main {
    margin-bottom: 5em;
  }

  /*ALTA CUENTA*/

  #div-alta-cuenta {
    width: 80%;
    margin: 0 auto 5em auto;
  }
  #div-form-alta {
    background-color: white;
    width: 50%;
    margin: 0 auto;
    box-shadow: 15px 20px 10px rgba(2, 2, 2, 0.103);
  }
  #form-alta {
    padding: 1em;
    display: flex;
    flex-direction: column;
    gap: 1em;
  }
  #form-alta > div {
    display: flex;
    justify-content: space-between;
    flex-direction: row;
  }
  #form-alta > div input,
  #form-alta > div select,
  #form-buscar input[type="text"] {
    min-width: 20em;
    padding: 0.4em;
    border-radius: 0.5em;
    outline: none;
    border: 1px solid darkgray;
  }
  #form-alta > input[type="submit"] {
    width: 50%;
    margin: 0 auto;
    padding: 1em;
    margin-top: 2em;
    border-radius: 0.5em;
    cursor: pointer;
    outline: none;
    background-color: rgba(77, 180, 187, 0.637);
    border: none;
    transition: all 0.2s ease;
  }
  #form-alta > input[type="submit"]:hover,
  #form-buscar input[type="submit"]:hover {
    background-color: rgba(38, 117, 122, 0.637);
  }

  /*LISTADO CUENTA*/
  #contenedor-listado-cuenta {
    display: flex;
    flex-direction: column;
    gap: 2em;
  }
  #form-buscar input[type="submit"] {
    margin: 0 auto;
    padding: 0.5em;
    border-radius: 0.5em;
    cursor: pointer;
    outline: none;
    background-color: rgba(77, 180, 187, 0.637);
    border: none;
    transition: all 0.2s ease;
    border: 1px solid white;
  }
  #listado-cuenta {
    background-color: white;
    width: 90%;
    margin: 0 auto;
    padding: 1em;
    box-shadow: 15px 20px 10px rgba(2, 2, 2, 0.103);
  }
  #listado-cuenta table {
    border-collapse: collapse;
    text-align: center;
  }

  #listado-cuenta thead {
    background-color: #343a40;
    color: white;
    position: sticky;
    top: 0;
    z-index: 1;
  }

  #listado-cuenta th,
  #listado-cuenta td {
    border: 1px solid #ddd;
    padding: 8px;
    min-width: 120px;
  }

  #listado-cuenta input[type="text"],
  #listado-cuenta input[type="date"],
  #listado-cuenta select {
    width: 100%;
    padding: 4px;
    border: 1px solid #ccc;
    border-radius: 4px;
  }

  #listado-cuenta input[type="submit"] {
    padding: 6px 10px;
    border: none;
    border-radius: 0.5em;
    cursor: pointer;
    color: white;
  }

  .btn-warning {
    background-color: #c2830f;
  }

  .btn-danger {
    background-color: #dc3545;
  }
</style>
	
</head>
<body>
    <%@ include file="./HeaderAdmin.jsp" %>
    <main>
      <div id="div-alta-cuenta">
        <h2>DAR DE ALTA</h2>
        <div id="div-form-alta">
          <form id="form-alta" action="ServletCuentas" method="post">
            <div>
           <%
           int numeroDeCuenta = 0;
           if (request.getAttribute("numeroDeCuenta")!=null) {
        	    numeroDeCuenta = (int) request.getAttribute("numeroDeCuenta");// valor por defecto o manejo alternativo
           }
           %>
              <label>Numero de Cuenta</label>
              <input
                type="text"
                name="NumeroCuenta"
                disabled
                value="<%=numeroDeCuenta%>"
              />
            </div>
            <div>
              <label>DNI del Titular</label>
              <input type="text" name="DNI" id="" />
            </div>
            <div>
              <label>CBU</label>
              <input type="text" name="CBU" />
            </div>
            <div>
              <label>Fecha de Creación</label>
              <input type="date" name="fechaCreacion" />
            </div>
            <div>
              <label>Tipo de Cuenta</label>
              <select name="ddlTipoCuenta" id="">
                <%
                		ArrayList<TipoCuenta> tiposCuentas;
                            if(request.getAttribute("listaTiposCuentas") != null) {
                            	tiposCuentas = (ArrayList<TipoCuenta>) request.getAttribute("listaTiposCuentas");
                                for(TipoCuenta tipo : tiposCuentas) {
                        %>
                            <option value="<%= tipo.getIdTipo() %>"><%= tipo.getNombre() %></option>
                        <%
                                } // for
                            } // if
                        %>
              </select>
            </div>
            <div>
              <label>Saldo Inicial</label>
              <input name="saldoInicial" type="text" value="10.000" disabled />
            </div>
            <input type="submit" value="Vincular Cuenta al Cliente" name="btnAgregar"/>
          </form>
          <%
         String mensaje =(String)request.getAttribute("mensajeAlta");
          %>
          <%=mensaje %>
        </div>
      </div>

      <div id="div-listado-cuenta">
        <h2>LISTADO Y MODIFICACION</h2>
        <div id="contenedor-listado-cuenta">
          <div id="buscar-cuenta">
            <form id="form-buscar">
              <label>Ingrese DNI del cliente</label>
              <input type="text" name="DNIClienteBuscar" />
              <input type="submit" value="Buscar Cuentas" />
            </form>
          </div>
          <div id="listado-cuenta">
            <table>
              <thead>
                <tr>
                  <th>Numero de Cuenta</th>
                  <th>DNI</th>
                  <th>CBU</th>
                  <th>Tipo de Cuenta</th>
                  <th>Saldo</th>
                  <th>Fecha de Creacion</th>
                  <th>Modificar</th>
                  <th>Eliminar</th>
                </tr>
              </thead>
              <tbody>
              <form method="post" action="ServletCuentas">
                <tr>
                  <td>
                    <input name="txtTablaNumero" type="text" value="Obtener Numero DB" disabled />
                  </td>

                  <td>
                    <input name="txtTablaDni" type="text" value="DNI" disabled />
                  </td>

                  <td>
                    <input name="txtTablaCbu" type="text" value="CBU" disabled />
                  </td>

</body>
                  <td>
                    <select name="ddlTablaTipo">
                      <option value="1">Caja de Ahorro</option>
                      <option value="1">Cuenta Corriente</option>
                    </select>
                  </td>
                </td>
                <td>
                  <input name="txtTablaSaldo" type="text" value="10.000">
                </td>
                  <td>
                    <input name="txtTablaFecha" type="date" />
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
              </form>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </main>
  </body>
</html>