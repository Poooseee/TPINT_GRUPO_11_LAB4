package entidades;

public class Transferencia {
	
	private int numeroCuentaOrigen;
	private int numeroCuentaDestino;
	private float importe;

	public int getNumeroCuentaOrigen() {
		return numeroCuentaOrigen;
	}
	public void setNumeroCuentaOrigen(int numeroCuentaOrigen) {
		this.numeroCuentaOrigen = numeroCuentaOrigen;
	}
	public int getNumeroCuentaDestino() {
		return numeroCuentaDestino;
	}
	public void setNumeroCuentaDestino(int numeroCuentaDestino) {
		this.numeroCuentaDestino = numeroCuentaDestino;
	}
	public float getImporte() {
		return importe;
	}
	public void setImporte(float importe) {
		this.importe = importe;
	}
	public Transferencia(int numeroCuentaOrigen, int numeroCuentaDestino, float importe) {
		super();
		this.numeroCuentaOrigen = numeroCuentaOrigen;
		this.numeroCuentaDestino = numeroCuentaDestino;
		this.importe = importe;
	}
	
	public Transferencia(){};
}
