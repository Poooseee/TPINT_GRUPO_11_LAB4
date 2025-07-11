package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletPrestamos
 */
@WebServlet("/ServletPrestamos")
public class ServletPrestamos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ServletPrestamos() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/prestamosCliente.jsp");
    	dispatcher.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. SE DISPARA TERMINAS DE ESCRIBIR EL MONTO O CUANDO SELECCIONA LAS CUOTAS
		
		//Obtener el monto a pedir
		float montoAPedir = Float.parseFloat(request.getParameter("montoPedido"));
			
		
		//Obtener la cantidad de cuotas
		Integer cantidadCuotas = Integer.parseInt(request.getParameter("cuotas"));	
		
		// Calcular cuota mensual y monto total
		float valorCuota = calcularValorCuota(montoAPedir, cantidadCuotas);
		float montoTotal = valorCuota * cantidadCuotas;

		// Pasar los datos al JSP
		request.setAttribute("montoPedido", montoAPedir);
		request.setAttribute("cuotas", cantidadCuotas);
		request.setAttribute("valorCuota", valorCuota);
		request.setAttribute("montoTotal", montoTotal);
		
		System.out.println(montoAPedir);
		System.out.println(cantidadCuotas);
		System.out.println(valorCuota);
		System.out.println(montoTotal);
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/prestamosCliente.jsp");
    	dispatcher.forward(request, response);
	}
	
	public static float calcularValorCuota(float monto, int cuotas) {

		    double tasaMensual = 50.0 / 12.0 / 100.0;

		    double denominador = 1 - Math.pow(1 + tasaMensual, -cuotas);

		    double cuota = monto * tasaMensual / denominador;
		    
		    cuota = Math.round(cuota * 100.0) / 100.0;
		    
		    return (float) cuota;
		//La tasa de amortizacion francesa calcula
		//monto multiplicado por la tasa mensual, todo eso dividido por 1 - ((1+ tasa mensual) elevado a -cuotas)
	}

}
