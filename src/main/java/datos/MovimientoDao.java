package datos;

import java.util.List;

import entidades.Movimiento;
import entidades.Prestamo;
import entidades.Transferencia;

public interface MovimientoDao {
	
	public 	int realizarTransferencia(Movimiento movimientoEntrada, Movimiento movimientoSalida , Transferencia transferencia);
	List<Object[]> filtrar(String dniCliente, String fecha, String nroCuenta, String importe, String tipo);
	List<Object[]> obtenerMovimientosConCuenta(String dniCliente);
	public int pagarCuota(Movimiento movimiento, int numeroCuenta, float importe, int idPrestamo, int numeroCuota);
	public int altaPrestamoMovimiento(Movimiento movimiento, Float importePedido);
}
