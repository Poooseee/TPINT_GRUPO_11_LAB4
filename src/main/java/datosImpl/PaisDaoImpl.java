package datosImpl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import datos.PaisDao;
import entidades.Pais;

public class PaisDaoImpl implements PaisDao{

	private Conexion cn;
	
	@Override
	public List<Pais> obtenerPaises() {
        List<Pais> lista = new ArrayList<>();
        try {
            cn = new Conexion();
            cn.Open();
            String query = "SELECT * FROM PAISES";
            ResultSet rs = cn.query(query);
            if (rs == null) {
                System.out.println("ResultSet es null");
            } else {
                System.out.println("ResultSet obtenido");
            }
            while (rs.next()) {
                Pais p = new Pais();
                p.setId(rs.getInt("idPais_Pa"));
                p.setNombre(rs.getString("nombre_Pa"));
                p.setNacionalidad(rs.getString("nacionalidad_Pa"));

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
	public Pais obtenerPaisxNombre(String nombrePais) {
		Pais p = null;
		
        try {
            cn = new Conexion();
            cn.Open();
            String query = "SELECT * FROM PAISES WHERE nombre_Pa = '" + nombrePais + "'";
            ResultSet rs = cn.query(query);
            if (rs == null) {
                System.out.println("ResultSet es null");
            } else {
                System.out.println("ResultSet obtenido");
            }
            while(rs.next()) {
            	p = new Pais();
                p.setId(rs.getInt("idPais_Pa"));
                p.setNombre(rs.getString("nombre_Pa"));
                p.setNacionalidad(rs.getString("nacionalidad_Pa"));
            }
            rs.close();
            cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		return p;
	}

	@Override
	public Pais obtenerPaisxNacionalidad(String nacionalidad) {
		Pais p = null;
		
        try {
            cn = new Conexion();
            cn.Open();
            String query = "SELECT * FROM PAISES WHERE nacionalidad_Pa = '" + nacionalidad + "'";
            ResultSet rs = cn.query(query);
            if (rs == null) {
                System.out.println("ResultSet es null");
            } else {
                System.out.println("ResultSet obtenido");
            }
            while(rs.next()) {
            	p = new Pais();
                p.setId(rs.getInt("idPais_Pa"));
                p.setNombre(rs.getString("nombre_Pa"));
                p.setNacionalidad(rs.getString("nacionalidad_Pa"));
            }
            rs.close();
            cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		return p;
	}

	@Override
	public Pais obtenerPaisxId(int id) {
		Pais p = null;
		
        try {
            cn = new Conexion();
            cn.Open();
            String query = "SELECT * FROM PAISES WHERE idPais_Pa = " + id + "";
            ResultSet rs = cn.query(query);
            if (rs == null) {
                System.out.println("ResultSet es null");
            } else {
                System.out.println("ResultSet obtenido");
            }
            while(rs.next()) {
            	p = new Pais();
                p.setId(rs.getInt("idPais_Pa"));
                p.setNombre(rs.getString("nombre_Pa"));
                p.setNacionalidad(rs.getString("nacionalidad_Pa"));
            }
            rs.close();
            cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		return p;
	}

}
