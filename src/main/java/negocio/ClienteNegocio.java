package negocio;

import java.util.List;
import entidades.Cliente;

public interface ClienteNegocio {
    public List<Cliente> listar();
    public int agregar(Cliente cliente);
    public boolean modificar(Cliente cliente);
    public int eliminar(String dni);
    public boolean existe(String dni);
}
