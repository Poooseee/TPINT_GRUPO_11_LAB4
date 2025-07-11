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
		
		//HACER CLICK EN TRANSFERIR
		if(request.getParameter("btnTransferir")!=null) {
			System.out.println("SE PULSO TRANSFERIR");
			//Primero, validar que la cuenta tenga el saldo suficiente,
			//Para eso validamos que el importe de la cuenta sea mayor o igual al importe del input
			try {
				
				float saldoDisponible = Float.parseFloat(request.getParameter("saldoCuentaSeleccionada")) ;
				float importeATransferir = Float.parseFloat(request.getParameter("importe"));
				
				if(importeATransferir > saldoDisponible) {
					throw new SaldoInsuficienteExcepcion();
				};
				System.out.println("SALDO DISPONIBLE "+ saldoDisponible);
				System.out.println("SALDO A ENVIAR "+ importeATransferir);
				
			}catch(Exception e) {
				
				request.setAttribute("errorSaldo", e.getMessage());
				
				String numeroCuentaOrigen = request.getParameter("numeroCuenta").trim();
				Cuenta cuentaOrigen = cuentaNegocio.obtenerCuentaPorNumero(Integer.parseInt(numeroCuentaOrigen));
				request.setAttribute("saldoCuentaSeleccionada", cuentaOrigen.getSaldo());
				request.setAttribute("cbuSeleccionado", cuentaOrigen.getCbu());
				request.setAttribute("numeroCuenta", cuentaOrigen.getNumero());
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/transferenciasCliente.jsp");
				dispatcher.forward(request, response);
				
				System.out.println("ERROR DE SALDO");
				return;
			}
			
			//Ahora si, SI TIENE EL DINERO SUFICIENTE:
			
			//1.Obtenemos el importe
			float importeATransferir = Float.parseFloat(request.getParameter("importe"));
			String importeString = String.valueOf(importeATransferir);
			
			//2.Obtenemos las cuentas
			String numeroCuentaOrigen = request.getParameter("numeroCuenta").trim();
			String numeroCuentaDestino = request.getParameter("nCta").trim();
			
			//validamos que no transfiera a la misma cuenta
			if(numeroCuentaOrigen.equals(numeroCuentaDestino)) {
				
				request.setAttribute("mismaCuenta","No puede realizar transferencias a la misma cuenta");
				
				Cuenta cuentaOrigen = cuentaNegocio.obtenerCuentaPorNumero(Integer.parseInt(numeroCuentaOrigen));
				
				request.setAttribute("saldoCuentaSeleccionada", cuentaOrigen.getSaldo());
				request.setAttribute("cbuSeleccionado", cuentaOrigen.getCbu());
				request.setAttribute("numeroCuenta", cuentaOrigen.getNumero());
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/transferenciasCliente.jsp");
				dispatcher.forward(request, response);
				return;
			}
			
			
			Cuenta cuentaOrigen = cuentaNegocio.obtenerCuentaPorNumero(Integer.parseInt(numeroCuentaOrigen));
			Cuenta cuentaDestino = cuentaNegocio.obtenerCuentaPorNumero(Integer.parseInt(numeroCuentaDestino));
			
			//3.Obtenemos la fecha
			Date fechaMovimiento = Date.valueOf(LocalDate.now());
			
			//4. Instaciamos el movimiento de ENTRADA con todos su datos
			String detalleMovimientoEntrada = "Transferencia recibida. Cuenta de Origen:" + cuentaOrigen.getNumero()+". Importe: $"+importeString;
			
			Movimiento movimientoEntrada = new Movimiento();
			movimientoEntrada.setDniMovimiento(Integer.parseInt(cuentaDestino.getDni()));
			movimientoEntrada.setNumeroCuenta(cuentaDestino.getNumero());
			movimientoEntrada.setFecha(fechaMovimiento);
			movimientoEntrada.setDetalle(detalleMovimientoEntrada);
			movimientoEntrada.setImporte(importeATransferir);
			movimientoEntrada.setTipo( new TipoMovimiento(4,"Transferencia"));
			
			//5. Instanciamos el movimiento de SALIDA con todos sus datos
			
			String detalleMovimientoSalida = "Transferencia enviada. Cuenta de Destino:" + cuentaDestino.getNumero()+". Importe: $"+importeString;
			
			Movimiento movimientoSalida = new Movimiento();
			movimientoSalida.setDniMovimiento(Integer.parseInt(cuentaOrigen.getDni()));
			movimientoSalida.setNumeroCuenta(cuentaOrigen.getNumero());
			movimientoSalida.setFecha(fechaMovimiento);
			movimientoSalida.setDetalle(detalleMovimientoSalida);
			movimientoSalida.setImporte(importeATransferir);
			movimientoSalida.setTipo( new TipoMovimiento(4,"Transferencia"));
			
			//6. Instanciar el objeto de TRANSFERENCIA
			Transferencia transferencia = new Transferencia();
			transferencia.setNumeroCuentaOrigen(cuentaOrigen.getNumero());
			transferencia.setNumeroCuentaDestino(cuentaDestino.getNumero());
			transferencia.setImporte(importeATransferir);
			
			//7. Enviar la transferencia
			try {
				int filas = movimientoNegocio.realizarTransferencia(movimientoEntrada, movimientoSalida, transferencia);
				
				//Si todo sale bien, se hicieron 3 inserciones y 2 modificaciones, por ende 5 filas afectadas
				if(filas != 5) {
					throw new TransferenciaConErrorExcepcion();	
				}
				
			}catch(Exception e) {
				request.setAttribute("errorTransferencia", e.getMessage());

				request.setAttribute("saldoCuentaSeleccionada", cuentaOrigen.getSaldo());
				request.setAttribute("cbuSeleccionado", cuentaOrigen.getCbu());
				request.setAttribute("numeroCuenta", cuentaOrigen.getNumero());
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/transferenciasCliente.jsp");
				dispatcher.forward(request, response);
				return;
			}
			
			request.setAttribute("TransferenciaRealizada", "Transferencia Realizada de Forma Correcta");
			Cuenta cuentaActualizada = cuentaNegocio.obtenerCuentaPorNumero(cuentaOrigen.getNumero());

			request.setAttribute("saldoCuentaSeleccionada", cuentaActualizada.getSaldo());
			request.setAttribute("cbuSeleccionado", cuentaActualizada.getCbu());
			request.setAttribute("numeroCuenta", cuentaActualizada.getNumero());
			
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/transferenciasCliente.jsp");
			dispatcher.forward(request, response);
		}
	}

}
