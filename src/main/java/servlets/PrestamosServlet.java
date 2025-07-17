package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Prestamo;
import negocio.PrestamoNegocio;
import negocioImpl.PrestamoNegocioImpl;

@WebServlet("/PrestamosServlet")
public class PrestamosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public PrestamosServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String estado="",dni ="";
		request.setAttribute("listaPrestamos", obtenerListaPrestamos(estado,dni));
		
		List<Prestamo> listado=obtenerListaPrestamos(estado,dni);
		
		for(Prestamo prestamo : listado) {
			
			String prestamosS = prestamo.toString();
			
			System.out.println(prestamosS);
		}

		request.setAttribute("filtroEstado", estado);
		request.setAttribute("filtroDni", dni);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/autorizacionPrest.jsp");
		dispatcher.forward(request, response);
		
	}
private List<Prestamo> obtenerListaPrestamos(String estado , String dni){
    PrestamoNegocio neg = new PrestamoNegocioImpl();
    return neg.get(estado,dni,"");
}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String estado="",dni ="";
		if(request.getParameter("btnFiltrar")!=null) {
			
			estado = request.getParameter("ddlEstado");
			dni = request.getParameter("txtDni");
			
		}
		int id = 0;
		String NuevoEstado ="Pendiente";
		if(request.getParameter("txtIdPrestamo")!=null) {
			
			id = Integer.parseInt(request.getParameter("txtIdPrestamo"));
			
		if(request.getParameter("btnAutorizar")!=null) {
			
			NuevoEstado = "Aprobado";
		}
		if(request.getParameter("btnRechazar")!=null) {
			
			NuevoEstado = "Rechazado";
		}
		cambiarEstado(NuevoEstado,id);
		}
		
		request.setAttribute("listaPrestamos", obtenerListaPrestamos(estado,dni));
		request.setAttribute("filtroEstado", estado);
		request.setAttribute("filtroDni", dni);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/autorizacionPrest.jsp");
		dispatcher.forward(request, response);
	}
	private boolean cambiarEstado(String estado, int id) {
		PrestamoNegocio neg = new PrestamoNegocioImpl();
		return neg.cambiarEstado(estado, id);
	}

}
