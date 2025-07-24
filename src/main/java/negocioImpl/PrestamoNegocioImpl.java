package negocioImpl;

import java.util.List;

import entidades.Cuota;
import entidades.Movimiento;
import entidades.Prestamo;
import negocio.PrestamoNegocio;
import datos.PrestamoDao;
import datosImpl.MovimientoDaoImpl;
import datosImpl.PrestamoDaoImpl;

public class PrestamoNegocioImpl implements PrestamoNegocio {
PrestamoDaoImpl dao = new PrestamoDaoImpl();
MovimientoDaoImpl daoMov = new MovimientoDaoImpl();
	@Override
	public List<Prestamo> get(String estado,String dni, String numeroCuenta) {
         return dao.get(estado,dni,numeroCuenta);		
	}
	@Override
	public boolean cambiarEstado(String estado, int id, Movimiento movimiento,boolean aceptado, Float importePedido) {
		
		Boolean insertado = true;
		
		insertado = dao.cambiarEstado(estado, id);
		
		if(aceptado) {
			insertado = daoMov.altaPrestamoMovimiento(movimiento,importePedido) == 2;			
		}
		
		return insertado;
	}
	@Override
	public Prestamo obtenerPrestamo(int id) {
		return dao.obtenerPrestamo(id);
	}
	@Override
	public List<Cuota> obtenerCuotasPorPrestamo(int idPrestamo) {
		return dao.obtenerCuotasPorPrestamo(idPrestamo);
	}
	
	@Override
	public boolean insertarPrestamo(Prestamo prestamo) {

		int filas = dao.insertarPrestamo(prestamo);
		return filas == 1;
	}
}
