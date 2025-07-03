package negocioImpl;

import java.util.ArrayList;
import java.util.List;

import datosImpl.ProvinciaDaoImpl;
import entidades.Provincia;
import negocio.ProvinciaNegocio;

public class ProvinciaNegocioImpl implements ProvinciaNegocio{
	ProvinciaDaoImpl provDao = new ProvinciaDaoImpl();
	
	public List<Provincia> obtenerProvinciasPorPais(String nombrePais){
		List<Provincia> listaProvincias = new ArrayList<>();
		listaProvincias = provDao.obtenerProvinciasPorPais(nombrePais);
		for (Provincia p : listaProvincias) {
			System.out.println(p.getNombre());
		}
		return listaProvincias;
	}

	@Override
	public Provincia obtenerProvinciaPorNombre(String nombreProvincia) {
		Provincia p = provDao.obtenerProvinciaPorNombre(nombreProvincia);
		return p;
	}

	@Override
	public Provincia obtenerProvinciaPorId(int idProvincia, int idPais) {
		Provincia p = provDao.obtenerProvinciaPorId(idProvincia, idPais);
		return p;
	}
}
