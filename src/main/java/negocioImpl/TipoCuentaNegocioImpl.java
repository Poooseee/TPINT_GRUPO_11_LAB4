package negocioImpl;

import negocio.TipoCuentaNegocio;
import datos.TiposCuentaDao;
import datosImpl.TipoCuentaDaoImpl;
public class TipoCuentaNegocioImpl implements TipoCuentaNegocio {
TiposCuentaDao dao = new TipoCuentaDaoImpl();
	@Override
	public String obtenerNombre(int idTipo) {
		
	return dao.ObtenerNombreDelTipo(idTipo);
	}

}
