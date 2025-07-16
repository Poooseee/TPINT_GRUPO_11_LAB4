package datos;

import java.util.ArrayList;

import entidades.Cuenta;
import entidades.Movimiento;

public interface CuentaDao {
public int insert(Cuenta cuenta, Movimiento movimiento);
public int obtenerUltimoNumCuenta();
public boolean update(Cuenta cuenta);
public ArrayList<Cuenta> obtenerCuentas(String dni,Boolean cuentasInactivas);
public boolean eliminar(int NumeroCuenta);
public boolean tieneMenosDe3Cuentas(String dni);
Cuenta obtenerCuentaPorDni(String dni);
Cuenta obtenerCuentaPorCBU(String cbu);
Cuenta obtenerCuentaPorNumero(int numeroCuenta);
Boolean existeCuenta(int numeroCuenta);
}
