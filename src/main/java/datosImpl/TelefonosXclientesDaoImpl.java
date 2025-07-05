package datosImpl;

import java.sql.PreparedStatement;

import datos.TelefonosXclientesDao;
import entidades.TelefonosXclientes;

public class TelefonosXclientesDaoImpl implements TelefonosXclientesDao {
Conexion cn;
	@Override
	public boolean modificar(TelefonosXclientes telXcli) {
		boolean modificado = false;
		
		String query="UPDATE TELEFONOSXCLIENTES SET telefono_TxC = ?,DNI_TxC = ?";
		cn = new Conexion();
		cn.Open();
		try {
			PreparedStatement pst = cn.prepare(query);
			pst.setString(1, telXcli.getTelefono());
			pst.setString(2, telXcli.getDniCliente());
			
			int filas = pst.executeUpdate();
			
			if(filas ==1) {
				modificado = true;
			}
			pst.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		cn.close();
		return modificado;
	}

}
