package entidades;

import java.sql.Date;

public class CuotasReporte {
	private int idPrestamo;
	private int cuenta;
	private Date fecha;
	private float importePedido;
	private float importePagar;
	private int cuotas;
	private int cuotasPagas;
	private float montoMensual;
	public int getIdPrestamo() {
		return idPrestamo;
	}
	public void setIdPrestamo(int idPrestamo) {
		this.idPrestamo = idPrestamo;
	}
	public int getCuenta() {
		return cuenta;
	}
	public void setCuenta(int cuenta) {
		this.cuenta = cuenta;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public float getImportePedido() {
		return importePedido;
	}
	public void setImportePedido(float importePedido) {
		this.importePedido = importePedido;
	}
	public float getImportePagar() {
		return importePagar;
	}
	public void setImportePagar(float importePagar) {
		this.importePagar = importePagar;
	}
	public int getCuotas() {
		return cuotas;
	}
	public void setCuotas(int cuotas) {
		this.cuotas = cuotas;
	}
	public int getCuotasPagas() {
		return cuotasPagas;
	}
	public void setCuotasPagas(int cuotasPagas) {
		this.cuotasPagas = cuotasPagas;
	}
	public float getMontoMensual() {
		return montoMensual;
	}
	public void setMontoMensual(float montoMensual) {
		this.montoMensual = montoMensual;
	}
	public CuotasReporte(int idPrestamo, int cuenta, Date fecha, float importePedido, float importePagar, int cuotas,
			int cuotasPagas, float montoMensual) {
		super();
		this.idPrestamo = idPrestamo;
		this.cuenta = cuenta;
		this.fecha = fecha;
		this.importePedido = importePedido;
		this.importePagar = importePagar;
		this.cuotas = cuotas;
		this.cuotasPagas = cuotasPagas;
		this.montoMensual = montoMensual;
	}
	
	public CuotasReporte() {
		
	};
}
