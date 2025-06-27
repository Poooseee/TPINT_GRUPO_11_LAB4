package datosImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import datos.ProvinciaDao;
import entidades.Provincia;

public class ProvinciaDaoImpl implements ProvinciaDao {
	Conexion cn;
	
	@Override
	public ArrayList<Provincia> obtenerProvincias(int idPais) {
		ArrayList<Provincia> provincias = new ArrayList<Provincia>();
		try {
            cn = new Conexion();
            cn.Open();
            String query = "SELECT idProvincia_Prov, nombre_Prov from PROVINCIAS WHERE idPais_Prov = ?";
            PreparedStatement ps = cn.prepare(query);
            ps.setInt(1, idPais);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) {
            	Provincia provincia = new Provincia();
            	provincia.setId(rs.getInt(1));
            	provincia.setNombre(rs.getString(2));
            	
            	provincias.add(provincia);
            }
           
            rs.close();
            cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return provincias;
	}

}
