package negocio;

import java.util.List;

import entidades.Provincia;

public interface ProvinciaNegocio {
	public List<Provincia> obtenerProvinciasPorPais(String nombrePais);
	public Provincia obtenerProvinciaPorNombre (String nombreProvincia);
	public Provincia obtenerProvinciaPorId (int idProvincia, int idPais);
}
