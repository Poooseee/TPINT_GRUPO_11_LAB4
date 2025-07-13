package datos;

import java.util.List;

import entidades.Movimiento;
import entidades.Prestamo;

public interface PrestamoDao {
public List<Prestamo> get(String estado,String dni);
public boolean cambiarEstado(String estado,int id);	
}
