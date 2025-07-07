package datosImpl;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import datos.ReportesDao;
import entidades.ClienteReporte;
import entidades.PrestamosReporte;

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

	@Override
	public ClienteReporte obtenerClienteConMasTransferencias() {

		ClienteReporte cliente = new ClienteReporte();
		try {
			cn = new Conexion();
			cn.Open();
			
			String query = "SELECT u.idUsuario_UxC,COUNT(*) AS cantidad_movimientos "
					+ "FROM movimientos m "
					+ "INNER JOIN usuariosxclientes u ON m.DNI_Movs = u.DNI_UxC "
					+ "WHERE m.tipoMovimiento_Movs = 4 "
					+ "GROUP BY u.DNI_UxC "
					+ "ORDER BY cantidad_movimientos DESC "
					+ "LIMIT 1;";

			ResultSet rs = cn.query(query);
			
			if(rs.next()) {
			    int idUsuario = rs.getInt("idUsuario_UxC");
			    int cantidad = rs.getInt("cantidad_movimientos");
			    
			    cliente.setId(idUsuario);
			    cliente.setCantidad(cantidad);
			}

			cn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return cliente;
	}

	@Override
	public ClienteReporte obtenerClienteConMasPrestamos() {
		ClienteReporte cliente = new ClienteReporte();
		try {
			cn = new Conexion();
			cn.Open();
			
			String query = "SELECT u.idUsuario_UxC,COUNT(*) AS cantidad_movimientos "
					+ "FROM movimientos m "
					+ "INNER JOIN usuariosxclientes u ON m.DNI_Movs = u.DNI_UxC "
					+ "WHERE m.tipoMovimiento_Movs = 2 "
					+ "GROUP BY u.DNI_UxC "
					+ "ORDER BY cantidad_movimientos DESC "
					+ "LIMIT 1;";

			ResultSet rs = cn.query(query);
			
			if(rs.next()) {
			    int idUsuario = rs.getInt("idUsuario_UxC");
			    int cantidad = rs.getInt("cantidad_movimientos");
			    
			    cliente.setId(idUsuario);
			    cliente.setCantidad(cantidad);
			}

			cn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return cliente;
	}

	@Override
	public PrestamosReporte obtenerPorcentajesPrestamos() {
	    PrestamosReporte reporte = new PrestamosReporte();
	    Conexion cn = new Conexion();
	    cn.Open(); // abrir una sola vez

	    try {
	        // Obtener total de préstamos
	        String cantidadTotalQuery = "SELECT COUNT(*) FROM prestamos";
	        ResultSet rsTotal = cn.query(cantidadTotalQuery);
	        int total = 0;
	        if (rsTotal.next()) total = rsTotal.getInt(1);
	        rsTotal.close();

	        if (total == 0) return reporte; // evitar división por cero

	        // Obtener préstamos Aprobados
	        int totalAp = 0;
	        String queryAp = "SELECT COUNT(*) FROM prestamos WHERE estado_Prest = 'Aprobado'";
	        ResultSet rsAprobado = cn.query(queryAp);
	        if (rsAprobado.next()) totalAp = rsAprobado.getInt(1);
	        rsAprobado.close();

	        // Obtener préstamos Rechazados
	        int totalRe = 0;
	        String queryRe = "SELECT COUNT(*) FROM prestamos WHERE estado_Prest = 'Rechazado'";
	        ResultSet rsRechazado = cn.query(queryRe);
	        if (rsRechazado.next()) totalRe = rsRechazado.getInt(1);
	        rsRechazado.close();

	        // Calcular porcentajes
	        int porcentajeAp = totalAp * 100 / total;
	        int porcentajeRe = totalRe * 100 / total;
	        int porcentajePen = 100 - (porcentajeAp + porcentajeRe);

	        // Setear en el DTO
	        reporte.setAprobado(porcentajeAp);
	        reporte.setRechazado(porcentajeRe);
	        reporte.setPendiente(porcentajePen);

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        cn.close();
	    }

	    return reporte;
	}

}
