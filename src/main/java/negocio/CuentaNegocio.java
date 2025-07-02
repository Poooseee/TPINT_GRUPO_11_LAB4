package negocio;

import java.util.ArrayList;

import entidades.Cuenta;

public interface CuentaNegocio {
public int insert(Cuenta cuenta);
public int obtenerNuevoNumero();
public boolean update(Cuenta cuenta);
public ArrayList<Cuenta> obtenerListaCuentas(String dni);
public boolean eliminarCuenta(int numeroCuenta);
public boolean tieneMenosDe3Cuentas(String dni);
}
