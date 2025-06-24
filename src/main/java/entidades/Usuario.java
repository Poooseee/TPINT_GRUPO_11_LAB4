package entidades;

public class Usuario {
	
	private int idUsuario;
	private String nickUsuario;
	private String contraseñaUsuario;
	private String tipoUsuario;
	
	public Usuario(String nombre,String contraseña) {
		super();
		this.nickUsuario = nombre;
		this.contraseñaUsuario = contraseña;
	}
	
	
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int id_suario) {
		this.idUsuario = id_suario;
	}
	public String getNickUsuario() {
		return nickUsuario;
	}
	public void setNickUsuario(String nickUsuario) {
		this.nickUsuario = nickUsuario;
	}
	public String getContraseñaUsuario() {
		return contraseñaUsuario;
	}
	public void setContraseñaUsuario(String contraseñaUsuario) {
		this.contraseñaUsuario = contraseñaUsuario;
	}
	public String getTipoUsuario() {
		return tipoUsuario;
	}
	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	
	public Usuario(int id_suario, String nickUsuario, String contraseñaUsuario, String tipoUsuario) {
		super();
		this.idUsuario = id_suario;
		this.nickUsuario = nickUsuario;
		this.contraseñaUsuario = contraseñaUsuario;
		this.tipoUsuario = tipoUsuario;
	}
	
	public Usuario() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Usuario [id_suario=" + idUsuario + ", nickUsuario=" + nickUsuario + ", contraseñaUsuario="
				+ contraseñaUsuario + ", tipoUsuario=" + tipoUsuario + "]";
	}
	
	
}
