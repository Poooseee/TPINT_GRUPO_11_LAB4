package entidades;

public class Pais {
	
	private int id;
	private String nombre;
	private String nacionalidad;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNacionalidad() {
		return nacionalidad;
	}
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	public Pais(int id, String nombre, String nacionalidad) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.nacionalidad = nacionalidad;
	}
	
	public Pais(String nombre,String nacionalidad) {
		super();
		this.nombre = nombre;
		this.nacionalidad = nacionalidad;
	}
	public Pais() {};
	@Override
	public String toString() {
		return "Pais [id=" + id + ", nombre=" + nombre + ", nacionalidad=" + nacionalidad + "]";
	}
}
