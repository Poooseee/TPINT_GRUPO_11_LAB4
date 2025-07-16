package negocio;


import java.util.List;

import datos.Cuota;
import entidades.Prestamo;

public interface PrestamoNegocio {
	List<Prestamo> get(String estado,String dni);
	public boolean cambiarEstado(String estado,int id);	
	public Prestamo obtenerPrestamo(int id);
	public List<Cuota>obtenerCuotasPorPrestamo(int idPrestamo);
}
