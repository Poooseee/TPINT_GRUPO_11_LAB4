package servlets;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import entidades.Cliente;
import entidades.Cuenta;
import entidades.Usuario;
import negocio.CuentaNegocio;
import negocioImpl.CuentaNegocioImpl;
import negocio.ClienteNegocio;
import negocioImpl.ClienteNegocioImpl;

@WebServlet("/ServletClientes")
public class ServletClientes extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CuentaNegocio cuentaNegocio = new CuentaNegocioImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("✔ Entró al doGet de ServletClientes");
        
        HttpSession session = request.getSession(false);
        
        if(session == null || session.getAttribute("clienteLogueado") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        Cliente cliente = (Cliente) session.getAttribute("clienteLogueado");
        Cuenta cuenta = cuentaNegocio.obtenerCuentaPorDni(cliente.getDNI());
        
        request.setAttribute("nombreCliente", cliente.getNombre());
        request.setAttribute("apellidoCliente", cliente.getApellido());
        request.setAttribute("saldoDisponible", cuenta != null ? cuenta.getSaldo() : 0);
        
        if(cuenta == null) {
            request.setAttribute("error", "No se encontró cuenta asociada");
        }

        request.getRequestDispatcher("/menuCliente.jsp").forward(request, response);
    }
}