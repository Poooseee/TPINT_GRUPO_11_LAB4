package negocioImpl;

import java.util.ArrayList;
import java.util.List;

import datosImpl.ProvinciaDaoImpl;
import entidades.Provincia;
import negocio.ProvinciaNegocio;

public class ProvinciaNegocioImpl implements ProvinciaNegocio{
	ProvinciaDaoImpl provDao = new ProvinciaDaoImpl();
	
	public List<Provincia> obtenerProvinciasPorPais(int idPais){
		List<Provincia> listaProvincias = new ArrayList<>();
		listaProvincias = provDao.obtenerProvinciasPorPais(idPais);
		for (Provincia p : listaProvincias) {
			System.out.println(p.getNombre());
		}
		return listaProvincias;
	}
}
