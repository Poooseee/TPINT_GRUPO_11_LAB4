package datosImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import datos.PrestamoDao;
import entidades.Cuota;
import entidades.Prestamo;

public class PrestamoDaoImpl implements PrestamoDao {
	private Conexion cn;
	@Override
	public List<Prestamo> get(String estado,String dni,String numeroCuenta) {
		
		List<Prestamo> listaPrestamos = new ArrayList<>();
		String query ="SELECT idPrestamo_Prest AS id, DNI_Prest AS dni, importeAPagar_Prest AS importePagar,"
			        	+ " importePedido_Prest AS importePedido, montoPorMes_Prest AS montoPorMes,"
			        	+ " plazoPagos_Prest AS plazoPagos, fechaPrestamo_Prest AS fecha, estado_Prest AS estado, cuenta_Prest AS cuenta"
			        	+ " FROM prestamos ";
		
		    boolean primero = true;
			if(!estado.isBlank()) {
				query += " WHERE estado_Prest = '"+estado+"' ";
				primero = false;
				
			}
			if(!dni.isBlank()) {
				query+= primero ? "WHERE DNI_Prest LIKE '%"+dni+"%' " : " AND DNI_Prest LIKE '%"+dni+"%'";
				primero = false;
			}
			if(!numeroCuenta.isBlank()) {
				query+= primero ? "WHERE cuenta_Prest LIKE '%"+numeroCuenta+"%' " : " AND cuenta_Prest LIKE '%"+numeroCuenta+"%'";
				primero = false;
				
			}
			
			
		
		cn = new Conexion();
		cn.Open();
		ResultSet rs = cn.query(query);
		
		try {
			
			while(rs.next()) {
				Prestamo prestamo = new Prestamo();
				prestamo.setIdPrestamo(rs.getInt(1));
				prestamo.setDni(rs.getString(2));
				prestamo.setImportePagar(rs.getFloat(3));
				prestamo.setImportePedido(rs.getFloat(4));
				prestamo.setMontoPorMes(rs.getFloat(5));
				prestamo.setPlazoPagos(rs.getInt(6));
				prestamo.setFecha(rs.getDate(7));
				prestamo.setEstado(rs.getString(8));
				prestamo.setCuenta(rs.getInt(9));
                 listaPrestamos.add(prestamo);
			}
			rs.close();			
		}catch(Exception e) {
			e.printStackTrace();
		}
		cn.close();
		return listaPrestamos;
	}
	@Override
	public boolean cambiarEstado(String estado,int id) {
		int filas =0;
		boolean cambiar = false;
		System.out.println("estado "+estado);
        String query = "UPDATE prestamos SET estado_Prest = ? WHERE idPrestamo_Prest = ?";
        cn = new Conexion();
        cn.Open();
        
        
        try {
        	PreparedStatement pst = cn.prepare(query);
        	pst.setString(1, estado);
        	pst.setInt(2, id);
        	
          filas = pst.executeUpdate();
          pst.close();
        }catch(Exception e) {
        	e.printStackTrace();
        }finally {
        	cn.close();
        }
		if(filas ==1) {
			cambiar = true;
		}
		return cambiar;
	}
	@Override
	public Prestamo obtenerPrestamo(int id) {
		Prestamo prestamo = null;
		
		String query = "SELECT `prestamos`.`idPrestamo_Prest`,\r\n"
				+ "    `prestamos`.`DNI_Prest`,\r\n"
				+ "    `prestamos`.`cuenta_Prest`,\r\n"
				+ "    `prestamos`.`fechaPrestamo_Prest`,\r\n"
				+ "    `prestamos`.`importeAPagar_Prest`,\r\n"
				+ "    `prestamos`.`importePedido_Prest`,\r\n"
				+ "    `prestamos`.`plazoPagos_Prest`,\r\n"
				+ "    `prestamos`.`montoPorMes_Prest`,\r\n"
				+ "    `prestamos`.`estado_Prest`\r\n"
				+ "FROM `db_tp`.`prestamos` WHERE idPrestamo_Prest = ?";
		
		try {
			cn = new Conexion();
			cn.Open();
			
			PreparedStatement ps = cn.prepare(query);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				prestamo = new Prestamo();
				prestamo.setIdPrestamo(rs.getInt(1));
				prestamo.setDni(String.valueOf(rs.getInt(2)));
				prestamo.setCuenta(rs.getInt(3));
				prestamo.setFecha(rs.getDate(4));
				prestamo.setImportePagar(rs.getFloat(5));
				prestamo.setImportePedido(rs.getFloat(6));
				prestamo.setPlazoPagos(rs.getInt(7));
				prestamo.setMontoPorMes(rs.getFloat(8));
				prestamo.setEstado(rs.getString(9));
			}

		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			cn.close();
		}
		
		return prestamo;
	}
	@Override
	public List<Cuota> obtenerCuotasPorPrestamo(int idPrestamo) {
		
		List<Cuota>listado = new ArrayList<>();
		
		String query = "SELECT `cuotas`.`idPrestamo_Cuo`,\r\n"
				+ "    `cuotas`.`numeroCuota_Cuo`,\r\n"
				+ "    `cuotas`.`abonada_Cuo`\r\n"
				+ "FROM `db_tp`.`cuotas` WHERE idPrestamo_Cuo = ?";
		
		try {
			cn = new Conexion();
			cn.Open();
			
			PreparedStatement ps = cn.prepare(query);
			ps.setInt(1, idPrestamo);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Cuota cuota = new Cuota();
				
				cuota.setIdPrestamo(rs.getInt(1));
				cuota.setNumeroCuota(rs.getInt(2));
				cuota.setAbonada(rs.getBoolean(3));
				
				listado.add(cuota);
			}

		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			cn.close();
		}
		
		return listado;
	}
	public int insertarPrestamo(Prestamo prestamo) {
		int filas = 0;
		
		try {
			
			cn=new Conexion();
			cn.Open();
			
			//1. INSERTAR EN TABLA PRESTAMOS
			String prestamoQuery = "INSERT INTO `db_tp`.`prestamos`\r\n"
					+ "(`DNI_Prest`,\r\n"
					+ "`cuenta_Prest`,\r\n"
					+ "`fechaPrestamo_Prest`,\r\n"
					+ "`importeAPagar_Prest`,\r\n"
					+ "`importePedido_Prest`,\r\n"
					+ "`plazoPagos_Prest`,\r\n"
					+ "`montoPorMes_Prest`,\r\n"
					+ "`estado_Prest`)\r\n"
					+ "VALUES (?,?,?,?,?,?,?,?)";
			
			PreparedStatement psPr = cn.prepare(prestamoQuery,Statement.RETURN_GENERATED_KEYS);
			psPr.setString(1, prestamo.getDni());
			psPr.setInt(2, prestamo.getCuenta());
			psPr.setDate(3, prestamo.getFecha());
			psPr.setFloat(4, prestamo.getImportePagar());
			psPr.setFloat(5, prestamo.getImportePedido());
			psPr.setInt(6, prestamo.getPlazoPagos());
			psPr.setFloat(7, prestamo.getMontoPorMes());
			psPr.setString(8, prestamo.getEstado());
			
			filas+= psPr.executeUpdate();

			//INSERTAR LAS CUOTAS
			int idPrestamo = 0;
			ResultSet rs = psPr.getGeneratedKeys();
			
			if(rs.next()) {
				idPrestamo = rs.getInt(1);
			}
			
			for(int i = 1; i <= prestamo.getPlazoPagos();i++) {
				String cuotasQuery = "INSERT INTO cuotas (idPrestamo_Cuo, numeroCuota_Cuo, abonada_Cuo) VALUES (?, ?, ?)";
				PreparedStatement psCuo = cn.prepare(cuotasQuery);
				psCuo.setInt(1, idPrestamo);
				psCuo.setInt(2, i);
				psCuo.setBoolean(3, false);
				psCuo.executeUpdate();
			}
			
			
		}catch(Exception e) {
			
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
	
	
	
	

}
