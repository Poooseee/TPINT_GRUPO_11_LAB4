package datosImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import datos.UsuarioDao;
import entidades.Usuario;

public class UsuarioDaoImpl implements UsuarioDao {
	
	private Conexion cn;
	
	@Override
	public Usuario login(Usuario usuario) throws SQLException {
		Usuario u = null;
		
		try {
			cn = new Conexion();
			cn.Open();
			
			String query = "SELECT idUsuario_Usr as id, tipo_Usr as tipo FROM USUARIOS WHERE nick_Usr = ? and contraseña_Usr = ?";
			
			PreparedStatement ps = cn.prepare(query);
			ps.setString(1, usuario.getNickUsuario());
			ps.setString(2, usuario.getContraseñaUsuario());
			
			ResultSet rs = ps.executeQuery();
			System.out.println();
			if(rs.next()) {
				u = new Usuario();
				u.setIdUsuario(rs.getInt("id"));
				u.setTipoUsuario(rs.getString("tipo"));
				System.out.println("usuario en dao: "+u.getIdUsuario());
				System.out.println("usuario en dao: "+u.getTipoUsuario());
			}
			
			rs.close();
			ps.close();
			cn.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return u;
	}

}
