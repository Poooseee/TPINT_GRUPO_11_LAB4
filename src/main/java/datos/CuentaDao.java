package datos;

import java.util.ArrayList;

import entidades.Cuenta;

public interface CuentaDao {
public boolean insert(Cuenta cuenta);
public int obtenerUltimoNumCuenta();
public boolean update(Cuenta cuenta);
public ArrayList<Cuenta> obtenerCuentas(String dni);
public boolean eliminar(int NumeroCuenta);
public boolean tieneMenosDe3Cuentas(String dni);
}
