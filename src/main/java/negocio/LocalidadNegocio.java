package negocio;

import java.util.List;

import entidades.Localidad;

public interface LocalidadNegocio {
	public List<Localidad> obtenerLocalidadesXProvXPais(String nombrePais, String nombreProv);
	public Localidad obtenerLocalidadPorId (int idLocalidad, int idProvincia, int idPais);
	public Localidad obtenerLocalidadPorNombre (String nombreLoc);
}
