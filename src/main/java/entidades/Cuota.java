package entidades;

public class Cuota {
	private int idPrestamo;
	private int numeroCuota;
	private boolean abonada;
	public int getIdPrestamo() {
		return idPrestamo;
	}
	public void setIdPrestamo(int idPrestamo) {
		this.idPrestamo = idPrestamo;
	}
	public int getNumeroCuota() {
		return numeroCuota;
	}
	public void setNumeroCuota(int numeroCuota) {
		this.numeroCuota = numeroCuota;
	}
	public boolean isAbonada() {
		return abonada;
	}
	public void setAbonada(boolean abonada) {
		this.abonada = abonada;
	}
	public Cuota(int idPrestamo, int numeroCuota, boolean abonada) {
		super();
		this.idPrestamo = idPrestamo;
		this.numeroCuota = numeroCuota;
		this.abonada = abonada;
	}
	public Cuota() {}
	@Override
	public String toString() {
		return "Cuota [idPrestamo=" + idPrestamo + ", numeroCuota=" + numeroCuota + ", abonada=" + abonada + "]";
	};
	
}
