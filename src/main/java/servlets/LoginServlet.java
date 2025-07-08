package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import entidades.Cliente;
import entidades.Usuario;
import negocio.UsuarioNegocio;
import negocioImpl.UsuarioNegocioImpl;
import negocio.ClienteNegocio;
import negocioImpl.ClienteNegocioImpl;

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
    		String mensajeError = "usuario Incorrecto";
    		
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
    					ClienteNegocio clienteNeg = new ClienteNegocioImpl();
                    Cliente cliente = clienteNeg.obtenerPorUsuarioNick(nick); // Usamos el nick directamente
                    
                    if (cliente != null) {
                        session.setAttribute("clienteLogueado", cliente);
                        // Redirección absoluta para evitar problemas
                        response.sendRedirect(request.getContextPath() + "/ServletClientes");
                        return;
                    } else {
                        request.setAttribute("errorLogin", "No se encontraron datos del cliente");
                    }
                }
            }
            
            request.setAttribute("errorLogin", "Usuario o contraseña incorrectos");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    	}

    }
    // esta funcion verifica que el usuario este iniciado 
    // y en ese caso le setea al objeto pasado por parametro
    // los valores que le faltan (id y tipo )    
    
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