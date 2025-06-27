package datosImpl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import datos.LocalidadDao;
import entidades.Localidad;

public class LocalidadDaoImpl implements LocalidadDao{
	private Conexion cn;
	
	public List<Localidad> obtenerLocalidadesXProvXPais(int idPais, int idProvincia) {
        List<Localidad> lista = new ArrayList<>();
        try {
            cn = new Conexion();
            cn.Open();
            String query = "SELECT * FROM LOCALIDADES WHERE idPais_Loc = "+ idPais + "AND idProvincia_Loc =" + idProvincia;
            ResultSet rs = cn.query(query);
            if (rs == null) {
                System.out.println("ResultSet es null");
            } else {
                System.out.println("ResultSet obtenido");
            }
            while (rs.next()) {
            	Localidad l = new Localidad();
                l.setId(rs.getInt("idLocalidad_Loc"));
                l.setIdPais(rs.getInt("idPais_Loc"));
                l.setIdProvincia(rs.getInt("idProvincia_Loc"));
                l.setNombre(rs.getString("nombre_Loc"));

                lista.add(l);
            }
            rs.close();
            cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
	}
}
