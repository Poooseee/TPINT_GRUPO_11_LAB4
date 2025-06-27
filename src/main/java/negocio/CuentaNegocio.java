package negocio;

import entidades.Cuenta;

public interface CuentaNegocio {
public boolean insert(Cuenta cuenta);
public int obtenerNuevoNumero();
public boolean update(Cuenta cuenta);
}
