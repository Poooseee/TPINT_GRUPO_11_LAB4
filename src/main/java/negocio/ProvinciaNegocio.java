package negocio;

import java.util.ArrayList;

import entidades.Provincia;

public interface ProvinciaNegocio {
	public ArrayList <Provincia> obtenerProvincias(int idPais);
}
