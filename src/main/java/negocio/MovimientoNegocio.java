package negocio;

import java.util.ArrayList;

import entidades.Movimiento;

public interface MovimientoNegocio {

	ArrayList<Movimiento> obtenerMovimientosPorCliente(String dniCliente);

	ArrayList<Movimiento> filtrarMovimientos(String dniCliente, String fecha, String nroCuenta, String importe,
			String tipo);

}
