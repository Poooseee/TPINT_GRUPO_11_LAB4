package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Localidad;
import entidades.Pais;
import entidades.Provincia;
import negocioImpl.LocalidadNegocioImpl;
import negocioImpl.PaisNegocioImpl;
import negocioImpl.ProvinciaNegocioImpl;

/**
 * Servlet implementation class ServletClientes
 */
@WebServlet("/ServletClientes")
public class ServletClientes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ServletClientes() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Al entrar, cargar:
		
		//paises
		PaisNegocioImpl negPais = new PaisNegocioImpl(); 
		ArrayList<Pais> paises = negPais.obtenerPaises();
		
		
		//nacionalidades
		ArrayList<Pais> nacionalidades = negPais.obtenerNacionalidades();
		
		//sexos
		
		//poner todo en la request y mandarla
		request.setAttribute("paises", paises);
		request.setAttribute("nacionalidades", nacionalidades);
		//request.setAttribute("sexos", sexos);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/abmlClientes.jsp");
		dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Cargar siempre la lista de países
        PaisNegocioImpl paisNeg = new PaisNegocioImpl();
        ArrayList<Pais> paises = paisNeg.obtenerPaises();
        request.setAttribute("paises", paises);
        
        // Recuperar los valores seleccionados en cada dropdown
        String idPaisDDL = request.getParameter("ddlPaises");
        String idProvinciaDDL = request.getParameter("ddlProvincias");
        String idLocalidadDDL = request.getParameter("ddlLocalidades");
        String btnCrear = request.getParameter("btnCrear");

        // Si se seleccionó un país
        if(idPaisDDL != null && !idPaisDDL.isEmpty()){
            int idPais = Integer.parseInt(idPaisDDL);
            request.setAttribute("paisSeleccionado", idPais);
            
            // Cargar provincias según el país seleccionado
            ProvinciaNegocioImpl provNeg = new ProvinciaNegocioImpl();
            ArrayList<Provincia> provincias = provNeg.obtenerProvincias(idPais);
            request.setAttribute("provincias", provincias);
        } else {
            // Si no se seleccionó país, se limpian las provincias y localidades
            request.setAttribute("provincias", null);
            request.setAttribute("localidades", null);
        }
        
        // Si se seleccionó una provincia, cargar localidades para esa provincia
        if(idProvinciaDDL != null && !idProvinciaDDL.isEmpty()){
        	int idPais = Integer.parseInt(idPaisDDL);
            int idProvincia = Integer.parseInt(idProvinciaDDL);
            request.setAttribute("provinciaSeleccionada", idProvincia);
            
            LocalidadNegocioImpl locNeg = new LocalidadNegocioImpl();
            ArrayList<Localidad> localidades = locNeg.obtenerLocalidades(idProvincia, idPais);
            request.setAttribute("localidades", localidades);
        } else {
            request.setAttribute("localidades", null);
        }
        
        // Si se presionó el botón de Agregar Cliente, se asume que el formulario está completo y se procesa la alta
        if(btnCrear != null) {
            // Aquí deberías validar y guardar el cliente según los demás campos del formulario.
            // Por ejemplo:
            // String nombre = request.getParameter("nombre");
            // ... guardar la información del cliente ...
            request.setAttribute("mensaje", "Cliente agregado exitosamente.");
        }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/altaCliente.jsp");
        dispatcher.forward(request, response);
	}

}
