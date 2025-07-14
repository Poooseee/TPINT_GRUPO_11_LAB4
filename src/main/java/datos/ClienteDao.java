package datos;
import java.util.List;
import entidades.Cliente;
import entidades.Usuario;

public interface ClienteDao {
    public List<Cliente> listar(Cliente datosFiltracion);
    public int agregar(Cliente cliente, Usuario user);
    public boolean modificar(Cliente cliente);
    public int eliminar(String dni);
    public boolean existe(String dni);
	Cliente obtenerPorUsuarioNick(String nick);
	Cliente obtenerClienteCompleto(String dni);
}
