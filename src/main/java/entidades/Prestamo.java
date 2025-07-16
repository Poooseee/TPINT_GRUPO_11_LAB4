package entidades;

import java.sql.Date;

public class Prestamo {
private int idPrestamo;
private String dni;
private Date fecha;
private float importePagar;
private float importePedido;
private int plazoPagos;
private float montoPorMes;
private String estado;
private int cuenta;

public Prestamo() {
	
}

public int getIdPrestamo() {
	return idPrestamo;
}
public void setIdPrestamo(int idPrestamo) {
	this.idPrestamo = idPrestamo;
}
public String getDni() {
	return dni;
}
public void setDni(String dni) {
	this.dni = dni;
}
public Date getFecha() {
	return fecha;
}
public void setFecha(Date fechaPrestamo) {
	this.fecha = fechaPrestamo;
}
public float getImportePagar() {
	return importePagar;
}
public void setImportePagar(float importePagar) {
	this.importePagar = importePagar;
}
public float getImportePedido() {
	return importePedido;
}
public void setImportePedido(float importePedido) {
	this.importePedido = importePedido;
}
public int getPlazoPagos() {
	return plazoPagos;
}
public void setPlazoPagos(int plazoPagos) {
	this.plazoPagos = plazoPagos;
}
public float getMontoPorMes() {
	return montoPorMes;
}
public void setMontoPorMes(float montoPorMes) {
	this.montoPorMes = montoPorMes;
}
public String getEstado() {
	return estado;
}
public void setEstado(String estado) {
	this.estado = estado;
}
public int getCuenta() {
	return cuenta;
}
public void setCuenta(int cuenta) {
	this.cuenta = cuenta;
}

@Override
public String toString() {
	return "Prestamo [idPrestamo=" + idPrestamo + ", dni=" + dni + ", fecha=" + fecha + ", importePagar=" + importePagar
			+ ", importePedido=" + importePedido + ", plazoPagos=" + plazoPagos + ", montoPorMes=" + montoPorMes
			+ ", estado=" + estado + ", cuenta=" + cuenta + "]";
}
}
