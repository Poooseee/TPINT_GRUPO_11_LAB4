package negocio;

import java.util.List;

import entidades.Pais;

public interface PaisNegocio {
	public List<Pais> obtenerPaises();
	public Pais obtenerPaisxNombre(String nombrePais);
	public Pais obtenerPaisxNacionalidad (String nacionalidad);
	public Pais obtenerPaisxId (int id);
}
