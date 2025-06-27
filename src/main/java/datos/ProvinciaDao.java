package datos;

import java.util.ArrayList;

import entidades.Provincia;

public interface ProvinciaDao {
	public ArrayList<Provincia> obtenerProvincias(int idPais);
}
