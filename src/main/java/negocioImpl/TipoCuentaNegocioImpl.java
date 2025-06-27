package negocioImpl;

import negocio.TipoCuentaNegocio;

import java.util.ArrayList;

import datos.TiposCuentaDao;
import datosImpl.TipoCuentaDaoImpl;
import entidades.TipoCuenta;

public class TipoCuentaNegocioImpl implements TipoCuentaNegocio {
TiposCuentaDao dao = new TipoCuentaDaoImpl();
	@Override
	public ArrayList<TipoCuenta> obtenerTiposCuentas() {
		return dao.obtenerTiposCuentas();
	}

	@Override
	public int obtenerId(String NombreTipo) {
	return	dao.ObtenerIdDelTipo(NombreTipo);
	}

}
