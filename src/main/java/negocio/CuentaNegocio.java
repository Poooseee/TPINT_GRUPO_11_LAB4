package negocio;

import java.util.ArrayList;

import entidades.Cuenta;

public interface CuentaNegocio {
public boolean insert(Cuenta cuenta);
public int obtenerNuevoNumero();
<<<<<<< Updated upstream
public boolean update(Cuenta cuenta);
=======
public ArrayList<Cuenta> obtenerListaCuentas();
>>>>>>> Stashed changes
}
