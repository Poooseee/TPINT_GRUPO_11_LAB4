package servlets;

import java.io.IOException;
import java.sql.Date;
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
import entidades.Usuario;
import negocioImpl.ClienteNegocioImpl;
import negocioImpl.LocalidadNegocioImpl;
import negocioImpl.PaisNegocioImpl;
import negocioImpl.ProvinciaNegocioImpl;

@WebServlet("/abmlClientesServlet")
public class abmlClientesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public abmlClientesServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PaisNegocioImpl paisNeg = new PaisNegocioImpl();
		ClienteNegocioImpl cliNeg = new ClienteNegocioImpl();
	
	    List<Pais> listaPaises = paisNeg.obtenerPaises(); 
	    List<Cliente> listaClientes = cliNeg.listar();

	    request.setAttribute("listaClientes", listaClientes);
	    request.setAttribute("listaPaises", listaPaises);
	    
	    System.out.println("AAAAA"+ listaPaises);
		//String idPais = request.getParameter("ddlNacionalidad"); 
		//System.out.println(idPais);

	    RequestDispatcher rd = request.getRequestDispatcher("/abmlClientes.jsp");
	    rd.forward(request, response);
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int filas = 0;
		ClienteNegocioImpl clienteNeg = new ClienteNegocioImpl();
		String nombrePais = request.getParameter("ddlPais"); 
		String nombreProv = request.getParameter("ddlProvincia");
		String nombreLocalidad = request.getParameter("ddlLocalidad");
		PaisNegocioImpl paisNeg = new PaisNegocioImpl();
		ProvinciaNegocioImpl provNeg = new ProvinciaNegocioImpl();
		LocalidadNegocioImpl locNeg = new LocalidadNegocioImpl();
		
		if (nombrePais != null && !nombrePais.equals("") && request.getParameter("btnAgregarCliente") == null) {
		    List<Pais> listaPaises = paisNeg.obtenerPaises();
		    List<Provincia> listaProvincias = provNeg.obtenerProvinciasPorPais(nombrePais);

		    request.setAttribute("listaPaises", listaPaises);
		    request.setAttribute("listaProvincias", listaProvincias);
		    request.setAttribute("paisSeleccionado", nombrePais);

		    if (nombreProv != null && !nombreProv.equals("")) {
		    	List<Localidad> listaLocalidades = locNeg.obtenerLocalidadesXProvXPais(nombrePais, nombreProv);
		    	request.setAttribute("listaLocalidades", listaLocalidades);
		        request.setAttribute("provinciaSeleccionada", nombreProv);
		    }

		    if(nombreLocalidad != null && !nombreLocalidad.equals("")) {
		    	request.setAttribute("localidadSeleccionada", nombreLocalidad);
		    }
		    
		    RequestDispatcher rd = request.getRequestDispatcher("/abmlClientes.jsp");
		    rd.forward(request, response);
		    return;
		}
		
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
			String nacionalidad = request.getParameter("ddlNacionalidad");
			Pais pais = new Pais();
			pais.setNacionalidad(nacionalidad);
			String provincia = request.getParameter("ddlProvincia");
			Provincia prov = new Provincia();
			prov.setNombre(provincia);
			String localidad = request.getParameter("ddlLocalidad");
			Localidad loc = new Localidad();
			loc.setNombre(localidad);
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
			String contraseña  = request.getParameter("txtContraseña");
			String reContraseña = request.getParameter("txtContraseña2");


		    
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
		    
		    if(nacionalidad == null || nacionalidad.trim().isEmpty()) {
		        mensajeError += "* Debe seleccionar una nacionalidad.<br>";
		        hayErrores = true;
		    }
		    
		    if(provincia == null || provincia.trim().isEmpty()) {
		        mensajeError += "* Debe seleccionar una provincia.<br>";
		        hayErrores = true;
		    }
		    
		    if(localidad == null || localidad.trim().isEmpty()) {
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
		    
		    if(contraseña == null || contraseña.trim().isEmpty()) {
		        mensajeError += "* La contraseña es obligatoria.<br>";
		        hayErrores = true;
		    }
		    
		    if(reContraseña == null || reContraseña.trim().isEmpty()) {
		        mensajeError += "* Repetir la contraseña es obligatorio.<br>";
		        hayErrores = true;
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
		    	cliente.setNacionalidad(pais);
		    	cliente.setProvincia(prov);
		    	cliente.setLocalidad(loc);
		    	cliente.setDomicilio(domicilio);
		    	cliente.setFechaNacimiento(fechaDeNac);
		    	cliente.setEmail(mail);
		    	cliente.setTelefono(telefono);
		    	
		    	user.setNickUsuario(nick);
		    	user.setContraseñaUsuario(contraseña);
		    	
		    	//obtenemos las filas
		    	filas = clienteNeg.agregar(cliente);	
		    	
		    	//SETEAMOS LAS FILAS A LA REQUEST
		    	request.setAttribute("cantFilas", filas);
		    }
		    
		}
		
		if(request.getParameter("btnEliminarCliente") != null) {
		    String DNI = request.getParameter("listDNI");

		    int filasE = clienteNeg.eliminar(DNI);

		    request.setAttribute("cantFilasE", filasE);
		    request.setAttribute("listaClientes", clienteNeg.listar());
		}
		
		//DISPATCHER
		RequestDispatcher rd = request.getRequestDispatcher("/abmlClientes.jsp");
		
		System.out.println(request.getAttribute("hayError"));
		System.out.println("Redirigiendo a abmlClientes.jsp con errores: " + request.getAttribute("mensajeError"));
		
		//MANDAMOS LA REQUEST
		rd.forward(request, response);
		
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
