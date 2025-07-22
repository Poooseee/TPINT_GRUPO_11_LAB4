package datosImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import datos.CuentaDao;
import entidades.Cuenta;
import entidades.Movimiento;
import entidades.TipoCuenta;

public class CuentaDaoImpl implements CuentaDao {
	private Conexion cn;
	@Override
	public int insert(Cuenta cuenta, Movimiento movimiento) {
	    int filas = 0;
		try {
			cn=new Conexion();
			cn.Open();
			
			//1. INSERTAR EN LA TABLA DE CUENTAS
			String query="INSERT INTO CUENTAS(numeroCuenta_Ctas,DNI_Ctas,fechaCreacion_Ctas,tipoCta_Ctas,"
		    		+ "CBU_Ctas,saldo_Ctas) VALUES(?,?,?,?,?,?)";
			PreparedStatement ps;
			ps = cn.prepare(query);
			ps.setInt(1, cuenta.getNumero());
			ps.setString(2, cuenta.getDni());
			ps.setString(3, cuenta.getFechaCreacion());
			ps.setInt(4,cuenta.getTipo().getIdTipo());
			ps.setString(5, cuenta.getCbu());
			ps.setFloat(6, cuenta.getSaldo());
			
			filas+= ps.executeUpdate();
			
			//2. INSERTAR EN LA TABLA MOVIMIENTOS
			
			String queryM = "INSERT INTO MOVIMIENTOS \r\n"
					+ "(`DNI_Movs`,\r\n"
					+ "`numeroCuenta_Movs`,\r\n"
					+ "`fecha_Movs`,\r\n"
					+ "`detalle_Movs`,\r\n"
					+ "`importe_Movs`,\r\n"
					+ "`tipoMovimiento_Movs`)"
					+ " VALUES(?,?,?,?,?,?)";
			
			PreparedStatement psM;
			psM = cn.prepare(queryM);
			psM.setInt(1, movimiento.getDniMovimiento());
			psM.setInt(2, movimiento.getNumeroCuenta());
			psM.setDate(3, movimiento.getFecha());
			psM.setString(4, movimiento.getDetalle());
			psM.setFloat(5, movimiento.getImporte());
			psM.setInt(6, movimiento.getTipo().getIdTipoMovimiento());
			
			filas+= psM.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
		        if (cn != null)
		            cn.close();
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		return filas; 
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
				+ " saldo_Ctas = ?, "
				+ " baja_Ctas = ? "
				+ " WHERE numeroCuenta_Ctas = ? AND DNI_Ctas = ?";
		cn = new Conexion();
		cn.Open();
		try {
			PreparedStatement pst = cn.prepare(query);
			pst.setString(1, cuenta.getFechaCreacion());
			pst.setInt(2, cuenta.getTipo().getIdTipo());
			pst.setString(3, cuenta.getCbu());
			pst.setFloat(4, cuenta.getSaldo());
			pst.setBoolean(5, cuenta.getBaja());
			pst.setInt(6,cuenta.getNumero());
			pst.setString(7, cuenta.getDni());
			
			if(pst.executeUpdate() == 1) {
				update = true;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return update;
	}
	public ArrayList<Cuenta> obtenerCuentas(String dni, Boolean cuentasInactivas) {
		ArrayList<Cuenta> listaCuentas = new ArrayList<Cuenta>();
		try {
			cn = new Conexion();
			cn.Open();
			String query = "SELECT numeroCuenta_Ctas as numero, DNI_Ctas as dni, fechaCreacion_Ctas as fecha, descripcion_TCta as tipoNombre, "
					+ " CBU_Ctas as cbu, saldo_Ctas as saldo, idTipoCta_TCta as tipoId, baja_Ctas as baja"
					+ " FROM CUENTAS INNER JOIN tipos_de_cuentas ON cuentas.tipoCta_Ctas = tipos_de_cuentas.idTipoCta_TCta"
					+ " WHERE 1 = 1 ";
			if(dni!=null && !dni.isBlank()) {
				query+=" AND DNI_Ctas LIKE '%"+dni+"%'";
			}
			
			if(cuentasInactivas!=null) {
				if(cuentasInactivas != true) {
					query+=" AND cuentas.baja_Ctas = FALSE";
				}
				
			}else {
				System.out.println("NULL");
			}
			
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
				cuenta.setBaja(rs.getBoolean("baja"));
				
				listaCuentas.add(cuenta);
			}
			rs.close();
			cn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaCuentas;
	}
	@Override
	public boolean eliminar(int NumeroCuenta) {
		Conexion cn = new Conexion();
		boolean eliminado = false;
		System.out.println("Cuenta a eliminar dao: " + NumeroCuenta);
		try {
			cn.Open();
			String query = "UPDATE cuentas SET baja_Ctas = '1' WHERE NumeroCuenta_Ctas = ?";
			PreparedStatement ps = cn.prepare(query);
			ps.setInt(1, NumeroCuenta);
			
			eliminado = ps.executeUpdate() > 0;
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {			
			cn.close();
		}
		return eliminado;
	}
	public boolean tieneMenosDe3Cuentas(String dni) {
		boolean valido = false;
		int cantidad = 0;
		
		try {
			cn = new Conexion();
			cn.Open();
			
			String query = "SELECT COUNT(*) AS cantidad FROM CUENTAS C WHERE C.DNI_Ctas = ? AND C.baja_Ctas = FALSE";
			PreparedStatement ps = cn.prepare(query);
			ps.setString(1, dni);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				cantidad = rs.getInt("cantidad");
			}
			
			rs.close();
			ps.close();
			
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return cantidad < 3;

	}
    @Override
    public Cuenta obtenerCuentaPorDni(String dni) {
        Cuenta cuenta = null;
        String query = "SELECT * FROM cuentas WHERE DNI_Ctas = ?"; 
        cn = new Conexion();
        try {
            cn.Open(); 
            PreparedStatement ps = cn.prepare(query);
            ps.setString(1, dni);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                cuenta = new Cuenta();
                cuenta.setNumero(rs.getInt("numeroCuenta_Ctas"));
                cuenta.setDni(rs.getString("DNI_Ctas"));
                cuenta.setFechaCreacion(rs.getString("fechaCreacion_Ctas"));
                int tipoCuentaId = rs.getInt("tipoCta_Ctas");
                TipoCuenta tipoCuenta = obtenerTipoCuentaPorId(tipoCuentaId); 
                cuenta.setTipo(tipoCuenta);
                cuenta.setCbu(rs.getString("CBU_Ctas"));
                cuenta.setSaldo(rs.getFloat("saldo_Ctas"));
                cuenta.setBaja(rs.getBoolean("baja_Ctas"));
            }
            
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace(); 
        } finally {
            cn.close();
        }
        return cuenta;
    }
    private TipoCuenta obtenerTipoCuentaPorId(int tipoCuentaId) {
		// TODO Auto-generated method stub
		return null;
	}
	public Cuenta obtenerCuentaPorCBU(String cbu) {
		Cuenta cuenta = null;
		try {
			cn = new Conexion();
			cn.Open();
			String query = "SELECT numeroCuenta_Ctas as numero, DNI_Ctas as dni, fechaCreacion_Ctas as fecha, descripcion_TCta as tipoNombre, "
					+ " CBU_Ctas as cbu, saldo_Ctas as saldo, idTipoCta_TCta as tipoId, baja_Ctas as baja"
					+ " FROM CUENTAS INNER JOIN tipos_de_cuentas ON cuentas.tipoCta_Ctas = tipos_de_cuentas.idTipoCta_TCta"
					+ " WHERE CBU_Ctas = ? ";
			
			PreparedStatement ps = cn.prepare(query);
			ps.setString(1, cbu);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				cuenta = new Cuenta();
				TipoCuenta tipoCuenta = new TipoCuenta();
				
				cuenta.setCbu(rs.getString("cbu"));
				cuenta.setDni(rs.getString("dni"));
				cuenta.setFechaCreacion(rs.getString("fecha"));
				cuenta.setNumero(rs.getInt("numero"));
				cuenta.setSaldo(rs.getFloat("saldo"));
				tipoCuenta.setIdTipo(rs.getInt("tipoId"));
				tipoCuenta.setNombre(rs.getString("tipoNombre"));
				cuenta.setTipo(tipoCuenta);
				cuenta.setBaja(rs.getBoolean("baja"));

			}
			rs.close();
			cn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cuenta;
	}
	@Override
	public Cuenta obtenerCuentaPorNumero(int numeroCuenta) {
		Cuenta cuenta = null;
		try {
			cn = new Conexion();
			cn.Open();
			String query = "SELECT numeroCuenta_Ctas as numero, DNI_Ctas as dni, fechaCreacion_Ctas as fecha, descripcion_TCta as tipoNombre, "
					+ " CBU_Ctas as cbu, saldo_Ctas as saldo, idTipoCta_TCta as tipoId, baja_Ctas as baja"
					+ " FROM CUENTAS INNER JOIN tipos_de_cuentas ON cuentas.tipoCta_Ctas = tipos_de_cuentas.idTipoCta_TCta"
					+ " WHERE numeroCuenta_Ctas = ? ";
			
			PreparedStatement ps = cn.prepare(query);
			ps.setInt(1, numeroCuenta);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				cuenta = new Cuenta();
				TipoCuenta tipoCuenta = new TipoCuenta();
				
				cuenta.setCbu(rs.getString("cbu"));
				cuenta.setDni(rs.getString("dni"));
				cuenta.setFechaCreacion(rs.getString("fecha"));
				cuenta.setNumero(rs.getInt("numero"));
				cuenta.setSaldo(rs.getFloat("saldo"));
				tipoCuenta.setIdTipo(rs.getInt("tipoId"));
				tipoCuenta.setNombre(rs.getString("tipoNombre"));
				cuenta.setTipo(tipoCuenta);
				cuenta.setBaja(rs.getBoolean("baja"));

			}
			rs.close();
			cn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cuenta;
	}
	@Override
	public Boolean existeCuenta(int numeroCuenta) {
		Boolean existe = false;
		
		try {
			cn = new Conexion();
			cn.Open();
			
			String existeQuery="SELECT * FROM db_tp.cuentas WHERE numeroCuenta_Ctas = ? AND baja_Ctas = false";
			
			PreparedStatement ps = cn.prepare(existeQuery);
			ps.setInt(1, numeroCuenta);
			
			ResultSet rs = ps.executeQuery();
			  if (rs.next()) {
			       existe = true;
			   }
			  
			  cn.close();
			
		}catch(Exception e) {
			e.printStackTrace();		
		}
		
		return existe;
	}
	
}