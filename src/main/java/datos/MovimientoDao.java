package datos;

import java.util.ArrayList;
import java.util.List;

import entidades.Movimiento;
import entidades.Transferencia;

public interface MovimientoDao {
	
	public 	int realizarTransferencia(Movimiento movimientoEntrada, Movimiento movimientoSalida , Transferencia transferencia);
	ArrayList<Movimiento> filtrar(String dniCliente, String fecha, String nroCuenta, String importe, String tipo);
	//ArrayList<Movimiento> obtenerPorCliente(String dniCliente);
	List<Object[]> obtenerMovimientosConCuenta(String dniCliente);
}
