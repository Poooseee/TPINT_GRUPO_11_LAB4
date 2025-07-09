package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidades.Cliente;
import entidades.Usuario;
import negocioImpl.ClienteNegocioImpl;

@WebServlet("/ServletPerfilCliente")
public class ServletPerfilCliente extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private ClienteNegocioImpl clienteNegocio = new ClienteNegocioImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        
    	//Obtiene la sesión actual
        HttpSession session = request.getSession(false);
        
        // Verificar si hay sesión y usuario logueado
        if(session == null || session.getAttribute("usuarioLogueado") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }
        
        try {//Recupera el usuario logueado de la sesión
            Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
            
            // Verificar que el usuario sea válido
            if(usuario == null || usuario.getNickUsuario() == null) {
                throw new ServletException("Datos de usuario inválidos");
            }
        
        //Obtiene datos básicos del cliente usando el nick del usuario
        Cliente clienteBasico = clienteNegocio.obtenerPorUsuarioNick(usuario.getNickUsuario());
        //Obtiene todos los datos del cliente usando su DNI
        Cliente clienteCompleto = clienteNegocio.obtenerClienteCompleto(clienteBasico.getDNI());
        
        //Envía el cliente completo al JSP de perfil
        request.setAttribute("cliente", clienteCompleto);
        request.getRequestDispatcher("/perfilCliente.jsp").forward(request, response);
        
        } catch (Exception e) {
            // 5. Manejo de errores
            request.setAttribute("error", "Error al cargar el perfil: " + e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}