package negocioImpl;

import java.util.List;
import datosImpl.ClienteDaoImpl;
import entidades.Cliente;
import entidades.Usuario;
import negocio.ClienteNegocio;

public class ClienteNegocioImpl implements ClienteNegocio {

    private ClienteDaoImpl clienteDao = new ClienteDaoImpl();

    @Override
    public List<Cliente> listar(Cliente datosFiltracion) {
        return clienteDao.listar(datosFiltracion);
    }

    @Override
    public int agregar(Cliente cliente, Usuario user) {
        return clienteDao.agregar(cliente, user);
    }

    @Override
    public boolean modificar(Cliente cliente) {
        return clienteDao.modificar(cliente);
    }

    @Override
    public int eliminar(String dni) {
        return clienteDao.eliminar(dni);
    }

	@Override
	public boolean existe(String dni) {
		boolean existe = clienteDao.existe(dni);
		return existe;
	}

	@Override
    public Cliente obtenerPorUsuarioNick(String nick) {
        return clienteDao.obtenerPorUsuarioNick(nick);
    }

	@Override
    public Cliente obtenerClienteCompleto(String dni) {
        try {
            // Validación del DNI
            if(dni == null || dni.trim().isEmpty()) {
                throw new IllegalArgumentException("El DNI no puede estar vacío");
            }
            
            System.out.println("Obteniendo cliente completo para DNI: " + dni);
            Cliente cliente = clienteDao.obtenerClienteCompleto(dni);
            
            if(cliente == null) {
                System.out.println("No se encontró cliente con DNI: " + dni);
            } else {
                System.out.println("Cliente encontrado: " + cliente.getNombre() + " " + cliente.getApellido());
            }
            
            return cliente;
        } catch (Exception e) {
            System.out.println("Error en ClienteNegocioImpl.obtenerClienteCompleto: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
	
}
