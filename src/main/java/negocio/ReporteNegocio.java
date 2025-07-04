package negocio;

import java.sql.Date;

public interface ReporteNegocio {
	public int obtenerCantidadTransferencias (Date fechaDesde, Date fechaHasta);
}
