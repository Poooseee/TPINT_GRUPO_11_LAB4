package datos;

import java.util.List;

import entidades.Movimiento;
import entidades.Prestamo;
import entidades.Transferencia;

public interface MovimientoDao {
	
	public 	int realizarTransferencia(Movimiento movimientoEntrada, Movimiento movimientoSalida , Transferencia transferencia);
	public int insertarPrestamo(Prestamo prestamo);
	List<Object[]> filtrar(String dniCliente, String fecha, String nroCuenta, String importe, String tipo);
	List<Object[]> obtenerMovimientosConCuenta(String dniCliente);
}
