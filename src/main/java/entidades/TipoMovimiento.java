package entidades;

public class TipoMovimiento {
	private int idTipoMovimiento;
	private String descripcion;
	public int getIdTipoMovimiento() {
		return idTipoMovimiento;
	}
	public void setIdTipoMovimiento(int idTipoMovimiento) {
		this.idTipoMovimiento = idTipoMovimiento;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public TipoMovimiento(int idTipoMovimiento, String descripcion) {
		super();
		this.idTipoMovimiento = idTipoMovimiento;
		this.descripcion = descripcion;
	}
	
	public TipoMovimiento() {
		
	};
}
