package negocioImpl;

import java.util.List;

import entidades.Cuota;
import entidades.Prestamo;
import negocio.PrestamoNegocio;
import datos.PrestamoDao;
import datosImpl.PrestamoDaoImpl;

public class PrestamoNegocioImpl implements PrestamoNegocio {
PrestamoDao dao = new PrestamoDaoImpl();
	@Override
	public List<Prestamo> get(String estado,String dni, String numeroCuenta) {
         return dao.get(estado,dni,numeroCuenta);		
	}
	@Override
	public boolean cambiarEstado(String estado, int id) {
		return dao.cambiarEstado(estado, id);
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
