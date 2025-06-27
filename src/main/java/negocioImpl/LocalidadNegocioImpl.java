package negocioImpl;

import java.util.ArrayList;

import datos.LocalidadDao;
import entidades.Localidad;
import negocio.LocalidadNegocio;

public class LocalidadNegocioImpl implements LocalidadNegocio{
	LocalidadDao dao;
	@Override
	public ArrayList<Localidad> obtenerLocalidades(int idProv, int idPais) {
		return dao.obtenerLocalidades(idProv, idPais);
	}

}
