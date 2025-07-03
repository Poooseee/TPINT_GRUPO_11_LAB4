package datosImpl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import datos.LocalidadDao;
import datos.PaisDao;
import datos.ProvinciaDao;
import entidades.Localidad;
import entidades.Pais;
import entidades.Provincia;

public class LocalidadDaoImpl implements LocalidadDao{
	private Conexion cn;
	
	public List<Localidad> obtenerLocalidadesXProvXPais(String nombrePais, String nombreProv) {
        List<Localidad> lista = new ArrayList<>();
        PaisDao paDao;
        ProvinciaDao provDao;
        Provincia prov;
        Pais pa;
        
        try {
            cn = new Conexion();
            cn.Open();
            
            paDao = new PaisDaoImpl();
            provDao = new ProvinciaDaoImpl();
            
            pa = paDao.obtenerPaisxNombre(nombrePais);
            prov = provDao.obtenerProvinciaPorNombre(nombreProv);
            
            System.out.println("PAIS: " + pa.getNombre() + " (ID: " + pa.getId() + ")");
            System.out.println("PROVINCIA: " + prov.getNombre() + " (ID: " + prov.getId() + ")");
            
            String query = "SELECT * FROM LOCALIDADES WHERE idPais_Loc = "+ pa.getId() + " AND idProvincia_Loc =" + prov.getId();
            System.out.println("QUERY: SELECT * FROM LOCALIDADES WHERE idPais_Loc = " + pa.getId() + " AND idProvincia_Loc = " + prov.getId());
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

	@Override
	public Localidad obtenerLocalidadPorId(int idLoc, int idProv, int idPais) {
		Localidad l = null;
		
        try {
            cn = new Conexion();
            cn.Open();
            String query = "SELECT * FROM LOCALIDADES WHERE idLocalidad_Loc = " + idLoc + " AND idProvincia_Loc = " + idProv + " AND idPais_Loc = " + idPais;
            ResultSet rs = cn.query(query);
            if (rs == null) {
                System.out.println("ResultSet es null");
            } else {
                System.out.println("ResultSet obtenido");
            }
            while(rs.next()) {
            	l = new Localidad();
            	l.setId(rs.getInt("idLocalidad_Loc"));
                l.setIdProvincia(rs.getInt("idProvincia_Loc"));
                l.setIdPais(rs.getInt("idPais_Loc"));
                l.setNombre(rs.getString("nombre_Loc"));
            }
            rs.close();
            cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		return l;
	}

	@Override
	public Localidad obtenerLocalidadPorNombre(String nombreLoc) {
		Localidad l = null;
		
        try {
            cn = new Conexion();
            cn.Open();
            String query = "SELECT * FROM LOCALIDADES WHERE nombre_Loc = '" + nombreLoc + "'";
            ResultSet rs = cn.query(query);
            if (rs == null) {
                System.out.println("ResultSet es null");
            } else {
                System.out.println("ResultSet obtenido");
            }
            while(rs.next()) {
            	l = new Localidad();
                l.setId(rs.getInt("idLocalidad_Loc"));
                l.setIdProvincia(rs.getInt("idProvincia_Loc"));
                l.setIdPais(rs.getInt("idPais_Loc"));
                l.setNombre(rs.getString("nombre_Loc"));
            }
            rs.close();
            cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		return l;
	}
}
