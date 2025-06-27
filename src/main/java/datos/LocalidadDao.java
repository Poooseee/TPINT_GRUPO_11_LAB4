package datos;

import java.util.List;

import entidades.Localidad;

public interface LocalidadDao {
	public List<Localidad> obtenerLocalidadesXProvXPais(int idPais, int idProvincia);
}
