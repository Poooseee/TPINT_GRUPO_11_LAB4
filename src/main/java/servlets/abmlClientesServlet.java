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
        
        ProvinciaNegocioImpl provNeg = new ProvinciaNegocioImpl();
        LocalidadNegocioImpl locNeg = new LocalidadNegocioImpl();
        ClienteNegocioImpl clienteNeg = new ClienteNegocioImpl();

        // DATOS PARA FORM DE ALTA 
       

        // FILTRADO
        String paisFiltro = request.getParameter("ddlPaisFiltro");
        String provinciaFiltro = request.getParameter("ddlProvinciaFiltro");

        // Listas para filtros
       
        List<Provincia> listaProvinciasFiltro = new ArrayList<>();
        List<Localidad> listaLocalidadesFiltro = new ArrayList<>();

        if (paisFiltro != null && !paisFiltro.isEmpty()) {
            listaProvinciasFiltro = provNeg.obtenerProvinciasPorPais(paisFiltro);
            if (provinciaFiltro != null && !provinciaFiltro.isEmpty()) {
                listaLocalidadesFiltro = locNeg.obtenerLocalidadesXProvXPais(paisFiltro, provinciaFiltro);
            }
        }

       
        request.setAttribute("listaProvinciasFiltro", listaProvinciasFiltro);
        request.setAttribute("listaLocalidadesFiltro", listaLocalidadesFiltro);

        // FILTRADO DE CLIENTES
        List<Cliente> listaClientes = clienteNeg.listar();
        List<Cliente> filtrados = new ArrayList<>();

        // Demás filtros
        String dni = request.getParameter("txtDni");
        String cuil = request.getParameter("txtCuil");
        String nombre = request.getParameter("txtNombre");
        String apellido = request.getParameter("txtApellido");
        String sexo = request.getParameter("ddlSexo");
        String localidadFiltro = request.getParameter("ddlLocalidadFiltro");
        String fechaNac = request.getParameter("txtFechaDeNacimiento");

        if ((dni != null && !dni.isEmpty()) || (cuil != null && !cuil.isEmpty()) ||
            (nombre != null && !nombre.isEmpty()) || (apellido != null && !apellido.isEmpty()) ||
            (sexo != null && !sexo.isEmpty()) || (paisFiltro != null && !paisFiltro.isEmpty()) ||
            (provinciaFiltro != null && !provinciaFiltro.isEmpty()) || (localidadFiltro != null && !localidadFiltro.isEmpty()) ||
            (fechaNac != null && !fechaNac.isEmpty())) {

            for (Cliente c : listaClientes) {
                boolean coincide = true;
                if (dni != null && !dni.isEmpty() && !c.getDNI().contains(dni)) coincide = false;
                if (cuil != null && !cuil.isEmpty() && !c.getCUIL().contains(cuil)) coincide = false;
                if (nombre != null && !nombre.isEmpty() && !c.getNombre().toLowerCase().contains(nombre.toLowerCase())) coincide = false;
                if (apellido != null && !apellido.isEmpty() && !c.getApellido().toLowerCase().contains(apellido.toLowerCase())) coincide = false;
                if (sexo != null && !sexo.isEmpty() && !c.getSexo().equalsIgnoreCase(sexo)) coincide = false;
                if (paisFiltro != null && !paisFiltro.isEmpty() && c.getPais() != null && !c.getPais().getNombre().equalsIgnoreCase(paisFiltro)) coincide = false;
                if (provinciaFiltro != null && !provinciaFiltro.isEmpty() && c.getProvincia() != null && !c.getProvincia().getNombre().equalsIgnoreCase(provinciaFiltro)) coincide = false;
                if (localidadFiltro != null && !localidadFiltro.isEmpty() && c.getLocalidad() != null && !c.getLocalidad().getNombre().equalsIgnoreCase(localidadFiltro)) coincide = false;
                if (fechaNac != null && !fechaNac.isEmpty()) {
                    try {
                        Date fechaParam = Date.valueOf(fechaNac);
                        if (!c.getFechaNacimiento().equals(fechaParam)) coincide = false;
                    } catch (IllegalArgumentException e) {
                    }
                }
                if (coincide) filtrados.add(c);
            }
        } else {
            filtrados = listaClientes;
        }

        request.setAttribute("listaClientes", filtrados);

        // Enviar
        RequestDispatcher rd = request.getRequestDispatcher("/abmlClientes.jsp");
        rd.forward(request, response);
    }
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		atributoListaPaises(request);
		atributoListaPaisesFiltro(request);
		// carga las provincias segun pais en alta y setea la provincia seleccionada
		atributoListaProvinciasAlta(request);
		atributoListaProvinciasFiltro(request);
		// obtiene lista de localidades dependiendo del pais y provincia seleccionado en alta
		atributoListaLocalidadesAlta(request);
		atributoListaLocalidadesFiltro(request);
		
		int filas = 0;
		ClienteNegocioImpl clienteNeg = new ClienteNegocioImpl();
		Cliente c = new Cliente();
		Pais p = new Pais();
		PaisNegocioImpl pNeg = new PaisNegocioImpl();
		
		//RECUPERAR LOS CAMPOS LLENOS CUANDO SE RESETEA LA PAG
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
		
		List<Cliente> listaClientes = clienteNeg.listar();
		
		PaisNegocioImpl paisNeg = new PaisNegocioImpl();
		ProvinciaNegocioImpl provNeg = new ProvinciaNegocioImpl();
		LocalidadNegocioImpl locNeg = new LocalidadNegocioImpl();
		
	
		    
		    //REENVIAR LOS CAMPOS LLENOS CUANDO SE RESETEA LA PAG
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
		   
		    request.setAttribute("listaClientes", listaClientes);

		
		//Si se hizo click en agregar
		if(request.getParameter("btnAgregarCliente") != null) {
		    //creamos un booleano en false y un mensaje de error vacio para ir llenando
		    boolean hayErrores = false;
		    String mensajeError = "";
			
			//obtenemos todo lo del form 
			String DNI = request.getParameter("txtDni");
			String CUIL = request.getParameter("txtCuil");
			String nombre = request.getParameter("txtNombre");
			String apellido = request.getParameter("txtApellido");
			String sexo = request.getParameter("ddlSexo");
			
			String nomNac = request.getParameter("ddlNacionalidad");
			String nomPais = request.getParameter("ddlPais");
			Pais paisNac = new Pais(); //PARA NACIONALIDAD
			Pais paisRe = new Pais(); //PARA PAIS DE RESIDENCIA
			paisNac = pNeg.obtenerPaisxNacionalidad(nomNac);
			paisRe = pNeg.obtenerPaisxNombre(nomPais);
			
			String nomProv = request.getParameter("ddlProvincia");
			Provincia prov = new Provincia();
			prov = provNeg.obtenerProvinciaPorNombre(nomProv);
			
			String nomLoc = request.getParameter("ddlLocalidad");
			Localidad loc = new Localidad();
			loc = locNeg.obtenerLocalidadPorNombre(nomLoc);
			
			String domicilio = request.getParameter("txtDomicilio");
			String fechaNac = request.getParameter("txtFechaDeNacimiento");
	    	Date fechaDeNac = null;
	    	
	    	if (fechaNac != null && !fechaNac.trim().isEmpty()) {
	    	    try {
	    	        fechaDeNac = Date.valueOf(fechaNac);
	    	    } catch (IllegalArgumentException e) {
	    	        mensajeError += "* La fecha de nacimiento no tiene un formato válido.<br>";
	    	        hayErrores = true;
	    	    }
	    	} 
	    	else {
	    	    mensajeError += "* Debe seleccionar una fecha de nacimiento.<br>";
	    	    hayErrores = true;
	    	}
	    	
			String mail = request.getParameter("txtCorreo");
			String telefono  = request.getParameter("txtTelefono");
			String nick  = request.getParameter("txtUsuario");
			String contrasenia  = request.getParameter("txtContrasenia");
			String reContrasenia = request.getParameter("txtContrasenia2");

		    
		    if(DNI == null || DNI.trim().isEmpty()) {
		        mensajeError += "* El DNI es obligatorio.<br>";
		        hayErrores = true;
		    }
		    
		    if(DNI != null && NaN(DNI.trim())){
		        mensajeError += "* El DNI debe estar formado solo por números.<br>";
		        hayErrores = true;
		    }

		    if(CUIL == null || CUIL.trim().isEmpty()) {
		        mensajeError += "* El CUIL es obligatorio.<br>";
		        hayErrores = true;
		    }
		    
		    if(CUIL != null && NaN(CUIL.trim())){
		        mensajeError += "* El CUIL debe estar formado solo por números.<br>";
		        hayErrores = true;
		    }

		    if(nombre == null || nombre.trim().isEmpty()) {
		        mensajeError += "* El nombre es obligatorio.<br>";
		        hayErrores = true;
		    }
		    
		    if(nombre != null && !NaN(nombre.trim())){
		        mensajeError += "* El nombre debe estar formado solo por letras.<br>";
		        hayErrores = true;
		    }

		    if(apellido == null || apellido.trim().isEmpty()) {
		        mensajeError += "* El apellido es obligatorio.<br>";
		        hayErrores = true;
		    }
		    
		    if(apellido != null && !NaN(apellido.trim())){
		        mensajeError += "* El apellido debe estar formado solo por letras.<br>";
		        hayErrores = true;
		    }
		    
		    if(sexo == null || sexo.trim().isEmpty()) {
		    	mensajeError += "* Debe seleccionar un sexo.<br>";
		    	hayErrores = true;
		    }
		    
		    if(nomNac == null || nomNac.trim().isEmpty()) {
		        mensajeError += "* Debe seleccionar una nacionalidad.<br>";
		        hayErrores = true;
		    }
		    
		    if(nomProv == null || nomProv.trim().isEmpty()) {
		        mensajeError += "* Debe seleccionar una provincia.<br>";
		        hayErrores = true;
		    }
		    
		    if(nomLoc == null || nomLoc.trim().isEmpty()) {
		        mensajeError += "* Debe seleccionar una localidad.<br>";
		        hayErrores = true;
		    }
		    
		    if(mail == null || mail.trim().isEmpty()) {
		        mensajeError += "* El mail es obligatorio.<br>";
		        hayErrores = true;
		    }
		    
		    if(telefono == null || telefono.trim().isEmpty()) {
		        mensajeError += "* El telefono es obligatorio.<br>";
		        hayErrores = true;
		    }
		    
		    if(nick == null || nick.trim().isEmpty()) {
		        mensajeError += "* El usuario es obligatorio.<br>";
		        hayErrores = true;
		    }
		    
		    if(contrasenia == null || contrasenia.trim().isEmpty()) {
		        mensajeError += "* La contraseña es obligatoria.<br>";
		        hayErrores = true;
		    }
		    
		    if(reContrasenia == null || reContrasenia.trim().isEmpty()) {
		        mensajeError += "* Repetir la contraseña es obligatorio.<br>";
		        hayErrores = true;
		    }
		    
		    if(reContrasenia != null && !reContrasenia.trim().isEmpty() && contrasenia != null && !contrasenia.trim().isEmpty()) {
			    if (!contrasenia.equals(reContrasenia)) {
			        mensajeError += "* Las contraseñas no coinciden.<br>";
			        hayErrores = true;
			    }
		    }

		    if(hayErrores) {
		    	//SI HAY ERRORES SETEAMOS EL MENSAJE DE ERROR A LA REQUEST
		    	request.setAttribute("mensajeError", mensajeError);
		    	request.setAttribute("hayError", hayErrores);
		    }else {
		    	//SI NO HAY ERRORES...INSERTAR
		    	
		    	Cliente cliente = new Cliente();
		    	Usuario user = new Usuario();
		    	
		    	cliente.setDNI(DNI);
		    	cliente.setCUIL(CUIL);
		    	cliente.setNombre(nombre);
		    	cliente.setApellido(apellido);
		    	cliente.setSexo(sexo);
		    	cliente.setNacionalidad(paisNac);
		    	cliente.setPais(paisRe);
		    	cliente.setProvincia(prov);
		    	cliente.setLocalidad(loc);
		    	cliente.setDomicilio(domicilio);
		    	cliente.setFechaNacimiento(fechaDeNac);
		    	cliente.setEmail(mail);
		    	cliente.setTelefono(telefono);
		    	cliente.setBaja(0);
		    	user.setNickUsuario(nick);
		    	user.setContraseñaUsuario(contrasenia);
		    	user.setTipoUsuario("CLIENTE");
		    	
		    	//obtenemos las filas
		    	filas = clienteNeg.agregar(cliente, user);	
		    	
		    	//SETEAMOS LAS FILAS A LA REQUEST
		    	request.setAttribute("listaClientes", listaClientes);
		    	request.setAttribute("cantFilas", filas);
		    }
		    
		}
		
		if(request.getParameter("btnEliminarCliente") != null) {
		    String DNI = request.getParameter("listDNI");

		    int filasE = clienteNeg.eliminar(DNI);

		    request.setAttribute("cantFilasE", filasE);
		    request.setAttribute("listaClientes", clienteNeg.listar());
		}
		
		if(request.getParameter("btnAgregarCliente") != null) {
			
		}
		if(request.getParameter("btnModificar")!=null) {
			
			boolean modificado = modificarCliente(request);
			request.setAttribute("resultadoModificar", modificado);
			request.setAttribute("listaClientes", clienteNeg.listar());
			
		}
		//DISPATCHER
		RequestDispatcher rd = request.getRequestDispatcher("/abmlClientes.jsp");
		
		System.out.println(request.getAttribute("hayError"));
		System.out.println("Redirigiendo a abmlClientes.jsp con errores: " + request.getAttribute("mensajeError"));
		
		//MANDAMOS LA REQUEST
		rd.forward(request, response);
		
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
