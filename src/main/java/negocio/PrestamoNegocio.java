package negocio;


import java.util.List;

import entidades.Cuota;
import entidades.Movimiento;
import entidades.Prestamo;

public interface PrestamoNegocio {
	List<Prestamo> get(String estado,String dni,String numeroCuenta);
	public boolean cambiarEstado(String estado,int id,Movimiento movimiento, boolean aceptado, Float importePedido);	
	public Prestamo obtenerPrestamo(int id);
	public List<Cuota>obtenerCuotasPorPrestamo(int idPrestamo);
	public boolean insertarPrestamo(Prestamo prestamo);
}
