package entidades;

public class Sexo {
	private String sexo;
	public String getSexo() {
		return sexo;
	}
	public Sexo() {};
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public Sexo(String sexo) {
		super();
		this.sexo = sexo;
	}
	@Override
	public String toString() {
		return "Sexo [sexo=" + sexo + "]";
	}

}
