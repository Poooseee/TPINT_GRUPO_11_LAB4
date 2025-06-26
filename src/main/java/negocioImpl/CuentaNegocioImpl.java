package negocioImpl;

import entidades.Cuenta;
import negocio.CuentaNegocio;
import datos.CuentaDao;
import datosImpl.CuentaDaoImpl;
import negocio.ClienteNegocio;

public class CuentaNegocioImpl implements CuentaNegocio {
CuentaDao dao = new CuentaDaoImpl();
	@Override
	public boolean insert(Cuenta cuenta) {
		boolean insert = false;
		ClienteNegocio negCli = new ClienteNegocioImpl();
		if(negCli.existe(cuenta.getDni())) {
		insert = dao.insert(cuenta);
		}
		return insert;
	}
	@Override
	public int obtenerNuevoNumero() {
		return dao.obtenerUltimoNumCuenta()+1;
	}

}
