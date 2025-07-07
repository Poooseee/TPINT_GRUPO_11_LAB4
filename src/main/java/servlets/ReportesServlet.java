package servlets;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocioImpl.ReporteNegocioImpl;
import entidades.ClienteReporte;
import entidades.PrestamosReporte;
/**
 * Servlet implementation class ReportesServlet
 */
@WebServlet("/ReportesServlet")
public class ReportesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ReportesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//AL CARGAR
		ReporteNegocioImpl neg = new ReporteNegocioImpl();
		
		//MOSTRAR TRANSFERENCIAS DEFAULT
		Date fechaDesde = Date.valueOf(LocalDate.of(2024, 6, 1));
		Date fechaHasta = Date.valueOf(LocalDate.of(2026, 6, 1));
		
		int cantidad = neg.obtenerCantidadTransferencias(fechaDesde,fechaHasta);
		
		request.setAttribute("cantidadTransferencias", cantidad);
		System.out.println(cantidad);
		
		//MOSTRAR CLIENTE CON MAYOR ACTIVIDAD
		ClienteReporte clientePrestamos = neg.obtenerClienteConMasPrestamos();
		ClienteReporte clienteTransfer = neg.obtenerClienteConMasTransferencias();

		request.setAttribute("clientePrestamos", clientePrestamos);
		request.setAttribute("clienteTransfer", clienteTransfer);
		
		//PORCENTAJE DE PRESTAMOS
		PrestamosReporte prestamosReporte = neg.obtenerPorcentajesPrestamos();
		request.setAttribute("prestamosReporte", prestamosReporte);
		
		System.out.println(prestamosReporte.getAprobado());
		System.out.println(prestamosReporte.getPendiente());
		System.out.println(prestamosReporte.getRechazado());
		
		//DESPACHAR 
		RequestDispatcher dispatcher = request.getRequestDispatcher("/reporte.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//CLICK EN FILTRAR FECHAS
		if(request.getParameter("btnFiltrarFechas")!= null) {
			ReporteNegocioImpl neg = new ReporteNegocioImpl();
			
			String desde =  request.getParameter("dateDesde");
			String hasta =  request.getParameter("dateHasta");
			
			Date fechaDesde = Date.valueOf(desde);
			Date fechaHasta = Date.valueOf(hasta);
			
			int cantidad = neg.obtenerCantidadTransferencias(fechaDesde,fechaHasta);
			
			request.setAttribute("cantidadTransferencias", cantidad);
			request.setAttribute("fechaDesde", fechaDesde);
			request.setAttribute("fechaHasta", fechaHasta);
			
			System.out.println(cantidad);
			System.out.println(fechaDesde);
			System.out.println(fechaHasta);
		}
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/reporte.jsp");
		dispatcher.forward(request, response);
	}

}
