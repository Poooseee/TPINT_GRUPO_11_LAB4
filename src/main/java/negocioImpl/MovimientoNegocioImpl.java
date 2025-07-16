package negocioImpl;

import java.util.List;

import datosImpl.MovimientoDaoImpl;
import entidades.Movimiento;
import entidades.Prestamo;
import entidades.Transferencia;
import negocio.MovimientoNegocio;

public class MovimientoNegocioImpl implements MovimientoNegocio {
	MovimientoDaoImpl dao = new MovimientoDaoImpl();
	
	@Override
	public int realizarTransferencia(Movimiento movimientoEntrada, Movimiento movimientoSalida,
			Transferencia transferencia) {
		return dao.realizarTransferencia(movimientoEntrada, movimientoSalida, transferencia);
	}

    @Override
    public List<Object[]> obtenerMovimientosConCuenta(String dniCliente) {
        return dao.obtenerMovimientosConCuenta(dniCliente);
    }
    
    @Override
    public List<Object[]> filtrarMovimientosConCuenta(String dniCliente, String fecha, 
        String nroCuenta, String importe, String tipo) {
        return dao.filtrar(dniCliente, fecha, nroCuenta, importe, tipo);
    }

	@Override
	public boolean insertarPrestamo(Prestamo prestamo) {

		int filas = dao.insertarPrestamo(prestamo);
		return filas == 1;
	}

}