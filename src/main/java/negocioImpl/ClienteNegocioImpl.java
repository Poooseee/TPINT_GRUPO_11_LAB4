package negocioImpl;

import java.util.List;
import datos.ClienteDao;
import datosImpl.ClienteDaoImpl;
import entidades.Cliente;
import negocio.ClienteNegocio;

public class ClienteNegocioImpl implements ClienteNegocio {

    private ClienteDao clienteDao = new ClienteDaoImpl();

    @Override
    public List<Cliente> listar() {
        return clienteDao.listar();
    }

    @Override
    public boolean agregar(Cliente cliente) {
        return clienteDao.agregar(cliente);
    }

    @Override
    public boolean modificar(Cliente cliente) {
        return clienteDao.modificar(cliente);
    }

    @Override
    public boolean eliminar(String dni) {
        return clienteDao.eliminar(dni);
    }

	

	@Override
	public boolean existe(String dni) {
		return clienteDao.existe(dni);
	}
}