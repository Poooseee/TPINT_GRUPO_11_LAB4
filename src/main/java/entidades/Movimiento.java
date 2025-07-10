package entidades;

import java.sql.Date;

public class Movimiento {

	private int DniMovimiento;
	private int numeroCuenta;
	private Date fecha;
	private String detalle;
	private float importe;
	private TipoMovimiento tipo;

	public int getDniMovimiento() {
		return DniMovimiento;
	}
	public void setDniMovimiento(int dniMovimiento) {
		DniMovimiento = dniMovimiento;
	}
	public int getNumeroCuenta() {
		return numeroCuenta;
	}
	public void setNumeroCuenta(int numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	public float getImporte() {
		return importe;
	}
	public void setImporte(float importe) {
		this.importe = importe;
	}
	public TipoMovimiento getTipo() {
		return tipo;
	}
	public void setTipo(TipoMovimiento tipo) {
		this.tipo = tipo;
	}
	public Movimiento(int dniMovimiento, int numeroCuenta, Date fecha, String detalle, float importe,
			TipoMovimiento tipo) {
		super();

		DniMovimiento = dniMovimiento;
		this.numeroCuenta = numeroCuenta;
		this.fecha = fecha;
		this.detalle = detalle;
		this.importe = importe;
		this.tipo = tipo;
	}
	
	public Movimiento() {};
}
