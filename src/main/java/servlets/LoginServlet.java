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
        String nick = request.getParameter("nick");
        String pass = request.getParameter("password");

        UsuarioNegocio usuarioNegocio = new UsuarioNegocioImpl();
        Usuario usuario = usuarioNegocio.login(nick, pass);

        if (usuario != null) {
            HttpSession session = request.getSession();
            session.setAttribute("usuarioLogueado", usuario);

            if ("admin".equalsIgnoreCase(usuario.getTipoUsuario())) {
                response.sendRedirect("MenuAdmin.jsp");
            } else if ("cliente".equalsIgnoreCase(usuario.getTipoUsuario())) {
                response.sendRedirect("MenuCliente.jsp");
            } else {
                // Por si hay un tipo desconocido
                request.setAttribute("errorLogin", "Tipo de usuario no válido.");
                request.getRequestDispatcher("Login.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("errorLogin", "Usuario o contraseña incorrectos.");
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        }
    }
}