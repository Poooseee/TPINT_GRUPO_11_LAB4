package datosImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import datos.LocalidadDao;
import entidades.Localidad;


public class LocalidadDaoImpl implements LocalidadDao {
	Conexion cn;

	@Override
	public ArrayList<Localidad> obtenerLocalidades(int idProv, int idPais) {
		ArrayList<Localidad> localidades = new ArrayList<Localidad>();
		try {
            cn = new Conexion();
            cn.Open();
            String query = "SELECT idLocalidad_Loc, nombre_Loc from LOCALIDADES where idProvincia_Loc = ? and idPais_Loc = ?";
            PreparedStatement ps = cn.prepare(query);
            ps.setInt(1, idProv);
            ps.setInt(2, idPais);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) {
            	Localidad localidad = new Localidad();
            	localidad.setId(rs.getInt(1));
            	localidad.setNombre(rs.getString(2));
            	
            	localidades.add(localidad);
            }
           
            rs.close();
            cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return localidades;
	}
	

}
