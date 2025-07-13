package negocio;

import java.util.List;

import entidades.Movimiento;
import entidades.Prestamo;
import entidades.Transferencia;

public interface MovimientoNegocio {
	public int realizarTransferencia(Movimiento movimientoEntrada, Movimiento movimientoSalida , Transferencia transferencia);
	public boolean insertarPrestamo(Prestamo prestamo,Movimiento movimiento);
	List<Object[]> obtenerMovimientosConCuenta(String dni);	
	List<Object[]> filtrarMovimientosConCuenta(String dniCliente, String fecha, 
	        String nroCuenta, String importe, String tipo);
}
