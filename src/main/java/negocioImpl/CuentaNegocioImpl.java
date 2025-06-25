package negocioImpl;

import entidades.Cuenta;
import negocio.CuentaNegocio;
import datos.CuentaDao;
import datosImpl.CuentaDaoImpl;

public class CuentaNegocioImpl implements CuentaNegocio {
CuentaDao dao = new CuentaDaoImpl();
	@Override
	public boolean insert(Cuenta cuenta) {
		// TODO Auto-generated method stub
		return false;
	}

}
