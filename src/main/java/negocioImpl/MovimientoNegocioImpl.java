package negocioImpl;

import java.util.ArrayList;
import datosImpl.MovimientoDaoImpl;
import entidades.Movimiento;
import negocio.MovimientoNegocio;

public class MovimientoNegocioImpl implements MovimientoNegocio {
    private MovimientoDaoImpl movimientoDao = new MovimientoDaoImpl();

    @Override
    public ArrayList<Movimiento> obtenerMovimientosPorCliente(String dniCliente) {
        return movimientoDao.obtenerPorCliente(dniCliente);
    }

    @Override
    public ArrayList<Movimiento> filtrarMovimientos(String dniCliente, String fecha, String nroCuenta, String importe, String tipo) {
        return movimientoDao.filtrar(dniCliente, fecha, nroCuenta, importe, tipo);
    }
}