package servlets;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocioImpl.ReporteNegocioImpl;
import entidades.ClienteReporte;
import entidades.CuotasReporte;
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
		cargarDatosPagina(request);

		//DESPACHAR 
		RequestDispatcher dispatcher = request.getRequestDispatcher("/reporte.jsp");
		dispatcher.forward(request, response);
	}


		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//CARGAR DATOS ANTERIORES
			cargarDatosPagina(request);
			
		    ReporteNegocioImpl neg = new ReporteNegocioImpl();

		    //CLICK EN FECHAS
		    if (request.getParameter("btnFiltrarFechas") != null) {
		      
		            Date fechaDesde = Date.valueOf(request.getParameter("dateDesde"));
		            Date fechaHasta = Date.valueOf(request.getParameter("dateHasta"));

		            int cantidadFiltrada = neg.obtenerCantidadTransferencias(fechaDesde, fechaHasta);
		            request.setAttribute("cantidadTransferencias", cantidadFiltrada);
		            request.setAttribute("fechaDesde", fechaDesde);
		            request.setAttribute("fechaHasta", fechaHasta);
		    }

		    //CLICK EN BUSCAR CLIENTE
		    if (request.getParameter("buscarCliente") != null) {
		        String dni = request.getParameter("DNI");
		        ArrayList<CuotasReporte> lista = neg.obtenerInformacionCuotasPorCliente(dni);
		        request.setAttribute("listaCuotas", lista);
		        request.setAttribute("dni", dni);
		    }

		    //DESPACHAR
		    RequestDispatcher dispatcher = request.getRequestDispatcher("/reporte.jsp");
		    dispatcher.forward(request, response);
	
	}
		
		public void cargarDatosPagina(HttpServletRequest request) {
			ReporteNegocioImpl neg = new ReporteNegocioImpl();
			
			//MOSTRAR TRANSFERENCIAS DEFAULT
			Date fechaDesde = Date.valueOf(LocalDate.of(2024, 6, 1));
			Date fechaHasta = Date.valueOf(LocalDate.of(2026, 6, 1));
			
			int cantidad = neg.obtenerCantidadTransferencias(fechaDesde,fechaHasta);
			
			request.setAttribute("cantidadTransferencias", cantidad);
	        request.setAttribute("fechaDesde", fechaDesde);
	        request.setAttribute("fechaHasta", fechaHasta);
			
			//MOSTRAR CLIENTE CON MAYOR ACTIVIDAD
			ClienteReporte clientePrestamos = neg.obtenerClienteConMasPrestamos();
			ClienteReporte clienteTransfer = neg.obtenerClienteConMasTransferencias();

			request.setAttribute("clientePrestamos", clientePrestamos);
			request.setAttribute("clienteTransfer", clienteTransfer);
			
			//PORCENTAJE DE PRESTAMOS
			PrestamosReporte prestamosReporte = neg.obtenerPorcentajesPrestamos();
			request.setAttribute("prestamosReporte", prestamosReporte);
		}

}
