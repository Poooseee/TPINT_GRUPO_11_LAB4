package datos;

import entidades.Cuenta;

public interface CuentaDao {
public boolean insert(Cuenta cuenta);
public int obtenerUltimoNumCuenta();
public boolean update(Cuenta cuenta);
}
