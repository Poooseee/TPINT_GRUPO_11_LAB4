package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import entidades.Cliente;
import entidades.Cuenta;
import entidades.Usuario;
import negocioImpl.ClienteNegocioImpl;
import negocioImpl.CuentaNegocioImpl;
import negocioImpl.MovimientoNegocioImpl;

@WebServlet("/ServletMovimientos")
public class ServletMovimientos extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private MovimientoNegocioImpl movimientoNegocio = new MovimientoNegocioImpl();
    private ClienteNegocioImpl clienteNegocio = new ClienteNegocioImpl();
    private CuentaNegocioImpl cuentaNegocio = new CuentaNegocioImpl();
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
    
    	//Al cargar la pagina
    	
    	// Verificar si existe sesión y usuario logueado
        HttpSession session = request.getSession(false);
        if(session == null || session.getAttribute("usuarioLogueado") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }
        
        //1.Obtener usuario logueado desde la sesión
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
        Cliente cliente = clienteNegocio.obtenerPorUsuarioNick(usuario.getNickUsuario());
        
        if(cliente == null) {
            throw new ServletException("No se encontraron datos del cliente");
        }
        
        //2.Obtener las cuentas del cliente
    	ArrayList <Cuenta> cuentas = cuentaNegocio.obtenerListaCuentas(cliente.getDNI(), false);
    	request.setAttribute("cuentasCliente", cuentas);

    	//3. Obtener la cuenta mediante el cbu seleccionado
        String cbuSeleccionado = request.getParameter("cbuSeleccionado");
        Cuenta cuenta = cuentaNegocio.obtenerCuentaPorCBU(cbuSeleccionado);
        String numeroCuenta = String.valueOf(cuenta.getNumero());
        
        //4. Mandar la tabla filtrada por ese numero de cuenta
        List<Object[]> movimientosConCuentas = movimientoNegocio.filtrarMovimientosConCuenta(cliente.getDNI(), null, numeroCuenta, null, null);

        request.setAttribute("movimientosConCuentas", movimientosConCuentas);
        request.setAttribute("cbuSeleccionado", cbuSeleccionado);

	    //5. Despachar a la vista
	    RequestDispatcher dispatcher = request.getRequestDispatcher("/movimientosCliente.jsp");
	    dispatcher.forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	//SE DISPARA CUANDO CAMBIAS LA CUENTA DEL DDL Y AL FILTRAR
    	
    	//1. Obtener y mandar las cuentas del cliente
    	HttpSession session = request.getSession(false);
    	Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
    	Cliente clienteLogueado = clienteNegocio.obtenerPorUsuarioNick(usuario.getNickUsuario());
    	ArrayList<Cuenta> cuentas = cuentaNegocio.obtenerListaCuentas(clienteLogueado.getDNI(), false);
    	request.setAttribute("cuentasCliente", cuentas);
    	
    	//2. Obtener la cuenta mediante el cbu seleccionado
        String cbuSeleccionado = request.getParameter("cbuSeleccionado");
        Cuenta cuenta = cuentaNegocio.obtenerCuentaPorCBU(cbuSeleccionado);
        String numeroCuenta = String.valueOf(cuenta.getNumero());
        
        //3. Ver si se aplicaron filtros
        String fecha = request.getParameter("fecha");
        String importeFiltro = request.getParameter("importe");
        String tipoMovimiento = request.getParameter("tipo");
        
        
        //4. Mandar la tabla filtrada por ese numero de cuenta
        List<Object[]> movimientosConCuentas = movimientoNegocio.filtrarMovimientosConCuenta(clienteLogueado.getDNI(), fecha, numeroCuenta, importeFiltro, tipoMovimiento);
        request.setAttribute("movimientosConCuentas", movimientosConCuentas);
        request.setAttribute("cbuSeleccionado", cbuSeleccionado);

    	//Despachar a la vista
	    RequestDispatcher dispatcher = request.getRequestDispatcher("/movimientosCliente.jsp");
	    dispatcher.forward(request, response);

    }
}