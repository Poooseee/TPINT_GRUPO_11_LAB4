package servlets;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import entidades.Cliente;
import entidades.Movimiento;
import entidades.Usuario;
import negocioImpl.ClienteNegocioImpl;
import negocioImpl.MovimientoNegocioImpl;

@WebServlet("/ServletMovimientos")
public class ServletMovimientos extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private MovimientoNegocioImpl movimientoNegocio = new MovimientoNegocioImpl();
    private ClienteNegocioImpl clienteNegocio = new ClienteNegocioImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        try {
            // 1. Verificar sesión
            HttpSession session = request.getSession(false);
            if(session == null || session.getAttribute("usuarioLogueado") == null) {
                response.sendRedirect(request.getContextPath() + "/login.jsp");
                return;
            }
            
            // 2. Obtener cliente logueado
            Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
            Cliente cliente = clienteNegocio.obtenerPorUsuarioNick(usuario.getNickUsuario());
            
            if(cliente == null) {
                throw new ServletException("No se encontraron datos del cliente");
            }
            
            // 3. Obtener movimientos (sin filtros inicialmente)
            ArrayList<Movimiento> movimientos = movimientoNegocio.obtenerMovimientosPorCliente(cliente.getDNI());
            
            // 4. Enviar datos a la vista
            request.setAttribute("movimientos", movimientos);
            request.getRequestDispatcher("/movimientosCliente.jsp").forward(request, response);
            
        } catch (Exception e) {
            request.setAttribute("error", "Error al cargar movimientos: " + e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response);
    }
}