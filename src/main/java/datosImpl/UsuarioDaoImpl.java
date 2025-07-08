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
	        
	        String query = "SELECT idUsuario_Usr, tipo_Usr FROM usuarios WHERE nick_Usr = ? AND contraseña_Usr = ?";
	        
	        PreparedStatement ps = cn.prepare(query);
	        ps.setString(1, usuario.getNickUsuario());
	        ps.setString(2, usuario.getContraseñaUsuario());
	        
	        ResultSet rs = ps.executeQuery();
	        
	        if(rs.next()) {
	            u = new Usuario();
	            u.setIdUsuario(rs.getInt("idUsuario_Usr")); 
	            u.setTipoUsuario(rs.getString("tipo_Usr")); 

	        } else {
	            System.out.println("\nNo se encontró usuario con esas credenciales");
	        }
	        
	        rs.close();
	        ps.close();
	    }
	    catch(Exception e) {
	        System.out.println("\nERROR en UsuarioDaoImpl:");
	        e.printStackTrace();
	    }
	    finally {
	        if(cn != null) {
	            cn.close();
	        }
	    }
	    
	    return u;
	}
}
