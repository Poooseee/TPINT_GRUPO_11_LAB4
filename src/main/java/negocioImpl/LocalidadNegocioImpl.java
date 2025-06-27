package negocioImpl;

import java.util.ArrayList;
import java.util.List;

import datosImpl.LocalidadDaoImpl;
import entidades.Localidad;
import negocio.LocalidadNegocio;

public class LocalidadNegocioImpl implements LocalidadNegocio{
	LocalidadDaoImpl locDao = new LocalidadDaoImpl();
	
	public List<Localidad> obtenerLocalidadesXProvXPais(int idPais, int idProvincia) {
		List<Localidad> listaLocalidades = new ArrayList<>();
		listaLocalidades = locDao.obtenerLocalidadesXProvXPais(idPais, idProvincia);
		for (Localidad l : listaLocalidades) {
			System.out.println(l.getNombre());
		}
		return listaLocalidades;
	}
}
