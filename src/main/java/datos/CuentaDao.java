package datos;

import java.util.ArrayList;

import entidades.Cuenta;

public interface CuentaDao {
public boolean insert(Cuenta cuenta);
public int obtenerUltimoNumCuenta();
<<<<<<< Updated upstream
public boolean update(Cuenta cuenta);
=======
public ArrayList<Cuenta> obtenerCuentas();
>>>>>>> Stashed changes
}
