package negocioImpl;

import java.util.ArrayList;
import java.util.List;

import datosImpl.LocalidadDaoImpl;
import entidades.Localidad;
import negocio.LocalidadNegocio;

public class LocalidadNegocioImpl implements LocalidadNegocio{
	LocalidadDaoImpl locDao = new LocalidadDaoImpl();
	
	public List<Localidad> obtenerLocalidadesXProvXPais(String nombrePais, String nombreProv) {
		List<Localidad> listaLocalidades = new ArrayList<>();
		listaLocalidades = locDao.obtenerLocalidadesXProvXPais(nombrePais, nombreProv);
		for (Localidad l : listaLocalidades) {
			System.out.println(l.getNombre());
		}
		return listaLocalidades;
	}

	@Override
	public Localidad obtenerLocalidadPorId(int idLocalidad, int idProvincia, int idPais) {
		Localidad l = locDao.obtenerLocalidadPorId(idLocalidad, idProvincia, idPais);
		return l;
	}

	@Override
	public Localidad obtenerLocalidadPorNombre(String nombreLoc) {
		Localidad l = locDao.obtenerLocalidadPorNombre(nombreLoc);
		return l;
	}
}
