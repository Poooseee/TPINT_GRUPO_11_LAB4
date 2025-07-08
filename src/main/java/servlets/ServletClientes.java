package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.ClienteNegocio;
import negocioImpl.ClienteNegocioImpl;
import entidades.Cliente;

@WebServlet("/ServletClientes")
public class ServletClientes extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ClienteNegocio clienteNegocio = new ClienteNegocioImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("✔ Entró al doGet de ServletCuentas");
		List<Cliente> clientes = clienteNegocio.listar();
		request.setAttribute("clientes", clientes);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/abmlClientes.jsp");
		dispatcher.forward(request, response);
	}
}