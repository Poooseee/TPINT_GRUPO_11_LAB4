package datos;

import java.util.ArrayList;

import entidades.Pais;

public interface PaisDao {
	
	public ArrayList<Pais> ObtenerPaises();
	public ArrayList<Pais> ObtenerNacionalidades();
}
