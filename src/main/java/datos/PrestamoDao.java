package datos;

import java.util.List;

import entidades.Cuota;
import entidades.Prestamo;

public interface PrestamoDao {
	public List<Prestamo> get(String estado,String dni,String numeroCuenta);
	public boolean cambiarEstado(String estado,int id);	
	public Prestamo obtenerPrestamo(int id);
	public List<Cuota>obtenerCuotasPorPrestamo(int idPrestamo);
	public int insertarPrestamo(Prestamo prestamo);
}
