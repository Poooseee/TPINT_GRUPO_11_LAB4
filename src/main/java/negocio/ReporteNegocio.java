package negocio;

import java.sql.Date;
import java.util.ArrayList;

import entidades.ClienteReporte;
import entidades.CuotasReporte;
import entidades.PrestamosReporte;

public interface ReporteNegocio {
	public int obtenerCantidadTransferencias (Date fechaDesde, Date fechaHasta);
	public ClienteReporte obtenerClienteConMasTransferencias();
	public ClienteReporte obtenerClienteConMasPrestamos();
	public PrestamosReporte obtenerPorcentajesPrestamos();
	public ArrayList <CuotasReporte>  obtenerInformacionCuotasPorCliente(String dni);
}
