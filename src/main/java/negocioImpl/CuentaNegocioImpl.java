package negocioImpl;

import entidades.Cuenta;
import entidades.Movimiento;
import negocio.CuentaNegocio;
import java.util.ArrayList;

import datosImpl.CuentaDaoImpl;


public class CuentaNegocioImpl implements CuentaNegocio {
CuentaDaoImpl dao = new CuentaDaoImpl();


	@Override
	public int insert(Cuenta cuenta,Movimiento movimiento) {
		ClienteNegocioImpl negCli = new ClienteNegocioImpl();
		
		if(!negCli.existe(cuenta.getDni())) return -1;
		if(!tieneMenosDe3Cuentas(cuenta.getDni())) return -2;
		if(dao.insert(cuenta,movimiento)!=2) return -3;
		
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

	@Override
	public Cuenta obtenerCuentaPorDni(String dni) {
	    try {
	        return dao.obtenerCuentaPorDni(dni);
	    } catch (Exception e) {
	         
	        e.printStackTrace(); 
	        return null;
	    }
	}

	@Override
	public Cuenta obtenerCuentaPorCBU(String cbu) {
		return dao.obtenerCuentaPorCBU(cbu);
	}

	@Override
	public Cuenta obtenerCuentaPorNumero(int numeroCuenta) {
		return dao.obtenerCuentaPorNumero(numeroCuenta);
	}

	@Override
	public Boolean existeCuenta(int numeroCuenta) {
		
		return dao.existeCuenta(numeroCuenta);
		
	}
	
}
