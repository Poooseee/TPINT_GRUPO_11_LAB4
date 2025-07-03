package datos;

import java.util.List;

import entidades.Provincia;

public interface ProvinciaDao {
	public List<Provincia> obtenerProvinciasPorPais(String nombrePais);
	public Provincia obtenerProvinciaPorNombre (String nombreProvincia);
	public Provincia obtenerProvinciaPorId (int idProv, int idPais);
}
