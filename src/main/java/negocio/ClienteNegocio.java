package negocio;

import java.util.List;
import entidades.Cliente;

public interface ClienteNegocio {
    public List<Cliente> listar();
    public boolean agregar(Cliente cliente);
    public boolean modificar(Cliente cliente);
    public boolean eliminar(String dni);
    public boolean existe(String dni);
}