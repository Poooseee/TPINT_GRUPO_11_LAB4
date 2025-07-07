package entidades;

public class PrestamosReporte {
	public int aprobado;
	public int rechazado;
	public int pendiente;
	public int getAprobado() {
		return aprobado;
	}
	public void setAprobado(int aprobado) {
		this.aprobado = aprobado;
	}
	public int getRechazado() {
		return rechazado;
	}
	public void setRechazado(int rechazado) {
		this.rechazado = rechazado;
	}
	public int getPendiente() {
		return pendiente;
	}
	public void setPendiente(int pendiente) {
		this.pendiente = pendiente;
	}
	public PrestamosReporte(int aprobado, int rechazado, int pendiente) {
		super();
		this.aprobado = aprobado;
		this.rechazado = rechazado;
		this.pendiente = pendiente;
	}
	
	public PrestamosReporte() {};
}
