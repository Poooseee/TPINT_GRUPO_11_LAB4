package servlets;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Cuenta;
import entidades.Cuota;
import entidades.Movimiento;
import entidades.Prestamo;
import entidades.TipoMovimiento;
import negocioImpl.CuentaNegocioImpl;
import negocioImpl.MovimientoNegocioImpl;
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
	MovimientoNegocioImpl movimientoNeg = new MovimientoNegocioImpl();
	
    public PagoPrestamoServlet() {
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Obtenemos el cbu seleccionado para que siga el flujo
		String cbuSeleccionado = request.getParameter("cbuSeleccionado");
		request.setAttribute("cbuSeleccionado", cbuSeleccionado);
		
		//AL CARGAR LA PAGINA OBTENER EL CLIENTE Y MOSTRAR LOS PRESTAMOS
		cuenta = negCuenta.obtenerCuentaPorCBU(cbuSeleccionado);
		List<Prestamo> listado = PrestamoNeg.get("Aprobado", "", String.valueOf(cuenta.getNumero()));
		request.setAttribute("listado", listado);
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/PagoPrestamos.jsp");
		dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Obtenemos el cbu seleccionado para que siga el flujo
		String cbuSeleccionado = request.getParameter("cbuSeleccionado");
		request.setAttribute("cbuSeleccionado", cbuSeleccionado);
		
		//Volvemos a mandar los prestamos
				cuenta = negCuenta.obtenerCuentaPorCBU(cbuSeleccionado);
				List<Prestamo> listado = PrestamoNeg.get("Aprobado", "", String.valueOf(cuenta.getNumero()));
				request.setAttribute("listado", listado);
				
		//SE DISPARA AL HACER CLICK EN DETALLE
		if(request.getParameter("btnDetalle")!=null) {
			
			//Obtenemos el listado de las cuotas y la mandamos
			String idPrestamo = request.getParameter("idPrestamo");
			String valorCuota = request.getParameter("valorCuota");
			List<Cuota> listadoCuotas = PrestamoNeg.obtenerCuotasPorPrestamo(Integer.parseInt(idPrestamo));
			request.setAttribute("listadoCuotas", listadoCuotas);
			request.setAttribute("valorCuota", valorCuota);
		}
		
		//APRETAR PAGAR CUOTA
		if(request.getParameter("btnPagarCuota")!=null) {
			
			//1. Obtener los primeros datos
			Float valorCuota = Float.parseFloat(request.getParameter("valorCuota")) ;
			int numeroCuota = Integer.parseInt(request.getParameter("numeroCuota"));
			int idPrestamo = Integer.parseInt(request.getParameter("prestamoCuota"));
			int numeroCuenta = cuenta.getNumero();
			
			//validar que el usuario tenga el saldo suficiente
			if(cuenta.getSaldo() - valorCuota < 0) {
				request.setAttribute("saldoInsuficiente", "Saldo insuficiente");
				
				//Volver a cargar las cuotas
				List<Cuota> listadoCuotas = PrestamoNeg.obtenerCuotasPorPrestamo(idPrestamo);
				request.setAttribute("listadoCuotas", listadoCuotas);
				request.setAttribute("valorCuota", String.valueOf(valorCuota));
				/*request.setAttribute("prestamoSeleccionado", idPrestamo);*/
				RequestDispatcher dispatcher = request.getRequestDispatcher("/PagoPrestamos.jsp");
				dispatcher.forward(request, response);
			
				return;
			}
			
			//1.Instanciar el Movimiento
			Movimiento movimiento = new Movimiento();
			movimiento.setDniMovimiento(Integer.parseInt(cuenta.getDni()));
			movimiento.setNumeroCuenta(numeroCuenta);
			movimiento.setFecha(Date.valueOf(LocalDate.now()));
			movimiento.setDetalle("Pago de Cuota de Préstamo");
			movimiento.setImporte(valorCuota);
			movimiento.setTipo(new TipoMovimiento(3,"Pago de un prestamo"));
			
			Boolean pagada = movimientoNeg.pagarCuota(movimiento, numeroCuenta, valorCuota, idPrestamo, numeroCuota);
			
			if(pagada) {
				request.setAttribute("exito", pagada);
			}else {
				request.setAttribute("fracaso", pagada);
			}
			
			//Volver a cargar las cuotas
			List<Cuota> listadoCuotas = PrestamoNeg.obtenerCuotasPorPrestamo(idPrestamo);
			request.setAttribute("listadoCuotas", listadoCuotas);
			request.setAttribute("valorCuota", String.valueOf(valorCuota));
			
			/*request.setAttribute("prestamoSeleccionado", idPrestamo);*/
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/PagoPrestamos.jsp");
		dispatcher.forward(request, response);
	}

}
