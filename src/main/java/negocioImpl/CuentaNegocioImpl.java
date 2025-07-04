package negocioImpl;

import entidades.Cuenta;
import negocio.CuentaNegocio;

import java.util.ArrayList;

import datos.CuentaDao;
import datosImpl.CuentaDaoImpl;
import negocio.ClienteNegocio;

public class CuentaNegocioImpl implements CuentaNegocio {
CuentaDaoImpl dao = new CuentaDaoImpl();


	@Override
	public int insert(Cuenta cuenta) {
		ClienteNegocioImpl negCli = new ClienteNegocioImpl();
		
		if(!negCli.existe(cuenta.getDni())) return -1;
		if(!tieneMenosDe3Cuentas(cuenta.getDni())) return -2;
		if(! dao.insert(cuenta)) return -3;
		
		//pasó todas las validaciones
		return 1;

	}
	
	@Override
	public int obtenerNuevoNumero() {
		return dao.obtenerUltimoNumCuenta()+1;
	}
	
	@Override
	public int update(Cuenta cuenta) {
		
		if(cuenta.getBaja()== false) {
			if(!tieneMenosDe3Cuentas(cuenta.getDni())) return -1;
		}
		if(! dao.update(cuenta)) return -2;
		
		return 1;
		
	}
	
	public ArrayList<Cuenta> obtenerListaCuentas(String dni,Boolean cuentasInactivas) {
		return dao.obtenerCuentas(dni,cuentasInactivas);
	}
	@Override
	public boolean eliminarCuenta(int numeroCuenta) {
		return dao.eliminar(numeroCuenta);
	}
	@Override
	public boolean tieneMenosDe3Cuentas(String dni) {
		return dao.tieneMenosDe3Cuentas(dni);
	}

}
