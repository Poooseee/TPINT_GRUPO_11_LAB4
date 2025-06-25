package datos;

import java.util.List;
import entidades.Cliente;

public interface ClienteDao {
    public List<Cliente> listar();
    public boolean agregar(Cliente cliente);
    public boolean modificar(Cliente cliente);
    public boolean eliminar(String dni);
    public boolean existe(String dni);
}