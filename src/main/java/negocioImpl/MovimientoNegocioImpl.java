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
	public Boolean realizarTransferencia(Movimiento movimientoEntrada, Movimiento movimientoSalida,
			Transferencia transferencia) {
		
		int filas = dao.realizarTransferencia(movimientoEntrada, movimientoSalida, transferencia);
		if(filas == 5) {
			return true;
		}
		return false;
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
	public Boolean pagarCuota(Movimiento movimiento, int numeroCuenta, float importe, int idPrestamo, int numeroCuota) {
		
		int filas = dao.pagarCuota(movimiento, numeroCuenta, importe, idPrestamo, numeroCuota);
		
		return filas == 3;
	}



}