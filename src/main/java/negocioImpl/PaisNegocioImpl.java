package negocioImpl;

import java.util.List;

import entidades.Pais;
import negocio.PaisNegocio;
import datos.PaisDao;
import datosImpl.PaisDaoImpl;

public class PaisNegocioImpl implements PaisNegocio{

	private PaisDao paisDao = new PaisDaoImpl();
	
	@Override
	public List<Pais> obtenerPaises() {
		return paisDao.obtenerPaises();
	}

	@Override
	public Pais obtenerPaisxNombre(String nombrePais) {
		Pais p = paisDao.obtenerPaisxNombre(nombrePais);
		return p;
	}

	@Override
	public Pais obtenerPaisxNacionalidad(String nacionalidad) {
		Pais p = paisDao.obtenerPaisxNacionalidad(nacionalidad);
		return p;
	}

	@Override
	public Pais obtenerPaisxId(int id) {
		Pais p = paisDao.obtenerPaisxId(id);
		return p;
	}

}
