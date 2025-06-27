package negocio;



import java.util.ArrayList;

import entidades.TipoCuenta;

public interface TipoCuentaNegocio {
public int obtenerId(String NombreTipo);
public ArrayList<TipoCuenta> obtenerTiposCuentas();

}
