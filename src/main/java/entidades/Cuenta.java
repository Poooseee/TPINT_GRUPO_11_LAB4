package entidades;

public class Cuenta {
private int numero;
private String dni;
private String fechaCreacion;
private TipoCuenta tipo;
private String cbu;
private float saldo;
private Boolean baja;

public Cuenta() {
	
}

public int getNumero() {
	return numero;
}
public void setNumero(int numero) {
	this.numero = numero;
}
public String getDni() {
	return dni;
}
public void setDni(String dni) {
	this.dni = dni;
}
public String getFechaCreacion() {
	return fechaCreacion;
}
public void setFechaCreacion(String fechaCreacion) {
	this.fechaCreacion = fechaCreacion;
}
public TipoCuenta getTipo() {
	return tipo;
}
public void setTipo(TipoCuenta tipo) {
	this.tipo = tipo;
}
public String getCbu() {
	return cbu;
}
public void setCbu(String cbu) {
	this.cbu = cbu;
}
public float getSaldo() {
	return saldo;
}
public void setSaldo(float saldo) {
	this.saldo = saldo;
}


public Boolean getBaja() {
	return baja;
}

@Override
public String toString() {
	return "Cuenta [numero=" + numero + ", dni=" + dni + ", fechaCreacion=" + fechaCreacion + ", tipo=" + tipo
			+ ", cbu=" + cbu + ", saldo=" + saldo + ", baja=" + baja + "]";
}

public void setBaja(Boolean baja) {
	this.baja = baja;
}



}
