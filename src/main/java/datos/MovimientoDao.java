package datos;

import java.util.List;

import entidades.Movimiento;
import entidades.Transferencia;

public interface MovimientoDao {
	
	public 	int realizarTransferencia(Movimiento movimientoEntrada, Movimiento movimientoSalida , Transferencia transferencia);
	List<Object[]> filtrar(String dniCliente, String fecha, String nroCuenta, String importe, String tipo);
	List<Object[]> obtenerMovimientosConCuenta(String dniCliente);
}
