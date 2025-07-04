package negocioImpl;

import java.sql.Date;

import datosImpl.ReportesDaoImpl;
import negocio.ReporteNegocio;

public class ReporteNegocioImpl implements ReporteNegocio{
	ReportesDaoImpl dao = new ReportesDaoImpl() ;
	@Override
	public int obtenerCantidadTransferencias(Date fechaDesde, Date fechaHasta) {
		return dao.obtenerCantidadTransferencias(fechaDesde, fechaHasta);
	}

}
