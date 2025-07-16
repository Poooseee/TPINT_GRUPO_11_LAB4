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


import entidades.Cuenta;
import entidades.Movimiento;
import entidades.TipoCuenta;
import entidades.TipoMovimiento;
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
			ArrayList<Cuenta> listaCuentas = obtenerCuentas(dni,false);
			
			request.setAttribute("ListaCuentas", listaCuentas);
			request.setAttribute("numeroDeCuenta", nuevoNumCuenta);
			request.setAttribute("listaTiposCuentas", ListaTiposCuentas);
			request.setAttribute("dni", dni);
			request.setAttribute("cuentasInactivas",false);
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
	
	private ArrayList<Cuenta> obtenerCuentas(String dni,Boolean cuentasInactivas){
		CuentaNegocioImpl neg = new CuentaNegocioImpl();
		
		return neg.obtenerListaCuentas(dni,cuentasInactivas);
	}
	
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//AGREGAR
		if(request.getParameter("btnAgregar")!=null) {
			
			int codigoInsercion = agregarCuenta(request);
			String mensajeSalida = "";
			
			switch(codigoInsercion) {
			case -1:{
				 mensajeSalida = "El cliente NO existe";
			}
			break;
			case -2:{
				 mensajeSalida = "El cliente YA TIENE 3 cuentas ACTIVAS";
			}
			break;
			case -3:{
				 mensajeSalida = "Hubo un error inesperado al insertar la cuenta";
			}
			break;
			case 1:{
				 mensajeSalida = "La cuenta fue agregada correctamente";
			}
			break;
			default:{
				 mensajeSalida = "Hubo un error";
			}
			}
			
			
			System.out.println(mensajeSalida);
			request.setAttribute("mensajeAlta",mensajeSalida);

		}
		
		//MODIFICAR
		if(request.getParameter("btnModificar")!=null) {
		
			String mensajeUpdate = "no se pudo modificar";
			
			int resultado = modificarCuenta(request);
			
			switch(resultado) {
			case -1:
			{
				mensajeUpdate = "El cliente YA TIENE 3 cuentas ACTIVAS";	
			}
			break;
			case -2:
			{
				mensajeUpdate = "Hubo un ERROR al modificar la cuenta";
			}
			break;
			case 1:
			{
				mensajeUpdate = "Modificado correctamente";
			}
			break;
			
			}

			request.setAttribute("mensajeModificacion",mensajeUpdate);
			
		}
		
		//ELIMINAR
		if(request.getParameter("btnEliminar")!=null) {
			String mensajeEliminado = "No se pudo eliminar";
			if(eliminarCuenta(request)) {
				mensajeEliminado = "Eliminado correctamente";
			}
			request.setAttribute("mensajeEliminado", mensajeEliminado);
			
		}
		
		//FILTRAR
		String dni="";
		Boolean cuentasInactivas = false;

		//NO VALIDAMOS QUE APRETEMOS EL BOTON PARA SIEMPRE MANTENER LOS FILTROS
        dni = request.getParameter("DNIClienteBuscar");
        if(dni == null) dni = "";
        
        cuentasInactivas = request.getParameter("chkCuentaBaja")!=null;
		
		ArrayList<Cuenta> listaCuentas = obtenerCuentas(dni,cuentasInactivas);
		ArrayList<TipoCuenta> listaTipoCuentas = obtenerTiposCuentas();
		int nuevoNumCuenta = actualizarProximoNumeroDeCuenta();

		request.setAttribute("ListaCuentas", listaCuentas);
		request.setAttribute("listaTiposCuentas", listaTipoCuentas);
		request.setAttribute("numeroDeCuenta", nuevoNumCuenta);
		request.setAttribute("cuentasInactivas", cuentasInactivas);
		request.setAttribute("dni", dni);
		
		System.out.println(dni);
		System.out.println(cuentasInactivas);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/abmlCuentas.jsp");
		dispatcher.forward(request, response);
	}
	
	public int agregarCuenta(HttpServletRequest request) {
		CuentaNegocioImpl negCu = new CuentaNegocioImpl();
		Cuenta cuenta = cargarCuentaConDatosIngresados(request);
		Movimiento movimiento = cargarMovimiento(request);
		
		return negCu.insert(cuenta,movimiento);
	}
	
private Movimiento cargarMovimiento(HttpServletRequest request) {
		Movimiento movimiento = new Movimiento();
		CuentaNegocio neg = new CuentaNegocioImpl();

		movimiento.setDniMovimiento(Integer.parseInt(request.getParameter("DNI")));
		movimiento.setNumeroCuenta(neg.obtenerNuevoNumero());
		movimiento.setFecha( Date.valueOf(LocalDate.now()));
		movimiento.setDetalle("Alta de Cuenta");
		movimiento.setImporte(0);
		movimiento.setTipo(new TipoMovimiento(1,"Alta de Cuenta"));
		
		return movimiento;
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

private int modificarCuenta(HttpServletRequest request) {
	CuentaNegocioImpl neg = new CuentaNegocioImpl();
    Cuenta cuenta = cargarCuentaConDatosDeLaTabla(request);
    return neg.update(cuenta);
	
}
private Cuenta cargarCuentaConDatosDeLaTabla(HttpServletRequest request) {
	Cuenta cuenta = new Cuenta();
	TipoCuenta tipC = new TipoCuenta();
	
	tipC.setIdTipo(Integer.parseInt(request.getParameter("ddlTablaTipoCuenta")));
	cuenta.setCbu(request.getParameter("txtTablaCbu").trim());
	cuenta.setDni(request.getParameter("txtTablaDni").trim());
	cuenta.setFechaCreacion(request.getParameter("txtTablaFecha").trim());
	cuenta.setNumero(Integer.parseInt(request.getParameter("txtTablaNumero").trim()));
	cuenta.setTipo(tipC);
	cuenta.setSaldo(Float.parseFloat(request.getParameter("txtTablaSaldo").trim()));

	boolean baja = request.getParameter("ddlEstado").equals("baja");
	cuenta.setBaja(baja);
	
	System.out.println("DDL DE ESTADO MARCA:"+ baja);
	return cuenta;
}

private boolean eliminarCuenta(HttpServletRequest request) {
	CuentaNegocioImpl neg = new CuentaNegocioImpl();
	int numeroCuenta = Integer.parseInt(request.getParameter("txtTablaNumero").trim());
	return neg.eliminarCuenta(numeroCuenta);
}

}
