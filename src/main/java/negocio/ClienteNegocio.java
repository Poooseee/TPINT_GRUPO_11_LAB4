package negocio;

import java.util.List;
import entidades.Cliente;
import entidades.Usuario;

public interface ClienteNegocio {
    public List<Cliente> listar(Cliente datosFiltracion);
    public int agregar(Cliente cliente, Usuario user);
    public boolean modificar(Cliente cliente);
    public int eliminar(String dni);
    public boolean existe(String dni);
	public Cliente obtenerPorUsuarioNick(String nickUsuario);
	public Cliente obtenerClienteCompleto(String dni);
}
