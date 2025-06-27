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
		  System.out.println("✔ Entró al doGet de ServletCuentas");
		  System.out.println("✔ Entró al doGet de ServletCuentas");
		  System.out.println("✔ Entró al doGet de ServletCuentas");
		  System.out.println("✔ Entró al doGet de ServletCuentas");
		  
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
	
	
	//AL APRETAR BOTON AGREGAR
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("btnAgregar")!=null) {
			
			String mensajeSalida = "no se pudo cargar";
			if(agregarCuenta(request)) {
				mensajeSalida="Cargado Correctamente";
			}
			request.setAttribute("mensajeAlta",mensajeSalida);
			actualizarProximoNumeroDeCuenta();
			RequestDispatcher dispatcher = request.getRequestDispatcher("/abmlCuentas.jsp");
			dispatcher.forward(request, response);
		}
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
	
	return cuenta;
}
}
