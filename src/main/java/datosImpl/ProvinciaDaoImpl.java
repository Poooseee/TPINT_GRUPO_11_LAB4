package datosImpl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import datos.ProvinciaDao;
import entidades.Provincia;

public class ProvinciaDaoImpl implements ProvinciaDao{
	private Conexion cn;
	
	@Override
	public List<Provincia> obtenerProvinciasPorPais(int idPais) {
        List<Provincia> lista = new ArrayList<>();
        try {
            cn = new Conexion();
            cn.Open();
            String query = "SELECT * FROM PROVINCIAS WHERE idPais_Prov = "+ idPais;
            ResultSet rs = cn.query(query);
            if (rs == null) {
                System.out.println("ResultSet es null");
            } else {
                System.out.println("ResultSet obtenido");
            }
            while (rs.next()) {
                Provincia p = new Provincia();
                p.setId(rs.getInt("idProvincia_Prov"));
                p.setIdPais(rs.getInt("idPais_Prov"));
                p.setNombre(rs.getString("nombre_Prov"));

                lista.add(p);
            }
            rs.close();
            cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
	}
	
}
