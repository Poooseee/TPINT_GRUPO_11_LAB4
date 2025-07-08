package negocioImpl;

import java.sql.Date;
import java.util.ArrayList;

import datosImpl.ReportesDaoImpl;
import entidades.ClienteReporte;
import entidades.CuotasReporte;
import entidades.PrestamosReporte;
import negocio.ReporteNegocio;

public class ReporteNegocioImpl implements ReporteNegocio{
	ReportesDaoImpl dao = new ReportesDaoImpl() ;
	@Override
	public int obtenerCantidadTransferencias(Date fechaDesde, Date fechaHasta) {
		return dao.obtenerCantidadTransferencias(fechaDesde, fechaHasta);
	}
	@Override
	public ClienteReporte obtenerClienteConMasTransferencias() {
		return dao.obtenerClienteConMasTransferencias();
	}
	@Override
	public ClienteReporte obtenerClienteConMasPrestamos() {
		return dao.obtenerClienteConMasPrestamos();
	}
	@Override
	public PrestamosReporte obtenerPorcentajesPrestamos() {
		return dao.obtenerPorcentajesPrestamos();
	}
	@Override
	public ArrayList<CuotasReporte> obtenerInformacionCuotasPorCliente(String dni) {
		return dao.obtenerInformacionCuotasPorCliente(dni);
	}

}
