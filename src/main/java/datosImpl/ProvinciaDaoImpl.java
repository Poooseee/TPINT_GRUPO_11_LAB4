package datosImpl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import datos.PaisDao;
import datos.ProvinciaDao;
import entidades.Pais;
import entidades.Provincia;

public class ProvinciaDaoImpl implements ProvinciaDao{
	private Conexion cn;
	private PaisDao pDao;
	private Pais p;
	
	@Override
	public List<Provincia> obtenerProvinciasPorPais(String nombrePais) {
        List<Provincia> lista = new ArrayList<>();
        pDao = new PaisDaoImpl();
        p = pDao.obtenerPaisxNombre(nombrePais);
        try {
            cn = new Conexion();
            cn.Open();
            String query = "SELECT * FROM PROVINCIAS WHERE idPais_Prov = "+ p.getId();
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

	@Override
	public Provincia obtenerProvinciaPorNombre(String nombreProvincia) {
		Provincia p = null;
		
        try {
            cn = new Conexion();
            cn.Open();
            String query = "SELECT * FROM PROVINCIAS WHERE nombre_Prov = '" + nombreProvincia + "'";
            ResultSet rs = cn.query(query);
            if (rs == null) {
                System.out.println("ResultSet es null");
            } else {
                System.out.println("ResultSet obtenido");
            }
            while(rs.next()) {
            	p = new Provincia();
                p.setId(rs.getInt("idProvincia_Prov"));
                p.setNombre(rs.getString("nombre_Prov"));
                p.setIdPais(rs.getInt("idPais_Prov"));
            }
            rs.close();
            cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		return p;
	}

	@Override
	public Provincia obtenerProvinciaPorId(int idProvincia, int idPais) {
		Provincia p = null;
		
        try {
            cn = new Conexion();
            cn.Open();
            String query = "SELECT * FROM PROVINCIAS WHERE idProvincia_Prov = " + idProvincia + " AND idPais_Prov = " + idPais;
            ResultSet rs = cn.query(query);
            if (rs == null) {
                System.out.println("ResultSet es null");
            } else {
                System.out.println("ResultSet obtenido");
            }
            while(rs.next()) {
            	p = new Provincia();
                p.setId(rs.getInt("idProvincia_Prov"));
                p.setNombre(rs.getString("nombre_Prov"));
                p.setIdPais(rs.getInt("idPais_Prov"));
            }
            rs.close();
            cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		return p;
	}
	
}
