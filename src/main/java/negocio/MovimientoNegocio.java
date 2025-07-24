package negocio;

import java.util.List;

import entidades.Movimiento;
import entidades.Prestamo;
import entidades.Transferencia;

public interface MovimientoNegocio {
	public Boolean realizarTransferencia(Movimiento movimientoEntrada, Movimiento movimientoSalida , Transferencia transferencia);
	List<Object[]> obtenerMovimientosConCuenta(String dni);	
	List<Object[]> filtrarMovimientosConCuenta(String dniCliente, String fecha, 
	        String nroCuenta, String importe, String tipo);
	public Boolean pagarCuota(Movimiento movimiento, int numeroCuenta, float importe, int idPrestamo, int numeroCuota);

}
