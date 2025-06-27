package negocio;

import java.util.ArrayList;

import entidades.Localidad;

public interface LocalidadNegocio {
	public ArrayList <Localidad> obtenerLocalidades(int idProv, int idPais);
	
}
