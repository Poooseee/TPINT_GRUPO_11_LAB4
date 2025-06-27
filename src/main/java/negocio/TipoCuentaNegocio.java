package negocio;



import java.util.ArrayList;

import entidades.TipoCuenta;

public interface TipoCuentaNegocio {
public String obtenerNombre(int idTipo);
public ArrayList<TipoCuenta> obtenerTiposCuentas();

}
