package entidades;

public class TipoCuenta {
private int idTipo;
private String nombre;

public TipoCuenta() {
	
}


public TipoCuenta(int idTipo, String nombre) {
	super();
	this.idTipo = idTipo;
	this.nombre = nombre;
}


public int getIdTipo() {
	return idTipo;
}
public void setIdTipo(int idTipo) {
	this.idTipo = idTipo;
}
public String getNombre() {
	return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}


@Override
public String toString() {
	return "TipoCuenta [idTipo=" + idTipo + ", nombre=" + nombre + "]";
}



}
