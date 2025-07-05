package entidades;

public class TelefonosXclientes {
private String telefono;
private String dni;
public TelefonosXclientes() {
	
}
public TelefonosXclientes(String telefono,String dni) {
	this.telefono = telefono;
	this.dni = dni;
}
public String getTelefono() {
	return telefono;
}
public void setTelefono(String telefono) {
	this.telefono = telefono;
}
public String getDniCliente() {
	return dni;
}
public void setDniCliente(String dni) {
	this.dni = dni;
}

}
