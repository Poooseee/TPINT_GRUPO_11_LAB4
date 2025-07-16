package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import entidades.Cuenta;
import entidades.Prestamo;
import negocioImpl.CuentaNegocioImpl;
import negocioImpl.PrestamoNegocioImpl;

/**
 * Servlet implementation class PagoPrestamoServlet
 */
@WebServlet("/PagoPrestamoServlet")
public class PagoPrestamoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PrestamoNegocioImpl PrestamoNeg = new PrestamoNegocioImpl();
	Cuenta cuenta = new Cuenta();
	CuentaNegocioImpl negCuenta = new CuentaNegocioImpl();

    public PagoPrestamoServlet() {
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Obtenemos el cbu seleccionado para que siga el flujo
		String cbuSeleccionado = request.getParameter("cbuSeleccionado");
		request.setAttribute("cbuSeleccionado", cbuSeleccionado);
		
		//AL CARGAR LA PAGINA OBTENER EL CLIENTE Y MOSTRAR LOS PRESTAMOS
		cuenta = negCuenta.obtenerCuentaPorCBU(cbuSeleccionado);
		List<Prestamo> listado = PrestamoNeg.get("", cuenta.getDni());
		request.setAttribute("listado", listado);
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/PagoPrestamos.jsp");
		dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Obtenemos el cbu seleccionado para que siga el flujo
		String cbuSeleccionado = request.getParameter("cbuSeleccionado");
		request.setAttribute("cbuSeleccionado", cbuSeleccionado);
		
		//SE DISPARA AL HACER CLICK EN DETALLE
		if(request.getParameter("btnDetalle")!=null) {
			
			String idPrestamo = request.getParameter("idPrestamo");
			
			
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/PagoPrestamos.jsp");
		dispatcher.forward(request, response);
	}

}
