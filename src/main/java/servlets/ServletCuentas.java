package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import entidades.Cuenta;
import entidades.TipoCuenta;
import negocio.CuentaNegocio;
import negocioImpl.CuentaNegocioImpl;
import negocio.TipoCuentaNegocio;
import negocioImpl.TipoCuentaNegocioImpl;

@WebServlet("/ServletCuentas")
public class ServletCuentas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ServletCuentas() {
        super();
       
    }

	//AL CARGAR LA PAGINA
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//obtener proximo numero de cuenta Y tipos de cuentas
			int nuevoNumCuenta = actualizarProximoNumeroDeCuenta();
			ArrayList <TipoCuenta> ListaTiposCuentas = obtenerTiposCuentas();
			String dni="";
			ArrayList<Cuenta> listaCuentas = obtenerCuentas(dni);
			
			request.setAttribute("ListaCuentas", listaCuentas);
			request.setAttribute("numeroDeCuenta", nuevoNumCuenta);
			request.setAttribute("listaTiposCuentas", ListaTiposCuentas);
			System.out.println("Tipos de cuenta obtenidos: " + ListaTiposCuentas.size());
			System.out.println("Cuentas obtenidas: " + listaCuentas.size());
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/abmlCuentas.jsp");
			dispatcher.forward(request, response);
	}
	
	private int actualizarProximoNumeroDeCuenta() {
		CuentaNegocio neg = new CuentaNegocioImpl();
		return neg.obtenerNuevoNumero();
	}
	private ArrayList<TipoCuenta> obtenerTiposCuentas(){
		TipoCuentaNegocio neg = new TipoCuentaNegocioImpl();
		return neg.obtenerTiposCuentas();
	}
	
	private ArrayList<Cuenta> obtenerCuentas(String dni){
		CuentaNegocio neg = new CuentaNegocioImpl();
		
		return neg.obtenerListaCuentas(dni);
	}
	
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("btnAgregar")!=null) {
			
			String mensajeSalida = "no se pudo cargar";
			if(agregarCuenta(request)) {
				mensajeSalida="Cargado Correctamente";
			}
			request.setAttribute("mensajeAlta",mensajeSalida);
		}
		
		
		if(request.getParameter("btnModificar")!=null) {
		
			String mensajeUpdate = "no se pudo modificar";
			if(modificarCuenta(request)) {
				 mensajeUpdate = "Modificado correctamente";
			}
			request.setAttribute("mensajeModificacion",mensajeUpdate);
			
		}
		
		if(request.getParameter("btnEliminar")!=null) {
			String mensajeEliminado = "No se pudo eliminar";
			if(eliminarCuenta(request)) {
				mensajeEliminado = "Eliminado correctamente";
			}
			request.setAttribute("mensajeEliminado", mensajeEliminado);
		}
		
		String dni="";
		if(request.getParameter("btnBuscar")!=null) {
           dni = request.getParameter("DNIClienteBuscar");			
		}
		ArrayList<Cuenta> listaCuentas = obtenerCuentas(dni);
		ArrayList<TipoCuenta> listaTipoCuentas = obtenerTiposCuentas();
		int nuevoNumCuenta = actualizarProximoNumeroDeCuenta();

		request.setAttribute("ListaCuentas", listaCuentas);
		request.setAttribute("listaTiposCuentas", listaTipoCuentas);
		request.setAttribute("numeroDeCuenta", nuevoNumCuenta);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/abmlCuentas.jsp");
		dispatcher.forward(request, response);
	}
	
	public boolean agregarCuenta(HttpServletRequest request) {
		CuentaNegocio negCu = new CuentaNegocioImpl();
		Cuenta cuenta = cargarCuentaConDatosIngresados(request);
	
		return negCu.insert(cuenta);
	}
private Cuenta cargarCuentaConDatosIngresados(HttpServletRequest request) {
	
	CuentaNegocio neg = new CuentaNegocioImpl();
	Cuenta cuenta = new Cuenta();
	TipoCuenta tipC = new TipoCuenta();
	int idTipo = 0;
	if(request.getParameter("ddlTipoCuenta")!=null) {
		 idTipo = Integer.parseInt(request.getParameter("ddlTipoCuenta"));
	}
	tipC.setIdTipo(idTipo);
	cuenta.setCbu(request.getParameter("CBU"));
	cuenta.setDni(request.getParameter("DNI"));
	cuenta.setFechaCreacion(request.getParameter("fechaCreacion"));
	cuenta.setNumero(neg.obtenerNuevoNumero());
	cuenta.setTipo(tipC);
	cuenta.setSaldo(10000);
	
	return cuenta;
}

private boolean modificarCuenta(HttpServletRequest request) {
	CuentaNegocio neg = new CuentaNegocioImpl();
    Cuenta cuenta = cargarCuentaConDatosDeLaTabla(request);
    return neg.update(cuenta);
	
}
private Cuenta cargarCuentaConDatosDeLaTabla(HttpServletRequest request) {
	TipoCuentaNegocio negTip = new TipoCuentaNegocioImpl();
	Cuenta cuenta = new Cuenta();
	TipoCuenta tipC = new TipoCuenta();
	
	tipC.setIdTipo(Integer.parseInt(request.getParameter("ddlTablaTipoCuenta")));
	cuenta.setCbu(request.getParameter("txtTablaCbu").trim());
	cuenta.setDni(request.getParameter("txtTablaDni").trim());
	cuenta.setFechaCreacion(request.getParameter("txtTablaFecha").trim());
	cuenta.setNumero(Integer.parseInt(request.getParameter("txtTablaNumero").trim()));
	cuenta.setTipo(tipC);
	cuenta.setSaldo(Float.parseFloat(request.getParameter("txtTablaSaldo").trim()));
	return cuenta;
}

private boolean eliminarCuenta(HttpServletRequest request) {
	CuentaNegocio neg = new CuentaNegocioImpl();
	int numeroCuenta = Integer.parseInt(request.getParameter("txtTablaNumero").trim());
	return neg.eliminarCuenta(numeroCuenta);
}

}
