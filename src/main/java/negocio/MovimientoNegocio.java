package negocio;

import java.util.ArrayList;
import java.util.List;

import entidades.Movimiento;
import entidades.Transferencia;

public interface MovimientoNegocio {
	public int realizarTransferencia(Movimiento movimientoEntrada, Movimiento movimientoSalida , Transferencia transferencia);
	//ArrayList<Movimiento> obtenerMovimientosPorCliente(String dniCliente);
	List<Object[]> obtenerMovimientosConCuenta(String dni);
	ArrayList<Movimiento> filtrarMovimientos(String dniCliente, String fecha, String nroCuenta, String importe,
			String tipo);	
}
