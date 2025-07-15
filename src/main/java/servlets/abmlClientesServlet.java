package servlets;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Cliente;
import entidades.Localidad;
import entidades.Pais;
import entidades.Provincia;
//import entidades.TelefonosXclientes;
import entidades.Usuario;
import negocio.ClienteNegocio;
import negocio.PaisNegocio;
//import negocio.TelefonosXclientesNegocio;
import negocioImpl.ClienteNegocioImpl;
import negocioImpl.LocalidadNegocioImpl;
import negocioImpl.PaisNegocioImpl;
import negocioImpl.ProvinciaNegocioImpl;
//import negocioImpl.TelefonosXclientesNegocioImpl;

@WebServlet("/abmlClientesServlet")
public class abmlClientesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public abmlClientesServlet() {
        super();
    }
    
    private void atributoListaPaises(HttpServletRequest request) {
    	if(request.getParameter("btnAgregarCliente")!=null) {
    		// 
    	}
    	
    	 PaisNegocioImpl paisNeg = new PaisNegocioImpl();
    	 List<Pais> listaPaises = paisNeg.obtenerPaises();
    	 
    	 String paisSeleccionado = "";
    	 if(request.getParameter("ddlPais")!=null && !request.getParameter("ddlPais").isBlank()) {
    		 paisSeleccionado = request.getParameter("ddlPais");
    	 }
    	 request.setAttribute("paisSeleccionado", paisSeleccionado);
         request.setAttribute("listaPaises", listaPaises);
    }
    
    private void atributoListaPaisesFiltro(HttpServletRequest request) {
   	 PaisNegocioImpl paisNeg = new PaisNegocioImpl();
	 List<Pais> listaPaises = paisNeg.obtenerPaises();
	 
	 String paisSeleccionado = "";
	 if(request.getParameter("ddlPaisFiltro")!=null && !request.getParameter("ddlPaisFiltro").isBlank()) {
		 paisSeleccionado = request.getParameter("ddlPaisFiltro");
	 }
	 request.setAttribute("paisSeleccionadoFiltro", paisSeleccionado);
     request.setAttribute("listaPaises", listaPaises);
    }
    
    private void atributoListaProvinciasAlta(HttpServletRequest request) {
    	ProvinciaNegocioImpl provNeg = new ProvinciaNegocioImpl();
    	 List<Provincia> listaProvinciasAlta = new ArrayList<>();
    	 
    	 String paisAlta = request.getParameter("ddlPais");
    	 if(paisAlta!=null && !paisAlta.isBlank()) {
    		 listaProvinciasAlta = provNeg.obtenerProvinciasPorPais(paisAlta);
    	 }	
    	 
    	 String provSeleccionada = "";
    	 if(request.getParameter("ddlProvincia")!=null && !request.getParameter("ddlProvincia").isBlank()) {
    		 provSeleccionada = request.getParameter("ddlProvincia");
    	 } 
    	 
    	 request.setAttribute("listaProvinciasAlta", listaProvinciasAlta);
    	 request.setAttribute("provSeleccionada", provSeleccionada);
    }
   
    private void atributoListaProvinciasFiltro(HttpServletRequest request) {
    	ProvinciaNegocioImpl provNeg = new ProvinciaNegocioImpl();
   	  List<Provincia> listaProvinciasAlta = new ArrayList<>();
   	 
   	 String paisAlta = request.getParameter("ddlPaisFiltro");
   	 if(paisAlta!=null && !paisAlta.isBlank()) {
   		 listaProvinciasAlta = provNeg.obtenerProvinciasPorPais(paisAlta);
   	 }	
   	 
   	 String provSeleccionada = "";
   	 if(request.getParameter("ddlProvinciaFiltro")!=null && !request.getParameter("ddlProvinciaFiltro").isBlank()) {
   		 provSeleccionada = request.getParameter("ddlProvinciaFiltro");
   	 } 
   	 
   	 request.setAttribute("listaProvinciasFiltro", listaProvinciasAlta);
   	 request.setAttribute("provSeleccionadaFiltro", provSeleccionada);
    }
    
    private void atributoListaLocalidadesAlta(HttpServletRequest request) {
    	LocalidadNegocioImpl locNeg = new LocalidadNegocioImpl();
    	List<Localidad> listaLocalidadesAlta = new ArrayList<>();
    	String paisAlta = request.getParameter("ddlPais");
    	String provinciaAlta = request.getParameter("ddlProvincia");
    	if(paisAlta !=null && !paisAlta.isBlank() && provinciaAlta!=null && !provinciaAlta.isBlank()) {
    		listaLocalidadesAlta = locNeg.obtenerLocalidadesXProvXPais(paisAlta, provinciaAlta);
    	}
    	String localidadSeleccionada = "";
   	 if(request.getParameter("ddlLocalidad")!=null && !request.getParameter("ddlLocalidad").isBlank()) {
   		localidadSeleccionada = request.getParameter("ddlProvincia");
   	 } 
    	request.setAttribute("listaLocalidadesAlta", listaLocalidadesAlta);
    	request.setAttribute("localidadSeleccionada", localidadSeleccionada);
    }
    
    private void atributoListaLocalidadesFiltro(HttpServletRequest request) {
    	LocalidadNegocioImpl locNeg = new LocalidadNegocioImpl();
    	List<Localidad> listaLocalidadesAlta = new ArrayList<>();
    	String paisAlta = request.getParameter("ddlPaisFiltro");
    	String provinciaAlta = request.getParameter("ddlProvinciaFiltro");
    	if(paisAlta !=null && !paisAlta.isBlank() && provinciaAlta!=null && !provinciaAlta.isBlank()) {
    		listaLocalidadesAlta = locNeg.obtenerLocalidadesXProvXPais(paisAlta, provinciaAlta);
    	}
    	String localidadSeleccionada = "";
   	 if(request.getParameter("ddlLocalidadFiltro")!=null && !request.getParameter("ddlLocalidadFiltro").isBlank()) {
   		localidadSeleccionada = request.getParameter("ddlProvinciaFiltro");
   	 } 
    	request.setAttribute("listaLocalidadesFiltro", listaLocalidadesAlta);
    	request.setAttribute("locSeleccionadaFiltro", localidadSeleccionada);
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
    	response.setCharacterEncoding("UTF-8");
    	response.setContentType("text/html; charset=UTF-8");
    	
    	// obtiene todos los paises, es igual en todos los casos, y setea el pais seleccionado
    	atributoListaPaises(request);
    	atributoListaPaisesFiltro(request);
    	// obtiene lista de provincias dependiendo del pais seleccionado en alta
    	atributoListaProvinciasAlta(request);
    	atributoListaProvinciasFiltro(request);
    	// obtiene lista de localidades dependiendo del pais y provincia seleccionado en alta
    	atributoListaLocalidadesAlta(request);
    	atributoListaLocalidadesFiltro(request);
    	// Negocios

       listarClientes(request);
        // Enviar
        RequestDispatcher rd = request.getRequestDispatcher("/abmlClientes.jsp");
        rd.forward(request, response);
    }
    private Cliente obtenerDatosFiltracion(HttpServletRequest request) {
    	Cliente datosFiltracion = new Cliente();
    	
    	datosFiltracion.setDNI(request.getParameter("txtDniFiltro"));
         datosFiltracion.setCUIL(request.getParameter("txtCuilFiltro"));
         datosFiltracion.setNombre(request.getParameter("txtNombreFiltro"));
         datosFiltracion.setApellido(request.getParameter("txtApellidoFiltro"));
        datosFiltracion.setSexo(request.getParameter("ddlSexoFiltro"));
        
        PaisNegocio negPais = new PaisNegocioImpl();
        String paisFiltro = request.getParameter("ddlPaisFiltro");
        Pais pais = negPais.obtenerPaisxNombre(paisFiltro);
         datosFiltracion.setPais(pais);
         
         ProvinciaNegocioImpl negProv = new ProvinciaNegocioImpl();
         String provinciaFiltro = request.getParameter("ddlProvinciaFiltro");
         Provincia provincia = negProv.obtenerProvinciaPorNombre(provinciaFiltro);
         datosFiltracion.setProvincia(provincia);
         
         String localidadFiltro = request.getParameter("ddlLocalidadFiltro");
         LocalidadNegocioImpl negLoc = new LocalidadNegocioImpl();
         Localidad localidad = negLoc.obtenerLocalidadPorNombre(localidadFiltro);
         datosFiltracion.setLocalidad(localidad);
         
         String fechaNac = request.getParameter("txtFechaDeNacimientoFiltro");
         if(request.getParameter("txtFechaDeNacimientoFiltro")!=null) {
        	 datosFiltracion.setFechaNacimiento(Date.valueOf(fechaNac));        	 
         }
         
         return datosFiltracion;
    }
    private void listarClientes(HttpServletRequest request) {
    	Cliente datosFiltracion = obtenerDatosFiltracion(request);
    	ClienteNegocioImpl clienteNeg = new ClienteNegocioImpl();
    	List<Cliente> listaClientes = clienteNeg.listar(datosFiltracion);
    	request.setAttribute("listaClientes", listaClientes);
    }
	private int agregarCliente(HttpServletRequest request) {
		Cliente cliente = new Cliente();
		Usuario user = new Usuario();
		int filas = 0;
		ClienteNegocio negCli = new ClienteNegocioImpl();
		// la funcion carga los objetos pasados como parametro
		cargarClienteYusuarioConDatosDelFormAlta(request,cliente,user);
		 filas = negCli.agregar(cliente, user);
		return filas;
		
	}
	private void cargarClienteYusuarioConDatosDelFormAlta(HttpServletRequest request,Cliente cliente,Usuario user) {
	PaisNegocioImpl paisNeg = new PaisNegocioImpl();
	ProvinciaNegocioImpl provNeg = new ProvinciaNegocioImpl();
	LocalidadNegocioImpl locNeg = new LocalidadNegocioImpl();
		
		//obtenemos todo lo del form 
	    cliente.setDNI(request.getParameter("txtDni"));
	    cliente.setCUIL(request.getParameter("txtCuil"));
	    cliente.setNombre(request.getParameter("txtNombre"));
	    cliente.setApellido(request.getParameter("txtApellido"));
	    cliente.setSexo(request.getParameter("ddlSexo"));
		
		String nomNac = request.getParameter("ddlNacionalidad");
		String nomPais = request.getParameter("ddlPais");
		Pais paisNac = new Pais(); //PARA NACIONALIDAD
		Pais paisRe = new Pais(); //PARA PAIS DE RESIDENCIA
		paisNac = paisNeg.obtenerPaisxNacionalidad(nomNac);
		paisRe = paisNeg.obtenerPaisxNombre(nomPais);
		
		cliente.setNacionalidad(paisNac);
		cliente.setPais(paisRe);
		
		// provincia
		String nomProv = request.getParameter("ddlProvincia");
		Provincia prov = new Provincia();
		prov = provNeg.obtenerProvinciaPorNombre(nomProv);
		cliente.setProvincia(prov);
		
		// localidad
		String nomLoc = request.getParameter("ddlLocalidad");
		Localidad loc = new Localidad();
		loc = locNeg.obtenerLocalidadPorNombre(nomLoc);
		cliente.setLocalidad(loc);
		
		cliente.setDomicilio(request.getParameter("txtDomicilio"));
		
		String fechaNac = request.getParameter("txtFechaDeNacimiento");
		Date fechaDeNac = null;
		try {
	        fechaDeNac = Date.valueOf(fechaNac);
	    } catch (IllegalArgumentException e) {
	      e.printStackTrace();
	    }
		cliente.setFechaNacimiento(fechaDeNac);
		
		cliente.setEmail(request.getParameter("txtCorreo"));
		cliente.setTelefono(request.getParameter("txtTelefono"));
		cliente.setBaja(0);
		user.setNickUsuario(request.getParameter("txtUsuario"));
		user.setContraseñaUsuario(request.getParameter("txtContrasenia"));
		user.setTipoUsuario("CLIENTE");
	}
	private void limpiarAtributosPaisYProvincia(HttpServletRequest request) {
		request.setAttribute("paisSeleccionado",null);
		request.setAttribute("provSeleccionada",null);
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		atributoListaPaises(request);
		atributoListaPaisesFiltro(request);
		// carga las provincias segun pais en alta y setea la provincia seleccionada
		atributoListaProvinciasAlta(request);
		atributoListaProvinciasFiltro(request);
		// obtiene lista de localidades dependiendo del pais y provincia seleccionado en alta
		atributoListaLocalidadesAlta(request);
		atributoListaLocalidadesFiltro(request);
		
		

		//RECUPERAR LOS CAMPOS LLENOS CUANDO SE RESETEA LA PAG
	    Cliente valoresDeLosControles = obtenerValoresIngresadosDelFormAlta(request);
	    //REENVIAR LOS CAMPOS LLENOS CUANDO SE RESETEA LA PAG
	    cargarAtributosParaElFormAlta(request,valoresDeLosControles);
	
		

		
	
		if(request.getParameter("btnAgregarCliente") != null) {
			
			int filas = agregarCliente(request);
			if(filas == 1) {
				Cliente vacio = new Cliente();
				// reutilizo funcion y limpio los inputs 
				cargarAtributosParaElFormAlta(request,vacio);
				limpiarAtributosPaisYProvincia(request);
				
			}
			request.setAttribute("cantFilas", filas);			 
		}

		if(request.getParameter("btnEliminarCliente") != null) {
			ClienteNegocioImpl clienteNeg = new ClienteNegocioImpl();
		    String DNI = request.getParameter("listDNI");
		    int filasE = clienteNeg.eliminar(DNI);
		    request.setAttribute("cantFilasE", filasE);
		   
		}
		
		
		if(request.getParameter("btnModificar")!=null) {
			
			boolean modificado = modificarCliente(request);
			request.setAttribute("resultadoModificar", modificado);
			
		}
		listarClientes(request);
		//DISPATCHER
		RequestDispatcher rd = request.getRequestDispatcher("/abmlClientes.jsp");
		
		System.out.println(request.getAttribute("hayError"));
		System.out.println("Redirigiendo a abmlClientes.jsp con errores: " + request.getAttribute("mensajeError"));
		
		//MANDAMOS LA REQUEST
		rd.forward(request, response);
		
	}
	// obtenerValoresIngresadosDelFormAlta
	private Cliente obtenerValoresIngresadosDelFormAlta(HttpServletRequest request) {
		PaisNegocioImpl pNeg = new PaisNegocioImpl();
		Cliente c = new Cliente();
		Pais p = new Pais();
		c.setDNI(request.getParameter("txtDni"));
		c.setCUIL(request.getParameter("txtCuil"));
		c.setNombre(request.getParameter("txtNombre"));
		c.setApellido(request.getParameter("txtApellido"));
		c.setSexo(request.getParameter("ddlSexo"));
		p = pNeg.obtenerPaisxNacionalidad(request.getParameter("ddlNacionalidad"));
		c.setNacionalidad(p);
		c.setDomicilio(request.getParameter("txtDomicilio"));
		if(request.getParameter("txtFechaDeNacimiento")!=null) {
			
		String fechaStr = request.getParameter("txtFechaDeNacimiento");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
		    java.util.Date utilDate = sdf.parse(fechaStr);
		    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		    c.setFechaNacimiento(sqlDate); // si espera java.sql.Date
		} catch (ParseException e) {
		    e.printStackTrace();
		}
		}
		c.setEmail(request.getParameter("txtCorreo"));
		c.setTelefono(request.getParameter("txtTelefono"));
		c.setNick(request.getParameter("txtUsuario"));
		return c;
	}
	private void cargarAtributosParaElFormAlta(HttpServletRequest request,Cliente c) {
		  request.setAttribute("dniIngresado", c.getDNI());
		    request.setAttribute("cuilIngresado", c.getCUIL());
		    request.setAttribute("nombreIngresado", c.getNombre());
		    request.setAttribute("apellidoIngresado", c.getApellido());
		    request.setAttribute("sexoSeleccionado", c.getSexo());
		    request.setAttribute("nacionalidadSeleccionada", c.getNacionalidad());
		    request.setAttribute("domicilioIngresado", c.getDomicilio());
		    request.setAttribute("fechaIngresada", c.getFechaNacimiento());
		    request.setAttribute("correoIngresado", c.getEmail());
		    request.setAttribute("telefonoIngresado", c.getTelefono());
		    request.setAttribute("usuarioIngresado", c.getNick());
	}
private boolean modificarCliente(HttpServletRequest request) {
	boolean modificado = false;
	ClienteNegocio neg = new ClienteNegocioImpl();
	//TelefonosXclientesNegocio negTel = new TelefonosXclientesNegocioImpl();
	Cliente CliAModificar = cargarClienteConDatosDeLaTabla(request);
	
	if(neg.modificar(CliAModificar)) {
		modificado = true;
		//modificado = negTel.modificar(new TelefonosXclientes(CliAModificar.getTelefono(),CliAModificar.getDNI()));
	}
	return modificado;
}
private Cliente cargarClienteConDatosDeLaTabla(HttpServletRequest request) {
	Cliente cli = new Cliente();
	cli.setDNI(request.getParameter("listDNI").toString());
	cli.setCUIL(request.getParameter("listCUIL"));
	cli.setNombre(request.getParameter("listNombre"));
	cli.setApellido(request.getParameter("listApellido"));
	cli.setSexo(request.getParameter("listSexo"));
	String fechaNac = request.getParameter("listFecha");	
	cli.setFechaNacimiento( Date.valueOf(fechaNac));
	
	PaisNegocio negPais = new PaisNegocioImpl() ;
	Pais pais = negPais.obtenerPaisxNombre(request.getParameter("listPais"));
	cli.setNacionalidad(pais);
	cli.setPais(pais);
	ProvinciaNegocioImpl neg = new ProvinciaNegocioImpl();
	Provincia provincia = neg.obtenerProvinciaPorNombre(request.getParameter("listProvincia"));
	cli.setProvincia(provincia);
	LocalidadNegocioImpl negLoc = new LocalidadNegocioImpl();
	System.out.println("localidad recibida por param "+request.getParameter("listLocalidad"));
	Localidad localidad = negLoc.obtenerLocalidadPorNombre(request.getParameter("listLocalidad"));
	
	cli.setLocalidad(localidad);
	cli.setDomicilio(request.getParameter("listDireccion"));
	cli.setEmail(request.getParameter("listEmail"));
	cli.setTelefono(request.getParameter("listTelefono"));
	cli.setPassword(request.getParameter("listPassword"));
	cli.setNick(request.getParameter("listNick"));
	
	return cli;
	
}


	public boolean NaN(String texto) {
	    try {
	    	Long.parseLong(texto);
	        return false;
	    } catch (NumberFormatException e) {
	        return true;
	    }
	}
}
