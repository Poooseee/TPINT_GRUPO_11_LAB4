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
	    
	    System.out.println("\n--- INICIO DE DEPURACIÓN UsuarioDaoImpl ---");
	    System.out.println("Credenciales recibidas:");
	    System.out.println("Usuario: " + usuario.getNickUsuario());
	    System.out.println("Contraseña: " + usuario.getContraseñaUsuario());
	    
	    try {
	        cn = new Conexion();
	        cn.Open();
	        
	        // CONSULTA 100% CORRECTA CON TUS NOMBRES DE COLUMNA
	        String query = "SELECT idUsuario_Usr, tipo_Usr FROM usuarios WHERE nick_Usr = ? AND contraseña_Usr = ?";
	        System.out.println("\nConsulta SQL exacta: " + query);
	        
	        PreparedStatement ps = cn.prepare(query);
	        ps.setString(1, usuario.getNickUsuario());
	        ps.setString(2, usuario.getContraseñaUsuario());
	        
	        ResultSet rs = ps.executeQuery();
	        
	        if(rs.next()) {
	            u = new Usuario();
	            // USANDO LOS NOMBRES EXACTOS DE TUS COLUMNAS
	            u.setIdUsuario(rs.getInt("idUsuario_Usr"));  // Cambiado a idUsuario_Usr
	            u.setTipoUsuario(rs.getString("tipo_Usr"));   // Cambiado a tipo_Usr
	            
	            System.out.println("\nUsuario encontrado en BD:");
	            System.out.println("ID: " + u.getIdUsuario());
	            System.out.println("Tipo: " + u.getTipoUsuario());
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
	    
	    System.out.println("--- FIN DE DEPURACIÓN UsuarioDaoImpl ---\n");
	    return u;
	}
}
