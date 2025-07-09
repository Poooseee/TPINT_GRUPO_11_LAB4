package negocio;

import java.util.ArrayList;

import entidades.Cuenta;

public interface CuentaNegocio {
public int insert(Cuenta cuenta);
public int obtenerNuevoNumero();
public int update(Cuenta cuenta);
public ArrayList<Cuenta> obtenerListaCuentas(String dni,Boolean cuentasInactivas);
public boolean eliminarCuenta(int numeroCuenta);
public boolean tieneMenosDe3Cuentas(String dni);
public Cuenta obtenerCuentaPorDni(String dni);
public Cuenta obtenerCuentaPorCBU(String cbu);
public Cuenta obtenerCuentaPorNumero(int numeroCuenta);
}
