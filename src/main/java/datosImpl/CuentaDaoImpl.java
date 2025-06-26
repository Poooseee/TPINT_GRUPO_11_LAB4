package datosImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import datos.CuentaDao;
import entidades.Cuenta;

public class CuentaDaoImpl implements CuentaDao {
	private Conexion cn;
	@Override
	public boolean insert(Cuenta cuenta) {
	    boolean insert = false;
 String query="INSERT INTO CUENTAS(numeroCuenta_Ctas,DNI_Ctas,fechaCreacion_Ctas,tipoCta_Ctas,"
	    		+ "CBU_Ctas,saldo_Ctas) VALUES(?,?,?,?,?,?)";
		cn = new Conexion();
		cn.Open();
		 PreparedStatement ps;
		try {
			 ps = cn.prepare(query);
			ps.setInt(1, cuenta.getNumero());
			ps.setString(2, cuenta.getDni());
			ps.setString(3, cuenta.getFechaCreacion());
			ps.setInt(4,cuenta.getTipo().getIdTipo());
			ps.setString(5, cuenta.getCbu());
			ps.setFloat(6, cuenta.getSaldo());
			insert = ps.executeUpdate() > 0;
			
			ps.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			cn.close();
		}
		return insert;
	}
	@Override
	public int obtenerUltimoNumCuenta() {
		 int utlimoNum = 0;
	        try {
	            cn = new Conexion();
	            cn.Open();
	            String query = "SELECT MAX(numeroCuenta_Ctas) AS ultimoNum FROM CUENTAS";
	           
	          ResultSet rs = cn.query(query);
	          if(rs.next()) {
	        	  utlimoNum = rs.getInt(1);
	          }
	           
	            cn.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return utlimoNum;
		
	}

}
