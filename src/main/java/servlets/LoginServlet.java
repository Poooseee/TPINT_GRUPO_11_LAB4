package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import entidades.Usuario;
import negocio.UsuarioNegocio;
import negocioImpl.UsuarioNegocioImpl;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
    	if(request.getParameter("btnIngresar") != null) {
         
    		String nick = request.getParameter("nick");
    		String pass = request.getParameter("password");
    		
    		String rutaVistaDestino = "/menuLog.jsp";
    		String mensajeError = "Usuario o contraseña incorrectos";
    		
    		Usuario usuario = new Usuario(nick,pass);  
    		
    		if (login(usuario)) {
    		 
    			mensajeError = "";
    			HttpSession session = request.getSession();
    			session.setAttribute("usuarioLogueado", usuario);
    			
    			
    			String tipo = usuario.getTipoUsuario();
    			if(tipo != null && !tipo.trim().isEmpty()) {
    				

    				if(tipo.equals("ADMIN")) {
    					rutaVistaDestino = "/menuAdministrador.jsp";
    				}
    				else if(tipo.equals("CLIENTE")){
    					rutaVistaDestino = "/ServletClientes";
    				}
            }
            
        }else {
        	request.setAttribute("errorLogin", mensajeError);
        }
    		response.sendRedirect(request.getContextPath() + rutaVistaDestino);
    	}

    }

    
    private boolean login(Usuario usuario) {
    	boolean iniciado = false;
    	UsuarioNegocio usuarioNegocio = new UsuarioNegocioImpl();
    Usuario user = usuarioNegocio.login(usuario.getNickUsuario(), usuario.getContraseñaUsuario());
    	if(user != null) {
    		usuario.setIdUsuario(user.getIdUsuario());
    		usuario.setTipoUsuario(user.getTipoUsuario());
    		iniciado = true;
    	}
    	return iniciado;
    }
}