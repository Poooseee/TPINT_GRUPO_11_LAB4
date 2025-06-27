package datosImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import datos.CuentaDao;
import entidades.Cuenta;
import entidades.TipoCuenta;

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
			System.out.println("idTipo en insert dao "+cuenta.getTipo().getIdTipo());
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
	@Override
	public boolean update(Cuenta cuenta) {
		boolean update = false;
		System.out.println("cuenta a modificar en cuenta dao: "+cuenta.toString());
		String query = "UPDATE cuentas SET  "
				+ " fechaCreacion_Ctas = ?, "
				+ " tipoCta_Ctas = ?, "
				+ " CBU_Ctas = ?, "
				+ " saldo_Ctas = ? "
				+ "WHERE numeroCuenta_Ctas = ? AND DNI_Ctas = ?";
		cn = new Conexion();
		cn.Open();
		try {
			PreparedStatement pst = cn.prepare(query);
			pst.setString(1, cuenta.getFechaCreacion());
			pst.setInt(2, cuenta.getTipo().getIdTipo());
			pst.setString(3, cuenta.getCbu());
			pst.setFloat(4, cuenta.getSaldo());
			pst.setInt(5,cuenta.getNumero());
			pst.setString(6, cuenta.getDni());
			
			if(pst.executeUpdate() == 1) {
				update = true;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return update;
	}
	
	public ArrayList<Cuenta> obtenerCuentas() {
		ArrayList<Cuenta> listaCuentas = new ArrayList<Cuenta>();
		try {
			cn = new Conexion();
			cn.Open();
			String query = "SELECT numeroCuenta_Ctas as numero, DNI_Ctas as dni, fechaCreacion_Ctas as fecha, descripcion_TCta as tipoNombre, "
					+ "CBU_Ctas as cbu, saldo_Ctas as saldo, idTipoCta_TCta as tipoId"
					+ " FROM CUENTAS INNER JOIN tipos_de_cuentas ON cuentas.tipoCta_Ctas = tipos_de_cuentas.idTipoCta_TCta";
			
			ResultSet rs = cn.query(query);
			while(rs.next()) {
				Cuenta cuenta = new Cuenta();
				TipoCuenta tipoCuenta = new TipoCuenta();
				cuenta.setCbu(rs.getString("cbu"));
				cuenta.setDni(rs.getString("dni"));
				cuenta.setFechaCreacion(rs.getString("fecha"));
				cuenta.setNumero(rs.getInt("numero"));
				cuenta.setSaldo(rs.getFloat("saldo"));
				tipoCuenta.setIdTipo(rs.getInt("tipoId"));
				tipoCuenta.setNombre(rs.getString("tipoNombre"));
				cuenta.setTipo(tipoCuenta);
				listaCuentas.add(cuenta);
			}
			rs.close();
			cn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaCuentas;
	}

}
