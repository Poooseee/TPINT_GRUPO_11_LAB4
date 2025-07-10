package entidades;

import java.sql.Date;

public class Movimiento {
    private int id;
    private Date fecha;
    private String nroCuenta;
    private String detalle;
    private float importe;
    private String tipo;
    
    //Constructor
    public Movimiento(int id, Date fecha, String nroCuenta, String detalle, float importe, String tipo) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.nroCuenta = nroCuenta;
		this.detalle = detalle;
		this.importe = importe;
		this.tipo = tipo;
	}
    
    public Movimiento() {}
    
    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }
    
    public String getNroCuenta() { return nroCuenta; }
    public void setNroCuenta(String nroCuenta) { this.nroCuenta = nroCuenta; }
    
    public String getDetalle() { return detalle; }
    public void setDetalle(String detalle) { this.detalle = detalle; }
    
    public float getImporte() { return importe; }
    public void setImporte(float importe) { this.importe = importe; }
    
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

	@Override
	public String toString() {
		return "movimiento [id=" + id + ", fecha=" + fecha + ", nroCuenta=" + nroCuenta + ", detalle=" + detalle
				+ ", importe=" + importe + ", tipo=" + tipo + "]";
	}

}
