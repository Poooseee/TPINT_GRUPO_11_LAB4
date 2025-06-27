package datos;

import java.util.ArrayList;


import entidades.TipoCuenta;

public interface TiposCuentaDao {
public String ObtenerNombreDelTipo(int idTipo);
public ArrayList<TipoCuenta> obtenerTiposCuentas();
}
