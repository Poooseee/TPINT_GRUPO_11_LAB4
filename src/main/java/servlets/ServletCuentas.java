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
			
			request.setAttribute("numeroDeCuenta", nuevoNumCuenta);
			request.setAttribute("listaTiposCuentas", ListaTiposCuentas);
			System.out.println("Tipos de cuenta obtenidos: " + ListaTiposCuentas.size());
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
		
		actualizarProximoNumeroDeCuenta();
		RequestDispatcher dispatcher = request.getRequestDispatcher("/abmlCuentas.jsp");
		dispatcher.forward(request, response);
	}
	
	public boolean agregarCuenta(HttpServletRequest request) {
		CuentaNegocio negCu = new CuentaNegocioImpl();
		Cuenta cuenta = cargarCuentaConDatosIngresados(request);
		return negCu.insert(cuenta);
	}
private Cuenta cargarCuentaConDatosIngresados(HttpServletRequest request) {
	TipoCuentaNegocio negTip = new TipoCuentaNegocioImpl();
	CuentaNegocio neg = new CuentaNegocioImpl();
	Cuenta cuenta = new Cuenta();
	cuenta.setCbu(request.getParameter("CBU"));
	cuenta.setDni(request.getParameter("DNI"));
	cuenta.setFechaCreacion(request.getParameter("fechaCreacion"));
	cuenta.setNumero(neg.obtenerNuevoNumero());
	cuenta.setTipo(new TipoCuenta(Integer.parseInt(request.getParameter("ddlTipoCuenta")),
			negTip.obtenerNombre(Integer.parseInt(request.getParameter("ddlTipoCuenta")))));
	cuenta.setSaldo(10000);
    System.out.println(cuenta.toString());
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
	cuenta.setCbu(request.getParameter("txtTablaCbu"));
	cuenta.setDni(request.getParameter("txtTablaDni"));
	cuenta.setFechaCreacion(request.getParameter("txtTablaFecha"));
	if(request.getParameter("txtTablaNumero")!=null) {
		cuenta.setNumero(Integer.parseInt(request.getParameter("txtTablaNumero")));		
	}
	cuenta.setTipo(new TipoCuenta(Integer.parseInt(request.getParameter("ddlTablaTipo")),
			negTip.obtenerNombre(Integer.parseInt(request.getParameter("ddlTablaTipo")))));
	cuenta.setSaldo(10000);
	
	return cuenta;
}

}
