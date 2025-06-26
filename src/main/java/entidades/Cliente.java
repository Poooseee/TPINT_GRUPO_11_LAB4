package entidades;

import java.sql.Date;

public class Cliente {
	private String DNI;
	private String CUIL;
	private String nombre;
	private String apellido;
	private Sexo sexo;
	private Pais nacionalidad;
	private Date fechaNacimiento;
	private String domicilio;
	private Localidad localidad;
	private Provincia provincia;
	private String email;
	private String telefono;
	private Boolean baja;
	
	
	//GETTERS & SETTERS
	public String getDNI() {
		return DNI;
	}
	public void setDNI(String dNI) {
		DNI = dNI;
	}
	public String getCUIL() {
		return CUIL;
	}
	public void setCUIL(String cUIL) {
		CUIL = cUIL;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public Sexo getSexo() {
		return sexo;
	}
	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}
	public Pais getNacionalidad() {
		return nacionalidad;
	}
	public void setNacionalidad(Pais nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
	public Localidad getLocalidad() {
		return localidad;
	}
	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}
	public Provincia getProvincia() {
		return provincia;
	}
	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public Boolean getBaja() {
		return baja;
	}
	public void setBaja(Boolean baja) {
		this.baja = baja;
	}
	
	
	//CONSTRUCTORES Y TO STRING
	public Cliente(String dNI, String cUIL, String nombre, String apellido, Sexo sexo, Pais nacionalidad,
			Date fechaNacimiento, String domicilio, Localidad localidad, Provincia provincia, String email, String telefono,
			Boolean baja) {
		super();
		DNI = dNI;
		CUIL = cUIL;
		this.nombre = nombre;
		this.apellido = apellido;
		this.sexo = sexo;
		this.nacionalidad = nacionalidad;
		this.fechaNacimiento = fechaNacimiento;
		this.domicilio = domicilio;
		this.localidad = localidad;
		this.provincia = provincia;
		this.email = email;
		this.telefono = telefono;
		this.baja = baja;
	}
	
	public Cliente() {}
	@Override
	public String toString() {
		return "Cliente [DNI=" + DNI + ", CUIL=" + CUIL + ", nombre=" + nombre + ", apellido=" + apellido + ", sexo="
				+ sexo + ", nacionalidad=" + nacionalidad + ", fechaNacimiento=" + fechaNacimiento + ", domicilio="
				+ domicilio + ", localidad=" + localidad + ", provincia=" + provincia + ", email=" + email + ", telefono=" + telefono +", baja="
				+ baja + "]";
	};
	
	
}
