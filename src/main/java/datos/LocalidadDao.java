package datos;

import java.util.List;

import entidades.Localidad;
import entidades.Provincia;

public interface LocalidadDao {
	public List<Localidad> obtenerLocalidadesXProvXPais(String nombrePais, String nombreProv);
	public Localidad obtenerLocalidadPorId (int idLoc, int idProv, int idPais);
	public Localidad obtenerLocalidadPorNombre (String nombreLoc);
}
