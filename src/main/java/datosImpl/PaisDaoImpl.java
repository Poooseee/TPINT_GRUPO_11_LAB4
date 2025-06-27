package datosImpl;

import java.sql.ResultSet;
import java.util.ArrayList;

import datos.PaisDao;
import entidades.Pais;

public class PaisDaoImpl implements PaisDao {
	Conexion cn;
	
	@Override
	public ArrayList<Pais> ObtenerNacionalidades() {
		ArrayList<Pais> paises = new ArrayList<Pais>();
		try {
            cn = new Conexion();
            cn.Open();
            String query = "SELECT idPais_Pa, nacionalidad_Pa from paises";
            ResultSet rs = cn.query(query);
            
            while(rs.next()) {
            	Pais nacionalidad = new Pais();
            	nacionalidad.setId(rs.getInt(1));
            	nacionalidad.setNacionalidad(rs.getString(2));
            	
            	paises.add(nacionalidad);
            }
           
            rs.close();
            cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return paises;
	}

	@Override
	public ArrayList<Pais> ObtenerPaises() {
		ArrayList<Pais> paises = new ArrayList<Pais>();
		try {
            cn = new Conexion();
            cn.Open();
            String query = "SELECT idPais_Pa, nombre_Pa from paises";
            ResultSet rs = cn.query(query);
            
            while(rs.next()) {
            	Pais pais = new Pais();
            	pais.setId(rs.getInt(1));
            	pais.setNombre(rs.getString(2));
            	
            	paises.add(pais);
            }
           
            rs.close();
            cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return paises;
	}

}
