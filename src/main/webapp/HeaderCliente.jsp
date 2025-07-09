

<%@page import="entidades.Usuario"%>
<header>
	<div class="inicio">
            <a href="menuCliente.jsp"><img src="imgs/logo_Honse-sinNombre.png" alt="logoBanco" id="logoBanco"></a>
            <%
            Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
            String nombreUs = "Invitado";
            if (usuario != null) {
                nombreUs = usuario.getNickUsuario();
            }
            	%>
            <div style="display:flex; flex-direction:column">
	            <p>Hola, <%=nombreUs %></p>
	            <a href="LogoutServlet">Cerrar Sesión</a>   
            </div>
     </div>
     <div class="perfil">
     	<a href="${pageContext.request.contextPath}/ServletClientes/perfil">
    		<img src="${pageContext.request.contextPath}/imgs/logoPerfilDefault.png" alt="imgPerfil" id="imgPerfil">
		</a>
    </div>
</header>
