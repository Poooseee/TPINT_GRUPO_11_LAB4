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
		}catch(Exception e) {
			e.printStackTrace();
		}
		cn.close();
		
		//2. REGISTRAR MOVIMIENTO DE SALIDA
		
			//REGISTRAR EN TABLA MOVIMIENTOS
			try {
				cn=new Conexion();
				cn.Open();
				
				String movimientoSalidaQuery = "INSERT INTO MOVIMIENTOS ( \r\n"
						+ "DNI_Movs, \r\n"
						+ "numeroCuenta_Movs, \r\n"
						+ "fecha_Movs, \r\n"
						+ "detalle_Movs, \r\n"
						+ "importe_Movs, \r\n"
						+ "tipoMovimiento_Movs) "
						+ " VALUES (?,?,?,?,?,?);";
				
				PreparedStatement ps = cn.prepare(movimientoSalidaQuery);
				ps.setInt(1, movimientoSalida.getDniMovimiento());
				ps.setInt(2, movimientoSalida.getNumeroCuenta());
				ps.setDate(3, movimientoSalida.getFecha());
				ps.setString(4, movimientoSalida.getDetalle());
				ps.setFloat(5, movimientoSalida.getImporte());
				ps.setInt(6, movimientoSalida.getTipo().getIdTipoMovimiento());
				
				filas+=ps.executeUpdate();
			}catch(Exception e) {
				e.printStackTrace();
			}
			cn.close();
			
			//REGISTRAR EN TABLA CUENTAS (DISMINUIR IMPORTE)
		
		//3. REGISTRAR MOVIMIENTO DE ENTRADA
	}

}
