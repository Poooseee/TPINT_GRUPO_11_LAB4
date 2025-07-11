package datosImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import datos.MovimientoDao;
import entidades.Cuenta;
import entidades.Movimiento;
import entidades.TipoMovimiento;
import entidades.Transferencia;

public class MovimientoDaoImpl implements MovimientoDao{
	Conexion cn;
	@Override
	public int realizarTransferencia(Movimiento movimientoEntrada, Movimiento movimientoSalida , Transferencia transferencia) {
		
		int filas = 0;
		
		//1. REGISTRAR LA TRANSFERENCIA
		try {
			
			cn=new Conexion();
			cn.Open();
			
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
			System.out.println("REGISTRAR TRANSFERENCIA" + filas );
		
		
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
				System.out.println("REGISTRAR MOVIMIENTO 1" + filas );
			
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
				System.out.println("REGISTRAR CUENTA 1" + filas );
		
		//3. REGISTRAR MOVIMIENTO DE ENTRADA
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
				System.out.println("REGISTRAR MOVIEMIENTO 2" + filas );
			
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
				System.out.println("REGISTRAR CUENTA 2" + filas );
			

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
	    List<Object[]> resultados = new ArrayList<>();
	    try {
	        cn = new Conexion();
	        cn.Open();
	        
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
	public ArrayList<Movimiento> filtrar(String dniCliente, String fecha, String nroCuenta, String importe,
			String tipo) {
		// TODO Auto-generated method stub
		return null;
	}

    /*@Override
    public ArrayList<Movimiento> filtrar(String dniCliente, String fecha, String nroCuenta, String importe, String tipo) {
        ArrayList<Movimiento> movimientos = new ArrayList<>();
        try {
            cn = new Conexion();
            cn.Open();
            
            StringBuilder query = new StringBuilder(
                "SELECT m.idMovimiento_Movs, m.fecha_Movs, c.CBU_Ctas as numero_cuenta, " +
                "m.detalle_Movs, m.importe_Movs, tm.descripcion_TMovs as tipo_movimiento " +
                "FROM MOVIMIENTOS m " +
                "JOIN CUENTAS c ON m.numeroCuenta_Movs = c.numeroCuenta_Ctas " +
                "JOIN TIPOS_DE_MOVIMIENTOS tm ON m.tipoMovimiento_Movs = tm.idTipoMov_TMovs " +
                "WHERE m.DNI_Movs = ? "
            );
            
            if(fecha != null && !fecha.isEmpty()) {
                query.append("AND DATE(m.fecha_Movs) = ? ");
            }
            
            if(nroCuenta != null && !nroCuenta.isEmpty()) {
                query.append("AND c.CBU_Ctas = ? ");
            }
            
            if(tipo != null && !tipo.isEmpty()) {
                query.append("AND tm.descripcion_TMovs = ? ");
            }
            
            query.append("ORDER BY m.fecha_Movs DESC");
            
            PreparedStatement stmt = cn.prepare(query.toString());
            int paramIndex = 1;
            stmt.setString(paramIndex++, dniCliente);
            
            if(fecha != null && !fecha.isEmpty()) {
                stmt.setDate(paramIndex++, Date.valueOf(fecha));
            }
            
            if(nroCuenta != null && !nroCuenta.isEmpty()) {
                stmt.setString(paramIndex++, nroCuenta);
            }
            
            if(tipo != null && !tipo.isEmpty()) {
                stmt.setString(paramIndex++, tipo);
            }
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()) {
                Movimiento m = new Movimiento();
                m.setId(rs.getInt("idMovimiento_Movs"));
                m.setFecha(rs.getDate("fecha_Movs"));
                m.setNroCuenta(rs.getString("numero_cuenta"));
                m.setDetalle(rs.getString("detalle_Movs"));
                m.setImporte(rs.getFloat("importe_Movs"));
                m.setTipo(rs.getString("tipo_movimiento"));
                movimientos.add(m);
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
        return movimientos;
    }*/
	
}
