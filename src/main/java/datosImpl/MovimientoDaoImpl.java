package datosImpl;

import java.sql.*;

import datos.MovimientoDao;
import entidades.Movimiento;
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
	
	
}
