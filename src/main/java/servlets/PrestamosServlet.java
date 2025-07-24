package servlets;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Movimiento;
import entidades.Prestamo;
import entidades.TipoMovimiento;
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
		
		//CLICK EN FILTRAR
		String estado="",dni ="";
		if(request.getParameter("btnFiltrar")!=null) {
			
			estado = request.getParameter("ddlEstado");
			dni = request.getParameter("txtDni");
			
		}
		
		//HACER CLICK EN ACEPTAR O RECHAZAR
		int id = 0;
		String NuevoEstado ="Pendiente";
		
		if(request.getParameter("btnAutorizar")!=null) {
			
			NuevoEstado = "Aprobado";
			id = Integer.parseInt(request.getParameter("txtIdPrestamo"));
			cambiarEstado(NuevoEstado,id,true);
		}
		if(request.getParameter("btnRechazar")!=null) {
			
			NuevoEstado = "Rechazado";
			id = Integer.parseInt(request.getParameter("txtIdPrestamo"));
			cambiarEstado(NuevoEstado,id,false);
		}
		
		
		request.setAttribute("listaPrestamos", obtenerListaPrestamos(estado,dni));
		request.setAttribute("filtroEstado", estado);
		request.setAttribute("filtroDni", dni);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/autorizacionPrest.jsp");
		dispatcher.forward(request, response);
	}
	private boolean cambiarEstado(String estado, int id, boolean aceptado) {
		PrestamoNegocioImpl neg = new PrestamoNegocioImpl();
		Movimiento movimiento = new Movimiento();
		Float importePedido = 0f;
		
		if(aceptado) {
			Prestamo prestamo = neg.obtenerPrestamo(id);
			
			movimiento.setDniMovimiento(Integer.parseInt(prestamo.getDni()));
			movimiento.setFecha(Date.valueOf(LocalDate.now()));
			movimiento.setImporte(prestamo.getImportePagar());
			movimiento.setNumeroCuenta(prestamo.getCuenta());
			movimiento.setDetalle("Alta de un préstamo");
			movimiento.setTipo(new TipoMovimiento(2,"Alta de un préstamo"));
			
			importePedido = prestamo.getImportePedido();
		}
	
		
		return neg.cambiarEstado(estado, id,movimiento,aceptado,importePedido);
	}

}
