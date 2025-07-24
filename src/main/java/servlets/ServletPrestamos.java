package servlets;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Excepciones.ErrorPrestamoException;
import entidades.Cuenta;
import entidades.Movimiento;
import entidades.Prestamo;
import entidades.TipoMovimiento;
import negocioImpl.CuentaNegocioImpl;
import negocioImpl.MovimientoNegocioImpl;
import negocioImpl.PrestamoNegocioImpl;

/**
 * Servlet implementation class ServletPrestamos
 */
@WebServlet("/ServletPrestamos")
public class ServletPrestamos extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CuentaNegocioImpl cuentaNegocio = new CuentaNegocioImpl();
    private MovimientoNegocioImpl movimientoNegocio = new MovimientoNegocioImpl();
    private PrestamoNegocioImpl prestamoNegocio = new PrestamoNegocioImpl();
	
    public ServletPrestamos() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Al cargar la pagina, validar con que cuenta y que saldo quiere transferir
				String cbuPreviamenteSeleccionado = request.getParameter("cbuSeleccionado");
				String numeroCuenta = String.valueOf(request.getParameter("numeroCuenta"));

		    	if(cbuPreviamenteSeleccionado !=null) {
		    		Cuenta cuenta = cuentaNegocio.obtenerCuentaPorCBU(cbuPreviamenteSeleccionado);
		        	float saldo = cuenta.getSaldo();
		        	request.setAttribute("saldoCuentaSeleccionada",saldo);
		        	request.setAttribute("cbuSeleccionado", cbuPreviamenteSeleccionado);
		        	request.setAttribute("numeroCuenta", numeroCuenta);
		    	}
		    	
		    	RequestDispatcher dispatcher = request.getRequestDispatcher("/prestamosCliente.jsp");
		    	dispatcher.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1. SE DISPARA TERMINAS DE ESCRIBIR EL MONTO O CUANDO SELECCIONA LAS CUOTAS
		
		//Obtener el monto a pedir - tira error si no hay nada ingresado
		float montoAPedir = Float.parseFloat(request.getParameter("montoPedido"));
			
		//Obtener la cantidad de cuotas
		Integer cantidadCuotas = Integer.parseInt(request.getParameter("cuotas"));	
		
		// Calcular cuota mensual y monto total
		float valorCuota = calcularValorCuota(montoAPedir, cantidadCuotas);
		float montoTotal = valorCuota * cantidadCuotas;

		// Pasar los datos al JSP
		request.setAttribute("montoPedido", montoAPedir);
		request.setAttribute("cuotas", cantidadCuotas);
		request.setAttribute("valorCuota", valorCuota);
		request.setAttribute("montoTotal", montoTotal);

		//2. SE DISPARA CUANDO HACES CLICK EN PEDIR
		if(request.getParameter("btnPedir")!=null) {
			
			//Obtener los elementos del préstamo
			float montoPedido = Float.parseFloat(request.getParameter("montoPedido"));
			Integer cantCuotas = Integer.parseInt(request.getParameter("cuotas"));	
			float totalDevolver = Float.parseFloat(request.getParameter("totalDevolver"));
			float montoCuotas = Float.parseFloat(request.getParameter("montoCuotas"));
			String estadoPrestamo = "Pendiente";
			Date fechaPrestamo = Date.valueOf(LocalDate.now());
			
			//Obtener los elementos de la cuenta
			String cbuSeleccionado = request.getParameter("cbuSeleccionado");
			Cuenta cuenta = cuentaNegocio.obtenerCuentaPorCBU(cbuSeleccionado);
			String DNI = cuenta.getDni();
			int numeroCuenta = cuenta.getNumero();
			
			//Instanciar la entidad Prestamo para insertar a la DB
			Prestamo prestamo = new Prestamo();
			prestamo.setDni(DNI);
			prestamo.setCuenta(numeroCuenta);
			prestamo.setFecha(fechaPrestamo);
			prestamo.setImportePagar(totalDevolver);
			prestamo.setImportePedido(montoPedido);
			prestamo.setPlazoPagos(cantCuotas);
			prestamo.setMontoPorMes(montoCuotas);
			prestamo.setEstado(estadoPrestamo);
			
			
			//Insertar el prestamos en las tablas
			try {
				boolean prestamoInsertado = prestamoNegocio.insertarPrestamo(prestamo);
				
				
				if(!prestamoInsertado) throw new ErrorPrestamoException();
				request.setAttribute("PrestamoRealizado", "Préstamo Solicitado de Forma Correcta");
			    request.setAttribute("montoPedido", null);
			    request.setAttribute("cuotas", null);
			    request.setAttribute("valorCuota", null);
			    request.setAttribute("montoTotal", null);
			    
			}catch(ErrorPrestamoException e) {
				devolverYDespacharConError(e.getMessage(),response,request,cuenta);
				return;
			}
			catch(Exception e) {
				e.printStackTrace();
				return;
			}
		}
		
		//Setear los atributos necesarios para la cuenta
		String cbuSeleccionado = request.getParameter("cbuSeleccionado");
		System.out.println(cbuSeleccionado);
		Cuenta cuenta = cuentaNegocio.obtenerCuentaPorCBU(cbuSeleccionado);
		System.out.println(cuenta);
		request.setAttribute("saldoCuentaSeleccionada", cuenta.getSaldo());
		request.setAttribute("cbuSeleccionado", cuenta.getCbu());
		request.setAttribute("numeroCuenta", cuenta.getNumero());
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/prestamosCliente.jsp");
		dispatcher.forward(request, response);
	
	}
	
	public static float calcularValorCuota(float monto, int cuotas) {

		    double tasaMensual = 50.0 / 12.0 / 100.0;

		    double denominador = 1 - Math.pow(1 + tasaMensual, -cuotas);

		    double cuota = monto * tasaMensual / denominador;
		    
		    cuota = Math.round(cuota * 100.0) / 100.0;
		    
		    return (float) cuota;
		//La tasa de amortizacion francesa calcula
		//monto multiplicado por la tasa mensual, todo eso dividido por 1 - ((1+ tasa mensual) elevado a -cuotas)
	}
	public void devolverYDespacharConError(String errorMessage,HttpServletResponse response, HttpServletRequest request, Cuenta cuentaOrigen) throws ServletException, IOException {
		request.setAttribute("ErrorMessage", errorMessage);

		request.setAttribute("saldoCuentaSeleccionada", cuentaOrigen.getSaldo());
		request.setAttribute("cbuSeleccionado", cuentaOrigen.getCbu());
		request.setAttribute("numeroCuenta", cuentaOrigen.getNumero());
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/transferenciasCliente.jsp");
		dispatcher.forward(request, response);
	}

}
