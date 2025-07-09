package entidades;

public class Transferencia {
	private int idTransferencia;
	private int numeroCuentaOrigen;
	private int numeroCuentaDestino;
	private float importe;
	public int getIdTransferencia() {
		return idTransferencia;
	}
	public void setIdTransferencia(int idTransferencia) {
		this.idTransferencia = idTransferencia;
	}
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
	public Transferencia(int idTransferencia, int numeroCuentaOrigen, int numeroCuentaDestino, float importe) {
		super();
		this.idTransferencia = idTransferencia;
		this.numeroCuentaOrigen = numeroCuentaOrigen;
		this.numeroCuentaDestino = numeroCuentaDestino;
		this.importe = importe;
	}
	
	public Transferencia(){};
}
