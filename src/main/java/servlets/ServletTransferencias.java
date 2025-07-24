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

import Excepciones.SaldoInsuficienteExcepcion;
import Excepciones.TransferenciaConErrorExcepcion;
import entidades.Cuenta;
import entidades.Movimiento;
import entidades.TipoMovimiento;
import entidades.Transferencia;
import negocioImpl.CuentaNegocioImpl;
import negocioImpl.MovimientoNegocioImpl;

/**
 * Servlet implementation class ServletTransferencias
 */
@WebServlet("/ServletTransferencias")
public class ServletTransferencias extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    private CuentaNegocioImpl cuentaNegocio = new CuentaNegocioImpl();
    private MovimientoNegocioImpl movimientoNegocio = new MovimientoNegocioImpl();

    public ServletTransferencias() {
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
    	
    	RequestDispatcher dispatcher = request.getRequestDispatcher("/transferenciasCliente.jsp");
    	dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//HACER CLICK EN TRANSFERIR
		if(request.getParameter("btnTransferir")!=null) {
			
			//Obtenemos las cuentas
			String numeroCuentaOrigen = request.getParameter("numeroCuenta").trim();
			String numeroCuentaDestino = request.getParameter("cuentaDestino").trim();
			Cuenta cuentaOrigen = cuentaNegocio.obtenerCuentaPorNumero(Integer.parseInt(numeroCuentaOrigen));
			Cuenta cuentaDestino = cuentaNegocio.obtenerCuentaPorNumero(Integer.parseInt(numeroCuentaDestino));
			
			
			//Primero, validar que la cuenta tenga el saldo suficiente
			try {
				
				float saldoDisponible = Float.parseFloat(request.getParameter("saldoCuentaSeleccionada")) ;
				float importeATransferir = Float.parseFloat(request.getParameter("importe"));
				
				if(importeATransferir > saldoDisponible) throw new SaldoInsuficienteExcepcion();
       
			} catch (SaldoInsuficienteExcepcion e) {
			    devolverYDespacharConError(e.getMessage(), response, request, cuentaOrigen);
			    return;
			} catch (Exception e) {
			    devolverYDespacharConError("Error al procesar la transferencia.", response, request, cuentaOrigen);
			    return;
			}

			//SI TIENE EL DINERO SUFICIENTE:
			
			//1.Obtenemos el importe a transferir
			float importeATransferir = Float.parseFloat(request.getParameter("importe"));
			
			//2. validamos que no transfiera a la misma cuenta
			if(numeroCuentaOrigen.equals(numeroCuentaDestino)) {
				devolverYDespacharConError("No puede realizar una transferencia a la misma cuenta",response,request,cuentaOrigen);
				return;
			}
			
			//3. validamos que exista la cuenta
			if(!cuentaNegocio.existeCuenta(Integer.parseInt(numeroCuentaDestino))) {
				devolverYDespacharConError("Cuenta de Destinto inexsistente",response,request,cuentaOrigen);
				return;
			}
			
			//4.Obtenemos la fecha
			Date fechaMovimiento = Date.valueOf(LocalDate.now());
			
			//5. Instaciamos el movimiento de ENTRADA con todos su datos
			String detalleMovimientoEntrada = "Transferencia recibida. Cuenta de Origen:" + cuentaOrigen.getNumero();
			
			Movimiento movimientoEntrada = new Movimiento();
			movimientoEntrada.setDniMovimiento(Integer.parseInt(cuentaDestino.getDni()));
			movimientoEntrada.setNumeroCuenta(cuentaDestino.getNumero());
			movimientoEntrada.setFecha(fechaMovimiento);
			movimientoEntrada.setDetalle(detalleMovimientoEntrada);
			movimientoEntrada.setImporte(importeATransferir);
			movimientoEntrada.setTipo( new TipoMovimiento(4,"Transferencia"));
			
			//6. Instanciamos el movimiento de SALIDA con todos sus datos
			String detalleMovimientoSalida = "Transferencia enviada. Cuenta de Destino:" + cuentaDestino.getNumero();
			
			Movimiento movimientoSalida = new Movimiento();
			movimientoSalida.setDniMovimiento(Integer.parseInt(cuentaOrigen.getDni()));
			movimientoSalida.setNumeroCuenta(cuentaOrigen.getNumero());
			movimientoSalida.setFecha(fechaMovimiento);
			movimientoSalida.setDetalle(detalleMovimientoSalida);
			movimientoSalida.setImporte(importeATransferir);
			movimientoSalida.setTipo( new TipoMovimiento(4,"Transferencia"));
			
			//7. Instanciar el objeto de TRANSFERENCIA
			Transferencia transferencia = new Transferencia();
			transferencia.setNumeroCuentaOrigen(cuentaOrigen.getNumero());
			transferencia.setNumeroCuentaDestino(cuentaDestino.getNumero());
			transferencia.setImporte(importeATransferir);
			
			//8. Enviar la transferencia
			try {
				Boolean transferido = movimientoNegocio.realizarTransferencia(movimientoEntrada, movimientoSalida, transferencia);
				
				//Deberian ser 5
				if(!transferido) {
					throw new TransferenciaConErrorExcepcion();	
				}
				
			}catch(TransferenciaConErrorExcepcion e) {
				devolverYDespacharConError(e.getMessage(),response,request,cuentaOrigen);
				return;
			}
			catch(Exception e) {
				devolverYDespacharConError("Error en la transferencia",response,request,cuentaOrigen);
				return;
			}
			
			
			//9. Despachar todos los atributos necesarios
			request.setAttribute("TransferenciaRealizada", "Transferencia Realizada de Forma Correcta");
			Cuenta cuentaActualizada = cuentaNegocio.obtenerCuentaPorNumero(cuentaOrigen.getNumero());

			request.setAttribute("saldoCuentaSeleccionada", cuentaActualizada.getSaldo());
			request.setAttribute("cbuSeleccionado", cuentaActualizada.getCbu());
			request.setAttribute("numeroCuenta", cuentaActualizada.getNumero());
			
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/transferenciasCliente.jsp");
			dispatcher.forward(request, response);
		}
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
