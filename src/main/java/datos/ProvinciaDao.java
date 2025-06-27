package datos;

import java.util.List;

import entidades.Provincia;

public interface ProvinciaDao {
	public List<Provincia> obtenerProvinciasPorPais(int idPais);
}
