package negocio;

import java.sql.Date;

import entidades.ClienteReporte;
import entidades.PrestamosReporte;

public interface ReporteNegocio {
	public int obtenerCantidadTransferencias (Date fechaDesde, Date fechaHasta);
	public ClienteReporte obtenerClienteConMasTransferencias();
	public ClienteReporte obtenerClienteConMasPrestamos();
	public PrestamosReporte obtenerPorcentajesPrestamos();
	
}
