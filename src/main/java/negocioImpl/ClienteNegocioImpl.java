package negocioImpl;

import java.util.List;
import datosImpl.ClienteDaoImpl;
import entidades.Cliente;
import entidades.Usuario;
import negocio.ClienteNegocio;

public class ClienteNegocioImpl implements ClienteNegocio {

    private ClienteDaoImpl clienteDao = new ClienteDaoImpl();

    @Override
    public List<Cliente> listar() {
        return clienteDao.listar();
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

	
	
}
