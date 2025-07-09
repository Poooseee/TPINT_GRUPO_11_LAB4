<%@page import="entidades.Usuario"%>

<style>
  header {
  width: 100%;
  margin: 0;
  background: #ffffff;
  background: linear-gradient(90deg, rgba(255, 255, 255, 1) 21%, rgba(77, 180, 187, 1) 100%);
  font-family: system-ui;
  height: 8em;
  display: flex;
  flex-direction: row;
  border-bottom: 1px solid rgba(77, 180, 187, 1);
  }

#informacion {
  display: flex;
  flex-direction: row;
  align-items: center;
  
  width: 30%;
  padding: 0 1em;
  
}

#logo img {
  height: 5em;
  width: auto;
  margin-right: 1em;
}

#admin-interaccion {
  display: flex;
  flex-direction: column;
  justify-content: center;
  gap: 0.3em;
}

#nombre-admin {
  display: flex;
  align-items: center;
  gap: 0.3em;
}

#nombre-admin a,
#nombre-admin p {
  margin: 0;
  font-size: 1em;
  color: #333;
  text-decoration: none;
}

#nombre-admin a:hover, #cerrarSesion:hover {
  text-decoration: underline;
}

#nombre-admin {
  display: flex;
  gap: 0.5em;
}


#div-nav {
  width: 70%;
  display: flex;
  align-items: center;
  justify-content: flex-end;
  padding-right: 2em;
  gap: 2em;
}

#nav-header {
  display: flex;
  gap: 1.5em;
}

#nav-header a {
  text-decoration: none;
  color: black;
  font-weight: 400;
  font-size: large;
}
#admin-interaccion a{
  text-decoration: none;
  color: black;
}
  
</style>
<%
Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
String nombreUs = "Invitado";
if (usuario != null) {
    nombreUs = usuario.getNickUsuario();
}

%>
    <header>
      <div id="informacion">
        <div id="logo">
          <img src="./imgs/logo_Honse-sinNombre.png" alt="Logo">
        </div>
        <div id="admin-interaccion">
          <div id="nombre-admin">
            <a href="./menuAdministrador.jsp">Administrador ~</a>
        
            <p><%=nombreUs%></p>
          </div>
          <a href="LogoutServlet" id="cerrarSesion">Cerrar Sesión</a>
        </div>
    </div>
    <div id="div-nav">
      <nav id="nav-header">
        <a id="a-nav-header" href="abmlClientesServlet">Clientes</a>
        <a id="a-nav-header" href="ServletCuentas?param=menu">Cuentas</a>
        <a id="a-nav-header" href="PrestamosServlet">Préstamos</a>
        <a id="a-nav-header" href="ReportesServlet">Reportes</a>
      </nav>
    </div>
  </header>

