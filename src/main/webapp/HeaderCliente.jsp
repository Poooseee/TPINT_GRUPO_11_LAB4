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
#imgPerfilLogo{
	width: 75px;
   border-radius: 100%;
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
	        <a href="ServletClientes">
	          <img src="./imgs/logo_Honse-sinNombre.png" alt="Logo">
	        </a>
        </div>
        <div id="admin-interaccion">
          <div id="nombre-admin">
            <p>Hola, <%=nombreUs%></p>
          </div>
          <a href="LogoutServlet" id="cerrarSesion">Cerrar Sesión</a>
        </div>
    </div>
    
    <div id="div-nav">
      <div class="perfil">
            <a href="${pageContext.request.contextPath}/ServletPerfilCliente">
    			<img id="imgPerfilLogo" src="${pageContext.request.contextPath}/imgs/logoPerfilDefault.png" alt="imgPerfil">
			</a>
      </div>
    </div>
  </header>

