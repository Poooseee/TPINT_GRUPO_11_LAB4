package negocioImpl;

import java.util.ArrayList;

import datos.ProvinciaDao;
import entidades.Provincia;
import negocio.ProvinciaNegocio;

public class ProvinciaNegocioImpl implements ProvinciaNegocio {

	ProvinciaDao dao;
	@Override
	public ArrayList<Provincia> obtenerProvincias(int idPais) {
		return dao.obtenerProvincias(idPais);
	}

}
