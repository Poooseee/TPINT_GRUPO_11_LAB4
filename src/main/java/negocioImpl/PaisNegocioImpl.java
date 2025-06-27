package negocioImpl;

import java.util.ArrayList;

import datosImpl.PaisDaoImpl;
import entidades.Pais;
import negocio.PaisNegocio;

public class PaisNegocioImpl implements PaisNegocio{
	PaisDaoImpl dao = new PaisDaoImpl();
	@Override
	public ArrayList<Pais> obtenerPaises() {
		return dao.ObtenerPaises();
	}
	@Override
	public ArrayList<Pais> obtenerNacionalidades() {
		return dao.ObtenerNacionalidades();
	}

}
