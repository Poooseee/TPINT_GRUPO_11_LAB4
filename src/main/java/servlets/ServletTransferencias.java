package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Cuenta;
import negocioImpl.ClienteNegocioImpl;
import negocioImpl.CuentaNegocioImpl;

/**
 * Servlet implementation class ServletTransferencias
 */
@WebServlet("/ServletTransferencias")
public class ServletTransferencias extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    private CuentaNegocioImpl cuentaNegocio = new CuentaNegocioImpl();
    private ClienteNegocioImpl clienteNegocio = new ClienteNegocioImpl();

    public ServletTransferencias() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		//Al cargar la pagina, validar con que cuenta y que saldo quiere transferir
		String cbuPreviamenteSeleccionado = request.getParameter("cbuSeleccionado");
		String numeroCuenta = String.valueOf(request.getParameter("numeroCuenta"));
    	System.out.println(cbuPreviamenteSeleccionado+ "cbu"+numeroCuenta);
    	if(cbuPreviamenteSeleccionado !=null) {
    		Cuenta cuenta = cuentaNegocio.obtenerCuentaPorCBU(cbuPreviamenteSeleccionado);
        	float saldo = cuenta.getSaldo();
        	request.setAttribute("saldoCuentaSeleccionada",saldo);
        	request.setAttribute("cbuSeleccionado", cbuPreviamenteSeleccionado);
        	request.setAttribute("numeroCuenta", numeroCuenta);
    	}
    	
    	RequestDispatcher dispatcher = request.getRequestDispatcher("/transferenciasCliente.jsp");
    	dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
