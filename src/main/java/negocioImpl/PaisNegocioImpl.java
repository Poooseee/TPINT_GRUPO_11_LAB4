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

}
