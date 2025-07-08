package servlets;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import entidades.Cliente;
import entidades.Cuenta;
import negocio.CuentaNegocio;
import negocioImpl.CuentaNegocioImpl;

@WebServlet("/ServletClientes")
public class ServletClientes extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private CuentaNegocio cuentaNegocio = new CuentaNegocioImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("✔ Entró al doGet de ServletClientes (menú cliente)");

        HttpSession session = request.getSession();
        Cliente cliente = (Cliente) session.getAttribute("clienteLogueado");

        if (cliente == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String nombre = cliente.getNombre();
        String apellido = cliente.getApellido(); //Agrega el apellido
        Cuenta cuenta = cuentaNegocio.obtenerCuentaPorDni(cliente.getDNI()); //Método para obtener la cuenta

        double saldo = 0;
        if (cuenta != null) {
            saldo = cuenta.getSaldo();
        } else {
            //Manejo de error: No se encontró la cuenta
            request.setAttribute("error", "No se encontró la cuenta asociada al cliente.");
        }

        request.setAttribute("nombreCliente", nombre);
        request.setAttribute("apellidoCliente", apellido); //Agrega el atributo al request
        request.setAttribute("saldoDisponible", saldo);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/menuCliente.jsp");
        dispatcher.forward(request, response);
    }
}