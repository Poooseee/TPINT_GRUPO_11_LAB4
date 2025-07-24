package datosImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import datos.MovimientoDao;
import entidades.Cuenta;
import entidades.Movimiento;
import entidades.Prestamo;
import entidades.TipoMovimiento;
import entidades.Transferencia;

public class MovimientoDaoImpl implements MovimientoDao{
	Conexion cn;
	@Override
	public int realizarTransferencia(Movimiento movimientoEntrada, Movimiento movimientoSalida , Transferencia transferencia) {
		
		int filas = 0;
		
		try {
			
			cn=new Conexion();
			cn.Open();
			
			//1. REGISTRAR LA TRANSFERENCIA
			
				String queryTransfer = "INSERT INTO TRANSFERENCIAS (\r\n"
						+ "numeroCuentaOrigen_Trans, \r\n"
						+ "numeroCuentaDestino_Trans, \r\n"
						+ "importe_Trans) \r\n"
						+ "VALUES (\r\n"
						+ " ?, ?, ?);" ;
				
				PreparedStatement ps = cn.prepare(queryTransfer);
				ps.setInt(1, transferencia.getNumeroCuentaOrigen());
				ps.setInt(2,transferencia.getNumeroCuentaDestino());
				ps.setFloat(3,transferencia.getImporte());
				
				filas+=ps.executeUpdate();

			//2. REGISTRAR MOVIMIENTO DE SALIDA
		
				//REGISTRAR EN TABLA MOVIMIENTOS
				String movimientoSalidaQuery = "INSERT INTO MOVIMIENTOS ( \r\n"
						+ "DNI_Movs, \r\n"
						+ "numeroCuenta_Movs, \r\n"
						+ "fecha_Movs, \r\n"
						+ "detalle_Movs, \r\n"
						+ "importe_Movs, \r\n"
						+ "tipoMovimiento_Movs) "
						+ " VALUES (?,?,?,?,?,?);";
				
				PreparedStatement psMS = cn.prepare(movimientoSalidaQuery);
				psMS.setInt(1, movimientoSalida.getDniMovimiento());
				psMS.setInt(2, movimientoSalida.getNumeroCuenta());
				psMS.setDate(3, movimientoSalida.getFecha());
				psMS.setString(4, movimientoSalida.getDetalle());
				psMS.setFloat(5, movimientoSalida.getImporte());
				psMS.setInt(6, movimientoSalida.getTipo().getIdTipoMovimiento());
				
				filas+=psMS.executeUpdate();
			
				//REGISTRAR EN TABLA CUENTAS (DISMINUIR IMPORTE)
				String cuentaSalidaQuery = "UPDATE CUENTAS"
						+ " SET saldo_Ctas = (saldo_Ctas - ?)"
						+ " WHERE numeroCuenta_Ctas = ?"
						+ " AND DNI_Ctas = ?;";
				
				PreparedStatement psCS = cn.prepare(cuentaSalidaQuery);
				psCS.setFloat(1, transferencia.getImporte());
				psCS.setInt(2, movimientoSalida.getNumeroCuenta());
				psCS.setInt(3, movimientoSalida.getDniMovimiento());
				
				filas+=psCS.executeUpdate();

				
			//3. REGISTRAR MOVIMIENTO DE ENTRADA
				
				//REGISTRAR EN TABLA MOVIMIENTOS
				String movimientoEnrtadaQuery = "INSERT INTO MOVIMIENTOS ( \r\n"
						+ "DNI_Movs, \r\n"
						+ "numeroCuenta_Movs, \r\n"
						+ "fecha_Movs, \r\n"
						+ "detalle_Movs, \r\n"
						+ "importe_Movs, \r\n"
						+ "tipoMovimiento_Movs) "
						+ " VALUES (?,?,?,?,?,?);";
				
				PreparedStatement psME = cn.prepare(movimientoEnrtadaQuery);
				psME.setInt(1, movimientoEntrada.getDniMovimiento());
				psME.setInt(2, movimientoEntrada.getNumeroCuenta());
				psME.setDate(3, movimientoEntrada.getFecha());
				psME.setString(4, movimientoEntrada.getDetalle());
				psME.setFloat(5, movimientoEntrada.getImporte());
				psME.setInt(6, movimientoEntrada.getTipo().getIdTipoMovimiento());
				
				filas+=psME.executeUpdate();
			
				//REGISTRAR EN TABLA CUENTAS (SUMAR IMPORTE)
				String cuentaEntradaQuery = "UPDATE CUENTAS"
						+ " SET saldo_Ctas = (saldo_Ctas + ?)"
						+ " WHERE numeroCuenta_Ctas = ?"
						+ " AND DNI_Ctas = ?;";
				
				PreparedStatement psCE = cn.prepare(cuentaEntradaQuery);
				psCE.setFloat(1, transferencia.getImporte());
				psCE.setInt(2, movimientoEntrada.getNumeroCuenta());
				psCE.setInt(3, movimientoEntrada.getDniMovimiento());
				
				filas+=psCE.executeUpdate();
				
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
			//EN TEORIA SON 3 INSERT Y 2 MODIFICACIONES, FILAS DEBERIA VALER 5
			return filas;
	  }

	@Override
	public List<Object[]> obtenerMovimientosConCuenta(String dniCliente) {
		//lista de objetos
		List<Object[]> resultados = new ArrayList<>();
	    try {
	        cn = new Conexion();
	        cn.Open();
	        
	        //Consulta filtrada por DNI
	        String query = "SELECT m.*, c.*, tm.descripcion_TMovs " +
	                      "FROM MOVIMIENTOS m " +
	                      "JOIN CUENTAS c ON m.numeroCuenta_Movs = c.numeroCuenta_Ctas " +
	                      "JOIN TIPOS_DE_MOVIMIENTOS tm ON m.tipoMovimiento_Movs = tm.idTipoMov_TMovs " +
	                      "WHERE m.DNI_Movs = ? " +
	                      "ORDER BY m.fecha_Movs DESC";
	        
	        PreparedStatement stmt = cn.prepare(query);
	        stmt.setString(1, dniCliente);
	        
	        ResultSet rs = stmt.executeQuery();
	        
	        while(rs.next()) {
	            // Crear objeto Movimiento
	            Movimiento movimiento = new Movimiento();
	            movimiento.setDniMovimiento(rs.getInt("DNI_Movs"));
	            movimiento.setFecha(rs.getDate("fecha_Movs"));
	            movimiento.setNumeroCuenta(rs.getInt("numeroCuenta_Movs"));
	            movimiento.setDetalle(rs.getString("detalle_Movs"));
	            movimiento.setImporte(rs.getFloat("importe_Movs"));
	            
	            TipoMovimiento tipo = new TipoMovimiento(
	                rs.getInt("tipoMovimiento_Movs"),
	                rs.getString("descripcion_TMovs")
	            );
	            movimiento.setTipo(tipo);
	            
	            // Crear objeto Cuenta (solo con los datos necesarios)
	            Cuenta cuenta = new Cuenta();
	            cuenta.setNumero(rs.getInt("numeroCuenta_Ctas"));
	            cuenta.setCbu(rs.getString("CBU_Ctas"));
	            
	            // Almacenar ambos objetos en un array
	            Object[] fila = {movimiento, cuenta};
	            resultados.add(fila);
	        }
	        
	        rs.close();
	        stmt.close();
	    } catch(Exception e) {
	        e.printStackTrace();
	    } finally {
	        if(cn != null) {
	            cn.close();
	        }
	    }
	    return resultados;
	}
	@Override
	public List<Object[]> filtrar(String dniCliente, String fecha, String nroCuenta, String importe, String tipo) {
	    List<Object[]> resultados = new ArrayList<>();
	    try {
	        cn = new Conexion();
	        cn.Open();
	        
	        StringBuilder query = new StringBuilder(
	            "SELECT m.*, c.*, tm.descripcion_TMovs " +
	            "FROM MOVIMIENTOS m " +
	            "JOIN CUENTAS c ON m.numeroCuenta_Movs = c.numeroCuenta_Ctas " +
	            "JOIN TIPOS_DE_MOVIMIENTOS tm ON m.tipoMovimiento_Movs = tm.idTipoMov_TMovs " +
	            "WHERE m.DNI_Movs = ? "
	        );
	        
	        // Filtro por fecha
	        if(fecha != null && !fecha.isEmpty()) {
	            query.append("AND DATE(m.fecha_Movs) = ? ");
	        }
	        
	        // Filtro por número de cuenta
	        if(nroCuenta != null && !nroCuenta.isEmpty()) {
	            query.append("AND c.numeroCuenta_Ctas = ? ");
	        }
	        
	        // Filtro por importe
	        if(importe != null && !importe.isEmpty()) {
	            switch(importe) {
	                case "0a10k":
	                    query.append("AND m.importe_Movs BETWEEN 0 AND 10000 ");
	                    break;
	                case "10ka50k":
	                    query.append("AND m.importe_Movs BETWEEN 10000 AND 50000 ");
	                    break;
	                case "50ka100k":
	                    query.append("AND m.importe_Movs BETWEEN 50000 AND 100000 ");
	                    break;
	                case "+100k":
	                    query.append("AND m.importe_Movs > 100000 ");
	                    break;
	            }
	        }
	        
	        // Filtro por tipo de movimiento
	        if(tipo != null && !tipo.isEmpty()) {
	            query.append("AND m.tipoMovimiento_Movs = ? ");
	        }
	        
	        query.append("ORDER BY m.fecha_Movs DESC");
	        
	        PreparedStatement stmt = cn.prepare(query.toString());
	        int paramIndex = 1;
	        stmt.setString(paramIndex++, dniCliente);
	        
	        if(fecha != null && !fecha.isEmpty()) {
	            stmt.setDate(paramIndex++, java.sql.Date.valueOf(fecha));
	        }
	        
	        if(nroCuenta != null && !nroCuenta.isEmpty()) {
	            stmt.setInt(paramIndex++, Integer.parseInt(nroCuenta));
	        }
	        
	        if(tipo != null && !tipo.isEmpty()) {
	            stmt.setInt(paramIndex++, Integer.parseInt(tipo));
	        }
	        
	        ResultSet rs = stmt.executeQuery();
	        
	        while(rs.next()) {
	            Movimiento movimiento = new Movimiento();
	            movimiento.setDniMovimiento(rs.getInt("DNI_Movs"));
	            movimiento.setFecha(rs.getDate("fecha_Movs"));
	            movimiento.setNumeroCuenta(rs.getInt("numeroCuenta_Movs"));
	            movimiento.setDetalle(rs.getString("detalle_Movs"));
	            movimiento.setImporte(rs.getFloat("importe_Movs"));
	            
	            TipoMovimiento tipoMov = new TipoMovimiento(
	                rs.getInt("tipoMovimiento_Movs"),
	                rs.getString("descripcion_TMovs")
	            );
	            movimiento.setTipo(tipoMov);
	            
	            Cuenta cuenta = new Cuenta();
	            cuenta.setNumero(rs.getInt("numeroCuenta_Ctas"));
	            cuenta.setCbu(rs.getString("CBU_Ctas"));
	            
	            Object[] fila = {movimiento, cuenta};
	            resultados.add(fila);
	        }
	        
	        rs.close();
	        stmt.close();
	    } catch(Exception e) {
	        e.printStackTrace();
	    } finally {
	        if(cn != null) {
	            cn.close();
	        }
	    }
	    return resultados;
	}
	@Override
	public int pagarCuota(Movimiento movimiento, int numeroCuenta, float importe, int idPrestamo, int numeroCuota) {
		int filas = 0;
		
		try {
		
			cn = new Conexion();
			cn.Open();
			
			//CAMBIAR EL ESTADO EN LA TABLA CUOTAS
			
			String query = "UPDATE CUOTAS SET abonada_Cuo = TRUE WHERE numeroCuota_Cuo = ? and idPrestamo_Cuo = ?";
			
			PreparedStatement ps= cn.prepare(query);
			ps.setInt(1, numeroCuota);
			ps.setInt(2, idPrestamo);
			
			filas += ps.executeUpdate();
			
			//INSERTAR EL MOVIMIENTO
			String queryMov = " INSERT INTO `db_tp`.`movimientos`\r\n"
					+ " (`DNI_Movs`,\r\n"
					+ "`numeroCuenta_Movs`,\r\n"
					+ "`fecha_Movs`,\r\n"
					+ "`detalle_Movs`,\r\n"
					+ "`importe_Movs`,\r\n"
					+ "`tipoMovimiento_Movs`) "
					+ " VALUES(?,?,?,?,?,?)";
			PreparedStatement psMov = cn.prepare(queryMov);
			psMov.setInt(1, movimiento.getDniMovimiento());
			psMov.setInt(2, movimiento.getNumeroCuenta());
			psMov.setDate(3, movimiento.getFecha());
			psMov.setString(4, movimiento.getDetalle());
			psMov.setFloat(5, movimiento.getImporte());
			psMov.setInt(6, movimiento.getTipo().getIdTipoMovimiento());
			
			filas+=psMov.executeUpdate();
			
			//DESCONTAR LA PLATA AL CLIENTE
			String queryCliente = "UPDATE CUENTAS SET saldo_Ctas = (saldo_Ctas - ?) WHERE numeroCuenta_Ctas = ?";
			PreparedStatement psCliente = cn.prepare(queryCliente);
			psCliente.setFloat(1, importe);
			psCliente.setInt(2, numeroCuenta);
			
			filas+=psCliente.executeUpdate();
			
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
		
		//si son 3 esta todo OK
		return filas;
	}

	@Override
	public int altaPrestamoMovimiento(Movimiento movimiento, Float importePedido) {
		int filas = 0;
		
		try {
		
			cn = new Conexion();
			cn.Open();
			
			//1. INSERTAR EL MOVIMIENTO
			String queryMov = " INSERT INTO `db_tp`.`movimientos`\r\n"
					+ " (`DNI_Movs`,\r\n"
					+ "`numeroCuenta_Movs`,\r\n"
					+ "`fecha_Movs`,\r\n"
					+ "`detalle_Movs`,\r\n"
					+ "`importe_Movs`,\r\n"
					+ "`tipoMovimiento_Movs`) "
					+ " VALUES(?,?,?,?,?,?)";
			
			PreparedStatement psMov = cn.prepare(queryMov);
			psMov.setInt(1, movimiento.getDniMovimiento());
			psMov.setInt(2, movimiento.getNumeroCuenta());
			psMov.setDate(3, movimiento.getFecha());
			psMov.setString(4, movimiento.getDetalle());
			psMov.setFloat(5, movimiento.getImporte());
			psMov.setInt(6, movimiento.getTipo().getIdTipoMovimiento());
			
			filas+=psMov.executeUpdate();
			
			//2. DAR LA PLATA A LA CUENTA
			String queryCuenta="UPDATE CUENTAS SET saldo_Ctas = (saldo_Ctas + ?) WHERE numeroCuenta_Ctas = ?";
			PreparedStatement psCuenta = cn.prepare(queryCuenta);
			psCuenta.setFloat(1, importePedido);
			psCuenta.setInt(2, movimiento.getNumeroCuenta());
			
			filas+=psCuenta.executeUpdate();
			
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
		
		//deberia valer 2
		return filas;
	}



}
