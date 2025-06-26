package datosImpl;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import datos.TiposCuentaDao;
public class TipoCuentaDaoImpl implements TiposCuentaDao {
Conexion cn;
	@Override
	public String ObtenerNombreDelTipo(int idTipo) {
		String nombre = "";
	        try {
	            cn = new Conexion();
	            cn.Open();
	            String query = "SELECT descripcion_TCta FROM TIPOS_DE_CUENTAS WHERE idTipoCta_TCta = ?";
	            PreparedStatement ps = cn.prepare(query);
	            ps.setInt(1, idTipo);
	          ResultSet rs = ps.executeQuery();
	          if(rs.next()) {
	        	  nombre = rs.getString(1);
	          }
	            ps.close();
	            cn.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return nombre;
	}

}
