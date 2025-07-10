package datos;

import java.util.ArrayList;

import entidades.Movimiento;

public interface MovimientoDao {

	ArrayList<Movimiento> filtrar(String dniCliente, String fecha, String nroCuenta, String importe, String tipo);

	ArrayList<Movimiento> obtenerPorCliente(String dniCliente);

}
