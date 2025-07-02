package negocioImpl;

import java.util.List;

import entidades.Prestamo;
import negocio.PrestamoNegocio;
import datos.PrestamoDao;
import datosImpl.PrestamoDaoImpl;

public class PrestamoNegocioImpl implements PrestamoNegocio {
PrestamoDao dao = new PrestamoDaoImpl();
	@Override
	public List<Prestamo> get(String estado,String dni) {
         return dao.get(estado,dni);		
	}
	@Override
	public boolean cambiarEstado(String estado, int id) {
		return dao.cambiarEstado(estado, id);
	}

}
