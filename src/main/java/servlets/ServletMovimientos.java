package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import entidades.Cliente;
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
        
        try {// Verificar si existe sesión y usuario logueado
            HttpSession session = request.getSession(false);
            if(session == null || session.getAttribute("usuarioLogueado") == null) {
                response.sendRedirect(request.getContextPath() + "/login.jsp");
                return;
            }
            
            //Obtener usuario logueado desde la sesión
            Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
            Cliente cliente = clienteNegocio.obtenerPorUsuarioNick(usuario.getNickUsuario());
            
            if(cliente == null) {
                throw new ServletException("No se encontraron datos del cliente");
            }
            
            // Obtener parámetros de filtrado
            String fecha = request.getParameter("fecha");
            String nroCuenta = request.getParameter("nCta");
            String importe = request.getParameter("importe");
            String tipo = request.getParameter("tipo");
            
            //Almacenar resultados
            List<Object[]> movimientosConCuentas;
            
            // Verificar si hay filtros aplicados
            if((fecha != null && !fecha.isEmpty()) || 
               (nroCuenta != null && !nroCuenta.isEmpty()) || 
               (importe != null && !importe.isEmpty()) || 
               (tipo != null && !tipo.isEmpty())) {
                
                movimientosConCuentas = movimientoNegocio.filtrarMovimientosConCuenta(
                    cliente.getDNI(), fecha, nroCuenta, importe, tipo);
            } else {
            	//Obtener todos los movimientos sin filtros
                movimientosConCuentas = movimientoNegocio.obtenerMovimientosConCuenta(cliente.getDNI());
            }
            
            request.setAttribute("movimientosConCuentas", movimientosConCuentas);
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