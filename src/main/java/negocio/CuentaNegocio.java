package negocio;

import java.util.ArrayList;

import entidades.Cuenta;
import entidades.Movimiento;

public interface CuentaNegocio {
public int insert(Cuenta cuenta, Movimiento movimiento);
public int obtenerNuevoNumero();
public int update(Cuenta cuenta);
public ArrayList<Cuenta> obtenerListaCuentas(String dni,Boolean cuentasInactivas);
public boolean eliminarCuenta(int numeroCuenta);
public boolean tieneMenosDe3Cuentas(String dni);
public Cuenta obtenerCuentaPorDni(String dni);
public Cuenta obtenerCuentaPorCBU(String cbu);
public Cuenta obtenerCuentaPorNumero(int numeroCuenta);
public Boolean existeCuenta(int numeroCuenta);
}
