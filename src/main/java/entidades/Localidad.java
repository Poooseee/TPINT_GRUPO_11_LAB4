package entidades;

public class Localidad {
	private int id;
	private int idProvincia;
	private int idPais;
	private String Nombre;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdProvincia() {
		return idProvincia;
	}
	public void setIdProvincia(int idProvincia) {
		this.idProvincia = idProvincia;
	}
	public int getIdPais() {
		return idPais;
	}
	public void setIdPais(int idPais) {
		this.idPais = idPais;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public Localidad(int id, int idProvincia, int idPais, String nombre) {
		super();
		this.id = id;
		this.idProvincia = idProvincia;
		this.idPais = idPais;
		Nombre = nombre;
	}
	public Localidad(String nombre) {
		super();
		
		Nombre = nombre;
	}
	public Localidad() {}
	@Override
	public String toString() {
		return "Localidad [id=" + id + ", idProvincia=" + idProvincia + ", idPais=" + idPais + ", Nombre=" + Nombre
				+ "]";
	};	
}

