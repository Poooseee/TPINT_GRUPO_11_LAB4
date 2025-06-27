package datosImpl;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


import datos.TiposCuentaDao;
import entidades.TipoCuenta;
public class TipoCuentaDaoImpl implements TiposCuentaDao {
Conexion cn;
	@Override
	public int ObtenerIdDelTipo(String NombreTipo) {
		int id=0;
	        try {
	            cn = new Conexion();
	            cn.Open();
	            String query = "SELECT descripcion_TCta FROM TIPOS_DE_CUENTAS WHERE idTipoCta_TCta = ?";
	            PreparedStatement ps = cn.prepare(query);
	            ps.setInt(1, id);
	          ResultSet rs = ps.executeQuery();
	          if(rs.next()) {
	        	  id = rs.getInt(1);
	          }
	            ps.close();
	            cn.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return id;
	}
	
	

	@Override
	public ArrayList<TipoCuenta> obtenerTiposCuentas() {
		ArrayList<TipoCuenta> listaCuentas = new ArrayList<TipoCuenta>();
		try {
            cn = new Conexion();
            cn.Open();
            String query = "SELECT idTipoCta_TCta as id, descripcion_TCta as descripcion FROM tipos_de_cuentas";
            ResultSet rs = cn.query(query);
            
            while(rs.next()) {
            	TipoCuenta tipoCuenta = new TipoCuenta();
            	tipoCuenta.setIdTipo(rs.getInt(1));
            	tipoCuenta.setNombre(rs.getString(2));
            	
            	listaCuentas.add(tipoCuenta);	
            }
           
            rs.close();
            cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaCuentas;
	}

}
