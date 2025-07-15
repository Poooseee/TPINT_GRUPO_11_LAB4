package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import entidades.Cliente;
import entidades.Cuenta;
import entidades.Usuario;
import negocioImpl.CuentaNegocioImpl;
import negocioImpl.ClienteNegocioImpl;

@WebServlet("/ServletClientes")
public class ServletClientes extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private CuentaNegocioImpl cuentaNegocio = new CuentaNegocioImpl();
    private ClienteNegocioImpl clienteNegocio = new ClienteNegocioImpl();
    
    public ServletClientes() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    throws ServletException, IOException {
    	//Al cargar la pagina mostrar las diferentes cuentas del cliente
    	
	    	//1.Obtener el cliente a traves del usuario
	    	HttpSession session = request.getSession(false);
	    	Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
	    	Cliente cienteLogueado = clienteNegocio.obtenerPorUsuarioNick(usuario.getNickUsuario());
	    	
	    	//2.Obtener las cuentas del cliente
	    	ArrayList <Cuenta> cuentas = cuentaNegocio.obtenerListaCuentas(cienteLogueado.getDNI(), false);
	    	
	    	//3.Mandar la info de las cuentas y el saldo de la primera como default
	    	if(!cuentas.isEmpty()) {
	    		request.setAttribute("cuentasCliente", cuentas);
	    		request.setAttribute("saldoCuentaSeleccionada", cuentas.get(0).getSaldo());
	    		request.setAttribute("numeroCuenta", cuentas.get(0).getNumero());
	    	}
	    	
	    	//4.Validar si ya habia un cbu seleccionado, caso positivo mandar ese
	    	String cbuPreviamenteSeleccionado = request.getParameter("cbuSeleccionado");
	    	System.out.println(cbuPreviamenteSeleccionado+ "cbu");
	    	if(cbuPreviamenteSeleccionado !=null) {
	    		Cuenta cuenta = cuentaNegocio.obtenerCuentaPorCBU(cbuPreviamenteSeleccionado);
	        	float saldo = cuenta.getSaldo();
	        	int numeroCuenta = cuenta.getNumero();
	        	request.setAttribute("saldoCuentaSeleccionada",saldo);
	        	request.setAttribute("cbuSeleccionado", cbuPreviamenteSeleccionado);
	        	request.setAttribute("numeroCuenta", numeroCuenta);
	    	}
	    	
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("/menuCliente.jsp");
	    	dispatcher.forward(request, response);
    }
 

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

    	//OBTENER SALDO Y NUMERO DE LA CUENTA SELECCIONADA
    	String cbu = request.getParameter("cbuSeleccionado");
    	Cuenta cuenta = cuentaNegocio.obtenerCuentaPorCBU(cbu);
    	float saldo = cuenta.getSaldo();
    	int numeroCuenta = cuenta.getNumero();
    	request.setAttribute("numeroCuenta", numeroCuenta);
    	request.setAttribute("saldoCuentaSeleccionada",saldo);
    	request.setAttribute("cbuSeleccionado", cbu);
    	
    	//Volver a mandar las cuentas del cliente
    	HttpSession session = request.getSession(false);
    	Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
    	Cliente clienteLogueado = clienteNegocio.obtenerPorUsuarioNick(usuario.getNickUsuario());
    	ArrayList<Cuenta> cuentas = cuentaNegocio.obtenerListaCuentas(clienteLogueado.getDNI(), false);
    	request.setAttribute("cuentasCliente", cuentas);
    	
    	//DESPACHAR
    	RequestDispatcher dispatcher = request.getRequestDispatcher("/menuCliente.jsp");
		dispatcher.forward(request, response);

    }
}