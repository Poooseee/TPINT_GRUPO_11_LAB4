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
		
		//MOSTRAR TRANSFERENCIAS DEFAULT
		ReporteNegocioImpl neg = new ReporteNegocioImpl();
		Date fechaDesde = Date.valueOf(LocalDate.of(2024, 6, 1));
		Date fechaHasta = Date.valueOf(LocalDate.of(2026, 6, 1));
		
		int cantidad = neg.obtenerCantidadTransferencias(fechaDesde,fechaHasta);
		
		request.setAttribute("cantidadTransferencias", cantidad);
		System.out.println(cantidad);
		
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
