package datos;

import java.util.ArrayList;


import entidades.TipoCuenta;

public interface TiposCuentaDao {
public int ObtenerIdDelTipo(String idTipo);
public ArrayList<TipoCuenta> obtenerTiposCuentas();
}
