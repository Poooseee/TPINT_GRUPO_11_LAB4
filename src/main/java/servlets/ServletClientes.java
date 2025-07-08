package servlets;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import entidades.Cliente;
import entidades.Cuenta;
import negocio.CuentaNegocio;
import negocioImpl.CuentaNegocioImpl;
import negocio.ClienteNegocio;
import negocioImpl.ClienteNegocioImpl;

@WebServlet(urlPatterns = {"/ServletClientes", "/ServletClientes/perfil"})
public class ServletClientes extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private CuentaNegocio cuentaNegocio = new CuentaNegocioImpl();
    private ClienteNegocio clienteNegocio = new ClienteNegocioImpl();
    
    public ServletClientes() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        System.out.println("\n--- INICIO DOGET SERVLET CLIENTES ---");
        System.out.println("URL solicitada: " + request.getRequestURL().toString());
        
        try {
            // 1. Verificar sesión
            HttpSession session = request.getSession(false);
            
            if(session == null || session.getAttribute("clienteLogueado") == null) {
                System.out.println("⚠ No hay sesión activa o cliente no logueado");
                response.sendRedirect(request.getContextPath() + "/login.jsp");
                return;
            }

            // 2. Obtener cliente de la sesión
            Cliente clienteSesion = (Cliente) session.getAttribute("clienteLogueado");
            System.out.println("\n📌 Cliente en sesión:");
            System.out.println("DNI: " + clienteSesion.getDNI());
            System.out.println("Nombre: " + clienteSesion.getNombre());
            System.out.println("Apellido: " + clienteSesion.getApellido());

            String path = request.getRequestURI();
            System.out.println("\n🔗 Path solicitado: " + path);

            // 3. Lógica para perfil
            if(path.endsWith("/perfil")) {
                System.out.println("\n🔄 Solicitando perfil completo...");
                
                // 4. Obtener datos completos del cliente
                System.out.println("🔍 Buscando en BD cliente con DNI: " + clienteSesion.getDNI());
                Cliente clienteCompleto = clienteNegocio.obtenerClienteCompleto(clienteSesion.getDNI());
                
                System.out.println("\n📊 Resultado de obtenerClienteCompleto():");
                System.out.println(clienteCompleto != null ? "✅ Cliente encontrado" : "❌ Cliente NULL");
                
                if(clienteCompleto != null) {
                    System.out.println("\n🧾 Datos obtenidos:");
                    System.out.println("Nombre: " + clienteCompleto.getNombre());
                    System.out.println("Apellido: " + clienteCompleto.getApellido());
                    System.out.println("DNI: " + clienteCompleto.getDNI());
                    System.out.println("Email: " + clienteCompleto.getEmail());
                    System.out.println("Teléfono: " + clienteCompleto.getTelefono());
                    
                    if(clienteCompleto.getNacionalidad() != null) {
                        System.out.println("Nacionalidad: " + clienteCompleto.getNacionalidad().getNombre());
                    }
                    
                    if(clienteCompleto.getPais() != null) {
                        System.out.println("País: " + clienteCompleto.getPais().getNombre());
                    }
                }

                // 5. Pasar datos al JSP
                request.setAttribute("cliente", clienteCompleto);
                System.out.println("\n📤 Atributo 'cliente' establecido en request");
                
                System.out.println("🔄 Redirigiendo a perfilCliente.jsp");
                request.getRequestDispatcher("/perfilCliente.jsp").forward(request, response);
                
            } else {
                // Lógica para el menú principal
                System.out.println("\n🏠 Solicitando menú principal...");
                Cuenta cuenta = cuentaNegocio.obtenerCuentaPorDni(clienteSesion.getDNI());
                
                System.out.println("💳 Datos de cuenta:");
                System.out.println(cuenta != null ? "CBU: " + cuenta.getCbu() : "Cuenta NULL");
                
                request.setAttribute("nombreCliente", clienteSesion.getNombre());
                request.setAttribute("apellidoCliente", clienteSesion.getApellido());
                request.setAttribute("saldoDisponible", cuenta != null ? cuenta.getSaldo() : 0.0);
                
                System.out.println("\n📤 Atributos establecidos para menú:");
                System.out.println("Nombre: " + clienteSesion.getNombre());
                System.out.println("Apellido: " + clienteSesion.getApellido());
                System.out.println("Saldo: " + (cuenta != null ? cuenta.getSaldo() : "0.0"));
                
                System.out.println("🔄 Redirigiendo a menuCliente.jsp");
                request.getRequestDispatcher("/menuCliente.jsp").forward(request, response);
            }
            
        } catch (Exception e) {
            System.out.println("\n❌ ERROR en doGet: " + e.getMessage());
            e.printStackTrace();
            request.setAttribute("error", "Ocurrió un error: " + e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
        
        System.out.println("--- FIN DOGET SERVLET CLIENTES ---\n");
    }
 

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response);
    }
}