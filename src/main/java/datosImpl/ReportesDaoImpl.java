package datosImpl;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import datos.ReportesDao;

public class ReportesDaoImpl  implements ReportesDao{
	private Conexion cn;
	
	@Override
	public int obtenerCantidadTransferencias(Date fechaDesde, Date fechaHasta) {
		int cantidad = 0;
		try {
			cn = new Conexion();
			cn.Open();
			
			String query = "SELECT COUNT(*) FROM MOVIMIENTOS WHERE fecha_Movs BETWEEN ? AND ? AND tipoMovimiento_Movs = 4 ";
			
			PreparedStatement ps = cn.prepare(query);
			ps.setDate(1, fechaDesde);
			ps.setDate(2, fechaHasta);
			
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
			    cantidad = rs.getInt(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return cantidad;
	}

}
