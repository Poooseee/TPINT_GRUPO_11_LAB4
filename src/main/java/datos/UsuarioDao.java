package datos;

import java.sql.SQLException;

import entidades.Usuario;

public interface UsuarioDao {
	 Usuario login(Usuario usuario) throws SQLException; //funcion que recibe un usuario y hace el login
}

