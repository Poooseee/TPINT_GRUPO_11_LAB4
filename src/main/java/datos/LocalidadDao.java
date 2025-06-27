package datos;

import java.util.ArrayList;

import entidades.Localidad;

public interface LocalidadDao {
	
	public ArrayList<Localidad>obtenerLocalidades(int idProv, int idPais);
}
