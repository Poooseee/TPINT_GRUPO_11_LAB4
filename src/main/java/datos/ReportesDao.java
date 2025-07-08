package datos;

import java.sql.Date;
import java.util.ArrayList;

import entidades.ClienteReporte;
import entidades.PrestamosReporte;
import entidades.CuotasReporte;

public interface ReportesDao {
	
	public int obtenerCantidadTransferencias(Date fechaDesde, Date fechaHasta);
	public ClienteReporte obtenerClienteConMasTransferencias();
	public ClienteReporte obtenerClienteConMasPrestamos();
	public PrestamosReporte obtenerPorcentajesPrestamos();
	public ArrayList <CuotasReporte> obtenerInformacionCuotasPorCliente(String dni);
}
