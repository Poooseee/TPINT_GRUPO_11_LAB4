package datos;

import java.sql.Date;

public interface ReportesDao {
	
	public int obtenerCantidadTransferencias(Date fechaDesde, Date fechaHasta);
}
